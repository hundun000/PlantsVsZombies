package game.entity.bullet;
import java.awt.Rectangle;
import java.util.List;

import game.GamePanel;
import game.ILogicFrameListener;
import game.OnLevelUpListener;
import game.component.BulletPositionComponent;
import game.component.PositionComponent;
import game.entity.GameObject;
import game.entity.zombie.BaseZombie;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea extends BaseBullet {
    protected GamePanel gamePanel;
    
    private int damage;

    private boolean freeze;
    
    public Pea(GamePanel parent, int x, int y) {
        this(parent, x, y, false);
    }
    
    public Pea(GamePanel parent, int x, int y, boolean freeze) {
        super(parent, "pea", x, y);
        this.gamePanel = parent;
        this.damage = 300;
        this.freeze = freeze;
    }


    @Override
    public void updateLogicFrame() {
        super.updateLogicFrame();
        
        
    }
    
    

    public int getDamage() {
        return damage;
    }

}
