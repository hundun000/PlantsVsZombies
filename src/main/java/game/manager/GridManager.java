package game.manager;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.ImageManager;
import game.component.PositionComponent;
import game.component.ZombiePositionComponent;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.Pea;
import game.entity.plant.BasePlant;
import game.entity.plant.FreezePeashooter;
import game.entity.plant.Peashooter;
import game.entity.plant.Sunflower;
import game.entity.zombie.BaseZombie;
import game.planting.PlantSlot;

/**
 * @author hundun
 * Created on 2020/09/02
 */
public class GridManager extends BaseManager {
    static Logger logger = LoggerFactory.getLogger(GridManager.class);
    
    public static final int NUM_ROW_CONSTANT = 5;
    public static final int NUM_COLUMN_CONSTANT = 9;
    public static final int GRID_HEIGHT = 120;
    public static final int GRID_WIDTH = 100;
    public static final int LAYER_PLANT_SLOT = 0;
    String planting = null;
    /**
     * 网格距离root上端的距离
     */
    public static final int GRIDS_UP_PADDING = 109;
    
    /**
     * 网格距离root上端的距离
     */
    public static final int GRIDS_LEFT_PADDING = 109;
    
    private PlantSlot[][] plantSlots;
    private ArrayList<BaseBullet> bullets;
    
    
    public GridManager(GamePanel gamePanel) {
        super(gamePanel);
        setLocation(GRIDS_LEFT_PADDING, GRIDS_UP_PADDING);
    }

    @Override
    public void updateLogicFrame() {
        for (BaseBullet bullet : bullets) {
            bullet.updateLogicFrame();
        }
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < GridManager.NUM_COLUMN_CONSTANT; i++) {
            for (int j = 0; j < GridManager.NUM_ROW_CONSTANT; j++) {
                PlantSlot c = plantSlots[i][j];
                if (c.assignedPlant != null) {
                    BasePlant plant = c.assignedPlant;
                    Image image = ImageManager.getImage(plant.getRegisterName());
                    g.drawImage(image, i * GRID_WIDTH, j * GRID_HEIGHT, null);
                }
            }
        }
        
        //Draw gameObject
        for (BaseBullet bullet : bullets) {
            bullet.drawSelf(g);
        }
    }
    

    @Override
    protected void initChild() {
        super.initChild();
        
        plantSlots = new PlantSlot[ NUM_COLUMN_CONSTANT][NUM_ROW_CONSTANT];
        for (int i = 0; i < NUM_COLUMN_CONSTANT; i++) {
            for (int j = 0; j < NUM_ROW_CONSTANT; j++) {
                PlantSlot plantSlot = new PlantSlot(gamePanel, i, j);
                plantSlots[i][j] = plantSlot;
                add(plantSlot, LAYER_PLANT_SLOT); /* Singleton */
            }
        }
        
        bullets = new ArrayList<>();
        
    }
    
    public PlantSlot[][] getPlantSlots() {
        return plantSlots;
    }
    
    public void addBullet(BaseBullet pea) {
        bullets.add(pea);
    }
    
    public PlantSlot getCollideredPlantSlot(PositionComponent positionComponent) {

        PlantSlot[][] plantSlots = gamePanel.getGridManager().getPlantSlots();
        for (int i = 0; i < GridManager.NUM_COLUMN_CONSTANT; i++) {
            for (int j = 0; j < GridManager.NUM_ROW_CONSTANT; j++) {
                PlantSlot plantSlot = plantSlots[i][j];
                if (plantSlot.assignedPlant != null) {
                    Rectangle plantBox = plantSlot.assignedPlant.getPositionComponent().getCoillderBox();
                    Rectangle zombieBox = positionComponent.getCoillderBox();
                    boolean intersectPlant = plantBox.intersects(zombieBox);
                    if (intersectPlant) {
                        return plantSlot;
                    }
                }
            }
        }
        
        return null;
    }

    public void removeBullet(BaseBullet bullet) {
        bullets.remove(bullet); 
    }

    public void clearPlanting() {
        logger.info("plating will be cleared: {}", planting);
        this.planting = null;
    }
    
    public void setPlanting(String planting) {
        logger.info("plating set: {}", planting);
        this.planting = planting;
    }
    
    public String getPlanting() {
        return planting;
    }

    
    

}
