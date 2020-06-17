import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;

public class ZombieAdvanceTest {
	private GamePanel gamePanel;
	private GameWindow gameWindow;
	private Zombie zombie;
	ArrayList<ArrayList<Zombie>> laneZombies;

	@Before
	public void setUp() {
		gameWindow = new GameWindow();
		gamePanel = gameWindow.getGamePanel();
	}

	/*
	 * Purpose: test whether isMoving is true when object is created 
	 * Input: null
	 * Expected: Return true 
	 * 				isMoving = true;
	 * 
	 */
	@Test
	public void testIsMoving_create() {
		laneZombies = gamePanel.getLaneZombies();
		zombie = null;
		boolean find = true;
		while (find) {
			for (int row = 0; row < 5; row++) {
				for (Zombie z : laneZombies.get(row)) {
					if (z != null) {
						find = false;
						zombie = z;
					}
				}
			}
		}
		
		boolean expectedIsMoving = true;

		assertEquals(zombie.isMoving(), expectedIsMoving);
	}

	/*
	 * Purpose: test whether isMoving is true when object uses setter function
	 * Input: isMoving: false -> true
	 * Expected: Return true
	 * 				isMoving = false
	 * 
	 */
	@Test
	public void testIsMoving_setter() {
		laneZombies = gamePanel.getLaneZombies();
		zombie = null;
		boolean find = true;
		while (find) {
			for (int row = 0; row < 5; row++) {
				for (Zombie z : laneZombies.get(row)) {
					if (z != null) {
						find = false;
						zombie = z;
					}
				}
			}
		}
		
		boolean expectedIsMoving = false;

		zombie.setMoving(false);
		assertEquals(zombie.isMoving(), expectedIsMoving);
	}
	
	/*
	 * Purpose: test posX when zombie advances
	 * Input: null
	 * Expected: Return true
	 * 				posX = 999
	 * 
	 */
	@Test
	public void testAdvance() {
		laneZombies = gamePanel.getLaneZombies();
		zombie = null;
		boolean find = true;
		while (find) {
			for (int row = 0; row < 5; row++) {
				for (Zombie z : laneZombies.get(row)) {
					if (z != null) {
						find = false;
						zombie = z;
					}
				}
			}
		}
		
		int expectedPosX = 999;

		zombie.advance();

		assertEquals(zombie.getPosX(), expectedPosX);
	}
	
	/*
	 * Purpose: test slowInt when slowInt is odd
	 * Input: slowInt: 0 -> 300 
	 * Expected: Return true
	 * 				slowInt = 299
	 * 				posX = 998
	 * 
	 */

	@Test
	public void testSlowInt_odd() {
		laneZombies = gamePanel.getLaneZombies();
		zombie = null;
		boolean find = true;
		while (find) {
			for (int row = 0; row < 5; row++) {
				for (Zombie z : laneZombies.get(row)) {
					if (z != null) {
						find = false;
						zombie = z;
					}
				}
			}
		}
		
		int expectedPosX = 998;
		int expectedSlowInt = 299;
		
		zombie.setSlowInt(300);
		zombie.advance();

		assertEquals(zombie.getSlowInt(), expectedSlowInt);
		assertEquals(zombie.getPosX(), expectedPosX);
	}

	/*
	 * Purpose: test slowInt when slowInt is even
	 * Input: slowInt: 0 -> 200
	 * Expected: Return true
	 * 				slowInt = 199
	 * 				posX = 999
	 * 
	 */
	@Test
	public void testSlowInt_even() {
		laneZombies = gamePanel.getLaneZombies();
		zombie = null;
		boolean find = true;
		while (find) {
			for (int row = 0; row < 5; row++) {
				for (Zombie z : laneZombies.get(row)) {
					if (z != null) {
						find = false;
						zombie = z;
					}
				}
			}
		}
		
		int expectedPosX = 999;
		int expectedSlowInt = 199;
		
		zombie.setSlowInt(200);
		zombie.advance();
		System.out.print(zombie.getPosX());
		assertEquals(zombie.getSlowInt(), expectedSlowInt);
		assertEquals(zombie.getPosX(), expectedPosX);
	}

}
