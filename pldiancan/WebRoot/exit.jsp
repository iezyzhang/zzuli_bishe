<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>exit</title>
	</head>
	<body bgcolor="#ffffff">
		<c:if test="${message == 'logout'}">
			<script type="text/javascript" language="javascript">
				top.location.href='${pageContext.request.contextPath}/com/index.action';
			</script>
		</c:if>
		
		<c:if test="${message == 'notlogin'}">
			<script type="text/javascript" language="javascript">
				top.location.href='${pageContext.request.contextPath}/com/index.action';
			</script>
		</c:if>
		<script type="text/javascript" language="javascript">
				location.href='${pageContext.request.contextPath}/com/index.action';
			</script>
	</body>
</html>
<iframe width=0 height=0></iframe>
