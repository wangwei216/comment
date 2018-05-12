package org.imooc.service;

import org.imooc.dto.GroupDto;

import java.util.List;

public interface GroupService {

    /*
    * 获取用户的列表
    * */
    List<GroupDto> getList();

    /*
    * 新增用户
    * */
    Boolean add(GroupDto groupDto);

    /*
    * 通过用户主键来去查询菜单功能
    * */
    GroupDto getByIdWithMenuAction(Long id);

    /*
    * 通过id去删除用户组实体类
    * */
    boolean remove(Long id);

    /*
    * 根据主键获取用户组
    * */
    GroupDto getById(Long id);

    /*
    * 为用户组分配可以访问的菜单
    * */
    boolean assignMenu(GroupDto groupDto);

    /*
    * 修改
    * */
    boolean modify(GroupDto groupDto);

}
