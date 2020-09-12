package game.manager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.gameobject.zombie.BaseZombie;
import game.gameobject.zombie.ZombieInstanceParams;

/**
 * @author hundun
 * Created on 2020/09/02
 */
public class ZombieManager extends BaseManager {
    private static Logger logger = LoggerFactory.getLogger(ZombieManager.class);
    
    public static final int MANAGER_START_Y = 0;
    
    public static final int MANAGER_START_X = 0;
   
    public static final int POSITION_START_X = 50;
    public static final int POSITION_START_Y = 110;
    
    public static final int MANAGER_WIDTH = GamePanel.SCREEN_WIDTH_CONSTANT;
    public static final int MANAGER_HEIGHT = GamePanel.SCREEN_HEIGHT_CONSTANT;
    
    private List<BaseZombie> zombies;
    private NaturalZombieProducer naturalZombieProducer;
    
    public ZombieManager(GamePanel gamePanel) {
        super(gamePanel, ZombieManager.MANAGER_START_X, ZombieManager.MANAGER_START_Y, ZombieManager.MANAGER_WIDTH, ZombieManager.MANAGER_HEIGHT, POSITION_START_X, POSITION_START_Y);
        //setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
    }

    @Override
    public void updateLogicFrame() {
        if (naturalZombieProducer != null) {
            naturalZombieProducer.updateLogicFrame();
        }
        
        for (BaseZombie zombie : zombies) {
            zombie.updateLogicFrame();
        }
    }

    @Override
    public void initChild() {
        zombies = new LinkedList<>();

        naturalZombieProducer = new NaturalZombieProducer(gamePanel);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.translate(ZombieManager.POSITION_START_X, ZombieManager.POSITION_START_Y);
        
        for (BaseZombie zombie : zombies) {
            zombie.drawSelf(g);
        }
    }
    
    public void addZombie(String zombieRegisterName, int lane) {
        ZombieInstanceParams params = new ZombieInstanceParams(lane);
        
        BaseZombie zombie = gamePanel.getZombieFactory().getInstacne(zombieRegisterName, gamePanel, params);
        addZombie(zombie);
    }
    
    public void addZombie(BaseZombie zombie) {
        if (zombie != null) {
            zombies.add(zombie);
            logger.info(zombie.toString() + " created");
        }
    }
    
    public List<BaseZombie> getZombies() {
        return zombies;
    }

    public void removeZombie(BaseZombie zombie) {
        zombies.remove(zombie);
    }
    
    public List<BaseZombie> getZombiesIntersected(Rectangle rect) {
        List<BaseZombie> result = new ArrayList<>();
        for (BaseZombie zombie : zombies) {
            Rectangle zombieRect = zombie.getPositionComponent().getCoillderBox(); 
            
            if (rect.intersects(zombieRect)) {
                result.add(zombie);
            }
        }
        return result;
    }

    
    public NaturalZombieProducer getNaturalZombieProducer() {
        return naturalZombieProducer;
    }
}
