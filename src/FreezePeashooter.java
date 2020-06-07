import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Plant {

    private Timer shootTimer;
    private FreezePea freezePea;

    public FreezePeashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            if (getGp().getLaneZombies().get(y).size() > 0) {
                freezePea = new FreezePea(getGp(), y, 103 + this.getX() * 100);
                freezePea.setMovingStrategy(new FreezePeaAdvanceStrategy(getGp(),freezePea,y,103 + this.getX() * 100));
                getGp().getLanePeas().get(y).add(freezePea);
                getGp().getLanePeas().get(y).add(new FreezePea(getGp(), y, 103 + this.getX() * 100));
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}