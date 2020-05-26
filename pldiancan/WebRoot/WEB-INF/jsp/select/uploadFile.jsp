<%@ page pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="contentTitle">选择需要上传的文件</h2>
<form action="${ctx}/com/fileUpload.action" method="post" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, $.bringBack)">
<input type="hidden" name="fileType" value="${fileType }">
<div class="pageContent">
	<div class="pageFormContent" >
		<dl>
			<dt>附件：</dt><dd><input type="file" name=upload class="required" size="30" /></dd>
		</dl>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">上传</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>
</form>