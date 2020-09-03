package game.planting;
import javax.swing.*;
import javax.swing.border.LineBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.entity.plant.BasePlant;
import game.entity.plant.FreezePeashooter;
import game.entity.plant.Peashooter;
import game.entity.plant.Sunflower;
import game.manager.GridManager;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class PlantSlot extends JPanel implements MouseListener {
    static Logger logger = LoggerFactory.getLogger(PlantSlot.class);
    private static final int SUN_PLANT_COST = 50;
    private static final int PEASHOOTER_PLANT_COST = 100;
    private static final int FREEZE_PEASHOOTER_PLANT_COST = 175;
    
    GamePanel gamePanel;
    int gridX, gridY;
    public BasePlant assignedPlant;
    
    public PlantSlot(GamePanel gamePanel, int gridX, int gridY) {
        setBorder(new LineBorder(Color.RED));
        setOpaque(false);
        addMouseListener(this);
        setSize(GridManager.GRID_WIDTH, GridManager.GRID_HEIGHT);
        setLocation(gridX * GridManager.GRID_WIDTH, gridY * GridManager.GRID_HEIGHT);
        this.gamePanel = gamePanel;
        this.gridX = gridX;
        this.gridY = gridY;
    }



    public void plantDie() {
        assignedPlant.stop();
        assignedPlant = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String type = gamePanel.getGridManager().getPlanting();
        BasePlant plant = PlantFactory.getPlant(type, gamePanel, gridX, gridY);
        
        
        if (plant != null) {
            int cost = plant.getPlantCost();
            boolean hasEnoughSunScore = gamePanel.getSunScoreManager().hasEnoughSunScore(cost);
            boolean isSlotEmpty = this.assignedPlant == null;
            if (hasEnoughSunScore && isSlotEmpty) {
                this.assignedPlant = plant;
                logger.info("plant success, slot update to: {}", this.toString());
            } else {
                logger.info("cannot plant because hasEnoughSunScore = {}, isSlotEmpty = {}", hasEnoughSunScore, isSlotEmpty);
            }
            gamePanel.getGridManager().clearPlanting();
        }
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
    public String toString() {
        return "slot(" + gridX + ", " + gridY + ")[" + (assignedPlant != null ? assignedPlant.getRegisterName() : "none") + "]";
    }

    

}
