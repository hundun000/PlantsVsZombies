package game.entity.zombie;

import game.GamePanel;
import game.component.ZombiePositionComponent;


/**
 * Created by Armin on 6/25/2016.
 */
public class NormalZombie extends BaseZombie {

    public NormalZombie(GamePanel parent, int lane, ZombiePositionComponent zombiePositionComponent) {
        super(parent, lane, "zombie", zombiePositionComponent);
    }

}
