package game.entity.zombie;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.entity.component.PositionComponent;
import game.entity.component.ZombiePositionComponent;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.GameObject;
import game.entity.gameobject.GameObjectStatus;
import game.entity.gameobject.HealthComponent;
import game.entity.plant.PlantSlot;
import game.manager.GridManager;
import game.manager.ZombieManager;
import game.mod.pvz.zombie.ConeHeadZombie;
import game.mod.pvz.zombie.NormalZombie;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class BaseZombie extends FightObject {
    static Logger logger = LoggerFactory.getLogger(BaseZombie.class);
  
    protected ZombiePositionComponent zombiePositionComponent;
    protected HealthComponent healthComponent;
    protected ZombieModel model;
    protected GameObjectStatus status;
    protected int damagePerFrame;
    public BaseZombie(GamePanel parent, ZombieModel model, ZombieInstanceParams params) {
        super(parent, model.registerName);
        super.spirit = model.spirit;
        super.coillderBoxColor = Color.DARK_GRAY;
        this.model = model;
        this.zombiePositionComponent = new ZombiePositionComponent(parent, model, params);
        this.status = new GameObjectStatus(this, model, params);
        this.damagePerFrame = GamePanel.perSecondToPerFrame(model.damagePerSecond);
        this.healthComponent = new HealthComponent(this, model, params);
    }

    public void setSlowFrame(int slowInt) {
        this.zombiePositionComponent.setFreezeFrame(slowInt);
    }

    
    @Override
    public PositionComponent getPositionComponent() {
        return zombiePositionComponent;
    }

    
    @Override
    public GameObjectStatus getStatus() {
        return status;
    }
    
    @Override
    protected boolean wantAttack() {
        PlantSlot collided = gamePanel.getGridManager().getCollideredPlantSlot(getPositionComponent());
        return collided != null;
    }

    @Override
    protected void attack() {
        PlantSlot collided = gamePanel.getGridManager().getCollideredPlantSlot(getPositionComponent());

        if (collided != null) {
            collided.getPlant().getHealthComponent().subtractHealth(model.damagePerSecond);

            if (!collided.getPlant().getHealthComponent().alive()) {
                collided.clearPlant();
            }
        }
    }
    
    @Override
    protected boolean wantUseSkill() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    protected void useSkill() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public HealthComponent getHealthComponent() {
        return healthComponent;
    }

}
