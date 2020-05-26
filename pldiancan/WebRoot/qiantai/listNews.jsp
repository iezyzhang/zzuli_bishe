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
<jsp:include page="./menu.jsp"><jsp:param value="news" name="type"/> </jsp:include>
<!---->
<div class="single-sec">
	 <div class="container">
		 <ol class="breadcrumb">
		 </ol>
		 <!-- start content -->	
		 <div class="col-md-9 det">
		  <!---->
		   <div class="product-table">
		   		 <div class="item-sec">
					 <h4>新闻列表</h4>
					 <table class="table table-bordered">
					 <tbody>
					 <c:forEach items="${list }" var="item">
							<tr>
								<td><p><a href="${ctx}/com/getNews.action?iuid=${item.id}">${item.title}</a></p></td>
							</tr>
					 </c:forEach>
							 													
						</tbody>
						</table>
				 </div>		
			</div>
		  		
			<!---->
		    </div>
		    
		   
		     <div class="clearfix"></div>
	  </div>	 
</div>
 <jsp:include page="./foot.jsp"></jsp:include> 
 <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"0","bdPos":"right","bdTop":"100"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>