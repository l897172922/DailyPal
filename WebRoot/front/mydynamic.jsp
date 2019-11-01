<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>我的动态</title>

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

<!--手动样式-->
<link rel="stylesheet" href="css/forsircle.css" />

<!--点赞-->
<link rel="stylesheet" type="text/css" href="css/fordetails.css" />
<link rel="stylesheet" type="text/css" href="css/fa.css" />

<style type="text/css" media="screen">
.s-styles {
	max-width: 1100px;
	padding-bottom: 12rem;
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

<!-- Java Script
    ================================================== -->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/plugins.js"></script>
<script src="js/main.js"></script>

</head>

<body class="ss-bg-white">


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
			<li><a href="PlazaServlet?op=query" title="">广场</a></li>
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
			<li class="current"><a href="MyDynamicServlet?op=query" title="">我的动态</a></li>
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
			<main class="row s-styles fr-main"> <!-- styles
                ================================================== --> <section
				id="styles" class="column large-full">

			<div class="row section-intro add-bottom">

				<div class="column large-full">

					<h1 class="display-1">我的动态.</h1>

					<p class="lead">
						<a href="release.jsp">发布动态</a>
					</p>

				</div>

			</div>
			<c:forEach items="${list}" var="blog">
				<!-- 动态模板 -->
				<div class="r-block">
					<div style="width: 100%; height: 102px;">
						<img src="../headpic/${blog.users.headPic }" class="r-img" />
						<p class="r-cent">
							${fn:substring(blog.text,0,199)} 
						</p>
					</div>
					<cite> <span>${blog.sendTime}</span>
					</cite>
					<div class="r-like">
						<!--外部导入按钮-->
						<div class="d-nice">
							<div class="CTAs">
								<a href="DetailsServlet?op=manageblog&blogid=${blog.b_id}" class="btn d-h" style="color:white">详情</a> <a href="DetailsServlet?op=delblog&blogid=${blog.b_id}" class="btn d-h" style="color:white">删除
								</a>
							</div>
						</div>
					</div>
				</div>
				<!-- end row -->
			</c:forEach> </section> <!-- end styles --> </main>
			<!-- <script>
			$(function() {
				$("#size").change(function() {
					window.location.href = "NewsInfoServlet?op=query&currPage=${newsPage.currPage}&pageSize="
					+ $(this).val();
				});
			});
			</script> -->
			<!--分页查询-->
			<div class="column large-full">
				<nav class="pgn">

				<ul>
					<!-- 上一页 -->
					<li><a class="pgn__prev" href="MyDynamicServlet?currPage=${page.privPage}&pageSize=${page.pageSize}">Prev</a></li>
					<!-- 当前页 -->
					<c:forEach begin="1" end="${page.allPage}" step="1" var="i">
					<!-- 当前页样式 -->
						<c:if test="${page.currPage==i}">
							<li><a class="pgn__num current" href="MyDynamicServlet?currPage=${i}&pageSize=${page.pageSize}">${i}</a></li>
						</c:if>
					<!-- 普通样式 -->
						<c:if test="${page.currPage!=i}">
							<li><a class="pgn__num" href="MyDynamicServlet?currPage=${i}&pageSize=${page.pageSize}">${i}</a></li>
						</c:if>
					</c:forEach>
		
					<!-- 下一页 -->
					<li><a class="pgn__next" href="MyDynamicServlet?currPage=${page.nextPage}&pageSize=${page.pageSize}">Next</a></li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- end s-content -->

		<!-- footer
        ================================================== -->
		<footer class="s-footer footer">
		<div class="column large-full footer__content">
			<div class="footer__copyright">
				<span>© Copyright BugGurad 2019 - More Templates <a
					href="index.html" target="_blank" title="Bug护卫队">Bug护卫队</a> -
					Collect from <a href="http://www.cssmoban.com/" title="网页模板"
					target="_blank">版权所有</a>
				</span> <span>Design by BugGurad</span>
			</div>
		</div>

		<div class="go-top">
			<a class="smoothscroll" title="Back to Top" href="#top"></a>
		</div>
		</footer>

	</div>
	<!-- end s-wrap -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>
