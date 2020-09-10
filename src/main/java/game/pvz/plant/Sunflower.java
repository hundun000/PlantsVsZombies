package game.pvz.plant;
import javax.swing.*;

import game.GamePanel;
import game.OnLevelUpListener;
import game.gameobject.plant.BasePlant;
import game.gameobject.plant.PlantInstanceParams;
import game.gameobject.plant.PlantModel;
import game.manager.GridManager;
import game.pvz.item.SunItem;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends BasePlant implements OnLevelUpListener {
    
    public static final String NAME = "sunflower";

    public Sunflower(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
    }
    
    private void generateSunItemAndAddToBoard() {
        int startX = getBulletStartX();
        int startY = getBulletStartY();
        int endY = startY + (GridManager.GRID_HEIGHT / 2);
        SunItem sunItem = new SunItem(gamePanel, startX, startY, endY);
        gamePanel.getGridManager().addSunItem(sunItem);
    }

    @Override
    public void onLevelUp() {

    }

    @Override
    protected boolean wantWork() {
        return true;
    }

    @Override
    protected void work() {
        generateSunItemAndAddToBoard();
    }
}
