package pers.ayres.daily.ch2.service;

import pers.ayres.daily.ch2.domain.Order;

/**
 * Created by Ye on 2019/11/15.
 */

public interface OrderQueryService {
    Order queryById(long id);
}
