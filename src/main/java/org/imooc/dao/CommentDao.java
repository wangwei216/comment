package org.imooc.dao;

import org.imooc.bean.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {

    /*
    *根据查询条件分页查询评论列表
    * */
    List<Comment> selectByPage(Comment comment);

}
