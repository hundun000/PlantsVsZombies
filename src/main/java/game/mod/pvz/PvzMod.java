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
import game.entity.drop.DropModel;
import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.PlantModel;
import game.entity.zombie.ZombieModel;
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
import game.mod.pvz.bullet.Explosion;
import game.mod.pvz.bullet.Pea;
import game.mod.pvz.drop.SunItem;
import game.mod.pvz.plant.Cherrybomb;
import game.mod.pvz.plant.DoublePeashooter;
import game.mod.pvz.plant.FrozenPeashooter;
import game.mod.pvz.plant.Peashooter;
import game.mod.pvz.plant.PotetoMine;
import game.mod.pvz.plant.Sunflower;
import game.mod.pvz.plant.Wallnut;
import game.mod.pvz.zombie.ConeHeadZombie;
import game.mod.pvz.zombie.NormalZombie;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/09
 */
public class PvzMod extends Mod{
    static Logger logger = LoggerFactory.getLogger(PvzMod.class);
    
    public PvzMod() {
        super("pvz");
    }
    
    public List<GameLevel> loadGameLevels() {
        List<GameLevel> levels = new ArrayList<>();
        
        List<NaturalZombieSpawnRule> rules;
        rules = new ArrayList<>();
        rules.add(new NaturalZombieSpawnRule("normal_zombie", 10));
        rules.add(new NaturalZombieSpawnRule("normal_zombie", 20));
        rules.add(new NaturalZombieSpawnRule("conehead_zombie", 20));
        levels.add(new GameLevel(rules));
        
        return levels;
    }
    
    public List<DropModel> loadDrops() {
        List<DropModel> models = new ArrayList<>();
        DropModel model;
        
        model = new DropModel(SunItem.NAME, SunItem.class);
        model.spirit = new Spirit(ImageLoadTool.loadOneDropImage(modName, model.registerName));
        models.add(model);
        
        return models;
    }

    public List<BulletModel> loadBulletModeles() {
        List<BulletModel> models = new ArrayList<>();
        BulletModel model;
        
        model = new BulletModel(Pea.NAME, Pea.class);
        model.spirit = new Spirit(
                Arrays.asList(
                        ImageLoadTool.loadOneBulletImage(modName, model.registerName + "_0"),
                        ImageLoadTool.loadOneBulletImage(modName, model.registerName + "_1")
                        )
                );
        models.add(model);
        
        model = new BulletModel(Explosion.CHERRYBOMB_EXPLOSION_NAME, Explosion.class);
        model.spirit = new Spirit(
                Arrays.asList(
                        ImageLoadTool.loadOneBulletImage(modName, model.registerName)
                        )
                );
        model.coillderBoxHeight = GridManager.GRID_HEIGHT * 3;
        model.coillderBoxWidth = GridManager.GRID_WIDTH * 3;
        model.coillderBoxOffsetX = 0;
        model.coillderBoxOffsetY = - GridManager.GRID_HEIGHT * 3;
        model.speedX = 0;
        model.speedY = 0;
        model.damage = 1000;
        models.add(model);
        
        model = new BulletModel(Explosion.POTETO_MINE_EXPLOSION_NAME, Explosion.class);
        model.spirit = new Spirit(
                Arrays.asList(
                        ImageLoadTool.loadOneBulletImage(modName, model.registerName)
                        )
                );
        model.coillderBoxHeight = (int) (GridManager.GRID_HEIGHT * 1);
        model.coillderBoxWidth = (int) (GridManager.GRID_WIDTH * 1.2);
        model.coillderBoxOffsetX = 0;
        model.coillderBoxOffsetY = - GridManager.GRID_HEIGHT * 1;
        model.speedX = 0;
        model.speedY = 0;
        model.damage = 1000;
        models.add(model);
        
        return models;
    }


    

    public List<PlantModel> loadPlantModeles() {
        List<PlantModel> models = new ArrayList<>();
        PlantModel model;
        
        model = new PlantModel(Sunflower.NAME, Sunflower.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
                .build(WorkState.WORKING, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING));
        model.plantCost = 50;
        model.attackColdDownFrameNum = 120;
        model.dropRegisterName = SunItem.NAME;
        models.add(model);
        
        model = new PlantModel(Peashooter.NAME, Peashooter.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
                .build(WorkState.WORKING, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING))
                ;
        model.plantCost = 100;
        model.bulletRegisterName = Pea.NAME;
        models.add(model);
        
        model = new PlantModel(FrozenPeashooter.NAME, FrozenPeashooter.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
                .build(WorkState.WORKING, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING))
                ;
        model.plantCost = 175;
        model.bulletRegisterName = Pea.NAME;
        models.add(model);
        
        model = new PlantModel(DoublePeashooter.NAME, DoublePeashooter.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
                .build(WorkState.WORKING, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING))
                ;
        model.plantCost = 200;
        model.attackContinuousTime = 2;
        model.bulletRegisterName = Pea.NAME;
        models.add(model);
        
        model = new PlantModel(Cherrybomb.NAME, Cherrybomb.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
                .build(WorkState.WORKING, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING))
                ;
        model.plantCost = 150;
        model.attackColdDownFrameNum = (int) (GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 0.5);
        model.bulletRegisterName = Explosion.CHERRYBOMB_EXPLOSION_NAME;
        model.bulletStartOffsetX = - GridManager.GRID_WIDTH;
        model.bulletStartOffsetY = GridManager.GRID_HEIGHT;
        models.add(model);
        
        model = new PlantModel(PotetoMine.NAME, PotetoMine.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
                .build(WorkState.WORK_READY, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORK_READY))
                .build(WorkState.WORKING, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING))
                ;
        model.plantCost = 25;
        model.attackRangeWidth = (int) (GridManager.GRID_WIDTH * 1);
        model.attackColdDownFrameNum = GamePanel.LOGICAL_FRAME_NUM_PER_SECOND * 4;
        model.bulletRegisterName = Explosion.POTETO_MINE_EXPLOSION_NAME;
        model.bulletStartOffsetX = - 0;
        model.bulletStartOffsetY = 0;
        models.add(model);
        
        model = new PlantModel(Wallnut.NAME, Wallnut.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
                ;
        model.plantCost = 50;
        model.health = 600;
        models.add(model);
        
        return models;
    }

    

    public List<ZombieModel> loadZombieModeles() {
        List<ZombieModel> models = new ArrayList<>();
        ZombieModel model;
        
        model = new ZombieModel(NormalZombie.NAME, NormalZombie.class);
        model.spirit = new Spirit(ImageLoadTool.loadOneZombieImage(modName, model.registerName));
        models.add(model);
        
        model = new ZombieModel(ConeHeadZombie.NAME, ConeHeadZombie.class);
        model.spirit = new Spirit(ImageLoadTool.loadOneZombieImage(modName, model.registerName));
        models.add(model);
        
        return models;
    }
    
    

}
