package game.manager;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.entity.gameobject.Spirit;
import game.mod.pvz.drop.SunItem;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/01
 */
public class SunScoreManager extends BaseManager {
    static Logger logger = LoggerFactory.getLogger(SunScoreManager.class);
    
    JLabel scoreLable;
    private int sunScore = 0;
    
    public SunScoreManager(GamePanel gamePanel, int initSunScore) {
        super(gamePanel, 0, 0, 96, 109, 30, 80);
        addSunScore(initSunScore);
        super.boardImage = ImageLoadTool.loadOneOtherImage(gamePanel.mod.getModName(), "sun_manager").getImage();
    }

    @Override
    public void updateLogicFrame() {
        
    }

    @Override
    public void initChild() {
        
        
        this.scoreLable = new JLabel("sunScore");
        scoreLable.setLocation(30, 80);
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

    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }

    

}
