package game.gameobject.bullet;

import java.awt.Rectangle;
import java.util.List;

import game.GamePanel;
import game.component.BulletPositionComponent;
import game.component.PositionComponent;
import game.gameobject.GameObject;
import game.gameobject.zombie.BaseZombie;

/**
 * @author hundun
 * Created on 2020/09/02
 */
public abstract class BaseBullet extends GameObject {

    private BulletPositionComponent bulletPositionComponent;
    private BulletModel model;
    public BaseBullet(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, model.registerName);
        super.spirit = model.spirit;
        this.model = model;
        this.bulletPositionComponent = new BulletPositionComponent(gamePanel, model, params);
    }
    
    public int getDamage() {;
        return model.damage;
    }
    
    public void addDebuff(BaseZombie zombie) {
    }
    
    
    
    @Override
    public PositionComponent getPositionComponent() {
        return bulletPositionComponent;
    }
    
    @Override
    public void updateLogicFrame() {
        bulletPositionComponent.updateLogicFrame();
        
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
    
    @Override
    public String toString() {
        return super.toString();
    }

}
