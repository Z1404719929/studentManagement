package com.datang.hrb.service.Impl;

import com.datang.hrb.dao.SclassDao;
import com.datang.hrb.service.SclassService;
import com.datang.hrb.vo.Sclass;

public class SclassServiceImpl implements SclassService {
	private SclassDao scd=new SclassDao();
	@Override
	public int addSclass(Sclass s) {
		return scd.addClass(s);
	}

}
