package game.gameobject.gameobject;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.gameobject.gameobject.WorkStatus.WorkState;


/**
 * @author hundun
 * Created on 2020/09/09
 */
public class Spirit {
    
    private final ImageIcon idleImageIcon;
    private final ImageIcon workingImageIcon;
    
    public Spirit(ImageIcon idleImageIcon) {
        this(idleImageIcon, idleImageIcon);
    }
    
    public Spirit(ImageIcon idleImageIcon, ImageIcon workingImageIcon) {
        this.idleImageIcon = idleImageIcon;
        this.workingImageIcon = workingImageIcon;
    }

    

    public ImageIcon getImage(WorkState workStatus) {
        switch (workStatus) {
            case WORKING: 
                return workingImageIcon;
            default:
                return idleImageIcon;
        }
    }
    
}
