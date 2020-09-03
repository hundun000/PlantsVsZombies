package game.entity.plant;
import javax.swing.*;

import game.GamePanel;
import game.OnLevelUpListener;
import game.entity.item.SunItem;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends BasePlant implements OnLevelUpListener {

    public static String REGISTER_NAME = "sunflower";
    
    int x;
    int y;

    public Sunflower(GamePanel gamePanel, int x, int y) {
        super(gamePanel, x, y, REGISTER_NAME, 10);
        this.x = x;
        this.y = y;

    }
    
    private void generateSunItemAndAddToBoard() {
        int startX = 60 + this.x * 100;
        int startY = 110 + this.y * 120;
        int endY = 130 + this.y * 120;
        SunItem sunItem = new SunItem(gamePanel, startX, startY, endY);
        gamePanel.getSunScoreManager().addSunItem(sunItem);
    }

    @Override
    public void onLevelUp() {

    }

    @Override
    public void updateLogicFrame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected boolean wantWork() {
        return true;
    }

    @Override
    protected void work() {
        generateSunItemAndAddToBoard();
    }

    @Override
    public Rectangle getAttackRangeBox() {
        return null;
    }

    @Override
    public int getPlantCost() {
        return 50;
    }
}
