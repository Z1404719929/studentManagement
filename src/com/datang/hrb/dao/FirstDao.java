package com.datang.hrb.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datang.hrb.vo.Sclass;
import com.datang.hrb.vo.Student;

public class FirstDao {
	public ResultSet getAllSclass(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// 查询所有教室信息

		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from class");
			rs = ps.executeQuery();

			if (rs != null ) {
				List<Sclass> SclassList = new ArrayList<Sclass>();
				while (rs.next()) {
					Sclass s = new Sclass();
					s.setName(rs.getString(1));
					s.setYx(rs.getString(2));
					s.setZy(rs.getString(3));
					s.setNum(rs.getInt(4));
					
					SclassList.add(s);
				}
				System.out.println("查询2");
				session.setAttribute("SclassList", SclassList);
				FirstDao fd=new FirstDao();
				System.out.println("查询3");
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
		return rs;
	}
	
	
	public ResultSet getFy(HttpSession session, HttpServletRequest req, HttpServletResponse resp,Integer num)
			throws IOException{				//学生分页
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select * from student");
			System.out.println((num-1)*5+5);
			rs = ps.executeQuery();
			if (rs != null) {
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
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			ps = conn.prepareStatement("select * from student limit ?,?");
			//num=(num-1)*5;
			ps.setInt(1, (num-1)*5);
			System.out.println((num-1)*5);
			//num=num+5;
			ps.setInt(2, 5);
			System.out.println((num-1)*5+5);
			rs = ps.executeQuery();
			if (rs != null) {
				List<Student> StudentLimit = new ArrayList<Student>();
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
					StudentLimit.add(s);
				}
				session.setAttribute("StudentLimit", StudentLimit);
				
				System.out.println("查询");
				FirstDao f1 = new FirstDao();
				ResultSet rs1 = f1.getAllSclass(session, req, resp); // resp.sendRedirect("student_list.jsp"); 
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
		return rs;

	}
	
	
	
	public ResultSet getFyCX(HttpSession session, HttpServletRequest req, HttpServletResponse resp,Integer num)
			throws IOException{				//学生分页
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select * from student");
			System.out.println((num-1)*5+5);
			rs = ps.executeQuery();
			if (rs != null) {
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
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			ps = conn.prepareStatement("select * from student limit ?,?");
			//num=(num-1)*5;
			ps.setInt(1, (num-1)*5);
			System.out.println((num-1)*5);
			//num=num+5;
			ps.setInt(2, 5);
			System.out.println((num-1)*5+5);
			rs = ps.executeQuery();
			if (rs != null) {
				List<Student> StudentLimit = new ArrayList<Student>();
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
					StudentLimit.add(s);
				}
				//session.setAttribute("StudentLimit", StudentLimit);
				
				System.out.println("查询");
				FirstDao f1 = new FirstDao();
				ResultSet rs1 = f1.getAllSclass(session, req, resp); // resp.sendRedirect("student_list.jsp"); 
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
		return rs;

	}
	
	
	
	/*
	 * public ResultSet getClassFy(HttpSession session, HttpServletRequest req,
	 * HttpServletResponse resp,Integer num) throws IOException { //班级分页 Connection
	 * conn = ConnectionUtil.getConnection(); PreparedStatement ps = null; ResultSet
	 * rs = null;
	 * 
	 * try { ps = conn.prepareStatement("select * from class limit ?,?");
	 * //num=(num-1)*5; ps.setInt(1, (num-1)*5); System.out.println((num-1)*5);
	 * //num=num+5; ps.setInt(2, 5); System.out.println((num-1)*5+5); rs =
	 * ps.executeQuery();
	 * 
	 * if (rs != null) { List<Sclass> SclassList = new ArrayList<Sclass>(); while
	 * (rs.next()) { Sclass sc = new Sclass(); sc.setName(rs.getString(1));
	 * sc.setYx(rs.getString(2)); sc.setZy(rs.getString(3)); SclassList.add(sc); }
	 * session.setAttribute("SclassList", SclassList);
	 * 
	 * // FirstDao f1 = new FirstDao(); //ResultSet rs1 = f1.getAllSclass(session,
	 * req, resp); // resp.sendRedirect("student_list.jsp"); } else {
	 * resp.sendRedirect("error.jsp"); } } catch (SQLException e) {
	 * resp.sendRedirect("error.jsp"); e.printStackTrace(); } finally { if (ps !=
	 * null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
	 * } return rs;
	 * 
	 * }
	 */
	

}
