package game.manager;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.ImageManager;
import game.entity.zombie.BaseZombie;

/**
 * @author hundun
 * Created on 2020/09/02
 */
public class ZombieManager extends BaseManager {
    static Logger logger = LoggerFactory.getLogger(ZombieManager.class);
    
    private List<BaseZombie> zombies;
    private NaturalZombieProducer naturalZombieProducer;
    
    public ZombieManager(GamePanel gamePanel) {
        super(gamePanel);
        setLocation(GridManager.GRIDS_LEFT_PADDING, GridManager.GRIDS_UP_PADDING);
    }

    @Override
    public void updateLogicFrame() {
        naturalZombieProducer.updateLogicFrame();
        
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
        for (BaseZombie zombie : zombies) {
            Image image = ImageManager.getImage(zombie.getRegisterName());
            g.drawImage(image, zombie.getPositionComponent().getPosX(), zombie.getPositionComponent().getPosY(), null);
        }
    }
    
    public void addZombie(BaseZombie zombie) {
        zombies.add(zombie);
        logger.info(zombie.getInstanceName() + " created");
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
            Rectangle zombieRect = zombie.getCoillderBox(); 
            
            if (rect.intersects(zombieRect)) {
                result.add(zombie);
            }
        }
        return result;
    }

}
