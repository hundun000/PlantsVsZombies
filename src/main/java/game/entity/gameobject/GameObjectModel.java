package game.entity.gameobject;

import game.entity.bullet.BaseBullet;

/**
 * @author hundun
 * Created on 2020/09/10
 * @param <T>
 */
public class GameObjectModel<T> {
    public GameObjectModel(String registerName, Class<? extends T> clazz) {
        this.registerName = registerName;
        this.instanceClazz = clazz;
    }
    public final String registerName;
    public final Class<? extends T> instanceClazz;
    public Spirit spirit;
    
    @Override
    public String toString() {
        return registerName + "-model";
    }
    
    public static int defaultOffset = 5;
}
