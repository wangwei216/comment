package org.imooc.dao;

import org.imooc.bean.Member;

import java.util.List;

public interface MemberDao {

    /*
    * 根据查询条件查询会员列表
    * @param member 查询条件
    * */
    List<Member> select(Member member);

    /*
    * 根据用户id去查询用户信息手机号
    * */
    Member selectByMemberId(Long memberId);

}