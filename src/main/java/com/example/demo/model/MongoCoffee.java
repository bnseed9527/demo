package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author: YsCy丶
 * @Date: 2020/7/31 18:23
 * @description 为使用MongoDB创建的model
 */
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MongoCoffee {

    //spring-data-mongo会把此String类型转换成MongoDB中的ObjectId
    @Id
    private String id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
