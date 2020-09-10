package game.manager;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.GameWindow;
import game.facroty.PlantFactory;
import game.gameobject.Spirit;
import game.gameobject.plant.BasePlant;
import game.gameobject.plant.PlantCard;
import game.gameobject.plant.PlantModel;
import game.pvz.plant.FreezePeashooter;
import game.pvz.plant.Peashooter;
import game.pvz.plant.Sunflower;

/**
 * @author hundun
 * Created on 2020/09/01
 */
public class PlantCardManager extends BaseManager {
    static Logger logger = LoggerFactory.getLogger(PlantCardManager.class);
    
    private final static int LAYER_CARD = 0;
    private final static int CARDS_Y = 8;
    private final static int CARDS_X = 110;
    public final static int CARD_WIDTH = 65;
    public final static int CARD_HEIGHT = 90;
    private ArrayList<PlantCard> plantCards;
    
    public final static int PlantCardManager_WIDTH = 300;
    public final static int PlantCardManager_HEIGHT = 100;
    
    private Map<String, Spirit> cardSpirits = new HashMap<>();
    
    public PlantCardManager(GamePanel gamePanel) {
        super(gamePanel, 0, 0, PlantCardManager_WIDTH, PlantCardManager_HEIGHT, 0, 0);
        setSize(PlantCardManager_WIDTH, PlantCardManager_HEIGHT);
    }
    
    
    
    public void addPlantCard(String plantRegisterName) {
        String cardRegisterName = getCardRegisterName(plantRegisterName);
        int x = CARDS_X + CARD_WIDTH * plantCards.size();
        int y = CARDS_Y + CARD_HEIGHT;
        Spirit spirit = cardSpirits.get(cardRegisterName);
        PlantCard plantCard = new PlantCard(gamePanel, x, y, plantRegisterName, cardRegisterName, spirit);
        plantCards.add(plantCard);
        addMouseListener(plantCard);
        logger.info("card {} added.", cardRegisterName);
    }

    @Override
    public void updateLogicFrame() {
        for (PlantCard plantCard : plantCards) {
            plantCard.updateLogicFrame();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (PlantCard plantCard : plantCards) {
            plantCard.drawSelf(g);
        }
    }
    
    public static String getCardRegisterName(String plantRegisterName) {
        return plantRegisterName + "_card";
    }


    @Override
    public void initChild() {
        plantCards = new ArrayList<>();
    }
    

    public Map<String, Spirit> getCardSpirits() {
        return cardSpirits;
    }

    
    
    

}
