package game.manager;

import javax.swing.JLayeredPane;
import game.GamePanel;
import game.ILogicFrameListener;

/**
 * @author hundun
 * Created on 2020/09/01
 */
public abstract class BaseManager extends JLayeredPane implements ILogicFrameListener {
    protected final GamePanel gamePanel;

    public BaseManager(GamePanel gamePanel) {
        super();
        setSize(GamePanel.SCREEN_WIDTH_CONSTANT, GamePanel.SCREEN_HEIGHT_CONSTANT);
        setLayout(null);
        this.gamePanel = gamePanel;
        initChild();
    }

    
    protected void initChild() {;
    
    }
}
