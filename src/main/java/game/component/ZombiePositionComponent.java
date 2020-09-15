package game.component;

import java.awt.Rectangle;

import game.GamePanel;
import game.gameobject.plant.PlantSlot;
import game.gameobject.zombie.ZombieInstanceParams;
import game.gameobject.zombie.ZombieModel;
import game.manager.GridManager;
import game.manager.ZombieManager;

public class ZombiePositionComponent extends PositionComponent{
    private static int START_POS_X = GridManager.GRID_WIDTH * (GridManager.NUM_COLUMN_CONSTANT + 1);
    int freezeFrame = 0;
    private int fullSpeed;
    private ZombieModel model;

    public ZombiePositionComponent(GamePanel gamePanel, ZombieModel model, ZombieInstanceParams params) {
        super(gamePanel, 
                ZombieManager.MANAGER_WIDTH, 
                (params.gridY + 1) * GridManager.GRID_HEIGHT);
        this.model = model;
        this.fullSpeed = GridManager.gridPerSecondToPixelPerFrame(0.5);
    }



    public void setFreezeFrame(int slowFrame) {
        this.freezeFrame = slowFrame;
    }

    @Override
    public void move() {
        PlantSlot collided = gamePanel.getGridManager().getCollideredPlantSlot(this);

        if (collided == null) {
            if (freezeFrame > 0) {
                freezeFrame--;
            }
            
            int currentSpeed;
            if (freezeFrame % 2 == 0) {
                currentSpeed = fullSpeed;
            } else {
                currentSpeed = 0;
            }
            
            posX -= currentSpeed;
        } else {
            collided.getPlant().getStatus().subtractHealth(10);

            if (!collided.getPlant().getStatus().alive()) {
                collided.clearPlant();
            }
        }
        if (posX < 0) {
            stopped = true;
        }
    }
    
    @Override
    public Rectangle getCoillderBox() {
        int coillderBoxX = posX + model.coillderBoxOffsetX;
        int coillderBoxY = posY + model.coillderBoxOffsetY;
        return new Rectangle(coillderBoxX, coillderBoxY, model.coillderBoxWidth, model.coillderBoxHeight);
    }
}