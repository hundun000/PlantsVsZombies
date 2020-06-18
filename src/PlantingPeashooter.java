
public class PlantingPeashooter extends Planting{
    public PlantingPeashooter(GamePanel gamePanel, int score, int x, int y) {
    	super(gamePanel, score, x, y);
    }
    	
    protected void setPlant() {
    	getCollider().setPlant(new Peashooter(getGamePanel(), getX(), getY()));
    }
}