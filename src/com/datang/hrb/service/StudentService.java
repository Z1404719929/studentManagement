package com.datang.hrb.service;

import javax.servlet.http.HttpSession;

import com.datang.hrb.vo.Student;

public interface StudentService {
	public int addStudent(Student s,HttpSession session);
}
