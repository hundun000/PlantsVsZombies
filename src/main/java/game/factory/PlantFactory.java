package game.factory;

import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;

/**
 * @author hundun
 * Created on 2020/09/10
 */
public class PlantFactory extends GameObjectModelFactory<PlantModel, BasePlant, PlantInstanceParams> {
    public PlantFactory() {
        super(PlantModel.class, PlantInstanceParams.class);
    }
}
