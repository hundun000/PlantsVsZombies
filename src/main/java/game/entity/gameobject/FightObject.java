package game.entity.gameobject;

import java.awt.Graphics;

import game.GamePanel;
import game.entity.component.PositionComponent;

/**
 * @author hundun
 * Created on 2020/09/18
 */
public abstract class FightObject extends GameObject {

    public FightObject(GamePanel gamePanel, String registerName) {
        super(gamePanel, registerName);
    }
    protected abstract boolean wantAttack();
    protected abstract void attack();
    protected abstract boolean wantUseSkill();
    protected abstract void useSkill();
    
    public abstract GameObjectStatus getStatus();

    
    @Override
    public void updateLogicFrame() {
        super.updateLogicFrame();
        
        getStatus().updateLogicFrame();
    }
    
    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        
        if (GamePanel.DRAW_DEBUG_BOX) {
            if (!getHealthComponent().isUncountableHealth()) {
                g.setFont(healthBarFont);
                int hpBarX = getPositionComponent().getPosX();
                int hpBarY = getPositionComponent().getPosY() - healthBarFont.getSize();
                g.drawString(String.valueOf(getHealthComponent().getHealth()), hpBarX, hpBarY);
            }
        }
    }
}
