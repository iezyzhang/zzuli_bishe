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
<div class="single-sec">
	 <div class="container">
		 <ol class="breadcrumb">
		 </ol>
		 <!-- start content -->	
		 <div class="col-md-9 det">
				 <div class="single_left">
					 <div class="flexslider">
							<ul class="slides">
								<li data-thumb="${ctx}/resource/${item.imgpath}">
									<img src="${ctx}/resource/${item.imgpath}" />
								</li>
								 
							</ul>
						</div>
						<!-- FlexSlider -->
						  <script defer src="${ctx }/qiantai/js/jquery.flexslider.js"></script>
						<link rel="stylesheet" href="${ctx }/qiantai/css/flexslider.css" type="text/css" media="screen" />

						<script>
						// Can also be used with $(document).ready()
						$(window).load(function() {
						  $('.flexslider').flexslider({
							animation: "slide",
							controlNav: "thumbnails"
						  });
						});
						function addGoods(id){
							var uname = "${SimpleUser.user.uname}";
							if(uname==""){
								alert("请先登录");
								return false;
							}
							var amount = $("#buyamount").val();
							window.location.href="${ctx}/com/add2Cart.action?uid="+id+"&amount="+amount;
						}
						</script>
				 </div>
				  <div class="single-right">
					 <h3>${item.name }</h3>
					  <div class="cost">
						 <div class="prdt-cost">
							 <ul>
							 	<li>评分:</li>
								 <li class="active">${goodsScore}</li>
								 <li>原价: <del>¥ ${item.price }</del></li>								 
								 <li>售价:</li>
								 <li class="active">¥ ${item.offPrice }</li>
								 <input type="text" id="buyamount" value="1" size="5"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="addGoods('${item.id}')">加入购物车</a>
							 </ul>
						 </div>
						  
						 <div class="clearfix"></div>
					  </div>
					  <div class="item-list">
						 <ul>
							 <li>类型: ${item.bookType.name }</li>
							 <li>场景: ${item.sid }</li>
							 <li>菜品: ${item.author }</li>
							 <li>口味: ${item.press }</li>
							 <li>主要食材: ${item.publicationYear }</li>
						 </ul>
					  </div>
					  				 
				  </div>
				  <div class="clearfix"></div>
					<div class="sofaset-info" style="word-wrap:break-word; word-break:break-all;display:block;">
						 ${item.note }
				  </div>
		  <!---->
		   <div class="product-table">
		   		 <div class="item-sec">
					 <h4>销售数据</h4>
					 <table class="table table-bordered">
					 <tbody>
							<tr>
								<td><p>今天</p></td>
								<td><p>${today }</p></td>
							</tr>
							<tr>
								<td><p>本周</p></td>
								<td><p>${week }</p></td>
							</tr>
							<tr>
								<td><p>本月</p></td>
								<td><p>${month }</p></td>
							</tr>
							<tr>
								<td><p>总计</p></td>
								<td><p>${total }</p></td>
							</tr>
							 													
						</tbody>
						</table>
				 </div>		
				 <h3 style="margin-top: 30px">评价:</h3>
				 <div class="item-sec">
				 <c:forEach items="${commentList }" var="item">
				 	<c:if test="${not empty item.comment }">
					 <h4>[ ${item.userOrder.buyer.user.userName } ]: ${item.comment }</h4>
				 	</c:if>
				 </c:forEach>
				 </div>		
				  
			</div>
		  	 <div class="arrivals">	
		 <h3>推荐商品</h3>
		 <div class="arrival-grids">			 
			 <ul id="flexiselDemo1">
			 	<c:forEach items="${Recommend.rlist }" var="ritem">
				 <li>
					 <a href="${ctx}/com/getGoods.action?uid=${ritem.id}"><img src="${ctx}/resource/${ritem.imgpath}" alt=""/>	
					 <div class="arrival-info">
						 <h4>${ritem.name }</h4>
						 <p>¥ ${ritem.offPrice }</p>
						 <span class="pric1"><del>¥ ${ritem.price }</del></span>
						 <span class="disc">[${ritem.offPercent }% Off]</span>
					 </div>
					 <div class="viw">
						<a href="${ctx}/com/getGoods.action?uid=${ritem.id}"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>Quick View</a>
					 </div></a>				 
				 </li>
			 	</c:forEach>
				  
				</ul>
				<script type="text/javascript">
				 $(window).load(function() {			
				  $("#flexiselDemo1").flexisel({
					visibleItems: 4,
					animationSpeed: 1000,
					autoPlay: true,
					autoPlaySpeed: 3000,    		
					pauseOnHover:true,
					enableResponsiveBreakpoints: true,
					responsiveBreakpoints: { 
						portrait: { 
							changePoint:480,
							visibleItems: 1
						}, 
						landscape: { 
							changePoint:640,
							visibleItems: 2
						},
						tablet: { 
							changePoint:768,
							visibleItems: 3
						}
					}
				});
				});
				</script>
							<script type="text/javascript" src="${ctx}/qiantai/js/jquery.flexisel.js"></script>	  
				 </div>
			</div>		
			<!---->
		    </div>
		    
		  <div class="rsidebar span_1_of_left">
				<section  class="sky-form">
					 	<div class="product_right">
						 <h4 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>分类列表</h4>
						 <div class="tab1">
							 <ul class="place">								
								 <li class="sort">商品类型</li>
								 <li class="by"><img src="${ctx}/qiantai/images/do.png" alt=""></li>
									<div class="clearfix"> </div>
							  </ul>
							 <div class="single-bottom">						
								<c:forEach items="${list1 }" var="item">
									<a href="${ctx}/com/list.action?uid=${item.id}"><p>${item.name }</p></a>
							 	</c:forEach>
						     </div>
					      </div>						  
						  
						  <!--script-->
						<script>
							$(document).ready(function(){
								//$(".tab1 .single-bottom").hide();
								$(".tab2 .single-bottom").hide();
								$(".tab3 .single-bottom").hide();
								$(".tab4 .single-bottom").hide();
								$(".tab5 .single-bottom").hide();
								
								$(".tab1 ul").click(function(){
									$(".tab1 .single-bottom").slideToggle(300);
									$(".tab2 .single-bottom").hide();
									$(".tab3 .single-bottom").hide();
									$(".tab4 .single-bottom").hide();
									$(".tab5 .single-bottom").hide();
								})
								$(".tab2 ul").click(function(){
									$(".tab2 .single-bottom").slideToggle(300);
									$(".tab1 .single-bottom").hide();
									$(".tab3 .single-bottom").hide();
									$(".tab4 .single-bottom").hide();
									$(".tab5 .single-bottom").hide();
								})
								$(".tab3 ul").click(function(){
									$(".tab3 .single-bottom").slideToggle(300);
									$(".tab4 .single-bottom").hide();
									$(".tab5 .single-bottom").hide();
									$(".tab2 .single-bottom").hide();
									$(".tab1 .single-bottom").hide();
								})
								$(".tab4 ul").click(function(){
									$(".tab4 .single-bottom").slideToggle(300);
									$(".tab5 .single-bottom").hide();
									$(".tab3 .single-bottom").hide();
									$(".tab2 .single-bottom").hide();
									$(".tab1 .single-bottom").hide();
								})	
								$(".tab5 ul").click(function(){
									$(".tab5 .single-bottom").slideToggle(300);
									$(".tab4 .single-bottom").hide();
									$(".tab3 .single-bottom").hide();
									$(".tab2 .single-bottom").hide();
									$(".tab1 .single-bottom").hide();
								});
							});
						</script>
						<!-- script -->					 
				 </section>
			 </div> 
		     <div class="clearfix"></div>
	  </div>	 
</div>
 <jsp:include page="./foot.jsp"></jsp:include> 
 <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"0","bdPos":"right","bdTop":"100"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>