package game.pvz.plant;
import game.GamePanel;
import game.gameobject.plant.PlantInstanceParams;
import game.gameobject.plant.PlantModel;
import game.pvz.bullet.Pea;
import game.pvz.bullet.Pea.PeaType;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Peashooter {
    public static final String NAME = "freeze_peashooter";
    public FreezePeashooter(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
        //setRegisterName(REGISTER_NAME);
        peaType = PeaType.FREEZE;
    }

}