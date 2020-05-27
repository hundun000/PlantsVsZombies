import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class MessageDialog extends JLayeredPane {
	
	private GamePanel gp;
	
	public MessageDialog(GamePanel parent) {
        this.gp = parent;
    }
	
	public void levelUpDialog() {
		JOptionPane.showMessageDialog(null, "LEVEL_CONTENT Completed !!!" + '\n' + "Starting next LEVEL_CONTENT");
	}
	public void gameClearDialog() {
		JOptionPane.showMessageDialog(null, "LEVEL_CONTENT Completed !!!" + '\n' + "More Levels will come soon !!!" + '\n' + "Resetting data");  	
	}
	public void gameOverDialog() {
		JOptionPane.showMessageDialog(gp, "ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
	}
	
}
