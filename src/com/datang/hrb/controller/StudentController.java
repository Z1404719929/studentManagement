package com.datang.hrb.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datang.hrb.dao.ConnectionUtil;
import com.datang.hrb.dao.StudentDao;
import com.datang.hrb.service.StudentService;
import com.datang.hrb.service.Impl.StudentServiceImpl;
import com.datang.hrb.vo.Student;

public class StudentController extends HttpServlet {
	static String stu_code = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("run in doGet");
		resp.sendRedirect("ok.jsp");
	}

	/**
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Student s = new Student();
		s.setNo(req.getParameter("no"));
		s.setName(req.getParameter("name"));
		s.setSex(req.getParameter("sex"));
		s.setSclass(req.getParameter("sclass"));
		s.setMajor(req.getParameter("major"));
		s.setSchool(req.getParameter("school"));
		s.setEmail(req.getParameter("email"));
		s.setPhone(req.getParameter("phone"));

		// String username = req.getParameter("username");
		// String password = req.getParameter("password");

		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf(".do"));
		HttpSession session = req.getSession();

		if (action.equals("login")) {
			StudentDao sd = new StudentDao();
			ResultSet rs = sd.getAllStudent(session, req, resp);
		}
			if (action.equals("add")) {			//增加学生
			StudentService studentService = new StudentServiceImpl();
			int i = studentService.addStudent(s);
			if (i == 1) {
				StudentDao sd = new StudentDao();
				ResultSet rs = sd.getAllStudent(session, req, resp);
				//resp.sendRedirect("student_list.jsp");
			} else {
				resp.sendRedirect("error.jsp");
			}
		}

		if (action.equals("redact")) {					//点击修改按钮
			stu_code = req.getParameter("stu_code");
			StudentDao sd = new StudentDao();
			sd.toalter(stu_code, session, req, resp);
			// System.out.println("学号："+stu_code);
		}
		if (action.equals("redact_ok")) {			//点击保存修改
			StudentDao sd = new StudentDao();
			int i=sd.alterstu(stu_code, req, resp);
			if(i==1) {
				ResultSet rs = sd.getAllStudent(session, req, resp);
			}else {
				resp.sendRedirect("error.jsp");
			}
		}
		
		if(action.equals("delete")) {			//删除按钮
			String stu_no= req.getParameter("stu_no");
			StudentDao sd = new StudentDao();
			int i=sd.deletestu(stu_no, req, resp);
			if(i==1) {
				ResultSet rs = sd.getAllStudent(session, req, resp);
			}else {
				resp.sendRedirect("error.jsp");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
	}
}