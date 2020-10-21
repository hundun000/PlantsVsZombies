package game.mod.pvz.drop;

import game.entity.drop.BaseDrop;
import game.entity.drop.DropInstanceParams;
import game.entity.drop.DropModel;
import game.manager.SunScoreManager.BasicWealth;
import game.ui.GamePanel;

/**
 * @author hundun
 * Created on 2020/09/26
 */
public class CoinDrop extends BaseDrop {

    public static String NAME = "coin";
    
    public CoinDrop(GamePanel gamePanel, DropModel model, DropInstanceParams params) {
        super(gamePanel, model, params);
    }

    @Override
    protected void charge() {
        gamePanel.getSunScoreManager().addWealth(BasicWealth.COIN_POINT, getChargePoint());
        this.getHealthComponent().forceKilled();
    }

}
