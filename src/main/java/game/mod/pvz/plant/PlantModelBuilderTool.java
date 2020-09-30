package game.mod.pvz.plant;

import game.GamePanel;
import game.entity.gameobject.FightData;
import game.entity.gameobject.GameObjectModel;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.gameobject.WorkStatus.WorkType;
import game.manager.GridManager;

/**
 * @author hundun
 * Created on 2020/09/29
 */
public class PlantModelBuilderTool {
    
    public static void buildDefaultPlantAttackLoop(FightData fightData) {
        buildAttackLoop(
                fightData,
                (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 1), 
                (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.9), 
                (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.1), 
                1);
    }
    
    public static void buildDefaultZombieAttackLoop(FightData fightData) {
        buildAttackLoop(
                fightData,
                1, 
                (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.9), 
                (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.1), 
                9999);
    }
    
    
    public static void buildAttackLoop(
            FightData fightData,
            Integer attackColdDownFrameNum,
            Integer attackStartFrameNum,
            Integer attackContinuousDelayFrameNum,
            Integer attackContinuousTime
            ) {
        fightData.attackWorkType = WorkType.LOOP;
        fightData.attackInitState = WorkState.IDLE;
        fightData.attackColdDownFrameNum = attackColdDownFrameNum;
        fightData.attackStartFrameNum = attackStartFrameNum;
        fightData.attackContinuousDelayFrameNum = attackContinuousDelayFrameNum;
        fightData.attackContinuousTime = attackContinuousTime;
        
    }
    
    public static void buildBullet(FightData fightData, String bulletRegisterName) {
        fightData.bulletRegisterName = bulletRegisterName;
        fightData.bulletStartOffsetX = GridManager.GRID_WIDTH / 2;
        fightData.bulletStartOffsetY = - GridManager.GRID_HEIGHT / 2;
        fightData.bulletSubTypeName = null;
        
    }
    
    public static void buildDefaultPlantAttackRange(FightData fightData) {
        fightData.attackRangeWidth = GridManager.GRID_WIDTH * 6 ;
        fightData.attackRangeHeight = GridManager.GRID_HEIGHT - GameObjectModel.defaultOffset * 2;
        fightData.attackRangeOffsetX = GameObjectModel.defaultOffset;
        fightData.attackRangeOffsetY = - GameObjectModel.defaultOffset - fightData.attackRangeHeight;
        
    }
    
    public static void buildSelfGridAttackRange(FightData fightData) {
        fightData.attackRangeWidth = GridManager.GRID_WIDTH - GameObjectModel.defaultOffset * 2;
        fightData.attackRangeHeight = GridManager.GRID_HEIGHT - GameObjectModel.defaultOffset * 2;
        fightData.attackRangeOffsetX = 0;
        fightData.attackRangeOffsetY = - GameObjectModel.defaultOffset - fightData.attackRangeHeight;
        
    }
    
    public static void buildDropSkill(FightData fightData, String dropRegisterName) {
        fightData.skillWorkType = WorkType.LOOP;
        fightData.skillInitState = WorkState.IDLE;
        fightData.skillColdDownFrameNum = GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 4;
        fightData.skillStartFrameNum = (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.5);
        fightData.dropRegisterName = dropRegisterName;
        fightData.dropStartOffsetX = GridManager.GRID_WIDTH / 2;
        fightData.dropStartOffsetY = - GridManager.GRID_HEIGHT / 2;
    }
    
    public static void buildAlwayWorkSkill(FightData fightData) {
        fightData.skillWorkType = WorkType.WORK_IF_WANT;
        fightData.skillInitState = WorkState.WORK_READY;
    }

}
