package game.entity.gameobject;

import game.GamePanel;
import game.manager.GridManager;
import game.mod.pvz.drop.SunItem;

/**
 * @author hundun
 * Created on 2020/09/22
 */
public class FightData {
    
    public Integer attackColdDownFrameNum;
    public Integer attackStartFrameNum;
    public Integer attackContinuousDelayFrameNum;
    public Integer attackContinuousTime;
    
    public FightData buildDefaultPlantAttackLoop() {
        return buildAttackLoop(
                (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 1), 
                (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.9), 
                (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.1), 
                1);
    }
    
    public FightData buildDefaultZombieAttackLoop() {
        return buildAttackLoop(
                1, 
                (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.9), 
                (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.1), 
                9999);
    }
    
    
    public FightData buildAttackLoop(
            Integer attackColdDownFrameNum,
            Integer attackStartFrameNum,
            Integer attackContinuousDelayFrameNum,
            Integer attackContinuousTime
            ) {
        this.attackColdDownFrameNum = attackColdDownFrameNum;
        this.attackStartFrameNum = attackStartFrameNum;
        this.attackContinuousDelayFrameNum = attackContinuousDelayFrameNum;
        this.attackContinuousTime = attackContinuousTime;
        return this;
    }
    
    public String bulletRegisterName;
    public Integer bulletStartOffsetX;
    public Integer bulletStartOffsetY;
    public Integer bulletSubTypeId;
    
    public FightData buildBullet(String bulletRegisterName) {
        this.bulletRegisterName = bulletRegisterName;
        this.bulletStartOffsetX = GridManager.GRID_WIDTH / 2;
        this.bulletStartOffsetY = - GridManager.GRID_HEIGHT / 2;
        this.bulletSubTypeId = 0;
        return this;
    }
    
    public Integer attackRangeOffsetX;
    public Integer attackRangeOffsetY;
    public Integer attackRangeWidth;
    public Integer attackRangeHeight;
    
    public FightData buildDefaultPlantAttackRange() {
        this.attackRangeWidth = GridManager.GRID_WIDTH * 6 ;
        this.attackRangeHeight = GridManager.GRID_HEIGHT - GameObjectModel.defaultOffset * 2;
        this.attackRangeOffsetX = GameObjectModel.defaultOffset;
        this.attackRangeOffsetY = - GameObjectModel.defaultOffset - attackRangeHeight;
        return this;
    }
    
    public FightData buildSelfGridAttackRange() {
        this.attackRangeWidth = GridManager.GRID_WIDTH - GameObjectModel.defaultOffset * 2;
        this.attackRangeHeight = GridManager.GRID_HEIGHT - GameObjectModel.defaultOffset * 2;
        this.attackRangeOffsetX = GameObjectModel.defaultOffset;
        this.attackRangeOffsetY = - GameObjectModel.defaultOffset - attackRangeHeight;
        return this;
    }
    
    
    public String dropRegisterName;
    public Integer dropStartOffsetX;
    public Integer dropStartOffsetY;
    
    public Integer skillColdDownFrameNum;
    public Integer skillStartFrameNum;
    
    
    public FightData buildDropSkill(String dropRegisterName) {
        this.skillColdDownFrameNum = GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 4;
        this.skillStartFrameNum = (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.5);
        this.dropRegisterName = dropRegisterName;
        this.dropStartOffsetX = GridManager.GRID_WIDTH / 2;
        this.dropStartOffsetY = - GridManager.GRID_HEIGHT / 2;
        return this;
    }
    
    
    public int maxBlockNum = 1;
}
