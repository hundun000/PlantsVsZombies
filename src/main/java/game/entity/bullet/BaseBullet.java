package game.entity.bullet;

import java.awt.Rectangle;
import java.util.List;

import game.GamePanel;
import game.component.BulletPositionComponent;
import game.component.PositionComponent;
import game.entity.GameObject;
import game.entity.zombie.BaseZombie;

/**
 * @author hundun
 * Created on 2020/09/02
 */
public abstract class BaseBullet extends GameObject {

    private BulletPositionComponent bulletPositionComponent;
    
    public BaseBullet(GamePanel gamePanel, String registerName, int x, int y) {
        super(gamePanel, registerName);
        this.bulletPositionComponent = new BulletPositionComponent(gamePanel, x, y);
    }
    
    public abstract int getDamage();
    
    public void addDebuff(BaseZombie zombie) {
    }
    
    
    
    @Override
    public PositionComponent getPositionComponent() {
        return bulletPositionComponent;
    }
    
    @Override
    public void updateLogicFrame() {
        bulletPositionComponent.move();
        
        Rectangle bulletRect = this.getPositionComponent().getCoillderBox();
        List<BaseZombie> zombies = gamePanel.getZombieManager().getZombiesIntersected(bulletRect);
        if (!zombies.isEmpty()) {
            BaseZombie zombie = zombies.get(0);
            bulletHitZombie(zombie);
            gamePanel.getGridManager().removeBullet(this);
        }
    }
    
    public void bulletHitZombie(BaseZombie zombie) {

        final boolean zombiehasHealth = zombie.getHealth() >= 0;
        
        zombie.setHealth(zombie.getHealth() - this.getDamage());
        this.addDebuff(zombie);
        if (!zombiehasHealth) {
            System.out.println("ZOMBIE DIED");
            gamePanel.getZombieManager().removeZombie(zombie);
            gamePanel.addLevelPoint(10);
        }

    }

}
