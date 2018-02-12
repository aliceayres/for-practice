package pers.ayres.daily.ch1.common.manager;

import pers.ayres.daily.ch1.spi.event.SuccessEvent;
import pers.ayres.daily.ch1.spi.factory.EventListener;
import pers.ayres.daily.ch1.spi.manager.LogicManager;
import pers.ayres.daily.ch1.spi.factory.EventListenerFactory;

/**
 * Created by Ye on 2018/2/12.
 */

public class DefaultLogicManager implements LogicManager{
    private EventListener eventListener;

    public void init(){
        if (eventListener == null){
            eventListener = EventListenerFactory.create();
        }
    }

    public void destory() {

    }

    public void doSome(){
        System.out.println("start");
        eventListener.onSuccess(new SuccessEvent());
    }
}
