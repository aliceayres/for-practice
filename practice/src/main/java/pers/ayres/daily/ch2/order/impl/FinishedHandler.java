package pers.ayres.daily.ch2.order.impl;

import pers.ayres.daily.ch1.spi.Spi;
import pers.ayres.daily.ch2.order.StateHandler;

/**
 * Created by Ye on 2018/3/14.
 */

@Spi("finished")
public class FinishedHandler implements StateHandler {

    @Override
    public void handleStateChanged(long id) {
        System.out.println(id+" handle FinishedHandler state.");
    }
}