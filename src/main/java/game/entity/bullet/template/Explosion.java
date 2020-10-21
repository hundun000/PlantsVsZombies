package game.entity.bullet.template;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.gameobject.FightObject;
import game.entity.plant.BasePlant;
import game.entity.zombie.BaseZombie;
import game.entity.zombie.ZombieModel;
import game.ui.GamePanel;

/**
 * @author hundun
 * Created on 2020/09/16
 */
public class Explosion extends BaseBullet {
    private static Logger logger = LoggerFactory.getLogger(Explosion.class);
    //public static String CHERRYBOMB_EXPLOSION_NAME = "cherrybomb_explosion";
    //public static String POTETO_MINE_EXPLOSION_NAME = "poteto_mine_explosion";
    
    
    public Explosion(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, model, params);
        super.forceKilledAfterHit = false;
    }
    
    double DELAY_DISAPPEAR_SECONDS = 2;
    Integer delayDisappearCount;
       
    @Override
    protected List<FightObject> calculateHitTargets() {
        if (delayDisappearCount == null) {
            Rectangle bulletRect = this.getPositionComponent().getCoillderBox();
            List<FightObject> zombies = gamePanel.getGridManager().getIntersectedOtherSideFightObjects(bulletRect, attacterSide);
            logger.debug(instanceName + " hit " + zombies.size() + " zombies.");
            return zombies;
        } else {
            return new ArrayList<>(0);
        }

    }


    @Override
    protected void onMoveDone() {
        if (delayDisappearCount == null) {
            delayDisappearCount = (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * DELAY_DISAPPEAR_SECONDS);
        } else {
            delayDisappearCount--;
            if (delayDisappearCount <= 0) {
                this.getHealthComponent().forceKilled();
            }
        }
    }
}
