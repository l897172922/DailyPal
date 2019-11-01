<%@page import="com.pal.listener.RegisterCountListener"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	//String u_name="";
	//String u_pwd="";
	
	//session.setAttribute(u_name, u_pwd);
	
	//session.setAttribute("RegisterCountListener",new RegisterCountListener(u_name,u_pwd));
	
	/* response.sendRedirect("charts.jsp"); */

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%-- <base href="<%=basePath%>"> --%>
    
    <title>登陆</title>
    
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
	 <script type="text/javascript">
		window.onload = function(){
			var al =${msg };
			if(al==null){
								
			}else{
			alert(al);
			}		 
		}
</script>
<%	
	String username = "";
	String password = "";
	Cookie[] c = request.getCookies();
	if(c==null){
		
	}else{
	for (int i = 0; i < c.length; i++) {
		if ("UserName".equals(c[i].getName())) {
			username = c[i].getValue();
			//System.out.println(username);
		} else if ("UserPwd".equals(c[i].getName())) {
			password = c[i].getValue();
			//System.out.println(password);
		}
	}
	}
%>
	<!-- Meta tag Keywords -->
	<!-- css files -->
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<!-- Style-CSS -->
	<link rel="stylesheet" href="css/fontawesome-all.css">
	<!-- Font-Awesome-Icons-CSS -->
	<!-- //css files -->
	<!-- web-fonts -->
	<!--<link href="http://maxcdn.bootstrapcdn.com/css?family=Josefin+Sans:100,100i,300,300i,400,400i,600,600i,700,700i" rel="stylesheet">
	<link href="http://maxcdn.bootstrapcdn.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">-->
	<!-- //web-fonts -->
	
	<!-- favicons
    ================================================== -->
		<link rel="apple-touch-icon" sizes="180x180" href="images/palhead/apple-touch-icon.png">
		<link rel="icon" type="image/png" sizes="32x32" href="images/palhead/favicon-32x32.png">
		<link rel="icon" type="image/png" sizes="16x16" href="images/palhead/favicon-16x16.png">
		

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
	
	
		<form action="UserServlet?op=login" method="post">
			<h2>登陆
				<i class="fas fa-level-down-alt"></i>
			</h2>
			<div class="form-style-agile">
				<label>
					<i class="fas fa-user"></i>
					用户名
				</label>
				<input placeholder="Username" name="UserName" type="text" id="UserName" value="<%=username%>">
				<p style="color:WHITE">${namemsg}</p>
			</div>
			<div class="form-style-agile">
				<label>
					<i class="fas fa-unlock-alt"></i>
					密码
				</label>
				<input placeholder="Password" name="Password" type="password" id="Password" value="<%=password%>">
				<p style="color:white">${pwdmsg}</p>
			</div>
			<!-- checkbox -->
			<div class="wthree-text">
				<ul>
					<li>
						<label class="anim">
							<input type="checkbox" class="checkbox" id="remenber" name="remenber" value="true">
							<span>记住我</span>
						</label>
					</li>
					<li>
						<a href="#">忘记密码?</a>
					</li>
				</ul>
			</div>
			<!-- //checkbox -->
			<input type="submit" value="登陆" style="margin-right: 30px;">
			<input type="button" value="注册" onclick="window.location.href='register.jsp'" style="margin-left: 30px;">
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
