package org.imooc.controller;

import org.imooc.bean.Ad;
import org.imooc.constant.PageCodeEnum;
import org.imooc.dto.AdDto;
import org.imooc.service.AdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/ad")
public class AdController {

	@Autowired
	private AdService adService;


	public String init(){
		return "/content/adlist";
	}

	/*
	 *
	 * 这个是搜索广告实体的列表
	 * */
	@RequestMapping("/search")
	public String search(Model model, AdDto adDto) {
		model.addAttribute("list", adService.searchAll(adDto));
		model.addAttribute("searchParam", adDto);
		return "/content/adList";
	}

	/*
	* 这个是新增页初始化
	* */
	@RequestMapping("/addInit")
	public String addInit() {
		return "/content/adAdd";
	}
	/*
	* 这个是根据添加是不是成功，进行返回想对应的状态码
	* */
	@RequestMapping("/add")
	public String add(AdDto adDto,Model model){

		//先判断是不是从service层拿到了数据,如果是的话就把返回的状态码给set进Model中
		if (adService.add(adDto)){
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
		}
		else {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
		}
			return "/content/adAdd";
	}
	/*
	* 这个是转化到删除广告实体列表数据的页面
	* */
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") Long id,Model model) {
		model.addAttribute("removeObj",adService.getById(id));
		if(adService.remove(id)) {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
		} else {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
		}
		return "forward:/ad";
	}
	/*
	* 修改广告的初始化界面，也就是先查询从前台拿到id再去进行修改
	* */
	@RequestMapping("/modifyInit")
	public String modifyInit(Model model, @RequestParam("id") Long id) {
		model.addAttribute("modifyObj", adService.getById(id));
		return "/content/adModify";
	}

	/*
	* 修改界面
	* */
	@RequestMapping("/modify")
	public String modify(Model model, AdDto adDto) {
		model.addAttribute("modifyObj", adDto);
		if (adService.modify(adDto)) {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
		} else {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_FAIL);
		}
		return "/content/adModify";
	}

	/*
	* 根据广告的标题进行模糊查询
	* */
	@RequestMapping(value = "/vagueListIntit",method = RequestMethod.GET)
	public String vagueSearchInit(HttpServletRequest request,Model model){
		return "/content/adVagueList";
	}

	@RequestMapping(value = "/vagueList",method = RequestMethod.GET)
	public String vagueSearch(HttpServletRequest request, @RequestParam String title,Model model){

		Ad ad = new Ad();
		AdDto adDto = new AdDto();
		ad.setTitle(title);
		BeanUtils.copyProperties(ad,adDto);
		List<AdDto> adDtoList = adService.vagueQuery(adDto);
		model.addAttribute("list",adDtoList);
		return "/content/adVagueList";
	}


}
