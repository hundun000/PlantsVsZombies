package game.entity.planting;

import game.GamePanel;
import game.collision.PlantSlot;
import game.entity.plant.Sunflower;

public class PlantingSunflower extends Planting{
    public PlantingSunflower(GamePanel gamePanel, int cost, int x, int y, PlantSlot plantSlot) {
    	super(gamePanel, cost, x, y, plantSlot);
    }
    	
    @Override
    protected void setPlant() {
    	getCollider().setPlant(new Sunflower(getGamePanel(), getGridX(), getGridY()));
    }
}
