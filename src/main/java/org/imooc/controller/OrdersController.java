package org.imooc.controller;

import org.imooc.constant.PageCodeEnum;
import org.imooc.dto.OrdersDto;
import org.imooc.service.OrdersService;
import org.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String init() {
		return "/content/orderList";
	}

	/*
	* 这个是从用户的Id去查询订单信息所关联的其他表的信息，
	* */
	@RequestMapping("/search")
	public String search(Model model , OrdersDto ordersDto){

		List<OrdersDto> list = ordersService.getListByMemberId(1);
		model.addAttribute("list",list);
		model.addAttribute("search",ordersDto);
		return "/content/orderList";
	}

	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public String searchByOrderId(Model model, @RequestParam("id")long id){
		 ordersService.getById(id);
		return "/content/orderList";
	}

	/*
	* 这个是根据主键获取用户的
	* */
	/*@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public UserDto getById(@PathVariable("id") Long id) {
		return userService.getById(id);
	}*/

	/*
	* 修改哪个订单先拿到哪个订单的id，也就是初始化
	* @PathVariable是获取url上数据的。
	  @RequestParam获取请求参数的（包括post表单提交
	* */
	@RequestMapping("/modifyInit")
	//TODO 这个因为是根据订单id去查询订单，然后没有从前台那数据，只能自己手动加上才能拿到
	public String modify(Model model,@RequestParam("id")long id){
		model.addAttribute("modifyObj",ordersService.getById(id));
		return "/content/orderModify";
	}

	@RequestMapping("/modify")
	public String modify(Model model,OrdersDto ordersDto){
		model.addAttribute("modifyObj",ordersDto);
		if (ordersService.modify(ordersDto)){
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_SUCCESS);
		}
		else {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL);
		}
		return "/content/orderModify";
	}

}