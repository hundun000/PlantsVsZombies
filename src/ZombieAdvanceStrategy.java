public class ZombieAdvanceStrategy implements ZombieMovingStrategy{
    private int posX = 1000;
    private int myLane;
    private boolean isMoving = true;
    private Facade facade;
    int slowInt = 0;
    private int speed = 1;

    public ZombieAdvanceStrategy(GamePanel gp, int myLane) {
        facade = new Facade(gp);
        this.myLane = myLane;
        if(gp.getCurrentLevel() == 2) {
            this.speed += 1;
        }
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

    public void faster() {
        speed += 1;
    }

    public void move() {
        if (isMoving) {

            boolean isCollides = false;
            Collider collided = facade.peaCoillder(myLane, posX);

            if(collided!= null) {
                isCollides = true;
            }
            if (!isCollides) {
                if (slowInt > 0) {
                    if (slowInt % 2 == 0) {
                        posX -= speed;
                    }
                    slowInt--;
                } else {
                    posX -= speed;
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
                facade.gameOver();
            }
        }
    }

}