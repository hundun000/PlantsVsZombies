package game.facroty;

import game.gameobject.bullet.BulletInstanceParams;
import game.gameobject.bullet.BulletModel;
import game.gameobject.plant.BasePlant;
import game.gameobject.plant.PlantInstanceParams;
import game.gameobject.plant.PlantModel;

/**
 * @author hundun
 * Created on 2020/09/10
 */
public class PlantFactory extends GameObjectModelFactory<PlantModel, BasePlant, PlantInstanceParams> {
    public PlantFactory() {
        super(PlantModel.class, PlantInstanceParams.class);
    }
}
