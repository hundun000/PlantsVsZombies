import javax.swing.*;

import java.awt.Image;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class GameWindow extends JFrame {

	private GamePanel gamePanel;
	
    enum PlantType {
        None,
        Sunflower,
        Peashooter,
        FreezePeashooter
    }

    public GameWindow() {
        setSize(1012, 785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel sun = setSun();

        setGamePanel(sun);

        String sunflowerImage = "images/cards/card_sunflower.png";
        setPlantCard(sunflowerImage, 110, 8, PlantType.Sunflower);

        String peashooterImage = "images/cards/card_peashooter.png";
        setPlantCard(peashooterImage, 175, 8, PlantType.Peashooter);

        String freezepeashooterImage = "images/cards/card_freezepeashooter.png";
        setPlantCard(freezepeashooterImage, 240, 8, PlantType.FreezePeashooter);
        
        setResizable(false);
        setVisible(true);
    }
    
    private void setPlantCard(String imageResource, int x, int y, PlantType plantType) {
    	Image image = new ImageIcon(this.getClass().getResource(imageResource)).getImage();
    	PlantCard plantCard = new PlantCard(image);
        plantCard.setLocation(x, y);
        plantCard.setAction((ActionEvent e) -> {
            gamePanel.setActivePlantingBrush(plantType);
        });
        getLayeredPane().add(plantCard, new Integer(3));
    }

	private void setGamePanel(JLabel sun) {
		gamePanel = new GamePanel(sun);
        gamePanel.setLocation(0, 0);
        getLayeredPane().add(gamePanel, new Integer(0));
	}

	private JLabel setSun() {
		JLabel sun = new JLabel("SUN");
        sun.setLocation(37, 80);
        sun.setSize(60, 20);
        getLayeredPane().add(sun, new Integer(2));
		return sun;
	}

    public GameWindow(boolean b) {
        Menu menu = new Menu();
        menu.setLocation(0, 0);
        setSize(1012, 785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getLayeredPane().add(menu, new Integer(0));
        menu.repaint();
        setResizable(false);
        setVisible(true);
    }

    private static GameWindow gameWindow;

    public static void begin() {
        gameWindow.dispose();
        gameWindow = new GameWindow();
    }

    public static void main(String[] args) {
        gameWindow = new GameWindow(true);
    }

}
