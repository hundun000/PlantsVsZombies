import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JLabel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractPeaTest {
	private AbstractPea testItem;
	private GamePanel gamePanel;
	
	@Before
	public void setUp() throws Exception {}
	@After
	public void tearDown() throws Exception { testItem = null; }
	/*
	 * Purpose: test attackZombie function 
	 * Input: Zombie.setHealth(299)->(300)
	 * Expected: 
	 * 			(299) => Return True
	 * 			(300) => Return False
	 */
	@Test
	public void testAttackZombie() {
		gamePanel = new GamePanel(new JLabel("SUN"));
		testItem = new Pea(gamePanel, 0, 1);	   
		
		Rectangle pRect = new Rectangle(1000, 130 + 0 * 120, 28, 28);
        Zombie z = new NormalZombie(gamePanel, 0);
        z.setMovingStrategy(new ZombieAdvanceStrategy(gamePanel, 0));
        gamePanel.getLaneZombies().get(0).add(z);
        Rectangle zRect = new Rectangle(1000, 109 + 0 * 120, 400, 120);

        z.setHealth(299);
        testItem.attackZombie(z, pRect,zRect,0);
        assertTrue(testItem.attackZombie(z, pRect,zRect,0));      
        
        z.setHealth(300);
        testItem.attackZombie(z, pRect,zRect,0);
        assertFalse(testItem.attackZombie(z, pRect,zRect,0)); 
	}
}
