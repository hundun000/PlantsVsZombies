package game.entity;

import game.GamePanel;
import game.ILogicFrameListener;
import game.component.PositionComponent;

/**
 * @author hundun
 * Created on 2020/08/29
 */
public abstract class GameObject implements ILogicFrameListener {
    protected String registerName;
    
    protected final GamePanel gamePanel;
    
    public GameObject(GamePanel gamePanel, String registerName) {
        super();
        this.gamePanel = gamePanel;
        this.registerName = registerName;
    }

    public String getRegisterName() {
        return registerName;
    }
    
    public abstract PositionComponent getPositionComponent();
    
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    
    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

}
