package game.entity.item;
import game.GamePanel;
import game.ILogicFrameListener;
import game.component.PositionComponent;
import game.component.SunPositionComponent;
import game.entity.GameObject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/27/2016.
 */
public class SunItem extends GameObject implements MouseListener, ILogicFrameListener {
    private int chargePoint = 25;
    private SunPositionComponent sunPositionComponent;
    
    public SunItem(GamePanel parent, int startX, int startY, int endY) {
        super(parent, "sun");
        this.sunPositionComponent = new SunPositionComponent(parent, startX, startY, endY);
    }
    
    public SunPositionComponent getSunPositionComponent() {
        return sunPositionComponent;
    }

    @Override
    public void updateLogicFrame() {
        sunPositionComponent.move();
        if (sunPositionComponent.getDestruct() < 0) {
            gamePanel.getSunScoreManager().deleteSun(this);
        }
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gamePanel.getSunScoreManager().chargeSunPointAndDelete(this);
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
