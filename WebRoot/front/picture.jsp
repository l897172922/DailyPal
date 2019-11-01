<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>我的相册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- favicons
    ================================================== -->
<link rel="apple-touch-icon" sizes="180x180"
	href="images/palhead/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="images/palhead/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="images/palhead/favicon-16x16.png">

<!--main-->
<link rel="stylesheet" type="text/css" href="css/forpic.css" />
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var param = {op:"query",currPage:"1",pageSize:"12"};
	$("#pic").empty();
	$.get("PictureServlet",param,function(data,status,xhr){
		if(status == "success"){
		var list = data;
		if(list=='请登录后重试'){
			alert(list);
			window.location.href="login.jsp"; 
		}else{
		
		var currPage = list.currPage;
		var pageSize = list.pageSize;
		var picList = list.list;
		var privPage = list.privPage;
		var nextPage = list.nextPage;
		var allPage = list.allPage;
		var total = list.total;
			$.each(picList,function(index,pic){
				$("#pic").append(
					"<div class=\"brick tint lr\"><img src=\"picture/"+ pic.path +
					"\" class=\"img\" style=\"height:250px;width:250px\"/><div class=\"overlay\"><h3 class=\"project-title\">"
					+ pic.users.nickName + "</h3><p class=\"project-description\">"+ pic.time +
					".</p><a href=\"#\" class=\"open-project\" onclick=\"javascript:del("+ pic.pictureId +");\" >删除</a></div></div>"
				)
			});
			$("#part-l").empty();
			$("#part-l").append("<li><a class=\"pgn__prev\" href=\"#0\"onclick=\"javascript:paging("+privPage+");\">Prev</a></li>");
			for(var i=1;i<=allPage;i++){
				if(i==currPage){
					$("#part-l").append("<li><a class=\"pgn__num current\" >"+ i +"</a></li>");
				}else{
					$("#part-l").append("<li><span class=\"pgn__num\" href=\"#\" onclick=\"javascript:paging("+ i +");\">"+ i +"</span></li>");
				}
			}
			$("#part-l").append("<li><a class=\"pgn__next\" href=\"#0\"onclick=\"javascript:paging("+nextPage+");\">Next</a></li>");
			
		}	
			
		}
	});
  		
	$("#file").change(function(){
		//获取图片信息
		var file = this.files;
		var formData = new FormData();
		formData.append("file",file[0]);
		$.ajax({
			url:"PictureServlet?op=add",
			data:formData,
			type:"post",
			processData : false,
			contentType : false,
			success : function(data){
				location.reload();
			}
		});
	});
});
//分页
function paging(curr){
	var param = {op:"query",currPage:curr,pageSize:"12"};
	$("#pic").empty();
	$.get("PictureServlet",param,function(data,status,xhr){
		if(status == "success"){
		var list = data;
		var currPage = list.currPage;
		var pageSize = list.pageSize;
		var picList = list.list;
		var privPage = list.privPage;
		var nextPage = list.nextPage;
		var allPage = list.allPage;
		var total = list.total;
			$.each(picList,function(index,pic){
				$("#pic").append(
					"<div class=\"brick tint lr\"><img src=\"picture/"+ pic.path +
					"\" class=\"img\" style=\"height:250px;width:250px\"/><div class=\"overlay\"><h3 class=\"project-title\">"
					+ pic.users.nickName + "</h3><p class=\"project-description\">"+ pic.time +
					".</p><a href=\"#\" class=\"open-project\" onclick=\"javascript:del("+ pic.pictureId +");\" >删除</a></div></div>"
				)
			});
			$("#part-l").empty();
			$("#part-l").append("<li><a class=\"pgn__prev\" href=\"#0\"onclick=\"javascript:paging("+privPage+");\">Prev</a></li>");
			for(var i=1;i<=allPage;i++){
				if(i==currPage){
					$("#part-l").append("<li><a class=\"pgn__num current\" >"+ i +"</a></li>");
				}else{
					$("#part-l").append("<li><span class=\"pgn__num\" href=\"#\" onclick=\"javascript:paging("+ i +");\">"+ i +"</span></li>");
				}
			}
			$("#part-l").append("<li><a class=\"pgn__next\" href=\"#0\"onclick=\"javascript:paging("+nextPage+");\">Next</a></li>");
			
			
			
		}
	});
	
}

  	
function del(id){
	console.log(id);
	if(confirm("确定要删除吗?")){

		var param = {op:"del",picId:id};
		$.ajax({
			url:"PictureServlet",
			type:"post",
			data:param,
			success:function(data){
			}
		}); 
		location.reload();
	}
}
  	
$(document).ready(function() {
	$("#up").click(function() {
		$("#file").click();
	});
});
</script>

</head>

<body>
	<div id="all">

		<div class="head">
			<div class="left">
				<img src="images/daily.gif" class="logo" />
			</div>
			<div class="right">
				<input type="file" id="file" name="file" /> <a
					class="btn btn--primary full-width" id="up" name="up">上传文件</input> <a
					class="btn btn--primary full-width" href="index.jsp">返回首页</a>
			</div>
		</div>
		<div class="main">
			<div id="pic"></div>

		</div>

		<!--分页查询-->
		<div class="column large-full">
			<nav class="pgn">

			<ul id="part-l">
				
			</ul>
			</nav>
		</div>

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
</body>
</html>
