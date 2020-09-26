package game.entity.zombie;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.entity.component.FightComponent;
import game.entity.component.HealthComponent;
import game.entity.component.PositionComponent;
import game.entity.component.ZombiePositionComponent;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.GameObject;
import game.entity.gameobject.FightObject.FightSide;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.others.PlantSlot;
import game.entity.zombie.template.NormalZombie;
import game.manager.GridManager;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class BaseZombie extends FightObject {
    static Logger logger = LoggerFactory.getLogger(BaseZombie.class);
  
    protected ZombiePositionComponent zombiePositionComponent;
    protected HealthComponent healthComponent;
    protected ZombieModel model;
    protected FightComponent status;
    protected int damagePerFrame;
    public BaseZombie(GamePanel parent, ZombieModel model, ZombieInstanceParams params) {
        super(parent, model.registerName, model.fightData, FightSide.ZOMBIE);
        super.spirit = model.spirit;
        super.coillderBoxColor = Color.DARK_GRAY;
        this.model = model;
        this.zombiePositionComponent = new ZombiePositionComponent(parent, model, params, this);
        this.status = new FightComponent(this, model, params);
        this.damagePerFrame = GamePanel.perSecondToPerFrame(model.damagePerSecond);
        this.healthComponent = new HealthComponent(this, model, params);
    }


    
    @Override
    public PositionComponent getPositionComponent() {
        return zombiePositionComponent;
    }

    
    @Override
    public FightComponent getFightComponent() {
        return status;
    }
    
    @Override
    protected boolean wantAttack() {
        return wantAttackByAttackRangeBox();
    }

    @Override
    protected void attack() {
        attackByGenerateBullet();
    }
    
    @Override
    protected boolean wantUseSkill() {
        return false;
    }
    
    @Override
    protected void useSkill() {
    }
    
    @Override
    public HealthComponent getHealthComponent() {
        return healthComponent;
    }
    
    @Override
    protected boolean wantMove() {
        boolean wantMoveByState = this.getFightComponent().getAttackStatus().getWorkState() != WorkState.WORKING 
                && this.getFightComponent().getSkillStatus().getWorkState() != WorkState.WORKING;
        boolean wantMoveByNotBlocked = !isBlockedByOtherSide();
        
        return wantMoveByState && wantMoveByNotBlocked;
    }

}
