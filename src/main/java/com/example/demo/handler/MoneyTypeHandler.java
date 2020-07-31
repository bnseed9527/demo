package com.example.demo.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: YsCy丶
 * @Date: 2020/7/30 14:08
 * @Descrition 在Money与Long之间转换的TypeHandler 处理 CNY 人民币
 */
public class MoneyTypeHandler extends BaseTypeHandler<Money>{

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money money, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i,money.getAmountMinorLong());
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parseMoney(resultSet.getLong(s));
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parseMoney(resultSet.getLong(i));
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseMoney(callableStatement.getLong(i));
    }
    private Money parseMoney(Long value){

        return Money.of(CurrencyUnit.of("CNY"),value / 100.0);
    }
}
