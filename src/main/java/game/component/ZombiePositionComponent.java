package game.component;

import game.GamePanel;
import game.collision.PlantSlot;
import game.manager.GridManager;

public class ZombiePositionComponent extends PositionComponent{
    private static int START_POS_X = 1000;
    private boolean isMoving = true;
    int slowInt = 0;
    private int speed = 1;

    public ZombiePositionComponent(GamePanel gamePanel, int y) {
        super(START_POS_X, y, gamePanel);
        if(gamePanel.getCurrentLevel() == 2) {
            this.speed += 1;
        }
    }

    
    public boolean isMoving() {
        return isMoving;
    }
    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }
    public void slow() {
        this.slowInt = 1000;
    }
    public int getSlowInt() {
        return slowInt;
    }

    public void setSlowInt(int slowInt) {
        this.slowInt = slowInt;
    }

    public void faster() {
        speed += 1;
    }

    @Override
    public void move() {
        if (isMoving) {

            boolean isCollides = false;
            PlantSlot collided = gamePanel.getGridManager().getCollideredPlantSlot(posX, true);

            if(collided!= null) {
                isCollides = true;
            }
            if (!isCollides) {
                if (slowInt > 0) {
                    if (slowInt % 2 == 0) {
                        posX -= speed;
                    }
                    slowInt--;
                } else {
                    posX -= speed;
                }
            } else {
                collided.assignedPlant.setHealth(collided.assignedPlant.getHealth() - 10);

                /* edited */
                final boolean planthasHealth = collided.assignedPlant.getHealth() >= 0;

                if (!planthasHealth) {
                    collided.removePlant();
                }
            }
            if (posX < 0) {
                isMoving = false;
                gamePanel.gameOver();
            }
        }
    }

}