package game.mod.arknights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.GamePanel;
import game.entity.bullet.BulletModel;
import game.entity.bullet.template.DebuffBullect;
import game.entity.bullet.template.Explosion;
import game.entity.drop.DropModel;
import game.entity.gameobject.Spirit;
import game.entity.gameobject.WorkStatus.WorkState;
import game.entity.plant.PlantModel;
import game.entity.plant.template.DropPlant;
import game.entity.zombie.ZombieModel;
import game.level.GameLevel;
import game.level.NaturalZombieSpawnRule;
import game.manager.GridManager;
import game.mod.Mod;
import game.mod.arknights.bullet.CloseHit;
import game.mod.arknights.drop.DeployCostDrop;
import game.mod.arknights.plant.template.Operator;
import game.mod.pvz.drop.SunItem;
import game.utils.ImageLoadTool;

/**
 * @author hundun
 * Created on 2020/09/18
 */
public class ArknightsMod extends Mod {

    public ArknightsMod(String modName) {
        super("arknights");
        
    }

    @Override
    public List<DropModel> loadDrops() {
        List<DropModel> models = new ArrayList<>();
        DropModel model;
        
        model = new DropModel(DeployCostDrop.NAME, DeployCostDrop.class);
        model.spirit = new Spirit(model.registerName, ImageLoadTool.loadOneDropImage(modName, model.registerName));
        model.chargePoint = 5;
        models.add(model);
        
        
        return models;
    }

    @Override
    public List<BulletModel> loadBulletModeles() {
        List<BulletModel> models = new ArrayList<>();
        BulletModel model;
        

        return models;
    }

    @Override
    public List<PlantModel> loadPlantModeles() {
        List<PlantModel> models = new ArrayList<>();
        PlantModel model;
        
//        model = new PlantModel(Vanguard.YATO_NAME, Vanguard.class);
//        model.spirit = new Spirit(ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.IDLE))
//                .build(WorkState.WORKING, ImageLoadTool.loadOnePlantImage(modName, model.registerName, WorkState.WORKING));
//        model.buildBasic(10);
//
//        models.add(model);
        
        return models;
    }

    @Override
    public List<ZombieModel> loadZombieModeles() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GameLevel> loadGameLevels() {
        List<GameLevel> levels = new ArrayList<>();
        
        List<NaturalZombieSpawnRule> rules;
        rules = new ArrayList<>();
        levels.add(new GameLevel(rules));
        
        return levels;
    }

}
