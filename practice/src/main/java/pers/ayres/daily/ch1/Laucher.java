package pers.ayres.daily.ch1;

import pers.ayres.daily.ch1.spi.SpiLoader;
import pers.ayres.daily.ch1.spi.manager.LogicManager;

/**
 * Created by Ye on 2018/2/12.
 */

public class Laucher {
    public static void main(String[] args){
        LogicManager manager = SpiLoader.load(LogicManager.class);
        manager.init();
        manager.doSome();
    }
}
