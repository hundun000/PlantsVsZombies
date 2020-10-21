package game.mod.pvz.plant;

import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.PlantModel;
import game.entity.plant.PlantModelBuilder;
import game.entity.plant.template.PitcherPlant;
import game.entity.plant.template.ShooterPlant;
import game.mod.pvz.PvzMod;
import game.mod.pvz.bullet.KernelBuilder;
import game.mod.pvz.bullet.PeaBuilder;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/10/10
 */
public class KernelPitcherBuilder extends PlantModelBuilder {

    public static final String NAME = "kernel_pitcher";
    public final String modName = PvzMod.NAME;
    
    
    @Override
    protected PlantModel start() {
        PlantModel model;
        
        model = new PlantModel(NAME, PitcherPlant.class);
        model.spirit = new Spirit(model.registerName, 
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE)
                );
        model.plantCost = 100;
        return model;
    }

    @Override
    protected void build(PlantModel model) {
        PlantModelBuilderTool.buildBullet(model.fightData, KernelBuilder.NAME);
        PlantModelBuilderTool.buildPitcherPlantAttackRange(model.fightData);
        PlantModelBuilderTool.buildDefaultPlantAttackLoop(model.fightData);
    }

}
