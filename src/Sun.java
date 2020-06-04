import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/27/2016.
 */
public class Sun extends JPanel implements MouseListener {

    private GamePanel gp;
    private Image sunImage;
    private int myX;
    private int myY;
    private int endY;

    private SunMovingStrategy sunMovingStrategy;

    public Sun(GamePanel parent) {
        this.gp = parent;
        setSize(80, 80);
        setOpaque(false);
        sunImage = new ImageIcon(this.getClass().getResource("images/sun.png")).getImage();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sunImage, 0, 0, null);
    }

    public void advance() {
        sunMovingStrategy.move();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gp.setSunScore(gp.getSunScore() + 25);
        gp.remove(this);
        gp.getActiveSuns().remove(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setMovingStrategy(SunMovingStrategy movingStrategy) {
        this.sunMovingStrategy = movingStrategy;
    }

    public void deleteSun() {
        gp.remove(this);
        gp.getActiveSuns().remove(this);
    }
}
