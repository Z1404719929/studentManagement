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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	/**
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String sclass = req.getParameter("sclass");
		String major = req.getParameter("major");
		String school = req.getParameter("school");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");

		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf(".do"));
		HttpSession session = req.getSession();
		StudentDao sd = new StudentDao();
		
		Connection conn = ConnectionUtil.getConnection(); 
		PreparedStatement ps = null;
		
		/*
		 * if (action.equals("all")) {
		 * 
		 * //HttpSession session = req.getSession();
		 * 
		 * try { ps = conn.prepareStatement("select * from student where no=?");
		 * ps.setString(1, "1"); ResultSet rs = ps.executeQuery();
		 * 
		 * if (rs != null && rs.next()) { try { ResultSet rsList = sd.getAllStudent();
		 * session.setAttribute("no", no); ps =
		 * conn.prepareStatement("select * from student where no = ?"); ps.setString(1,
		 * no); //ResultSet rsList = ps.executeQuery(); List<Student> studentList = new
		 * ArrayList<Student>(); while (rsList.next()) { Student s = new Student();
		 * s.setNo(rsList.getString(1)); s.setName(rsList.getString(2));
		 * s.setSex(rsList.getString(3)); s.setSclass(rsList.getString(4));
		 * s.setMajor(rsList.getString(5)); s.setSchool(rsList.getString(6));
		 * s.setEmail(rsList.getString(7)); s.setPhone(rsList.getString(8));
		 * studentList.add(s); } session.setAttribute("studentList", studentList);
		 * resp.sendRedirect("student_list.jsp"); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 */
		
		
		if (action.equals("add")) {
			resp.sendRedirect("student_add.jsp");
		}
		if (action.equals("addinto")) {
			if (no != "" && name != "" && sex != "" && sclass != "" && major != "" && school != "" && email != ""
					&& phone != "") {
				Student s = new Student();
				s.setNo(no);
				s.setName(name);
				s.setSex(sex);
				s.setSclass(sclass);
				s.setSchool(school);
				s.setEmail(email);
				s.setPhone(phone);
				s.setMajor(major);
				StudentService studentService = new StudentServiceImpl();
				int i = studentService.addStudent(s);
				if (i == 1) {
					resp.sendRedirect("add_success.jsp");
				} else {
					resp.sendRedirect("add_fail.jsp");
				}
			} else {
				resp.sendRedirect("add_fail.jsp");
			}
		}

		if (action.equals("delect")) {
			resp.sendRedirect("student_delete.jsp");
		}
		if (action.equals("delect_ok.jsp")) {
			
		}
		
	}

}
