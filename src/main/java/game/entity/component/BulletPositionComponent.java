package game.entity.component;
import java.awt.Rectangle;

import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.gameobject.FightObject;
import game.ui.GamePanel;

public class BulletPositionComponent extends PositionComponent {
    BulletModel model;

    private final int startX;
    private final int startY;
    private double speedX;
    private double speedY;
    private Integer endX;
    private Integer endY;
    
    public BulletPositionComponent(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, params.posX, params.posY);
        this.model = model;
        this.startX = params.posX;
        this.startY = params.posY;
        this.speedX = model.speedX;
        this.speedY = model.speedY;
        this.endX = model.endDeltaX != null ? startX + model.endDeltaX : null;
        this.endY = model.endDeltaY != null ? startY + model.endDeltaY : null;
    }

    @Override
    public void move() {
        int nextX = (int) (posX + getSpeedX());
        int nextY = (int) (posY + getSpeedY());
        boolean wantMoveX = endX == null || Math.abs(posX - endX) > Math.abs(nextX - endX);
        boolean wantMoveY = endY == null || Math.abs(posY - endY) > Math.abs(nextY - endY);
        if (wantMoveX) {
            posX += speedX;
        }
        if (wantMoveY) {
            posY += speedY;
        }
        if (!wantMoveX && !wantMoveY) {
            moveDone = true;
        }
    }

    
    @Override
    public Rectangle getCoillderBox() {
        int coillderBoxX = posX + model.coillderBoxOffsetX;
        int coillderBoxY = posY + model.coillderBoxOffsetY;
        return new Rectangle(coillderBoxX, coillderBoxY, model.coillderBoxWidth, model.coillderBoxHeight);
    }
    
    
    
    public double getSpeedX() {
        return speedX;
    }
    
    public double getSpeedY() {
        return speedY;
    }

    
    /**
     * 重置speed为沿this.coillderBox与target.coillderBox中心连线
     */
    public void updateSpeed(FightObject target) {
        Rectangle targetCoillderBox = target.getPositionComponent().getCoillderBox();
        Rectangle myCoillderBox = this.getCoillderBox();
        double deltaX = targetCoillderBox.getCenterX() - myCoillderBox.getCenterX();
        double deltaY = targetCoillderBox.getCenterY() - myCoillderBox.getCenterY();
        double speedMagnitude = Math.sqrt(this.getSpeedX() * this.getSpeedX() + this.getSpeedY() * this.getSpeedY());
        if (deltaX != 0) {
            double speedAngle = Math.atan(deltaY / deltaX);
            this.speedX = (speedMagnitude * Math.cos(speedAngle));
            this.speedY = (speedMagnitude * Math.sin(speedAngle));
        } else {
            this.speedX = (speedMagnitude);
        }
        
    }
    
}