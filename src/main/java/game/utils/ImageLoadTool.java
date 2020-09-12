package game.utils;

import java.io.File;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GameWindow;
import game.manager.PlantCardManager;
import game.pvz.PvzMod;

/**
 * @author hundun
 * Created on 2020/09/09
 */
public class ImageLoadTool {
    private static Logger logger = LoggerFactory.getLogger(ImageLoadTool.class);
    private static final String IMAGES_FOLDER = GameWindow.RESOURCE_FOLDER + "images/";
    
    public static ImageIcon loadOnePlantCardImage(String modName, String registerName) {
        String folder = modName + "/cards/";
        String extend = "png";
        return loadOneImage(folder, registerName, extend);
    }
    public static ImageIcon loadOneZombieImage(String modName, String registerName) {
        String folder = modName + "/zombies/";
        String extend = "png";
        return loadOneImage(folder, registerName, extend);
    }
    public static ImageIcon loadOnePlantImage(String modName, String registerName) {
        String folder = modName + "/plants/";
        String extend = "gif";
        return loadOneImage(folder, registerName, extend);
    }
    public static ImageIcon loadOneBulletImage(String modName, String registerName) {
        String folder = modName + "/bullets/";
        String extend = "png";
        return loadOneImage(folder, registerName, extend);
    }
    public static ImageIcon loadOneDropImage(String modName, String registerName) {
        String folder = modName + "/drops/";
        String extend = "png";
        return loadOneImage(folder, registerName, extend);
    }
    private static ImageIcon loadOneImage(String folder, String registerName, String extend) {
        String fileName = IMAGES_FOLDER + folder + registerName + "." + extend;
        File file = new File(fileName);
        if (file.exists()) {
            ImageIcon image = new ImageIcon(fileName);
            return image;
        } else {
            logger.warn("file {} not found", fileName);
            return null;
        }
    }
    
}
