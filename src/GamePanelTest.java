import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;
import javax.swing.JLabel;
import org.junit.Test;

public class GamePanelTest {
	GamePanel gp;
	
	/*
	 * Purpose: Check if getSunScore operate correctly
	 * Input: gp.getSunScore()
	 * Expected: 
	 * 			gp.getSunScore() = 10 
	 */
	
	@Test
	public void testgetSunScore() {
		 JLabel sun = new JLabel("SUN");
		 gp = new GamePanel(sun);
		 gp.setSunScore(10);
		 
		 int result = gp.getSunScore();
		 assertEquals(10, result);
	 
	 }
	 
	/*
	 * Purpose: Check if setSunScore operate correctly
	 * Input: gp.getsunScoreboard().getText()
	 * Expected: 
	 * 			gp.getsunScoreboard().getText() = "10"
	 */
	
	@Test
	public void testsetSunScore() {
		JLabel sun = new JLabel("SUN");
		 gp = new GamePanel(sun);
		 gp.setSunScore(10);
		 
		 String result = gp.getsunScoreboard().getText();
		 assertEquals("10", result);
	 }
	
	/*
	 * Purpose: Check if getRow operate correctly
	 * Input: gp.getRow(90)
	 * Expected: 
	 * 			gp.getRow(90) = 10
	 */
	
	@Test
	 public void TestgetRow() {
		 JLabel sun = new JLabel("SUN");
		 gp = new GamePanel(sun);
		 
		 int result = gp.getRow(90); 
		 assertEquals(10, result);
		 
	 }
	
	/*
	 * Purpose: Check if getRow operate correctly
	 * Input: gp.getColumn(90)
	 * Expected: 
	 * 			gp.getColumn(90) = 0
	 */
	
	@Test
	public void TestgetColumn() {
		 JLabel sun = new JLabel("SUN");
		 gp = new GamePanel(sun);
		 
		 int result = gp.getColumn(90); 
		 assertEquals(0, result);
	 }   
	
}
