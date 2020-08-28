package game.entity.bullet;
import game.GamePanel;
import game.entity.zombie.BaseZombie;

/**
 * Created by Armin on 6/28/2016.
 */
public class FreezePea extends Pea {
   
    public FreezePea(GamePanel parent, int lane, int startX) {
        super(parent, lane, startX, true);
    }
    
    @Override
    public void addDebuff(BaseZombie zombie) {
        super.addDebuff(zombie);
        
        zombie.setSlowFrame(100);
    }

}
