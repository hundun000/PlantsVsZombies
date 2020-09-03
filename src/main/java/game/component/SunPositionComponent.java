package game.component;

import java.awt.Rectangle;

import game.GamePanel;

public class SunPositionComponent extends PositionComponent {
    private int endY;
    private int destruct = 100;

    
    
    public SunPositionComponent(GamePanel gamePanel, int startX, int startY, int endY) {
        super(gamePanel, startX, startY);
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
    
    @Override
    public Rectangle getCoillderBox() {
        return new Rectangle(posX, posY, 1, 1);
    }

}
