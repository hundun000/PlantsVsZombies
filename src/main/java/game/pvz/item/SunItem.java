package game.pvz.item;
import game.GamePanel;
import game.ILogicFrameListener;
import game.component.PositionComponent;
import game.component.SunPositionComponent;
import game.gameobject.GameObject;
import game.manager.GridManager;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/27/2016.
 */
public class SunItem extends GameObject implements MouseListener, ILogicFrameListener {
    public static String REGISTER_NAME = "sun";
    
    private int chargePoint = 25;
    protected SunPositionComponent sunPositionComponent;
    
    public SunItem(GamePanel gamePanel, int startX, int startY, int endY) {
        super(gamePanel, REGISTER_NAME);
        super.spirit = gamePanel.getSunScoreManager().getSunItemSpirit();
        this.sunPositionComponent = new SunPositionComponent(gamePanel, startX, startY, endY);
    }

    @Override
    public void updateLogicFrame() {
        sunPositionComponent.move();
        if (sunPositionComponent.getDestructCount() < 0) {
            gamePanel.getGridManager().deleteSun(this);
        }
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

    @Override
    public PositionComponent getPositionComponent() {
        return sunPositionComponent;
    }
    
    public int getChargePoint() {
        return chargePoint;
    }




}
