package game.gameobject.plant;
import javax.swing.*;

import game.GamePanel;
import game.component.IdlePositionComponent;
import game.component.PositionComponent;
import game.facroty.PlantFactory;
import game.gameobject.gameobject.GameObject;
import game.gameobject.gameobject.GameObjectStatus;
import game.gameobject.gameobject.Spirit;
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
    private GameObjectStatus status;
    
    public PlantCard(GamePanel gamePanel, int posX, int posY, String plantRegisterName, String cardRegisterName, Spirit spirit) {
        super(gamePanel, cardRegisterName);
        super.spirit = spirit;
        this.plantModel = gamePanel.getPlantFactory().getModel(plantRegisterName);
        this.positionComponent = new IdlePositionComponent(
                gamePanel, posX, posY, 0, - PlantCardManager.CARD_HEIGHT, PlantCardManager.CARD_WIDTH, PlantCardManager.CARD_HEIGHT);
        this.status = new GameObjectStatus(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!getPositionComponent().getCoillderBox().contains(e.getX(), e.getY())) {
            return;
        }
        
        gamePanel.getGridManager().setPlanting(plantModel);
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
    public PositionComponent getPositionComponent() {
        return positionComponent;
    }


    @Override
    public GameObjectStatus getStatus() {
        return status;
    }


    @Override
    protected boolean wantWork() {
        return false;
    }


    @Override
    protected void work() {
        boolean hasEnoughSunScore = gamePanel.getSunScoreManager().hasEnoughSunScore(plantModel.plantCost);
        if (hasEnoughSunScore) {
            setHighLight(true);
        }
    }
}
