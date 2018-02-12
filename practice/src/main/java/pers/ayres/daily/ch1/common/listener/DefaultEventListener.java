package pers.ayres.daily.ch1.common.listener;

import pers.ayres.daily.ch1.spi.Spi;
import pers.ayres.daily.ch1.spi.event.SuccessEvent;
import pers.ayres.daily.ch1.spi.factory.EventListener;
import pers.ayres.daily.ch1.spi.factory.EventListenerFactory;

/**
 * Created by Ye on 2018/2/11.
 */

@Spi(order = 1)
public final class DefaultEventListener implements EventListener, EventListenerFactory{
    @Override
    public EventListener get() {
        return this;
    }

    @Override
    public void onSuccess(SuccessEvent event) {
        System.out.println("success");
    }

}
