package game.manager;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.ImageManager;
import game.entity.item.SunItem;

/**
 * @author hundun
 * Created on 2020/09/01
 */
public class SunScoreManager extends BaseManager {
    static Logger logger = LoggerFactory.getLogger(SunScoreManager.class);
    
    private ArrayList<SunItem> activeSuns;
    
    JLabel scoreLable;
    private int sunScore = 0;
    
    public SunScoreManager(GamePanel gamePanel, int initSunScore) {
        super(gamePanel);
        addSunScore(initSunScore);
    }

    @Override
    public void updateLogicFrame() {
        for (int object = 0; object < activeSuns.size(); object++) {
            activeSuns.get(object).updateLogicFrame();
        }
    }

    @Override
    public void initChild() {
        activeSuns = new ArrayList<>();
        
        this.scoreLable = new JLabel("sunScore");
        scoreLable.setLocation(0, 0);
        scoreLable.setSize(60, 20);
        this.add(scoreLable);
    }

    public void addSunScore(int addtion) {
        this.sunScore += addtion;
        scoreLable.setText(String.valueOf(sunScore));
    }

    public boolean hasEnoughSunScore(int cost) {
        return this.sunScore >= cost;
    }

    public void addSunItem(SunItem sunItem) {
        activeSuns.add(sunItem);
        logger.info("sunitem added: ", sunItem.getPositionComponent().toString());
    }

    
    public void deleteSun(SunItem sun) {
        //this.remove(sun);
        this.activeSuns.remove(sun);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (SunItem sunItem : activeSuns) {
            sunItem.drawSelf(g);
        }
    }
    
    /**
     * 执行加费
     * @param chargePoint 
     */
    public void chargeSunPointAndDelete(SunItem sumItem) {
        this.addSunScore(sumItem.getChargePoint());
        this.activeSuns.remove(sumItem);
    }

}
