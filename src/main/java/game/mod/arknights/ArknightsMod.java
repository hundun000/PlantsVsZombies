package game.mod.arknights;

import java.util.List;

import game.entity.bullet.BulletModel;
import game.entity.drop.DropModel;
import game.entity.plant.PlantModel;
import game.entity.zombie.ZombieModel;
import game.level.GameLevel;
import game.mod.Mod;

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<BulletModel> loadBulletModeles() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PlantModel> loadPlantModeles() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ZombieModel> loadZombieModeles() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GameLevel> loadGameLevels() {
        // TODO Auto-generated method stub
        return null;
    }

}
