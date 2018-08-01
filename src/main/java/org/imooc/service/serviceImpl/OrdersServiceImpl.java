package org.imooc.service.serviceImpl;

import org.imooc.bean.Orders;
import org.imooc.constant.CommentStateConst;
import org.imooc.dao.OrdersDao;
import org.imooc.dto.OrdersDto;
import org.imooc.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersDao ordersDao;
    /* 商户图片访问URL*/
    @Value("${businessImage.url}")
    private String businessImageUrl;
    /*
    * 这个添加就是相当于把从controller层拿到的数据都给初始化
    * */
    @Override
    public Boolean add(OrdersDto ordersDto) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersDto,orders);
        orders.setCommentState(CommentStateConst.NOT_COMMENT);
        ordersDao.insert(orders);
        return true;
    }

    /*通过订单id拿到订单信息*/
    @Override
    public OrdersDto getById(long id) {
        OrdersDto result = new OrdersDto();
        Orders orders = ordersDao.selectById(id);
        BeanUtils.copyProperties(result,orders);
        return result;
    }

    /*通过用户的手机号来获取订单的集合信息包括订单表、商户表
    *   1.创建一个返回结果的集合
    *   2.创建一个用来保存从数据库拿过来的数据
    *
    *   4.把controller层传进来的手机号给set进去，因为你想通过数据库拿到订单信息集合的话，需要穿进去的是一个实体对象，而且这个实体对象还要有值
    *   5.然后去遍历集合,最重要是你需要定一个实体数据传输对象，然后把对象给add最终要返回的结果list集合中
    *   6.后把得到的每一个数据实体对象的都给复制到数据传输对象中
    *   7.在根据业务情况，把最后需要初始化保存的图片地址、标题、count什么的都给set进去,
    *因为你要把前台需要的数据都给装配进去，但是需要的字段名都是可以从其他表中进行拿到，但是需要在实体中添加另一个实体的属性
    * */
    @Override
    public List<OrdersDto> getListByMemberId(long memberId) {
        List<OrdersDto> result = new ArrayList<OrdersDto>();
        Orders ordersForSelect = new Orders();
        //把controller层传进来的手机号给set进去，因为你想通过数据库拿到订单信息集合的话，需要
        ordersForSelect.setMemberId(memberId);
        List<Orders> ordersList = ordersDao.select(ordersForSelect);
        for (Orders orders : ordersList) {
            OrdersDto ordersDto = new OrdersDto();
            result.add(ordersDto);
            //然后把得到的每一个数据实体对象的都给复制到数据传输对象中
            BeanUtils.copyProperties(orders,ordersDto);
            ordersDto.setImg(businessImageUrl+orders.getBusiness().getImgFileName());
            ordersDto.setTitle(orders.getBusiness().getTitle());
            ordersDto.setCount(orders.getBusiness().getNumber());
        }
        return result;
    }


    /*
    * 这个是修改订单信息看是不是修改成功。以便于controller中进行使用
    *   1.先去把数据传输层的实体给复制到你要传到Dao层修改接口的参数实体
    * */
    @Override
    public Boolean modify(OrdersDto ordersDto) {

        Orders ordersForUpdate = new Orders();
        BeanUtils.copyProperties(ordersDto,ordersForUpdate);
        int updateNum = ordersDao.update(ordersForUpdate);
        if (updateNum!=1){
            return false;
        }
        return true;
    }

    //查询所有的订单信息
    @Override
    public List<Orders> getAllList() {
        List<Orders> ordersDaoList = ordersDao.selectAll();
            return ordersDaoList;
    }


}
