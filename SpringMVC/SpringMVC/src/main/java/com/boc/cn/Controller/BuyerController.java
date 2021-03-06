package com.boc.cn.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boc.cn.Controller.VO.Product;
import com.boc.cn.Controller.VO.User;
import com.boc.cn.Service.ProductService;
import com.boc.cn.Service.DTO.BuyDTO;

@Controller
public class BuyerController {
	@Autowired
	private ProductService productServiceImpl;

	// 账务
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(HttpServletRequest req, ModelMap map) {
		User user = CurrentUser.getUserInSession(req);
		BuyDTO buyDto = productServiceImpl.getBuys(user.getId());
		List<Product> buyList = buyDto.getBuyList();
		int total = buyDto.getTotal();
		map.addAttribute("user", user);
		map.addAttribute("buyList", buyList);
		map.addAttribute("total", total);
		return "account";
	}

	// 购物车
	@RequestMapping(value = "/settleAccount", method = RequestMethod.GET)
	public String settleAccount(HttpServletRequest req, ModelMap map) {
		User user = CurrentUser.getUserInSession(req);
		map.addAttribute("user", user);
		return "settleAccount";
	}
}
