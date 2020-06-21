import static org.junit.Assert.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlnatActionListenerTest {
	private GamePanel.PlantActionListener testItem;
	private GamePanel gamePanel;
	
	@Before
	public void setUp() throws Exception {}
	@After
	public void tearDown() throws Exception { testItem = null; }
	/*
	 * Purpose: test creating Planting object 
	 * Input: activePlantingBrush <- GameWindow.PlantType.Sunflower
	 * Expected: 
	 * 			planting != null
	 */
	@Test
	public void testPlanting_Sunflower() {
		gamePanel = new GamePanel(new JLabel("SUN"));
		gamePanel.setActivePlantingBrush(GameWindow.PlantType.Sunflower);
		testItem = gamePanel.getPlantActionListener(1,1);
		testItem.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
		
		assertNotNull(testItem.planting);
	}
	
	/*
	 * Purpose: test creating Planting object 
	 * Input: activePlantingBrush <- GameWindow.PlantType.Peashooter
	 * Expected: 
	 * 			planting != null
	 */
	@Test
	public void testPlanting_Peashooter() {
		gamePanel = new GamePanel(new JLabel("SUN"));
		gamePanel.setActivePlantingBrush(GameWindow.PlantType.Peashooter);
		testItem = gamePanel.getPlantActionListener(1,1);
		testItem.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
		
		assertNotNull(testItem.planting);
	}
	
	/*
	 * Purpose: test creating Planting object 
	 * Input: activePlantingBrush <- GameWindow.PlantType.FreezePeashooter
	 * Expected: 
	 * 			planting != null
	 */
	@Test
	public void testPlanting_FreezePeashooter() {
		gamePanel = new GamePanel(new JLabel("SUN"));
		gamePanel.setActivePlantingBrush(GameWindow.PlantType.FreezePeashooter);
		testItem = gamePanel.getPlantActionListener(1,1);
		testItem.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
		
		assertNotNull(testItem.planting);
	}
}
