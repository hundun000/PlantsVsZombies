package game.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import game.GamePanel;
import game.ILogicFrameListener;
import game.entity.zombie.BaseZombie;
import game.entity.zombie.BaseZombie.ZombieType;

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
        rules.add(new NaturalZombieSpawnRule(ZombieType.NORMAL_ZOMBIE, 60));
        rules.add(new NaturalZombieSpawnRule(ZombieType.NORMAL_ZOMBIE, 100));
        rules.add(new NaturalZombieSpawnRule(ZombieType.CONEHEAD_ZOMBIE, 100));
    }
    
    class NaturalZombieSpawnRule {
        
        ZombieType type;
        int startFrame;
        
        public NaturalZombieSpawnRule(ZombieType type, int startFrame) {
            super();
            this.type = type;
            this.startFrame = startFrame;
        }
        
        public int getStartFrame() {
            return startFrame;
        }
        
        public ZombieType getType() {
            return type;
        }
    }
    
    @Override
    public void updateLogicFrame() {
        if (!rules.isEmpty()) {
            Iterator<NaturalZombieSpawnRule> iterator = rules.iterator();
            while (iterator.hasNext()) {
                NaturalZombieSpawnRule rule = iterator.next();
                if (rule.getStartFrame() == gamePanel.logicFrameCounter) {
                    int lane = rnd.nextInt(5);
                    BaseZombie zombie = BaseZombie.getZombie(rule.getType(), gamePanel, lane);
                    gamePanel.getZombieManager().addZombie(zombie);
                    iterator.remove();
                }
            }
        }
    }

}
