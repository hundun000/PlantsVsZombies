package game.component;

import java.awt.Rectangle;

import game.GamePanel;

public class DropPositionComponent extends PositionComponent {
    private int endY;
    
    int width = 80;
    int height = 80;
    
    
    public DropPositionComponent(GamePanel gamePanel, int startX, int startY, int endY) {
        super(gamePanel, startX, startY);
        this.endY = endY;
    }
    
    

    @Override
    public void move() {
        if (posY < endY) {
            posY += 2;
        }
    }
    
    @Override
    public Rectangle getCoillderBox() {
        return new Rectangle(posX, posY - height, width, height);
    }

}
