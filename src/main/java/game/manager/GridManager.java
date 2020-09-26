package game.manager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.GamePanel.GameState;
import game.ILevelListener;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.template.DebuffBullect;
import game.entity.component.PositionComponent;
import game.entity.component.ZombiePositionComponent;
import game.entity.drop.BaseDrop;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.FightObject.FightSide;
import game.entity.others.PlantSlot;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantModel;
import game.entity.plant.template.DropPlant;
import game.entity.plant.template.ShooterPlant;
import game.entity.zombie.BaseZombie;
import game.entity.zombie.ZombieInstanceParams;
import game.level.GameLevel;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/02
 */
public class GridManager extends BaseManager implements MouseListener, ILevelListener {
    static Logger logger = LoggerFactory.getLogger(GridManager.class);
    
    public static final int NUM_ROW_CONSTANT = 5;
    public static final int NUM_COLUMN_CONSTANT = 9;
    public static final int GRID_HEIGHT = 120;
    public static final int GRID_WIDTH = 100;
    public static final int LAYER_PLANT_SLOT = 0;
    PlantModel planting = null;
    
    public static final int MANAGER_START_Y = 0;
    
    
    public static final int MANAGER_START_X = 0;
    
    
    public static final int POSITION_START_X = 50;
    public static final int POSITION_START_Y = 110;
    
    public static final int MANAGER_WIDTH = GamePanel.SCREEN_WIDTH_CONSTANT;
    public static final int MANAGER_HEIGHT = GamePanel.SCREEN_HEIGHT_CONSTANT;

    private PlantSlot[][] plantSlots;
    private ArrayList<BaseBullet> bullets;
    private ArrayList<BaseDrop> drops;
    private List<BaseZombie> zombies;
    
    private NaturalZombieProducer naturalZombieProducer;
    
    
    public GridManager(GamePanel gamePanel) {
        super(gamePanel, MANAGER_START_X, MANAGER_START_Y, MANAGER_WIDTH, MANAGER_HEIGHT, POSITION_START_X, POSITION_START_Y);
        addMouseListener(this);
        super.boardColor = Color.BLUE;
        //super.boardImage = ImageLoadTool.loadOneOtherImage(gamePanel.mod.getModName(), "grid_manager").getImage();
    }

    @Override
    public void updateLogicFrame() {
        if (naturalZombieProducer != null) {
            naturalZombieProducer.updateLogicFrame();
        }
        
        
        {
            Iterator<BaseZombie> iterator = zombies.iterator();
            while (iterator.hasNext()) {
                BaseZombie zombie = iterator.next();
                zombie.updateLogicFrame();
                if (!zombie.getHealthComponent().alive()) {
                    iterator.remove();
                }
            }
        }
        
        for (int i = 0; i < GridManager.NUM_COLUMN_CONSTANT; i++) {
            for (int j = 0; j < GridManager.NUM_ROW_CONSTANT; j++) {
                PlantSlot plantSlot = plantSlots[i][j];
                plantSlot.updateLogicFrame();
            }
        }

        {
            Iterator<BaseBullet> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                BaseBullet bullet = iterator.next();
                bullet.updateLogicFrame();
                if (!bullet.getHealthComponent().alive()) {
                    iterator.remove();
                }
            }
        }
        
