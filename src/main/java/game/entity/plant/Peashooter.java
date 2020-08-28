package game.entity.plant;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.*;

import game.GamePanel;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.Pea;
import game.entity.zombie.BaseZombie;
import game.manager.GridManager;

/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends BasePlant {
    
    public static String REGISTER_NAME = "peashooter";

    private int gridY;
    public Peashooter(GamePanel parent, int gridX, int gridY) {
        super(parent, gridX, gridY, REGISTER_NAME, 10);
        this.gridY = gridY;
    }
    
    protected Pea generatePea() {
        return new Pea(getGamePanel(), getPositionComponent().getPosX(), getPositionComponent().getPosY());
    }


    @Override
    public void updateLogicFrame() {
        
    }

    @Override
    protected boolean wantWork() {
        Rectangle bulletRect = this.getAttackRangeBox();
        List<BaseZombie> zombies = gamePanel.getZombieManager().getZombiesIntersected(bulletRect);
        return !zombies.isEmpty();
    }

    @Override
    protected void work() {
        BaseBullet pea = generatePea();
        getGamePanel().getGridManager().addBulletToLane(pea, gridY);
    }
    
    private int attackRangeSpace = 1;

    @Override
    public Rectangle getAttackRangeBox() {
        return new Rectangle(getPositionComponent().getPosX() + attackRangeSpace, getPositionComponent().getPosY() + attackRangeSpace, GridManager.GRID_HEIGHT * 3, GridManager.GRID_WIDTH - 2 * attackRangeSpace);
    }

}
