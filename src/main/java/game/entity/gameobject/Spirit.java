package game.entity.gameobject;

import java.awt.Image;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import game.entity.gameobject.WorkStatus.WorkState;
import game.utils.ImageLoadTool;


/**
 * @author hundun
 * Created on 2020/09/09
 */
public class Spirit {
    
    private final Map<WorkState, List<ImageIcon>> images = new HashMap<>();
    
    public Spirit(ImageIcon singleIcon) {
        this(Arrays.asList(singleIcon));
    }
    
    public Spirit(List<ImageIcon> singleIcons) {
        images.put(WorkState.IDLE, singleIcons);
        images.put(WorkState.WORK_READY, singleIcons);
        images.put(WorkState.WORKING, singleIcons);
    }

    public Spirit set(WorkState workState, List<ImageIcon> singleIcons) {
        images.put(workState, singleIcons);
        return this;
    }
    
    public Spirit build(WorkState workState, ImageIcon singleIcon) {
        return set(workState, Arrays.asList(singleIcon));
    }
    
    
    public ImageIcon getImage() {
        return getImage(WorkState.IDLE, 0);
    }
    
    public ImageIcon getImage(WorkState workState, int subtypeIndex) {
        List<ImageIcon> stateImages = images.get(workState);
        if (stateImages != null && stateImages.size() > subtypeIndex) {
            return stateImages.get(subtypeIndex);
        } else {}
            return ImageLoadTool.defaultIcon;
        
    }

    
    
}
