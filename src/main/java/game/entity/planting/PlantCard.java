package game.entity.planting;
import javax.swing.*;

import game.GamePanel;
import game.GameWindow.PlantType;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/28/2016.
 */
public class PlantCard extends JLabel implements MouseListener {

    private GamePanel gamePanel;
    private PlantType plantType;
    
    public PlantCard(GamePanel gamePanel, Image image, int x, int y, PlantType plantType) {
        super();
        this.gamePanel = gamePanel;
        ImageIcon imageIcon = new ImageIcon(image);
        setIcon(imageIcon);
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        setLocation(x, y);
        this.plantType = plantType;
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gamePanel.getPlantCardManager().setActivePlantingBrush(plantType);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
