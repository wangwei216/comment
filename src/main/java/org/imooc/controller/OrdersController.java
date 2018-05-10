package org.imooc.controller;

import org.imooc.constant.PageCodeEnum;
import org.imooc.dto.OrdersDto;
import org.imooc.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping
	public String init() {
		return "/content/orderList";
	}

	/*
	* 这个是从用户的Id去查询，也就是member的主键id
	* */
	@RequestMapping("/search")
	public String search(Model model , OrdersDto ordersDto){

		List<OrdersDto> list = ordersService.getListByMemberId(ordersDto.getMemberId());
		model.addAttribute("list",list);
		model.addAttribute("search",ordersDto);
		return "/content/orderList";
	}

	/*
	* 修改哪个订单先拿到哪个订单的id，也就是初始化
	* */
	@RequestMapping("/modifyInit")
	public String modify(Model model, @PathVariable("id")long id){

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