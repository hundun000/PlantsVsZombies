package game.component;

import java.awt.Rectangle;

import game.GamePanel;
import game.manager.GridManager;

public abstract class PositionComponent {
    int posX;
    int posY;
    GamePanel gamePanel;
    public PositionComponent(GamePanel gamePanel, int x, int y) {
        setPosX(x);
        setPosY(y);
        this.gamePanel = gamePanel;
    }


    public abstract void move();
    
    
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
    
    @Override
    public String toString() {
        return "(" + posX + ", " + posY + ")";
    }
    
    public abstract Rectangle getCoillderBox();
    
}