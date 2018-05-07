package org.imooc.controller;

import org.imooc.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping
	public String init() {
		return "/content/orderList";
	}


}