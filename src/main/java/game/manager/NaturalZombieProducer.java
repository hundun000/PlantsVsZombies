package game.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import game.GamePanel;
import game.ILogicFrameListener;
import game.gameobject.zombie.BaseZombie;

/**
 * @author hundun
 * Created on 2020/08/31
 */
public class NaturalZombieProducer implements ILogicFrameListener {
    List<NaturalZombieSpawnRule> rules = new ArrayList<>();
    
    protected final GamePanel gamePanel;
    Random rnd = new Random();
    
    public NaturalZombieProducer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        rules.add(new NaturalZombieSpawnRule("normal_zombie", 10));
        rules.add(new NaturalZombieSpawnRule("normal_zombie", 20));
        rules.add(new NaturalZombieSpawnRule("conehead_zombie", 20));
    }
    
    class NaturalZombieSpawnRule {
        
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
    
    @Override
    public void updateLogicFrame() {
        if (!rules.isEmpty()) {
            Iterator<NaturalZombieSpawnRule> iterator = rules.iterator();
            while (iterator.hasNext()) {
                NaturalZombieSpawnRule rule = iterator.next();
                if (rule.getStartFrame() == gamePanel.logicFrameCounter) {
                    int lane = rnd.nextInt(GridManager.NUM_ROW_CONSTANT);
                    gamePanel.getZombieManager().addZombie(rule.getZombieRegisterName(), lane);
                    iterator.remove();
                }
            }
        }
    }

}
