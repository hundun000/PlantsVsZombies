package game.manager;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.ILevelListener;
import game.GamePanel.GameState;
import game.entity.gameobject.Spirit;
import game.entity.others.PlantCard;
import game.level.GameLevel;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/01
 */
public class PlantCardManager extends BaseManager implements ILevelListener, MouseListener {
    static Logger logger = LoggerFactory.getLogger(PlantCardManager.class);
    public final static int FIGHT_STATE_WIDTH = 600;
    public final static int FIGHT_STATE_HEIGHT = 100;
    public final static int PREPARE_STATE_WIDTH = 600;
    public final static int PREPARE_STATE_HEIGHT = 436;
    public final static int CARD_WIDTH = 65;
    public final static int CARD_HEIGHT = 90;
    private final static int ACTIVATED_CARDS_START_X = 110;
    private final static int ACTIVATED_CARDS_START_Y = 8 + CARD_HEIGHT;
    private final static int PREPARE_DONE_BUTTON_X = ACTIVATED_CARDS_START_X;
    private final static int PREPARE_DONE_BUTTON_Y = ACTIVATED_CARDS_START_Y + 8;
    private final static int REGISTERED_CARDS_START_X = ACTIVATED_CARDS_START_X;
    private final static int REGISTERED_CARDS_START_Y = PREPARE_DONE_BUTTON_Y + 100 + CARD_HEIGHT;
    
    
    private List<PlantCard> activatedCards;
    private List<PlantCard> registeredCards;
    private int maxActiveCardNum = 6;
    
    
    
    public PlantCardManager(GamePanel gamePanel) {
        super(gamePanel, 0, 0, 0, 0, 0, 0);
        addMouseListener(this);
        super.boardImage = ImageLoadTool.loadOneOtherImage(gamePanel.mod.getModName(), "card_manager").getImage();
    }

    
    
    public void registerPlantCard(String plantRegisterName, Spirit spirit) {
        String cardRegisterName = getCardRegisterName(plantRegisterName);
        int posX = REGISTERED_CARDS_START_X + CARD_WIDTH * registeredCards.size();
        int posY = REGISTERED_CARDS_START_Y;
        PlantCard plantCard = new PlantCard(gamePanel, posX, posY, plantRegisterName, cardRegisterName, spirit);
        registeredCards.add(plantCard);
        addMouseListener(plantCard);
        logger.info("card {} registered.", cardRegisterName);
    }

    @Override
    public void updateLogicFrame() {
        for (PlantCard plantCard : activatedCards) {
            plantCard.updateLogicFrame();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (PlantCard plantCard : activatedCards) {
            plantCard.drawSelf(g);
        }
        
        if (gamePanel.gameState == GameState.PREPARE_CARDS) {
            for (PlantCard plantCard : registeredCards) {
                plantCard.drawSelf(g);
            }
        }
        
        
    }
    
    public static String getCardRegisterName(String plantRegisterName) {
        return plantRegisterName + "_card";
    }


    @Override
    public void initChild() {
        registeredCards = new ArrayList<>();
        activatedCards = new ArrayList<>();
        
        JButton prepareDoneButton = new JButton("开始");
        prepareDoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.updateGameState(GameState.FIGHT);
            }
        });
        prepareDoneButton.setBounds(PREPARE_DONE_BUTTON_X, PREPARE_DONE_BUTTON_Y, 100, 30);
        add(prepareDoneButton);
    }



    @Override
    public void levelStart(GameLevel level) {

    }



    @Override
    public void levelEnd() {
        activatedCards.forEach(item -> unactiveCard(item));
    }
    
    
    
    
    public void cardSelected(PlantCard plantCard) {
        if (gamePanel.gameState == GameState.PREPARE_CARDS) {
            if (!activatedCards.contains(plantCard)) {
                if (activatedCards.size() < maxActiveCardNum) {
                    activeCard(plantCard);
                }
            } else {
                unactiveCard(plantCard);
            }
        } else if (gamePanel.gameState == GameState.FIGHT) {
            gamePanel.getGridManager().holdingPlantModel = plantCard.getPlantModel();
        }
    }
    
    private void unactiveCard(PlantCard plantCard) {
        activatedCards.remove(plantCard);
        removeMouseListener(plantCard);
        logger.info("card {} unactivated.", plantCard);
    }
    
    private void activeCard(PlantCard registeredCard) {
        int posX = ACTIVATED_CARDS_START_X + CARD_WIDTH * activatedCards.size();
        int posY = ACTIVATED_CARDS_START_Y;
        PlantCard activatedCard = registeredCard.clone(posX, posY);
        activatedCards.add(activatedCard);
        addMouseListener(activatedCard);
        logger.info("card {} activated.", activatedCard);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        logger.debug("mouse pressed at CardBoard({}, {})", e.getX(), e.getY());
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
    public void updateGameState(GameState gameState) {
        switch (gameState) {
            case PREPARE_CARDS:
                updateBaseManagerSize(PREPARE_STATE_WIDTH, PREPARE_STATE_HEIGHT);
                break;
            case FIGHT:
                updateBaseManagerSize(FIGHT_STATE_WIDTH, FIGHT_STATE_HEIGHT);
                break;
            case GAME_PAUSE:
            default:
                updateBaseManagerSize(0, 0);
                break;
        }
        
    }

    
    

}
