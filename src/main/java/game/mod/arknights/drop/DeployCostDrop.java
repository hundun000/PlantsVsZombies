package game.mod.arknights.drop;

import java.awt.event.MouseEvent;

import game.GamePanel;
import game.entity.drop.BaseDrop;
import game.entity.drop.DropInstanceParams;
import game.entity.drop.DropModel;

/**
 * 可拾取的部署费用
 * @author hundun
 * Created on 2020/09/22
 */
public class DeployCostDrop extends BaseDrop {

    public static final String NAME = "cost";



    public DeployCostDrop(GamePanel gamePanel, DropModel model, DropInstanceParams params) {
        super(gamePanel, model, params);
        // TODO Auto-generated constructor stub
    }

    

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }



    @Override
    protected void charge() {
        // TODO Auto-generated method stub
        
    }

    

}
