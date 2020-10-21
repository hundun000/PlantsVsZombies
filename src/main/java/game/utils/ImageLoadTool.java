package game.utils;

import java.io.File;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.gameobject.WorkStatus.WorkState;
import game.manager.PlantCardManager;
import game.mod.pvz.PvzMod;
import game.ui.GameWindow;

/**
 * @author hundun
 * Created on 2020/09/09
 */
public class ImageLoadTool {
    private static Logger logger = LoggerFactory.getLogger(ImageLoadTool.class);
    private static final String IMAGES_FOLDER = GameWindow.RESOURCE_FOLDER + "images/";
    public static ImageIcon defaultIcon = loadOneImage("", "default", "png");
    public static ImageIcon loadOnePlantCardImage(String modName, String registerName) {
        String folder = modName + "/cards/";
        String extend = "png";
        return loadOneImage(folder, registerName, extend);
    }
    public static ImageIcon loadOneOtherImage(String modName, String registerName) {
        String folder = modName + "/others/";
        String extend = "png";
        return loadOneImage(folder, registerName, extend);
    }
    public static ImageIcon loadOneZombieImage(String modName, String registerName) {
        String folder = modName + "/zombies/";
        String extend = "png";
        return loadOneImage(folder, registerName, extend);
    }
    public static ImageIcon loadOnePlantImage(String modName, String registerName, WorkState workState) {
        String folder = modName + "/plants/" + registerName + "/";
        String extend = "png";
        String fileName = workState.name().toLowerCase();
        return loadOneImage(folder, fileName, extend);
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
    private static ImageIcon loadOneImage(String folder, String fileName, String extend) {
        String filePath = IMAGES_FOLDER + folder + fileName + "." + extend;
        File file = new File(filePath);
        if (file.exists()) {
            ImageIcon image = new ImageIcon(filePath);
            image.setDescription(fileName);
            return image;
        } else {
            logger.warn("file {} not found", filePath);
            return defaultIcon;
        }
    }
    
}
