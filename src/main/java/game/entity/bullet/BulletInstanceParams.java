package game.entity.bullet;

import game.entity.gameobject.FightObject.FightSide;

/**
 * @author hundun
 * Created on 2020/09/10
 */
public class BulletInstanceParams {
    public int posX;
    public int posY;
    public int subTypeId = 0;
    public FightSide attacterSide;
    
    public BulletInstanceParams() {
        
    }
    
    public BulletInstanceParams(int posX, int posY, FightSide attacterSide) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.attacterSide = attacterSide;
    }
}
