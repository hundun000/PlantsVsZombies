package game.pvz.bullet;
import java.awt.Rectangle;
import java.util.List;

import game.GamePanel;
import game.ILogicFrameListener;
import game.component.BulletPositionComponent;
import game.component.PositionComponent;
import game.gameobject.GameObject;
import game.gameobject.bullet.BaseBullet;
import game.gameobject.bullet.BulletInstanceParams;
import game.gameobject.bullet.BulletModel;
import game.gameobject.zombie.BaseZombie;
import game.manager.GridManager;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea extends BaseBullet {
    public static String NAME = "pea";

    private PeaType type;
    
    public enum PeaType {
        NORMAL,
        FREEZE,
        FIRE
    }
    
    public Pea(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, model, params);
    }

}
