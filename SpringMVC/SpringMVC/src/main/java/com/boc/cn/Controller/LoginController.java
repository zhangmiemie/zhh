package com.boc.cn.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boc.cn.Controller.VO.Product;
import com.boc.cn.Controller.VO.User;
import com.boc.cn.Service.ProductService;
import com.boc.cn.Service.DTO.ProductInfoReqDto;

@Controller
public class LoginController {
	@Autowired
	private ProductService productServiceImpl;

	// 登陆
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest req, ModelMap map) {
		User user = null;
		map.addAttribute("user", user);
		return "login";
	}

	// 展示
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndex(HttpServletRequest req, ModelMap map) {
		ProductInfoReqDto dto = new ProductInfoReqDto();
		User user = CurrentUser.getUserInSession(req);
		if (user != null) {
			map.addAttribute("user", user);
			dto.setUserId(user.getId());
			dto.setUserType(user.getUsertype());
		}
		List<Product> productList  = productServiceImpl.getProducts(dto);
		map.addAttribute("productList", productList);
		return "index";
	}

	// 退出
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req, ModelMap map) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "login";
	}

	// 查看
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(HttpServletRequest req, ModelMap map) {
		ProductInfoReqDto dto = new ProductInfoReqDto();
		User user = CurrentUser.getUserInSession(req);
		if (user != null) {
			map.addAttribute("user", user);
			dto.setUserId(user.getId());
			dto.setUserType(user.getUsertype());
		}
		String id = req.getParameter("id");
		dto.setId(id);
		Product product = productServiceImpl.getProductInfo(dto);
		map.addAttribute("product", product);
		return "show";
	}

	

}
