package game.entity.gameobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.drop.DropInstanceParams;
import game.entity.drop.DropModel;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.gameobject.WorkStatus.WorkType;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.entity.zombie.ZombieInstanceParams;
import game.entity.zombie.ZombieModel;

/**
 * @author hundun
 * Created on 2020/09/15
 */
public class WorkStatus {
    private static Logger logger = LoggerFactory.getLogger(WorkStatus.class);
    
    private int workProgress = 0;
    private int targetProgress = 1;
    private WorkState workState;
    private WorkType workType;
    /**
     * 工作循环【冷却】长度
     */
    public final int workColdDownFrameNum;
    /**
     * 工作循环【工作前摇】长度
     */
    public final int workStartFrameNum;
    /**
     * 工作循环【连续工作间隔】长度
     */
    public final int workContinuousDelayFrameNum;
    /**
     * 工作循环【工作】重复次数n
     */
    public final int workContinuousTime;
    private int currntWorkTime = 0;
    
    public enum WorkState {
        IDLE,
        WORK_READY,
        WORKING
    }

    public enum WorkType {
        /**
         * 工作循环：
         * (1): [IDLE] -(coldDownCount)-> [WORK_READY] 
         * (2): [WORK_READY] -(want work)-> [WORKING]
         * (3.1): [WORKING] -(count workStartFrameNum)-> workHappen
         * (3.2): [WORKING] -(not want work)-> [IDLE]
         * (4): workHappen -(count workContinuousDelayFrameNum)-> arrive workContinuousTime?
         * (5.1): arrive workContinuousTime? -(T)-> [IDLE]
         * (5.2): arrive workContinuousTime? -(F)-> [WORKING]
         */
        LOOP,
        /**
         * 工作循环：[WORK_READY] -(want work)-> [WORKING] -(not want work)-> [WORK_READY]
         */
        WORK_IF_WANT,
        /**
         * 一直处于：[IDLE]
         */
        ALWAY_IDLE
    }
    
    public WorkStatus() {
        this(WorkType.ALWAY_IDLE, WorkState.IDLE, -1, -1, -1, -1);
    }

    
    public WorkStatus(
            WorkType workType,
            WorkState initState,
            int workColdDownFrameNum, 
            int workStartFrameNum,
            int workContinuousTime,
            int workContinuousDelayFrameNum
            ) {
        this.workType = workType;
        this.workState = initState;
        
        this.workColdDownFrameNum = workColdDownFrameNum;
        this.workStartFrameNum = workStartFrameNum;
        this.workContinuousTime = workContinuousTime;
        this.workContinuousDelayFrameNum = workContinuousDelayFrameNum; 
    }
    
    
    public boolean transition(boolean wantWork) {
        boolean workHappen = false;
        WorkState nextWorkStatus = workState;
        
        switch (workState) {
            case IDLE:
                if (workType != WorkType.ALWAY_IDLE) {
                    if (workProgress >= workColdDownFrameNum) {
                        nextWorkStatus = WorkState.WORK_READY;
                        workProgress = 0;
                    } else {
                        workProgress++;
                    }
                    targetProgress = workColdDownFrameNum;
                } else {
                    workProgress = 0;
                    targetProgress = 1;
                }
                break;
            case WORK_READY:
                if (wantWork) {
                    nextWorkStatus = WorkState.WORKING;
                    workProgress = 0;
                    currntWorkTime = 0;
                }
                targetProgress = 0;
                break;
            case WORKING:
                if (workType == WorkType.LOOP) {
                    if (wantWork) {
                        if (workProgress == workStartFrameNum) {
                            workHappen = true;
                            currntWorkTime++;
                        }
                        targetProgress = workStartFrameNum + workContinuousDelayFrameNum;
                        if (currntWorkTime >= workContinuousTime) {
                            nextWorkStatus = WorkState.IDLE;
                            workProgress = 0;
                        } else {
                            if (workHappen) {
                                workProgress = 0;
                                workProgress = workStartFrameNum - workContinuousDelayFrameNum;
                            }
                            workProgress ++;
                        }
                    } else {
                        nextWorkStatus = WorkState.IDLE;
                        workProgress = 0;
                    }
                } else if(workType == WorkType.WORK_IF_WANT) {
                    if (wantWork) {
                        workHappen = true;
                    } else {
                        nextWorkStatus = WorkState.WORK_READY;
                    }
                }
                break;
            default:
                logger.error("未定义的nextWorkState规则: {}", workState);
                nextWorkStatus = WorkState.IDLE;
        }
        workState = nextWorkStatus;
        return workHappen;
    }
    
    public WorkState getWorkState() {
        return workState;
    }
    
    public double getProgressRate() {
        if (targetProgress == 0) {
            return 1;
        } else {
            return workProgress * 1.0 / targetProgress;
        }
    }
}



