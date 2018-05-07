package org.imooc.controller;

import org.imooc.constant.PageCodeEnum;
import org.imooc.dto.BusinessDto;
import org.imooc.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;


    /*
    * 商户新增页面的初始化
    * */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addInit(Model model) {
        /*model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));*/
        return "/content/businessAdd";
    }
    /*
    * 商户新增功能
    * */
    @RequestMapping(method = RequestMethod.POST)
    public String addInit(BusinessDto businessDto, RedirectAttributes attr){
        //判断从Service层是不是插入数据库成功
        if (businessService.add(businessDto)){
            attr.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
            return "redirect:/business";
        }
        else {
            attr.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
            return "redirect:/business/addPage";
        }

    }
    /*
    * 查询商户列表
    * */
    @RequestMapping(method = RequestMethod.GET)
    public String search(Model model ,BusinessDto dto){
        //这个是把从service层获取到的集合数据返回给前端对应的list属性名中
        model.addAttribute("list",businessService.searchByPage(dto));
        model.addAttribute("searchParam",dto);
        return "/content/businessList";
    }


    /*
    * 这个是实现商户的删除功能，这个id就是当用户请求过来的时候session中会携带id，然后从获取到的id去查询到是哪个实体
    * */
    @RequestMapping("/remove")
    public String remove(@RequestParam("id") long id,Model model){
        //这步意思就是把得到的商户DTO给set进模型中
        model.addAttribute("remove",businessService.getById(id));
        if (businessService.remove(id)){
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
        }
        return "forward:/business";
    }

    /*
    * 这个是初始化修改商户信息
    * */
    @RequestMapping("/modifyInit")
    public String modify(Model model, @PathVariable("id") Long id){
        //首先先把数据传输模型放到model模型中
        model.addAttribute("modifyObj",businessService.getById(id));

        return "/content/businessModify";
    }
    /*
    * 商户修改
    * */
    @RequestMapping("/modify")
    public String modify(Model model,BusinessDto businessDto){
        //先把数据传输的数据实体set进model中
        model.addAttribute("modifyObj",businessDto);
        if (businessService.modify(businessDto)){
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
        }
        else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL);
        }

        return "/content/businessModify";
    }


}
