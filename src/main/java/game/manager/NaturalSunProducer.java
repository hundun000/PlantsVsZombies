package game.manager;

import java.util.Random;

import game.GamePanel;
import game.ILogicFrameListener;
import game.entity.item.SunItem;

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
            int sunX = rnd.nextInt(GamePanel.SCREEN_WIDTH_CONSTANT - 200) + 100;
            int sunY = 0;
            int endY = rnd.nextInt(300) + 200;
            SunItem sta = new SunItem(gamePanel, sunX, sunY, endY);
            gamePanel.getSunScoreManager().addSunItem(sta);
            
            workColdDown = workColdDownReset;
        }
    }

}
