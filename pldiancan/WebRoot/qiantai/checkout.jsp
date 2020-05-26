<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${appTitle }</title>
<jsp:include page="./header.jsp"></jsp:include> 
</head>
<body> 
<!--header-->	
 <jsp:include page="./menu.jsp"><jsp:param value="product" name="type"/> </jsp:include>
<!---->
<div class="checkout">	 
	 <div class="container">	
		 <ol class="breadcrumb">
		 </ol>
		 <div class="col-md-9 product-price1">
			 <div class="check-out">			
				 <div class=" cart-items">
					 <h3>购物车 (${size })</h3>
					 <div class="in-check" >
						  <ul class="unit">
							<li style="width: 200px"><span>商品</span></li>
							<li style="width: 200px"><span>名称</span></li>		
							<li><span>价格</span></li>
							<li> </li>
							<div class="clearfix"> </div>
						  </ul>
						 <c:forEach items="${SIMPLEUSERCART }" var="item">
						<ul class="cart-header">
								<li class="ring-in"><a href="${ctx}/com/getGoods.action?uid=${item.id}" ><img src="${ctx }/resource/${item.imgpath}" class="img-responsive" alt=""></a>
								</li>
								<li style="width: 200px"><span>${item.name }</span></li>
								<li style="width: 200px"><span>¥ ${item.offPrice } * ${item.buyAmount }</span></li>
								<li> <a href="${ctx }/com/del2Cart.action?uid=${item.id}" class="add-cart cart-check">移除</a></li>
								<div class="clearfix"> </div>
						</ul>
						 </c:forEach>
<!-- 						<ul class="cart-header"> -->
<!-- 								<li class="ring-in"><a href="single.html" ><img src="${ctx }/qiantai/images/f2.jpg" class="img-responsive" alt=""></a> -->
<!-- 								</li> -->
<!-- 								<li style="width: 200px"><span>Woo Dress</span></li> -->
<!-- 								<li style="width: 200px"><span>¥ 60.00</span></li> -->
<!-- 								<li> <a href="single.html" class="add-cart cart-check">移除</a></li> -->
<!-- 								<div class="clearfix"> </div> -->
<!-- 						</ul> -->
					 </div>
				  </div>					  
			 </div>
		 </div>
		 <div class="col-md-3 cart-total">
			 <h4 class="last-price">总金额:</h4>
			 <span class="total final">$ ${total }</span>
			 <div class="clearfix"></div>
			 <a class="order" href="${ctx}/com/toOrder.action">订单结算</a>
			 
			</div>
	 </div>
</div>
<!---->
 <jsp:include page="./foot.jsp"></jsp:include> 
 <c:if test="${not empty orderMessage }">
 <script type="text/javascript">
 alert("${orderMessage}");
 </script>
 </c:if>
 <%session.removeAttribute("orderMessage"); %>
</body>
</html>