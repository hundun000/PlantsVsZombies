package game.pvz.plant;

import game.GamePanel;
import game.gameobject.plant.PlantInstanceParams;
import game.gameobject.plant.PlantModel;

/**
 * @author hundun
 * Created on 2020/09/14
 */
public class DoublePeashooter extends Peashooter {
    public static final String NAME = "double_peashooter";
    
    
    public DoublePeashooter(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
    }

}
