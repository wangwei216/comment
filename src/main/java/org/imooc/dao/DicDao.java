package org.imooc.dao;

import org.imooc.bean.Dic;

import java.util.List;

public interface DicDao {
    //这个是字典索引表
    List<Dic> select(Dic dic);
}