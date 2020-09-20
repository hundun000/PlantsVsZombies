package game.mod.pvz.bullet;
import java.awt.Rectangle;
import java.util.List;

import game.GamePanel;
import game.ILogicFrameListener;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.component.BulletPositionComponent;
import game.entity.component.PositionComponent;
import game.entity.gameobject.GameObject;
import game.entity.gameobject.GameObjectStatus;
import game.entity.zombie.BaseZombie;
import game.manager.GridManager;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea extends BaseBullet {
    public static String NAME = "pea";

    private PeaSubType type;
    
    public enum PeaSubType {
        NORMAL(0),
        FREEZE(1),
        FIRE(2)
        ;
        
        private final int subTypeId;
        
        private PeaSubType(int subTypeId) {
            this.subTypeId = subTypeId;
        }
        
        public int getSubTypeId() {
            return subTypeId;
        }
    }
    
    public Pea(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, model, params);
    }


    

}
