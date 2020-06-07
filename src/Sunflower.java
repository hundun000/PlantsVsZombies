import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends Plant implements OnLevelUpListener {

    private Timer sunProduceTimer;
    private int delay = 15000;
    int x;
    int y;

    public Sunflower(GamePanel parent, int x, int y) {
        super(parent, x, y);
        this.x = x;
        this.y = y;

        getGp().addLevelUpObservers(this);
        sunProduceTimer = new Timer(delay, (ActionEvent e) -> {
            Sun sta = new Sun(getGp());
            getGp().getActiveSuns().add(sta);
            sta.setMovingStrategy( new SunAdvanceStrategy(getGp(), sta, 60 + x * 100, 110 + y * 120, 130 + y * 120));
            getGp().add(sta, new Integer(1));
        });
        sunProduceTimer.start();
    }

    @Override
    public void onLevelUp() {
        delay = 3000;
        sunProduceTimer.stop();
        sunProduceTimer = new Timer(delay, (ActionEvent e) -> {
            Sun sta = new Sun(getGp());
            getGp().getActiveSuns().add(sta);
            sta.setMovingStrategy( new SunAdvanceStrategy(getGp(), sta, 60 + x * 100, 110 + y * 120, 130 + y * 120));
            getGp().add(sta, new Integer(1));
        });
        sunProduceTimer.start();
    }
}
