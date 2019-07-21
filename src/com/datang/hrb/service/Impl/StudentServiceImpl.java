package com.datang.hrb.service.Impl;

import com.datang.hrb.dao.StudentDao;
import com.datang.hrb.service.StudentService;
import com.datang.hrb.vo.Student;

public class StudentServiceImpl implements StudentService {
	private StudentDao sd=new StudentDao();
	@Override
	public int addStudent(Student s) {
		return sd.addstudent(s);
	}

}
