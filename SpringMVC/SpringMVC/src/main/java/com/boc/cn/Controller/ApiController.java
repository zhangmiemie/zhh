package com.boc.cn.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boc.cn.Service.ProductService;
import com.boc.cn.Service.UserService;
import com.boc.cn.Service.DTO.UserDTO;

@Controller
public class ApiController {

	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private ProductService productServiceImpl;

	// 登陆
	@RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> userLogin(HttpServletRequest req) {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		System.out.println(userName + " " + password);
		String message = null;
		UserDTO user = userServiceImpl.getUser(userName);
		Map<String, String> map = new HashMap<String, String>();

		if (user == null) {
			message = "无此用户！";
		} else {
			if (!password.equals(user.getPassword()))
				message = "密码错误！";
			else {
				HttpSession session = req.getSession();
				session.setAttribute("userName", userName);
				session.setAttribute("userType", user.getUsertype());
				session.setAttribute("userId", user.getId());
			}
		}
		if (message == null) {
			map.put("result", "true");
			map.put("code", "200");
		} else {
			map.put("result", "false");
			map.put("code", "222");
			map.put("message", message);
		}
		return map;
	}

	// 登陆
	@RequestMapping(value = "/api/delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> deleteProduct(HttpServletRequest req) {
		int id = Integer.valueOf(req.getParameter("id"));
		boolean flag = productServiceImpl.deleteProduct(id);
		Map<String, String> map = new HashMap<String, String>();
		if (flag ) {
			map.put("result", "true");
			map.put("code", "200");
		} else {
			map.put("result", "false");
			map.put("code", "222");
			map.put("message", "删除失败！");
		}
		return map;
	}
}
