import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;
import javax.swing.JLabel;
import org.junit.Test;

public class FactoryTest {
	
	/*
	 * Purpose: 
	 * Check if functions of factory strategy class operate correctly
	 * Input: sunProduct.getname()
	 * Input: creator.createProduct("sun")
	 * Input: creator.createProduct("moon")
	 * Expected: 
	 * 			sunProduct.getname() = "sun"
	 * 			creator.createProduct("sun")'s return instance 
	 * 			= new SunProduct()
	 * 			creator.createProduct("moon")'s return instance 
	 * 			= null   
	 */
	

	@Test
	public void Factorytest() {
		ClickScoreCreator creator = new ClickScoreCreator();
		Product sunProduct = new SunProduct();	
		
		String result_0 = sunProduct.getname(); 
		assertEquals(result_0, "sun");
		
		JLabel sun = new JLabel("SUN");
		GamePanel gp= new GamePanel(sun);
		Sun sun0 = new Sun(gp);
		
		sunProduct.setscore(gp, sun0);
		
		Product result_1 = creator.createProduct("sun");			
		assertEquals(sunProduct.getClass().isInstance(result_1), true);
		
		result_1 = creator.createProduct("moon");
		assertEquals(sunProduct.getClass().isInstance(result_1), false);
	}
	
}

