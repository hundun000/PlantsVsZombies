package game.mod.pvz.plant;

import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.PlantModel;
import game.entity.plant.PlantModelBuilder;
import game.entity.plant.template.DropPlant;
import game.mod.pvz.PvzMod;
import game.mod.pvz.plant.template.TorchwoodPlant;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/29
 */
public class TorchwoodBuilder extends PlantModelBuilder {

    public static final String NAME = "torchwood";
    public final String modName = PvzMod.NAME;
    
    
    @Override
    protected PlantModel start() {
        PlantModel model;
        model = new PlantModel(NAME, TorchwoodPlant.class);
        model.spirit = new Spirit(model.registerName, 
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE),
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING)
                );
        model.plantCost = 175;
        return model;
    }

    @Override
    protected void build(PlantModel model) {
        PlantModelBuilderTool.buildAlwayWorkSkill(model.fightData);
    }

}
