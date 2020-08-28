package game.entity.planting;

import game.GamePanel;
import game.collision.PlantSlot;
import game.entity.plant.Peashooter;

public class PlantingPeashooter extends Planting{
    public PlantingPeashooter(GamePanel gamePanel, int score, int gridX, int gridY, PlantSlot plantSlot) {
    	super(gamePanel, score, gridX, gridY, plantSlot);
    }
    	
    @Override
    protected void setPlant() {
    	getCollider().setPlant(new Peashooter(getGamePanel(), getGridX(), getGridY()));
    }
}