package org.imooc.controller;

import org.imooc.constant.PageCodeEnum;
import org.imooc.dto.UserDto;
import org.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private UserService userService;
    //这个是springMVc中提供的session接口
    @Autowired
    private HttpSession session;
    /*
    * 这个是跳转到登录界面
    * */
    @RequestMapping
    public String index(){
        return "/system/login";
    }

    /*
    * 这个是验证用户名和登录信息是不是正确的,如果验证成功直接跳转到管理首页，失败直接重新的定向到登录界面
    *   1.首先去调用service层的功能函数是不是验证用户名和密码成功
    *   2.然后把用户的数据传输模型放到作为value，用一个session常量放到用户key中(这个需要自己定义一个类似枚举)，然后放到session中
    *
    *   3.登录失败的话，就把失败的状态码给放到重定向的域中
    *
    * */
    @RequestMapping("/validate")
    public String validate(UserDto userDto, RedirectAttributes attr, HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        userDto.setName(name);
        userDto.setPassword(password);
        Boolean effect = userService.validate(userDto);
        if (effect) {
            return "/system/index";
        }
        else {
            System.out.println("登录失败");
            attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.LOGIN_FAIL);
            return "redirect:/login";
        }

    }

    /*
    * 这个是处理session超时的处理情况
    * */
    @RequestMapping("/sessionTimeout")
    private String sessionTimeout(RedirectAttributes attr){

        attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.SESSION_TIMEOUT);
        return "redirect:/login";
    }

}
