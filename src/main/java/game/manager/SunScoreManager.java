package game.manager;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.gameobject.Spirit;
import game.mod.pvz.drop.SunItem;
import game.ui.GamePanel;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/01
 */
public class SunScoreManager extends BaseManager {
    static Logger logger = LoggerFactory.getLogger(SunScoreManager.class);
    
    JLabel scoreLable;
    
    public interface WealthType {
        String getName();
    }
    
    public enum BasicWealth implements WealthType {
        SUN_POINT("sun"),
        COIN_POINT("$")
        ;
        
        String name;
        
        private BasicWealth(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }
        
    }
    

    Map<WealthType, Integer> wealths;
    
    
    public SunScoreManager(GamePanel gamePanel, int initSunScore) {
        super(gamePanel, 0, 0, 96, 109, 30, 80);
        
        addWealth(BasicWealth.SUN_POINT, initSunScore);
        super.boardImage = ImageLoadTool.loadOneOtherImage(gamePanel.mod.getModName(), "sun_manager").getImage();
    }

    @Override
    public void updateLogicFrame() {
        
    }

    @Override
    public void initChild() {
        this.wealths = new HashMap<>();
        registerWealthType(BasicWealth.SUN_POINT);
        
        this.scoreLable = new JLabel("wealths");
        scoreLable.setLocation(30, 80);
        scoreLable.setSize(60, 20);
        this.add(scoreLable);
    }


    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
    
    public void registerWealthType(WealthType wealthType) {
        wealths.put(wealthType, 0);
    }
    
    public boolean hasEnoughWealth(WealthType wealthType, int want) {
        if (!wealths.containsKey(wealthType)) {
            throw new UnsupportedOperationException();
        }
        
        return wealths.get(wealthType) >= want;
    }

    public void addWealth(WealthType wealthType, int addtion) {
        if (!wealths.containsKey(wealthType)) {
            throw new UnsupportedOperationException();
        }
        
        wealths.compute(wealthType, (k, v) -> Math.max(v + addtion, 0));
        updateWealthsLable();
    }
    
    
    private void updateWealthsLable() {
        StringBuilder builder = new StringBuilder();
        for (Entry<WealthType, Integer> entry : wealths.entrySet()) {
            builder.append(entry.getKey().getName()).append(": ").append(entry.getValue()).append("\n");
        }
        scoreLable.setText(builder.toString());
    }

}
