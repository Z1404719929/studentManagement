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
import com.datang.hrb.dao.SclassDao;
import com.datang.hrb.dao.StudentDao;
import com.datang.hrb.service.SclassService;
import com.datang.hrb.service.StudentService;
import com.datang.hrb.service.Impl.SclassServiceImpl;
import com.datang.hrb.service.Impl.StudentServiceImpl;
import com.datang.hrb.vo.Sclass;
import com.datang.hrb.vo.Student;

public class StudentController extends HttpServlet {
	static String stu_code = null;
	static String sc_code = null;

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

		Sclass sc = new Sclass();
		sc.setName(req.getParameter("教室名称"));
		sc.setYx(req.getParameter("所属院校"));
		sc.setZy(req.getParameter("所属专业"));
		// s.set(req.getParameter("sclass"));

		// String username = req.getParameter("username");
		// String password = req.getParameter("password");

		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf(".do"));
		HttpSession session = req.getSession();

		if (action.equals("login")) {
			StudentDao sd = new StudentDao();
			ResultSet rs = sd.getAllStudent(session, req, resp);
			// SclassDao scd = new SclassDao();
			// ResultSet rs = scd.getAllSclass(session, req, resp);

		}

		if (action.equals("add")) { // 增加学生
			StudentService studentService = new StudentServiceImpl();
			int i = studentService.addStudent(s, session);
			if (i == 1) {
				StudentDao sd = new StudentDao();
				ResultSet rs = sd.getAllStudent(session, req, resp);
				// resp.sendRedirect("student_list.jsp");
			} else{
				resp.sendRedirect("add_stu.jsp");
			}

		}

		if (action.equals("addsc")) { // 增加班级
			SclassService sclassService = new SclassServiceImpl();
			int i = sclassService.addSclass(sc);
			if (i == 1) {
				SclassDao scd = new SclassDao();
				ResultSet rs = scd.getAllSclass(session, req, resp);
				// resp.sendRedirect("student_list.jsp");
			} else {
				resp.sendRedirect("error.jsp");
			}
		}

		if (action.equals("redact")) { // 点击学生修改按钮
			stu_code = req.getParameter("stu_code");
			StudentDao sd = new StudentDao();
			sd.toalter(stu_code, session, req, resp);
			// System.out.println("学号："+stu_code);
		}

		if (action.equals("class_redact")) { // 点击教室修改按钮
			System.out.println("1");
			sc_code = req.getParameter("sc_code");
			SclassDao scd = new SclassDao();
			scd.toalter(sc_code, session, req, resp);
			// System.out.println("学号："+stu_code);
		}

		if (action.equals("redact_ok")) { // 点击保存修改
			StudentDao sd = new StudentDao();
			int i = sd.alterstu(stu_code, req, resp);
			if (i == 1) {
				ResultSet rs = sd.getAllStudent(session, req, resp);
			} else {
				resp.sendRedirect("error.jsp");
			}
		}

		if (action.equals("class_redact_ok")) { // 点击教室保存修改
			SclassDao sd = new SclassDao();
			int i = sd.altersc(sc_code, req, resp);
			if (i == 1) {
				ResultSet rs = sd.getAllSclass(session, req, resp);
			} else {
				resp.sendRedirect("error.jsp");
			}
		}

		if (action.equals("delete")) { // 删除按钮
			String stu_no = req.getParameter("stu_no");
			StudentDao sd = new StudentDao();
			int i = sd.deletestu(stu_no, req, resp);
			if (i == 1) {
				ResultSet rs = sd.getAllStudent(session, req, resp);
			} else {
				resp.sendRedirect("error.jsp");
			}
		}

		if (action.equals("class_delete")) { // 教室删除按钮
			String sc_no = req.getParameter("sc_no");
			SclassDao sd = new SclassDao();
			int i = sd.deletesc(sc_no, req, resp);
			if (i == 1) {
				ResultSet rs = sd.getAllSclass(session, req, resp);
			} else {
				resp.sendRedirect("error.jsp");
			}
		}
		
		
	}
}