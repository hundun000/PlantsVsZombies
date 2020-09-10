package game.pvz.zombie;

import game.GamePanel;
import game.component.ZombiePositionComponent;
import game.gameobject.zombie.BaseZombie;
import game.gameobject.zombie.ZombieInstanceParams;
import game.gameobject.zombie.ZombieModel;


/**
 * Created by Armin on 6/25/2016.
 */
public class NormalZombie extends BaseZombie {

    public static final String NAME = "normal_zombie";

    public NormalZombie(GamePanel parent, ZombieModel model, ZombieInstanceParams params) {
        super(parent, model, params);
    }

}
