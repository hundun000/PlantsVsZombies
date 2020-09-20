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
    
    public int workProcess = 0;
    public WorkState workState;
    public WorkType workType;
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
         * 工作循环：IDLE -(coldDownCount)-> WORK_READY -(want work)-> WORKING -(count continuous-time)-> IDLE
         */
        LOOP,
        /**
         * 工作循环：WORK_READY -(want work)-> WORKING -(not want work)-> WORK_READY
         */
        WORK_IF_WANT,
        /**
         * 一直处于：IDLE
         */
        ALWAY_IDLE
    }
    
    public WorkStatus() {
        this(WorkType.ALWAY_IDLE, WorkState.IDLE, -1, -1, -1, -1);
    }
    @Deprecated
    public WorkStatus(DropModel model, DropInstanceParams params) {
        this(WorkType.WORK_IF_WANT, WorkState.WORKING, -1, -1, -1, -1);
    }
    @Deprecated
    public WorkStatus(BulletModel model, BulletInstanceParams params) {
        this(WorkType.WORK_IF_WANT, WorkState.WORKING, -1, -1, -1, -1);
    }
    @Deprecated
    public WorkStatus(ZombieModel model, ZombieInstanceParams params) {
        this(WorkType.WORK_IF_WANT, WorkState.IDLE, -1, 20, -1, 10);
    }

    @Deprecated
    public WorkStatus(PlantModel model, PlantInstanceParams params) {
        this(WorkType.LOOP, WorkState.IDLE, 
                model.attackColdDownFrameNum,
                model.attackStartFrameNum,
                model.attackContinuousTime,
                model.attackContinuousDelayFrameNum);
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
                    if (workProcess >= workColdDownFrameNum) {
                        nextWorkStatus = WorkState.WORK_READY;
                        workProcess = 0;
                    } else {
                        workProcess++;
                    }
                }
                break;
            case WORK_READY:
                if (wantWork) {
                    nextWorkStatus = WorkState.WORKING;
                    workProcess = 0;
                    currntWorkTime = 0;
                }
                break;
            case WORKING:
                if (workType == WorkType.LOOP) {
                    if (workProcess % workStartFrameNum == 0) {
                        workHappen = true;
                        currntWorkTime++;
                    }
                    if (currntWorkTime >= workContinuousTime) {
                        nextWorkStatus = WorkState.IDLE;
                        workProcess = 0;
                    } else {
                        workProcess++;
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
}



