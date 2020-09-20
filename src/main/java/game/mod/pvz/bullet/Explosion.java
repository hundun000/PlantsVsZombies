package game.mod.pvz.bullet;

import java.awt.Rectangle;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.plant.BasePlant;
import game.entity.zombie.BaseZombie;
import game.entity.zombie.ZombieModel;

/**
 * @author hundun
 * Created on 2020/09/16
 */
public class Explosion extends BaseBullet {
    private static Logger logger = LoggerFactory.getLogger(Explosion.class);
    public static String CHERRYBOMB_EXPLOSION_NAME = "cherrybomb_explosion";
    public static String POTETO_MINE_EXPLOSION_NAME = "poteto_mine_explosion";
    
//    public enum ExplosionSubType {
//        CHERRYBOMB_EXPLOSION(0),
//        POTETO_MINE_EXPLOSION(1),
//        ;
//        
//        
//        private final int subTypeId;
//        
//        private ExplosionSubType(int subTypeId) {
//            this.subTypeId = subTypeId;
//        }
//        
//        public int getSubTypeId() {
//            return subTypeId;
//        }
//    }
    
    public Explosion(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, model, params);
    }
    double DELAY_DISAPPEAR_SECONDS = 2;
    int delayDisappearCount;
    boolean delayDisappearing = false;
    
    @Override
    protected void attack() {
        if (!delayDisappearing) {
            Rectangle bulletRect = this.getPositionComponent().getCoillderBox();
            List<BaseZombie> zombies = gamePanel.getZombieManager().getZombiesIntersected(bulletRect);
            for (BaseZombie zombie : zombies) {
                bulletHitZombie(zombie);
            }
            logger.debug(instanceName + " hit " + zombies.size() + " zombies.");
            delayDisappearCount = (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * DELAY_DISAPPEAR_SECONDS);
            delayDisappearing = true;
        } else {
            delayDisappearCount--;
            if (delayDisappearCount <= 0) {
                this.getHealthComponent().forceKilled();
            }
        }
        
    }

}
