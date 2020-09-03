package game.component;
import java.awt.Rectangle;

import game.GamePanel;

public class BulletPositionComponent extends PositionComponent{
    
    public BulletPositionComponent (GamePanel gamePanel, int x,int y) {
        super(gamePanel, x, y);
    }

    @Override
    public void move() {
        
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
    
    @Override
    public Rectangle getCoillderBox() {
        return new Rectangle(this.getPosX(), this.getPosY(), 28, 28);
    }
}