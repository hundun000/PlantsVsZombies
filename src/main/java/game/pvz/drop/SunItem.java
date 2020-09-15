package game.pvz.drop;
import game.GamePanel;
import game.ILogicFrameListener;
import game.component.PositionComponent;
import game.component.DropPositionComponent;
import game.gameobject.drop.BaseDrop;
import game.gameobject.drop.DropInstanceParams;
import game.gameobject.drop.DropModel;
import game.gameobject.gameobject.GameObject;
import game.gameobject.gameobject.GameObjectStatus;
import game.manager.GridManager;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Created by Armin on 6/27/2016.
 */
public class SunItem extends BaseDrop implements MouseListener {
    public static String REGISTER_NAME = "sun";
    
    public SunItem(GamePanel gamePanel, DropModel model, DropInstanceParams params) {
        super(gamePanel, model, params);
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        int eventGridsX = e.getX() - GridManager.POSITION_START_X;
        int eventGridsY = e.getY() - GridManager.POSITION_START_Y;
        if (!getPositionComponent().getCoillderBox().contains(eventGridsX, eventGridsY)) {
            return;
        }
        gamePanel.getGridManager().chargeSunPointAndDelete(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    

    
    
    




}
