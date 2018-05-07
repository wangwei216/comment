package org.imooc.service;

import org.imooc.dto.OrdersDto;

import java.util.List;

public interface OrdersService {

    //这个是添加订单信息
    Boolean add(OrdersDto ordersDto);

    //通过订单id查询拿到订单信息
    OrdersDto getById(long id);

    //这个是根据用户的手机号码的去查询订单信息集合
    List<OrdersDto> getListByMemberId(long memberId);


}
