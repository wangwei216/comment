package org.imooc.service.serviceImpl;

import org.imooc.bean.User;
import org.imooc.dao.UserDao;
import org.imooc.dto.UserDto;
import org.imooc.service.UserService;
import org.imooc.util.CommonUtil;
import org.imooc.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

            int effectNum = userDao.selectByNameAndPassword(userDto.getName(), user.getPassword());
            if(effectNum == 1) {
                return true;
            }else {
                return false;
            }

        }
        return false;
    }

    @Override
    public List<UserDto> getList() {
        List<UserDto> result = new ArrayList<>();
        List<User> userList = userDao.select(new User());
        for (User user : userList) {
            UserDto userDto = new UserDto();
            result.add(userDto);
            BeanUtils.copyProperties(user, userDto);
            userDto.setpId(0);
        }
        return result;
    }

    @Override
    public boolean add(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(MD5Util.getMD5(userDto.getPassword()));
        return userDao.insert(user) == 1;
    }

    @Override
    public UserDto getById(Long id) {
        UserDto userDto = new UserDto();
        User user = userDao.selectById(id);
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public boolean modify(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        if(!CommonUtil.isEmpty(userDto.getPassword())) {
            user.setPassword(MD5Util.getMD5(userDto.getPassword()));
        }
        return userDao.update(user) == 1;
    }

    @Override
    public boolean remove(Long id) {
        return userDao.delete(id) == 1;
    }
}