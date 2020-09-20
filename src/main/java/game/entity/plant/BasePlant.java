package game.entity.plant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.component.IdlePositionComponent;
import game.entity.component.PositionComponent;
import game.entity.drop.BaseDrop;
import game.entity.drop.DropInstanceParams;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.GameObject;
import game.entity.gameobject.GameObjectStatus;
import game.entity.gameobject.HealthComponent;
import game.entity.zombie.BaseZombie;
import game.manager.GridManager;
import game.manager.ZombieManager;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class BasePlant extends FightObject {
    private static Logger logger = LoggerFactory.getLogger(BasePlant.class);
    
    protected PositionComponent positionComponent;
    protected HealthComponent healthComponent;
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
        this.healthComponent = new HealthComponent(this, model, params);
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
    protected boolean wantAttack() {
        Rectangle attackRect = this.getAttackRangeBox();
        if (attackRect != null) {
            List<BaseZombie> zombies = gamePanel.getZombieManager().getZombiesIntersected(attackRect);
            return !zombies.isEmpty();
        } else {
            return false;
        }
    }
    
    
    @Override
    protected void attack() {
        BaseBullet bullet = generateBullet();
        if (bullet != null) {
            gamePanel.getGridManager().addBullet(bullet);
        }
    }
    
    @Override
    protected boolean wantUseSkill() {
        return false;
    }
    
    @Override
    protected void useSkill() {
        
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

    protected BaseDrop generateDrop() {
        if (this.model.dropRegisterName != null) {
            int startX = getBulletStartX();
            int startY = getBulletStartY();
            int endY = startY + (GridManager.GRID_HEIGHT / 2);
            DropInstanceParams params = new DropInstanceParams(startX, startY, endY);
            BaseDrop drop = gamePanel.getDropFactory().getInstacne(model.dropRegisterName, gamePanel, params);
            return drop;
        } else {
            return null;
        }
    }

    protected BaseBullet generateBullet() {
        if (this.model.bulletRegisterName != null) {
            int bulletStartX = getPositionComponent().getPosX() + this.model.bulletStartOffsetX;
            int bulletStartY = getPositionComponent().getPosY() + this.model.bulletStartOffsetY;
            
            BulletInstanceParams params = new BulletInstanceParams(bulletStartX, bulletStartY);
            BaseBullet bullet = gamePanel.getBulletFactory().getInstacne(
                    this.model.bulletRegisterName, gamePanel, params);
            return bullet;
        } else {
            return null;
        }
        
    }
    
    @Override
    public HealthComponent getHealthComponent() {
        return healthComponent;
    }
    

}
