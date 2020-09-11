package game.gameobject.bullet;
/**
 * @author hundun
 * Created on 2020/09/10
 */
public class BulletInstanceParams {
    public int posX;
    public int posY;
    public int subTypeId = 0;
    
    public BulletInstanceParams() {
        
    }
    
    public BulletInstanceParams(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
    }
}
