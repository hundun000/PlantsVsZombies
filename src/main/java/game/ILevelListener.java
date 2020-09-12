package game;

import game.level.GameLevel;

/**
 * @author hundun
 * Created on 2020/09/12
 */
public interface ILevelListener {
    void levelStart(GameLevel level);
    void levelEnd();
}
