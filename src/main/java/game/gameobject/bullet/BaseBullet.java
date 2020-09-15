package game.gameobject.bullet;

import java.awt.Rectangle;
import java.util.List;

import game.GamePanel;
import game.component.BulletPositionComponent;
import game.component.PositionComponent;
import game.gameobject.gameobject.GameObject;
import game.gameobject.gameobject.GameObjectStatus;
import game.gameobject.zombie.BaseZombie;

/**
 * @author hundun
 * Created on 2020/09/02
 */
public abstract class BaseBullet extends GameObject {

    private BulletPositionComponent bulletPositionComponent;
    private BulletModel model;
    protected GameObjectStatus status;
    
    public BaseBullet(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, model.registerName);
        super.spirit = model.spirit;
        this.model = model;
        this.bulletPositionComponent = new BulletPositionComponent(gamePanel, model, params);
        this.status = new GameObjectStatus(this, model, params);
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

    
    public void bulletHitZombie(BaseZombie zombie) {

        
        zombie.getStatus().subtractHealth(this.getDamage());
        this.addDebuff(zombie);
        

    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    @Override
    public GameObjectStatus getStatus() {
        return status;
    }

    @Override
    protected boolean wantWork() {
        return true;
    }

    @Override
    protected void work() {
        Rectangle bulletRect = this.getPositionComponent().getCoillderBox();
        List<BaseZombie> zombies = gamePanel.getZombieManager().getZombiesIntersected(bulletRect);
        if (!zombies.isEmpty()) {
            BaseZombie zombie = zombies.get(0);
            bulletHitZombie(zombie);
            this.getStatus().forceKilled();
        }
    }

}
