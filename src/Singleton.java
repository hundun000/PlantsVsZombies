import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Singleton {
	private static Integer colliderAdd =  new Integer(0);
	private static Integer sunAdd =  new Integer(1);
	
	public static Integer coliderInstance() {
		return colliderAdd;
	}
	
	public static Integer sunInstance() {
		return sunAdd;
	}

}

