package game;
import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.facroty.BulletFactory;
import game.facroty.PlantFactory;
import game.facroty.ZombieFactory;
import game.manager.GridManager;
import game.manager.PlantCardManager;
import game.manager.SunScoreManager;
import game.manager.ZombieManager;
import game.pvz.PvzMod;
import game.pvz.item.SunItem;
import game.pvz.plant.Sunflower;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Armin on 6/25/2016.
 */
public class GamePanel extends JLayeredPane implements OnLevelUpListener, ILogicFrameListener {
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
	 * 逻辑帧间隔毫秒
	 */
	public static final int ADVANCETIME_CONSTANT = 50;
	/**
	 * 渲染帧间隔毫秒
	 */
	private static final int REDRAWTIME_CONSTANT = 45;
	
    public static final boolean DRAW_DEBUG_BOX = true;

	private Image boardImage;
    
    private Timer redrawTimer;
    private Timer logicFrameTimer;
    
    private static MessageDialog messageDialog;
    
    //private int mouseX, mouseY;

    
    //  ======  manager ======
    private SunScoreManager sunScoreManager;
    private ZombieManager zombieManager;
    private GridManager gridManager;
    private PlantCardManager plantCardManager;
    private PlantFactory plantFactory ;
    private ZombieFactory zombieFactory;
    private BulletFactory bulletFactory;
    /**
     * 关卡进度值
     */
    static int totalLevelPoint = 0;
    public int currentLevel = 1;

    public long logicFrameCounter = 0;
    
    

    public int getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void onLevelUp() {
        
    }
    
    public ZombieManager getZombieManager() {
        return zombieManager;
    }
    
    public GridManager getGridManager() {
        return gridManager;
    }
    
    public ZombieFactory getZombieFactory() {
        return zombieFactory;
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
        
        setRedrawTimer(REDRAWTIME_CONSTANT);
        setAdvanceTimer(ADVANCETIME_CONSTANT);

        this.plantCardManager = new PlantCardManager(this);
        add(plantCardManager, LAYER_CARDS_MANAGER);
        
        this.sunScoreManager = new SunScoreManager(this, 150);
        add(sunScoreManager, LAYER_IMAGE_MANAGER);
        
        this.zombieManager = new ZombieManager(this);
        add(zombieManager, LAYER_IMAGE_MANAGER);
        
        this.gridManager = new GridManager(this);
        add(gridManager, LAYER_GRIDS_MANAGER);
         
        PvzMod pvzMod = new PvzMod();
        pvzMod.load(plantFactory, zombieFactory, plantCardManager, bulletFactory, sunScoreManager);

        
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
        logicFrameTimer.start();
	}

	private void setRedrawTimer(int redrawTime) {
		redrawTimer = new Timer(redrawTime, (ActionEvent e) -> {
		    repaint();
        });
        redrawTimer.start();
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
    
    

}
