<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>好友列表</title>

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

<!--=====手动==========-->

<link rel="stylesheet" href="css/forlist.css" />
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


<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

<script type="text/javascript">
/* 页面加载 ajax */
$(function(){
	//设置参数
	var param = {op:"query",currPage:"1",pageSize:"5"};
	$.get("FriendListServlet",param,function(data,status,xhr){
		if(status == "success"){
			//json字符串转对象
			var list = data;
			if(list=='请登录后重试'){
				alert(list);
				window.location.href="login.jsp"; 
			}else{
			var page = list[0];
			var currPage = page.currPage;
			var pageSize = page.pageSize;
			var allPage = page.allPage;
			var privPage = page.privPage;
			var nextPage = page.nextPage;
			var total = page.total;
			var friList = list[1];
			$.each(friList,function(index,fri){
				$("tbody").append("<tr><td width=\"3\"><li style=\"color: green;\"></li></td><td width=\"60\"><img src=\" ../headpic/"
					+ fri.headPic +"\" class=\"f-head\" style=\"margin: 0;\"/></td><td width=\"180\">"
					+ fri.nickName +"</td><td class=\"f-sig\">"
					+ fri.introdution  +"</td><td width=\"150\"><a class=\"btn btn--stroke full-width f-send\" href=\"chat.jsp?friend="
					+ fri.u_id +" \" target=\"_blank\">发消息</a></td><td style=\"width:50\"><a href=\"javascript:;\" onclick=\"javascript:del("+ fri.u_id +");\" >删除</a></td></tr>");
			});
			
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
		}
	});
});





//分页
function paging(curr){
	$("tbody").empty();
	$("#part-l").empty();
	var param = {op:"query",currPage:curr,pageSize:"5"};
	$.get("FriendListServlet",param,function(data,status,xhr){
		if(status == "success"){
			//json字符串转对象
			var list = data;
			var page = list[0];
			var currPage = page.currPage;
			var pageSize = page.pageSize;
			var allPage = page.allPage;
			var privPage = page.privPage;
			var nextPage = page.nextPage;
			var total = page.total;
			var friList = list[1];
			console.log(friList);
			$.each(friList,function(index,fri){
				$("tbody").append("<tr><td width=\"3\"><li style=\"color: green;\"></li></td><td width=\"60\"><img src=\" ../headpic/"
					+ fri.headPic +"\" class=\"f-head\" style=\"margin: 0;\"/></td><td width=\"180\">"
					+ fri.nickName +"</td><td class=\"f-sig\">"
					+ fri.introdution  +"</td><td width=\"150\"><a class=\"btn btn--stroke full-width f-send\" href=\"chat.jsp?friend="
					+ fri.u_id +" \" target=\"_blank\">发消息</a></td><td style=\"width:50\"><a href=\"javascript:;\" onclick=\"javascript:del("+ fri.u_id +");\" >删除</a></td></tr>");
			});
			
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
	if(confirm("确定要删除该好友吗?")){
		var param = {op:"del",friId:id};
		$.ajax({
			url:"FriendListServlet",
			type:"post",
			data:param,
			success:function(data){
			}
		}); 
		location.reload();
	}
}	

  
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
			<li class="current"><a href="friendslist.jsp" title="">好友</a></li>

			<!--我的-->
			<li class="has-children"><a href="#0" title="">我的</a>
				<ul class="sub-menu">
					<li><a href="picture.jsp" target="_self">相册</a></li>
					<li><a href="FriendSsircleServlet?op=query">朋友圈</a></li>
					<li><a href="messageboard.jsp" class="current">留言板</a></li>
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
				<h1 class="display-1"></h1>
			</div>
			<!-- end content__page-header --> <!--留言板-->
			<div class="row add-bottom">

				<div class="column large-full">

					<h3>好友列表</h3>
					<p>我的个性签名.</p>

					<div class="table-responsive">

						<table id="list">
							<thead>

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

</body>


	<!-- Java Script
    ================================================== -->
	 <script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>

</html>
