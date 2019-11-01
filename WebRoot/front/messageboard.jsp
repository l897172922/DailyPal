<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%--  <base href="<%=basePath%>"> --%>

<title>留言板</title>

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
<style type="text/css">
.chk {
	margin: 0;
}
</style>
	<!-- Java Script
    ================================================== -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
	/* 页面加载 ajax */
	$(function(){
		//设置参数
		var param = {op:"query",currPage:"1",pageSize:"15"};
		$("tbody").empty();
		$.get("BoardServlet",param,function(data,status,xhr){
			if(status == "success"){
				//json字符串转对象
				var list = data;
				if(list=='请登录后重试'){
					alert(list);
					window.location.href="login.jsp"; 
				}else{
				var page = list[0];
				var total = page.total;
				var allPage = page.allPage;
				var currPage = page.currPage;
				var privPage = page.privPage;
				var nextPage = page.nextPage;
				
				var mesList = list[1];
				
				$.each(mesList,function(index,msg){
					var message = msg[0];
					var nickname = msg[1];
					$("tbody").append(
						"<tr><td>"+ nickname +"</td><td>"+ message.content +"</td><td>"+
						message.time +"</td><td><a href=\"javascript:;\" onclick=\"javascript:del("+ message.m_id +");\">删除</a></td></tr>");
				});
				$("#part-l").empty();
				$("#part-l").append("<li><a class=\"pgn__prev\" href=\"#0\" onclick=\"javascript:paging("+privPage+");\">Prev</a></li>");

				for(var i=1;i<=allPage;i++){

					if(i==currPage){
						$("#part-l").append("<li><a class=\"pgn__num current\" >"+ i +"</a></li>");
					}else{
						$("#part-l").append("<li><span class=\"pgn__num\" href=\"#\" onclick=\"javascript:paging("+ i +");\">"+ i +"</span></li>");
					}
				}
				$("#part-l").append("<li><a class=\"pgn__next\" href=\"#0\" onclick=\"javascript:paging("+nextPage+");\">Next</a></li>");			
			}}
		});
	});
	
	//分页
	function paging(curr){
		var param = {op:"query",currPage:curr,pageSize:"15"};
		$("tbody").empty();
		$.get("BoardServlet",param,function(data,status,xhr){
			if(status == "success"){
				//json字符串转对象
				var list = data;
				var page = list[0];
				var total = list.total;
				var allPage = list.allPage;
				var currPage = list.currPage;
				var privPage = list.privPage;
				var nextPage = list.nextPage;
				
				var mesList = list[1];
				
				$.each(mesList,function(index,msg){
					console.log(msg);
					var message = msg[0];
					var nickname = msg[1];
					$("tbody").append(
						"<tr><td>"+ nickname +"</td><td>"+ message.content +"</td><td>"+
						message.time +"</td><td><a href=\"javascript:;\" onclick=\"javascript:del("+ message.m_id +");\">删除</a></td></tr>");
				});
				$("#part-l").empty();
				$("#part-l").append(
					"<li><a class=\"pgn__prev\" href=\"#0\" onclick=\"javascript:paging("+privPage+");\">Prev</a></li>"	
				);
				
				for(var i=1;i<=allPage;i++){
					if(i==currPage){
						$("#part-l").append("<li><a class=\"pgn__num current\" >"+ i +"</a></li>");
					}else{
						$("#part-l").append("<li><span class=\"pgn__num\" href=\"#\" onclick=\"javascript:paging("+ i +");\">"+ i +"</span></li>");
					}
				}
				$("#part-l").append("<li><a class=\"pgn__next\" href=\"#0\" onclick=\"javascript:paging("+nextPage+");\">Next</a></li>");			
			}
		});
		
	}
	
	
  
	function del(id){
		var m_id = id;
		console.log(m_id);
		var param = {op:"del",m_id:m_id};
		$.ajax({
			url:"BoardServlet",
			type:"post",
			data:param,
			success:function(){
				location.reload();
				alert("删除成功");
			}
		});
	}
	
  </script>
</head>



<body class="ss-bg-white">

	<!-- preloader
    ================================================== -->

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
					<li><a href="FriendSsircleServlet?op=query">朋友圈</a></li>
					<li class="current"><a href="messageboard.jsp" class="current">留言板</a>
					</li>
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
			<main class="row content__page"> <section
				class="column large-full entry format-standard">

			<div class="content__page-header">
				<h1 class="display-1">留言板.</h1>
			</div>
			<!-- end content__page-header --> <!--留言板-->
			<div class="row add-bottom">

				<div class="column large-full">
					<p>
						.
					</p>

					<div class="table-responsive">

						<table>
							<thead>
								<tr>
									<th>昵称</th>
									<th width="60%">内容</th>
									<th>时间</th>
									<th width="10%" style="padding-left: 0;"></th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

					</div>

				</div>

			</div>
			<!-- end row --> <!--分页查询-->
			<div class="row">
				<div class="column large-full">
					<nav class="pgn">

					<ul id="part-l">

					</ul>
					</nav>
				</div>
			</div>
		</div>
		<!-- end s-content -->

		</section>

		</main>

	</div>
	<!-- end s-content -->

	<!-- footer
        ================================================== -->
	<footer class="s-footer footer">
	<div class="row">
		<div class="column large-full footer__content">
			<div class="footer__copyright">
				<span>© Copyright Typerite 2019 - More Templates <a
					href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a>
					- Collect from <a href="http://www.cssmoban.com/" title="网页模板"
					target="_blank">网页模板</a>
				</span> <span>Design by styleshout</span>
			</div>
		</div>
	</div>

	<div class="go-top">
		<a class="smoothscroll" title="Back to Top" href="#top"></a>
	</div>
	</footer>

	</div>
	<!-- end s-wrap -->



</body>
</html>
