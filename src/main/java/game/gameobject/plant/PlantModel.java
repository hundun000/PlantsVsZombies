package game.gameobject.plant;

import game.facroty.BulletFactory;
import game.facroty.DropFactory;
import game.gameobject.gameobject.GameObjectModel;
import game.gameobject.gameobject.Spirit;
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
    public int health = 200;
    
    private int defaultOffset = 5;
    public int coillderBoxWidth = GridManager.GRID_WIDTH - defaultOffset * 2;
    public int coillderBoxHeight = GridManager.GRID_HEIGHT - defaultOffset * 2;
    public int coillderBoxOffsetX = defaultOffset;
    public int coillderBoxOffsetY = - coillderBoxHeight - defaultOffset;
    
    
    public int workColdDownFrameNum = 0;
    public int workStartFrameNum = 10;
    public int workContinuousDelayFrameNum = 10;
    public int workContinuousTime = 1;
    public String bulletRegisterName = BulletFactory.DEFAULT_BULLET_REGISTER_NAME;
    public int bulletStartOffsetX = GridManager.GRID_WIDTH / 2;
    public int bulletStartOffsetY = - GridManager.GRID_HEIGHT / 2;
    
    public int attackRangeOffsetX = bulletStartOffsetX + defaultOffset;
    public int attackRangeOffsetY = coillderBoxOffsetY;
    public int attackRangeWidth = GridManager.GRID_WIDTH * 5 ;
    public int attackRangeHeight = coillderBoxHeight ;
    
    public String dropRegisterName = DropFactory.DEFAULT_DROP_REGISTER_NAME;
    
    
    
}
