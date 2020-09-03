package game.planting;
import javax.swing.*;

import game.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/28/2016.
 */
public class PlantCard extends JLabel implements MouseListener {

    private GamePanel gamePanel;
    private String plantRegisterName;
    private Image image;
    public PlantCard(GamePanel gamePanel, Image image, int x, int y, String plantRegisterName) {
        super();
        this.gamePanel = gamePanel;
        ImageIcon imageIcon = new ImageIcon(image);
        setIcon(imageIcon);
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        setLocation(x, y);
        this.plantRegisterName = plantRegisterName;
        addMouseListener(this);
        this.image = image;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        gamePanel.getGridManager().setPlanting(plantRegisterName);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
