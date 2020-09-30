package game.mod.pvz.zombie;

import game.entity.gameobject.Spirit;
import game.entity.zombie.ZombieModel;
import game.entity.zombie.ZombieModelBuilder;
import game.entity.zombie.template.NormalZombie;
import game.mod.pvz.PvzMod;
import game.mod.pvz.bullet.ZombieHitBuilder;
import game.mod.pvz.plant.PlantModelBuilderTool;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/24
 */
public class PlainZombieBuilder extends ZombieModelBuilder {

    public static final String NAME = "plain_zombie";
    public final String modName = PvzMod.NAME;
    
    @Override
    protected ZombieModel start() {
        ZombieModel model;
        model = new ZombieModel(NAME, NormalZombie.class);
        model.spirit = new Spirit(model.registerName, ImageLoadTool.loadOneZombieImage(modName, model.registerName));
        return model;
    }

    @Override
    protected void build(ZombieModel model) {
        PlantModelBuilderTool.buildBullet(model.fightData, ZombieHitBuilder.NAME);
        PlantModelBuilderTool.buildSelfGridAttackRange(model.fightData);
        PlantModelBuilderTool.buildDefaultZombieAttackLoop(model.fightData);
    }

}
