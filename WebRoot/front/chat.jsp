<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%--  <base href="<%=basePath%>"> --%>

<title>聊天窗</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/chat.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

<!-- favicons
    ================================================== -->
<link rel="apple-touch-icon" sizes="180x180"
	href="images/palhead/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="images/palhead/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="images/palhead/favicon-16x16.png">
<link rel="manifest" href="site.webmanifest">

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
var ind;
var friid;
	/* 页面加载 ajax */
	$(function(){
		//获取jap页面传来的值
		//设置参数
	friid = '${param.friend}';
		var param = {op:"query",currPage:"1",pageSize:"100"};
		//设置同步ajax 保持js好用
		$.ajaxSettings.async = false;
		$.get("FriendListServlet",param,function(data,status,xhr){
			if(status == "success"){
				//json字符串转对象
				var list = data;
				var page = list[0];
				var currPage = page.currPage;
				var pageSize = page.pageSize;
				var friList = list[1];
				$.each(friList,function(index,fri){
					$("#flist").append("<li id=\"li-"+ index +"\"><label class=\"online\"> </label> <a href=\"javascript:;\"><img name=\"picc\" src=\"../headpic/"+ fri.headPic +"\"> </a> <a href=\"javascript:;\" class=\"chat03_name\">"+ fri.nickName +"</a><a  class=\"uId\" style=\"display:none;\">"+ fri.u_id +"</a></li>");
					$(".chat01_content").append("<div class=\"message_box mes"+ index +" \"  id=\"modbox-"+ index +" \"></div>");
					if(fri.u_id==friid){
						ind = index;
					}
				});
			}
		});
		$.ajaxSettings.async = true;
		
		console.log("内部页面ind:"+ind);
		console.log("内部页面friId:"+friid);
		
		/* li添加选择样式 其他li移除样式 */
		$("#li-"+ind).addClass("choosed").siblings().removeClass("choosed"), 
		/* 标题栏显示好友名字 */
		$(".talkTo a").text($("#li-"+ind).children(".chat03_name").text()), 
		/* 根据排序选聊天窗口 */
		$(".mes" + ind).show().siblings().hide()
		
		var param = {op:"query",friId:friid};
		$.ajax({
			url:"ChatServlet",
			type:"post",
			data:param,
			success:function(data){
				var list = data;
				$.each(list,function(index,mes){$(".mes" + ind).append("<div class=\"message clearfix\"><div class=\"user-logo\"><img src=\"../headpic/"+ mes.users.headPic +"\" style=\"width: 50;height: 50;\"></div><div class=\"wrap-text\"><h5 class=\"clearfix\">"+ mes.users.nickName +"</h5><div>"+ mes.content +"</div></div><div class=\"wrap-ri\"><div class=\"clearfix\"><span>"+ mes.sendTime +"</span></div></div><div style=\"clear:both;\"></div></div>");});
				$(".chat01_content").scrollTop($(".mes" + ind).height());
			}
		});
		
	});
	
	
var usernickname = '${userinfo.nickName}'; 
var userhead = '${userinfo.headPic}';

</script>
<script type="text/javascript" src="js/chat.js"></script>

</head>

<body class="keBody">
	<div class="kePublic">
		<!--Ч��html��ʼ-->
		<div class="content">
			<div class="chatBox">
				<div class="chatLeft">
					<div class="chat01">
						<div class="chat01_title">
							<ul class="talkTo">
								<li><a href="javascript:;"></a></li>
							</ul>
							<!-- 关闭窗口 -->
							<a class="close_btn" href="javascript:;"></a>
						</div>



						<div class="chat01_content"></div>
					</div>
					<div class="chat02">
						<div class="chat02_title">
							<!-- 表情 -->
							<a class="chat02_title_btn ctb01" href="javascript:;"></a>
							
								
							<div class="wl_faces_box">
								<div class="wl_faces_content">
									<div class="title">
										<ul>
											<li class="title_name">表情</li>
											<li class="wl_faces_close"><span>&nbsp;</span></li>
										</ul>
									</div>
									<!-- 表情 -->
									<div class="wl_faces_main">
										<ul>
											<li><a href="javascript:;"> <img
													src="img/emo_01.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_02.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_03.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_04.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_05.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_06.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_07.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_08.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_09.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_10.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_11.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_12.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_13.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_14.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_15.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_16.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_17.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_18.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_19.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_20.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_21.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_22.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_23.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_24.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_25.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_26.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_27.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_28.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_29.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_30.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_31.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_32.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_33.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_34.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_35.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_36.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_37.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_38.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_39.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_40.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_41.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_42.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_43.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_44.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_45.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_46.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_47.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_48.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_49.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_50.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_51.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_52.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_53.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_54.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_55.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_56.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_57.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_58.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_59.gif" /></a></li>
											<li><a href="javascript:;"> <img
													src="img/emo_60.gif" /></a></li>
										</ul>
									</div>
								</div>
								<div class="wlf_icon"></div>
							</div>
						</div>
						<div class="chat02_content">
							<textarea id="textarea"></textarea>
						</div>
						<div class="chat02_bar">
							<ul>
								<li style="left: 20px; top: 10px; padding-left: 30px;"></li>

								<!--发送按钮-->
								<li style="right: 5px; top: 5px;"><a href="javascript:;"><img
										src="img/send_btn.jpg"></a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="chatRight">
					<div class="chat03">
						<div class="chat03_title">
							<label class="chat03_title_t">好友列表</label>
						</div>
						<div class="chat03_content">
							<ul id="flist" style="height:600px;overflow-y:scroll;">
								<!-- 循环li -->
							</ul>
						</div>
						<!-- <div class="chat03_bottom">
							<a href="javascript:;" class="float-l">上一页</a>
							<a href="javascript:;" class="float-r">下一页</a>
						</div> -->
					</div>
				</div>
				<div style="clear: both;"></div>
			</div>
		</div>
		<!--end-->
	</div>

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
</body>

</html>
