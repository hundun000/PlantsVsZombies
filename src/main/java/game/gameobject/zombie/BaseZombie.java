package game.gameobject.zombie;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.component.PositionComponent;
import game.component.ZombiePositionComponent;
import game.gameobject.gameobject.GameObject;
import game.gameobject.gameobject.GameObjectStatus;
import game.gameobject.plant.PlantSlot;
import game.manager.GridManager;
import game.manager.ZombieManager;
import game.pvz.zombie.ConeHeadZombie;
import game.pvz.zombie.NormalZombie;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class BaseZombie extends GameObject {
    static Logger logger = LoggerFactory.getLogger(BaseZombie.class);
  
    protected ZombiePositionComponent zombiePositionComponent;
    protected ZombieModel model;
    protected GameObjectStatus status;
    
    public BaseZombie(GamePanel parent, ZombieModel model, ZombieInstanceParams params) {
        super(parent, model.registerName);
        super.spirit = model.spirit;
        super.coillderBoxColor = Color.DARK_GRAY;
        this.model = model;
        this.zombiePositionComponent = new ZombiePositionComponent(parent, model, params);
        this.status = new GameObjectStatus(this, model, params);
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
    protected boolean wantWork() {
        PlantSlot collided = gamePanel.getGridManager().getCollideredPlantSlot(getPositionComponent());
        return collided != null;
    }

    @Override
    protected void work() {
        PlantSlot collided = gamePanel.getGridManager().getCollideredPlantSlot(getPositionComponent());

        if (collided != null) {
            collided.getPlant().getStatus().subtractHealth(10);

            if (!collided.getPlant().getStatus().alive()) {
                collided.clearPlant();
            }
        }
    }

}
