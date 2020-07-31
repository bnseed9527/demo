package com.example.demo.converter;

import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * @author: YsCy丶
 * @Date: 2020/7/31 18:29
 */
public class MoneyReadConverter implements Converter<Document,Money> {

    /**
     * MongoDB取到文档后会做一个转换
     * @param document
     * @return
     */
    @Override
    public Money convert(Document document) {
        Document money = (Document) document.get("money");
        double amount = Double.parseDouble(money.getString("amount"));
        String currency = ((Document) money.get("currency")).getString("code");
        return Money.of(CurrencyUnit.of(currency),amount);
    }
}
