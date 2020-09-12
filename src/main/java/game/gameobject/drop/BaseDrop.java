package game.gameobject.drop;

import java.awt.event.MouseListener;

import game.GamePanel;
import game.component.DropPositionComponent;
import game.gameobject.GameObject;

/**
 * @author hundun
 * Created on 2020/09/12
 */
public abstract class BaseDrop extends GameObject implements MouseListener {
    
    protected DropPositionComponent dropPositionComponent;
    protected DropModel model;
    
    public BaseDrop(GamePanel gamePanel, DropModel model, DropInstanceParams params) {
        super(gamePanel, model.registerName);
        super.spirit = model.spirit;
        this.model = model;
        this.dropPositionComponent = new DropPositionComponent(gamePanel, params.startX, params.startY, params.endY);
    }
    
    public int getChargePoint() {
        return model.chargePoint;
    }

}
