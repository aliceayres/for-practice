package pers.ayres.daily.ch2.order;

/**
 * Created by Ye on 2018/3/14.
 */

public class OrderStateContext {
    private final ThreadLocal<StateHandler> handler = new ThreadLocal<>();

    public void setContextByState(int state){
        StateHandler hd = StateHandlerFactory.getStateHandler(state);
        handler.set(hd);
    }

    public void afterUpdateOrderState(long id){
        handler.get().handleStateChanged(id);
    }

}
