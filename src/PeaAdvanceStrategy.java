import java.awt.Rectangle;

public class PeaAdvanceStrategy implements PlantMovingStrategy{
    private Facade facade;

    private int myLane;
    private int posX;

    public PeaAdvanceStrategy (GamePanel gp, Pea pea, int lane,int startX) {
        this.facade = new Facade(gp);
        facade.setPea(pea);
        this.myLane = lane;
        posX = startX;
    }


    public void move() {
        facade.zombieCoillderAtPea(myLane, posX);
        posX += 15;
    }
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
}