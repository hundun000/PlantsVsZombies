package game.gameobject.gameobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.ILogicFrameListener;
import game.gameobject.bullet.BulletInstanceParams;
import game.gameobject.bullet.BulletModel;
import game.gameobject.drop.DropInstanceParams;
import game.gameobject.drop.DropModel;
import game.gameobject.plant.BasePlant;
import game.gameobject.plant.PlantInstanceParams;
import game.gameobject.plant.PlantModel;
import game.gameobject.zombie.ZombieInstanceParams;
import game.gameobject.zombie.ZombieModel;

/**
 * @author hundun
 * Created on 2020/09/14
 */
public class GameObjectStatus implements ILogicFrameListener {
    private static Logger logger = LoggerFactory.getLogger(GameObjectStatus.class);
    /**
     * 不通过具体数值来决定存亡时使用。将health设为该值。
     */
    private static final int UNCOUNTABLE_HEALTH = -1;

    
    
    
    protected final GameObject gameObject;
    
    protected int health;
    
    
    
    private WorkStatus workStatus;
    
    
    public GameObjectStatus(GameObject gameObject) {
        this(gameObject, UNCOUNTABLE_HEALTH, new WorkStatus());
    }
    
    public GameObjectStatus(GameObject gameObject, DropModel model, DropInstanceParams params) {
        this(gameObject, model.disappearFrame, new WorkStatus(model, params));
    }
    
    public GameObjectStatus(GameObject gameObject, BulletModel model, BulletInstanceParams params) {
        this(gameObject, UNCOUNTABLE_HEALTH, new WorkStatus(model, params));
    }
    
    public GameObjectStatus(GameObject gameObject, ZombieModel model, ZombieInstanceParams params) {
        this(gameObject, model.health, new WorkStatus(model, params));
    }

    public GameObjectStatus(GameObject gameObject, PlantModel model, PlantInstanceParams params) {
        this(gameObject, model.health, new WorkStatus(model, params));
    }
    
    private GameObjectStatus(
            GameObject gameObject,
            int health,
            WorkStatus workStatus
            ) {
        this.gameObject = gameObject;
        this.health = health;
        this.workStatus = workStatus;
    }

    

    public int getHealth() {
        return health;
    }
    
    public void subtractHealth(int subtract) {
        if (isUncountableHealth()) {
            return;
        }
        health = Math.max(health - subtract, 0);
        
    }
    
    public boolean isUncountableHealth() {
        return health == UNCOUNTABLE_HEALTH;
    }
    
    public boolean alive() {
        return health > 0 || isUncountableHealth();
    }

    @Override
    public void updateLogicFrame() {
        
        boolean workHappen = workStatus.transition(gameObject.wantWork());
        if (workHappen) {
            gameObject.work();
        }

    }
    
    public WorkStatus getWorkStatus() {
        return workStatus;
    }

    public void forceKilled() {
        this.health = 0;
    }
    
}
