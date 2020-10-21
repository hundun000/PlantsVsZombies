package game.entity.plant.template;

import java.awt.Rectangle;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.bullet.BaseBullet;
import game.entity.gameobject.FightObject;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.ui.GamePanel;

/**
 * @author hundun
 * Created on 2020/10/10
 */
public class PitcherPlant extends BasePlant {

    private static Logger logger = LoggerFactory.getLogger(BasePlant.class);
    
    public PitcherPlant(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
    }

    @Override
    protected boolean wantAttack() {
        return wantAttackByAttackRangeBox();
    }

    @Override
    protected void attack() {
        attackByGenerateGuidedBullet();
    }

    private void attackByGenerateGuidedBullet() {
        BaseBullet bullet = generateBullet();
        if (bullet != null) {
            Rectangle attackRect = this.getAttackRangeBox();
            if (attackRect != null) {
                List<FightObject> intersecteds = gamePanel.getGridManager().getIntersectedOtherSideFightObjects(attackRect, fightSide);
                if (intersecteds.size() > 0) {
                    bullet.setTarget(intersecteds.get(0));
                    logger.info("{}'s target set to {}", this, intersecteds.get(0));
                }
            }
            gamePanel.getGridManager().addBullet(bullet);
        }
    }

    @Override
    protected boolean wantUseSkill() {
        return false;
    }

    @Override
    protected void useSkill() {
        
    }

}
