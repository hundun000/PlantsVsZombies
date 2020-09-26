package game.entity.bullet;

import java.awt.Rectangle;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.entity.bullet.template.Explosion;
import game.entity.component.BulletPositionComponent;
import game.entity.component.FightComponent;
import game.entity.component.HealthComponent;
import game.entity.component.PositionComponent;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.FightObject.FightSide;
import game.entity.gameobject.GameObject;
import game.entity.zombie.BaseZombie;

/**
 * @author hundun
 * Created on 2020/09/02
 */
public abstract class BaseBullet extends GameObject {
    private static Logger logger = LoggerFactory.getLogger(BaseBullet.class);
    private BulletPositionComponent bulletPositionComponent;
    private BulletModel model;
    protected FightComponent status;
    protected HealthComponent healthComponent;
    protected FightSide attacterSide;
    protected boolean forceKilledAfterHit = true;
    
    public BaseBullet(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, model.registerName);
        super.spirit = model.spirit;
        this.model = model;
        this.bulletPositionComponent = new BulletPositionComponent(gamePanel, model, params);
        //this.status = new GameObjectStatus(this, model, params);
        this.healthComponent = new HealthComponent();
        this.attacterSide = params.attacterSide;
    }
    
    public int getDamage() {;
        return model.damage;
    }
    
    public void addDebuff(FightObject targetObject) {
    }
    
    
    
    @Override
    public PositionComponent getPositionComponent() {
        return bulletPositionComponent;
    }

    
    public void bulletHitGameObject(FightObject targetObject) {

        
        targetObject.getHealthComponent().subtractHealth(this.getDamage());
        this.addDebuff(targetObject);
        

    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
//    @Override
//    public GameObjectStatus getStatus() {
//        return status;
//    }
//
//    @Override
//    protected boolean wantAttack() {
//        return true;
//    }

    
    @Override
    public void updateLogicFrame() {
        super.updateLogicFrame();
        
        List<FightObject> targets = calculateHitTargets();
        for (FightObject target : targets) {
            bulletHitGameObject(target);
            if (forceKilledAfterHit) {
                this.getHealthComponent().forceKilled();
            }
        }
        
    }
    
    protected List<FightObject> calculateHitTargets() {
        Rectangle bulletRect = this.getPositionComponent().getCoillderBox();
        List<FightObject> targets = gamePanel.getGridManager().getIntersectedOtherSideFightObjects(bulletRect, attacterSide);
        if (!targets.isEmpty()) {
            // only hit first
            targets = targets.subList(0, 1);
        }
        //logger.debug(instanceName + " hit " + targets.size() + " zombies.");
        return targets;
    }
    
//    @Override
//    protected boolean wantUseSkill() {
//        // TODO Auto-generated method stub
//        return false;
//    }
//    
//    @Override
//    protected void useSkill() {
//        // TODO Auto-generated method stub
//        
//    }
    
    @Override
    public HealthComponent getHealthComponent() {
        return this.healthComponent;
    }
    
    @Override
    protected boolean wantMove() {
        return true;
    }

}
