package game.entity.zombie;

import game.entity.gameobject.GameObjectModel;
import game.entity.gameobject.Spirit;
import game.manager.GridManager;

/**
 * @author hundun
 * Created on 2020/09/09
 */
public class ZombieModel extends GameObjectModel<BaseZombie> {
    public ZombieModel(String registerName, Class<? extends BaseZombie> clazz) {
        super(registerName, clazz);
    }
    
    public int health = 1000;
    
    private int defaultOffset = 5;
    public int coillderBoxOffsetX = defaultOffset;
    public int coillderBoxOffsetY = - GridManager.GRID_HEIGHT + defaultOffset;
    public int coillderBoxWidth = GridManager.GRID_WIDTH - defaultOffset * 2;
    public int coillderBoxHeight = GridManager.GRID_HEIGHT - defaultOffset * 2;

    public double gridPerSecondSpeedX = 0.5;

    public int damagePerSecond = 200;

}
