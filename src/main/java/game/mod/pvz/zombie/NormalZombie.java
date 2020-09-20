package game.mod.pvz.zombie;

import game.GamePanel;
import game.entity.component.ZombiePositionComponent;
import game.entity.zombie.BaseZombie;
import game.entity.zombie.ZombieInstanceParams;
import game.entity.zombie.ZombieModel;


/**
 * Created by Armin on 6/25/2016.
 */
public class NormalZombie extends BaseZombie {

    public static final String NAME = "normal_zombie";

    public NormalZombie(GamePanel parent, ZombieModel model, ZombieInstanceParams params) {
        super(parent, model, params);
    }

}
