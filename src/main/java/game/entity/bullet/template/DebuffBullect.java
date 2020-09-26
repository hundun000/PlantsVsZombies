package game.entity.bullet.template;
import java.awt.Rectangle;
import java.util.List;

import game.GamePanel;
import game.ILogicFrameListener;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.component.BulletPositionComponent;
import game.entity.component.FightComponent;
import game.entity.component.PositionComponent;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.GameObject;
import game.entity.zombie.BaseZombie;
import game.manager.GridManager;

/**
 * Created by Armin on 6/25/2016.
 */
public class DebuffBullect extends BaseBullet {
    //public static String NAME = "pea";
    
    public enum DebuffType {
        NORMAL(0),
        FREEZE(1),
        FIRE(2)
        ;
        
        private final int subTypeId;
        
        private DebuffType(int subTypeId) {
            this.subTypeId = subTypeId;
        }
        
        public int getSubTypeId() {
            return subTypeId;
        }
    }
    
    public DebuffBullect(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, model, params);
    }


    @Override
    public void addDebuff(FightObject targetObject) {
        super.addDebuff(targetObject);
        if (getSubTypeId() == DebuffType.FREEZE.subTypeId) {
            targetObject.setSlowDebuff(GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 3);
        }
    }
    

}
