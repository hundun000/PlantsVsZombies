package game.entity.gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.template.DebuffBullect.DebuffType;
import game.entity.component.FightComponent;
import game.entity.component.PositionComponent;
import game.entity.component.PositionComponent.HeightZ;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.BasePlant;
import game.entity.zombie.BaseZombie;

/**
 * @author hundun
 * Created on 2020/09/18
 */
public abstract class FightObject extends GameObject {
    
    private static Logger logger = LoggerFactory.getLogger(FightObject.class);
    
    /**
     * 阵营：玩家/敌人
     */
    public enum FightSide {
        PLAYER,
        ZOMBIE
    }
    
    
    
    protected FightSide fightSide;
    protected FightData fightData;
    int frozenStacks = 0;
    int fireStacks = 0;
    private List<FightObject> currentBlockedObjects = new ArrayList<>();
    private HeightZ maxBlockableHeightZ = HeightZ.GROUND;
    
    
    public FightObject(GamePanel gamePanel, String registerName, FightData fightData, FightSide fightSide) {
        super(gamePanel, registerName);
        this.fightData = fightData;
        this.fightSide = fightSide;
    }
    protected abstract boolean wantAttack();
    protected abstract void attack();
    protected abstract boolean wantUseSkill();
    protected abstract void useSkill();
    
    protected abstract FightComponent getFightComponent();

    
    
    protected boolean isBlockedByOtherSide() {
        boolean blockedByOtherSide = false;
        List<FightObject> intersectedObjects = gamePanel.getGridManager().getIntersectedOtherSideFightObjects(getPositionComponent().getCoillderBox(), fightSide);
        for (FightObject intersectedObject : intersectedObjects) {
            if (intersectedObject.getCurrentBlockedObjects().contains(this)
                    && intersectedObject.maxBlockableHeightZ.higherThan(this.getPositionComponent().getPosZ())
                    ) {
                blockedByOtherSide = true;
                break;
            }
        }
        return blockedByOtherSide;
    }
    
    public List<FightObject> getCurrentBlockedObjects() {
        return currentBlockedObjects;
    }
    
    private void updatBlockedObjects() {
        List<FightObject> intersectedObjects = gamePanel.getGridManager().getIntersectedOtherSideFightObjects(getPositionComponent().getCoillderBox(), fightSide);
        List<FightObject> lastBlockedObjects = new ArrayList<>(currentBlockedObjects);
        currentBlockedObjects.clear();
        // 先加入last与new的交集
        lastBlockedObjects.retainAll(intersectedObjects);
        for (FightObject lastBlockedObject : lastBlockedObjects) {
            if (currentBlockedObjects.size() > fightData.maxBlockNum) {
                break;
            }
            currentBlockedObjects.add(lastBlockedObject);
        }
        // 再先加入剩余的new
        intersectedObjects.removeAll(lastBlockedObjects);
        for (FightObject intersectedObject : intersectedObjects) {
            if (currentBlockedObjects.size() > fightData.maxBlockNum) {
                break;
            }
            currentBlockedObjects.add(intersectedObject);
        }
    }
    
