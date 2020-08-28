package game.component;

import game.GamePanel;

public class SunPositionComponent extends PositionComponent {
    private int endY;
    private int destruct = 100;

    
    
    public SunPositionComponent(GamePanel gp, int startX, int startY, int endY) {
        super(startX, startY, gp);
        this.endY = endY;
    }

    @Override
    public void move() {
        if (posY < endY) {
            posY += 4;
        } else {
            destruct--;
        }
    }

    
    public int getDestruct() {
    	return destruct;
    }

}