
public class ClickScoreCreator extends ScoreCreator{

	@Override
	public Product createProduct(String name) {
		switch (name) {
        case "sun": return new SunProduct(); 
        //case "moon": return new moonProduct();
        
        }
		return null;
	}

}
