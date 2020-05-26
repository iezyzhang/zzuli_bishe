<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<h2 class="contentTitle">修改商品信息</h2>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/updateGoods.action" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this,navTabAjaxDone)">
		<input type="hidden" value="${modifybean.id}" name="id"/>
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl class="nowrap">
							<dt>图片：</dt>
							<dd>
								<img alt="" src="${ctx }/resource/${modifybean.imgpath}" width="150px" height="150px">
								<br/>
								<input name="attachment.attachmentPath" value="${modifybean.imgpath }" type="hidden">
								<input class="readonly" name="attachment.fileName" value="" readonly="readonly" type="text"/>
								<a class="btnAttach" href="${ctx }/sys/toUpload.action" lookupGroup="attachment" width="560" height="300" title="商品图片 ">Attachment </a>
							</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>类型：</dt>
							<dd>
								<select name="bookType.id" class="combox" id="dfsrwer34333333333333333" >
									<c:forEach items="${list }" var="item">
										<option value="${item.id }">${item.name }</option>
									</c:forEach>
								</select>
							</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>名称：</dt>
							<dd><input name="name" value="${modifybean.name}" type="text"  class="required"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>场景：</dt>
							<dd><input name="sid" value="${modifybean.sid}" type="text"  class="required"/></dd>
							</dl></td>
					</tr>
					
					<tr>
						<td colspan="2"><dl>
							<dt>原始价格：</dt>
							<dd><input name="price" value="${modifybean.price}" type="text"  class="required number"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>出售价格：</dt>
							<dd><input name="offPrice" value="${modifybean.offPrice}" type="text"  class="required number"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>打折：</dt>
							<dd><input name="offPercent"  value="${modifybean.offPercent}" type="text"  class="required digits"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>菜品：</dt>
							<dd><input name="author" value="${modifybean.author}" type="text"  class="required"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>口味：</dt>
							<dd><input name="press" value="${modifybean.press}" type="text"  class="required"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>主要食材：</dt>
							<dd><input name="publicationYear" value="${modifybean.publicationYear}" type="text"  class=" required"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>明细：</dt>
							<dd>
							<textarea rows="10" cols="80" name="note">${modifybean.note }</textarea>
							</dd>
							</dl></td>
					</tr>
					 
					 
				</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset" class="reset">重置</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">$("#dfsrwer34333333333333333").val("${modifybean.bookType.id}");
</script>