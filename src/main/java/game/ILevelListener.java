package game;

import game.GamePanel.GameState;
import game.level.GameLevel;


/**
 * @author hundun
 * Created on 2020/09/12
 */
public interface ILevelListener {
    void updateGameState(GameState gameState);
    void levelStart(GameLevel level);
    void levelEnd();
}
