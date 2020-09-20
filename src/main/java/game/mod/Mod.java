package game.mod;

import java.util.List;

import game.entity.bullet.BulletModel;
import game.entity.drop.DropModel;
import game.entity.plant.PlantModel;
import game.entity.zombie.ZombieModel;
import game.level.GameLevel;

/**
 * @author hundun
 * Created on 2020/09/18
 */
public abstract class Mod {
    protected String modName;
    public Mod(String modName) {
        this.modName = modName;
    }
    
    public abstract List<DropModel> loadDrops();
    public abstract List<BulletModel> loadBulletModeles();
    public abstract List<PlantModel> loadPlantModeles();
    public abstract List<ZombieModel> loadZombieModeles();
    public abstract List<GameLevel> loadGameLevels();
    
    public String getModName() {
        return modName;
    }
}
