package game.level;
/**
 * @author hundun
 * Created on 2020/09/12
 */
public class NaturalZombieSpawnRule {
    String zombieRegisterName;
    int startFrame;
    
    public NaturalZombieSpawnRule(String zombieRegisterName, int startFrame) {
        super();
        this.zombieRegisterName = zombieRegisterName;
        this.startFrame = startFrame;
    }
    
    public int getStartFrame() {
        return startFrame;
    }
    
    public String getZombieRegisterName() {
        return zombieRegisterName;
    }
}
