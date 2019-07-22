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
import com.datang.hrb.vo.Student;

public class StudentDao {
	
	public ResultSet getAllStudent(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws IOException {
				//查询所有学生信息
		/*
		 * Connection conn = ConnectionUtil.getConnection(); PreparedStatement ps =
		 * null; ResultSet rs = null; try { ps =
		 * conn.prepareStatement("select * from student"); rs = ps.executeQuery(); }
		 * catch (SQLException e) { e.printStackTrace(); } finally { if (ps != null) {
		 * try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } } }
		 * return rs;
		 */
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement("select * from student");
			rs = ps.executeQuery();

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
		return rs;
		
	}
	
	public int addstudent(Student s) {		//增加学生
		int i=0;
		if (s.getNo() != "" && s.getName() != "" && s.getSex() != "" && s.getSclass() != "" && s.getMajor() != "" && s.getSchool() != "" && s.getEmail() != ""
				&& s.getPhone() != "") {
			//StudentService studentService = new StudentServiceImpl();
			Connection conn=ConnectionUtil.getConnection();
			PreparedStatement ps=null;
			try {
				ps=conn.prepareStatement("insert into student(no,name,sex,sclass,major,school,email,phone) values(?,?,?,?,?,?,?,?)");
				ps.setString(1, s.getNo());
				ps.setString(2, s.getName()); 
				ps.setString(3, s.getSex());
				ps.setString(4, s.getSclass());
				ps.setString(5, s.getMajor());
				ps.setString(6, s.getSchool());
				ps.setString(7, s.getEmail());
				ps.setString(8, s.getPhone());
				i =ps.executeUpdate(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(ps!=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			//return i;
			}else if(s.getNo() == "") {
				
			}
		return i;
		}
//		Connection conn=ConnectionUtil.getConnection();
//		PreparedStatement ps=null;
//		int i=0;
//		try {
//			ps=conn.prepareStatement("insert into student(no,name,sex,sclass,major,school,email,phone) values(?,?,?,?,?,?,?,?)");
//			ps.setString(1, s.getNo());
//			ps.setString(2, s.getName()); 
//			ps.setString(3, s.getSex());
//			ps.setString(4, s.getSclass());
//			ps.setString(5, s.getMajor());
//			ps.setString(6, s.getSchool());
//			ps.setString(7, s.getEmail());
//			ps.setString(8, s.getPhone());
//			i =ps.executeUpdate(); 
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			if(ps!=null) {
//				try {
//					ps.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return i;
//		}
	
	public void toalter(String stu_code,HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws IOException {	//去修改学生页面
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement("select * from student where no=?");
			ps.setString(1, stu_code);
			rs = ps.executeQuery();
			//if (rs != null && rs.next()) {
				//List<Student> StudentList = new ArrayList<Student>();
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
					//StudentList.add(s);
				//}
				session.setAttribute("s", s);
				//session.setAttribute("StudentList", StudentList);
				resp.sendRedirect("redact_stu.jsp");
		//	} else {
		//		resp.sendRedirect("error.jsp");
		//	}
				}
		} catch (SQLException e) {
			//resp.sendRedirect("error.jsp");
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
	
	public int alterstu(String stu_code,HttpServletRequest req, HttpServletResponse resp) throws IOException {	//修改学生信息
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		int i=0;
		try {
			ps = conn.prepareStatement("update student set no=?,name=?,sex=?,sclass=?,major=?,school=?,email=?,phone=? where no=?");
			ps.setString(1,req.getParameter("no") );
			ps.setString(2,req.getParameter("name") );
			ps.setString(3,req.getParameter("sex") );
			ps.setString(4,req.getParameter("sclass") );
			ps.setString(5,req.getParameter("major") );
			ps.setString(6,req.getParameter("school") );
			ps.setString(7,req.getParameter("email") );
			ps.setString(8,req.getParameter("phone") );
			ps.setString(9,stu_code);
			//System.out.println(s.getNo());
			//System.out.println(stu_code);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public int deletestu(String stu_no,HttpServletRequest req, HttpServletResponse resp) throws IOException {	//删除学生信息
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		int i=0;
		try {
			ps = conn.prepareStatement("delete from student where no=?");
			ps.setString(1,stu_no);
			//System.out.println(s.getNo());
			//System.out.println(stu_code);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	
}
