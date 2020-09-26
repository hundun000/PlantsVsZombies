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
    
    public int coillderBoxWidth = 20;
    public int coillderBoxHeight = 20;
    public int coillderBoxOffsetX = 0;
    public int coillderBoxOffsetY = - coillderBoxHeight;
    
    
    public Integer endDeltaX;
    public Integer endDeltaY;
    
    public Integer speedX;
    public Integer speedY;
    
    public void buildeDefaultSpeedAndRange() {
        this.speedX = 15;
        this.speedY = 0;
        this.endDeltaX = 10 * GridManager.GRID_WIDTH;
        this.endDeltaY = 0;
    }
    
    public void buildeNoSpeedAndRange() {
        this.speedX = 0;
        this.speedY = 0;
        this.endDeltaX = 0;
        this.endDeltaY = 0;
    }
    
    public Integer damage = 100;
    public FightData fightData;
    
    
    
    
}
