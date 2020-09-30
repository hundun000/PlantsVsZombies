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
    public enum MoveType {
        WALK,
        JUMP_PREPARE,
        JUMPING,
    }
    private int maxJumpRange = GridManager.GRID_WIDTH;
    private Integer currentJumpRange;
    
    private MoveType moveType;
    
    public ZombiePositionComponent(GamePanel gamePanel, ZombieModel model, ZombieInstanceParams params, BaseZombie zombie) {
        super(gamePanel, 
                GridManager.MANAGER_WIDTH, 
                (params.gridY + 1) * GridManager.GRID_HEIGHT);
        this.model = model;
        this.fullSpeed = GridManager.gridPerSecondToPixelPerFrame(model.speedXGridPerSecond);
        setMoveTypeAndUpdatePosZ(MoveType.WALK);
    }


    @Override
    public void move() {

        
        switch (moveType) {
            case WALK:
                posX -= fullSpeed;
                break;
            case JUMP_PREPARE:
                posX -= fullSpeed * 1.5;
                break;
            case JUMPING:
                if (currentJumpRange == null) {
                    currentJumpRange = maxJumpRange;
                }
                posX -= fullSpeed * 1.5;
                currentJumpRange -= (int) (fullSpeed * 1.5);
                if (currentJumpRange <= 0) {
                    moveType = MoveType.WALK;
                    currentJumpRange = null;
                }
                break;
            default:
                break;
        }
        

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
    
    public MoveType getMoveType() {
        return moveType;
    }
    
    public void setMoveTypeAndUpdatePosZ(MoveType moveType) {
        this.moveType = moveType;
        switch (moveType) {
            case WALK:
            case JUMP_PREPARE:
                posZ = HeightZ.GROUND;
                break;
            case JUMPING:
                posZ = HeightZ.JUMPING;
            default:
                break;
        }
    }
    
    
    
}