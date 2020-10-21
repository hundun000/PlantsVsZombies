package game.mod.arknights.plant.template;

import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.ui.GamePanel;

/**
 * 明日方舟干员
 * @author hundun
 * Created on 2020/09/22
 */
public class Operator extends BasePlant {
    public static final String YATO_NAME = "yato";
    

    public Operator(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
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
        return true;
    }

    @Override
    protected void useSkill() {
        useSkillByGenerateDrop();
    }

}
