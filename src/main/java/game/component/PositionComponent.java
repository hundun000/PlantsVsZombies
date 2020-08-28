package game.component;

import game.GamePanel;

public class PositionComponent {
    int posX;
    int posY;
    GamePanel gamePanel;
    public PositionComponent(int x, int y, GamePanel gamePanel) {
        setPosX(x);
        setPosY(y);
        this.gamePanel = gamePanel;
    }


    public void move() {
    
    }
    
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    
    
    public int getPosY() {
        return posY;
    }
    
    public void setPosY(int posY) {
        this.posY = posY;
    }
    
}