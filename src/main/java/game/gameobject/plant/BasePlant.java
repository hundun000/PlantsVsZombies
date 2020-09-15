package game.gameobject.plant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.component.IdlePositionComponent;
import game.component.PositionComponent;
import game.gameobject.gameobject.GameObject;
import game.gameobject.gameobject.GameObjectStatus;
import game.manager.GridManager;
import game.manager.ZombieManager;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class BasePlant extends GameObject {
    private static Logger logger = LoggerFactory.getLogger(BasePlant.class);
    
    protected PositionComponent positionComponent;
    protected PlantModel model;
    protected GameObjectStatus status;
    
    
    /**
     * 子类通过Factory构造，可能使用反射。
     */
    public BasePlant(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model.registerName);
        super.spirit = model.spirit;
        this.positionComponent = new IdlePositionComponent(gamePanel, model, params);
        this.model = model;
        this.status = new GameObjectStatus(this, model, params);
    }
   
    protected int getBulletStartX() {
        return getPositionComponent().getPosX() + model.bulletStartOffsetX;
    }
    
    protected int getBulletStartY() {
        return getPositionComponent().getPosY() + model.bulletStartOffsetY;
    }
    
    @Override
    public PositionComponent getPositionComponent() {
        return positionComponent;
    }
    
    @Override
    public GameObjectStatus getStatus() {
        return status;
    }
    
    
    
    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        if (GamePanel.DRAW_DEBUG_BOX) {
            Rectangle box = getAttackRangeBox();
            if (box != null) {
                Color last = g.getColor();
                g.setColor(Color.RED);
                g.drawRect((int)box.getX(), (int)box.getY(), (int)box.getWidth(), (int)box.getHeight());
                g.setColor(last);
            }
        }
    }
    
    
    
    
    
    
    public Rectangle getAttackRangeBox() {
        if (model.attackRangeWidth > 0) {
            int leftTopX = getPositionComponent().getPosX() + model.attackRangeOffsetX;
            int leftTopY = getPositionComponent().getPosY() + model.attackRangeOffsetY;
            return new Rectangle(leftTopX, leftTopY, model.attackRangeWidth, model.attackRangeHeight);
        } else {
            return null;
        }
        
    }
    
    public PlantModel getModel() {
        return model;
    }

    

}
