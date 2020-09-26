package game.mod.pvz.drop;
import game.GamePanel;
import game.ILogicFrameListener;
import game.entity.component.DropPositionComponent;
import game.entity.component.FightComponent;
import game.entity.component.PositionComponent;
import game.entity.drop.BaseDrop;
import game.entity.drop.DropInstanceParams;
import game.entity.drop.DropModel;
import game.entity.gameobject.GameObject;
import game.manager.GridManager;
import game.manager.SunScoreManager.BasicWealth;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Created by Armin on 6/27/2016.
 */
public class SunItem extends BaseDrop {
    public static String NAME = "sun";
    
    public SunItem(GamePanel gamePanel, DropModel model, DropInstanceParams params) {
        super(gamePanel, model, params);
    }

    @Override
    protected void charge() {
        gamePanel.getSunScoreManager().addWealth(BasicWealth.SUN_POINT, getChargePoint());
        this.getHealthComponent().forceKilled();
    }
    

    


}
