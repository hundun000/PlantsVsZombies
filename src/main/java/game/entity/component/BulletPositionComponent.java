package game.entity.component;
import java.awt.Rectangle;

import game.GamePanel;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;

public class BulletPositionComponent extends PositionComponent {
    BulletModel model;

    private final int startX;
    private final int startY;
    
    public BulletPositionComponent(GamePanel gamePanel, BulletModel model, BulletInstanceParams params) {
        super(gamePanel, params.posX, params.posY);
        this.model = model;
        this.startX = params.posX;
        this.startY = params.posY;
    }

    @Override
    public void move() {
        boolean wantMoveX = posX - startX < model.endDeltaX;
        boolean wantMoveY = posY - startY < model.endDeltaX;
        if (wantMoveX) {
            posX += model.speedX;
        }
        if (wantMoveY) {
            posY += model.speedY;
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
}