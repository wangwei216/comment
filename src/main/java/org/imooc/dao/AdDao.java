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
    List<Ad> selectByPage(Ad ad);
}
