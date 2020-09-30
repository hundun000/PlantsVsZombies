package game.entity.gameobject;

import java.awt.Image;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.gameobject.WorkStatus.WorkState;
import game.utils.ImageLoadTool;


/**
 * @author hundun
 * Created on 2020/09/09
 */
public class Spirit {
    
    private static Logger logger = LoggerFactory.getLogger(Spirit.class);
    
    
    private final Map<String, ImageIcon> images = new HashMap<>();
    private String owner; 

    public Spirit(String owner, ImageIcon... icons) {
        this.owner = owner;
        for (ImageIcon icon : icons) {
            add(icon);
        }
    }
    
    public Spirit add(ImageIcon singleIcon) {
        images.put(singleIcon.getDescription(), singleIcon);
        return this;
    }

    
    public ImageIcon getImage(String id) {
        if (images.containsKey(id)) {
            return images.get(id);
        } else {
            for (Entry<String, ImageIcon> entry : images.entrySet()) {
                if (entry.getKey().startsWith(id)) {
                    return entry.getValue();
                }
            }
            logger.warn("Sprite[{}] no image of id = {}", owner, id);
            return ImageLoadTool.defaultIcon;
        }
    }

    
    
}
