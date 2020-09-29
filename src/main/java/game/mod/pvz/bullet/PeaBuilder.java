package game.mod.pvz.bullet;

import java.util.Arrays;

import game.entity.bullet.BulletModel;
import game.entity.bullet.BulletModelBuilder;
import game.entity.bullet.template.DebuffBullect;
import game.entity.gameobject.Spirit;
import game.mod.pvz.PvzMod;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/24
 */
public class PeaBuilder extends BulletModelBuilder{

    public static final String NAME = "pea";
    public final String modName = PvzMod.NAME;
    
    
    @Override
    protected BulletModel start() {
        BulletModel model;
        model = new BulletModel(NAME, DebuffBullect.class);
        model.spirit = new Spirit(
                Arrays.asList(
                        ImageLoadTool.loadOneBulletImage(modName, model.registerName + "_0"),
                        ImageLoadTool.loadOneBulletImage(modName, model.registerName + "_1")
                        )
                );
        return model;
    }

    @Override
    protected void build(BulletModel model) {
        BulletModelBuilderTool.buildeDefaultSpeedAndRange(model);
    }

}
