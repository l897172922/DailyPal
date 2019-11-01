<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>发布动态</title>

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
<link rel="stylesheet" type="text/css" href="css/forcenter.css" />

<script src="js/jquery-3.2.1.min.js"></script>

	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>

<script src="js/jQuery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/mycity.js" type="text/javascript" charset="utf-8"></script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script> 
<script type="text/javascript">
$(function(){
	$("#submit").click(function(){
		var file = $("#inpo").prop('files');
		var text = $("#content").val();
		var index = file.length;

		if(index==0){
			var param = {op:"releaseCon",text:text};
			$.ajax({
				url:"PlazaServlet",
				data:param,
				type:"post",
				success : function(){
					alert("发表成功");
					window.location.href="PlazaServlet?op=query"; 
				}
			});
			
		}else{
			var formData = new FormData();
			formData.append("file",file[0]);
			formData.append("text",text);
			$.ajax({
				url:"PlazaServlet?op=release",
				data:formData,
				type:"post",
				processData : false,
				contentType : false,
				success : function(){
					alert("发表成功");
					window.location.href="release.jsp"; 
				}
			});
		}
		
	});

});
$(document).ready(function() {
	$("#head-b").click(function() {
		$("#inpo").click();
	});
});
</script>

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
			<main class="row content__page fr-main"> <section
				class="column large-full entry format-standard">

			<div class="content__page-header">
				<h1 class="display-1">发布动态</h1>
			</div>
			<!-- end content__page-header -->

			<div style="width: 100%; height: 600px;">

				<div id="pic"
					style="height:200px; width:20%; text-align: center; float: left; margin: 20px 50px">
					<!-- <input type="button" id="head-b" value="添加图片" /> -->
					<input type="file" name="inpo" id="inpo"  style="display:block;"/>

				</div>


				<div style="float: left; width: 90%; margin: 0 5%">
					<p class="ptop">内容:</p>
					<textarea name="content" id="content" rows="" cols="" class="areat"></textarea>

					<div id="but" align="center">
						<input type="submit" name="submit" id="submit" value="发布"
							class="btu" style="margin-right:50px;width:300px;height: 60px;" />

					</div>

				</div>
			</section> </main>

		</div>
		<!-- end s-content -->

		<!-- footer
        ================================================== -->
		<footer class="s-footer footer">
		<div class="column large-full footer__content">
			<div class="footer__copyright">
				<span>© Copyright Typerite 2019 - More Templates <a href=""
					target="_blank" title="BugGuard">Bug护卫队</a> - Collect from <a
					href="" title="bug" target="_blank">BubGuard</a>
				</span> <span>Design by styleshout</span>
			</div>
		</div>

		<div class="go-top">
			<a class="smoothscroll" title="Back to Top" href="#top"></a>
		</div>
		</footer>

	</div>
	<!-- end s-wrap -->
<script src="js/jquery-3.2.1.min.js"></script>

<script src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>

	<!-- Java Script
    ================================================== -->
	

</body>
</html>
