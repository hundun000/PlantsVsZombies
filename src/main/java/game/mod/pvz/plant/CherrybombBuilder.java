package game.mod.pvz.plant;

import game.entity.bullet.template.Explosion;
import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.PlantModel;
import game.entity.plant.PlantModelBuilder;
import game.entity.plant.template.BombPlant;
import game.manager.GridManager;
import game.mod.pvz.PvzMod;
import game.mod.pvz.bullet.CherrybombExplosionBuilder;
import game.ui.GamePanel;
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
        model.spirit = new Spirit(model.registerName, 
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE),
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING)
                );
        model.plantCost = 150;
        return model;
    }

    @Override
    protected void build(PlantModel model) {
        PlantModelBuilderTool.buildBullet(model.fightData, CherrybombExplosionBuilder.NAME);
        PlantModelBuilderTool.buildDefaultPlantAttackLoop(model.fightData);
        model.fightData.attackColdDownFrameNum = (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.5);
        model.fightData.bulletStartOffsetX = - GridManager.GRID_WIDTH;
        model.fightData.bulletStartOffsetY = GridManager.GRID_HEIGHT;
    }

}
