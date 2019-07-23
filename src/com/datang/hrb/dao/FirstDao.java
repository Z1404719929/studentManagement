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
				session.setAttribute("SclassList", SclassList);
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

}
