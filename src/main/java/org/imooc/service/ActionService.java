package org.imooc.service;

import org.imooc.dto.ActionDto;

public interface ActionService {

    /*
    * 是否根据用户行为主键删除实体成功
    * */
    Boolean remove(Long id);

    /*
    * 添加用户事件
    * */
    Boolean add(ActionDto actionDto);

    /*
    * 修改
    * */
    Boolean modify(ActionDto actionDto);

    /*
    *通过获取用户实体类的事件
    * */
    ActionDto getById(Long id);
}
