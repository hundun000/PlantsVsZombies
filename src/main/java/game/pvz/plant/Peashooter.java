package game.pvz.plant;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.*;

import game.GamePanel;
import game.gameobject.bullet.BaseBullet;
import game.gameobject.bullet.BulletInstanceParams;
import game.gameobject.bullet.BulletModel;
import game.gameobject.plant.BasePlant;
import game.gameobject.plant.PlantInstanceParams;
import game.gameobject.plant.PlantModel;
import game.gameobject.zombie.BaseZombie;
import game.manager.GridManager;
import game.pvz.bullet.Pea;
import game.pvz.bullet.Pea.PeaType;

/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends BasePlant {
    public static final String NAME = "peashooter";
    
    protected PeaType peaType;
    
    
    public Peashooter(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
        this.peaType = PeaType.NORMAL;
    }
    
    private BaseBullet generateBullet() {
        int bulletStartX = getPositionComponent().getPosX() + this.model.bulletStartOffsetX;
        int bulletStartY = getPositionComponent().getPosY() + this.model.bulletStartOffsetY;
        
        BulletInstanceParams params = new BulletInstanceParams(bulletStartX, bulletStartY);
        BaseBullet bullet = gamePanel.getBulletFactory().getInstacne(
                this.model.bulletRegisterName, gamePanel, params);
        return bullet;
    }

    @Override
    protected boolean wantWork() {
        Rectangle bulletRect = this.getAttackRangeBox();
        List<BaseZombie> zombies = gamePanel.getZombieManager().getZombiesIntersected(bulletRect);
        return !zombies.isEmpty();
    }

    @Override
    protected void work() {
        BaseBullet pea = generateBullet();
        gamePanel.getGridManager().addBullet(pea);
    }

}
