
public class AdvanceStrategy implements ZombieMovingStrategy{
    private GamePanel gp;

    private int posX = 1000;
    private int myLane;
    private boolean isMoving = true;
    int slowInt = 0;

    public AdvanceStrategy(GamePanel gp, int myLane) {
        this.gp = gp;
        this.myLane = myLane;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    public boolean isMoving() {
        return isMoving;
    }
    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }
    public void slow() {
        this.slowInt = 1000;
    }
    public int getSlowInt() {
        return slowInt;
    }

    public void setSlowInt(int slowInt) {
        this.slowInt = slowInt;
    }

    public void move() {
        if (isMoving) {

            boolean isCollides = false;
            Collider collided = null;

            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {

                /* edited */
                final boolean intersectPlant = gp.getColliders()[i].assignedPlant != null && gp.getColliders()[i].isInsideCollider(posX);

                if (intersectPlant) {
                    isCollides = true;
                    collided = gp.getColliders()[i];
                }
            }
            if (!isCollides) {
                if (slowInt > 0) {
                    if (slowInt % 2 == 0) {
                        posX--;
                    }
                    slowInt--;
                } else {
                    posX -= 1;
                }
            } else {
                collided.assignedPlant.setHealth(collided.assignedPlant.getHealth() - 10);

                /* edited */
                final boolean planthasHealth = collided.assignedPlant.getHealth() >= 0;

                if (!planthasHealth) {
                    collided.removePlant();
                }
            }
            if (posX < 0) {
                isMoving = false;
                gp.getMessageDialog().gameOverDialog();
                GameWindow.begin();
            }
        }
    }

}