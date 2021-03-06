package com.boc.cn.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.boc.cn.Controller.VO.User;

public class CurrentUser {
	// 获取session中用户数据
		public static User getUserInSession(HttpServletRequest req) {
			HttpSession session = req.getSession();
			String userName = (String) session.getAttribute("userName");
			Integer userType = (Integer) session.getAttribute("userType");
			Integer userId = (Integer) session.getAttribute("userId");
			User user = null;
			if (userName != null) {
				user = new User();
				user.setUsername(userName);
				user.setUsertype(userType);
				user.setId(userId);
			}
			return user;
		}
}
