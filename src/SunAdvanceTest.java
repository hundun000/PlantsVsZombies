import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SunAdvanceTest {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Sun sun;
	private SunAdvanceStrategy sunAdvanceStrategy; 
	
	@Before
	public void setUp() {
		gameWindow = new GameWindow();
		gamePanel = gameWindow.getGamePanel();
		sun = new Sun(gamePanel);
	}
	
	/*
	 *Purpose: test location of sun using function move when sun is moving down
	 *Input: 
	 *		startX	startY		endY
	 *		10		10	 		50
	 *Expected: 
	 * 		Return 
	 * 			myX = 10
	 * 			myY = 14
	 * 			destruct = 100
	 * 				
	 */
	@Test
	public void testLocation_advance() {
		sunAdvanceStrategy = new SunAdvanceStrategy(gamePanel, sun,10,10,50);
		int expectedMyX = 10;
		int expectedMyY = 14;
		int expectedDestruct = 100;
		
		sunAdvanceStrategy.move();
		
		assertEquals(expectedMyX,sunAdvanceStrategy.getMyX());
		assertEquals(expectedMyY,sunAdvanceStrategy.getMyY());
		assertEquals(expectedDestruct, sunAdvanceStrategy.getDestruct());
	}
	
	/*
	 *Purpose: test location of sun using function move when sun arrives at the bottom
	 *Input: 
	 *		startX	startY		endY
	 *		10		10	 		10
	 *Expected: 
	 * 		Return True
	 * 			myX = 10
	 * 			myY = 10
	 * 			destruct = 99 		
	 */
	@Test
	public void testLocation_arrive() {
		sunAdvanceStrategy = new SunAdvanceStrategy(gamePanel, sun,10,10,10);
		int expectedMyX = 10;
		int expectedMyY = 10;
		int expectedDestruct = 99;
		
		sunAdvanceStrategy.move();
		
		assertEquals(expectedMyX,sunAdvanceStrategy.getMyX());
		assertEquals(expectedMyY,sunAdvanceStrategy.getMyY());
		assertEquals(expectedDestruct, sunAdvanceStrategy.getDestruct());
	}
	
	/*
	 *Purpose: test location of sun using function move when sun arrives at the bottom
	 *		,destruct decrease to zero
	 *Input: 
	 *		startX	startY		endY
	 *		10		10	 		10
	 *Expected: 
	 * 		Return True
	 * 			myX = 10
	 * 			myY = 10
	 * 			destruct = 0		
	 */
	@Test
	public void testLocation_wait() {
		sunAdvanceStrategy = new SunAdvanceStrategy(gamePanel, sun,10,10,10);
		int expectedMyX = 10;
		int expectedMyY = 10;
		int expectedDestruct = 0;
		
		
		for(int i = 0; i < 100 ; i++) {
			sunAdvanceStrategy.move();
		}
		assertEquals(expectedMyX,sunAdvanceStrategy.getMyX());
		assertEquals(expectedMyY,sunAdvanceStrategy.getMyY());
		assertEquals(expectedDestruct, sunAdvanceStrategy.getDestruct());		
	}
}
