package game.mod.arknights.bullet;

import game.GamePanel;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;

/**
 * @author hundun
 * Created on 2020/09/22
 */
public class CloseHit extends BaseBullet {
    public static String NAME = "sword_energy";
    

    public CloseHit(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, model, params);
    }

}
