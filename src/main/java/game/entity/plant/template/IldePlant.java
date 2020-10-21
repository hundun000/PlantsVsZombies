package game.entity.plant.template;

import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.ui.GamePanel;

/**
 * @author hundun
 * Created on 2020/09/17
 */
public class IldePlant extends BasePlant {
    public static final String NAME = "wallnut";
    public IldePlant(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
    }
    @Override
    protected boolean wantAttack() {
        return false;
    }
    @Override
    protected void attack() {
    }
    @Override
    protected boolean wantUseSkill() {
        return false;
    }
    @Override
    protected void useSkill() {
    }

    
}
