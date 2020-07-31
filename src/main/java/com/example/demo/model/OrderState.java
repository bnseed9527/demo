package com.example.demo.model;

/**
 * @author: YsCy丶
 * @Date: 2020/7/30 19:54
 */
public abstract class OrderState {
    /**
     * 订单初始化
    */
    public static final Integer INIT = 1;
    /**
     * 支付
     */
    public static final Integer PAID = 2;
    /**
     * 酿造，制作
     */
    public static final Integer BREWING =3;
    /**
     * 酿造，制作完成
     */
    public static final Integer BREWED = 4;
    /**
     * 拿
     */
    public static final Integer TAKEN = 5;
    /**
     * 取消
     */
    public static final Integer CANCELLED = 6;
}
