package game.mod.pvz.plant;

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
public class Cherrybomb extends BasePlant {
    
    public static final String NAME = "cherrybomb";
    public Cherrybomb(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
    }
    @Override
    protected boolean wantAttack() {
        return true;
    }
    @Override
    protected void attack() {
        super.attack();
        this.getHealthComponent().forceKilled();
    }
    

}
