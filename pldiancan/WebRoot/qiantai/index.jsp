<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${appTitle }</title>
<jsp:include page="./header.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/qiantai/js/img.js"></script>
<!-- /start menu -->
</head>
<body> 
<jsp:include page="./menu.jsp"><jsp:param value="index" name="type"/> </jsp:include> 
<div class="banner">
	 <div class="container">
	 </div>
</div>
<!---->
<div class="welcome">
	 <div class="container">
		 <div class="col-md-3 welcome-left">
			 <h2>欢迎选购</h2>
		 </div>
		 <div class="col-md-9 welcome-right">
			 <h3>点餐系统.</h3>
			 <p>提供餐点大全全面服务信息，包含餐点报价、参数、评测、比较、点评、论坛等，帮您全面了解美食信息.</p>
		 </div>
	 </div>
</div>
<!---->
 
<!---->
<div class="featured">
	 <div class="container">
		 <h3><a href="${ctx}/com/index.action">点餐排行榜</a></h3>
		 	<c:forEach items="${list }" var="item" varStatus="vsitem">
		 		<c:if test="${vsitem.count==1 || vsitem.count%4==1 }" var="test111">
				 <div class="feature-grids">
					 <div class="col-md-3 feature-grid jewel">
						 <a href="${ctx}/com/getGoods.action?uid=${item.id}"><img height="194px" width="166px"  src="${ctx }/resource/${item.imgpath}" alt=""/>	
							 <div class="arrival-info">
								 <h4>${item.name }</h4>
								 <p>¥ ${item.offPrice }</p>
								 <span class="pric1"><del>¥ ${item.price }</del></span>
								 <span class="disc">[${item.offPercent }% 折扣]</span>
							 </div>
							 
							 </a>
					 </div>
				 </div>
		 		</c:if>
		 		<c:if test="${!test111 }">
		 			<div class="col-md-3 feature-grid jewel">
						 <a href="${ctx}/com/getGoods.action?uid=${item.id}"><img height="194px" width="166px"  src="${ctx }/resource/${item.imgpath}" alt=""/>	
							 <div class="arrival-info">
								 <h4>${item.name }</h4>
								 <p>¥ ${item.offPrice }</p>
								 <span class="pric1"><del>¥ ${item.price }</del></span>
								 <span class="disc">[${item.offPercent }% 折扣]</span>
							 </div>
							 
							 </a>
					 </div>
					 <c:if test="${vsitem.count%4==0 }" var="test111">
					 	 <div class="clearfix"></div>
					 </c:if>
		 		</c:if>
		 	</c:forEach>
		  
	 </div>
</div>
<!---->
 
<!---->
 
<!---->
<jsp:include page="./foot.jsp"></jsp:include> 	 
</body>
</html>
