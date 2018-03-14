package pers.ayres.daily.ch2.service.impl;

import pers.ayres.daily.ch2.order.OrderStateContext;
import pers.ayres.daily.ch2.service.OrderService;

/**
 * Created by Ye on 2018/3/14.
 */

public class OrderServiceImpl implements OrderService{
    private OrderStateContext context = new OrderStateContext();

    @Override
    public void updateOrderState(long id, int state) {
        System.out.println("Do update order state.");
        context.setContextByState(state);
        context.afterUpdateOrderState(id);
        System.out.println("End update order state.");
    }
}
