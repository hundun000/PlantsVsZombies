package game.entity.zombie;



import java.awt.Graphics;
import java.awt.Rectangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.OnLevelUpListener;
import game.component.PositionComponent;
import game.component.ZombiePositionComponent;
import game.entity.GameObject;
import game.manager.GridManager;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class BaseZombie extends GameObject implements OnLevelUpListener {
    static Logger logger = LoggerFactory.getLogger(GamePanel.class);
    private int health = 1000;
    private int speed = 1;

    private GamePanel gp;
    private final String instanceName;
    private static int instanceCounter = 0;
    
    private int myLane;
    private boolean moving = true;
    //private static BaseZombie z;
    public int level = 0;
    protected ZombiePositionComponent zombiePositionComponent;
    
    public BaseZombie(GamePanel parent, int lane, String reregisterName, ZombiePositionComponent zombiePositionComponent) {
        super(parent, reregisterName);
        this.gp = parent;
        myLane = lane;
        this.zombiePositionComponent = zombiePositionComponent;
        this.instanceName = getRegisterName() + "【" + String.valueOf(instanceCounter++) + "】";
    }


    @Override
    public void onLevelUp() {

    }
    
    public enum ZombieType {
        NORMAL_ZOMBIE,
        CONEHEAD_ZOMBIE
    }

    public static BaseZombie getZombie(ZombieType type, GamePanel parent, int lane) {
        BaseZombie z;
        switch (type) {
            case NORMAL_ZOMBIE:
                z = new NormalZombie(parent, lane, new ZombiePositionComponent(parent, lane * GridManager.GRID_HEIGHT));
                break;
            case CONEHEAD_ZOMBIE:
                z = new ConeHeadZombie(parent, lane, new ZombiePositionComponent(parent, lane));
                break;
            default:
                z = null;
        }
        return z;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSlowFrame(int slowInt) {
        this.zombiePositionComponent.setSlowFrame(slowInt);
    }
    
    public boolean isMoving() {
        return moving;
    }
    
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    
    @Override
    public PositionComponent getPositionComponent() {
        return zombiePositionComponent;
    }
    
    @Override
    public void updateLogicFrame() {
        zombiePositionComponent.move();
    }
    
    public String getInstanceName() {
        return instanceName;
    }

    

}
