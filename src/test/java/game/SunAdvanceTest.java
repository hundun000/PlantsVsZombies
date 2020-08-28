package game;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.component.PositionComponent;
import game.component.SunPositionComponent;
import game.entity.item.SunItem;

public class SunAdvanceTest {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private SunPositionComponent sunPositionComponent; 
	
	@Before
	public void setUp() {
		gameWindow = GameWindow.fightWindow();
		gamePanel = gameWindow.getGamePanel();
		
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
	    SunItem sun = new SunItem(gamePanel, 10, 10, 50);
		sunPositionComponent = sun.getSunPositionComponent();
		int expectedMyX = 10;
		int expectedMyY = 14;
		int expectedDestruct = 100;
		
		sunPositionComponent.move();
		
		assertEquals(expectedMyX,sunPositionComponent.getPosX());
		assertEquals(expectedMyY,sunPositionComponent.getPosY());
		assertEquals(expectedDestruct, sunPositionComponent.getDestruct());
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
	    SunItem sun = new SunItem(gamePanel, 10, 10, 10);
        sunPositionComponent = sun.getSunPositionComponent();
		int expectedMyX = 10;
		int expectedMyY = 10;
		int expectedDestruct = 99;
		
		sunPositionComponent.move();
		
		assertEquals(expectedMyX,sunPositionComponent.getPosX());
		assertEquals(expectedMyY,sunPositionComponent.getPosY());
		assertEquals(expectedDestruct, sunPositionComponent.getDestruct());
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
	    SunItem sun = new SunItem(gamePanel, 10, 10, 10);
        sunPositionComponent = sun.getSunPositionComponent();
		int expectedMyX = 10;
		int expectedMyY = 10;
		int expectedDestruct = 0;
		
		
		for(int i = 0; i < 100 ; i++) {
			sunPositionComponent.move();
		}
		assertEquals(expectedMyX,sunPositionComponent.getPosX());
		assertEquals(expectedMyY,sunPositionComponent.getPosY());
		assertEquals(expectedDestruct, sunPositionComponent.getDestruct());		
	}
}
