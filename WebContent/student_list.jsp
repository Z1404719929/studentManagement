<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        .active::after {
          content: "";
          display: block;
          width: 22px;
          height: 4px;
          margin: -15px auto 0;
          border-radius: 2px;
          background-color: #00abf1;
        }
      a {
        color: #fff;
        height: 60px;
        text-decoration: none;
      }
      .exit {
        padding-right: 5px;
        margin-left: 40px;
        font-size: 14px;
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
	    width: 1200px;
	    border: 1px solid #fff;
	    overflow: hidden;
	    padding: 20px;
    }
    tr:nth-child(n){
    	background-color: #fff;
    }
     tr:nth-child(2n+1){
    	background-color: #f5f7f9;
    }
    th{
    	background-color: #dbdbdb;
    }
    th,td{
    	text-align: center;
    	padding: 7px 0;
    }
    td{
    	width: 120px;
    }
    .gender{
    	width: 80px;
    }
    .school-name{
    	width: 200px;
    }
    .email{
    	width: 180px;
    }
    .redact, .del{
    	height: 27px;
	    background: #2b96e5;
	    color: #fff;
	    font-size: 12px;
	    line-height: 10px;
	    border-radius: 4px;
	    border: none;
	    padding: 10px;
	    cursor: pointer;
	    display: inline-block;
    }
    .paging{
    	width: 600px;
    	margin: 30px auto 40px;
    }
    .paging .pages{
    	width: 20px;
	    height: 28px;
	    margin: 0 5px;
	    min-width: 30px;
	    border-radius: 4px;
	    cursor: default;
	    padding: 0 4px;
	    background: #fff;
	    font-size: 13px;
	    line-height: 28px;
	    box-sizing: border-box;
	    vertical-align: top;
	    display: inline-block;
	    text-align: center;
	    font-weight: bold;
	    color: #606266;
	    cursor: pointer;
    }
     .paging .pages:hover{
     	color: #fff;
     	background-color: #409EFF
    }
    .paging input{
    	width: 50px;
    	height: 24px;
    	text-align: center;
    	margin-left: 15px;
    }
    .linkto{
    	height: 28px;
	    background: #2b96e5;
	    color: #fff;
	    font-size: 12px;
	    line-height: 10px;
	    border-radius: 4px;
	    border: none;
	    padding: 10px 15px;
	    cursor: pointer;
	    display: inline-block;
    }
    .total{
    	font-size: 14px;
    	margin-left: 10px;
    }
</style>

</head>
<body>
	<div id="header">
      <div class="lab-header">
         <span class="title">学生信息管理系统</span>
        <ul class="lab-menu" >
          <li class="menu-home">学生管理</li>
          <li class="menu-home" > <a href="class_list.jsp">班级管理</a></li>
         </ul>
          <span>XXX</span>
          <a href class="exit" >退出</a>
      </div>
   </div>
   <div class="main">
   		<button class="addstu"><a href="add_stu.jsp">新增学生</a></button>
   		<table border="1"  cellpadding="0" cellspacing="0">
   			<thead>
   				<tr>
   					<th>学员学号</th>
   					<th>学员姓名</th>
   					<th>性别</th>
   					<th>班级名称</th>
   					<th>专业名称</th>
   					<th>学校名称</th>
   					<th>邮箱</th>
   					<th>手机号</th>
   					<th>操作</th>
   				</tr>
   			</thead>
   			<tbody>
   			<%for(int i = 0; i<20; i++){ %>
   				<tr>
   					<td>2017053285</td>
   					<td>王二毛</td>
   					<td class="gender">男</td>
   					<td>大数据xxxx班</td>
   					<td>大数据</td>
   					<td class="school-name">学校名称</td>
   					<td class="email">123456@123.com</td>
   					<td>12345678910</td>
   					<td>
   						<button class="redact"><a href="redact_stu.jsp">编辑</a></button>
   						<button class="del">删除</button>
   					</td>
   				</tr>
   				<%} %>
   			</tbody>
   		</table>
   </div>
	<div class="paging">
		<%for(int i = 0; i<5; i++){ %>
			<span class="pages"><%=i+1 %></span>
			<%} %>
			<input type="text" />
			<button class="linkto">跳转</button>
			<span class="total">共   X页</span>
	</div>
	<script>
		var test = window.location.href;
		if((test.substring(test.lastIndexOf('/')+1,test.indexOf(".jsp"))) == "student_list"){
			var stu = document.getElementsByClassName("menu-home");
			stu[0].className += " active";
		}
	</script>
</body>
</html>