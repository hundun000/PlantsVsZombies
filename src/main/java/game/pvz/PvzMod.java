package game.pvz;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.DropMode;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.GameWindow;
import game.facroty.BulletFactory;
import game.facroty.DropFactory;
import game.facroty.PlantFactory;
import game.facroty.ZombieFactory;
import game.gameobject.bullet.BulletModel;
import game.gameobject.drop.DropModel;
import game.gameobject.gameobject.Spirit;
import game.gameobject.gameobject.WorkStatus.WorkState;
import game.gameobject.plant.PlantModel;
import game.gameobject.zombie.ZombieModel;
import game.level.GameLevel;
import game.level.NaturalZombieSpawnRule;
import game.manager.PlantCardManager;
import game.manager.SunScoreManager;
import game.pvz.bullet.Pea;
import game.pvz.drop.SunItem;
import game.pvz.plant.DoublePeashooter;
import game.pvz.plant.FreezePeashooter;
import game.pvz.plant.Peashooter;
import game.pvz.plant.Sunflower;
import game.pvz.zombie.ConeHeadZombie;
import game.pvz.zombie.NormalZombie;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/09
 */
public class PvzMod {
    static Logger logger = LoggerFactory.getLogger(PvzMod.class);
     
    public String modName = "pvz";
    
    public List<GameLevel> levels = new ArrayList<>(); 
    
    public void load(
            PlantFactory plantFactory, 
            ZombieFactory zombieFactory, 
            PlantCardManager plantCardManager, 
            BulletFactory bulletFactory, 
            DropFactory dropFactory
            ) {
        loadBulletModeles(bulletFactory);
        loadPlantModeles(plantFactory);
        loadZombieModeles(zombieFactory);
        loadDrops(dropFactory);
        
        loadCardSpirits(plantFactory, plantCardManager);
        
        
        List<NaturalZombieSpawnRule> rules;
        rules = new ArrayList<>();
        rules.add(new NaturalZombieSpawnRule("normal_zombie", 10));
        rules.add(new NaturalZombieSpawnRule("normal_zombie", 20));
        rules.add(new NaturalZombieSpawnRule("conehead_zombie", 20));
        levels.add(new GameLevel(rules));
    }
    
    private void loadDrops(DropFactory dropFactory) {
        DropModel model;
        
        model = new DropModel(SunItem.REGISTER_NAME, SunItem.class);
        model.spirit = new Spirit(ImageLoadTool.loadOneDropImage(modName, model.registerName));
        dropFactory.registerModel(model);
    }

    private void loadBulletModeles(BulletFactory bulletFactory) {
        BulletModel model;
        
        model = new BulletModel(Pea.NAME, Pea.class);
        model.spirit = new Spirit(ImageLoadTool.loadOneBulletImage(modName, model.registerName));
        bulletFactory.registerModel(model);
    }


    private void loadCardSpirits(PlantFactory plantFactory, PlantCardManager plantCardManager) {
        Collection<PlantModel> models = plantFactory.getModels();
        for (PlantModel model : models) {
            String cardRegisterName = PlantCardManager.getCardRegisterName(model.registerName);
            Spirit spirit = new Spirit(ImageLoadTool.loadOnePlantCardImage(modName, cardRegisterName));
            plantCardManager.getCardSpirits().put(cardRegisterName, spirit);
            plantCardManager.addPlantCard(model.registerName);
        }
        
    }

    private void loadPlantModeles(PlantFactory plantFactory) {
        PlantModel model;
        
        model = new PlantModel(Sunflower.NAME, Sunflower.class);
        model.spirit = new Spirit(
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE),
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING)
                );
        model.plantCost = 50;
        model.workColdDownFrameNum = 120;
        model.attackRangeWidth = -1;
        model.attackRangeHeight = -1;
        model.dropRegisterName = "sun";
        plantFactory.registerModel(model);
        
        model = new PlantModel(Peashooter.NAME, Peashooter.class);
        model.spirit = new Spirit(
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE),
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING)
                );
        model.plantCost = 100;
        model.bulletRegisterName = "pea";
        plantFactory.registerModel(model);
        
        model = new PlantModel(FreezePeashooter.NAME, FreezePeashooter.class);
        model.spirit = new Spirit(
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE),
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING)
                );
        model.plantCost = 175;
        model.bulletRegisterName = "pea";
        plantFactory.registerModel(model);
        
        model = new PlantModel(DoublePeashooter.NAME, DoublePeashooter.class);
        model.spirit = new Spirit(
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE),
                ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING)
                );
        model.plantCost = 200;
        model.workContinuousTime = 2;
        model.bulletRegisterName = "pea";
        plantFactory.registerModel(model);
    }

    

    private void loadZombieModeles(ZombieFactory zombieFactory) {
        ZombieModel model;
        
        model = new ZombieModel(NormalZombie.NAME, NormalZombie.class);
        model.spirit = new Spirit(ImageLoadTool.loadOneZombieImage(modName, model.registerName));
        zombieFactory.registerModel(model);
        
        model = new ZombieModel(ConeHeadZombie.NAME, ConeHeadZombie.class);
        model.spirit = new Spirit(ImageLoadTool.loadOneZombieImage(modName, model.registerName));
        zombieFactory.registerModel(model);
    }
    
    

}
