public abstract class Planting {
	protected static final int NUM_COLUMN_CONSTANT = 9;
	
	private GamePanel gamePanel;
    private int score;
    private int x;
    private int y;
    private Collider collider;
    	
    public Planting(GamePanel gamePanel, int score, int x, int y) {
    	this.gamePanel = gamePanel;
    	this.score = score;
    	this.x = x;
    	this.y = y;
    	collider = getGamePanel().getColliders()[x + y * NUM_COLUMN_CONSTANT];
    }
    	
    public final void activePlanting() {
    	if (gamePanel.getSunScore() >= score) {
    		setPlant();
            gamePanel.setSunScore(gamePanel.getSunScore() - score);
        }
    }

    protected abstract void setPlant();
    
    public GamePanel getGamePanel() {
    	return gamePanel;
    }
    	
    public int getScore() {
    	return score;
    }
    	
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public Collider getCollider() {
    	return collider;
    }
}
    

    

    
