package game.entity.zombie;

import game.GamePanel;
import game.component.ZombiePositionComponent;

/**
 * Created by Armin on 6/29/2016.
 */
public class ConeHeadZombie extends BaseZombie {

    public ConeHeadZombie(GamePanel parent, int lane, ZombiePositionComponent zombiePositionComponent) {
        super(parent, lane, "conehead_zombie", zombiePositionComponent);
    }
}
