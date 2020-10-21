package game.entity.zombie.template;

import game.entity.component.ZombiePositionComponent.MoveType;
import game.entity.zombie.BaseZombie;
import game.entity.zombie.ZombieInstanceParams;
import game.entity.zombie.ZombieModel;
import game.ui.GamePanel;

/**
 * @author hundun
 * Created on 2020/09/30
 */
public class JumperZombie extends BaseZombie {

    public JumperZombie(GamePanel parent, ZombieModel model, ZombieInstanceParams params) {
        super(parent, model, params);
        zombiePositionComponent.setMoveTypeAndUpdatePosZ(MoveType.JUMP_PREPARE);
    }
}
