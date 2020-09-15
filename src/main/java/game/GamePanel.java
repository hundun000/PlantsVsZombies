package game;
import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.facroty.BulletFactory;
import game.facroty.DropFactory;
import game.facroty.PlantFactory;
import game.facroty.ZombieFactory;
import game.level.GameLevel;
import game.manager.GridManager;
import game.manager.PlantCardManager;
import game.manager.SunScoreManager;
import game.manager.ZombieManager;
import game.pvz.PvzMod;
import game.pvz.drop.SunItem;
import game.pvz.plant.Sunflower;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Armin on 6/25/2016.
 */
public class GamePanel extends JLayeredPane implements ILogicFrameListener, ILevelListener {
    static Logger logger = LoggerFactory.getLogger(GamePanel.class);
    public static final int SCREEN_HEIGHT_CONSTANT = 752;
	public static final int SCREEN_WIDTH_CONSTANT = 1000;

	/**
	 * 不需要接受鼠标事件的绘图层
	 */
	private final static int LAYER_IMAGE_MANAGER = 1;
	
	private final static int LAYER_GRIDS_MANAGER = 2;
	
    private final static int LAYER_CARDS_MANAGER = 3;
	
	/**
	 * 每秒逻辑帧数
	 */
	public static final int LOGICAL_FRAME_NUM_PER_SECOND = 20;
	/**
	 * 每秒渲染帧数
	 */
	private static final int DRAW_FRAME_NUM_PER_SECOND = 40;
	
    public static final boolean DRAW_DEBUG_BOX = true;

	private Image boardImage;
    
    private Timer redrawTimer;
    private Timer logicFrameTimer;
    
    private static MessageDialog messageDialog;
    
    //private int mouseX, mouseY;

    public PvzMod pvzMod = new PvzMod();
    //  ======  manager ======
    private SunScoreManager sunScoreManager;
    private ZombieManager zombieManager;
    private GridManager gridManager;
    private PlantCardManager plantCardManager;
    private PlantFactory plantFactory ;
    private ZombieFactory zombieFactory;
    private BulletFactory bulletFactory;
    private DropFactory dropFactory ;
    /**
     * 关卡进度值
     */
    static int totalLevelPoint = 0;

    public long logicFrameCounter = 0;

    
    public ZombieManager getZombieManager() {
        return zombieManager;
    }
    
    public GridManager getGridManager() {
        return gridManager;
    }
    
    public ZombieFactory getZombieFactory() {
        return zombieFactory;
    }
    
    public DropFactory getDropFactory() {
        return dropFactory;
    }
    
    public void gameOver() {
        this.getMessageDialog().gameOverDialog();
        GameWindow.intoFightWindow();
    }

    public GamePanel(boolean visible) {
        setSize(SCREEN_WIDTH_CONSTANT, SCREEN_HEIGHT_CONSTANT);
        setLayout(null);
        setVisible(visible);
        
        messageDialog = new MessageDialog(GamePanel.this);

        loadBackGroundImage();
        
        this.plantFactory = new PlantFactory();
        this.zombieFactory = new ZombieFactory();
        this.bulletFactory = new BulletFactory();
        this.dropFactory = new DropFactory();
        
        setRedrawTimer(1000 / DRAW_FRAME_NUM_PER_SECOND);
        setAdvanceTimer(1000 / LOGICAL_FRAME_NUM_PER_SECOND);

        this.plantCardManager = new PlantCardManager(this);
        add(plantCardManager, LAYER_CARDS_MANAGER);
        
        this.sunScoreManager = new SunScoreManager(this, 300);
        add(sunScoreManager, LAYER_IMAGE_MANAGER);
        
        this.gridManager = new GridManager(this);
        add(gridManager, LAYER_GRIDS_MANAGER);
        
        
        this.zombieManager = new ZombieManager(this);
        add(zombieManager, LAYER_IMAGE_MANAGER);
        
         
        
        pvzMod.load(
                plantFactory, 
                zombieFactory, 
                plantCardManager, 
                bulletFactory, 
                dropFactory);
        
        levelStart(pvzMod.levels.get(0));
        
        logger.debug("gamepannel constructed.");
    }
    
    public PlantFactory getPlantFactory() {
        return plantFactory;
    }
    

    public SunScoreManager getSunScoreManager() {
        return sunScoreManager;
    }
    
    public PlantCardManager getPlantCardManager() {
        return plantCardManager;
    }

    public BulletFactory getBulletFactory() {
        return bulletFactory;
    }

	private void setAdvanceTimer(int advanceTime) {
		logicFrameTimer = new Timer(advanceTime, (ActionEvent e) -> {
		    updateLogicFrame();
		});
        
	}

	private void setRedrawTimer(int redrawTime) {
		redrawTimer = new Timer(redrawTime, (ActionEvent e) -> {
		    repaint();
        });
        
	}

	

	

	

	private void loadBackGroundImage() {
	    ImageIcon imageIcon = new ImageIcon(GameWindow.RESOURCE_FOLDER + "images/mainBG.png");
		boardImage = imageIcon.getImage();
	}

	@Override
    public void updateLogicFrame() {
        logicFrameCounter++;
        //logger.debug("logicFrame: " + logicFrameCounter);
        
        // ====== notify managers ======
        sunScoreManager.updateLogicFrame();
        zombieManager.updateLogicFrame();
        gridManager.updateLogicFrame();

        // ====== notify entities ======
        
        


    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(boardImage, 0, 0, null);
        super.paintComponent(graphics);
        
    }
    
    

    


    public void addLevelPoint(int levelPoint) {
 
    }
    
    
    
    

    

    public MessageDialog getMessageDialog() {
    	return messageDialog;
    }

    @Override
    public void levelStart(GameLevel level) {
        zombieManager.getNaturalZombieProducer().levelStart(level);
        
        redrawTimer.start();
        logicFrameTimer.start();
    }

    @Override
    public void levelEnd() {
        // TODO Auto-generated method stub
        
    }
    
    

}
