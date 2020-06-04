
public class PlantingSunflower extends Planting{
    public PlantingSunflower(GamePanel gamePanel, int score, int x, int y) {
    	super(gamePanel, score, x, y);
    }
    	
    protected void setPlant() {
    	getCollider().setPlant(new Sunflower(getGamePanel(), getX(), getY()));
    }
}
