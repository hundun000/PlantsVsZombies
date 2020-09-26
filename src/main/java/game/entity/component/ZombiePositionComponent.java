package game.entity.component;

import java.awt.Rectangle;

import game.GamePanel;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.others.PlantSlot;
import game.entity.zombie.BaseZombie;
import game.entity.zombie.ZombieInstanceParams;
import game.entity.zombie.ZombieModel;
import game.manager.GridManager;

public class ZombiePositionComponent extends PositionComponent{
    
    private int fullSpeed;
    private ZombieModel model;
    
    public ZombiePositionComponent(GamePanel gamePanel, ZombieModel model, ZombieInstanceParams params, BaseZombie zombie) {
        super(gamePanel, 
                GridManager.MANAGER_WIDTH, 
                (params.gridY + 1) * GridManager.GRID_HEIGHT);
        this.model = model;
        this.fullSpeed = GridManager.gridPerSecondToPixelPerFrame(model.speedXGridPerSecond);
    }


    @Override
    public void move() {

        
        
        posX -= fullSpeed;

        if (posX < 0) {
            moveDone = true;
        }
    }
    
    @Override
    public Rectangle getCoillderBox() {
        int coillderBoxX = posX + model.coillderBoxOffsetX;
        int coillderBoxY = posY + model.coillderBoxOffsetY;
        return new Rectangle(coillderBoxX, coillderBoxY, model.coillderBoxWidth, model.coillderBoxHeight);
    }
}