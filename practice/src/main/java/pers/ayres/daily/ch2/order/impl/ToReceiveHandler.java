package pers.ayres.daily.ch2.order.impl;

import pers.ayres.daily.ch1.spi.Spi;
import pers.ayres.daily.ch2.domain.Order;
import pers.ayres.daily.ch2.order.StateHandler;
import pers.ayres.daily.ch2.service.OrderQueryService;
import pers.ayres.daily.ch2.service.impl.OrderQueryServiceImpl;

/**
 * Created by Ye on 2018/3/14.
 */
@Spi("toReceive")
public class ToReceiveHandler implements StateHandler{

    private OrderQueryService orderQueryService = new OrderQueryServiceImpl(); //测试结构，不使用Spring注入

    @Override
    public void handleStateChanged(long id) {
        Order order = orderQueryService.queryById(id);
        String no = order == null?"":order.getOrderNo();
        System.out.println(id+":"+ no +" handle ToReceiveHandler state.");
    }
}
