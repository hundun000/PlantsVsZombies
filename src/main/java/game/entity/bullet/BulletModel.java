package game.entity.bullet;

import game.entity.gameobject.FightData;
import game.entity.gameobject.GameObjectModel;
import game.entity.gameobject.Spirit;
import game.entity.zombie.BaseZombie;
import game.manager.GridManager;

/**
 * @author hundun
 * Created on 2020/09/10
 */
public class BulletModel extends GameObjectModel<BaseBullet> {

    public BulletModel(String registerName, Class<? extends BaseBullet> clazz) {
        super(registerName, clazz);

    }
    
    public int coillderBoxWidth;
    public int coillderBoxHeight;
    public int coillderBoxOffsetX= 0;
    public int coillderBoxOffsetY;
    
    
    
    public Integer endDeltaX;
    public Integer endDeltaY;
    
    public Integer speedX;
    public Integer speedY;
    
    
    
    public Integer damage = 100;
    public FightData fightData;
    
    
    
    
}
