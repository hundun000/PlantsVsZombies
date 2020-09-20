package game.entity.bullet;

import java.awt.Rectangle;
import java.util.List;

import game.GamePanel;
import game.entity.component.BulletPositionComponent;
import game.entity.component.PositionComponent;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.GameObject;
import game.entity.gameobject.GameObjectStatus;
import game.entity.gameobject.HealthComponent;
import game.entity.zombie.BaseZombie;

/**
 * @author hundun
 * Created on 2020/09/02
 */
public abstract class BaseBullet extends FightObject {

    private BulletPositionComponent bulletPositionComponent;
    private BulletModel model;
    protected GameObjectStatus status;
    protected HealthComponent healthComponent;
    public BaseBullet(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, model.registerName);
        super.spirit = model.spirit;
        this.model = model;
        this.bulletPositionComponent = new BulletPositionComponent(gamePanel, model, params);
        this.status = new GameObjectStatus(this, model, params);
        this.healthComponent = new HealthComponent();
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

        
        zombie.getHealthComponent().subtractHealth(this.getDamage());
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
    protected boolean wantAttack() {
        return true;
    }
    
    

    @Override
    protected void attack() {
        Rectangle bulletRect = this.getPositionComponent().getCoillderBox();
        List<BaseZombie> zombies = gamePanel.getZombieManager().getZombiesIntersected(bulletRect);
        if (!zombies.isEmpty()) {
            BaseZombie zombie = zombies.get(0);
            bulletHitZombie(zombie);
            this.getHealthComponent().forceKilled();
        }
    }
    
    @Override
    protected boolean wantUseSkill() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    protected void useSkill() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public HealthComponent getHealthComponent() {
        return this.healthComponent;
    }

}
