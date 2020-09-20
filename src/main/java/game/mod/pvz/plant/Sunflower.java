package game.mod.pvz.plant;
import javax.swing.*;

import game.GamePanel;
import game.entity.drop.BaseDrop;
import game.entity.drop.DropInstanceParams;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.manager.GridManager;
import game.mod.pvz.drop.SunItem;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends BasePlant {
    
    public static final String NAME = "sunflower";
    
    public Sunflower(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
    }

    @Override
    protected boolean wantAttack() {
        return true;
    }

}
