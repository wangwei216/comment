package org.imooc.service;

import org.imooc.dto.OrdersDto;

import java.util.List;

public interface OrdersService {

    //这个是添加订单信息
    Boolean add(OrdersDto ordersDto);

    //通过用户id查询拿到订单集合还有其他关联表的订单信息
    OrdersDto getById(long id);

    //这个是根据用户的主键去查询订单信息集合
    List<OrdersDto> getListByMemberId(long memberId);

    //这个是修改订单信息，只要是修改都最好用Boolean类型来作为返回值
    Boolean modify(OrdersDto ordersDto);




}
