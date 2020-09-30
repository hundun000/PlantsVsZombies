package game.mod.pvz.zombie;

import game.entity.gameobject.Spirit;
import game.entity.zombie.ZombieModel;
import game.entity.zombie.ZombieModelBuilder;
import game.entity.zombie.template.NormalZombie;
import game.mod.pvz.PvzMod;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/24
 */
public class ConeheadZombieBuilder extends PlainZombieBuilder {

    public static final String NAME = "conehead_zombie";
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
        super.build(model);
        model.health = model.health * 2;
    }

}
