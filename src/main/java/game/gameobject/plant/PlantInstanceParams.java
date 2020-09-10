package game.gameobject.plant;
/**
 * @author hundun
 * Created on 2020/09/08
 */
public class PlantInstanceParams {
    public int gridX;
    public int gridY;
    public int posX;
    public int posY;
    
    public PlantInstanceParams() {
        
    }
    
    public PlantInstanceParams(int gridX, int gridY, int posX, int posY) {
        super();
        this.gridX = gridX;
        this.gridY = gridY;
        this.posX = posX;
        this.posY = posY;
    }
    
    
}
