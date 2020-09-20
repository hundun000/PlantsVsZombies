package game.mod.pvz.plant;
/**
 * @author hundun
 * Created on 2020/09/17
 */

import game.GamePanel;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;

public class PotetoMine extends BasePlant {
    public static final String NAME = "poteto_mine";
    public PotetoMine(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
    }
    
    @Override
    protected void attack() {
        super.attack();
        this.getHealthComponent().forceKilled();
    }

}
