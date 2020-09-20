package game.mod.pvz.plant;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.*;

import game.GamePanel;
import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.bullet.BulletModel;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.entity.zombie.BaseZombie;
import game.manager.GridManager;
import game.mod.pvz.bullet.Pea;
import game.mod.pvz.bullet.Pea.PeaSubType;

/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends BasePlant {
    public static final String NAME = "peashooter";
    
    protected PeaSubType peaType;
    
    
    public Peashooter(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model, params);
        this.peaType = PeaSubType.NORMAL;
    }
    
    @Override
    protected BaseBullet generateBullet() {
        BaseBullet bullet = super.generateBullet();
        bullet.getStatus().setSubTypeId(peaType.getSubTypeId());
        return bullet;
    }


}
