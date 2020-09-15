package game.gameobject.drop;

import java.awt.event.MouseListener;

import game.GamePanel;
import game.component.DropPositionComponent;
import game.component.PositionComponent;
import game.gameobject.gameobject.GameObject;
import game.gameobject.gameobject.GameObjectStatus;

/**
 * @author hundun
 * Created on 2020/09/12
 */
public abstract class BaseDrop extends GameObject implements MouseListener {
    
    protected DropPositionComponent dropPositionComponent;
    protected DropModel model;
    protected GameObjectStatus status;
    
    public BaseDrop(GamePanel gamePanel, DropModel model, DropInstanceParams params) {
        super(gamePanel, model.registerName);
        super.spirit = model.spirit;
        this.model = model;
        this.dropPositionComponent = new DropPositionComponent(gamePanel, params.startX, params.startY, params.endY);
        this.status = new GameObjectStatus(this, model, params);
    }
    
    public int getChargePoint() {
        return model.chargePoint;
    }
    
    @Override
    public GameObjectStatus getStatus() {
        return status;
    }

    @Override
    protected boolean wantWork() {
        return true;
    }

    @Override
    protected void work() {
        getStatus().subtractHealth(1);
    }
    
    @Override
    public PositionComponent getPositionComponent() {
        return dropPositionComponent;
    }

}
