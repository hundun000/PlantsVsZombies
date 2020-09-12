package game.pvz.plant;
import javax.swing.*;

import game.GamePanel;
import game.gameobject.drop.BaseDrop;
import game.gameobject.drop.DropInstanceParams;
import game.gameobject.plant.BasePlant;
import game.gameobject.plant.PlantInstanceParams;
import game.gameobject.plant.PlantModel;
import game.manager.GridManager;
import game.pvz.drop.SunItem;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends BasePlant {
    
    public static final String NAME = "sunflower";

    public Sunflower(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
    }
    
    private void generateSunItemAndAddToBoard() {
        int startX = getBulletStartX();
        int startY = getBulletStartY();
        int endY = startY + (GridManager.GRID_HEIGHT / 2);
        DropInstanceParams params = new DropInstanceParams(startX, startY, endY);
        BaseDrop drop = gamePanel.getDropFactory().getInstacne(model.dropRegisterName, gamePanel, params);
        gamePanel.getGridManager().addDrop(drop);
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
