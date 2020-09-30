package game.mod.pvz.plant;

import game.entity.gameobject.GameObjectModel;
import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantModel;
import game.entity.plant.PlantModelBuilder;
import game.entity.plant.template.DropPlant;
import game.mod.pvz.PvzMod;
import game.mod.pvz.drop.SunItem;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/23
 */
public class SunflowerBuilder extends PlantModelBuilder {

    public static final String NAME = "sunflower";
    public final String modName = PvzMod.NAME;
    


    @Override
    public PlantModel start() {
        PlantModel model;
        model = new PlantModel(NAME, DropPlant.class);
        model.spirit = new Spirit(model.registerName, 
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE),
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING)
                );
        model.plantCost = 50;
        return model;
    }

    @Override
    protected void build(PlantModel model) {
        PlantModelBuilderTool.buildDropSkill(model.fightData, SunItem.NAME);
    }

}
