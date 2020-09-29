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
public class FrozenPeashooterBuilder extends PeashooterBuilder {
    public static final String NAME = "frozen_peashooter";
    
    
    @Override
    public PlantModel start() {
        PlantModel model;
        
        model = new PlantModel(NAME, ShooterPlant.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
                .build(WorkState.WORKING, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING))
                ;
        model.plantCost = 175;
        return model;
    }
    
    @Override
    protected void build(PlantModel model) {
        super.build(model);
        model.fightData.bulletSubTypeId = DebuffType.FREEZE.getId();
    }


}
