package game.gameobject.zombie;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.OnLevelUpListener;
import game.component.PositionComponent;
import game.component.ZombiePositionComponent;
import game.gameobject.GameObject;
import game.manager.GridManager;
import game.manager.ZombieManager;
import game.pvz.zombie.ConeHeadZombie;
import game.pvz.zombie.NormalZombie;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class BaseZombie extends GameObject implements OnLevelUpListener {
    static Logger logger = LoggerFactory.getLogger(BaseZombie.class);
    private int health = 1000;

    
    protected ZombiePositionComponent zombiePositionComponent;
    protected ZombieModel model;
    
    public BaseZombie(GamePanel parent, ZombieModel model, ZombieInstanceParams params) {
        super(parent, model.registerName);
        super.spirit = model.spirit;
        super.coillderBoxColor = Color.DARK_GRAY;
        this.model = model;
        this.health = model.health;
        this.zombiePositionComponent = new ZombiePositionComponent(parent, model, params);
    }


    @Override
    public void onLevelUp() {

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSlowFrame(int slowInt) {
        this.zombiePositionComponent.setFreezeFrame(slowInt);
    }

    
    @Override
    public PositionComponent getPositionComponent() {
        return zombiePositionComponent;
    }
    
    @Override
    public void updateLogicFrame() {
        zombiePositionComponent.move();
    }
    
    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        if (GamePanel.DRAW_DEBUG_BOX) {
            drawHealthBar(g, health);
        }
    }
    

}
