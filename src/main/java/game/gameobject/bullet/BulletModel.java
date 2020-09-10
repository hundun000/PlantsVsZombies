package game.gameobject.bullet;

import game.gameobject.GameObjectModel;
import game.gameobject.Spirit;
import game.gameobject.zombie.BaseZombie;
import game.manager.GridManager;

/**
 * @author hundun
 * Created on 2020/09/10
 */
public class BulletModel extends GameObjectModel<BaseBullet> {

    public BulletModel(String registerName, Class<? extends BaseBullet> clazz) {
        super(registerName, clazz);

    }
    
    public int coillderBoxWidth = 20;
    public int coillderBoxHeight = 20;
    public int coillderBoxOffsetX = 0;
    public int coillderBoxOffsetY = - coillderBoxHeight;
    
    
    public int endDeltaX = 10 * GridManager.GRID_WIDTH;
    public int endDeltaY = 0;
    
    public int speedX = 15;
    public int speedY = 0;
    public int damage = 300;
}
