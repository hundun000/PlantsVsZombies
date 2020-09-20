package game.entity.drop;

import game.entity.gameobject.GameObjectModel;
import game.entity.plant.BasePlant;

/**
 * @author hundun
 * Created on 2020/09/12
 */
public class DropModel extends GameObjectModel<BaseDrop> {

    public DropModel(String registerName, Class<? extends BaseDrop> clazz) {
        super(registerName, clazz);
    }
    
    public int chargePoint = 25;
    public int disappearFrame = 100;
}
