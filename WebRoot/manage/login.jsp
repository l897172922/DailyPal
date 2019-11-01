<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="robots" content="all,follow">
<!-- Bootstrap CSS-->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome CSS-->
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<!-- Fontastic Custom icon font-->
<link rel="stylesheet" href="assets/css/fontastic.css">
<!-- Google fonts - Poppins -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
<!-- theme stylesheet-->
<link rel="stylesheet" href="assets/css/style.default.css"
	id="theme-stylesheet">
<!-- Custom stylesheet - for your changes-->
<link rel="stylesheet" href="assets/css/custom.css">
<!-- Favicon-->
<link rel="shortcut icon" href="assets/img/favicon.ico">
<style type="text/css">
.loginBtn {
	background-color: #796AEE; background-image : -moz-linear-gradient( to
	bottom, blue, #796AEE);
	border: 1px solid #98CCE7;
	border-radius: 20px;
	box-shadow: inset rgba(255, 255, 255, 0.6) 0 1px 1px, rgba(0, 0, 0, 0.1)
		0 1px 1px;
	color: white;
	/*登录*/
	cursor: pointer;
	/* float: right; */
	font: bold 13px Arial;
	padding: 10px 50px;
	background-image: -moz-linear-gradient(to bottom, blue, #796AEE);
}
</style>
<!-- Tweaks for older IEs-->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
</head>
<%
    String loginMsg = (String)session.getAttribute("loginMsg");



%>
<body>
	<div class="page login-page">

		<div class="container d-flex align-items-center">
			<div class="form-holder has-shadow">
				<div class="row">
					<!-- Logo & Information Panel-->
					<div class="col-lg-13">
						<div class="info d-flex align-items-center">
							<div class="content">
								<div class="logo">
									<h1>天天交友网</h1>
								</div>
								<p>让我们交最好的朋友</p>
							</div>
						</div>
					</div>
					<!-- Form Panel    -->
					<div class="col-lg-13 bg-white">
						<div class="form d-flex align-items-center">
							<div class="content">
								<form action="ManagerServlet" method="post"
									class="form-validate">
									<div class="form-group">
										<input id="login-username" placeholder="请输入管理员账号" type="text"
											name="mname" required
											data-msg="Please enter your username" class="input-material">
										<!-- 将登陆的op传给管理员servlet -->
										<input type="hidden" class="form-control" id="op" name="op"
											placeholder="" value="login">
									</div>
									<div class="form-group">
										<input id="login-password" placeholder="请输入密码"
											type="password" name="mpassword" required
											data-msg="Please enter your password" class="input-material">									
										<!-- <label for="login-password" class="label-material">Password</label> -->
									</div>
																	
									<button type="submit" class="loginBtn">Login</button>
									<label><%=loginMsg == null? "": loginMsg %></label>	
									<!-- <a id="login" href="index.jsp" class="btn btn-primary">Login</a> -->
									<!-- This should be submit button but I replaced it with <a> for demo purposes-->
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="copyrights text-center">
			<p>
				Design by <a href="#" class="external">BUG guard</a>
				<!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)-->
			</p>
		</div>
	</div>
	<!-- JavaScript files-->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/popper.js/umd/popper.min.js">
		
	</script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery.cookie/jquery.cookie.js">
		
	</script>
	<script src="assets/vendor/chart.js/Chart.min.js"></script>
	<script src="assets/vendor/jquery-validation/jquery.validate.min.js"></script>
	<!-- Main File-->
	<script src="js/front.js"></script>
</body>

</html>