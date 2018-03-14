package pers.ayres.daily.ch2;

import pers.ayres.daily.ch2.constant.OrderStateEnum;
import pers.ayres.daily.ch2.service.OrderService;
import pers.ayres.daily.ch2.service.impl.OrderServiceImpl;

/**
 * Created by Ye on 2018/3/14.
 */

public class Main {
    public static void main(String[] args){
        OrderService service = new OrderServiceImpl();
        service.updateOrderState(1, OrderStateEnum.TO_RECEIVE.getState());
        service.updateOrderState(12, OrderStateEnum.FINISHED.getState());
    }
}
