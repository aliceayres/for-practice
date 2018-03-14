package pers.ayres.daily.ch2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ye on 2018/3/14.
 */
@Data
@NoArgsConstructor
public class Order {
    private long id;
    private String orderNo;

    public Order(long id, String orderNo) {
        this.id = id;
        this.orderNo = orderNo;
    }
}
