import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea extends AbstractPea {

    private int posX;
    protected GamePanel gp;
    private PlantMovingStrategy plantMovingStrategy;
    private int myLane;

  
    public Pea(GamePanel parent, int lane, int startX) {
        super(parent, lane, startX);
    }

}
