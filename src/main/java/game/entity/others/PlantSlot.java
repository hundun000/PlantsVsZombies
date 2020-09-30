package game.entity.others;
import javax.swing.*;
import javax.swing.border.LineBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.ILogicFrameListener;
import game.entity.component.FightComponent;
import game.entity.component.HealthComponent;
import game.entity.component.IdlePositionComponent;
import game.entity.component.PositionComponent;
import game.entity.gameobject.GameObject;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.entity.plant.template.DropPlant;
import game.entity.plant.template.ShooterPlant;
import game.factory.PlantFactory;
import game.manager.GridManager;
import game.manager.SunScoreManager.BasicWealth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class PlantSlot extends GameObject implements MouseListener, MouseMotionListener {
    public static String REGISTER_NAME = "plant_slot";
    
    static Logger logger = LoggerFactory.getLogger(PlantSlot.class);
    private static final int SUN_PLANT_COST = 50;
    private static final int PEASHOOTER_PLANT_COST = 100;
    private static final int FREEZE_PEASHOOTER_PLANT_COST = 175;
    
    //boolean mouseInside = false;
    int gridX, gridY;
    private BasePlant plant;
    protected PositionComponent positionComponent;
    protected HealthComponent healthComponent;
    protected FightComponent status;
    
    public PlantSlot(GamePanel gamePanel, int gridX, int gridY) {
        super(gamePanel, REGISTER_NAME);
        super.coillderBoxColor = Color.GRAY;
        this.gridX = gridX;
        this.gridY = gridY;
        int posX = gridX * GridManager.GRID_WIDTH;
        int posY = (gridY + 1) * GridManager.GRID_HEIGHT;
        this.positionComponent = new IdlePositionComponent(gamePanel, posX, posY, 0, - GridManager.GRID_HEIGHT , GridManager.GRID_WIDTH, GridManager.GRID_HEIGHT);
        this.healthComponent = new HealthComponent();
    }
    
    @Override
    public void updateLogicFrame() {
        super.updateLogicFrame();
        
        if (plant != null) {
            plant.updateLogicFrame();
            if (!plant.getHealthComponent().alive()) {
                plant = null;
                logger.info("{}'s plant cleard.", this.toString());
            }
        }
    }
    
    public BasePlant getPlant() {
        return plant;
    }
    
    public boolean hasPlant() {
        return plant != null;
    }



    public void clearPlant() {
        plant = null;
    }
    
    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        
        if (hasPlant()) {
            plant.drawSelf(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        int eventGridsX = e.getX() - GridManager.POSITION_START_X;
        int eventGridsY = e.getY() - GridManager.POSITION_START_Y;
        if (!getPositionComponent().getCoillderBox().contains(eventGridsX, eventGridsY)) {
            setHighLight(false);
            return;
        } else {
            setHighLight(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int eventGridsX = e.getX() - GridManager.POSITION_START_X;
        int eventGridsY = e.getY() - GridManager.POSITION_START_Y;
        if (!getPositionComponent().getCoillderBox().contains(eventGridsX, eventGridsY)) {
            return;
        }
        logger.info("click slot[{}][{}]", gridX, gridY);
        
        PlantModel plantModel = gamePanel.getGridManager().getHoldingPlantModel();
        if (plantModel != null) {
            logger.info("planting = {}", plantModel.registerName);
            PlantInstanceParams params = new PlantInstanceParams(gridX, gridY, getPositionComponent().getPosX(), getPositionComponent().getPosY());
            
            BasePlant plant = gamePanel.getPlantFactory().getInstacne(plantModel.registerName, gamePanel, params);
            
            if (plant != null) {
                int cost = plant.getModel().getPlantCost();
                boolean hasEnoughSunScore = gamePanel.getSunScoreManager().hasEnoughWealth(BasicWealth.SUN_POINT, cost);
                if (hasEnoughSunScore && !hasPlant()) {
                    this.plant = plant;
                    gamePanel.getSunScoreManager().addWealth(BasicWealth.SUN_POINT, - cost);
                    logger.info("plant success, slot update to: {}", this.toString());
                } else {
                    logger.info("cannot plant because hasEnoughSunScore = {}, hasPlant = {}", hasEnoughSunScore, hasPlant());
                }
                gamePanel.getGridManager().clearPlanting();
            }
        }
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
    public String toString() {
        return "slot(" + gridX + ", " + gridY + ")[" + (plant != null ? plant.getRegisterName() : "none") + "]";
    }




    @Override
    public PositionComponent getPositionComponent() {
        return positionComponent;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public HealthComponent getHealthComponent() {
        return healthComponent;
    }

    @Override
    protected boolean wantMove() {
        // TODO Auto-generated method stub
        return false;
    }


    

}
