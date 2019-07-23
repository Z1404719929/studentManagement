<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.datang.hrb.vo.*" %>
    
    <% List<Student> StudentList=(ArrayList<Student>)session.getAttribute("StudentList"); %>
    <% Student s=(Student)session.getAttribute("s"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理系统</title>
<style>
*{margin: 0; padding: 0;}
body{
	background-color: #f3f5f7;
}
#header {
  // width:fill-available可以让元素的宽度表现为默认的block水平元素的尺寸表现  实际上盒子是display: inline-block;
  width: -webkit-fill-available;
  width: -moz-fill-available;
  width: -moz-available; /* FireFox目前这个生效 */
  width: fill-available;

  height: 60px;
  background: #35373a;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 999; // 顶部菜单栏选中样式
  }
  .lab-header {
    width: 1200px;
    margin: 0 auto;
    height: 60px;
    background: #35373a;
    line-height: 60px;
    color: #ffffff;
    overflow: hidden;
    }
    .title {
        display: inline-block;
        font-size: 18px;
      }
    .lab-menu {
      width: 890px;
      margin-left: 25px;
      display: inline-flex;
      justify-content: flex-start;
      }
      li {
        color: #fff;
        cursor: pointer;
        height: 60px;
        box-sizing: border-box;
        text-align: center;
        margin-left: 30px;
        list-style: none;
      }
     .lab-menu li a{
      	color: #fff;
      	text-decoration: none;
      }
        .active::after {
          content: "";
          display: block;
          width: 22px;
          height: 4px;
          margin: -15px auto 0;
          border-radius: 2px;
          background-color: #00abf1;
        }
      .exit {
        padding-right: 5px;
        margin-left: 40px;
        font-size: 14px;
        color: #fff;
        height: 60px;
        text-decoration: none;
      }
      .addstu{
    	height: 34px;
	    background: #2b96e5;
	    color: #fff;
	    font-size: 14px;
	    line-height: 10px;
	    display: block;
	    border-radius: 4px;
	    border: none;
	    padding: 12px;
	    cursor: pointer;
	    margin-bottom: 20px;
      }
      .main{	
	    position: relative;
	    background: white;
	    margin: 80px auto 0;
	    border-radius: 6px;
	    width: 980px;
	    border: 1px solid #fff;
	    overflow: hidden;
	    padding: 20px;
    }
	form{
		margin-top: 15px;
	}
	.main span{
		width: 100px;
		text-align: right;
		font-size: 18px;
		display: inline-block;
		margin: 20px;
	}
	.main input{
		width: 217px;
	    height: 34px;
	    border-raduis: 6px;
	}
	.main .save{
		width: 80px;
		height: 34px;
	    background: #2b96e5;
	    color: #fff;
	    font-size: 16px;
	    line-height: 10px;
	    border-radius: 4px;
	    border: none;
	    padding: 10px 15px;
	    cursor: pointer;
	    display: inline-block;
	    margin: 20px 0 0 180px;
	}
</style>

</head>
<body>
	<div id="header">
      <div class="lab-header">
         <span class="title">学生信息管理系统</span>
        <ul class="lab-menu" >
          <li class="menu-home"><a href="student_list.jsp">学生管理</a></li>
          <li class="menu-home" ><a href="class_list.jsp">班级管理</a></li>
         </ul>
          <span>XXX</span>
          <a href="login.jsp"  class="exit" >退出</a>
      </div>
   </div>
   <div class="main">
   		<a href="student_list.jsp">返回学生列表页</a>
   		<form method="post" action="redact_ok.do">
   			<span>学员学号：</span> <input type="text" value="<%=s.getNo()%>"  name="no"required="required"  /><br />
   			<span>姓名：</span> <input type="text"  value="<%=s.getName()%>" name="name"required="required"  /><br />
   			<span>性别：</span> <input type="text"  value="<%=s.getSex()%>"name="sex" required="required"  /><br />
   			<span>班级名称：</span> <input type="text"   value="<%=s.getSclass()%>"name="sclass"  required="required"  /><br />
   			<span>专业名称：</span> <input type="text"  value="<%=s.getMajor()%>"name="major"  required="required"  /><br />
   			<span>学校名称：</span> <input type="text"  value="<%=s.getSchool()%>"name="school"  required="required"  /><br />
   			<span>邮箱：</span> <input type="text"  value="<%=s.getEmail()%>"name="email"  required="required"  /><br />
   			<span>手机号：</span> <input type="text"  value="<%=s.getPhone()%>"name="phone" required="required" /><br />
   			<input class="save" type="submit" value="保存" />
   		</form>
   </div>
	<script>
		var test = window.location.href;
		if((test.substring(test.lastIndexOf('/')+1,test.indexOf(".jsp"))) == "redact_stu"){
			var stu = document.getElementsByClassName("menu-home");
			stu[0].className += " active";
		}
	</script>
</body>
</html>