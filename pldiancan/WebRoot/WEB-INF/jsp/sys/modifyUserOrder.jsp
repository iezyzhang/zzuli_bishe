<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<h2 class="contentTitle">订单明细</h2>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/updateUserOrder.action" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this)">
		<input type="hidden" value="${modifybean.id}" name="id"/>
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>订单号：</dt>
							<dd>${modifybean.sid }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>买家姓名：</dt>
							<dd>${modifybean.buyer.user.userName }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>买家电话：</dt>
							<dd>${modifybean.buyer.user.userPhone }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>买家地址：</dt>
							<dd>${modifybean.buyer.user.userAddress}</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>日期：</dt>
							<dd>${modifybean.addDate }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>金额：</dt>
							<dd>${modifybean.money }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>状态：</dt>
							<dd>${modifybean.statusStr }</dd>
							</dl></td>
					</tr>
					 
				</table>
				<div class="tabs">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li class="selected"><a href="javascript:void(0)"><span>Books</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height: 250px;">
				<div>
					<table class="list nowrap"  width="100%">
						<thead>
							<tr>
								<th>场景</th>
								<th>名称</th>
								<th>购买数量</th>
								<th>金额</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.book.sid }</td>
								<td>${item.book.name }</td>
								<td>${item.amount}</td>
								<td>${item.price }</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				 
			</div>
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
			</div>
		</div>
		</div>
		<div class="formBar">
			<ul>
			<c:if test="${modifybean.status=='Shipping' }">
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认无误并发货</button></div></div></li>
			</c:if>
			</ul>
		</div>
	</form>
</div>
