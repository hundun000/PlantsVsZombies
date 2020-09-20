package game.level;

import java.util.List;

import game.entity.plant.PlantCard;

/**
 * @author hundun
 * Created on 2020/09/12
 */
public class GameLevel {
    
    private List<NaturalZombieSpawnRule> naturalZombieSpawnRules;
    
    
    public GameLevel(List<NaturalZombieSpawnRule> rules) {
        this.naturalZombieSpawnRules = rules;
    }

    public List<NaturalZombieSpawnRule> getNaturalZombieSpawnRules() {
        return naturalZombieSpawnRules;
    }

}
