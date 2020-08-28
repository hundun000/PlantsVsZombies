package game;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import game.entity.zombie.BaseZombie.ZombieType;

/**
 * @author Vaibhav Singh Sikarwar
 */
public class LevelData {

    public static String LEVEL_NUMBER = "1";
    public static ZombieType[][] LEVEL_CONTENT = {
            {ZombieType.NORMAL_ZOMBIE},
            {ZombieType.NORMAL_ZOMBIE, ZombieType.CONEHEAD_ZOMBIE}
            };
    public static int[][][] LEVEL_VALUE = {{{0, 99}}, {{0, 49}, {50, 99}}};

    public LevelData() {
        LEVEL_NUMBER = "1";
    }

}
