package game.collision;
import javax.swing.*;
import javax.swing.border.LineBorder;

import game.GamePanel;
import game.GameWindow.PlantType;
import game.entity.plant.BasePlant;
import game.entity.planting.Planting;
import game.entity.planting.PlantingFreezePeashooter;
import game.entity.planting.PlantingPeashooter;
import game.entity.planting.PlantingSunflower;
import game.manager.GridManager;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Tile的碰撞箱
 * Created by Armin on 6/25/2016.
 */
public class PlantSlot extends JPanel implements MouseListener {

    private static final int SUN_PLANT_COST = 50;
    private static final int PEASHOOTER_PLANT_COST = 100;
    private static final int FREEZE_PEASHOOTER_PLANT_COST = 175;
    
    private ActionListener al;
    GamePanel gamePanel;
    int gridX, gridY;
    
    public PlantSlot(GamePanel gamePanel, int gridX, int gridY) {
        setBorder(new LineBorder(Color.RED));
        setOpaque(false);
        addMouseListener(this);
        setBackground(Color.green);
        setSize(GridManager.GRID_WIDTH, GridManager.GRID_HEIGHT);
        setLocation(gridX * GridManager.GRID_WIDTH, gridY * GridManager.GRID_HEIGHT);
        this.gamePanel = gamePanel;
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public BasePlant assignedPlant;

    public void setPlant(BasePlant p) {
        assignedPlant = p;
    }

    public void removePlant() {
        assignedPlant.stop();
        assignedPlant = null;
    }

    public boolean isInsideCollider(int tx) {
        return (tx > getLocation().x) && (tx < getLocation().x + GridManager.GRID_WIDTH);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        doPlant();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    
    public void doPlant() {
        Planting planting;
        switch(gamePanel.getPlantCardManager().getActivePlantingBrush()) {
            case Sunflower:
                planting = new PlantingSunflower(gamePanel, SUN_PLANT_COST, gridX, gridY, this);
                break;
            case Peashooter:
                planting = new PlantingPeashooter(gamePanel, PEASHOOTER_PLANT_COST, gridX, gridY, this);
                break;
            case FreezePeashooter:
                planting = new PlantingFreezePeashooter(gamePanel, FREEZE_PEASHOOTER_PLANT_COST, gridX, gridY, this);
                break;
            default: return;
        }

        planting.activePlanting();
        gamePanel.getPlantCardManager().setActivePlantingBrush(PlantType.None);
    }
}
