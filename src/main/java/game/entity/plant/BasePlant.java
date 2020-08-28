package game.entity.plant;

import java.awt.Rectangle;

import game.GamePanel;
import game.component.PositionComponent;
import game.entity.GameObject;
import game.manager.GridManager;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class BasePlant extends GameObject {
    
    protected int health = 200;
    protected PositionComponent positionComponent;

    private int workColdDownReset;
    private int workColdDown = 0;
    
    public BasePlant(GamePanel parent, int gridX, int gridY, String registerName, int workColdDownReset) {
        super(parent, registerName);
        this.positionComponent = new PositionComponent(gridX * GridManager.GRID_WIDTH, gridY * GridManager.GRID_HEIGHT, parent);
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
    
    
    
    protected abstract boolean wantWork();
    protected abstract void work();
    public abstract Rectangle getAttackRangeBox();
}
