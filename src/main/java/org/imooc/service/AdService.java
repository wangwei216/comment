package org.imooc.service;

import org.imooc.dto.AdDto;

import java.util.List;


public interface AdService {

    //这个是添加广告信息的实体类的
    boolean add(AdDto adDto);

    //这个是通过模糊查询得到广告实体类集合
    List<AdDto> searchByPage(AdDto adDto);
    //通过根据主键删除广告的数据
    boolean remove(Long id);
    //拿到广告实体的id
    AdDto getById(Long id);
    //修改实体广告信息
    boolean modify(AdDto adDto);
}
