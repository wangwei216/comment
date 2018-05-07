package org.imooc.dao;

import org.imooc.bean.Business;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BusinessDao {

    /*
    * 增加商户信息
    * */
    int insert(Business business);
    /*
    * 根据主键查询商户信息
    * */
    Business selectById(long id);
    /*
    * 通过查询条件分页查询商户列表
    * */
    List<Business> selectByPage(Business business);
    /*
     *  根据查询条件分页查询商户列表 :
     *  标题、副标题、描述三个过滤条件为模糊查询
     *  并且这三个过滤条件之间为或者的关系，用 OR 连接
     *  这三个过滤条件与其他过滤条件依然是并且关系，用 AND 连接
    * */
    List<Business> selectLikeByPage(Business business);
    /*
    * 删除商户信息
    * */

    int delete(long id);
    /*
    * 修改商户信息
    * */

    int update(Business business);

    /*
    * 更新商户的【统计评论星星总数】、【统计评论总次数】，商户的【星级】用这两个字段数据计算得出
    * */
    int updateStar(Map<String,Object> map);

}
