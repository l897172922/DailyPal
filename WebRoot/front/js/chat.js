/* 新消息 闪烁*/
function message() {
	var a = $.blinkTitle.show();
	setTimeout(function() {
		$.blinkTitle.clear(a)
	}, 8e3)
}
$(document).ready(function() {
	/* 自动刷新 */
	setInterval(function(){ refresh(); },3000);
	function refresh(){ 
		var param = {op:"readstate",friId:friId}; 
		$.ajax({
			url:"ChatServlet", 
			type:"post", 
			data:param, 
			success:function(data){
				var list = data;
				$.each(list,function(index,mes){
					$(".mes" + a).append( 
						"<div class=\"message clearfix\"><div class=\"user-logo\"><img src=\"../headpic/" 
						+ mes.users.headPic +"\" style=\"width: 50;height:50;\"></div><div class=\"wrap-text\"><h5 class=\"clearfix\">" 
						+ mes.users.nickName +"</h5><div>"+ mes.content +"</div></div><div class=\"wrap-ri\"><div class=\"clearfix\"><span>"
						+ mes.sendTime +"</span></div></div><div style=\"clear:both;\"></div></div>"
					);
					$(".chat01_content").scrollTop($(".mes" + a).height());
				});
			}
		}); 
	}
	 
	
	
/* send 将消息添加到数据库 */
	function z() {
		var param = {op:"send",friId:friId,text:g};
		$.ajax({
			url:"ChatServlet",
			type:"post",
			data:param,
			success:function(){
			}
		});
	}
/* 打开窗口时读取聊天记录 */
	function y() {
		$(".mes" + a).empty();
		var param = {op:"query",friId:friId};
		$.ajax({
			url:"ChatServlet",
			type:"post",
			data:param,
			success:function(data){
				var list = data;
				$.each(list,function(index,mes){$(".mes" + a).append("<div class=\"message clearfix\"><div class=\"user-logo\"><img src=\"../headpic/"+ mes.users.headPic +"\" style=\"width: 50;height: 50;\"></div><div class=\"wrap-text\"><h5 class=\"clearfix\">"+ mes.users.nickName +"</h5><div>"+ mes.content +"</div></div><div class=\"wrap-ri\"><div class=\"clearfix\"><span>"+ mes.sendTime +"</span></div></div><div style=\"clear:both;\"></div></div>");});
				$(".chat01_content").scrollTop($(".mes" + a).height());
			}
		});
	}
		var g;
/* 函数e 为 发送聊天内容操作 */
		function e() {
/* 函数h 设置? */
			function h() {-1 != g.indexOf("*#emo_") && (g = g.replace("*#", "<img src='img/").replace("#*", ".gif'/>"), h())}
			/* e获取当前时间 */
			var e = new Date,
				f = "";
			/* f整理好格式的时间 */
			f += e.getFullYear() + "-", f += e.getMonth() + 1 + "-", f += e.getDate() + "  ", f += e.getHours() + ":", f += e.getMinutes() + ":", f += e.getSeconds();
			/* g聊天框的内容 */
			g = $("#textarea").val();
			h();
			
			/*
			 * 我的头像连接 头像尺寸 我的名字 我发送的内容 发送消息的时间 
			 */
			var i = "<div class='message clearfix'><div class='user-logo'><img src='" + b + "' style='width:50;height:50;'/>" + "</div>" + "<div class='wrap-text'>" + "<h5 class='clearfix'>我</h5>" + "<div>" + g + "</div>" + "</div>" + "<div class='wrap-ri'>" + "<div clsss='clearfix'><span>" + f + "</span></div>" + "</div>" + "<div style='clear:both;'></div>" + "</div>" ;
			/* 发送的内容不为空 */
			null != g && "" != g ? ($(".mes" + a).append(i), $(".chat01_content").scrollTop($(".mes" + a).height()), $("#textarea").val(""), message()) : alert("请输入聊天内容!")
		}
		
		var friId = friid;
		var scrnum= 0;
		//var friId = 0;
		var ds;
		var a = ind,
		/* b为我的头像 ,在这里更改 */
		b = '../headpic/'+userhead,
		/* c为好友头像 */
		c = "img/head/201.jpg",
		/* d为好友名称 */
		d = "好友";
		console.log("w部页面a:"+a);
		console.log("w部页面friId:"+friId);
		
		
		/* 关闭页面 */
		$(".close_btn").click(function() {
			$(".chatBox").hide()
		}), $(".chat03_content li").mouseover(function() {		/* 列表悬浮 */
			$(this).addClass("hover").siblings().removeClass("hover")
		}).mouseout(function() {								/* 列表悬浮移除 */
			$(this).removeClass("hover").siblings().removeClass("hover")
		}), $(".chat03_content li").dblclick(function() {		/* 列表好友双击 */
			/* b为第几个好友 */
			var b = $(this).index();
			a = b,
			/* c为好友头像 */
			c = $(this).find("img").attr("src"), 
            /* d为获取的好友名称 */
			d = $(this).find(".chat03_name").text(), 
			/* 好友的Id */
			friId = $(this).find(".uId").text();
			/* 滚动条,0为最顶端*/
			$(".chat01_content").scrollTop(0), 
			/* li添加选择样式 其他li移除样式 */
			$(this).addClass("choosed").siblings().removeClass("choosed"), 
			/* 标题栏显示好友名字 */
			$(".talkTo a").text($(this).children(".chat03_name").text()), 
			/* 根据排序选聊天窗口 */
			$(".mes" + b).show().siblings().hide(),
			y()
		}), $(".ctb01").mouseover(function() {					/* 鼠标悬浮表情标签 */
			$(".wl_faces_box").show()
		}).mouseout(function() {								/* 鼠标移出表情标签 */
			$(".wl_faces_box").hide()
		}), $(".wl_faces_box").mouseover(function() {			/* 鼠标悬浮表情div */
			$(".wl_faces_box").show()
		}).mouseout(function() {								/* 鼠标移出表情div */
			$(".wl_faces_box").hide()
		}), $(".wl_faces_close").click(function() {				/* 隐藏表情div */
			$(".wl_faces_box").hide()
		}), $(".wl_faces_main img").click(function() {			/* 选中表情到聊天框 */
			/* a为选中表情的名字 */
			var a = $(this).attr("src");
			$("#textarea").val($("#textarea").val() + "*#" + a.substr(a.indexOf("img/") + 4, 6) + "#*"), 
			$("#textarea").focusEnd(), 
			$(".wl_faces_box").hide()
		}), $(".chat02_bar img").click(function() {				/* 点击发送 */
			e(),
			z()
		}), document.onkeydown = function(a) {					/* 回车发送 */
			var b = document.all ? window.event : a;
			return 13 == b.keyCode ? (e(),z(), !1) : void 0
		}, $.fn.setCursorPosition = function(a) {				/* 设置光标位置 */
			return 0 == this.lengh ? this : $(this).setSelection(a, a)
		}, $.fn.setSelection = function(a, b) {					/* 选中??? */
			if(0 == this.lengh) return this;
			if(input = this[0], input.createTextRange) {
				var c = input.createTextRange();
				c.collapse(!0), c.moveEnd("character", b), c.moveStart("character", a), c.select()
			} else input.setSelectionRange && (input.focus(), input.setSelectionRange(a, b));
			return this
		}, $.fn.focusEnd = function() {							/* 结束聚焦 */
			this.setCursorPosition(this.val().length)
		}
	}),
	function(a) {												/* 匿名类执行 */
		a.extend({
			blinkTitle: {
				show: function() {
					var a = 0,
						b = document.title;
					if(-1 == document.title.indexOf("\u3010")) var c = setInterval(function() {
						a++, 3 == a && (a = 1), 1 == a && (document.title = "[新消息]" + b), 2 == a && (document.title = "[新消息]" + b)
					}, 500);
					return [c, b]
				},
				clear: function(a) {
					a && (clearInterval(a[0]), document.title = a[1])
				}
			}
		})
	}(jQuery);