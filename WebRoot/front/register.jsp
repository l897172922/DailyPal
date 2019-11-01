<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%-- <base href="<%=basePath%>"> --%>
    
  <title>用户注册页面</title>
   <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	
	<!-- Meta tag Keywords -->
	<!-- css files -->
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<!-- Style-CSS -->
	<link rel="stylesheet" href="css/fontawesome-all.css">
	<!-- Font-Awesome-Icons-CSS -->
	<!-- //css files -->
	<!-- web-fonts -->
	<link href="http://maxcdn.bootstrapcdn.com/css?family=Josefin+Sans:100,100i,300,300i,400,400i,600,600i,700,700i" rel="stylesheet">
	<link href="http://maxcdn.bootstrapcdn.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
	<!-- //web-fonts -->
	<!-- favicons
    ================================================== -->
		<link rel="apple-touch-icon" sizes="180x180" href="images/palhead/apple-touch-icon.png">
		<link rel="icon" type="image/png" sizes="32x32" href="images/palhead/favicon-32x32.png">
		<link rel="icon" type="image/png" sizes="16x16" href="images/palhead/favicon-16x16.png">
 <script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">

/* $(document).ready(function(){
	
	$("formId").submit(function(){
		//判断用户名是否为空
		var username = $("#username").val();
		if(username.length == 0){
			$("#errorName").html("用户名不能为空！");
			return false;
		}
		if(username.length < 2 || username.length > 12){
			$("#errorName").html("用户名长度为2-12位之间");
			return false;
		}
		//判断密码是否为空
		var password = $("#password").val();
		if(password.length == 0){
			$("#errorPassword").html("密码不能为空！");
			return false;
		}
		if(password.length < 2 || password.length > 16){
			$("#errorPassword").html("密码长度为2-16位之间");
			return false;
		}
		//判断确认密码是否与密码一致
		var repeatPassword = $("#repeatPassword").val();
		if(repeatPassword.length == 0){
			$("#errorRepeatPassword").html("确认密码不能为空！");
			return false;
		}
		if(password != repeatPassword){
			$("#errorRepeatPassword").html("确认密码输入不正确，请重新输入！");
			return false;	
		}
		
	});

}); */

</script>
  </head>
  
  <body>
    <!-- bg effect -->
	<div id="bg">
		<canvas></canvas>
		<canvas></canvas>
		<canvas></canvas>
	</div>
	<!-- //bg effect -->
	<!-- title -->
	<h1>天天交友</h1>
	<!-- //title -->
	<!-- content -->
	<div class="sub-main-w3">
		<form action="UserServlet?op=register" id="formId" method="post">
			<h2>用户注册
				<i class="fas fa-level-down-alt"></i>
			</h2>
			<div class="form-style-agile">
				<label>
					<i class="fas fa-user"></i>
					用户名
				</label>
				<input placeholder="请输入用户名" id="username" name="username" type="text" required="">
				<span id="errorName" ></span>
				<p style="color:white">${regi}</p>
			</div>
			
			<div class="form-style-agile">
				<label>
					<i class="fas fa-unlock-alt"></i>
					密码
				</label>
				<input placeholder="请输入密码" id="password" name="password" type="text"  required="">
				<span id="errorPassword" ></span>
				<p style="color:white">${regipwd}</p>
			</div>
			<div class="form-style-agile">
				<label>
					<i class="fas fa-unlock-alt"></i>
					确认密码
				</label>
				<input placeholder="请再次输入密码" id="repeatPassword" name="repeatPassword" type="text" required="">
				<span id="errorRepeatPassword" ></span>
				<p style="color:white">${regipwd1}</p>
			</div>
			<!-- checkbox -->
			
			<!-- //checkbox -->
			<input type="submit" value="注册" style="margin-right: 30px;">
			<input type="button" value="取消" onclick="window.location.href='login.jsp'" style="margin-left: 30px;">
		</form>
	</div>
	<!-- //content -->

	<!-- copyright -->
	<div class="footer">
		<p>Copyright &copy; 2019.BugGuard All rights reserved.<a target="_blank" href="#">天天交友</a></p>
	</div>
	<!-- //copyright -->

	<!-- Jquery -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<!-- //Jquery -->

	<!-- effect js -->
	<script src="js/canva_moving_effect.js"></script>
	<!-- //effect js -->

  </body>
</html>
