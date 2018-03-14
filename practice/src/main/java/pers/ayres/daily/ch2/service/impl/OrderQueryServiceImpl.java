package pers.ayres.daily.ch2.service.impl;

import pers.ayres.daily.ch2.domain.Order;
import pers.ayres.daily.ch2.service.OrderQueryService;

/**
 * Created by Ye on 2018/3/14.
 */

public class OrderQueryServiceImpl implements OrderQueryService{
    @Override
    public Order queryById(long id) {
        return new Order(id,"testNo2018031401");
    }
}
