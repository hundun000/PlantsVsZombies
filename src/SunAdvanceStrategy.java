public class SunAdvanceStrategy implements SunMovingStrategy{
    private GamePanel gp;
    private Sun sun;
    private int myX;
    private int myY;
    private int endY;
    private int destruct = 100;

    public SunAdvanceStrategy(GamePanel gp, Sun sun, int startX, int startY, int endY) {
        this.gp = gp;
        this.sun = sun;

        this.endY = endY;
        myX = startX;
        myY = startY;


        sun.setLocation(myX, myY);
    }

    public void move() {
        if (myY < endY) {
            myY += 4;
        } else {
            destruct--;
            if (destruct < 0) {
                sun.deleteSun();
            }
        }
        sun.setLocation(myX, myY);
    }
    
    public int getMyX() {
    	return myX;
    }
    
    public int getMyY() {
    	return myY;
    }
    
    public int getDestruct() {
    	return destruct;
    }

}