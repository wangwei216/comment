package org.imooc.controller;

import org.imooc.constant.PageCodeEnum;
import org.imooc.dto.GroupDto;
import org.imooc.dto.PageCodeDto;
import org.imooc.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /*
    * 获取用户的列表
    * */
    @RequestMapping(method = RequestMethod.GET)
    public List<GroupDto> getList(){
        return groupService.getList();
    }

    /*
    * 新增用户
    * */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto add(GroupDto groupDto){
        PageCodeDto result;
        if (groupService.add(groupDto)){
            result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        }
        else {

            result = new PageCodeDto(PageCodeEnum.GROUPNAME_EXISTS);
        }

        return result;

    }

    /*
    * 获取用户组对应可以访问的菜单和动作
    * */
    @RequestMapping(value = "id",method = RequestMethod.GET)
    public GroupDto getMenuList(@PathVariable("id")Long id){
        return groupService.getByIdWithMenuAction(id);
    }


}
