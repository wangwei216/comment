package org.imooc.service;

import org.imooc.bean.Page;
import org.imooc.dto.CommentListDto;

public interface CommentService {

    //这个是实现查询评论信息
    CommentListDto getListByBusinessId(int businessId, Page page);

}
