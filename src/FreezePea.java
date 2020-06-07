import java.awt.*;

/**
 * Created by Armin on 6/28/2016.
 */
public class FreezePea extends AbstractPea {
    private PlantMovingStrategy plantMovingStrategy;

    public FreezePea(GamePanel parent, int lane, int startX) {
        super(parent, lane, startX);
    }
}
