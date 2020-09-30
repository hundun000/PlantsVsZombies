package game.entity.gameobject;

import game.GamePanel;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.gameobject.WorkStatus.WorkType;
import game.manager.GridManager;
import game.mod.pvz.drop.SunItem;

/**
 * @author hundun
 * Created on 2020/09/22
 */
public class FightData {
    public WorkType attackWorkType;
    public WorkState attackInitState;
    public Integer attackColdDownFrameNum;
    public Integer attackStartFrameNum;
    public Integer attackContinuousDelayFrameNum;
    public Integer attackContinuousTime;
    
    
    
    public String bulletRegisterName;
    public Integer bulletStartOffsetX;
    public Integer bulletStartOffsetY;
    public String bulletSubTypeName;
    
    
    
    public Integer attackRangeOffsetX;
    public Integer attackRangeOffsetY;
    public Integer attackRangeWidth;
    public Integer attackRangeHeight;
    
    
    
    
    public String dropRegisterName;
    public Integer dropStartOffsetX;
    public Integer dropStartOffsetY;
    
    public WorkType skillWorkType;
    public WorkState skillInitState;
    public Integer skillColdDownFrameNum;
    public Integer skillStartFrameNum;
    
    
    
    
    
    public int maxBlockNum = 1;
}
