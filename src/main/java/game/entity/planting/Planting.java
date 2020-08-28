package game.entity.planting;

import game.GamePanel;
import game.collision.PlantSlot;

public abstract class Planting {
	protected static final int NUM_COLUMN_CONSTANT = 9;
	
	private GamePanel gamePanel;
    private int plantCost;
    private int gridX;
    private int gridY;
    private PlantSlot plantSlot;
    	
    public Planting(GamePanel gamePanel, int plantCost, int gridX, int gridY, PlantSlot plantSlot) {
    	this.gamePanel = gamePanel;
    	this.plantCost = plantCost;
    	this.gridX = gridX;
    	this.gridY = gridY;
    	this.plantSlot = plantSlot;
    }
    	
    public final void activePlanting() {
    	if (gamePanel.getSunScoreManager().hasEnoughSunScore(plantCost)) {
    		setPlant();
            gamePanel.getSunScoreManager().addSunScore(-1 * plantCost);;
        }
    }

    protected abstract void setPlant();
    
    public GamePanel getGamePanel() {
    	return gamePanel;
    }
    	
    public int getPlantCost() {
    	return plantCost;
    }
    	
    public int getGridX() {
    	return gridX;
    }
    
    public int getGridY() {
    	return gridY;
    }
    
    public PlantSlot getCollider() {
    	return plantSlot;
    }
}
    

    

    
