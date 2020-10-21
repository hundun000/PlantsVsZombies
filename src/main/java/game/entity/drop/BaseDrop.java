package game.entity.drop;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.entity.component.DropPositionComponent;
import game.entity.component.FightComponent;
import game.entity.component.HealthComponent;
import game.entity.component.PositionComponent;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.GameObject;
import game.manager.GridManager;
import game.manager.SunScoreManager.WealthType;
import game.ui.GamePanel;

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
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int eventGridsX = e.getX() - GridManager.POSITION_START_X;
        int eventGridsY = e.getY() - GridManager.POSITION_START_Y;
        if (!getPositionComponent().getCoillderBox().contains(eventGridsX, eventGridsY)) {
            return;
        }
        
    }
    
    @Override
    protected boolean wantMove() {
        return true;
    }
    
    protected abstract void charge();

}
