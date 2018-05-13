package org.imooc.dao;

import org.imooc.bean.Action;
import org.springframework.stereotype.Repository;

    /*
    * 这个是执行的动作
    * */
@Repository
public interface ActionDao {

    /*
    *根据菜单ID来去删除动作的
    * */
    int deleteByMenuId(Long id);

    //根据动作实体主键来去删除该实体
    int deleteById(Long id);

    /*
    * 新增action实体类，其实也就是新增一个动作事件
    * */
    int insert(Action action);

    /*
    * 修改action实体类
    * */
    int update(Action action);

    /*
    * 根据主键获取动作实体
    * */
    Action selectById(Long id);

}
