package game.entity.planting;

import game.GamePanel;
import game.collision.PlantSlot;
import game.entity.plant.FreezePeashooter;

public class PlantingFreezePeashooter extends Planting{
    public PlantingFreezePeashooter(GamePanel gamePanel, int score, int x, int y, PlantSlot plantSlot) {
    	super(gamePanel, score, x, y, plantSlot);
   	}
    	
    @Override
    protected void setPlant() {
    	getCollider().setPlant(new FreezePeashooter(getGamePanel(), getGridX(), getGridY()));
    }
}
