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
  
//     public Pea(GamePanel parent, int lane, int startX) {
//         this.gp = parent;
//         this.myLane = lane;
//         posX = startX;
//     }

    public void advance() {
        plantMovingStrategy.move();
    }

    /* edited */
    public boolean attackZombie(Zombie z, Rectangle pRect,Rectangle zRect,int i) {

        final boolean zombiehasHealth = z.getHealth() >= 0;
        boolean exit = false;

        if (pRect.intersects(zRect)) {
            z.setHealth(z.getHealth() - 300);

            if (!zombiehasHealth) {
                System.out.println("ZOMBIE DIED");

                gp.getLaneZombies().get(myLane).remove(i);
                GamePanel.setLevel(10);
                exit = true;
            }
            gp.getLaneZombies().get(myLane).remove(this);

        }
        return exit;

    }

    public int getPosX() {
        return plantMovingStrategy.getPosX();
    }

    public void setPosX(int posX) {
        plantMovingStrategy.setPosX(posX);
    }

    public int getMyLane() {
        return myLane;




    protected void setPosXX() {
    	setPosX(getPosX() + 15);
    }

    public void setMovingStrategy(PlantMovingStrategy movingStrategy) {
        this.plantMovingStrategy = movingStrategy;
    }
}
