package org.imooc.dao;

import org.imooc.bean.User;

import java.util.List;

public interface UserDao {


    //这个是查询系统用户管理员的信息，为了来进行登录
    List<User> select(User user);

}
