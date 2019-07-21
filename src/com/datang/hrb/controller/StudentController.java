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
		System.out.println("run in doGet");

		resp.sendRedirect("ok.jsp");
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

		if (action.equals("login")) {
			try {
				ps = conn.prepareStatement("select * from student");
				ResultSet rs = ps.executeQuery();

				if (rs != null && rs.next()) {
					List<Student> StudentList = new ArrayList<Student>();
					while (rs.next()) {
						Student s = new Student();
						s.setNo(rs.getString(1));
						s.setName(rs.getString(2));
						s.setSex(rs.getString(3));
						s.setSclass(rs.getString(4));
						s.setMajor(rs.getString(5));
						s.setSchool(rs.getString(6));
						s.setEmail(rs.getString(7));
						s.setPhone(rs.getString(8));
						StudentList.add(s);
					}
					session.setAttribute("StudentList", StudentList);
					resp.sendRedirect("student_list.jsp");
				} else {
					resp.sendRedirect("error.jsp");
				}
			} catch (SQLException e) {
				resp.sendRedirect("error.jsp");
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (action.equals("add")) {
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
					resp.sendRedirect("student_list.jsp");
				} else {
					resp.sendRedirect("error.jsp");
				}
			} else {
				resp.sendRedirect("error.jsp");
			}
		}
		if (action.equals("redact")) {
			
		}
	}
}