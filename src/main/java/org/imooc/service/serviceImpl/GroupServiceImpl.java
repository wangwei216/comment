package org.imooc.service.serviceImpl;

import org.imooc.bean.*;
import org.imooc.dao.GroupActionDao;
import org.imooc.dao.GroupDao;
import org.imooc.dao.GroupMenuDao;
import org.imooc.dto.ActionDto;
import org.imooc.dto.GroupDto;
import org.imooc.dto.MenuDto;
import org.imooc.service.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupDao groupDao;

    @Resource
    private GroupMenuDao groupMenuDao;
    @Resource
    private GroupActionDao groupActionDao;

    /*
    * 如何实现在serviceImpl层去从DAO层拿到数据
    *   1.先定一个你要返回什么的类型的集合
    *   2.把你要从DAO拿到的数据也放到一个新的集合中
    *   3.然后遍历拿到数据的那个集合，然后定义一个数据传输模型，先把这个传输模型给放到你的返回结果的集和中
    *   4.然后把从DAO拿到的数据都给放到DTO中去，最后返回结果
    *
    * */
    @Override
    public List<GroupDto> getList() {
        List<GroupDto> result = new ArrayList<GroupDto>();
        List<Group> groupList = groupDao.select(new Group());
        for (Group group : groupList) {
            GroupDto groupDto = new GroupDto();
            result.add(groupDto);
            BeanUtils.copyProperties(group,groupDto);
            /*groupDto.setpId(0);*/
        }

        return result;
    }

    /*
    * 新增用户表
    * */
    @Override
    public Boolean add(GroupDto groupDto) {
        Group group = new Group();
        BeanUtils.copyProperties(groupDto,group);
        if (groupDao.insert(group)==1){
            return true;
        }
        else {
            return false;
        }

    }


    /*
    * 通过DAO获取用户主键id去分配菜单信息
    *
    * */
    @Override
    public GroupDto getByIdWithMenuAction(Long id) {

        GroupDto result = new GroupDto();
        //定义两个list
        List<MenuDto> menuDtoList = new ArrayList<MenuDto>();
        List<ActionDto> actionDtoList = new ArrayList<ActionDto>();
        /*因为你后面需要调用DAO去查询数据库，并且返回的还是一个GroupResultMap，里面包含的有其他好几个表中的字段*/
        result.setActionDtoList(actionDtoList);
        result.setMenuDtoList(menuDtoList);
        //这个是从数据库总去拿到数据
        Group group = groupDao.selectMenuListById(id);
        //然后去判断是不是拿到了数据,
        if (group!=null){
            /*然后从用户组中拿到之前set进去的MenuDTO，再去把dto中的字段都给赋值到menu中*/
            BeanUtils.copyProperties(group,result);
           for (Menu menu: group.getMenuList()){
               MenuDto menuDto = new MenuDto();
               menuDtoList.add(menuDto);
               BeanUtils.copyProperties(menu,menuDto);
           }
           for (Action action : group.getActionList()){
               //这个就相当是定义一个零食的数据传输模型，来先得到地址的引用，后面直接传值
               ActionDto actionDto = new ActionDto();
               actionDtoList.add(actionDto);
               BeanUtils.copyProperties(action,actionDto);
           }

        }

        return result;
    }

    /*
    *
    * */
    @Override
    public boolean modify(GroupDto groupDto) {
        Group group = new Group();
        BeanUtils.copyProperties(groupDto, group);
        return groupDao.update(group) == 1;
    }

    @Override
    public boolean remove(Long id) {
        return groupDao.delete(id) == 1;
    }

    /*
    *
    * */
    @Override
    public GroupDto getById(Long id) {
        GroupDto groupDto = new GroupDto();
        Group group = groupDao.selectById(id);
        BeanUtils.copyProperties(group, groupDto);
        return groupDto;
    }

    /*
    *
    * */
    @Override
    @Transactional
    public boolean assignMenu(GroupDto groupDto) {
        groupMenuDao.deleteByGroupId(groupDto.getId());
        groupActionDao.deleteByGroupId(groupDto.getId());
        // 保存为用户组分配的菜单
        if (groupDto.getMenuIdList() != null && groupDto.getMenuIdList().size() > 0) {
            List<GroupMenu> list = new ArrayList<>();
            for (Long menuId : groupDto.getMenuIdList()) {
                if (menuId != null) {
                    GroupMenu groupMenu = new GroupMenu();
                    groupMenu.setGroupId(groupDto.getId());
                    groupMenu.setMenuId(menuId);
                    list.add(groupMenu);
                }
            }
            groupMenuDao.insertBatch(list);
        }
        // 保存为用户组分配的动作
        if (groupDto.getActionIdList() != null && groupDto.getActionIdList().size() > 0) {
            List<GroupAction> list = new ArrayList<>();
            for (Long actionId : groupDto.getActionIdList()) {
                if (actionId != null) {
                    GroupAction groupAction = new GroupAction();
                    groupAction.setGroupId(groupDto.getId());
                    groupAction.setActionId(actionId);
                    list.add(groupAction);
                }
            }
            groupActionDao.insertBatch(list);
        }
        return true;

    }
}
