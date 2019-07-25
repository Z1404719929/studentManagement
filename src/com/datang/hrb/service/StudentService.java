package com.datang.hrb.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datang.hrb.vo.Student;

public interface StudentService {
	public int addStudent(Student s,HttpSession session) throws IOException;
}
