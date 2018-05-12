package org.imooc.dao;

import org.imooc.bean.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    * 这个是用户组的，也就是对用户进行分类，分为管理、业务、系统，给用户不同的职务分配
    * */
@Repository
public interface GroupDao {

        //根据查询条件查询用户组列表
        List<Group> select (Group group);

        //新增用户
        int insert(Group group);

        //通过用户的主键来获取菜单的列表
        Group selectMenuListById(long id);

        //根据主键获取用户组实体
        Group selectById(Long id);

        //修改
        int update(Group group);

        //删除
        int delete(Long id);
}
