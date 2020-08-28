package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.plant.FreezePeashooter;
import game.entity.plant.Peashooter;
import game.entity.plant.Sunflower;
import game.manager.PlantCardManager;

/**
 * @author hundun
 * Created on 2020/08/29
 */
public class ImageManager {
    static Logger logger = LoggerFactory.getLogger(GamePanel.class);
    
    
    private static final String IMAGES_FOLDER = GameWindow.RESOURCE_FOLDER + "images/"; 
    
  
    private static Map<String, Image> images = new HashMap<>();
    
    static {
        loadCardImages();
        loadPlantImage();
        loadZombieImage();
        loadOther();
    }
    
    private static void loadOneImage(String folder, String registerName, String extend) {
        String fileName = IMAGES_FOLDER + folder + registerName + "." + extend;
        File file = new File(fileName);
        if (file.exists()) {
            Image image = new ImageIcon(fileName).getImage();
            images.put(registerName, image);
        } else {
            logger.warn("file {} not found", fileName);
        }
    }

    
    private static void loadCardImages() {
        String folder = "cards/";
        loadOneImage(folder, PlantCardManager.getCardRegisterName(Peashooter.REGISTER_NAME), "png");
        loadOneImage(folder, PlantCardManager.getCardRegisterName(Sunflower.REGISTER_NAME), "png");
        loadOneImage(folder, PlantCardManager.getCardRegisterName(FreezePeashooter.REGISTER_NAME), "png");
    }

    private static void loadZombieImage() {
        String folder = "zombies/";
        loadOneImage(folder, "zombie", "png");
        loadOneImage(folder, "conehead_zombie", "png");
    }

    private static void loadPlantImage() {
        String folder = "plants/";
        loadOneImage(folder, Peashooter.REGISTER_NAME, "gif");
        loadOneImage(folder, Sunflower.REGISTER_NAME, "gif");
        loadOneImage(folder, FreezePeashooter.REGISTER_NAME, "gif");
    }
    
    private static void loadOther() {
        String folder = "others/";
        loadOneImage(folder, "pea", "png");
        loadOneImage(folder, "freeze_pea", "png");
    }
    
    public static Image getImage(String registerName) {
        if (!images.containsKey(registerName)) {
            logger.error("image of registerName = {} not found.", registerName);
        }
        return images.get(registerName);
    }

}
