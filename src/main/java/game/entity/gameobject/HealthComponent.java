package game.entity.gameobject;

import game.entity.drop.BaseDrop;
import game.entity.drop.DropInstanceParams;
import game.entity.drop.DropModel;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.entity.zombie.BaseZombie;
import game.entity.zombie.ZombieInstanceParams;
import game.entity.zombie.ZombieModel;

/**
 * @author hundun
 * Created on 2020/09/18
 */
public class HealthComponent {
    
    /**
     * 不通过具体数值来决定存亡时使用。将health设为该值。
     */
    private static final int UNCOUNTABLE_HEALTH = -1;
    
    private int health;

    public HealthComponent(BaseZombie baseZombie, ZombieModel model, ZombieInstanceParams params) {
        this(model.health);
    }
    
    public HealthComponent(int health) {
        this.health = health;
    }

    public HealthComponent() {
        this(UNCOUNTABLE_HEALTH);
    }

    public HealthComponent(BaseDrop baseDrop, DropModel model, DropInstanceParams params) {
        this(model.disappearFrame);
    }

    public HealthComponent(BasePlant basePlant, PlantModel model, PlantInstanceParams params) {
        this(model.health);
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
    
    public void forceKilled() {
        this.health = 0;
    }
}
