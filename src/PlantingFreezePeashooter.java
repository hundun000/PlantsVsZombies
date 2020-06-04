
public class PlantingFreezePeashooter extends Planting{
    public PlantingFreezePeashooter(GamePanel gamePanel, int score, int x, int y) {
    	super(gamePanel, score, x, y);
   	}
    	
    protected void setPlant() {
    	getCollider().setPlant(new FreezePeashooter(getGamePanel(), getX(), getY()));
    }
}
