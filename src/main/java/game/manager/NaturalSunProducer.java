package game.manager;

import java.util.Random;

import game.GamePanel;
import game.ILogicFrameListener;
import game.gameobject.drop.BaseDrop;
import game.gameobject.drop.DropInstanceParams;
import game.pvz.drop.SunItem;

/**
 * @author hundun
 * Created on 2020/09/03
 */
public class NaturalSunProducer implements ILogicFrameListener {
    protected final GamePanel gamePanel;
    Random rnd = new Random();
    
    private int workColdDownReset;
    private int workColdDown = 0;
    
    public NaturalSunProducer(GamePanel gamePanel, int workColdDownReset) {
        this.gamePanel = gamePanel;
        this.workColdDownReset = workColdDownReset;
    }
    
    @Override
    public void updateLogicFrame() {
        workColdDown --;
        if(workColdDown <= 0) {
            int startX = rnd.nextInt(GamePanel.SCREEN_WIDTH_CONSTANT - 200) + 100;
            int startY = 0;
            int endY = rnd.nextInt(300) + 200;
            DropInstanceParams params = new DropInstanceParams(startX, startY, endY);
            BaseDrop sunItem = gamePanel.getDropFactory().getInstacne(SunItem.REGISTER_NAME, gamePanel, params);
            gamePanel.getGridManager().addDrop(sunItem);
            
            workColdDown = workColdDownReset;
        }
    }

}
