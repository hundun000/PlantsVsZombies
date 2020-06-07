import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends Plant {

    public Timer shootTimer;
    private Pea pea;

    public Peashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            if (getGp().getLaneZombies().get(y).size() > 0) {
                pea = new Pea(getGp(), y, 103 + this.getX() * 100);
                pea.setMovingStrategy(new PeaAdvanceStrategy(getGp(),pea,y,103 + this.getX() * 100));
                getGp().getLanePeas().get(y).add(pea);
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}
