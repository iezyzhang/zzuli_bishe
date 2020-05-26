<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/sys/queryGoods.action">
	<input type="hidden" name="pageNum" value="" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sys/queryGoods.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>名称：</label>
				<input type="text" name="s_name" value=""/>
			</li>
			<li>
				<label>编号：</label>
				<input type="text" name="s_sid" value=""/>
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
			<li><a class="add" href="${ctx}/sys/add2Goods.action" target="navTab"  rel="baseAdd" title="添加信息"> <span>添加</span></a></li>
			<li><a class="edit" href="${ctx}/sys/getGoods.action?uid={sid_select}" target="navTab" rel="baseAdd" warn="选择需要操作的行" title="详细信息"><span>编辑</span></a></li>
			<li><a title="Delete All Selected?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/sys/deleteGoods.action" class="delete" warn="选择需要操作的行"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="3%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100">名称</th>
				<th width="100">场景</th>
				<th width="100">类型</th>
				<th width="100">原始价格</th>
				<th width="100">出售价格</th>
				<th width="100">打折</th>
				<th width="100">菜品</th>
				<th width="100">口味</th>
				<th width="100">主要食材</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${SESSION_PAGE.list}" var="item">
			<tr target="sid_select" rel="${item.id }">
				<td><input name="ids" value="${item.id }" type="checkbox"></td>
				<td>${item.name}</td>
				<td>${item.sid}</td>
				<td>${item.bookType.name}</td>
				<td>${item.price}</td>
				<td>${item.offPrice}</td>
				<td>${item.offPercent}</td>
				<td>${item.author}</td>
				<td>${item.press}</td>
				<td>${item.publicationYear}</td>
				<td width="70">
					<a title="Edit One"  href="${ctx}/sys/getGoods.action?uid=${item.id}" class="btnEdit" target="navTab" rel="baseAdd">Edit One</a>
					<a title="Delete All Selected?" target="ajaxTodo" href="${ctx}/sys/deleteGoods.action?ids=${item.id}" class="btnDel" style="margin-left: 10px">Delete</a>
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
