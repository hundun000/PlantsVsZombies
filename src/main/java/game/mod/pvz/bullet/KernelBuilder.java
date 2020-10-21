package game.mod.pvz.bullet;

import game.entity.bullet.BulletModel;
import game.entity.bullet.BulletModelBuilder;
import game.entity.bullet.template.DebuffBullect;
import game.entity.bullet.template.GuidedBullet;
import game.entity.gameobject.Spirit;
import game.mod.pvz.PvzMod;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/10/10
 */
public class KernelBuilder extends BulletModelBuilder {

    public static final String NAME = "kernel";
    public final String modName = PvzMod.NAME;
    
    @Override
    protected BulletModel start() {
        BulletModel model;
        model = new BulletModel(NAME, GuidedBullet.class);
        model.spirit = new Spirit(model.registerName, 
                ImageLoadTool.loadOneBulletImage(modName, model.registerName + "_0")
                );
        return model;
    }

    @Override
    protected void build(BulletModel model) {
        BulletModelBuilderTool.buildSmallCoillderBox(model);
        BulletModelBuilderTool.buildGuidedSpeedAndRange(model);
    }

}
