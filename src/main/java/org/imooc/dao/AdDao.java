package org.imooc.dao;

import org.imooc.bean.Ad;
import org.springframework.stereotype.Repository;

@Repository
public interface AdDao {

    /*
    * 插入广告
    * */
    int insert(Ad ad);
}
