package game.facroty;

import game.gameobject.plant.PlantInstanceParams;
import game.gameobject.plant.PlantModel;
import game.gameobject.zombie.BaseZombie;
import game.gameobject.zombie.ZombieInstanceParams;
import game.gameobject.zombie.ZombieModel;

/**
 * @author hundun
 * Created on 2020/09/10
 */
public class ZombieFactory extends GameObjectModelFactory<ZombieModel, BaseZombie, ZombieInstanceParams>{
    public ZombieFactory() {
        super(ZombieModel.class, ZombieInstanceParams.class);
    }
}
