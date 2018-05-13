package org.imooc.service.serviceImpl;

import org.imooc.bean.Action;
import org.imooc.dao.ActionDao;
import org.imooc.dto.ActionDto;
import org.imooc.service.ActionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActionServiceImpl implements ActionService {

    @Resource
    private ActionDao actionDao;

    /*看是否根据动作实体类的id，来去删除用户实体类*/
    @Override
    public Boolean remove(Long id) {

       if ( actionDao.deleteById(id)==1){
           return true;
       }
        else {
           return false;
       }

    }
    /*添加用户事件*/
    @Override
    public Boolean add(ActionDto actionDto) {
        return actionDao.insert(actionDto)==1;
    }
    /*
    * 为什么添加和删除的时候可以直接调用DAO的方法，但是在修改的时候就需要把数据传输对象的数据赋值到自己新建的一个实体中
    *   1.因为你要修改的时候，你是需要把之前的参数先给放进去，
    * */
    @Override
    public Boolean modify(ActionDto actionDto) {
        Action action = new Action();
        BeanUtils.copyProperties(actionDto,action);
        return actionDao.update(actionDto)==1;
    }

    /*
    * 通过用户的id来去获取用户动作的实体类
    *   1.在service实现类中去调用DAO的时候，需要
    * */
    @Override
    public ActionDto getById(Long id) {
        ActionDto result = new ActionDto();
        Action action = actionDao.selectById(id);
        BeanUtils.copyProperties(action,result);
        return result;
    }


}
