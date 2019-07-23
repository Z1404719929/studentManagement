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

public class SclassDao {

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
				session.setAttribute("SclassList", SclassList);
				resp.sendRedirect("class_list.jsp");
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
	
	public int addClass(Sclass sc) {		//增加教室
		int i=0;
		if (sc.getName() != "" && sc.getYx() != "" && sc.getZy() != "" && sc.getNum() !=-1) {
			Connection conn=ConnectionUtil.getConnection();
			PreparedStatement ps=null;
			try {
				ps=conn.prepareStatement("insert into class(教室名称,所属院校,所属专业,班级人数) values(?,?,?,?)");
				ps.setString(1, sc.getName());
				ps.setString(2, sc.getYx()); 
				ps.setString(3, sc.getZy());
				ps.setInt(4, sc.getNum());
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
			}
		return i;
		}
	
	public void toalter(String sc_code,HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws IOException {	//去修改页面
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement("select * from class where 教室名称=?");
			ps.setString(1, sc_code);
			rs = ps.executeQuery();
				while (rs.next()) {
					Sclass sc = new Sclass();
					sc.setName(rs.getString(1));
					sc.setYx(rs.getString(2));
					sc.setZy(rs.getString(3));
					//sc.setNum(rs.getString(4));
					//s.setMajor(rs.getString(5));
					//s.setSchool(rs.getString(6));
					//s.setEmail(rs.getString(7));
					//s.setPhone(rs.getString(8));
					//StudentList.add(s);
				//}
				session.setAttribute("sc", sc);
				//session.setAttribute("StudentList", StudentList);
				resp.sendRedirect("redact_class.jsp");
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
	
	
	public int altersc(String sc_code,HttpServletRequest req, HttpServletResponse resp) throws IOException {	//修改教室信息
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		int i=0;
		try {
			ps = conn.prepareStatement("update class set 教室名称=?,所属院校=?,所属专业=? where 教室名称=?");
			ps.setString(1,req.getParameter("教室名称") );
			ps.setString(2,req.getParameter("所属院校") );
			ps.setString(3,req.getParameter("所属专业") );
			ps.setString(4,sc_code);
			//System.out.println(s.getNo());
			//System.out.println(stu_code);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	
	public int deletesc(String sc_no,HttpServletRequest req, HttpServletResponse resp) throws IOException {	//删除教室学生信息
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		int i=0;
		try {
			ps = conn.prepareStatement("delete from class where 教室名称=?");
			ps.setString(1,sc_no);
			//System.out.println(s.getNo());
			//System.out.println(stu_code);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public int selectnum(String sc_no,HttpServletRequest req, HttpServletResponse resp) throws IOException {	//查询教室人数
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		int i=0;
		try {
			ps = conn.prepareStatement("select count(*) from student where sclass=?");
			ps.setString(1,sc_no);
			//System.out.println(s.getNo());
			//System.out.println(stu_code);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public ResultSet getSearch(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// 搜索

		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		String search = req.getParameter("class_search");
		ResultSet rs = null;
		System.out.println("查询内容"+search);
		if(search!="") {
		try {
			ps = conn.prepareStatement("select * from class where 教室名称=? or 所属专业=? or 所属院校=? ");
			ps.setString(1, search);
			ps.setString(2, search);
			ps.setString(3, search);
			//ps.setInt(4, search);
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
				session.setAttribute("SclassList", SclassList);
				resp.sendRedirect("class_list.jsp");
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
	}else {
		SclassDao f1 = new SclassDao();
		ResultSet rs1 = f1.getAllSclass(session, req, resp);
	}
		return rs;
	}
	
	
}
