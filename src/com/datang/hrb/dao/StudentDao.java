package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.datang.hrb.vo.Student;

public class StudentDao {
	
	public ResultSet getAllStudent() {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from student");
			rs = ps.executeQuery();
		} catch (SQLException e) {
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
	
	public int addstudent(Student s) {
		Connection conn=ConnectionUtil.getConnection();
		PreparedStatement ps=null;
		int i=0;
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
		return i;
		}
	
		
	
	}

