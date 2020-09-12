package game.level;

import java.util.List;

/**
 * @author hundun
 * Created on 2020/09/12
 */
public class GameLevel {
    
    public GameLevel(List<NaturalZombieSpawnRule> rules) {
        this.rules = rules;
    }

    private List<NaturalZombieSpawnRule> rules;
    
    public List<NaturalZombieSpawnRule> getRules() {
        return rules;
    }

}
