interface ZombieMovingStrategy {
    public void move();
    public int getPosX();
    public void setPosX(int posX);
    public boolean isMoving();
    public void setMoving(boolean isMoving);
    public void slow();
    public int getSlowInt();
    public void setSlowInt(int slowInt);
}