<%@page import="com.pal.entity.OnlineCounter"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>欢迎进入天天聊天交友管理系统</title>
		<meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="robots" content="all,follow">
		<!-- Bootstrap CSS-->
		<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
		<!-- Font Awesome CSS-->
		<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
		<!-- Fontastic Custom icon font-->
		<link rel="stylesheet" href="assets/css/fontastic.css">
		<!-- Google fonts - Poppins -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
		<!-- theme stylesheet-->
		<link rel="stylesheet" href="assets/css/style.default.css" id="theme-stylesheet">
		<!-- Custom stylesheet - for your changes-->
		<link rel="stylesheet" href="assets/css/custom.css">
		<!-- Favicon-->
		<link rel="shortcut icon" href="assets/img/favicon.ico">
		<!-- Tweaks for older IEs-->
		<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
        
        <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>
	</head>

	<body>
		<div class="page">
			<!-- Main Navbar-->

			<div class="page-content d-flex align-items-stretch">
				<!-- Side Navbar -->
				<nav class="side-navbar">
					<!-- Sidebar Header-->
					<div class="sidebar-header d-flex align-items-center">
						<div class="avatar"><img src="assets/img/admin1.jpg" alt="..." class="img-fluid rounded-circle"></div>
						<div class="title">
							<h1 class="h4">${admin.a_name }</h1>
							<p>管理员</p>
						</div>
					</div>
					<!-- Sidebar Navidation Menus--><span class="heading">hello</span>
					<ul class="list-unstyled">
						<li>
							<a href="ManagerServlet?op=queryhot"> <i class="icon-home"></i>主页 </a>
						</li>
						<li>
							<a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>管理 </a>
							<ul id="exampledropdownDropdown" class="collapse list-unstyled ">
								<li>
									<a href="ManagerServlet?op=queryuser">用户管理</a>
								</li>
								<li>
									<a href="ManagerServlet?op=queryblog">动态管理</a>
								</li>
								<li>
									<a href="ManagerServlet?op=querypic">相册管理</a>
								</li>
								<li>
									<a href="ManagerServlet?op=queryinform">举报管理</a>
								</li>
							</ul>
						</li>
						
						<li class="active">
							<a href="ManagerServlet?op=querychart"> <i class="fa fa-bar-chart"></i>统计 </a>
						</li>
						
						<li><a href="#exampledropdownDropdown2" aria-expanded="false"
					data-toggle="collapse"> <i class="icon-interface-windows"></i>帮助
				</a>
					<ul id="exampledropdownDropdown2" class="collapse list-unstyled ">
						
						<li><a href="404manage.jsp">404错误页面</a></li>
						<li><a href="500manage.jsp">500错误页面</a></li>
						<li><a href="blank.jsp">空白页面</a></li>
					</ul></li>
						
						<li>
							<a href="login.jsp"> <i class="icon-interface-windows"></i>退出登录 </a>
						</li>
					</ul>
				</nav>
				<div class="content-inner">
					<!-- Page Header-->

					<!-- Breadcrumb-->

					<!-- Charts Section-->
					<section class="charts">
						<div class="container-fluid">
							<div class="row">
								<!-- Line Charts-->
								
								
								
								<div class="col-lg-13">
									<div class="line-chart-example card">
										<div class="card-close">
											<div class="dropdown">
												<button type="button" id="closeCard1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
												<div aria-labelledby="closeCard1" class="dropdown-menu dropdown-menu-right has-shadow">
													<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
													<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
												</div>
											</div>
										</div>
										<div class="card-header d-flex align-items-center" >
											<h3 class="h4">网站流量：<%=OnlineCounter.getAccessCount() %></h3>
										</div>
										<%-- <div class="card-header d-flex align-items-center" >
											<h3 class="h4">用户注册人数：<%= (application.getAttribute("onlineUserList")) %></h3>
										</div> --%>
										<div class="card-body">
											
											<canvas id="lineChartExample2"></canvas>
										</div>
									</div>
								</div>
								<div class="col-lg-13">
									<div class="line-chart-example card no-margin-bottom">
										<div class="card-close">
											<div class="dropdown">
												<button type="button" id="closeCard2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
												<div aria-labelledby="closeCard2" class="dropdown-menu dropdown-menu-right has-shadow">
													<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
													<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
												</div>
											</div>
										</div>
										<!-- <div class="card-header d-flex align-items-center">
											<h3 class="h4">用户在线人数</h3>
										</div> -->
										<!-- 测试 -->
										<div class="card-header d-flex align-items-center">
											<h3 class="h4">用户在线人数：<%= application.getAttribute("numSessions")%></h3>
							
											<%-- <h3 class="h4">用户在线人数：<%= OnlineCounter.getOnlineCount()%> --%></h3>
										</div>
										<div class="card-body">
											<canvas id="lineChartExample1"></canvas>
										</div>
									</div>
									<%-- <div class="line-chart-example card">
										<div class="card-close">
											<div class="dropdown">
												<button type="button" id="closeCard3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
												<div aria-labelledby="closeCard3" class="dropdown-menu dropdown-menu-right has-shadow">
													<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
													<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
												</div>
											</div>
										</div>
										<div class="card-body">
											<canvas id="lineChartExample"></canvas>
										</div>
									</div> --%>
								</div>
								
								<div class="col-lg-13">
									<div class="bar-chart-example card">
										<div class="card-close">
											<div class="dropdown">
												<button type="button" id="closeCard6" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
												<div aria-labelledby="closeCard6" class="dropdown-menu dropdown-menu-right has-shadow">
													<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
													<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
												</div>
											</div>
										</div>
										<div class="card-header d-flex align-items-center">
											<h3 class="h4">我们的目标</h3>
											<!-- <img alt="" src="https://pic.nova.net.cn/images/index/map.gif" style="height: 22rem;" > -->
										</div>
										<div class="card-body" style="margin-bottom: 38px;">
											<img alt="" src="https://pic.nova.net.cn/images/index/map.gif" style="height: 25rem; width:50rem" >
										</div>
										<%-- <div class="card-body">
											<canvas id="barChartExample"></canvas>
										</div> --%>
									</div>
								</div>
								
								<!-- Bar Charts-->
								<div class="col-lg-13">
									<div class="bar-chart-example card no-margin-bottom">
										<div class="card-close">
											<div class="dropdown">
												<button type="button" id="closeCard4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
												<div aria-labelledby="closeCard4" class="dropdown-menu dropdown-menu-right has-shadow">
													<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
													<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
												</div>
											</div>
										</div>
										<div class="card-header d-flex align-items-center">
											<h3 class="h4">用户注册人数</h3>
										</div>
										<div class="card-body">
											<canvas id="barChart1"></canvas>
										</div>
										<%-- <div class="card-body">
											<canvas id="barChart1"></canvas>
										</div> --%>
									</div>
									<%-- <div class="line-chart-example card">
										<div class="card-close">
											<div class="dropdown">
												<button type="button" id="closeCard5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
												<div aria-labelledby="closeCard5" class="dropdown-menu dropdown-menu-right has-shadow">
													<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
													<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
												</div>
											</div>
										</div>
										 <div class="card-body">
											<canvas id="barChart2"></canvas>
										</div>
									</div> --%>
								</div>
								
								<!-- Doughnut Chart -->
								<%-- <div class="col-lg-13">
									<div class="pie-chart-example card">
										<div class="card-close">
											<div class="dropdown">
												<button type="button" id="closeCard7" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
												<div aria-labelledby="closeCard7" class="dropdown-menu dropdown-menu-right has-shadow">
													<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
													<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
												</div>
											</div>
										</div>
										<div class="card-header d-flex align-items-center">
											<h3 class="h4">Doughnut  Chart Example</h3>
										</div>
										<div class="card-body">
											<canvas id="doughnutChartExample"></canvas>
										</div>
									</div>
								</div>
								<!-- Pie Chart -->
								<div class="col-lg-13">
									<div class="pie-chart-example card">
										<div class="card-close">
											<div class="dropdown">
												<button type="button" id="closeCard8" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
												<div aria-labelledby="closeCard8" class="dropdown-menu dropdown-menu-right has-shadow">
													<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
													<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
												</div>
											</div>
										</div>
										<div class="card-header d-flex align-items-center">
											<h3 class="h4">Pie  Chart Example</h3>
										</div>
										<div class="card-body">
											<canvas id="pieChartExample"></canvas>
										</div>
									</div>
								</div>
								<!-- Polar Chart-->
								<div class="col-lg-13">
									<div class="polar-chart-example card">
										<div class="card-close">
											<div class="dropdown">
												<button type="button" id="closeCard9" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
												<div aria-labelledby="closeCard9" class="dropdown-menu dropdown-menu-right has-shadow">
													<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
													<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
												</div>
											</div>
										</div>
										<div class="card-header d-flex align-items-center">
											<h3 class="h4">Polar Chart Example</h3>
										</div>
										<div class="card-body">
											<canvas id="polarChartExample"></canvas>
										</div>
									</div>
								</div>
								<!-- Radar Chart-->
								<div class="col-lg-13">
									<div class="radar-chart-example card">
										<div class="card-close">
											<div class="dropdown">
												<button type="button" id="closeCard10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
												<div aria-labelledby="closeCard10" class="dropdown-menu dropdown-menu-right has-shadow">
													<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
													<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
												</div>
											</div>
										</div>
										<div class="card-header d-flex align-items-center">
											<h3 class="h4">Radar Chart Example</h3>
										</div>
										<div class="card-body">
											<canvas id="radarChartExample"></canvas>
										</div>
									</div>
								</div> --%>
							</div>
						</div>
					</section>
					<!-- Page Footer-->
					<footer class="main-footer">
						<div class="container-fluid">
							<div class="row">
								<div class="col-sm-6">
									<p>Copyright &copy; 2019.Company name All rights reserved
									</p>
								</div>
								<div class="col-sm-6 text-right">
									<p></p>
									<!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)-->
								</div>
							</div>
						</div>
					</footer>
				</div>
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
		<script src="assets/js/charts-custom.js"></script>
		<!-- Main File-->
		<script src="assets/js/front.js"></script>
	</body>

</html>