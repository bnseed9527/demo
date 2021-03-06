package com.example.demo.mapper;

import com.example.demo.model.CoffeeOrder;
import com.example.demo.model.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

public interface OrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    long countByExample(OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    int deleteByExample(OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    @Delete({
        "delete from t_order",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    @Insert({
        "insert into t_order (create_time, update_time, ",
        "customer, state)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{customer,jdbcType=VARCHAR}, #{state,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(CoffeeOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    int insertSelective(CoffeeOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    List<CoffeeOrder> selectByExampleWithRowbounds(OrderExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    List<CoffeeOrder> selectByExample(OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    @Select({
        "select",
        "id, create_time, update_time, customer, state",
        "from t_order",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.example.demo.mapper.OrderMapper.BaseResultMap")
    CoffeeOrder selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    int updateByExampleSelective(@Param("record") CoffeeOrder record, @Param("example") OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    int updateByExample(@Param("record") CoffeeOrder record, @Param("example") OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    int updateByPrimaryKeySelective(CoffeeOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Thu Jul 30 20:02:06 CST 2020
     */
    @Update({
        "update t_order",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "customer = #{customer,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CoffeeOrder record);
}