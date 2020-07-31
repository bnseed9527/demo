package com.example.demo.service;

import com.example.demo.model.Coffee;
import com.example.demo.model.CoffeeOrder;

/**
 * @author: YsCy丶
 * @Date: 2020/7/30 20:04
 */
public interface OrderService {

    CoffeeOrder createOrder(String customer, Coffee ...coffees);

    boolean updateState(CoffeeOrder order, Integer state);
}
