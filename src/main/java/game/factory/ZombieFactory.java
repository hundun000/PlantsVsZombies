package game.factory;

import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.entity.zombie.BaseZombie;
import game.entity.zombie.ZombieInstanceParams;
import game.entity.zombie.ZombieModel;

/**
 * @author hundun
 * Created on 2020/09/10
 */
public class ZombieFactory extends GameObjectModelFactory<ZombieModel, BaseZombie, ZombieInstanceParams>{
    public ZombieFactory() {
        super(ZombieModel.class, ZombieInstanceParams.class);
    }
}
