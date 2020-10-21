package game.entity.plant.template;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.*;

import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.bullet.template.DebuffBullect;
import game.entity.bullet.template.DebuffBullect.DebuffType;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.entity.zombie.BaseZombie;
import game.manager.GridManager;
import game.ui.GamePanel;

/**
 * Created by Armin on 6/25/2016.
 */
public class ShooterPlant extends BasePlant {
    //public static final String PEASHOOTER_NAME = "peashooter";
    //public static final String DOUBLE_PEASHOOTER_NAME = "double_peashooter";
    //public static final String FROZEN_PEASHOOTER_NAME = "frozen_peashooter";
    
    
    public ShooterPlant(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
    }

    @Override
    protected boolean wantAttack() {
        return wantAttackByAttackRangeBox();
    }

    @Override
    protected void attack() {
        attackByGenerateBullet();
    }

    @Override
    protected boolean wantUseSkill() {
        return false;
    }

    @Override
    protected void useSkill() {
        
    }


}
