<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>天天聊天交友网</title>

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
<link rel="stylesheet" href="css/indexmain.css">

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
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.default.css" />
<link rel="stylesheet" href="css/font-awesome.min.css" />

<link rel="stylesheet" href="css/fontastic.css" />
<link rel="stylesheet" type="text/css" href="css/fornav.css" />


<script type="text/javascript" src="js/front.js"></script>


<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
	/* 页面加载 ajax */
	$(function(){
		//设置参数
		var param = {op:"query"};
		$.get("IndexServlet",param,function(data,status,xhr){
			if(status == "success"){
				if(data.length==5){
					var list = data[0];
					var fr = data[1];
					var frNum = data[2];
					var chat = data[3];
					var chNum = data[4];
					
					
					//好友请求
					$("#ul-list").append("<li class=\"nav-item dropdown\"><a id=\"notifications\" rel=\"nofollow\" data-target=\"#\" href=\"javascript:;\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\" class=\"nav-link\"><i class=\"fa nav-i\">未读消息</i><span class=\"badge bg-red badge-corner\">"+ frNum +"</span></a><ul id=\"ul-child\" aria-labelledby=\"notifications\" class=\"dropdown-menu\">");
					$.each(fr,function(index,fr){
						$("#ul-child").append("<li><a rel=\"nofollow\"  class=\"dropdown-item\"><div class=\"notification\"><div class=\"notification-content\"><i class=\"bg-green\">详</i>"+ fr.users.nickName +"请求加为好友</div><div class=\"notification-time\"><small><input type=\"button\" value=\"接受\" onclick=\"javascript:request(1,"+ fr.fr_id +","+ fr.users.u_id +");\" style=\"height:30px; width:70px;margin:0;padding:0;line-height:30px;\"/><input type=\"button\" value=\"拒绝\" onclick=\"javascript:request(2,"+ fr.fr_id +","+ fr.users.u_id +");\" style=\"height:30px; width:70px;margin:0;padding:0;line-height:30px;\"/></small></div></div></a></li>");
					});
					if(fr.length!=0){
						$("#ul-child").append("<li><a onclick=\"javascript:lgonreR();\" rel=\"nofollow\" href=\"javascript:;\" class=\"dropdown-item all-notifications text-center\"><strong>忽&nbsp;略&nbsp;全&nbsp;部</strong></a></li>");
					}else{
						$("#ul-child").append("<li><a style=\"text-content:center;\">暂无消息</a></li>");
					}
					$("#ul-list").append("</ul></li>");
					
					//聊天消息
					$("#ul-list").append("<li class=\"nav-item dropdown\"><a id=\"messages\"	rel=\"nofollow\" data-target=\"#\" href=\"#\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\" class=\"nav-link\"><i class=\"fa nav-i\">好友消息</i><span class=\"badge bg-orange badge-corner\">"+ chNum +"</span></a><ul id=\"ul-childt\" aria-labelledby=\"notifications\" class=\"dropdown-menu\">");
					
					$.each(chat,function(index,chat){
						$("#ul-childt").append("<li><a href=\"chat.jsp?friend="+ chat.users.u_id +" \" rel=\"nofollow\" target=\"_blank\" class=\"dropdown-item d-flex\"><div class=\"msg-profile\"  href=\"chat.jsp?friend="+ chat.users.u_id +" \"><img src=\" ../headpic/"+ chat.users.headPic +"\" alt=\"...\" class=\"img-fluid rounded-circle\"  href=\"chat.jsp?friend="+ chat.users.u_id +" \"></div><div class=\"msg-body\" ><h3 class=\"nav-fr\"  href=\"chat.jsp?friend="+ chat.users.u_id +" \">"+ chat.users.nickName +"</h3><span  href=\"chat.jsp?friend="+ chat.users.u_id +" \">有新消息</span></div></a></li>");});
					if(chat.length!=0){
						$("#ul-childt").append("<li><a onclick=\"javascript:lgonreM();\" rel=\"nofollow\" href=\"javascript:;\" class=\"dropdown-item all-notifications text-center\"><strong>忽&nbsp;略&nbsp;全&nbsp;部</strong></a></li>");
					}else{
						$("#ul-childt").append("<li><a style=\"text-content:center;\">暂无消息</a></li>");
					}
					$("#ul-list").append("</ul></li>");
					
					//退出登录
					$("#ul-list").append("<li class=\"nav-item\"><a href=\"login.jsp\" class=\"nav-link logout\"><span class=\"d-none d-sm-inline\">退出登录</span></a></li>");					
					//主页推荐
					$.each(list,function(index,user){$("#sjtj").append("<article style=\"hetght:570px;\" class=\"masonry__brick entry format-standard animate-this\"><div class=\"entry__thumb\" style=\"width:300px; height:300px; \"><a href=\"DetailsServlet?op=query&userid="+user.u_id+"\" class=\"entry__thumb-link\"><img src=\"../headpic/"+ user.headPic +"\" srcset=\"../headpic/"+ user.headPic + "1x,../headpic/"+ user.headPic +" 2x\" style=\"width:300px; height:300px; \" ></a></div><div class=\"entry__text\"><div class=\"entry__header\"><h2 class=\"entry__title\"><a href=\"DetailsServlet?op=query&userid="+user.u_id+"\">"+ user.nickName +"</a></h2><div class=\"entry__meta\"><span class=\"entry__meta-cat\"><a href=\"\">"+ user.sex +"</a><a href=\"\">"+ user.age +"</a></span><span class=\"entry__meta-date\"><a href=\"\">"+ user.address +"</a></span></div></div><div class=\"entry__excerpt\"><p>"+ user.introdution +"</p></div></div></article>");});
					$("#sjtj").append("<article class=\"masonry__brick entry format-link animate-this\"><div class=\"entry__thumb\"><div class=\"link-wrap\"><h2>广告Powerful web & Wordpress hosting. Guaranteed.Starting at $2.59/mo!</h2><cite> <a target=\"_blank\"href=\"https://www.dreamhost.com/r.cgi?287326\">https://www.dreamhost.com</a></cite></div></div></article>");
				
				}else{
					var list = data;
					//登陆 注册
					$("#ul-list").append("<li class=\"nav-item\"><a href=\"login.jsp\" class=\"nav-link logout\"><span class=\"d-none d-sm-inline\">登录</span></a></li><li class=\"nav-item\"><a href=\"register.jsp\" class=\"nav-link logout\"><span class=\"d-none d-sm-inline\">注册</span></a></li>");
					$.each(list,function(index,user){$("#sjtj").append("<article style=\"hetght:570px;\" class=\"masonry__brick entry format-standard animate-this\"><div class=\"entry__thumb\" style=\"width:300px; height:300px; \"><a href=\"DetailsServlet?op=query&userid="+user.u_id+"\" class=\"entry__thumb-link\"><img src=\"../headpic/"+ user.headPic +"\" srcset=\"../headpic/"+ user.headPic + "1x,../headpic/"+ user.headPic +" 2x\" style=\" width:300px; hetght:300px;\" ></a></div><div class=\"entry__text\"><div class=\"entry__header\"><h2 class=\"entry__title\"><a href=\"DetailsServlet?op=query&userid="+user.u_id+"\">"+ user.nickName +"</a></h2><div class=\"entry__meta\"><span class=\"entry__meta-cat\"><a href=\"\">"+ user.sex +"</a><a href=\"\">"+ user.age +"</a></span><span class=\"entry__meta-date\"><a href=\"\">"+ user.address +"</a></span></div></div><div class=\"entry__excerpt\"><p>"+ user.introdution +"</p></div></div></article>");});
					$("#sjtj").append("<article class=\"masonry__brick entry format-link animate-this\"><div class=\"entry__thumb\"><div class=\"link-wrap\"><h2>广告Powerful web & Wordpress hosting. Guaranteed.Starting at $2.59/mo!</h2><cite> <a target=\"_blank\"href=\"https://www.dreamhost.com/r.cgi?287326\">https://www.dreamhost.com</a></cite></div></div></article>");
				}
			}
		});
		
		//重复刷新导航栏
		setInterval(function(){
			readstate();
		},6000);
	});
	
	//好友请求处理
	function request(datas,fr_id,u_id){
		param = {op:"re",datas:datas,fr_id:fr_id,u_id:u_id};
		$.ajax({
			url:"IndexServlet",
			type:"post",
			data:param,
			success:function(data){
				location.reload();
				
			}
		});
	}
	
	//忽略全部请求
	function lgonreR(){
		var param = {op:"lgonreR"};
		$.ajax({
			url:"IndexServlet",
			type:"post",
			data:param,
			success:function(data){
				location.reload();	
			}
		});
	}
	//忽略全部消息
	function lgonreM(){
		var param = {op:"lgonreM"};
		$.ajax({
			url:"IndexServlet",
			type:"post",
			data:param,
			success:function(data){
				location.reload();	
			}
		});
		
	}
	
	//获取未读请求消息
	//获取未读好友消息
	function readstate(){
		var param = {op:"query"};
		$.get("IndexServlet",param,function(data,status,xhr){
			if(status == "success"){
				if(data.length==5){
					var list = data[0];
					var fr = data[1];
					var frNum = data[2];
					var chat = data[3];
					var chNum = data[4];
					$("#ul-list").empty();
					 //好友请求
					$("#ul-list").append("<li class=\"nav-item dropdown\"><a id=\"notifications\" rel=\"nofollow\" data-target=\"#\" href=\"javascript:;\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\" class=\"nav-link\"><i class=\"fa nav-i\">未读消息</i><span class=\"badge bg-red badge-corner\">"+ frNum +"</span></a><ul id=\"ul-child\" aria-labelledby=\"notifications\" class=\"dropdown-menu\">");
					$.each(fr,function(index,fr){
						$("#ul-child").append("<li><a rel=\"nofollow\"  class=\"dropdown-item\"><div class=\"notification\"><div class=\"notification-content\"><i class=\"bg-green\">详</i>"+ fr.users.nickName +"请求加为好友</div><div class=\"notification-time\"><small><input type=\"button\" value=\"接受\" onclick=\"javascript:request(1,"+ fr.fr_id +","+ fr.users.u_id +");\" style=\"height:30px; width:70px;margin:0;padding:0;line-height:30px;\"/><input type=\"button\" value=\"拒绝\" onclick=\"javascript:request(2,"+ fr.fr_id +","+ fr.users.u_id +");\" style=\"height:30px; width:70px;margin:0;padding:0;line-height:30px;\"/></small></div></div></a></li>");
					});
					$("#ul-child").append("<li><a onclick=\"javascript:lgonreR();\" rel=\"nofollow\" href=\"javascript:;\" class=\"dropdown-item all-notifications text-center\"><strong>忽&nbsp;略&nbsp;全&nbsp;部</strong></a></li>");
					$("#ul-list").append("</ul></li>");
					
					//聊天消息
					$("#ul-list").append("<li class=\"nav-item dropdown\"><a id=\"messages\"	rel=\"nofollow\" data-target=\"#\" href=\"#\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\" class=\"nav-link\"><i class=\"fa nav-i\">好友消息</i><span class=\"badge bg-orange badge-corner\">"+ chNum +"</span></a><ul id=\"ul-childt\" aria-labelledby=\"notifications\" class=\"dropdown-menu\">");
					
					$.each(chat,function(index,chat){
						$("#ul-childt").append("<li><a rel=\"nofollow\" href=\"chat.jsp\" target=\"_blank\" class=\"dropdown-item d-flex\"><div class=\"msg-profile\"><img src=\" ../headpic/"+ chat.users.headPic +"\" alt=\"...\" class=\"img-fluid rounded-circle\"></div><div class=\"msg-body\"><h3 class=\"nav-fr\">"+ chat.users.nickName +"</h3><span>有新消息</span></div></a></li>	");});
					if(chat.length!=0){
						$("#ul-childt").append("<li><a onclick=\"javascript:lgonreM();\" rel=\"nofollow\" href=\"javascript:;\" class=\"dropdown-item all-notifications text-center\"><strong>忽&nbsp;略&nbsp;全&nbsp;部</strong></a></li>");
						}else{
							$("#ul-childt").append("<li><a style=\"text-content:center;\">暂无消息</a></li>");
						}
					$("#ul-list").append("</ul></li>");
					
					//退出登录
					$("#ul-list").append("<li class=\"nav-item\"><a href=\"login.jsp\" class=\"nav-link logout\"><span class=\"d-none d-sm-inline\">退出登录</span></a></li>");					
				 }
			}
		});
	}
	

  
  </script>

