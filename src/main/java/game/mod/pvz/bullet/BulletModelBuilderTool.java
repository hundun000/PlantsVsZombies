package game.mod.pvz.bullet;

import game.entity.bullet.BulletModel;
import game.manager.GridManager;

/**
 * @author hundun
 * Created on 2020/09/29
 */
public class BulletModelBuilderTool {
    
    
    public static BulletModel buildSmallCoillderBox(BulletModel model) {
        model.coillderBoxWidth = 20;
        model.coillderBoxHeight = 20;
        model.coillderBoxOffsetX = 0;
        model.coillderBoxOffsetY = - model.coillderBoxHeight;
        return model;
    }
    
    public static BulletModel buildCloseAttackCoillderBox(BulletModel model) {
        model.coillderBoxWidth = (int) (GridManager.GRID_WIDTH * 1);
        model.coillderBoxHeight = (int) (GridManager.GRID_HEIGHT * 0.5);
        model.coillderBoxOffsetX = (int) (- model.coillderBoxWidth * 0.5);
        model.coillderBoxOffsetY = - model.coillderBoxHeight;
        return model;
    }
    
    
    public static BulletModel buildDefaultSpeedAndRange(BulletModel model) {
        model.speedX = 15;
        model.speedY = 0;
        model.endDeltaX = 10 * GridManager.GRID_WIDTH;
        model.endDeltaY = 0;
        return model;
    }
    
    public static BulletModel buildGuidedSpeedAndRange(BulletModel model) {
        model.speedX = 10;
        model.speedY = 10;
        model.endDeltaX = null;
        model.endDeltaY = null;
        return model;
    }
    
    public static BulletModel buildNoSpeedAndRange(BulletModel model) {
        model.speedX = 0;
        model.speedY = 0;
        model.endDeltaX = 0;
        model.endDeltaY = 0;
        return model;
    }

}
