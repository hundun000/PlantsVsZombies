package game.entity.component;

import java.awt.Rectangle;

import game.GamePanel;
import game.ILogicFrameListener;
import game.manager.GridManager;

public abstract class PositionComponent implements ILogicFrameListener {
    protected int posX;
    protected int posY;
    protected HeightZ posZ;
    
    protected GamePanel gamePanel;
    protected boolean moveDone;
    
    public PositionComponent(GamePanel gamePanel, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.gamePanel = gamePanel;
        this.moveDone = false;
        this.posZ = HeightZ.GROUND;
    }

    
    /**
     * Z轴高度
     */
    public enum HeightZ {
        GROUND,
        JUMPING,
        FLY;
        
        
        public boolean higherThan(HeightZ other) {
            return this.ordinal() < other.ordinal();
        }
    }

    @Override
    public void updateLogicFrame() {
        if (!moveDone) {
            move();
        }
    }

    public abstract void move();
    
    
    public int getPosX() {
        return posX;
    }
    
    
    public int getPosY() {
        return posY;
    }
    
    public HeightZ getPosZ() {
        return posZ;
    }
    
    public boolean isMoveDone() {
        return moveDone;
    }
    
    @Override
    public String toString() {
        return "(" + posX + ", " + posY + ", " + posZ.ordinal() + ")";
    }
    
    public abstract Rectangle getCoillderBox();

}