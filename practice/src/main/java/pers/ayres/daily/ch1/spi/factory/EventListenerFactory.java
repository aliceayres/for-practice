package pers.ayres.daily.ch1.spi.factory;

import pers.ayres.daily.ch1.spi.SpiLoader;

import java.util.function.Supplier;

/**
 * Created by Ye on 2018/2/12.
 */

public interface EventListenerFactory extends Supplier<EventListener> {
    static EventListener create(){
        return SpiLoader.load(EventListenerFactory.class).get();
    }
}
