package game.entity.plant;
import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.entity.component.IdlePositionComponent;
import game.entity.component.PositionComponent;
import game.entity.gameobject.GameObject;
import game.entity.gameobject.GameObjectStatus;
import game.entity.gameobject.HealthComponent;
import game.entity.gameobject.Spirit;
import game.factory.PlantFactory;
import game.manager.PlantCardManager;
import game.utils.ImageLoadTool;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/28/2016.
 */
public class PlantCard extends GameObject implements MouseListener {
    static Logger logger = LoggerFactory.getLogger(PlantCard.class);
    private PlantModel plantModel;
    
    private PositionComponent positionComponent;
    private HealthComponent healthComponent;
    
    public PlantCard(GamePanel gamePanel, int posX, int posY, String plantRegisterName, String cardRegisterName, Spirit spirit) {
        super(gamePanel, cardRegisterName);
        super.spirit = spirit;
        this.plantModel = gamePanel.getPlantFactory().getModel(plantRegisterName);
        this.positionComponent = new IdlePositionComponent(
                gamePanel, posX, posY, 0, - PlantCardManager.CARD_HEIGHT, PlantCardManager.CARD_WIDTH, PlantCardManager.CARD_HEIGHT);
        this.healthComponent = new HealthComponent();
    }
    
    public PlantCard clone(int posX, int posY) {
        PlantCard plantCard = new PlantCard(gamePanel, posX, posY, plantModel.registerName, registerName, spirit);
        return plantCard;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!getPositionComponent().getCoillderBox().contains(e.getX(), e.getY())) {
            return;
        }
        gamePanel.getPlantCardManager().cardSelected(this);
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
    public void updateLogicFrame() {
        super.updateLogicFrame();
        boolean hasEnoughSunScore = gamePanel.getSunScoreManager().hasEnoughSunScore(plantModel.plantCost);
        if (hasEnoughSunScore) {
            setHighLight(true);
        }
    }

    
    public PlantModel getPlantModel() {
        return plantModel;
    }

    @Override
    public HealthComponent getHealthComponent() {
        return healthComponent;
    }
}
