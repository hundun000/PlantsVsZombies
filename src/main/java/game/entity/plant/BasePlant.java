package game.entity.plant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.GamePanel;
import game.component.PlantPositionComponent;
import game.component.PositionComponent;
import game.entity.GameObject;
import game.manager.GridManager;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class BasePlant extends GameObject {
    
    protected int health = 200;
    protected PlantPositionComponent positionComponent;

    private int workColdDownReset;
    private int workColdDown = 0;
    
    public BasePlant(GamePanel parent, int gridX, int gridY, String registerName, int workColdDownReset) {
        super(parent, registerName);
        this.positionComponent = new PlantPositionComponent(parent, gridX * GridManager.GRID_WIDTH, gridY * GridManager.GRID_HEIGHT);
        this.workColdDownReset = workColdDownReset;
    }
    


    public void stop() {
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    @Override
    public PositionComponent getPositionComponent() {
        return positionComponent;
    }
    
    @Override
    public void updateLogicFrame() {
        workColdDown --;
        if(workColdDown <= 0) {
            if (wantWork()) {
                work();
                workColdDown = workColdDownReset;
            } else {
                workColdDown = 1;
            }
            
        }
    }
    
    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        if (GamePanel.DRAW_DEBUG_BOX) {
            Rectangle box = getAttackRangeBox();
            if (box != null) {
                Color last = g.getColor();
                g.setColor(Color.RED);
                g.drawRect((int)box.getX(), (int)box.getY(), (int)box.getWidth(), (int)box.getHeight());
                g.setColor(last);
            }
        }
    }
    
    
    
    protected abstract boolean wantWork();
    protected abstract void work();
    public abstract Rectangle getAttackRangeBox();



    public abstract int getPlantCost();
}