</head>

<body>

	<div id="top" class="s-wrap site-wrapper">
		<!--置顶导航栏-->
		<header class="header">
			<nav class="navbar">

				<div class="container-fluid">
					<div
						class="navbar-holder d-flex align-items-center justify-content-between">
						<!-- Navbar Header-->
						<div class="navbar-header">
							<!-- Navbar Brand -->
							<a href="index.html"
								class="navbar-brand d-none d-sm-inline-block">
								<div class="brand-text d-none d-lg-inline-block">
									<span>Bootstrap </span><strong>Dashboard</strong>
								</div>
								<div class="brand-text d-none d-sm-inline-block d-lg-none">
									<strong>BD</strong>
								</div>
							</a>
							<!-- Toggle Button-->
							<a id="toggle-btn" href="#" class="menu-btn active"><span></span><span></span><span></span></a>
						</div>
						<!-- Navbar Menu -->
						<ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center" id="ul-list">
							
							
							
						</ul>
					</div>
				</div>
			</nav>
		</header>
		<!-- site header
        ================================================== -->
		<header class="s-header">

			<div class="header__top">
				<div class="header__logo">
					<!-- logo  -->
					<a class="site-logo" href="index.jsp"> <img
						src="images/daily.gif" alt="Homepage">
					</a>
				</div>
			</div>
			<!-- end header__top -->

			<nav class="header__nav-wrap">

				<ul class="header__nav">
					<!--主页-->
					<li class="current"><a href="index.jsp" title="">主页</a>
					</li>

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
							class="screen-reader-text">Dribbble</span>
					</a></li>
					<li class="ss-pinterest"><a href="#0"> <span
							class="screen-reader-text">Behance</span>
					</a></li>
				</ul>

			</nav>
			<!-- end header__nav-wrap -->

		</header>
		<!-- end s-header -->

		<div class="s-content">

			<div class="masonry-wrap">

				<div class="masonry"  id="sjtj">

					<div class="grid-sizer"></div>
		

					


				</div>
				<!-- end masonry -->

			</div>
			<!-- end masonry-wrap -->



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

</body>

<!-- Java Script
    ================================================== -->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/plugins.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</html>
