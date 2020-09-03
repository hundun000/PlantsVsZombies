package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import game.GamePanel;
import game.ILogicFrameListener;
import game.ImageManager;
import game.component.PositionComponent;
import game.manager.GridManager;

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
    
    public void drawSelf(Graphics g) {
        Image image = ImageManager.getImage(this.getRegisterName());
        g.drawImage(image, this.getPositionComponent().getPosX(), this.getPositionComponent().getPosY(), null);
        if (GamePanel.DRAW_DEBUG_BOX) {
            Rectangle coillderBox = getPositionComponent().getCoillderBox();
            Color last = g.getColor();
            g.setColor(Color.WHITE);
            g.drawRect((int)coillderBox.getX(), (int)coillderBox.getY(), (int)coillderBox.getWidth(), (int)coillderBox.getHeight());
            g.setColor(last);
        }
    }

}
