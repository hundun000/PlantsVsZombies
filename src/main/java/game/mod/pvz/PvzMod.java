package game.mod.pvz;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.DropMode;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.GameWindow;
import game.entity.bullet.BulletModel;
import game.entity.bullet.template.DebuffBullect;
import game.entity.bullet.template.Explosion;
import game.entity.bullet.template.DebuffBullect.DebuffType;
import game.entity.drop.DropModel;
import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.PlantModel;
import game.entity.plant.template.BombPlant;
import game.entity.plant.template.DropPlant;
import game.entity.plant.template.IldePlant;
import game.entity.plant.template.MinePlant;
import game.entity.plant.template.ShooterPlant;
import game.entity.zombie.ZombieModel;
import game.entity.zombie.template.NormalZombie;
import game.factory.BulletFactory;
import game.factory.DropFactory;
import game.factory.PlantFactory;
import game.factory.ZombieFactory;
import game.level.GameLevel;
import game.level.NaturalZombieSpawnRule;
import game.manager.GridManager;
import game.manager.PlantCardManager;
import game.manager.SunScoreManager;
import game.mod.Mod;
import game.mod.pvz.bullet.CherrybombExplosionBuilder;
import game.mod.pvz.bullet.PeaBuilder;
import game.mod.pvz.bullet.PotetoMineExplosionBuilder;
import game.mod.pvz.bullet.ZombieHitBuilder;
import game.mod.pvz.drop.SunItem;
import game.mod.pvz.plant.CherrybombBuilder;
import game.mod.pvz.plant.DoublePeashooterBuilder;
import game.mod.pvz.plant.FrozenPeashooterBuilder;
import game.mod.pvz.plant.PeashooterBuilder;
import game.mod.pvz.plant.PotetoMineBuilder;
import game.mod.pvz.plant.SunflowerBuilder;
import game.mod.pvz.plant.WallnutBuilder;
import game.mod.pvz.zombie.ConeheadZombieBuilder;
import game.mod.pvz.zombie.PlainZombieBuilder;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/09
 */
public class PvzMod extends Mod{
    static Logger logger = LoggerFactory.getLogger(PvzMod.class);
    public static String NAME = "pvz";
    public PvzMod() {
        super(NAME);
    }
    
    public List<GameLevel> loadGameLevels() {
        List<GameLevel> levels = new ArrayList<>();
        
        List<NaturalZombieSpawnRule> rules;
        rules = new ArrayList<>();
        rules.add(new NaturalZombieSpawnRule(PlainZombieBuilder.NAME, 10));
        rules.add(new NaturalZombieSpawnRule(PlainZombieBuilder.NAME, 15));
        rules.add(new NaturalZombieSpawnRule(PlainZombieBuilder.NAME, 20));
        rules.add(new NaturalZombieSpawnRule(ConeheadZombieBuilder.NAME, 20));
        levels.add(new GameLevel(rules));
        
        return levels;
    }
    
    public List<DropModel> loadDrops() {
        List<DropModel> models = new ArrayList<>();
        DropModel model;
        
        model = new DropModel(SunItem.NAME, SunItem.class);
        model.spirit = new Spirit(ImageLoadTool.loadOneDropImage(modName, model.registerName));
        model.chargePoint = 25;
        models.add(model);
        
        return models;
    }

    public List<BulletModel> loadBulletModeles() {
        List<BulletModel> models = new ArrayList<>();
        
        models.add(new ZombieHitBuilder().model());
        
        models.add(new PeaBuilder().model());
        
        models.add(new CherrybombExplosionBuilder().model());
        
        models.add(new PotetoMineExplosionBuilder().model());
        
        return models;
    }


    

    public List<PlantModel> loadPlantModeles() {
        List<PlantModel> models = new ArrayList<>();
        
        

        models.add(new SunflowerBuilder().model());
        
        models.add(new PeashooterBuilder().model());
        
        models.add(new FrozenPeashooterBuilder().model());
        
        models.add(new DoublePeashooterBuilder().model());
        
        models.add(new CherrybombBuilder().model());
        
        
        models.add(new PotetoMineBuilder().model());
        
        models.add(new WallnutBuilder().model());
        
        return models;
    }

    

    public List<ZombieModel> loadZombieModeles() {
        List<ZombieModel> models = new ArrayList<>();
        
        models.add(new PlainZombieBuilder().model());
        
        models.add(new ConeheadZombieBuilder().model());
        
        return models;
    }
    
    

}
