package game.manager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import game.GamePanel;
import game.ILogicFrameListener;

/**
 * @author hundun
 * Created on 2020/09/01
 */
public abstract class BaseManager extends JComponent implements ILogicFrameListener {
    protected final GamePanel gamePanel;
    Image boardImage;
    int width;
    int height;
    int positionStartX;
    int positionStartY;
    public BaseManager(GamePanel gamePanel, int x, int y, int width, int height, int positionStartX, int positionStartY) {
        super();
        setLocation(x, y);
        setSize(width, height);
        // setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        this.width = width;
        this.height = height;
        this.gamePanel = gamePanel;
        this.positionStartX = positionStartX;
        this.positionStartY = positionStartY;
        initChild();
        
    }

    
    protected void initChild() {;
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (boardImage != null) {
            g.drawImage(boardImage, -positionStartX, -positionStartY, null);
        }
        g.drawRect(-positionStartX, -positionStartY, width, height);
    }
    
    
}
