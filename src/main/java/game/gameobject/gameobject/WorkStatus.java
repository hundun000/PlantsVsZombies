package game.gameobject.gameobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.gameobject.bullet.BulletInstanceParams;
import game.gameobject.bullet.BulletModel;
import game.gameobject.drop.DropInstanceParams;
import game.gameobject.drop.DropModel;
import game.gameobject.gameobject.WorkStatus.WorkState;
import game.gameobject.gameobject.WorkStatus.WorkType;
import game.gameobject.plant.PlantInstanceParams;
import game.gameobject.plant.PlantModel;
import game.gameobject.zombie.ZombieInstanceParams;
import game.gameobject.zombie.ZombieModel;

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
         * 工作循环：IDLE -(countframes)-> WORK_READY -(wantwork)-> WORKING -(count continuoustime)-> IDLE
         */
        LOOP,
        /**
         * 一直处于：WORK_READY -(wantwork)-> WORKING -(not wantwork)-> WORK_READY
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
    
    public WorkStatus(DropModel model, DropInstanceParams params) {
        this(WorkType.WORK_IF_WANT, WorkState.WORKING, -1, -1, -1, -1);
    }
    
    public WorkStatus(BulletModel model, BulletInstanceParams params) {
        this(WorkType.WORK_IF_WANT, WorkState.WORKING, -1, -1, -1, -1);
    }
    
    public WorkStatus(ZombieModel model, ZombieInstanceParams params) {
        this(WorkType.WORK_IF_WANT, WorkState.IDLE, -1, 20, -1, 10);
    }

    public WorkStatus(PlantModel model, PlantInstanceParams params) {
        this(WorkType.LOOP, WorkState.IDLE, 
                model.workColdDownFrameNum,
                model.workStartFrameNum,
                model.workContinuousTime,
                model.workContinuousDelayFrameNum);
    }
    
    private WorkStatus(
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



