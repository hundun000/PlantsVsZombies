package game.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import game.GamePanel;
import game.GamePanel.GameState;
import game.entity.zombie.BaseZombie;
import game.ILevelListener;
import game.ILogicFrameListener;
import game.level.GameLevel;
import game.level.NaturalZombieSpawnRule;

/**
 * @author hundun
 * Created on 2020/08/31
 */
public class NaturalZombieProducer implements ILogicFrameListener, ILevelListener {
    GameLevel level;
    
    protected final GamePanel gamePanel;
    Random rnd = new Random();
    
    public NaturalZombieProducer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void updateLogicFrame() {
        if (level != null) {
            Iterator<NaturalZombieSpawnRule> iterator = level.getNaturalZombieSpawnRules().iterator();
            while (iterator.hasNext()) {
                NaturalZombieSpawnRule rule = iterator.next();
                if (rule.getStartFrame() == gamePanel.logicFrameCounter) {
                    int lane = rnd.nextInt(GridManager.NUM_ROW_CONSTANT);
                    gamePanel.getGridManager().addZombie(rule.getZombieRegisterName(), lane);
                    iterator.remove();
                }
            }
        }
    }
    
    @Override
    public void levelStart(GameLevel level) {
        this.level = level;
    }

    @Override
    public void levelEnd() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateGameState(GameState gameState) {
        // TODO Auto-generated method stub
        
    }

}
