package game.facroty;

import game.gameobject.drop.BaseDrop;
import game.gameobject.drop.DropInstanceParams;
import game.gameobject.drop.DropModel;

/**
 * @author hundun
 * Created on 2020/09/12
 */
public class DropFactory extends GameObjectModelFactory<DropModel, BaseDrop, DropInstanceParams> {
    public static final String DEFAULT_DROP_REGISTER_NAME = "default_drop";
    
    public DropFactory() {
        super(DropModel.class, DropInstanceParams.class);
        
    }

}
