package com.datang.hrb.service.Impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datang.hrb.dao.StudentDao;
import com.datang.hrb.service.StudentService;
import com.datang.hrb.vo.Student;

public class StudentServiceImpl implements StudentService {
	private StudentDao sd=new StudentDao();
	@Override
	public int addStudent(Student s,HttpSession session) throws IOException{
		return sd.addstudent(s,session);
	}

}