    @Override
    public void updateLogicFrame() {
        if (frozenStacks > 0) {
            frozenStacks--;
        }
        if (frozenStacks % 2 != 0) {
            return;
        }
        
        super.updateLogicFrame();
        
        updatBlockedObjects();

        boolean attackHappen = getFightComponent().getAttackStatus().transition(this.wantAttack());
        if (attackHappen) {
            this.attack();
        }
        
        boolean skillHappen = getFightComponent().getSkillStatus().transition(this.wantUseSkill());
        if (skillHappen) {
            this.useSkill();
        }
    }
    
    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        
        if (GamePanel.DRAW_DEBUG_BOX) {
            if (!getHealthComponent().isUncountableHealth()) {
                g.setColor(Color.GREEN);
                g.setFont(healthBarFont);
                int hpBarX = getPositionComponent().getPosX();
                int hpBarY = getPositionComponent().getPosY() - healthBarFont.getSize();
                g.drawString(String.valueOf(getHealthComponent().getHealth()), hpBarX, hpBarY);
            }
            
            g.setFont(healthBarFont);
            g.setColor(Color.RED);
            double attackProgress = getFightComponent().getAttackStatus().getProgressRate();
            int attackProgressX = getPositionComponent().getPosX();
            int attackProgressY = getPositionComponent().getPosY() - 2*healthBarFont.getSize();
            g.drawString(String.valueOf(attackProgress), attackProgressX, attackProgressY);
            
            g.setColor(Color.BLUE);
            double skillProgress = getFightComponent().getSkillStatus().getProgressRate();
            int skillProgressX = getPositionComponent().getPosX();
            int skillProgressY = getPositionComponent().getPosY() - 3*healthBarFont.getSize();
            g.drawString(String.valueOf(skillProgress), skillProgressX, skillProgressY);
            
            

            Rectangle box = getAttackRangeBox();
            if (box != null) {
                g.setColor(Color.RED);
                g.drawRect((int)box.getX(), (int)box.getY(), (int)box.getWidth(), (int)box.getHeight());
            }
            
            

        }
    }
    
    /**
     * PVZ 中的标准“攻击”：发射子弹/爆炸
     */
    protected void attackByGenerateBullet() {
        BaseBullet bullet = generateBullet();
        if (bullet != null) {
            gamePanel.getGridManager().addBullet(bullet);
        }
    }

    
    protected BaseBullet generateBullet() {
        if (fightData.bulletRegisterName != null) {
            int bulletStartX = getPositionComponent().getPosX() + fightData.bulletStartOffsetX;
            int bulletStartY = getPositionComponent().getPosY() + fightData.bulletStartOffsetY;
            
            BulletInstanceParams params = new BulletInstanceParams(bulletStartX, bulletStartY, fightSide);
            BaseBullet bullet = gamePanel.getBulletFactory().getInstacne(
                    fightData.bulletRegisterName, gamePanel, params);
            bullet.setSubTypeName(fightData.bulletSubTypeName);
            logger.info("generateBullet by bulletSubTypeId = {}", fightData.bulletSubTypeName);
            return bullet;
        } else {
            return null;
        }
        
    }
    
    protected boolean wantAttackByAttackRangeBox() {
        Rectangle attackRect = this.getAttackRangeBox();
        if (attackRect != null) {
            List<FightObject> intersected = gamePanel.getGridManager().getIntersectedOtherSideFightObjects(attackRect, fightSide);

            return !intersected.isEmpty();
        } else {
            return false;
        }
    }
    
    public Rectangle getAttackRangeBox() {
        if (fightData.attackRangeWidth != null) {
            int leftTopX = getPositionComponent().getPosX() + fightData.attackRangeOffsetX;
            int leftTopY = getPositionComponent().getPosY() + fightData.attackRangeOffsetY;
            return new Rectangle(leftTopX, leftTopY, fightData.attackRangeWidth, fightData.attackRangeHeight);
        } else {
            return null;
        }
    }
    
    
    
    public void onDebuff(DebuffType debuffType, int addStacks) {
        int lostStacks;
        switch (debuffType) {
            case FREEZE:
                lostStacks = Math.min(addStacks, this.fireStacks);
                this.fireStacks -= lostStacks;
                this.frozenStacks += (addStacks - lostStacks);
                break;
            case FIRE:
                lostStacks = Math.min(addStacks, this.frozenStacks);
                this.fireStacks -= lostStacks;
                this.frozenStacks += (addStacks - lostStacks);
                this.fireStacks += addStacks;
                break;
            default:
                break;
        }
        
    }
    
    @Override
    protected String getSpiritSubId() {
        return super.getSpiritSubId();
    }
    
    public int getFireStacks() {
        return fireStacks;
    }
    
    public int getFrozenStacks() {
        return frozenStacks;
    }
    
}
