package game.factory;

import game.entity.drop.BaseDrop;
import game.entity.drop.DropInstanceParams;
import game.entity.drop.DropModel;

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
