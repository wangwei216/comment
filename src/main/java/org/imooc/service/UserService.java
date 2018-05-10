package org.imooc.service;

import org.imooc.dto.UserDto;

public interface UserService {

    //因为这个是对应controller层的用户名和密码效验功能
    Boolean validate(UserDto userDto);

}
