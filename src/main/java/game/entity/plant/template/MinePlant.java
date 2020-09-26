package game.entity.plant.template;
/**
 * @author hundun
 * Created on 2020/09/17
 */

import game.GamePanel;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;

public class MinePlant extends BasePlant {
    
    public MinePlant(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
    }
    
    @Override
    protected void attack() {
        attackByGenerateBullet();
        this.getHealthComponent().forceKilled();
    }

    @Override
    protected boolean wantAttack() {
        return wantAttackByAttackRangeBox();
    }

    @Override
    protected boolean wantUseSkill() {
        return false;
    }

    @Override
    protected void useSkill() {
    }
    
    @Override
    public void updateLogicFrame() {
        super.updateLogicFrame();
        
        if (getFightComponent().getAttackStatus().getWorkState() == WorkState.WORK_READY) {
            getHealthComponent().changeToUncountableHealth();
        }
    }

}
