package game.entity.gameobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.ILogicFrameListener;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.drop.DropInstanceParams;
import game.entity.drop.DropModel;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.gameobject.WorkStatus.WorkType;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.entity.zombie.ZombieInstanceParams;
import game.entity.zombie.ZombieModel;

/**
 * @author hundun
 * Created on 2020/09/14
 */
public class GameObjectStatus implements ILogicFrameListener {
    private static Logger logger = LoggerFactory.getLogger(GameObjectStatus.class);
    

    
    
    
    private final FightObject fightObject;
    
    
    public static final int NO_SUBTYPE_INDEX = 0;
    private int subTypeId = NO_SUBTYPE_INDEX;
    
    private WorkStatus attackStatus;
    private WorkStatus skillStatus;
    

    
    public GameObjectStatus(FightObject fightObject, DropModel model, DropInstanceParams params) {
        this(fightObject, 
                new WorkStatus(WorkType.WORK_IF_WANT, WorkState.WORKING, -1, -1, -1, -1),
                new WorkStatus()
                );
    }
    
    public GameObjectStatus(FightObject fightObject, BulletModel model, BulletInstanceParams params) {
        this(fightObject, 
                new WorkStatus(WorkType.WORK_IF_WANT, WorkState.WORKING, -1, -1, -1, -1),
                new WorkStatus()
                );
    }
    
    public GameObjectStatus(FightObject fightObject, ZombieModel model, ZombieInstanceParams params) {
        this(fightObject, 
                new WorkStatus(WorkType.WORK_IF_WANT, WorkState.IDLE, -1, 20, -1, 10),
                new WorkStatus()
                );
    }

    public GameObjectStatus(FightObject fightObject, PlantModel model, PlantInstanceParams params) {
        this(fightObject, 
                new WorkStatus(
                        WorkType.LOOP, WorkState.IDLE, 
                        model.attackColdDownFrameNum,
                        model.attackStartFrameNum,
                        model.attackContinuousTime,
                        model.attackContinuousDelayFrameNum
                ),
                new WorkStatus(
                        WorkType.LOOP, WorkState.IDLE, 
                        model.skillColdDownFrameNum,
                        model.skillStartFrameNum,
                        1,
                        0
                )
        );
    }
    
    private GameObjectStatus(
            FightObject fightObject,
            WorkStatus attackStatus,
            WorkStatus skillStatus
            ) {
        this.fightObject = fightObject;
        this.attackStatus = attackStatus;
        this.skillStatus = skillStatus;
    }

    

    

    @Override
    public void updateLogicFrame() {
        
        boolean workHappen = attackStatus.transition(fightObject.wantAttack());
        if (workHappen) {
            fightObject.attack();
        }
        
        

    }
    
    public WorkStatus getAttackStatus() {
        return attackStatus;
    }

    
    
    public int getSubTypeId() {
        return subTypeId;
    }
    
    public void setSubTypeId(int subTypeId) {
        this.subTypeId = subTypeId;
    }
    
}
