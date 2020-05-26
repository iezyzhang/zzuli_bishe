<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/sys/queryUserOrder.action">
	<input type="hidden" name="pageNum" value="" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sys/queryUserOrder.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>订单号：</label>
				<input type="text" name="s_sid" value=""/>
			</li>
			<li>
				<label>日期：</label>
				<input type="text" name="s_addDate" value="" readonly="readonly" class="date"/>
			</li>
			<li>
				<label>订单状态：</label>
				<select name="s_userOrder.status">
				<option value="">选择状态</option>
				<option value="Shipping">待发货</option>
				<option value="Shipped">已发货</option>
				<option value="Received">已完成</option>
				</select>
			</li>
			<li>
				<label>评价状态：</label>
				<select name="s_status">
				<option value="">选择状态</option>
				<option value="未评价">未评价</option>
				<option value="已评价">已评价</option>
				</select>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
<!-- 			<li><a class="add" href="${ctx}/sys/add2UserOrder.action" target="navTab"  rel="baseAdd" title="Add One"> <span>Add</span></a></li> -->
			<li><a class="edit" href="${ctx}/sys/getUserOrder.action?uid={sid_select}" target="navTab" rel="baseAdd" warn="选择需要操作的行" title="订单信息"><span>查看订单</span></a></li>
<!-- 			<li><a title="Delete All Selected?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/sys/deleteUserOrder.action" class="delete" warn="选择需要操作的行"><span>Delete</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
<!-- 				<th width="3%"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
				<th width="100">订单号</th>
				<th width="100">买家</th>
				<th width="100">日期</th>
				<th width="100">金额</th>
				<th width="100">状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${SESSION_PAGE.list}" var="item">
			<tr target="sid_select" rel="${item.id }">
<!-- 				<td><input name="ids" value="${item.id }" type="checkbox"></td> -->
				<td>${item.sid}</td>
				<td>${item.buyer.user.userName}</td>
				<td>${item.addDate}</td>
				<td>${item.money}</td>
				<td>${item.statusStr}</td>
				<td width="70">
					<a title="订单信息"  href="${ctx}/sys/getUserOrder.action?uid=${item.id}" class="btnEdit" target="navTab" rel="baseAdd">Edit One</a>
<!-- 					<a title="Delete All Selected?" target="ajaxTodo" href="${ctx}/sys/deleteUserOrder.action?ids=${item.id}" class="btnDel" style="margin-left: 10px">Delete</a> -->
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>总数: ${SESSION_PAGE.totalNumber}</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${SESSION_PAGE.totalNumber}" numPerPage="${SESSION_PAGE.itemsPerPage}" pageNumShown="10" currentPage="${SESSION_PAGE.currentPageNumber}"></div>
	</div>
</div>
<%@ include file="../common/clear.jsp"%>
