package game.factory;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.gameobject.GameObjectModel;
import game.entity.plant.BasePlant;
import game.entity.plant.PlantInstanceParams;
import game.entity.plant.PlantModel;
import game.ui.GamePanel;

/**
 * @author hundun
 * Created on 2020/09/10
 * @param <T>
 * @param <T>
 */
public abstract class GameObjectModelFactory<TModel extends GameObjectModel<? extends TGameObject>, TGameObject, TInstanceParams> {
    static Logger logger = LoggerFactory.getLogger(GameObjectModelFactory.class);
    protected Map<String, TModel> models = new HashMap<>();
    
    private final Class<TModel> modelClazz;
    private final Class<TInstanceParams> instanceParamsClazz;
    
    public void registerModel(TModel model) {
        models.put(model.registerName, model);
    }
    
    public TModel getModel(String registerName) {
        return models.get(registerName);
    }

    public Collection<TModel> getModels() {
        return models.values();
    }
    
    public GameObjectModelFactory(Class<TModel> modelClazz, Class<TInstanceParams> instanceParamsClazz) {
        super();
        this.modelClazz = modelClazz;
        this.instanceParamsClazz = instanceParamsClazz;
    }

    public TGameObject getInstacne(String registerName, GamePanel gamePanel, TInstanceParams params) {
        
        TModel model = models.get(registerName);
        TGameObject instance = null;
        if (model != null) {
            try {
                Constructor<? extends TGameObject> constroctor = model.instanceClazz.getConstructor(GamePanel.class, modelClazz, instanceParamsClazz);
                instance = constroctor.newInstance(gamePanel, model, params);
            } catch (Exception e) {
                e.printStackTrace();
                instance = null;
            }
        } else {
            logger.warn("cannot get instance model by: {}", registerName);
        }
        
        return instance;
    }
    
}
