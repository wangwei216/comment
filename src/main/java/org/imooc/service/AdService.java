package org.imooc.service;

import org.imooc.dto.AdDto;

import java.util.List;


public interface AdService {

    boolean add(AdDto adDto);

    //这个是通过模糊查询得到广告实体类集合
    List<AdDto> searchByPage(AdDto adDto);
}
