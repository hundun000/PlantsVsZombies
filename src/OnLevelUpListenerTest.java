import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OnLevelUpListenerTest {
	private GamePanel gamePanel;

	@Before
	public void setUp() {
		GameWindow gameWindow = new GameWindow();
		gamePanel = gameWindow.getGamePanel();
	}

	@After
	public void tearDown() { gamePanel.getLevelUpObservers().clear(); }

	/*
	 * Purpose: test adding LevelUp observer is valid
	 * Input: mLevelUpObservers
	 * Expected:
	 * 			gamePanel.getLevelUpObservers().size() == originalSize + 1
	 */
	@Test
	public void testAddLevelUpObservers() {
		Zombie zombie = new Zombie(gamePanel, 0);
		int originalSize = gamePanel.getLevelUpObservers().size();
		gamePanel.addLevelUpObservers(zombie);

		assertEquals(originalSize + 1, gamePanel.getLevelUpObservers().size());
	}

	/*
	 * Purpose: test did not LevelUp in GamePanel
	 * Input: currentLevel
	 * Expected:
	 * 			gamePanel.currentLevel == originalLevel
	 */
	@Test
	public void testNotLevelUp() {
		int originalLevel = gamePanel.currentLevel;
		gamePanel.setLevelPoint(10);

		assertEquals(originalLevel, gamePanel.currentLevel);
	}

	/*
	 * Purpose: test did LevelUp in GamePanel
	 * Input: currentLevel
	 * Expected:
	 * 		 	gamePanel.currentLevel == originalLevel + 1
	 */
	@Test
	public void testIsLevelUp() {
		int originalLevel = gamePanel.currentLevel;
		gamePanel.setLevelPoint(150);

		assertEquals(originalLevel + 1, gamePanel.currentLevel);
	}

	/*
	 * Purpose: test Zombie, Sunflower did level up
	 * Input: zombie.level, sunflower.delay
	 * Expected:
	 * 			zombie.level == zombie.level + 1
	 * 			sunflower.delay == 3000
	 */
	@Test
	public void testOnLevelUp_ZombieSunFlower() {
		Zombie zombie = new Zombie(gamePanel, 0);
		int originalZombieLevel = zombie.level;
		Sunflower sunflower = new Sunflower(gamePanel, 0, 0);
		gamePanel.addLevelUpObservers(zombie);
		gamePanel.addLevelUpObservers(sunflower);
		gamePanel.setLevelPoint(150);
		assertEquals(originalZombieLevel, zombie.level);
		assertEquals(3000, sunflower.delay);
	}
}
