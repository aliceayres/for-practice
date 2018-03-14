package pers.ayres.daily.ch2.order;

import com.google.common.collect.Maps;
import pers.ayres.daily.ch2.SpiLoader;
import pers.ayres.daily.ch2.constant.OrderStateEnum;

import java.util.Map;

/**
 * Created by Ye on 2018/3/14.
 */

public class StateHandlerFactory {
    private static Map<String,StateHandler> cache = Maps.newConcurrentMap();

    public static StateHandler getStateHandler(int state){
        String key = OrderStateEnum.getHandlerKey(state);
        StateHandler handler = cache.get(key);
        if(null == handler){
            handler = SpiLoader.load(StateHandler.class,key);
            cache.put(key,handler);
        }
        return handler;
    }
}
