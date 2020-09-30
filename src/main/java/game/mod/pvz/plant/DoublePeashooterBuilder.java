package game.mod.pvz.plant;

import game.entity.bullet.template.DebuffBullect.DebuffType;
import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.PlantModel;
import game.entity.plant.template.ShooterPlant;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/23
 */
public class DoublePeashooterBuilder extends PeashooterBuilder {
    public static final String NAME = "double_peashooter";
    
    
    @Override
    public PlantModel start() {
        PlantModel model;
        
        model = new PlantModel(NAME, ShooterPlant.class);
        model.spirit = new Spirit(model.registerName, 
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE),
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING)
                );
        model.plantCost = 200;
        
        return model;
    }
    
    @Override
    protected void build(PlantModel model) {
        super.build(model);
        model.fightData.attackContinuousTime = 2;
    }

}
