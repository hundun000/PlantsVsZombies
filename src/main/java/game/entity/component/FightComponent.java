package game.entity.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.ILogicFrameListener;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.drop.DropInstanceParams;
import game.entity.drop.DropModel;
import game.entity.gameobject.FightData;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.WorkStatus;
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
public class FightComponent {
    private static Logger logger = LoggerFactory.getLogger(FightComponent.class);
    

    
    
    
    private final FightObject fightObject;
    
    
    
    
    private WorkStatus attackStatus;
    private WorkStatus skillStatus;
    


   
    
    public FightComponent(FightObject fightObject, ZombieModel model, ZombieInstanceParams params) {
        this(fightObject, model.fightData);
    }

    public FightComponent(FightObject fightObject, PlantModel model, PlantInstanceParams params) {
        this(fightObject, model.fightData);
    }
    
    public FightComponent(FightObject fightObject, FightData fightData) {
        this.fightObject = fightObject;
        if (fightData.attackWorkType != null) {
            this.attackStatus = new WorkStatus(
                    fightData.attackWorkType, 
                    fightData.attackInitState, 
                    fightData.attackColdDownFrameNum,
                    fightData.attackStartFrameNum,
                    fightData.attackContinuousTime,
                    fightData.attackContinuousDelayFrameNum
            );
        } else {
            this.attackStatus = new WorkStatus();
        }
        if (fightData.skillWorkType != null) {
            this.skillStatus = new WorkStatus(
                    fightData.skillWorkType, 
                    fightData.skillInitState, 
                        fightData.skillColdDownFrameNum,
                        fightData.skillStartFrameNum,
                        1,
                        0
           );
        } else {
            this.skillStatus = new WorkStatus();
        }
    }

    public WorkStatus getSkillStatus() {
        return skillStatus;
    }

    
    public WorkStatus getAttackStatus() {
        return attackStatus;
    }

    
    

}
