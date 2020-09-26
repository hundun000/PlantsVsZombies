package game.entity.plant.template;

import game.GamePanel;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;

/**
 * @author hundun
 * Created on 2020/09/16
 */
public class BombPlant extends BasePlant {
    
    //public static final String NAME = "cherrybomb";
    public BombPlant(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
        getHealthComponent().changeToUncountableHealth();
    }
    @Override
    protected boolean wantAttack() {
        return true;
    }
    @Override
    protected void attack() {
        attackByGenerateBullet();
        this.getHealthComponent().forceKilled();
    }
    @Override
    protected boolean wantUseSkill() {
        return false;
    }
    @Override
    protected void useSkill() {
        
    }
    
    

}
