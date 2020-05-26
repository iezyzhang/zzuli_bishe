<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<div class="footer">
	 <div class="container">
		  	
	 </div>
</div>
<style>
.link-m1{
 width:400px;
}
.link-m1 ul li{
 float:left;
 margin:2px 5px;
 list-style:none;
 display:block;
 width:50px;
 height:50px;
}
</style>
<!---->
 <div class="copywrite">
	 <div class="container">
			 
			 <div class="link-m1">
			
				<ul>
				<c:forEach items="${APPLICATION_FLINK }" var="itemfoot">
				<li><a target="_blank" href="${itemfoot.url }">${itemfoot.name }</a></li>
				</c:forEach>
				</ul>
			</div>
			<div style="clear:both"></div>
			 <p>Copyright © 2019 ${appTitle }</p>
		 </div>
</div>	