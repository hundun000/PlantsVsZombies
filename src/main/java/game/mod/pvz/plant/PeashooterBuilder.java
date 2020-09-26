package game.mod.pvz.plant;

import game.entity.bullet.template.DebuffBullect;
import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.PlantModel;
import game.entity.plant.PlantModelBuilder;
import game.entity.plant.template.ShooterPlant;
import game.mod.pvz.PvzMod;
import game.mod.pvz.bullet.PeaBuilder;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/23
 */
public class PeashooterBuilder extends PlantModelBuilder {

    public static final String NAME = "peashooter";
    public final String modName = PvzMod.NAME;
    

    @Override
    public PlantModel start() {
        PlantModel model;
        
        model = new PlantModel(NAME, ShooterPlant.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
                .build(WorkState.WORKING, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING))
                ;
        model.plantCost = 100;
        return model;
    }

    @Override
    protected void build(PlantModel model) {
        model.fightData
            .buildBullet(PeaBuilder.NAME)
            .buildDefaultPlantAttackRange()
            .buildDefaultPlantAttackLoop();
    }

}
