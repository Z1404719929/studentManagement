package com.datang.hrb.service.Impl;

import com.datang.hrb.dao.UserDao;
import com.datang.hrb.service.LoginService;
import com.datang.hrb.vo.User;

public class LoginServiceImpl implements LoginService {
	public String login(User user) {
		System.out.println("LoginServiceImpl");
		UserDao userDao = new UserDao();
		String password = userDao.getUsersByAccounts(user.getAccounts());
		System.out.println("登陆密码"+password);
		System.out.println(user.getPassword());
		if (password.equals(user.getPassword())) {
			System.out.println("7");
			return "student_list.jsp";
		} else {
			System.out.println("8");
			return "error.jsp";
		}
	}
}
