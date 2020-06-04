import java.awt.*;

/**
 * Created by Armin on 6/28/2016.
 */
public class FreezePea extends Pea {
    private PlantMovingStrategy plantMovingStrategy;

    public FreezePea(GamePanel parent, int lane, int startX) {
        super(parent, lane, startX);
    }

    @Override
    public void advance() {
        plantMovingStrategy.move();
    }

    public void setMovingStrategy(PlantMovingStrategy movingStrategy) {
        this.plantMovingStrategy = movingStrategy;
    }

    public int getPosX() {
        return plantMovingStrategy.getPosX();
    }

    public void setPosX(int posX) {
        plantMovingStrategy.setPosX(posX);
    }




}