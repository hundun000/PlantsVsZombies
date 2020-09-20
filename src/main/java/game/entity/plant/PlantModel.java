package game.entity.plant;

import game.GamePanel;
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
    
    public int defaultOffset = 5;
    public int coillderBoxWidth = GridManager.GRID_WIDTH - defaultOffset * 2;
    public int coillderBoxHeight = GridManager.GRID_HEIGHT / 2 - defaultOffset * 2;
    public int coillderBoxOffsetX = defaultOffset;
    public int coillderBoxOffsetY = - coillderBoxHeight - defaultOffset;
    
    
    public int attackColdDownFrameNum = GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 2;
    public int attackStartFrameNum = (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.5);
    public int attackContinuousDelayFrameNum = (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.2);
    public int attackContinuousTime = 1;
    public String bulletRegisterName = null;
    public int bulletStartOffsetX = GridManager.GRID_WIDTH / 2;
    public int bulletStartOffsetY = - GridManager.GRID_HEIGHT / 2;
    
    public int attackRangeOffsetX = defaultOffset;
    public int attackRangeOffsetY = coillderBoxOffsetY;
    public int attackRangeWidth = GridManager.GRID_WIDTH * 5 ;
    public int attackRangeHeight = coillderBoxHeight ;
    
    public String dropRegisterName = null;
    public int skillColdDownFrameNum = GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 2;
    public int skillStartFrameNum = (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.5);
    
    
    
}
