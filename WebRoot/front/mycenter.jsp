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

<title>个人中心</title>

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
<style type="text/css">
	.messages{
	margin-left:60px;
	color:gray;
	font-size:8px;
	
}
	
</style>

<!-- Java Script
    ================================================== -->
<script src="js/jQuery.js" type="text/javascript" charset="utf-8"></script>
<!-- <script src="js/jquery-3.2.1.min.js"></script> -->
<script src="js/plugins.js"></script>
<script src="js/main.js"></script>
<script src="js/mycity.js" type="text/javascript" charset="utf-8"></script>



<!-- <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
 -->
<script type="text/javascript">
	$(function() {
		$("#inpt").change(function() {
			//获取图片信息
			var file = this.files;
			var formData = new FormData();
			formData.append("file", file[0]);
			//设置参数
			$.ajax({
				url : "MyCenterServlet?op=head",
				data : formData,
				type : "post",
				processData : false,
				contentType : false,
				success : function(data) {
					var imgPath = data;
					//$("#myheads").src="../headpic/"+imgPath;
					$("#myheads").attr("src", "../headpic/" + imgPath);
				}
			});
		});

		//设置爱好选中
		var stb = new Array();
		<c:forEach items="${hobby}" var="ho" varStatus="s">
		stb[${s.index}] = "${ho}";
		$("input[Value=" + stb[${s.index}] + "]").attr("checked", "checked");
		</c:forEach>

	});
	//点击头像上传头像
	$(document).ready(function() {
		$("#head-b").click(function() {
			$("#inpt").click();
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
			<li class="has-children current"><a href="#0" title=""
				class="sub-menu-is-open">我的</a>
				<ul class="sub-menu" style="display: block;">
					<li><a href="picture.jsp" target="_self">相册</a></li>
					<li><a href="FriendSsircleServlet?op=query">朋友圈</a></li>
					<li><a href="messageboard.jsp">留言板</a></li>
					<li class="current"><a href="MyCenterServlet?op=query">个人中心</a></li>
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
				<h1 class="display-1">个人中心</h1>
			</div>
			<!-- end content__page-header -->
			<form action="MyCenterServlet?op=submit" method="post">
				<div style="width: 100%; height: 600px;">
					<div id="pic"
						style="height: 500px; width:20%; text-align: center; float: left; margin: 20px 50px">
						<img id="myheads" src="../headpic/${user.headPic}" class="imgsss"
							style="height: 120px; width: 120px; margin: 0;" />
						<!-- <input type="file" id="inpt-t" class="filesss"/> -->
						<div style="height:50px; width=:100%;">
							<input type="button" id="head-b" value="上传头像" /> <input
								type="file" name="inpt" id="inpt" />
						</div>
					</div>

					<div id="leftc" style="float: left; width: 60%;">
						<div>
							<p>昵称</p>
							<input type="text" name="nickName" id="nickName"
								value="${user.nickName}" class="inp" />
							<span class="messages">${nickMsg == null?"":nickMsg}</span>
						</div>

						<div>
							<p>性别</p>
							<input type="text" name="sex" id="sex" value="${user.sex}"
								class="inp" />
							<span class="messages">${centMsg == null?"":centMsg}</span>
						</div>

						<div>
							<p>年龄</p>
							<input type="number" name="age" id="age" value="${user.age}"
								class="inp" />
							<span class="messages">${ageMsg == null?"":ageMsg}</span>
						</div>

						<div>
							<p>邮箱</p>
							<input type="email" name="email" id="email" value="${user.email}"
								class="inp" />
							<span class="messages">${emailMsg == null?"":emailMsg}</span>
						</div>

						<div>
							<p>电话</p>
							<input type="number" name="pon" id="pon" value="${user.phone}"
								class="inp" />
							<span class="messages">${ponMsg == null?"":ponMsg}</span>
						</div>

						<p>地址</p>
						<div id="home">

							<select name="pro" id="province">
								<option value="${addr[0]}">${addr[0]}</option>
							</select>
							<p>省</p>

							<select name="city" id="city">
								<option value="${addr[1]}">${addr[1]}</option>
							</select>
							<p style="width: 60px;">市/区</p>
						</div>

						<p style="clear: both;">职业</p>
						<select id="occu" name="occu" style="width: 200px;">
							<option value="${user.career}">${user.career}</option>
							<option value="计算机/互联网/通信">计算机/互联网/通信</option>
							<option value="生产/工艺/制造">生产/工艺/制造</option>
							<option value="医疗/护理/制药">医疗/护理/制药</option>
							<option value="金融/银行/投资/保险">金融/银行/投资/保险</option>
							<option value="商业/服务业/个体">商业/服务业/个体</option>
							<option value="文化/广告/媒体">文化/广告/媒体</option>
							<option value="娱乐/艺术/表演">娱乐/艺术/表演</option>
							<option value="律师/法务">律师/法务</option>
							<option value="教育/培训">教育/培训</option>
							<option value="公务员/行政/事业单位">公务员/行政/事业单位</option>
							<option value="学生">学生</option>
							<option value="其他">其他</option>
						</select>
					</div>
				</div>


				<div id="m">
					<hr>
					<div id="like">
						<p class="ptop">兴趣爱好</p>

						<div>
							<input type="checkbox" name="check" id="game" value="游戏" /><label
								for="game">游戏</label>
						</div>
						<div>
							<input type="checkbox" name="check" id="pict" value="摄影" /><label
								for="pict">摄影</label>
						</div>
						<div>
							<input type="checkbox" name="check" id="spor" value="运动" /><label
								for="spor">运动</label>
						</div>
						<div>
							<input type="checkbox" name="check" id="musi" value="音乐" /><label
								for="musi">音乐</label>
						</div>
						<div>
							<input type="checkbox" name="check" id="movi" value="电影" /><label
								for="movi">电影</label>
						</div>
						<div>
							<input type="checkbox" name="check" id="book" value="小说" /><label
								for="book">小说</label>
						</div>
						<div>
							<input type="checkbox" name="check" id="adve" value="冒险" /><label
								for="adve">冒险</label>
						</div>
						<div>
							<input type="checkbox" name="check" id="cart" value="动漫" /><label
								for="cart">动漫</label>
						</div>
						<div>
							<input type="checkbox" name="check" id="cate" value="美食" /><label
								for="cate">美食</label>
						</div>
						<div>
							<input type="checkbox" name="check" id="lzay" value="睡觉" /><label for="lazy">睡觉</label>
						</div>


					</div>
					<hr />
					<p class="ptop">个性签名</p>
					<textarea name="areaa" id="areaa" rows="" cols="" class="areat">${user.introdution}</textarea>
					<span class="messages">${areaMsg == null?"":areaMsg}</span>
					<div id="but" align="center">
						<input type="submit" name="submit" id="submit" value="保存"
							class="btu" style="margin-right:50px;width:300px;height: 60px;" />
						<input type="reset" name="rese" id="rese" value="取消" class="btu"
							style="margin-left:50px;width:300px;height: 60px;" />
					</div>

				</div>
			</form>
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

</body>
</html>
