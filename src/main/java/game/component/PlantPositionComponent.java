package game.component;

import java.awt.Rectangle;

import game.GamePanel;
import game.manager.GridManager;

/**
 * @author hundun
 * Created on 2020/09/04
 */
public class PlantPositionComponent extends PositionComponent {

    public PlantPositionComponent(GamePanel gamePanel, int x, int y) {
        super(gamePanel, x, y);
    }

    @Override
    public void move() {
        
    }

    @Override
    public Rectangle getCoillderBox() {
        return new Rectangle(this.getPosX(), this.getPosY(), GridManager.GRID_WIDTH, GridManager.GRID_HEIGHT);
    }

}
