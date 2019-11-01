<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
     System.out.println(request.getAttribute("namelist"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>欢迎进入天天聊天交友管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>

<!-- 页面重复 -->
<%-- <c:if test="${empty blogList}">
	<c:import url="ManagerServlet?op=queryhot" />
</c:if>  --%>


<body>
	<div class="page">
		<!-- Main Navbar-->
		<div class="page-content d-flex align-items-stretch">
			<!-- Side Navbar -->
			<nav class="side-navbar"> <!-- Sidebar Header-->
			<div class="sidebar-header d-flex align-items-center">
				<div class="avatar">
					<!-- 管理员头像 -->
					<img src="assets/img/admin1.jpg" alt="..."
						class="img-fluid rounded-circle">
				</div>
				<div class="title">
					<h1 class="h4">${admin.a_name }</h1>
					<p>管理员</p>
				</div>
			</div>
			<!-- Sidebar Navidation Menus--> <span class="heading">hello</span>
			<ul class="list-unstyled">
				<li class="active"><a href="ManagerServlet?op=queryhot"> <i
						class="icon-home"></i>主页
				</a></li>
				<li><a href="#exampledropdownDropdown" aria-expanded="false"
					data-toggle="collapse"> <i class="icon-interface-windows"></i>管理
				</a>
					<ul id="exampledropdownDropdown" class="collapse list-unstyled ">
						<!-- <li><a href="usermanage.jsp">用户管理</a></li> -->
						<li><a href="ManagerServlet?op=queryuser">用户管理</a></li>
						<li><a href="ManagerServlet?op=queryblog">动态管理</a></li>
						<li><a href="ManagerServlet?op=querypic">相册管理</a></li>
						<li><a href="ManagerServlet?op=queryinform">举报管理</a></li>
					</ul></li>

				<li><a href="ManagerServlet?op=querychart"> <i class="fa fa-bar-chart"></i>统计
				</a></li>

				<li><a href="#exampledropdownDropdown2" aria-expanded="false"
					data-toggle="collapse"> <i class="icon-interface-windows"></i>帮助
				</a>
					<ul id="exampledropdownDropdown2" class="collapse list-unstyled ">
						
						<li><a href="404manage.jsp">404错误页面</a></li>
						<li><a href="500manage.jsp">500错误页面</a></li>
						<li><a href="blank.jsp">空白页面</a></li>
					</ul></li>
				
				<li><a href="login.jsp"> <i class="icon-interface-windows"></i>退出登录
				</a></li>
			</ul>

			</nav>
			<div class="content-inner">
				<!-- Page Header-->

				<!-- Dashboard Counts Section-->
				<section class="dashboard-counts no-padding-bottom">
				<div class="container-fluid">
					<div class="row bg-white has-shadow">
						<!-- Item -->
						<div class="col-xl-3 col-sm-6">
							<div class="item d-flex align-items-center">
								<div class="icon bg-violet">
									<i class="icon-user"></i>
								</div>
								<div class="title">
									<span>新用户
									</span>
									<div class="progress">
										<div role="progressbar" style="width: 25%; height: 4px;"
											aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"
											class="progress-bar bg-violet"></div>
									</div>
								</div>
								<div class="number">
									<!-- <strong>25</strong> -->
									<strong>${userNum }</strong>
								</div>
							</div>
						</div>
						<!-- Item -->
						<div class="col-xl-3 col-sm-6">
							<div class="item d-flex align-items-center">
								<div class="icon bg-red">
									<i class="icon-padnote"></i>
								</div>
								<div class="title">
									<span>新动态
									</span>
									<div class="progress">
										<div role="progressbar" style="width: 70%; height: 4px;"
											aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
											class="progress-bar bg-red"></div>
									</div>
								</div>
								<div class="number">
									<!-- <strong>70</strong> -->
									<strong>${blogNum }</strong>
								</div>
							</div>
						</div>
						<!-- Item -->
						<div class="col-xl-3 col-sm-6">
							<div class="item d-flex align-items-center">
								<div class="icon bg-green">
									<i class="icon-bill"></i>
								</div>
								<div class="title">
									<span>待处理的举报
									</span>
									<div class="progress">
										<div role="progressbar" style="width: 40%; height: 4px;"
											aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
											class="progress-bar bg-green"></div>
									</div>
								</div>
								<div class="number">
									<strong>${informNum }</strong>
								</div>
							</div>
						</div>
						<!-- Item -->

					</div>
				</div>
				</section>
				<!-- Dashboard Header Section    -->

				<!-- Projects Section-->
				<section class="projects no-padding-top">
				<div class="container-fluid">
					<!-- Project-->




				</div>
				</section>
				<!-- Client Section-->

				<!-- Feeds Section-->
				<section class="feeds no-padding-top">
				<div class="container-fluid">
					<div class="row">
						<!-- 热门动态-->
						<div class="col-lg-6">
							<div class="articles card">
								<div class="card-close">
									<div class="dropdown">
										<button type="button" id="closeCard4" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"
											class="dropdown-toggle">
											<i class="fa fa-ellipsis-v"></i>
										</button>
										<div aria-labelledby="closeCard4"
											class="dropdown-menu dropdown-menu-right has-shadow">
											<a href="#" class="dropdown-item remove"> <i
												class="fa fa-times"></i>Close
											</a> <a href="#" class="dropdown-item edit"> <i
												class="fa fa-gear"></i>Edit
											</a>
										</div>
									</div>
								</div>
								<!-- 热门动态 -->
								<div class="card-header d-flex align-items-center">
									<h2 class="h3">热门动态</h2>
									<div class="badge badge-rounded bg-green">${blogHot } New</div>
								</div>
								<div class="card-body no-padding">
									<c:forEach items="${blogHotList }" var="blogs">

										<div class="item d-flex align-items-center">
											<div class="image">
												<!-- 人物头像 -->
												<!-- <img src="assets/img/user3.jpg" alt="..."
													class="img-fluid rounded-circle"> -->
													<%-- <img src="../front/picture/${blogs.picturePath }" alt="..." --%>
													<img src="../headpic/${blogs.users.headPic }" alt="..."
													class="img-fluid rounded-circle">
											</div>
											<div class="text">
												<a href="#">
												<%-- <c:forEach items="${namelist }" var="name"> --%>
												<h3 class="h5"> ${blogs.users.nickName}  </h3>
												
												<%-- </c:forEach> --%>
													
												</a><small> ${blogs.text}  </small>
												<!-- <small align="right" >   </small> -->
												
												<span align="right" style="margin-left: 1200px;">${blogs.sendTime}</span> 
												<%-- <div class="date text-right">
													<span align="right">${blogs.sendTime}</span>  
												</div> --%>
											</div>
											
										</div>
										
									</c:forEach> 

								




						<!-- 		<div class="item d-flex align-items-center">
										<div class="image">
											<img src="assets/img/admin.jpg" alt="..."
												class="img-fluid rounded-circle">
										</div>
										<div class="text">
											<a href="#">
												<h3 class="h5">林俊杰</h3>
											</a><small>晚上谁有空一起去吃海底捞啊 </small>
										</div>
									</div>
									<div class="item d-flex align-items-center">
										<div class="image">
											<img src="assets/img/avatar-2.jpg" alt="..."
												class="img-fluid rounded-circle">
										</div>
										<div class="text">
											<a href="#">
												<h3 class="h5">胡歌</h3>
											</a><small>Posted on 5th June 2017 by Frank Williams. </small>
										</div>
									</div>
									<div class="item d-flex align-items-center">
										<div class="image">
											<img src="assets/img/avatar-3.jpg" alt="..."
												class="img-fluid rounded-circle">
										</div>
										<div class="text">
											<a href="#">
												<h3 class="h5">彭于晏</h3>
											</a><small>Posted on 5th June 2017 by Ashley Wood. </small>
										</div>
									</div>
									<div class="item d-flex align-items-center">
										<div class="image">
											<img src="assets/img/avatar-4.jpg" alt="..."
												class="img-fluid rounded-circle">
										</div>
										<div class="text">
											<a href="#">
												<h3 class="h5">雷佳音</h3>
											</a><small>Posted on 5th June 2017 by Jason Doe. </small>
										</div>
									</div>
									<div class="item d-flex align-items-center">
										<div class="image">
											<img src="assets/img/avatar-5.jpg" alt="..."
												class="img-fluid rounded-circle">
										</div>
										<div class="text">
											<a href="#">
												<h3 class="h5">岳云鹏</h3>
											</a><small>Posted on 5th June 2017 by Sam Martinez. </small>
										</div>
									</div> -->
								</div>
							</div>
						</div>
						<!--动态管理-->
						<div class="col-lg-6">
							<div class="card">
								<div class="card-close">
									<div class="dropdown">
										<button type="button" id="closeCard4" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"
											class="dropdown-toggle">
											<i class="fa fa-ellipsis-v"></i>
										</button>
										<div aria-labelledby="closeCard4"
											class="dropdown-menu dropdown-menu-right has-shadow">
											<a href="#" class="dropdown-item remove"> <i
												class="fa fa-times"></i>Close
											</a> <a href="#" class="dropdown-item edit"> <i
												class="fa fa-gear"></i>Edit
											</a>
										</div>
									</div>
								</div>
								<div class="card-header d-flex align-items-center">
									<h3 class="h4">动态管理</h3>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table class="table table-striped table-sm">
											<thead>
												<tr class="center"  >
												</tr>
											
												<tr>
													<th>动态编号</th>
													<th>发表者</th>
													<th>图片路径</th>
													<th>内容</th>
													<th>发表时间</th>
													<th>点赞数量</th>
													<th>评论数量</th>
													<!-- <th>操作维护</th> -->
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${blogList }" var="blogs" >
													<tr>
														<th scope="row">${blogs.b_id }</th>
														<td>${blogs.users.u_name }</td>
														<td>${blogs.picturePath }</td>
														<td>${fn:substring(blogs.text,0,10) }</td>
														<td>${blogs.sendTime }</td>
														<td>${blogs.commentCounts }</td>
														<td>${blogs.likeCounts }</td>
														<%-- <td>
															<!-- 封禁 -->
															<a onclick="return confirm('请确认是否删除（一旦删除不可恢复）')" href="ManagerServlet?op=forbidblog&b_id=${blogs.b_id }" class="btn btn-xs btn-secondary">封禁</a>
															
														</td> --%>
													
													</tr>
													
												</c:forEach>
											</tbody>
											
											<!-- 分页 -->
											<tfoot>
												<tr>
													<td colspan="7" align="center">
														<!-- 分页 -->
														 <ul class="pagination pagination-lg">
															<!-- <li><a href="#">&laquo;</a></li> -->
															<li><a href="ManagerServlet?op=queryhot&currPageBlog=${blogPage.privPage}&pageSizeBlog=${blogPage.pageSize}">&laquo;</a></li>
															
														     <c:forEach begin="1" end="${blogPage.allPage}" step="1" var="i">
														    	<c:if test="${blogPage.currPage==i}">
														    			<li class="active"><a href="ManagerServlet?op=queryhot&currPageBlog=${i}&pageSizeBlog=${blogPage.pageSize}">${i}</a></li>
														    	</c:if>
														    	
														    	<c:if test="${blogPage.currPage!=i}">
														    			<li><a href="ManagerServlet?op=queryhot&currPageBlog=${i}&pageSizeBlog=${blogPage.pageSize}">${i}</a></li>
														    	</c:if>
														   	
															</c:forEach>
															
														    <li><a href="ManagerServlet?op=queryhot&currPageBlog=${blogPage.nextPage}&pageSizeBlog=${blogPage.pageSize}">&raquo;</a></li>
														    <!-- <li><a href="#">&raquo;</a></li> -->
														</ul>
										
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!--用户管理-->
						<div class="col-lg-6">
							<div class="card">
								<div class="card-close">
									<div class="dropdown">
										<button type="button" id="closeCard2" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"
											class="dropdown-toggle">
											<i class="fa fa-ellipsis-v"></i>
										</button>
										<div aria-labelledby="closeCard2"
											class="dropdown-menu dropdown-menu-right has-shadow">
											<a href="#" class="dropdown-item remove"> <i
												class="fa fa-times"></i>Close
											</a> <a href="#" class="dropdown-item edit"> <i
												class="fa fa-gear"></i>Edit
											</a>
										</div>
									</div>
								</div>
								<div class="card-header d-flex align-items-center">
									<h3 class="h4">用户管理</h3>
								</div>
								
								<div class="card-body">
									<div class="table-responsive">
										<table class="table table-striped">
											<thead>
												
												<tr>
												
													<th>用户编号</th>
													<th>用户名</th>
													<th>昵称</th>
													<th>性别</th>
													<th>年龄</th>
													<!-- <th>操作维护</th> -->
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${userList }" var="user" >
													<tr>
														<th scope="row">${user.u_id }</th>
														<td>${user.u_name }</td>
														<td>${user.nickName }</td>
														<td>${user.sex }</td>
														<td>${user.age }</td>
														<%-- <td>
															<!-- 封禁 -->
															<a onclick="return confirm('请确认是否封禁')" href="ManagerServlet?op=forbiduser&u_id=${user.u_id }" class="btn btn-xs btn-secondary">封禁</a>
														</td> --%>
													</tr>
												
												</c:forEach>
												
											</tbody> 
											<!-- 分页 -->
											<tfoot>
												<tr>
													<td colspan="5" align="center">
														<!-- 分页 -->
														 <ul class="pagination pagination-lg">
															<!-- <li><a href="#">&laquo;</a></li> -->
															<li><a href="ManagerServlet?op=queryhot&currPage=${userPage.privPage}&pageSize=${userPage.pageSize}">&laquo;</a></li>
															
														     <c:forEach begin="1" end="${userPage.allPage}" step="1" var="i">
														    	<c:if test="${userPage.currPage==i}">
														    			<li class="active"><a href="ManagerServlet?op=queryhot&currPage=${i}&pageSize=${userPage.pageSize}">${i}</a></li>
														    	</c:if>
														    	
														    	<c:if test="${userPage.currPage!=i}">
														    			<li><a href="ManagerServlet?op=queryhot&currPage=${i}&pageSize=${userPage.pageSize}">${i}</a></li>
														    	</c:if>
														   	
															</c:forEach>
															
														    <li><a href="ManagerServlet?op=queryhot&currPage=${userPage.nextPage}&pageSize=${userPage.pageSize}">&raquo;</a></li>
														    <!-- <li><a href="#">&raquo;</a></li> -->
														</ul>
										
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!-- 相片管理 -->
						<div class="col-lg-6">
							<div class="card">
								<div class="card-close">
									<div class="dropdown">
										<button type="button" id="closeCard4" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"
											class="dropdown-toggle">
											<i class="fa fa-ellipsis-v"></i>
										</button>
										<div aria-labelledby="closeCard4"
											class="dropdown-menu dropdown-menu-right has-shadow">
											<a href="#" class="dropdown-item remove"> <i
												class="fa fa-times"></i>Close
											</a> <a href="#" class="dropdown-item edit"> <i
												class="fa fa-gear"></i>Edit
											</a>
										</div>
									</div>
								</div>
								<div class="card-header d-flex align-items-center">
									<h3 class="h4">相片管理</h3>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table class="table table-striped table-sm">
											<thead>
												
												<tr>
													<th>相册编号 </th>
													<th>上传者</th>
													<th>图片路径</th>
													<th>上传时间</th>
													<!-- <th>操作维护</th> -->
												</tr>
											</thead>
											<tbody>
											<c:forEach items="${picList }" var="picture" >
												
												<tr>
													<th scope="row">${picture.pictureId }</th>
													<td>${picture.users.u_name }</td>
													<td>${picture.path }</td>
													<td>${picture.time }</td>
													<!-- <td>
														<a href="#" class="btn btn-xs btn-secondary">封禁</a>
													</td> -->
												</tr>
												
											</c:forEach>
												
											</tbody>
											<!-- 分页 -->
											<tfoot>
												<tr>
													<td colspan="5" align="center">
														<!-- 分页 -->
														 <ul class="pagination pagination-lg">
															
															<li><a href="ManagerServlet?op=queryhot&currPagePic=${picPage.privPage}&pageSizePic=${picPage.pageSize}">&laquo;</a></li>
															
														     <c:forEach begin="1" end="${picPage.allPage}" step="1" var="i">
														    	<c:if test="${picPage.currPage==i}">
														    			<li class="active"><a href="ManagerServlet?op=queryhot&currPagePic=${i}&pageSizePic=${picPage.pageSize}">${i}</a></li>
														    	</c:if>
														    	
														    	<c:if test="${picPage.currPage!=i}">
														    			<li><a href="ManagerServlet?op=queryhot&currPagePic=${i}&pageSizePic=${picPage.pageSize}">${i}</a></li>
														    	</c:if>
														   	
															</c:forEach>
															
														    <li><a href="ManagerServlet?op=queryhot&currPagePic=${picPage.nextPage}&pageSizePic=${picPage.pageSize}">&raquo;</a></li>
														    
														</ul>
										
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!--举报管理-->
						<div class="col-lg-4">
							<div class="daily-feeds card">
								<div class="card-close">
									<div class="dropdown">
										<button type="button" id="closeCard7" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"
											class="dropdown-toggle">
											<i class="fa fa-ellipsis-v"></i>
										</button>
										<div aria-labelledby="closeCard7"
											class="dropdown-menu dropdown-menu-right has-shadow">
											<a href="#" class="dropdown-item remove"> <i
												class="fa fa-times"></i>Close
											</a> <a href="#" class="dropdown-item edit"> <i
												class="fa fa-gear"></i>Edit
											</a>
										</div>
									</div>
								</div>
								<div class="card-header">
									<h3 class="h4">被举报列表</h3>
								</div>
								<div class="card-body no-padding">
									<!-- Item-->
									<table>
									<tbody>
									
									<c:forEach items="${informList }" var="inform" >
										<div class="item">
											<div class="feed d-flex justify-content-between">
												<div class="feed-body d-flex justify-content-between">
													<a href="#" class="feed-profile"><img
														src="../headpic/${inform.users.headPic }" alt="person"
														class="img-fluid rounded-circle"></a>
													<div class="content">
														<h5>${inform.users.u_name }</h5>
														<span>被举报原因：${inform.rType.rt_name }</span>
														
														<div class="CTAs">
															<a onclick="return confirm('请确认是否封禁')" href="ManagerServlet?op=forbidinform&i_id=${inform.i_id }" class="btn btn-xs btn-secondary">封禁</a> 
 
															<a href="ManagerServlet?op=warning&u_id=${inform.users.u_id }" class="btn btn-xs btn-secondary">警告 </a> 
															<a onclick="return confirm('请确认是否释放')" href="ManagerServlet?op=forfreeinform&i_id=${informban.i_id }" class="btn btn-xs btn-secondary">释放</a>
														</div>
													</div>
												</div>
												<div class="date text-right">
													<small>${inform.time }</small>
												</div>
											</div>
										</div>
										
									</c:forEach>
									</tbody>
										<!-- 分页 -->
											<tfoot>
												<tr>
													<td colspan="1" align="center" style="width: 1500px" >
														<!-- 分页 -->
														 <ul class="pagination pagination-lg">
															<!-- <li><a href="#">&laquo;</a></li> -->
															<li><a href="ManagerServlet?op=queryhot&currPageInform=${informPage.privPage}&pageSizeInform=${informPage.pageSize}">&laquo;</a></li>
															
														     <c:forEach begin="1" end="${informPage.allPage}" step="1" var="i">
														    	<c:if test="${informPage.currPage==i}">
														    			<li class="active"><a href="ManagerServlet?op=queryhot&currPageInform=${i}&pageSizeInform=${informPage.pageSize}">${i}</a></li>
														    	</c:if>
														    	
														    	<c:if test="${informPage.currPage!=i}">
														    			<li><a href="ManagerServlet?op=queryhot&currPageInform=${i}&pageSizeInform=${informPage.pageSize}">${i}</a></li>
														    	</c:if>
														   	
															</c:forEach>
															
														    <li><a href="ManagerServlet?op=queryhot&currPageInform=${informPage.nextPage}&pageSizeInform=${informPage.pageSize}">&raquo;</a></li>
														    <!-- <li><a href="#">&raquo;</a></li> -->
														</ul>
										
												</tr>
											</tfoot>
									
									</table>
									
									
									<!-- Item-->
									<!-- <div class="item">
										<div class="feed d-flex justify-content-between">
											<div class="feed-body d-flex justify-content-between">
												<a href="#" class="feed-profile"><img
													src="assets/img/avatar-2.jpg" alt="person"
													class="img-fluid rounded-circle"></a>
												<div class="content">
													<h5>Frank Williams</h5>
													<span>Posted a new blog </span>
													<div class="full-date">
														<small>Today 5:60 pm - 12.06.2014</small>
													</div>
													<div class="CTAs">
														<a href="#" class="btn btn-xs btn-secondary">封禁</a> <a
															href="#" class="btn btn-xs btn-secondary">警告 </a> <a
															href="#" class="btn btn-xs btn-secondary">释放 </a>
													</div>
												</div>
											</div>
											<div class="date text-right">
												<small>5min ago</small>
											</div>
										</div>
									</div>
									Item
									<div class="item clearfix">
										<div class="feed d-flex justify-content-between">
											<div class="feed-body d-flex justify-content-between">
												<a href="#" class="feed-profile"><img
													src="assets/img/avatar-3.jpg" alt="person"
													class="img-fluid rounded-circle"></a>
												<div class="content">
													<h5>Ashley Wood</h5>
													<span>Posted a new blog </span>
													<div class="full-date">
														<small>Today 5:60 pm - 12.06.2014</small>
													</div>
													<div class="CTAs">
														<a href="#" class="btn btn-xs btn-secondary">封禁</a> <a
															href="#" class="btn btn-xs btn-secondary">警告 </a> <a
															href="#" class="btn btn-xs btn-secondary">释放 </a>
													</div>
												</div>
											</div>
											<div class="date text-right">
												<small>5min ago</small>
											</div>
										</div>
										<div class="quote has-shadow">
											<small>Lorem Ipsum is simply dummy text of the
												printing and typesetting industry. Lorem Ipsum has been the
												industry's standard dummy text ever since the 1500s. Over
												the years.</small>
										</div>
										<div class="CTAs pull-right">
											<a href="#" class="btn btn-xs btn-secondary"><i
												class="fa fa-thumbs-up"> </i>Like</a>
										</div>
									</div> -->
								</div>
							</div>
						</div>
					</div>
				</div>
				</section>
				<!-- Updates Section                                                -->

				<!-- Page Footer-->
				<footer class="main-footer">
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-6">
							<p>Copyright &copy; 2019.Company name All rights reserved</p>
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
	<script src="assets/js/charts-home.js"></script>
	<!-- Main File-->
	<script src="js/front.js"></script>
</body>
</html>
