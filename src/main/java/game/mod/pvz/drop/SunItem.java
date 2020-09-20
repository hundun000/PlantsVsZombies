package game.mod.pvz.drop;
import game.GamePanel;
import game.ILogicFrameListener;
import game.entity.component.DropPositionComponent;
import game.entity.component.PositionComponent;
import game.entity.drop.BaseDrop;
import game.entity.drop.DropInstanceParams;
import game.entity.drop.DropModel;
import game.entity.gameobject.GameObject;
import game.entity.gameobject.GameObjectStatus;
import game.manager.GridManager;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Created by Armin on 6/27/2016.
 */
public class SunItem extends BaseDrop implements MouseListener {
    public static String NAME = "sun";
    
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
