package game.manager;

import game.level.GameLevel;
import game.ui.GamePanel;
import game.ui.GamePanel.GameState;


/**
 * @author hundun
 * Created on 2020/09/12
 */
public interface ILevelListener {
    void updateGameState(GameState gameState);
    void levelStart(GameLevel level);
    void levelEnd();
}
