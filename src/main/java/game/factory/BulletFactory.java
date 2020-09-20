package game.factory;

import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;

/**
 * @author hundun
 * Created on 2020/09/10
 */
public class BulletFactory extends GameObjectModelFactory<BulletModel, BaseBullet, BulletInstanceParams> {
    public static final String DEFAULT_BULLET_REGISTER_NAME = "default_bullet";
    
    public BulletFactory() {
        super(BulletModel.class, BulletInstanceParams.class);
    }
    
}
