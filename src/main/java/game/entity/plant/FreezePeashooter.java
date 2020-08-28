package game.entity.plant;
import game.GamePanel;
import game.entity.bullet.FreezePea;
import game.entity.bullet.Pea;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Peashooter {

    public static String REGISTER_NAME = "freeze_peashooter";
    
    public FreezePeashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        setRegisterName(REGISTER_NAME);
    }


    @Override
    protected Pea generatePea() {
        return new FreezePea(getGamePanel(), getPositionComponent().getPosY(), 103 + getPositionComponent().getPosX() * 100);
    }

}