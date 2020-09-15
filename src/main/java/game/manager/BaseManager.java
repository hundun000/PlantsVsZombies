package game.manager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GamePanel;
import game.ILogicFrameListener;

/**
 * @author hundun
 * Created on 2020/09/01
 */
public abstract class BaseManager extends JComponent implements ILogicFrameListener {
    static Logger logger = LoggerFactory.getLogger(BaseManager.class);
    
    protected final GamePanel gamePanel;
    Image boardImage;
    int width;
    int height;
    int positionStartX;
    int positionStartY;
    protected Color boardColor = Color.BLACK;
    
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
            g.drawImage(boardImage, 0, 0, null);
        }
        g.setColor(boardColor);
        g.drawRect(0, 0, width - 1, height - 1);
        int r = 4;
        g.fillOval(0 - r, 0 - r, r * 2, r * 2);
        
    }
    
    
}
