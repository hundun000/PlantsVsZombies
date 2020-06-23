/**
 * Created by Armin on 6/25/2016.
 */
public class Zombie implements OnLevelUpListener {

    private int health = 1000;
    private int speed = 1;

    private GamePanel gp;
    private ZombieMovingStrategy zombieMovingStrategy;

    private int posX = 1000;
    private int myLane;
    private boolean isMoving = true;
    private static Zombie z;
    public int level = 0;

    public Zombie(GamePanel parent, int lane) {
        this.gp = parent;
        myLane = lane;
        gp.addLevelUpObservers(this);
    }

    public void advance() {
        zombieMovingStrategy.move();
    }

    int slowInt = 0;

    public void slow() {
        zombieMovingStrategy.slow();
    }

    @Override
    public void onLevelUp() {
        try {
            zombieMovingStrategy.faster();
            level++;
        } catch (NullPointerException e) {

        }
    }

    public static Zombie getZombie(String type, GamePanel parent, int lane) {
        z = new Zombie(parent, lane);
        switch (type) {
            case "NormalZombie":
                z = new NormalZombie(parent, lane);
                z.setMovingStrategy(new ZombieAdvanceStrategy(parent, lane));
                break;
            case "ConeHeadZombie":
                z = new ConeHeadZombie(parent, lane);
                z.setMovingStrategy(new ZombieAdvanceStrategy(parent, lane));
                break;
        }
        return z;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public int getPosX() {
        return zombieMovingStrategy.getPosX();
    }

    public void setPosX(int posX) {
        zombieMovingStrategy.setPosX(posX);
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }

    public boolean isMoving() {
        return zombieMovingStrategy.isMoving();
    }

    public void setMoving(boolean moving) {
        zombieMovingStrategy.setMoving(moving);
    }

    public int getSlowInt() {
        return zombieMovingStrategy.getSlowInt();
    }

    public void setSlowInt(int slowInt) {
        zombieMovingStrategy.setSlowInt(slowInt);
    }

    public void setMovingStrategy(ZombieMovingStrategy movingStrategy) {
        this.zombieMovingStrategy = movingStrategy;
    }
}
