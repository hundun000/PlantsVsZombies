package game.component;
import game.GamePanel;

public class BulletPositionComponent extends PositionComponent{
    
    public BulletPositionComponent (GamePanel gamePanel, int x,int y) {
        super(x, y, gamePanel);
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
}