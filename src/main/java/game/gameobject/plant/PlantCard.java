package game.gameobject.plant;
import javax.swing.*;

import game.GamePanel;
import game.component.IdlePositionComponent;
import game.component.PositionComponent;
import game.facroty.PlantFactory;
import game.gameobject.GameObject;
import game.gameobject.Spirit;
import game.manager.PlantCardManager;
import game.utils.ImageLoadTool;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/28/2016.
 */
public class PlantCard extends GameObject implements MouseListener {

    private PlantModel plantModel;
    
    private PositionComponent positionComponent;
    
    public PlantCard(GamePanel gamePanel, int posX, int posY, String plantRegisterName, String cardRegisterName, Spirit spirit) {
        super(gamePanel, cardRegisterName);
        super.spirit = spirit;
        this.plantModel = gamePanel.getPlantFactory().getModel(plantRegisterName);
        this.positionComponent = new IdlePositionComponent(
                gamePanel, posX, posY, 0, - PlantCardManager.CARD_HEIGHT, PlantCardManager.CARD_WIDTH, PlantCardManager.CARD_HEIGHT);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (!getPositionComponent().getCoillderBox().contains(e.getX(), e.getY())) {
            return;
        }
        
        gamePanel.getGridManager().setPlanting(plantModel);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void updateLogicFrame() {
        boolean hasEnoughSunScore = gamePanel.getSunScoreManager().hasEnoughSunScore(plantModel.plantCost);
        if (hasEnoughSunScore) {
            setHighLight(true);
        }
    }

    @Override
    public PositionComponent getPositionComponent() {
        return positionComponent;
    }
}
