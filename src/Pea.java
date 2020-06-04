import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea extends AbstractPea {

    public Pea(GamePanel parent, int lane, int startX) {
        super(parent, lane, startX);
    }

    protected void setPosXX() {
    	setPosX(getPosX() + 15);
    }
}