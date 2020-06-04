import java.awt.*;

/**
 * Created by Armin on 6/28/2016.
 */
public class FreezePea extends AbstractPea {

    public FreezePea(GamePanel parent, int lane, int startX) {
        super(parent, lane, startX);
    }

    protected void setPosXX() {
    	setPosX(getPosX() - 15);
    }
}