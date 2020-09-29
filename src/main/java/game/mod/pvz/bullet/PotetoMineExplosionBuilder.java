package game.mod.pvz.bullet;

import java.util.Arrays;

import game.entity.bullet.BulletModel;
import game.entity.bullet.BulletModelBuilder;
import game.entity.bullet.template.Explosion;
import game.entity.gameobject.Spirit;
import game.manager.GridManager;
import game.mod.pvz.PvzMod;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/24
 */
public class PotetoMineExplosionBuilder extends BulletModelBuilder {

    public static final String NAME = "poteto_mine_explosion";
    public final String modName = PvzMod.NAME;
    
    @Override
    protected BulletModel start() {
        BulletModel model;
        model = new BulletModel(NAME, Explosion.class);
        model.spirit = new Spirit(
                Arrays.asList(
                        ImageLoadTool.loadOneBulletImage(modName, model.registerName)
                        )
                );
        return model;
    }

    @Override
    protected void build(BulletModel model) {
        model.coillderBoxHeight = GridManager.GRID_HEIGHT * 1;
        model.coillderBoxWidth = (int) (GridManager.GRID_WIDTH * 1.2);
        model.coillderBoxOffsetX = 0;
        model.coillderBoxOffsetY = - model.coillderBoxHeight;
        BulletModelBuilderTool.buildeNoSpeedAndRange(model);
        model.damage = 1000;
    }

}
