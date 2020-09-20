package game.entity.drop;

import java.awt.event.MouseListener;

import game.GamePanel;
import game.entity.component.DropPositionComponent;
import game.entity.component.PositionComponent;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.GameObject;
import game.entity.gameobject.GameObjectStatus;
import game.entity.gameobject.HealthComponent;

/**
 * @author hundun
 * Created on 2020/09/12
 */
public abstract class BaseDrop extends GameObject implements MouseListener {
    
    protected DropPositionComponent dropPositionComponent;
    protected DropModel model;
    protected HealthComponent healthComponent;
    
    public BaseDrop(GamePanel gamePanel, DropModel model, DropInstanceParams params) {
        super(gamePanel, model.registerName);
        super.spirit = model.spirit;
        this.model = model;
        this.dropPositionComponent = new DropPositionComponent(gamePanel, params.startX, params.startY, params.endY);
        this.healthComponent = new HealthComponent(this, model, params);
    }
    
    public int getChargePoint() {
        return model.chargePoint;
    }


    @Override
    public void updateLogicFrame() {
        super.updateLogicFrame();
        
        healthComponent.subtractHealth(1);
    }
    
    @Override
    public PositionComponent getPositionComponent() {
        return dropPositionComponent;
    }
    
    @Override
    public HealthComponent getHealthComponent() {
        return healthComponent;
    }

}
