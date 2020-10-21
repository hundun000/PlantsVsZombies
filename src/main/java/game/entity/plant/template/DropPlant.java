package game.entity.plant.template;
import javax.swing.*;

import game.entity.drop.BaseDrop;
import game.entity.drop.DropInstanceParams;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.manager.GridManager;
import game.mod.pvz.drop.SunItem;
import game.ui.GamePanel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * Created by Armin on 6/28/2016.
 */
public class DropPlant extends BasePlant {
    
    public DropPlant(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
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
        return true;
    }

    @Override
    protected void useSkill() {
        useSkillByGenerateDrop();
    }

}
