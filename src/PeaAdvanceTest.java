import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class PeaAdvanceTest {
	private GamePanel gamePanel;
	private GameWindow gameWindow;
	private Collider[] colliders;
	private ArrayList<ArrayList<Zombie>> laneZombies;
	private Peashooter[] peashooter;
	private Pea pea;
	
	@Before
	public void setUp() {
		gameWindow = new GameWindow();
		gamePanel = gameWindow.getGamePanel();
		peashooter = new Peashooter[5]; 
	}

	/*
	 * Purpose: test whether pea is null when peashooter meets zombie
	 * Input: peashooter (gamePanel,0,(0,4))
	 * Expected: Return true
	 * 				pea = null
	 * 
	 */
	@Test
	public void testPea_create() {
		colliders = gamePanel.getColliders();
		for(int row = 0; row<5; row++) {
			peashooter[row]= new Peashooter(gamePanel, 0, row);
			colliders[0+row*9].setPlant(peashooter[row]);
		}
	
		laneZombies = gamePanel.getLaneZombies();
		boolean find = true;
		while (find) {
			for (int row = 0; row < 5; row++) {
				for (Zombie z : laneZombies.get(row)) {
					if (z != null) {
						find = false;
					}
				}
			}
		}
		find = true;
		while (find) {
			for (int row = 0; row < 5; row++) {
				for (Zombie z : laneZombies.get(row)) {
					if (z != null) {
						pea =  peashooter[row].getPea();
						find = false;
						assertNull(pea);
					}
				}
			}
		}
	}
}
	

