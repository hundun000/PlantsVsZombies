import com.sun.glass.events.MouseEvent;

public class SunProduct extends Product {
	
	@Override
	public String getname() {
		return "sun";
	}
	
	public void setscore(GamePanel gp, Sun sun) {
		gp.setSunScore(gp.getSunScore() + 25);
		gp.remove(sun);
		gp.getActiveSuns().remove(sun);
	}

}
