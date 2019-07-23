package com.datang.hrb.service.Impl;

import com.datang.hrb.dao.UserDao;
import com.datang.hrb.service.RegisterService;
import com.datang.hrb.vo.User;

public class RegisterServiceImpl implements RegisterService{
	private UserDao userDao=new UserDao();
	@Override
	public int saveUser(User user){
		// TODO Auto-generated method stub
		return userDao.saveUser(user);
	}

}
