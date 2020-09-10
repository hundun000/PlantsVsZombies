package game.pvz.zombie;

import game.GamePanel;
import game.component.ZombiePositionComponent;
import game.gameobject.zombie.BaseZombie;
import game.gameobject.zombie.ZombieInstanceParams;
import game.gameobject.zombie.ZombieModel;

/**
 * Created by Armin on 6/29/2016.
 */
public class ConeHeadZombie extends BaseZombie {

    public static final String NAME = "conehead_zombie";

    public ConeHeadZombie(GamePanel parent, ZombieModel model, ZombieInstanceParams params) {
        super(parent, model, params);
    }
}
