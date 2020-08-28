package game.manager;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import game.GamePanel;
import game.ImageManager;
import game.collision.PlantSlot;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.Pea;
import game.entity.plant.BasePlant;
import game.entity.zombie.BaseZombie;

/**
 * @author hundun
 * Created on 2020/09/02
 */
public class GridManager extends BaseManager {
    public static final int NUM_ROW_CONSTANT = 5;
    public static final int NUM_COLUMN_CONSTANT = 9;
    public static final int GRID_HEIGHT = 120;
    public static final int GRID_WIDTH = 100;
    public static final int LAYER_PLANT_SLOT = 0;
    
    /**
     * 网格距离root上端的距离
     */
    public static final int GRIDS_UP_PADDING = 109;
    
    /**
     * 网格距离root上端的距离
     */
    public static final int GRIDS_LEFT_PADDING = 109;
    
    private PlantSlot[][] plantSlots;
    private ArrayList<ArrayList<BaseBullet>> laneBullets;
    
    
    public GridManager(GamePanel gamePanel) {
        super(gamePanel);
        setLocation(GRIDS_LEFT_PADDING, GRIDS_UP_PADDING);
    }

    @Override
    public void updateLogicFrame() {
        for (int row = 0; row < NUM_ROW_CONSTANT; row++) {
            for (BaseBullet bullet : laneBullets.get(row)) {
                bullet.updateLogicFrame();
            }
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
        for (int row = 0; row < NUM_ROW_CONSTANT; row++) {

            for (int object = 0; object < laneBullets.get(row).size(); object++) {
                BaseBullet bullet = laneBullets.get(row).get(object);
                Image image = ImageManager.getImage(bullet.getRegisterName());
                g.drawImage(image, bullet.getPositionComponent().getPosX(), (row * GRID_HEIGHT), null);
            }
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
        
        laneBullets = new ArrayList<>();
        for(int line = 0; line < NUM_ROW_CONSTANT; line++) {
            laneBullets.add(new ArrayList<>()); //line
        }
        
        
    }
    
    public PlantSlot[][] getPlantSlots() {
        return plantSlots;
    }
    
    public void addBulletToLane(BaseBullet pea, int lane) {
        laneBullets.get(lane).add(pea);
    }
    
    public PlantSlot getCollideredPlantSlot(int x, boolean needHasPlant) {

        PlantSlot[][] plantSlots = gamePanel.getGridManager().getPlantSlots();
        for (int i = 0; i < GridManager.NUM_COLUMN_CONSTANT; i++) {
            for (int j = 0; j < GridManager.NUM_ROW_CONSTANT; j++) {
                PlantSlot plantSlot = plantSlots[i][j];
                boolean intersectPlant = plantSlot.assignedPlant != null && (!needHasPlant || plantSlot.isInsideCollider(x));
                if (intersectPlant) {
                    return plantSlot;
                }
            }
        }
        
        return null;
    }


    
    

}
