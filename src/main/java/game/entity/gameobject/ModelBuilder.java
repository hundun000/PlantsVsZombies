package game.entity.gameobject;

import game.entity.plant.PlantModel;

/**
 * @author hundun
 * Created on 2020/09/24
 */
public abstract class ModelBuilder<T> {
    
    public final T model() {
        T model = start();
        build(model);
        return model;
    } 
    
    protected abstract T start();
    protected abstract void build(T model);
}
