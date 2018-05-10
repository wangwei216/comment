package org.imooc.service.serviceImpl;

import org.imooc.bean.User;
import org.imooc.dao.UserDao;
import org.imooc.dto.UserDto;
import org.imooc.service.UserService;
import org.imooc.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    /*
    *  对用户登录信息的验证
    *   1.因为传到service层的参数都是controller层在用的时候都已经准备好的，所以直接去遍历表里面的数据
    *   2.先判断在有没有从数据传输层中拿到数据
    *   3.然后创建一个非数据传输对象（也就是实体对象）
    *   4.把数据传输对象都赋值到实体对象中
    *   5.然后从Dao层去拿到所有用户的列表(这个Dao中已经定义好的，直接用就行)
    *   6.判断有几个值，然后取出
    *
    *
    * */
    @Override
    public Boolean validate(UserDto userDto) {

        if (userDto != null && !CommonUtil.isEmpty(userDto.getName()) && !CommonUtil.isEmpty(userDto.getPassword())) {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            List<User> list = userDao.select(user);
            if(list.size() == 1) {
                BeanUtils.copyProperties(list.get(0),userDto);
                return true;
            }
            return false;
        }
        return false;
    }

}
