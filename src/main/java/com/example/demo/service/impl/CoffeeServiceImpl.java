package com.example.demo.service.impl;

import com.example.demo.mapper.CoffeeMapper;
import com.example.demo.model.Coffee;
import com.example.demo.model.CoffeeExample;
import com.example.demo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: YsCy丶
 * @Date: 2020/7/30 20:06
 */
@Slf4j
@Service
public class CoffeeServiceImpl implements CoffeeService {

    @Autowired
    private CoffeeMapper coffeeMapper;
    @Override
    public List<Coffee> findOneCoffeeByName(String name) {

        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameEqualTo(name.toLowerCase());
        List<Coffee> list = coffeeMapper.selectByExample(example);
        if(list.size() >0 && list != null){
            log.info("Coffee Found:{}",list);
            return list;
        }
        return null;
    }
}
