package com.example.demo.service;

import com.example.demo.model.Coffee;

import java.util.List;

/**
 * @author: YsCy丶
 * @Date: 2020/7/30 20:04
 */
public interface CoffeeService {

   List<Coffee> findOneCoffeeByName(String name);
}
