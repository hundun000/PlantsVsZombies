import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;
import javax.swing.JLabel;
import org.junit.Test;



public class SingletonTest {

	/*
	 * Purpose: test creating Singleton object 
	 * Input: Singleton.coliderInstance(), Singleton.sunInstance()
	 * Expected: 
	 * 			Singleton.coliderInstance() = new Integer(0)
	 * 			Singleton.sunInstance() = new Integer(1)
	 */
	
	@Test
	public void Singletontest() {
		
		Integer result = Singleton.coliderInstance();
		assertEquals(new Integer(0), result);
		
		result = Singleton.sunInstance();
		assertEquals(new Integer(1), result);
	}
		
}


