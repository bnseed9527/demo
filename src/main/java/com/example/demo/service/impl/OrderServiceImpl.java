package com.example.demo.service.impl;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Coffee;
import com.example.demo.model.CoffeeOrder;
import com.example.demo.model.OrderState;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: YsCy丶
 * @Date: 2020/7/30 20:07
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public CoffeeOrder createOrder(String customer, Coffee... coffees) {

        CoffeeOrder order = new CoffeeOrder()
                .withCustomer(customer)
                .withItems(new ArrayList<>(Arrays.asList(coffees)))
                .withState(OrderState.INIT);
        orderMapper.insert(order);
        log.info("Insert CoffeeOrder:{}",order);
        return order;
    }

    @Override
    public boolean updateState(CoffeeOrder order, Integer orderState) {

        if ( orderState < order.getState()){
            log.warn("Wrong State order: {}, {}", orderState, order.getState());
            return  false;
        }
        order.setState(orderState);
        orderMapper.updateByPrimaryKey(order);
        log.info("Updated CoffeeOrder: {}", order);
        return true;
    }
}
