<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>查找好友</title>

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
<!-- 查找 -->
<link rel="stylesheet" href="css/forfind.css" />

<style type="text/css" media="screen">
.s-styles {
	max-width: 1100px;
	padding-bottom: 12rem;
}

.f-main {
	width: 100%;
	margin: 0 auto;
}
</style>

<!-- script
    ================================================== -->
<script src="js/modernizr.js"></script>
<!-- 城市 -->
<script src="js/jQuery.js"></script>
<script src="js/city.js"></script>
<!-- favicons
    ================================================== -->
<link rel="apple-touch-icon" sizes="180x180"
	href="images/palhead/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="images/palhead/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="images/palhead/favicon-16x16.png">

<link rel="manifest" href="site.webmanifest">
<script type="text/javascript">
$(function(){
	$("#sub").click(function(){
		$("#find-result").empty();
		var nickName = $("#sampleInput").val();
		var sex = $("#sexes").val();
		var province = $("#province").val();
		var city = $("#city").val();
		var age = $("#location").val();
		var param = {nickName:nickName,sex:sex,province:province,city:city,age:age,currPage:"1"};
		$.ajax({
			url:"FindServlet",
			data:param,
			success:function(data){
				var user = data.list;
				var total = data.total;
				var allPage = data.allPage;
				var currPage = data.currPage;
				var privPage = data.privPage;
				var nextPage = data.nextPage;
				
				$("#find-result").append("<blockquote class=\"result\"><h3 style=\"margin-top: 30px;\">查找结果</h3><cite><a href=\"#0\">共"+ total +"条</a> </cite></blockquote>");
				$.each(user,function(index,user){
					$("#find-result").append("<div class=\"u-out\"><blockquote class=\"u-in\"><img src=\" ../headpic/"+user.headPic+" \" class=\"u-img\" /><div class=\"u-word\"><label class=\"u-nick\">"+ user.nickName +"</label><p class=\"u-age\"</p>"+ user.age +"岁|"+ user.address +"</p></div><div class=\"u-add\"><a class=\"btn--stroke u-btn\" href=\"javascript:;\" onclick=\"javascript:add("+ user.u_id +");\">+加好友</a></div></blockquote></div>");	
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
		
	});
});
//分页
function paging(curr){
	$("#find-result").empty();
	var setcurr = curr;
	var nickName = $("#sampleInput").val();
	var sex = $("#sexes").val();
	var province = $("#province").val();
	var city = $("#city").val();
	var age = $("#location").val();
	var param = {nickName:nickName,sex:sex,province:province,city:city,age:age,currPage:setcurr};
	$.ajax({
		url:"FindServlet",
		data:param,
		success:function(data){
			var user = data.list;
			var total = data.total;
			var allPage = data.allPage;
			var currPage = data.currPage;
			var privPage = data.privPage;
			var nextPage = data.nextPage;
			
			$("#find-result").append("<blockquote class=\"result\"><h3 style=\"margin-top: 30px;\">查找结果</h3><cite><a href=\"#0\">共"+ total +"条</a> </cite></blockquote>");
			$.each(user,function(index,user){
				$("#find-result").append("<div class=\"u-out\"><blockquote class=\"u-in\"><img src=\" ../headpic/"+user.headPic+" \" class=\"u-img\" /><div class=\"u-word\"><label class=\"u-nick\">"+ user.nickName +"</label><p class=\"u-age\"</p>"+ user.age +"岁|"+ user.address +"</p></div><div class=\"u-add\"><a class=\"btn--stroke u-btn\" href=\"javascript:;\" onclick=\"javascript:add("+ user.u_id +");\">+加好友</a></div></blockquote></div>");	
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

function add(id){
	console.log(id);
	var param = {op:"add",FriId:id};
	$.ajax({
		url:"UserServlet",
		data:param,
		success:function(data){
			alert(data);
		}
	});
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
				<a class="site-logo" href="index.jsp"> <img
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
			<li class="current"><a href="find.jsp" title="">查找</a></li>
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
		<div class="s-content content" style="">
			<main class="row s-styles f-main"> <!-- styles
                ================================================== --> <section
				id="styles" class="column large-full">

			<div class="row section-intro add-bottom">

				<div class="column large-full">

					<h1 class="display-1">查找</h1>

					<p class="lead">.</p>

				</div>

			</div>

			<div class="row">
				<!-- 查找 -->
				<div class="divwidth">
					<!-- <form> -->
					<div>
						<input type="text" placeholder="关键字/昵称" id="sampleInput"
							name="sampleInput"> <input class="btn--primary"
							type="submit" value="查找" id="sub">
					</div>
					<div style="clear: both;">
						<label class="lab-1">性别:</label>
						<div>
							<select id="sexes">
								<option value="不限">不限</option>
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
					</div>
					<div>
						<label class="lab-1">所在地:</label>
						<div>
							<select name="province" id="province">
								<option value="不限">不限</option>
							</select> <label for="province" class="lab-1">省</label>
						</div>
						<div>
							<select name="city" id="city">
								<option value="不限">不限</option>
							</select> <label for="city" class="lab-1">市/区</label>
						</div>
					</div>
					<label class="lab-1">年龄:</label>
					<div>
						<select id="location">
							<option value="不限">不限</option>
							<option value="0-17">18岁以下</option>
							<option value="18-22">18-22岁</option>
							<option value="23-26">23-26岁</option>
							<option value="27-35">27-35岁</option>
							<option value="35-100">35岁以上</option>
						</select>
					</div>
					<div></div>
					<!-- </form> -->

				</div>

				<hr />

				<!-- 查找结果 -->
				<div class="" id="find-result" style="width: 100%;"></div>
			</div>
			<!-- end row --> </section> <!-- end styles --> </main>
			<!--分页查询-->
			<div class="">
				<div class="column large-full">
					<nav class="pgn">

					<ul id="part-l">

					</ul>
					</nav>
				</div>
			</div>

			<!-- footer
        ================================================== -->
			<footer class="s-footer footer" style="padding: 0 10%;">
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
		<!-- end s-content -->

	</div>
	<!-- end s-wrap -->

	<!-- Java Script
    ================================================== -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>

</body>
</html>
