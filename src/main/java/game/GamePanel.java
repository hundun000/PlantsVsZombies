package game;
import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.gameobject.Spirit;
import game.entity.plant.PlantModel;
import game.entity.plant.template.DropPlant;
import game.factory.BulletFactory;
import game.factory.DropFactory;
import game.factory.PlantFactory;
import game.factory.ZombieFactory;
import game.level.GameLevel;
import game.manager.GridManager;
import game.manager.PlantCardManager;
import game.manager.SunScoreManager;
import game.mod.Mod;
import game.mod.pvz.PvzMod;
import game.mod.pvz.drop.SunItem;
import game.utils.ImageLoadTool;

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
//	/**
//	 * 每秒渲染帧数
//	 */
//	private static final int DRAW_FRAME_NUM_PER_SECOND = 40;
//	
    public static final boolean DRAW_DEBUG_BOX = true;

	private Image boardImage;
    
    //private Timer redrawTimer;
    private Timer logicAndDrawFrameTimer;
    
    private static MessageDialog messageDialog;
    
    //private int mouseX, mouseY;
    List<GameLevel> levels;
    
    public Mod mod;
    //  ======  manager ======
    private SunScoreManager sunScoreManager;
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

    
    public GridManager getGridManager() {
        return gridManager;
    }
    
    public ZombieFactory getZombieFactory() {
        return zombieFactory;
    }
    
    public DropFactory getDropFactory() {
        return dropFactory;
    }
    
    public GameState gameState;
    public enum GameState {
        GAME_PAUSE,
        PREPARE_CARDS,
        FIGHT
    }
    
    public void gameOver() {
        this.getMessageDialog().gameOverDialog();
        GameWindow.intoFightWindow();
    }
    
    public static int perSecondToPerFrame(int input) {
        return (int) (input / GamePanel.LOGICAL_FRAME_NUM_PER_SECOND);
    }
    public static double perSecondToPerFrame(double input) {
        return (double) (input / GamePanel.LOGICAL_FRAME_NUM_PER_SECOND);
    }

    public GamePanel(boolean visible) {
        setSize(SCREEN_WIDTH_CONSTANT, SCREEN_HEIGHT_CONSTANT);
        setLayout(null);
        setVisible(visible);
        
        messageDialog = new MessageDialog(GamePanel.this);

        this.mod = new PvzMod();
        
        this.plantFactory = new PlantFactory();
        this.zombieFactory = new ZombieFactory();
        this.bulletFactory = new BulletFactory();
        this.dropFactory = new DropFactory();
        
//        setRedrawTimer(1000 / DRAW_FRAME_NUM_PER_SECOND);
        setAdvanceTimer(1000 / LOGICAL_FRAME_NUM_PER_SECOND);

        this.plantCardManager = new PlantCardManager(this);
        add(plantCardManager, LAYER_CARDS_MANAGER);
        
        this.sunScoreManager = new SunScoreManager(this, 1000);
        add(sunScoreManager, LAYER_IMAGE_MANAGER);
        
        this.gridManager = new GridManager(this);
        add(gridManager, LAYER_GRIDS_MANAGER);
        
        
        loadModEntities();
        
        logicAndDrawFrameTimer.start();
        updateGameState(GameState.PREPARE_CARDS);
        
        logger.debug("gamepannel constructed.");
    }
    
    
    private void loadModEntities() {
        
        mod.loadBulletModeles().forEach(item -> bulletFactory.registerModel(item));
        mod.loadDrops().forEach(item -> dropFactory.registerModel(item));
        mod.loadZombieModeles().forEach(item -> zombieFactory.registerModel(item));
        mod.loadPlantModeles().forEach(item -> plantFactory.registerModel(item));
        this.levels = mod.loadGameLevels();
        
        for (PlantModel model : plantFactory.getModels()) {
            String cardRegisterName = PlantCardManager.getCardRegisterName(model.registerName);
            Spirit spirit = new Spirit(ImageLoadTool.loadOnePlantCardImage(mod.getModName(), cardRegisterName));
            plantCardManager.registerPlantCard(model.registerName, spirit);
        }
        
        loadBackGroundImage();
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
		logicAndDrawFrameTimer = new Timer(advanceTime, (ActionEvent e) -> {
		    if (gameState == GameState.FIGHT) {
		        updateLogicFrame();
		    }
		    repaint();
		});
        
	}

//	private void setRedrawTimer(int redrawTime) {
//		redrawTimer = new Timer(redrawTime, (ActionEvent e) -> {
//		    repaint();
//        });
//        
//	}

	

	

	

	private void loadBackGroundImage() {
	    ImageIcon imageIcon = ImageLoadTool.loadOneOtherImage(mod.getModName(), "main");
		boardImage = imageIcon.getImage();
	}

	@Override
    public void updateLogicFrame() {
        logicFrameCounter++;
        //logger.debug("logicFrame: " + logicFrameCounter);
        
        // ====== notify managers ======
        sunScoreManager.updateLogicFrame();
        gridManager.updateLogicFrame();
        plantCardManager.updateLogicFrame();
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
        gridManager.levelStart(level);
        plantCardManager.levelStart(level);
        //redrawTimer.start();
        
    }

    @Override
    public void levelEnd() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateGameState(GameState gameState) {
        this.gameState = gameState;
        switch (gameState) {
        case FIGHT:
            levelStart(this.levels.get(0));
            break;
        default:
            break;
        }
        plantCardManager.updateGameState(gameState);
        logger.info("state update to {}", gameState);
    }
    
    

}
