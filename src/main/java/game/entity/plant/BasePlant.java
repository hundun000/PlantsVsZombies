package game.entity.plant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.entity.bullet.BaseBullet;
import game.entity.bullet.BulletInstanceParams;
import game.entity.component.FightComponent;
import game.entity.component.HealthComponent;
import game.entity.component.IdlePositionComponent;
import game.entity.component.PositionComponent;
import game.entity.drop.BaseDrop;
import game.entity.drop.DropInstanceParams;
import game.entity.gameobject.FightData;
import game.entity.gameobject.FightObject;
import game.entity.gameobject.GameObject;
import game.entity.zombie.BaseZombie;
import game.manager.GridManager;
import game.ui.GamePanel;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class BasePlant extends FightObject {
    private static Logger logger = LoggerFactory.getLogger(BasePlant.class);
    
    protected PositionComponent positionComponent;
    protected HealthComponent healthComponent;
    protected PlantModel model;
    protected FightComponent fightComponent;
    
    
    /**
     * 子类通过Factory构造，可能使用反射。
     */
    public BasePlant(GamePanel gamePanel, PlantModel model, PlantInstanceParams params) {
        super(gamePanel, model.registerName, model.fightData, FightSide.PLAYER);
        super.spirit = model.spirit;
        this.positionComponent = new IdlePositionComponent(gamePanel, model, params);
        this.model = model;
        this.fightComponent = new FightComponent(this, model, params);
        this.healthComponent = new HealthComponent(this, model, params);
    }
   
    
    
    @Override
    public PositionComponent getPositionComponent() {
        return positionComponent;
    }
    
    @Override
    public FightComponent getFightComponent() {
        return fightComponent;
    }
    
    
    

    
    
    /**
     * PVZ 中的标准“技能”：掉落阳光/硬币
     */
    protected void useSkillByGenerateDrop() {
        BaseDrop drop = generateDrop();
        if (drop != null) {
            gamePanel.getGridManager().addDrop(drop);
        }
    }

    
    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        
    }
    
    @Override
    protected boolean wantMove() {
        return false;
    }
    
    
    public PlantModel getModel() {
        return model;
    }

    protected BaseDrop generateDrop() {
        if (this.model.fightData.dropRegisterName != null) {
            int startX = getPositionComponent().getPosX() + fightData.dropStartOffsetX;
            int startY = getPositionComponent().getPosY() + fightData.dropStartOffsetY;
            int endY = startY + (GridManager.GRID_HEIGHT / 2);
            DropInstanceParams params = new DropInstanceParams(startX, startY, endY);
            BaseDrop drop = gamePanel.getDropFactory().getInstacne(model.fightData.dropRegisterName, gamePanel, params);
            return drop;
        } else {
            return null;
        }
    }

    
    
    @Override
    public HealthComponent getHealthComponent() {
        return healthComponent;
    }

    
}
