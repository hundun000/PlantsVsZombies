package game.mod.pvz.plant.template;

import java.awt.Rectangle;
import java.util.List;

import game.GamePanel;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.template.DebuffBullect;
import game.entity.bullet.template.DebuffBullect.DebuffType;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.mod.pvz.bullet.PeaBuilder;

/**
 * @author hundun
 * Created on 2020/09/29
 */
public class TorchwoodPlant extends BasePlant {

    
    
    public TorchwoodPlant(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean wantAttack() {
        return false;
    }

    @Override
    protected void attack() {
    }

    @Override
    protected boolean wantUseSkill() {
        return true;
    }

    @Override
    protected void useSkill() {
        Rectangle chackRange = getAttackRangeBox();
        List<BaseBullet> bullets = gamePanel.getGridManager().getBullets();
        for (BaseBullet bullet : bullets) {
            if (bullet.getRegisterName().equals(PeaBuilder.NAME) 
                    && chackRange.intersects(bullet.getPositionComponent().getCoillderBox())
                    ) {
                if (bullet.getSubTypeName().equals(DebuffType.NONE.name())) {
                    bullet.setSubTypeName(DebuffType.FIRE.name());
                } else if (bullet.getSubTypeName().equals(DebuffType.FREEZE.name())) {
                    bullet.setSubTypeName(DebuffType.NONE.name());
                }
            }
        }
    }

}
