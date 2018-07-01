package org.imooc.dao;

import org.imooc.bean.Ad;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdDao {

    /*
    * 插入广告
    * */
    int insert(Ad ad);

    /*
     * 从数据库中查询广告信息列表
     * */
    List<Ad> selectAll(Ad ad);
    /*
    *
    * 根据主键删除广告列表中的数据
    * */
    int delete(long id);
    /*
    * 根据主键查询广告实体，因为你要删除的话，必须要先查询删除那个
    * */
    Ad selectById(Long id);
    /*
    * 根据主键先查询然后再去修改广告实体类
    * */
    int update(Ad ad);

    /*
    * 根据标题名查询
    * */
    List<Ad> selectByTitle(Ad ad);

}
