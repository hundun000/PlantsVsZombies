package game.mod.pvz.plant;
import game.GamePanel;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.mod.pvz.bullet.Pea;
import game.mod.pvz.bullet.Pea.PeaSubType;

/**
 * Created by Armin on 6/25/2016.
 */
public class FrozenPeashooter extends Peashooter {
    public static final String NAME = "frozen_peashooter";
    public FrozenPeashooter(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
        //setRegisterName(REGISTER_NAME);
        peaType = PeaSubType.FREEZE;
    }

}