package org.imooc.service.serviceImpl;

import org.imooc.bean.Business;
import org.imooc.bean.Comment;
import org.imooc.bean.Orders;
import org.imooc.bean.Page;
import org.imooc.dao.CommentDao;
import org.imooc.dto.CommentDto;
import org.imooc.dto.CommentListDto;
import org.imooc.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    /*
    * 1.首先是先定义返回结果类型
    * 2.然后是组合查询条件,先从评论表去设置订单表的信息再从订单表去设置商户的信息，最后再去通过商户表去set商户的id
    * 3.因为你需要根据Dao穿进去的参数才能从数据库中查询到数据，所以你的目的就是吧数据都放到那个DAO层需要用到的参数中
    *
    * */
    @Override
    public CommentListDto getListByBusinessId(int businessId, Page page) {

        CommentListDto result = new CommentListDto();
        Comment comment = new Comment();
        Orders orders = new Orders();
        Business business = new Business();
        //这几句代码的目的就是把从参数里面传进来的商户id给set进去
        comment.setOrders(orders);
        orders.setBusiness(business);
        business.setId(businessId);
        //这里是用到了分页对象
        page.setCurrentPage(page.getCurrentPage()+1);
        //因为这里需要用到的是
        comment.setPage(page);
        List<Comment> commentsList = commentDao.selectByPage(comment);
        //然后准备返回值，而且返回的是评论实体的数据传输DTO
        List<CommentDto> data = new ArrayList<CommentDto>();
        //然后把这个
        result.setData(data);
        //对拿到的数据进行遍历，然后都给放到
        for (Comment commentTemp : commentsList) {
            CommentDto commentDto = new CommentDto();
            data.add(commentDto);
            BeanUtils.copyProperties(commentTemp,commentDto);

            // 隐藏手机号中间4位
            StringBuffer phoneBuffer = new StringBuffer(String.valueOf(commentTemp.getOrders().getMember().getPhone()));
            commentDto.setUsername(phoneBuffer.replace(3, 7, "****").toString());
        }

        result.setHasMore(page.getCurrentPage() < page.getTotalPage());
        return result;
    }
}
