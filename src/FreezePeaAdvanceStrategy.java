public class FreezePeaAdvanceStrategy implements PlantMovingStrategy {
    private Facade facade;

    private int myLane;
    private int posX;

    public FreezePeaAdvanceStrategy (GamePanel gp, FreezePea freezePea, int lane,int startX) {
        this.facade = new Facade(gp);
        facade.setFreezePea(freezePea);
        this.myLane = lane;
        posX = startX;
    }

    public void move() {
        facade.zombieCoillderAtFreezePea(myLane, posX);
        posX += 15;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public void setPosX(int posX) {
        this.posX = posX;
    }
}