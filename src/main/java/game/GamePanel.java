package game;
import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.item.SunItem;
import game.entity.plant.Sunflower;
import game.manager.GridManager;
import game.manager.PlantCardManager;
import game.manager.SunScoreManager;
import game.manager.ZombieManager;
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

	private final static int LAYER_UI_MANAGER = 1;
    
	
	/**
	 * 逻辑帧间隔毫秒
	 */
	public static final int ADVANCETIME_CONSTANT = 50;
	/**
	 * 渲染帧间隔毫秒
	 */
	private static final int REDRAWTIME_CONSTANT = 45;
	
	private static final int CONDITION_LEVEL_CONSTANT = 100;

	private Image bgImage;
    

    
    
    
    private Timer redrawTimer;
    private Timer logicFrameTimer;
    
    private static MessageDialog messageDialog;
    
    //private int mouseX, mouseY;

    
    //  ======  manager ======
    private SunScoreManager sunScoreManager;
    private ZombieManager zombieManager;
    private GridManager gridManager;
    private PlantCardManager plantCardManager;
    
    /**
     * 关卡进度值
     */
    static int totalLevelPoint = 0;
    public int currentLevel = Integer.parseInt(LevelData.LEVEL_NUMBER);

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
    
    public void gameOver() {
        this.getMessageDialog().gameOverDialog();
        GameWindow.begin();
    }

    public GamePanel() {
        setSize(SCREEN_WIDTH_CONSTANT, SCREEN_HEIGHT_CONSTANT);
        setLayout(null);

        messageDialog = new MessageDialog(GamePanel.this);

        loadBackGroundImage();
        

        setRedrawTimer(REDRAWTIME_CONSTANT);
        setAdvanceTimer(ADVANCETIME_CONSTANT);

        this.plantCardManager = new PlantCardManager(this);
        add(plantCardManager, LAYER_UI_MANAGER);
        
        this.sunScoreManager = new SunScoreManager(this, 150);
        add(sunScoreManager, LAYER_UI_MANAGER);
        
        this.zombieManager = new ZombieManager(this);
        add(zombieManager, LAYER_UI_MANAGER);
        
        this.gridManager = new GridManager(this);
        add(gridManager, LAYER_UI_MANAGER);
         
        logger.info("gamepannel constructed.");
    }
    

    public SunScoreManager getSunScoreManager() {
        return sunScoreManager;
    }
    
    public PlantCardManager getPlantCardManager() {
        return plantCardManager;
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
		bgImage = imageIcon.getImage();
	}

	@Override
    public void updateLogicFrame() {
        logicFrameCounter++;
        //logger.debug("logicFrame: " + logicFrameCounter);
        
        // ====== notify managers ======
        zombieManager.updateLogicFrame();
        sunScoreManager.updateLogicFrame();
        
        // ====== notify entities ======
        
        


    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(bgImage, 0, 0, null);
        super.paintComponent(graphics);
        
    }
    
    

    


    public void addLevelPoint(int levelPoint) {
        totalLevelPoint = totalLevelPoint + levelPoint;
        System.out.println(totalLevelPoint);
        boolean isLevelUp = totalLevelPoint >= CONDITION_LEVEL_CONSTANT;
		if (isLevelUp) {
            currentLevel = 2;
//            for (OnLevelUpListener listener : mLevelUpObservers) {
//                listener.onLevelUp();
//            }
            messageDialog.levelUpDialog();
//            if ("1".equals(LevelData.LEVEL_NUMBER)) {
//            	messageDialog.levelUpDialog();
//            	LevelData.write("2");
//            	GameWindow.begin();
//            } else {
//            	messageDialog.gameClearDialog();
//            	LevelData.write("1");
//                System.exit(0);
//            }
            totalLevelPoint = 0;
        }
    }
    
    
    
    

    

    public MessageDialog getMessageDialog() {
    	return messageDialog;
    }
    
    

}
