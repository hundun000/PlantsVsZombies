package game.entity.plant;

import game.GamePanel;
import game.entity.gameobject.FightData;
import game.entity.gameobject.GameObjectModel;
import game.entity.gameobject.Spirit;
import game.factory.BulletFactory;
import game.factory.DropFactory;
import game.manager.GridManager;

/**
 * @author hundun
 * Created on 2020/09/08
 */
public class PlantModel extends GameObjectModel<BasePlant> {
    public PlantModel(String registerName, Class<? extends BasePlant> clazz) {
        super(registerName, clazz);
    }
    
    public int plantCost = 0;
    public int health = 1000;
    
    public int coillderBoxWidth = GridManager.GRID_WIDTH - defaultOffset * 2;
    public int coillderBoxHeight = GridManager.GRID_HEIGHT - defaultOffset * 2;
    public int coillderBoxOffsetX = defaultOffset;
    public int coillderBoxOffsetY = - coillderBoxHeight - defaultOffset;
    
    
    
    public FightData fightData = new FightData();
    
    
    public int getPlantCost() {
        return plantCost;
    }
    
    
}
