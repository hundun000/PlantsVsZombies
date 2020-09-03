package game.manager;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.GameWindow;
import game.ImageManager;
import game.entity.plant.BasePlant;
import game.entity.plant.FreezePeashooter;
import game.entity.plant.Peashooter;
import game.entity.plant.Sunflower;
import game.planting.PlantCard;

/**
 * @author hundun
 * Created on 2020/09/01
 */
public class PlantCardManager extends BaseManager {
    static Logger logger = LoggerFactory.getLogger(PlantCardManager.class);
    
    private final static int LAYER_CARD = 0;
    private ArrayList<PlantCard> plantCards;
    
    public PlantCardManager(GamePanel gamePanel) {
        super(gamePanel);
    }
    
    
    
    private void addPlantCard(String registerName, int x, int y, String plantRegisterName) {
        Image image = ImageManager.getImage(registerName);
        PlantCard plantCard = new PlantCard(gamePanel, image, x, y, plantRegisterName);
        plantCards.add(plantCard);
        add(plantCard, LAYER_CARD);
        logger.info("card {} added.", registerName);
    }

    @Override
    public void updateLogicFrame() {
    }
    
    public static String getCardRegisterName(String plantRegisterName) {
        return plantRegisterName + "_card";
    }


    @Override
    public void initChild() {
        plantCards = new ArrayList<>();
        
        String sunflowerImage = getCardRegisterName(Sunflower.REGISTER_NAME);
        this.addPlantCard(sunflowerImage, 110, 8, Sunflower.REGISTER_NAME);

        String peashooterImage = getCardRegisterName(Peashooter.REGISTER_NAME);
        this.addPlantCard(peashooterImage, 175, 8, Peashooter.REGISTER_NAME);

        String freezepeashooterImage = getCardRegisterName(FreezePeashooter.REGISTER_NAME);
        this.addPlantCard(freezepeashooterImage, 240, 8, FreezePeashooter.REGISTER_NAME);
    }
    



    
    
    

}
