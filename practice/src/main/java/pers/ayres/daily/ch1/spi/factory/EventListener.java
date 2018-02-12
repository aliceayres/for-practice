package pers.ayres.daily.ch1.spi.factory;

import pers.ayres.daily.ch1.spi.event.SuccessEvent;

/**
 * Created by Ye on 2018/2/12.
 */

public interface EventListener {
    void onSuccess(SuccessEvent event);
}