        {
            Iterator<BaseDrop> iterator = drops.iterator();
            while (iterator.hasNext()) {
                BaseDrop drop = iterator.next();
                drop.updateLogicFrame();
                if (!drop.getHealthComponent().alive()) {
                    iterator.remove();
                    removeMouseListener(drop);
                }
            }
        }
    }
    
    public void addDrop(BaseDrop drop) {
        if (drop != null) {
            drops.add(drop);
            addMouseListener(drop);
            logger.info("drop added: ", drop.getPositionComponent().toString());
        }
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.translate(POSITION_START_X, POSITION_START_Y);
        
        for (int i = 0; i < GridManager.NUM_COLUMN_CONSTANT; i++) {
            for (int j = 0; j < GridManager.NUM_ROW_CONSTANT; j++) {
                PlantSlot plantSlot = plantSlots[i][j];
                plantSlot.drawSelf(g);
            }
        }
        
        //Draw gameObject
        for (BaseBullet bullet : bullets) {
            bullet.drawSelf(g);
        }
        
        for (BaseDrop drop : drops) {
            drop.drawSelf(g);
        }
        
        for (BaseZombie zombie : zombies) {
            zombie.drawSelf(g);
        }
       
    }
    

    @Override
    protected void initChild() {
        super.initChild();
        
        plantSlots = new PlantSlot[NUM_COLUMN_CONSTANT][NUM_ROW_CONSTANT];
        for (int i = 0; i < NUM_COLUMN_CONSTANT; i++) {
            for (int j = 0; j < NUM_ROW_CONSTANT; j++) {
                PlantSlot plantSlot = new PlantSlot(gamePanel, i, j);
                plantSlots[i][j] = plantSlot;
                addMouseListener(plantSlot);
                addMouseMotionListener(plantSlot);
            }
        }
        
        bullets = new ArrayList<>();
        
        drops = new ArrayList<>();
        
        zombies = new LinkedList<>();

        naturalZombieProducer = new NaturalZombieProducer(gamePanel);
    }
    
    public static int gridPerSecondToPixelPerFrame(double gridPerSecond) {
        double pixelPerSecond = gridPerSecond * GridManager.GRID_WIDTH;
        double f = GamePanel.LOGICAL_FRAME_NUM_PER_SECOND;
        double pixelPerFrame = pixelPerSecond / f;
        return (int) pixelPerFrame;
    }

    
    public void addBullet(BaseBullet bullet) {
        bullets.add(bullet);
        logger.debug("{} created", bullet.toString());
    }



    public void clearPlanting() {
        logger.info("plating will be cleared: {}", planting);
        this.planting = null;
    }
    
    public void setPlanting(PlantModel plantModel) {
        logger.info("plating set: {}", plantModel);
        this.planting = plantModel;
    }
    
    public PlantModel getPlanting() {
        return planting;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //logger.debug("mouse pressed at origin({}, {})", e.getX(), e.getY());
        int eventGridsX = e.getX() - GridManager.POSITION_START_X;
        int eventGridsY = e.getY() - GridManager.POSITION_START_Y;
        logger.debug("mouse pressed at Grids({}, {})", eventGridsX, eventGridsY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void levelStart(GameLevel level) {
        naturalZombieProducer.levelStart(level);
        
        for (int i = 0; i < NUM_COLUMN_CONSTANT; i++) {
            for (int j = 0; j < NUM_ROW_CONSTANT; j++) {
                PlantSlot plantSlot = plantSlots[i][j];
                plantSlot.clearPlant();
            }
        }
        bullets.clear();
        
        drops.clear();
        
    }

    @Override
    public void levelEnd() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateGameState(GameState gameState) {
        // TODO Auto-generated method stub
        
    }

    public void addZombie(String zombieRegisterName, int lane) {
        ZombieInstanceParams params = new ZombieInstanceParams(lane);
        
        BaseZombie zombie = gamePanel.getZombieFactory().getInstacne(zombieRegisterName, gamePanel, params);
        if (zombie != null) {
            zombies.add(zombie);
            logger.info(zombie.toString() + " created");
        }
    }
    
    public List<BaseZombie> getZombies() {
        return zombies;
    }

    public void removeZombie(BaseZombie zombie) {
        zombies.remove(zombie);
    }

    
    public List<FightObject> getIntersectedOtherSideFightObjects(Rectangle rect, FightSide attacterSide) {
        List<FightObject> result = new ArrayList<>();
        if (attacterSide == FightSide.PLAYER) {
            for (BaseZombie zombie : zombies) {
                Rectangle zombieRect = zombie.getPositionComponent().getCoillderBox(); 
                
                if (rect.intersects(zombieRect)) {
                    result.add(zombie);
                }
            }
        } else {
            for (int i = 0; i < GridManager.NUM_COLUMN_CONSTANT; i++) {
                for (int j = 0; j < GridManager.NUM_ROW_CONSTANT; j++) {
                    PlantSlot plantSlot = plantSlots[i][j];
                    if (plantSlot.hasPlant()) {
                        Rectangle plantBox = plantSlot.getPlant().getPositionComponent().getCoillderBox();
                        boolean intersectPlant = plantBox.intersects(rect);
                        if (intersectPlant) {
                            result.add(plantSlot.getPlant());
                        }
                    }
                }
            }
        }
        return result;
    }


}
