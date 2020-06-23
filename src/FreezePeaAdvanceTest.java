import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class FreezePeaAdvanceTest {
	private GamePanel gamePanel;
	private GameWindow gameWindow;
	private Collider[] colliders;
	private ArrayList<ArrayList<Zombie>> laneZombies;
	private FreezePeashooter[] freezePeashooter;
	private FreezePea freezePea;
	
	@Before
	public void setUp() {
		gameWindow = new GameWindow();
		gamePanel = gameWindow.getGamePanel();
		freezePeashooter = new FreezePeashooter[5]; 
	}

	/*
	 * Purpose: test whether freezePeais null when freezePeashooter meets zombie
	 * Input: freezePeashooter (gamePanel,0,(0,4))
	 * Expected: Return true
	 * 				freezePea = null
	 * 
	 */
	@Test
	public void testFreezePea_create() {
		colliders = gamePanel.getColliders();
		for(int row = 0; row<5; row++) {
			freezePeashooter[row]= new FreezePeashooter(gamePanel, 0, row);
			colliders[0+row*9].setPlant(freezePeashooter[row]);
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
						freezePea =  freezePeashooter[row].getFreezePea();
						find = false;
						assertNull(freezePea);
					}
				}
			}
		}
	}

}
