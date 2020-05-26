<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!--header-->	
<div class="top_bg">
	<div class="container">
		<div class="header_top-sec">
			<div class="top_left">
				<ul>
				<c:if test="${not empty SimpleUser }">
					<li class="top_link">欢迎您:<a href="javascript:;">${SimpleUser.user.userName }</a></li>
					<li class="top_link"><a href="${ctx }/com/toUserManage.action">[个人中心]</a></li>
					<li class="top_link"><a href="${ctx }/com/userLogout.action" style="color:black">退出</a></li>
				</c:if>
				<c:if test="${empty SimpleUser }">
					<li class="top_link"><a href="${ctx}/qiantai/login.jsp">用户登录</a></li>					
				</c:if>
				<li class="top_link"><a href="${ctx}/index.jsp">后台管理</a></li>	
				</ul>
			</div>
				<div class="clearfix"> </div>
		</div>
	</div>
</div>
<div class="header-top">
	 <div class="header-bottom">
		 <div class="container">			
				<div class="logo">
					<a href="index.html"><h1>${appTitle }</h1></a>
				</div>
			 <!---->
		 
			 <div class="top-nav">
				<ul class="memenu skyblue"><li class="active" id="menuli_index"><a href="${ctx}/com/index.action">首页</a></li>
					<li class="grid" id="menuli_product"><a href="${ctx}/com/list.action">餐点</a></li>
					<li class="grid" id="menuli_news"><a href="${ctx}/com/listNews.action">新闻</a></li>
				</ul>
				<div class="clearfix"> </div>
			 </div>
			 <!---->
			 <div class="cart box_1">
				 <a href="${ctx}/com/toCart.action">
					<h3> 购物车
					<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
					</h3>
				</a>
			 	<div class="clearfix"> </div>
			 </div>
			 <div class="clearfix"> </div>
			 <!---->			 
			 </div>
			<div class="clearfix"> </div>
	  </div>
</div>
<%String type = request.getParameter("type");
%>
<script type="text/javascript">
<!--
var pageii;
$("li[id^='menuli_']").each(function(){
	  if($(this).attr("id")=="menuli_"+"<%=type%>"){
		  $(this).addClass("active");
	  }else{
		  $(this).removeClass("active");
	  }
});
</script>
<!---->