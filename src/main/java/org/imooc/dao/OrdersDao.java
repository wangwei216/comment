package org.imooc.dao;

import org.imooc.bean.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {

    /*
    * 这个是添加订单信息
    * */
    int insert(Orders orders);

    /*
    * 查询订单信息实体信息所有
    * */
    List<Orders> select(Orders orders);

    /*
    * 这个是根据订单主键去查询订单实体对象
    * */
    Orders selectById(long id);
    /*
    * 这个是修改订单信息
    * */
    int update(Orders orders);

    /*
    * 查询所有的用户信息
    * */
    List<Orders> selectAll();

}
