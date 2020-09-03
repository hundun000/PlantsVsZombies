package game.component;

import java.awt.Rectangle;

import game.GamePanel;
import game.manager.GridManager;
import game.planting.PlantSlot;

public class ZombiePositionComponent extends PositionComponent{
    private static int START_POS_X = 1000;
    private boolean isMoving = true;
    int slowFrame = 0;
    private int speed = 1;

    public ZombiePositionComponent(GamePanel gamePanel, int y) {
        super(gamePanel, START_POS_X, y);
        if(gamePanel.getCurrentLevel() == 2) {
            this.speed += 1;
        }
    }

    public void setSlowFrame(int slowFrame) {
        this.slowFrame = slowFrame;
    }

    @Override
    public void move() {
        if (isMoving) {

            PlantSlot collided = gamePanel.getGridManager().getCollideredPlantSlot(this);

            if (collided == null) {
                if (slowFrame > 0) {
                    slowFrame--;
                }
                
                if (slowFrame % 2 == 0) {
                    posX -= speed;
                }
            } else {
                collided.assignedPlant.setHealth(collided.assignedPlant.getHealth() - 10);

                /* edited */
                final boolean planthasHealth = collided.assignedPlant.getHealth() >= 0;

                if (!planthasHealth) {
                    collided.plantDie();
                }
            }
            if (posX < 0) {
                isMoving = false;
                gamePanel.gameOver();
            }
        }
    }

    @Override
    public Rectangle getCoillderBox() {
        return new Rectangle(this.getPosX(), this.getPosY(), GridManager.GRID_WIDTH, GridManager.GRID_HEIGHT);
    }

}