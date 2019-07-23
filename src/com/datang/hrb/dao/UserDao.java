package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.datang.hrb.vo.User;

public class UserDao {
	public int saveUser(User user) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement("insert into user(accounts,password,phone) values(?,?,?)");
			ps.setString(1, user.getAccounts());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPhone());
			result = ps.executeUpdate();
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
		return result;
	}

	public String getUsersByAccounts(String accounts) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		String password = null;
		try {
			ps = conn.prepareStatement("select * from user where accounts=?");
			ps.setString(1, accounts);
			ResultSet rs = ps.executeQuery();
			if(rs!=null) {
			rs.next();
			password = rs.getString(2);
			System.out.println("mm" + password);
			}
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
		System.out.println("数库查询后的密码" + password);
		return password;
	}
	
	public int getPassword(String password,String accounts,String phone) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		int i=0;
		try {
			ps = conn.prepareStatement("update user set password=? where accounts=? and phone=?");
			ps.setString(1, password);
			ps.setString(2, accounts);
			ps.setString(3, phone);
			i= ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
	
	public int getUsername(String accounts) {			//查询用户名是否存在
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		String password = null;
		try {
			ps = conn.prepareStatement("select * from user where accounts=?");
			ps.setString(1, accounts);
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			if(rs!=null&&rs.next()) {
			//rs.next();
			return 1;
			}
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
		//System.out.println("数库查询后的密码" + password);
		return 0;
	}
	
}