package game.component;

import java.awt.Rectangle;

import game.GamePanel;

public class SunPositionComponent extends PositionComponent {
    private int endY;
    private int destructCount = 100;
    int width = 80;
    int height = 80;
    
    
    public SunPositionComponent(GamePanel gamePanel, int startX, int startY, int endY) {
        super(gamePanel, startX, startY);
        this.endY = endY;
    }
    
    

    @Override
    public void move() {
        if (posY < endY) {
            posY += 2;
        } else {
            destructCount--;
        }
    }

    
    public int getDestructCount() {
        return destructCount;
    }
    
    @Override
    public Rectangle getCoillderBox() {
        return new Rectangle(posX, posY - height, width, height);
    }

}
