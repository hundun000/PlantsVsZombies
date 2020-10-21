package game.entity.component;

import java.awt.Rectangle;

import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.ui.GamePanel;

/**
 * @author hundun
 * Created on 2020/09/07
 */
public class IdlePositionComponent extends PositionComponent {

    
    Rectangle idleCoillderBox;
    
    public IdlePositionComponent(
            GamePanel gamePanel, 
            int posX, 
            int posY, 
            int coillderBoxOffsetX, 
            int coillderBoxOffsetY,
            int coillderBoxWidth, 
            int coillderBoxHeight
            ) {
        super(gamePanel, posX, posY);
        int coillderBoxX = posX + coillderBoxOffsetX;
        int coillderBoxY = posY + coillderBoxOffsetY;
        this.idleCoillderBox = new Rectangle(coillderBoxX, coillderBoxY, coillderBoxWidth, coillderBoxHeight);
    }

    public IdlePositionComponent(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        this(gamePanel, params.posX, params.posY, model.coillderBoxOffsetX , model.coillderBoxOffsetY, model.coillderBoxWidth, model.coillderBoxHeight);
    }

    @Override
    public void move() {

    }

    @Override
    public Rectangle getCoillderBox() {
        return idleCoillderBox;
    }

}
