package game.facroty;

import game.gameobject.bullet.BaseBullet;
import game.gameobject.bullet.BulletInstanceParams;
import game.gameobject.bullet.BulletModel;

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
