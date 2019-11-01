<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>广场</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- CSS
    ================================================== -->
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/vendor.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<!--点赞-->
<link rel="stylesheet" type="text/css" href="css/fordetails.css" />
<link rel="stylesheet" type="text/css" href="css/fa.css" />

<style type="text/css" media="screen">
.s-styles {
	max-width: 100%;
	padding-bottom: 12rem;
}

.plaz {
	max-width: 30%;
}

#comm {
	margin-top: 10px;
	width: 90%;
}

.p-font {
	font-size: 14px;
}
</style>

<!-- script
    ================================================== -->
<script src="js/modernizr.js"></script>

<!-- favicons
    ================================================== -->
<link rel="apple-touch-icon" sizes="180x180"
	href="images/palhead/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="images/palhead/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="images/palhead/favicon-16x16.png">

<link rel="manifest" href="site.webmanifest">

</head>
<!-- 判断为空去调用servlet -->
<%-- <c:if test="${empty blogList}">
	<c:import url="PlazaServlet?op=query"/>
</c:if> --%>
<body class="ss-bg-white">

<script type="text/javascript">
			$("#myModal").modal("hide");
		function Values(ID){
			$("#blogid").val(ID);
		}

		</script>
		<!-- 动态模态框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">

					<!--header-->
					<div class="m-head">
						<h4 class="m-h4" id="myModalLabel"></h4>			
					</div>
					<form action="DetailsServlet" method="get">				
					<div class="modal-body">
						<p class="m-n">评论内容</p>
						<input type="text" name="commentcontent" id="" value="" class="m-inp" />
						<!-- 评论人的id -->
						<%-- <input type="hidden" id="userid" name="userid" value="${user.u_id }"> --%>
						<input type="hidden" id="op" name="op" value="blogcomments">
						<!-- 所评论的动态的id -->
						<input type="hidden" id="blogid" name="blogid">
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn m-sub" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn m-sub">提交</button>
					</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	<div id="top" class="s-wrap site-wrapper">

		<!-- site header
        ================================================== -->
		<header class="s-header header">

		<div class="header__top">
			<div class="header__logo">
				<a class="site-logo" href="index.html"> <img
					src="images/daily.gif" alt="Homepage">
				</a>
			</div>

		</div>
		
		<nav class="header__nav-wrap">

		<ul class="header__nav">
			<!--主页-->
			<li><a href="index.jsp" title="">主页</a></li>

			<!--广场-->
			<li class="current"><a href="PlazaServlet?op=query" title="">广场</a></li>
			<!--查找-->
			<li><a href="find.jsp" title="">查找</a></li>
			<!--好友-->
			<li><a href="friendslist.jsp" title="">好友</a></li>

			<!--我的-->
			<li class="has-children"><a href="#0" title="">我的</a>
				<ul class="sub-menu">
					<li><a href="picture.jsp" target="_self">相册</a></li>
					<li><a href="FriendSsircleServlet?op=query">朋友圈</a></li>
					<li><a href="messageboard.jsp">留言板</a></li>
					<li><a href="MyCenterServlet?op=query">个人中心</a></li>
				</ul></li>
			<!--动态-->
			<li><a href="MyDynamicServlet?op=query" title="">我的动态</a></li>

		</ul>
		<!-- end header__nav -->

		<ul class="header__social">
			<li class="ss-facebook"><a href="https://facebook.com/"> <span
					class="screen-reader-text">Facebook</span>
			</a></li>
			<li class="ss-twitter"><a href="#0"> <span
					class="screen-reader-text">Twitter</span>
			</a></li>
			<li class="ss-dribbble"><a href="#0"> <span
					class="screen-reader-text">Instagram</span>
			</a></li>
			<li class="ss-behance"><a href="#0"> <span
					class="screen-reader-text">Behance</span>
			</a></li>
		</ul>

		</nav> <!-- end header__nav-wrap --> </header>
		<!-- end s-header -->

		<!-- site content
        ================================================== -->
		<div class="s-content content">
			<main class="row s-styles"> <!-- styles
                ================================================== --> 
            <section id="styles" class="column large-full" style="padding-left:10rem;">

			<div class="row section-intro add-bottom">

				<div class="column large-full">

					<h1 class="display-1">热门广场</h1>

					<p class="lead">汇聚最近的热门动态</p>
					<a href="release.jsp" style="font-size: 30px">发布</a>
				</div>

			</div>

			<div class="row">

				<!-- 动态模板 -->
				<%-- <div class="column large-6 plaz">
					<h3>模板2(name)</h3>
					<blockquote cite="http://where-i-got-my-info-from.com">
						<cite> <a href="#0">John 时间11:21:33 ESV</a>
						</cite> <img src="images/avatars/user-04.jpg" width="50%" />
						<div id="comm">
							<a href="#" class="btn d-h"><i class="fa fa-thumbs-up"> </i>喜欢</a>
							<a href="#" class="btn d-h"><i class="fa fa-heart"> </i>评论 </a>
						</div>
						<p>
							内容<a>详情</a>
						</p>
					</blockquote>
				</div> --%>
				<c:forEach items="${blogList}" var="blogList">

					<!-- 动态模板 -->
					<div class="column large-6 plaz" style="margin-bottom:50px;">
						<h3 style="margin-left:60px;">${blogList.users.nickName}</h3>
						<blockquote cite="http://where-i-got-my-info-from.com">
							<cite> <a href="#0">${blogList.sendTime}</a>
							</cite> <img src="../headpic/${blogList.users.headPic}" width="50%" />
							<div id="comm">
								<a href="DetailsServlet?op=bloglike&blogid=${blogList.b_id}" class="btn d-h" style="color:white"><span>${blogList.likeCounts }</span>喜欢</a>
								<a class="btn d-h" data-toggle="modal" data-target="#myModal"
									style="color:white;" onclick="Values(${blogList.b_id})"><span>${blogList.commentCounts }</span>评论
								</a>
							</div>
							<p class="p-font">${blogList.text}
							</p><a href="DetailsServlet?op=querycomments&blogid=${blogList.b_id}">【详情】</a>
						</blockquote>
					</div>

				</c:forEach>


			</div>
			<!-- end row --> </section> <!-- end styles --> </main>
			

		</div>
		<!-- end s-content -->

		<!-- footer
        ================================================== -->
		<footer class="s-footer footer">
		<div class="row">
			<div class="column large-full footer__content">
				<div class="footer__copyright">
					<span>© Copyright BugGurad 2019 - More Templates <a
						href="index.html" target="_blank" title="Bug护卫队">Bug护卫队</a> -
						Collect from <a href="http://www.cssmoban.com/" title="网页模板"
						target="_blank">版权所有</a>
					</span> <span>Design by BugGurad</span>
				</div>
			</div>
		</div>

		<div class="go-top">
			<a class="smoothscroll" title="Back to Top" href="#top"></a>
		</div>
		</footer>

	</div>
	<!-- end s-wrap -->

	<!-- Java Script
    ================================================== -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
