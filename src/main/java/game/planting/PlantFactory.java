package game.planting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.entity.plant.BasePlant;
import game.entity.plant.Peashooter;
import game.entity.plant.Sunflower;
import game.manager.GridManager;

/**
 * @author hundun
 * Created on 2020/09/04
 */
public class PlantFactory {
    static Logger logger = LoggerFactory.getLogger(PlantFactory.class);
    public static BasePlant getPlant(String plantRegisterName, GamePanel gamePanel, int gridX, int gridY) {
        BasePlant plant = null;
        if (plantRegisterName != null) {
            if (plantRegisterName.equals(Sunflower.REGISTER_NAME)) {
                plant = new Sunflower(gamePanel, gridX * GridManager.GRID_WIDTH, gridY * GridManager.GRID_HEIGHT);
            } else if (plantRegisterName.equals(Peashooter.REGISTER_NAME)) {
                plant = new Peashooter(gamePanel, gridX * GridManager.GRID_WIDTH, gridY * GridManager.GRID_HEIGHT);
            }
        }
        if (plant == null) {
            logger.warn("cannot get plant by: {}", plantRegisterName);
        }
        return plant;
    }

}
