package org.imooc.service;

import org.imooc.dto.BusinessDto;
import org.imooc.dto.BusinessListDto;

import java.util.List;

public interface BusinessService {

    /*
    * 新增商户的信息，一般返回用Boolean类型便于Controller层进行判断
    * */
    Boolean add(BusinessDto businessDto);
    /*
     * 通过主键获取商户的DTO
     * */
    BusinessDto getById (long id);
    /*
    * 删除商户信息，先获取id
    * */
    Boolean remove(long id);

        //修改商户信息
    boolean modify(BusinessDto businessDto,int id);

    /*
     * 通过分页查询条件去查询
     * */
    List<BusinessDto> searchByPage(BusinessDto businessDto);
    /*
    * 通过分页查询（接口）
    * */
    BusinessListDto searchByPageForApi(BusinessDto businessDto);
}
