<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<h2 class="contentTitle">执行关联推荐算法 [ 成功执行推荐算法之后,原有的商品推荐将会被清除并重新生成推荐列表 ]</h2>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/addRecommend.action" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this)">
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>支持度阈值：</dt>
							<dd><input name="support"  type="text" value="2" class="required digits" min="1"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>置信度阈值：</dt>
							<dd><input name="confidence"  type="text" value="0.7" class="required number" min="0"/></dd>
							</dl></td>
					</tr>
					 
				</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">执行关联推荐算法</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset" class="reset">重置</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
