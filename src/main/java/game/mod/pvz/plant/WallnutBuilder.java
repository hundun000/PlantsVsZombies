package game.mod.pvz.plant;

import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.PlantModel;
import game.entity.plant.PlantModelBuilder;
import game.entity.plant.template.IldePlant;
import game.mod.pvz.PvzMod;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/24
 */
public class WallnutBuilder extends PlantModelBuilder {

    public static final String NAME = "wallnut";
    public final String modName = PvzMod.NAME;
    
    @Override
    protected PlantModel start() {
        PlantModel model;
        model = new PlantModel(IldePlant.NAME, IldePlant.class);
        model.spirit = new Spirit(model.registerName, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE));
        model.plantCost = 50;
        return model;
    }

    @Override
    protected void build(PlantModel model) {
        model.health = 3000;
    }

}
