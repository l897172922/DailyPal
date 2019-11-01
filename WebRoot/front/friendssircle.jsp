<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>朋友圈</title>

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
<!-- <link rel="stylesheet" href="css/fordetails.css" /> -->
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
<script src="js/jQuery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<!-- script
    ================================================== -->
<script src="js/modernizr.js"></script>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- favicons
    ================================================== -->
<link rel="apple-touch-icon" sizes="180x180"
	href="images/palhead/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="images/palhead/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="images/palhead/favicon-16x16.png">
<link rel="stylesheet" type="text/css" href="css/motel.css" />
<link rel="manifest" href="site.webmanifest">

</head>

<body class="ss-bg-white">
	<script type="text/javascript">
			$("#myModal").modal("hide");
			function Values(ID) {
				$("#blogid").val(ID);
			}
		</script>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">

					<!--header-->
					<div class="m-head">
						<h4 class="m-h4" id="myModalLabel"></h4>
					</div>
					<form action="DetailsServlet" method="post">
						<div class="modal-body">
							<p class="m-n">评论内容</p>
							<input type="text" name="commentcontent" id="" value=""
								class="m-inp" />
							<!-- 评论人的id -->
							<input type="hidden" id="userid" name="userid"
								value="${user.u_id }"> <input type="hidden" id="op"
								name="op" value="blogcomments">
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
			<li><a href="PlazaServlet?op=query" title="">广场</a></li>
			<!--查找-->
			<li><a href="find.jsp" title="">查找</a></li>
			<!--好友-->
			<li><a href="friendslist.jsp" title="">好友</a></li>

			<!--我的-->
			<li class="has-children current"><a href="#0" title=""
				class="sub-menu-is-open">我的</a>
				<ul class="sub-menu" style="display: block;">
					<li><a href="picture.jsp" target="_self">相册</a></li>
					<li class="current"><a href="FriendSsircleServlet?op=query">朋友圈</a></li>
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
			<main class="row s-styles fr-main"> <!-- styles
                ================================================== --> <section
				id="styles" class="column large-full" style="padding-left:10rem;">

			<div class="row section-intro add-bottom">

				<div class="column large-full">

					<h1 class="display-1">朋友圈.</h1>

					<p class="lead"></p>
					<a href="release.jsp" style="font-size: 30px">发布动态</a>

				</div>

			</div>

			<!-- 动态模板 -->
			<c:forEach items="${bloglist }" var="blog">
			<h3 class="r-name">${blog.users.nickName }</h3>
			<div class="r-block">
				<div style="width: 100%; height: 102px;">
					<img src="detailpic/${blog.picturePath }" class="r-img" />
					<p class="r-cent">${blog.text }</p>
				</div>

				<cite> <span>${blog.sendTime }</span><a href="DetailsServlet?op=querycomments&blogid=${blog.b_id}">详情</a> 
				</cite>

				<div class="r-like">
					<!--外部导入按钮-->
					<div class="d-nice">
						<div class="CTAs">
							<a href="FriendSsircleServlet?op=bloglike&blogid=${blog.b_id}"
										class="btn d-h" style="color:white;"><span>${blog.likeCounts }</span>喜欢</a>
									<%-- <a href="#" class="btn d-h"><span>${blog.commentCounts }</span><i class="fa fa-heart"></i>评论 </a> --%>
									<a class="btn d-h" data-toggle="modal" data-target="#myModal"
										style="color:white;" onclick="Values(${blog.b_id})"><span>${blog.commentCounts }</span>评论 </a>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>

		<!-- end row -->

		</section>
		<!-- end styles -->

		</main>
		
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

	<!-- Java Script
    ================================================== -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>

</body>
</html>
