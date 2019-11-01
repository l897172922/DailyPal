<%@page import="com.pal.entity.Pictures"%>
<%@page import="com.pal.service.PicturesService"%>
<%@page import="com.pal.entity.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%	
	Users user = (Users)request.getAttribute("user");	
	PicturesService ps = new PicturesService();
	ArrayList<Pictures> picList = ps.getMyPictures(user.getU_id());
	//System.out.println(picList);
	//System.out.println(user);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>查看信息</title>

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
<link rel="stylesheet" type="text/css" href="css/pinglun.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/fordetails.css" />

<!--Model窗口-->
<!--<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
<script src="js/jQuery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/motel.css" />


<!-- favicons
    ================================================== -->
<link rel="apple-touch-icon" sizes="180x180"
	href="images/palhead/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="images/palhead/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="images/palhead/favicon-16x16.png">
<script type="text/javascript">
window.onload =function(){			
	var msg = '<%=request.getAttribute("banmsg")%>';

	if (msg == "null") {

	} else {
		alert(msg);
	}
}
</script>	
</head>

<body
	class="home blog wp-custom-logo woocommerce-no-js hfeed pt-sticky-sidebar right-sidebar elementor-default"
	style="background-color: #f1f5f5;">
	<!--全局-->
	<div id="content" class="blog-wrapper">
		<div id="heads">
			<img src="images/daily.gif" class="heads-img" /> <input
				type="button" name="back" id="back" value="返回主页"
				onclick="window.location.href='index.jsp'" />
		</div>


		<!-- 将被评论的动态id传入动态模态框 -->
		<script type="text/javascript">
			$("#myModal").modal("hide");
			function Values(ID) {
				$("#blogid").val(ID);
			}
		</script>
		<!-- 动态模态框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">

					<!--header-->
					<div class="m-head">
						<h4 class="m-h4" id="myModalLabel"></h4>
					</div>
					<form action="DetailsServlet" method="get">
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
		<!-- 举报模态框 -->
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">

					<!--header-->
					<div class="m-head">
						<h4 class="m-h4" id="myModalLabel"></h4>
					</div>
					<form action="DetailsServlet" method="get">
					<div class="modal-body">
						<p class="m-n">举报内容</p>
						<!-- <input type="text" name="" id="" value="" class="m-inp" /> -->
						<select id="reason" name="reason" style="width: 200px;">							
							<option value="低俗色情">低俗色情</option>
							<option value="暴力">暴力</option>
							<option value="传销">传销</option>							
							<option value="政治敏感">政治敏感</option>							
							<option value="其他">其他</option>
						</select>
						<input type="hidden" id="userid" name="userid" value="${user.u_id }">
						<input type="hidden" id="op" name="op" value="inform">
					
						<%-- <label name="">${user.u_id }</label> --%>
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
		<!--Modelendlllllllllllllllll-->

		<!--body-->
		<div class="container">

			<!--all-->
			<div id="primary" class="">
				<!-- 动态模板 -->
				<article id="post-90" class="post-wrapper arti">
				<div class="table-responsive">



					<!-- 动态 -->
					<%-- <c:forEach items="${blist }" var="blog"> --%>
					<div class="apart">

						<div class="d-ver">
							<!-- 动态图片 -->
							<div class="d-img">
								<img src="detailpic/${blog.picturePath }" width="100" />
							</div>
							<div class="entry-header">
								<!-- 内容 -->
								<p>${blog.text }
									<!-- <a href="#">详情</a>  -->
								</p>
							</div>
						</div>

						<div>
							<!-- 时间 -->
							<span class="d-time">${blog.sendTime }</span>

							<!-- 外部导入按钮 -->
							<div class="d-nice">
								<div class="CTAs">
									<a href="DetailsServlet?op=bloglike&blogid=${blog.b_id}"
										class="btn d-h" style="color:white;"><span>${blog.likeCounts }</span><i
										class="fa fa-thumbs-up"></i>喜欢</a>
									<%-- <a href="#" class="btn d-h"><span>${blog.commentCounts }</span><i class="fa fa-heart"></i>评论 </a> --%>
									<a class="btn d-h" data-toggle="modal" data-target="#myModal"
										style="color:white;" onclick="Values(${blog.b_id})"><span>${blog.commentCounts }</span><i
										class="fa fa-heart"></i>评论 </a>
								</div>
							</div>

						</div>
						<hr />
					</div>



					<!-- 动态模板 -->

					<div class="table-responsive">
						<div class="apart">
							<c:forEach items="${cmlist }" var="comment">

								<%-- <div class="d-ver1">
									<!-- 动态图片 -->

									<div class="entry-header">
										<!-- 内容 -->
										<p>
											<span class="d-time">${comment.time }</span>&nbsp;&nbsp;&nbsp;${comment.fromUsers.nickName }:${comment.content }
											<!-- <a href="#">详情</a>  -->
										</p>
									</div>
								</div> --%>

								<div lang="en-US"
									class="gitment-container gitment-comments-container">
									<ul class="gitment-comments-list">
										<li class="gitment-comment">
											<div class="gitment-comment-main">
												<div class="gitment-comment-header">
													<a class="gitment-comment-name">
														${comment.fromUsers.nickName } </a> <span>${comment.time }</span>

												</div>
												<div class="gitment-comment-body gitment-markdown">
													<p>${comment.content }</p>
												</div>
											</div></li>

									</ul>
								</div>

							</c:forEach>
						</div>

						<%-- </c:forEach> --%>

					</div>
				</article>
				<!-- #primary -->

				<aside id="secondary" class="col-md-4 col-sm-4"
					style="width: 30%; float: right; margin-top: 40px; background-color: white; border-left: grey solid 1px;">
				<section id="deft-author-3" class="widget deft_widget_author"
					style="">

				<div class="author-profile" style="margin-top: 20px;">
				
				<!-- 个人信息 -->
				
					<!-- <h2 class="widget-title">个人信息</h2> -->
					<div class="profile-wrapper social-menu-wrap">
						<!-- 头像 -->
						<figure class="author"> <img
							src="../headpic/${user.headPic }" class="h-img"> </figure>
						<a class="btn btn--primary add" href="#0">加好友</a> <a
							class="btn btn--primary add" data-toggle="modal"
							data-target="#myModal2" style="color:white;">举报</a>

						<div id="info">
							<p>名字:${user.nickName }</p>
							<p>性别:${user.sex }</p>
							<p>年龄:${user.age }</p>
							<p>邮箱:${user.email }</p>
							<p>电话:${user.phone }</p>
							<p>地址:${user.address }</p>
							<p>职业:${user.career }</p>
							<p>${user.introdution }</p>
						</div>



						<!-- 平台 -->
					</div>
					<!-- .profile-wrapper -->
				</div>
				<!-- .author-profile -->
				<hr />
				</section> <!-- 相册 --> <section id="recent-posts-2"
					class="widget widget_recent_entries">
				<!-- <h2 class="widget-title">相册</h2> -->
				<%
				for(Pictures pic:picList){				
				%>
				<img src="picture/<%=pic.getPath() %>" class="d-dimg" /> 
				<%-- <p><%=pic.getPath() %></p> --%>
				<%} %>
				
				<!-- 
				<img src="" class="d-dimg" /> 
				<img src="" class="d-dimg" /> 
				<img src="" class="d-dimg" />  -->
				</section> 
				</aside>
				<!-- #secondary -->
			</div>
			<!-- #row -->

		</div>
		<!-- #content -->

		<!-- #colophon -->

		<!-- footer  -->
		<footer class="s-footer">
		<div class="row foot">
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
		<!-- footer
        ================================================== -->
		<!--点赞心心-->
		<script type='text/javascript'
			src='https://use.fontawesome.com/30858dc40a.js?ver=4.0.7'></script>
</body>
</html>
