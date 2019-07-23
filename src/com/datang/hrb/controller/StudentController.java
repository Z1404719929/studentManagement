package com.datang.hrb.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datang.hrb.dao.ConnectionUtil;
import com.datang.hrb.dao.SclassDao;
import com.datang.hrb.dao.StudentDao;
import com.datang.hrb.dao.UserDao;
import com.datang.hrb.service.LoginService;
import com.datang.hrb.service.RegisterService;
import com.datang.hrb.service.SclassService;
import com.datang.hrb.service.StudentService;
import com.datang.hrb.service.Impl.LoginServiceImpl;
import com.datang.hrb.service.Impl.RegisterServiceImpl;
import com.datang.hrb.service.Impl.SclassServiceImpl;
import com.datang.hrb.service.Impl.StudentServiceImpl;
import com.datang.hrb.util.ImgCodeUtil;
import com.datang.hrb.util.MD5Util;
import com.datang.hrb.vo.Sclass;
import com.datang.hrb.vo.Student;
import com.datang.hrb.vo.User;

public class StudentController extends HttpServlet {
	static String stu_code = null;
	static String sc_code = null;
	String img_code = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * System.out.println("run in doGet"); resp.sendRedirect("ok.jsp");
		 */
		System.out.println("run in doGet");

		// 调用工具类生成的验证码和验证码图片
		Map<String, Object> codeMap = ImgCodeUtil.generateCodeAndPic();

		// 将四位数字的验证码保存到Session中。
		HttpSession session = req.getSession();
		session.setAttribute("code", codeMap.get("code").toString());

		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no	-cache");
		resp.setDateHeader("Expires", -1);

		resp.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos;
		try {
			sos = resp.getOutputStream();
			ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
			sos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * /////////////////////////////////////////////////////////////////////////////
		 * //// // 接受表单内容 req.setCharacterEncoding("UTF-8"); String no =
		 * req.getParameter("no"); String name = req.getParameter("name"); // 向页面传值
		 * req.setAttribute("no", no); req.setAttribute("name", name); // 业务需要
		 * StudentService stu = new StudentService(); // 查询消息列表并传给页面
		 * req.setAttribute("studentList", stu.queryStudentList(no, name)); // 向页面跳转
		 * req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
		 * /////////////////////////////////////////////////////////////////////////////
		 * ///
		 */
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

		String accounts = req.getParameter("accounts");
		String password = req.getParameter("password");
		String phone= req.getParameter("phone");
		String imgcode = req.getParameter("imgcode");
		// String username = req.getParameter("username");
		// String password = req.getParameter("password");

		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf(".do"));
		HttpSession session = req.getSession();

		if (action.equals("register")) {
			if (accounts != "" && password != "") {
				RegisterService registerService = new RegisterServiceImpl();
				User user = new User();
				user.setAccounts(accounts);
				user.setPassword(MD5Util.getMD5(password.getBytes()));
				user.setPhone(phone);
				int i = registerService.saveUser(user);
				if (i == 1) {
					resp.sendRedirect("login.jsp");
				} else {
					resp.sendRedirect("error.jsp");
				}
			} else {
				resp.sendRedirect("error.jsp");
			}
		} else if (action.equals("login")) {
			// img_code = req.getParameter("img_code");
			// System.out.println(img_code);
			System.out.println("login");
			User user = new User();
			user.setAccounts(accounts);
			System.out.println(accounts);
			UserDao u = new UserDao();
			if (u.getUsername(accounts) == 1) {
				user.setPassword(MD5Util.getMD5(password.getBytes()));
				LoginService loginService = new LoginServiceImpl();
				System.out.println("6");
				if (loginService.login(user) == "student_list.jsp") {
					// img_code = req.getParameter("img_code");
					// System.out.println("session保存的验证码=="+session.getAttribute("code"));
					if (session.getAttribute("code").equals(req.getParameter("img_code"))) {
						// System.out.println("session保存的验证码=="+session.getAttribute("code"));
						session = req.getSession();
						session.setAttribute("accounts", accounts);

						/* resp.sendRedirect("student_list.jsp"); */
						System.out.println("4");
						StudentDao sd = new StudentDao();
						ResultSet rs = sd.getAllStudent(session, req, resp);
						// password = MD5Util.getMD5(password.getBytes());
					} else {
						System.out.println("验证码问题");
						resp.sendRedirect("yzm_fail.jsp");
					}
				} else {
					System.out.println("密码问题");
					resp.sendRedirect("password_fail.jsp");
				}
			}else {
				System.out.println("用户名没有");
				resp.sendRedirect("accounts_fail.jsp");
			}
		}

		/*
		 * if (action.equals("login")) { StudentDao sd = new StudentDao(); ResultSet rs
		 * = sd.getAllStudent(session, req, resp); // SclassDao scd = new SclassDao();
		 * // ResultSet rs = scd.getAllSclass(session, req, resp);
		 * 
		 * }
		 */

		if (action.equals("add")) { // 增加学生
			StudentService studentService = new StudentServiceImpl();
			int i = studentService.addStudent(s, session);
			if (i == 1) {
				StudentDao sd = new StudentDao();
				ResultSet rs = sd.getAllStudent(session, req, resp);
				// resp.sendRedirect("student_list.jsp");
			} else {
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
				resp.sendRedirect("add_class.jsp");
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

		if (action.equals("resetp")) { // 修改密码
			accounts = req.getParameter("accounts");
			password = req.getParameter("password");
			phone = req.getParameter("phone");
			UserDao ud = new UserDao();
			User u = new User();
			u.setPassword(MD5Util.getMD5(password.getBytes()));
			password = u.getPassword();
			System.out.println(password);
			int i = ud.getPassword(password, accounts, phone);
			if (i == 1) {
				resp.sendRedirect("login.jsp");
			} else {
				resp.sendRedirect("error.jsp");
			}
		}

		if (action.equals("search")) { // 搜索功能
			StudentDao sd = new StudentDao();
			ResultSet rs = sd.getSearch(session, req, resp);
		}

	}
}