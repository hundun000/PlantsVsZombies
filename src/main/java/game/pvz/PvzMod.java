package game.pvz;

import java.io.File;
import java.util.Collection;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.GameWindow;
import game.facroty.BulletFactory;
import game.facroty.PlantFactory;
import game.facroty.ZombieFactory;
import game.gameobject.Spirit;
import game.gameobject.bullet.BulletModel;
import game.gameobject.plant.PlantModel;
import game.gameobject.zombie.ZombieModel;
import game.manager.PlantCardManager;
import game.manager.SunScoreManager;
import game.pvz.bullet.Pea;
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
     
    String modName = "pvz";
    
    public void load(
            PlantFactory plantFactory, 
            ZombieFactory zombieFactory, 
            PlantCardManager plantCardManager, 
            BulletFactory bulletFactory, 
            SunScoreManager sunScoreManager
            ) {
        loadBulletModeles(bulletFactory);
        loadPlantModeles(plantFactory);
        loadZombieModeles(zombieFactory);
        loadCardSpirits(plantFactory, plantCardManager);
        
        sunScoreManager.setSunItemSpirit(new Spirit(ImageLoadTool.loadSunItemImage(modName)));
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
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName));
        model.plantCost = 50;
        model.workColdDownReset = 50;
        model.attackRangeWidth = -1;
        model.attackRangeHeight = -1;
        plantFactory.registerModel(model);
        
        model = new PlantModel(Peashooter.NAME, Peashooter.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName));
        model.plantCost = 100;
        model.bulletRegisterName = "pea";
        plantFactory.registerModel(model);
        
        model = new PlantModel(FreezePeashooter.NAME, FreezePeashooter.class);
        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName));
        model.plantCost = 175;
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
