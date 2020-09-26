package game.mod.pvz.plant;

import game.GamePanel;
import game.entity.bullet.template.Explosion;
import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.PlantModel;
import game.entity.plant.PlantModelBuilder;
import game.entity.plant.template.BombPlant;
import game.manager.GridManager;
import game.mod.pvz.PvzMod;
import game.mod.pvz.bullet.CherrybombExplosionBuilder;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/24
 */
public class CherrybombBuilder extends PlantModelBuilder {

    public static final String NAME = "cherrybomb";
    public final String modName = PvzMod.NAME;
    

    @Override
    protected PlantModel start() {
        PlantModel model;
        
        model = new PlantModel(NAME, BombPlant.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
                .build(WorkState.WORKING, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING))
                ;
        model.plantCost = 150;
        return model;
    }

    @Override
    protected void build(PlantModel model) {
        model.fightData
                .buildBullet(CherrybombExplosionBuilder.NAME)
                .buildDefaultPlantAttackLoop();
        model.fightData.attackColdDownFrameNum = (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.5);
        model.fightData.bulletStartOffsetX = - GridManager.GRID_WIDTH;
        model.fightData.bulletStartOffsetY = GridManager.GRID_HEIGHT;
    }

}
