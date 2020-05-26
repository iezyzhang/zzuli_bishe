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
<jsp:include page="./menu.jsp"><jsp:param value="index" name="type"/> </jsp:include>
<!---->
<div class="container">
	  <ol class="breadcrumb">
	  <c:if test="${not empty regErrorMessage }">
				<div class="alert alert-error"><strong>提示：</strong><br /><i class="icon-exclamation-sign"></i> ${regErrorMessage}</div>
			</c:if>
			<%session.removeAttribute("regErrorMessage"); %>
		 </ol>
	 <div class="registration">
		 <div class="registration_left">
		 	
			 <h2>新用户? <span> 注册新账号</span></h2>
			 <!-- [if IE] 
				< link rel='stylesheet' type='text/css' href='ie.css'/>  
			 [endif] -->  
			  
			 <!-- [if lt IE 7]>  
				< link rel='stylesheet' type='text/css' href='ie6.css'/>  
			 <! [endif] -->  
			 <script>
				(function() {
			
				// Create input element for testing
				var inputs = document.createElement('input');
				
				// Create the supports object
				var supports = {};
				
				supports.autofocus   = 'autofocus' in inputs;
				supports.required    = 'required' in inputs;
				supports.placeholder = 'placeholder' in inputs;
			
				// Fallback for autofocus attribute
				if(!supports.autofocus) {
					
				}
				
				// Fallback for required attribute
				if(!supports.required) {
					
				}
			
				// Fallback for placeholder attribute
				if(!supports.placeholder) {
					
				}
				
				// Change text inside send button on submit
				var send = document.getElementById('register-submit');
				if(send) {
					send.onclick = function () {
						this.innerHTML = '...Sending';
					}
				}
			
			 })();
			 </script>
			 <div class="registration_form">
			 <!-- Form -->
				<form id="registration_form" action="${pageContext.request.contextPath }/com/regUser.action" method="post">
					<div>
						<label>
							<input placeholder="账号:" name="bean.user.uname" type="text" tabindex="1" required autofocus>
						</label>
					</div>
					<div>
						<label>
							<input placeholder="姓名:" name="bean.user.userName" type="text" tabindex="2" required autofocus>
						</label>
					</div>
					<div>
						<label>
							<input placeholder="邮箱:" name="bean.user.userEmail" type="email" tabindex="3" required>
						</label>
					</div>
					<div>
						<label>
							<input placeholder="手机号码:" name="bean.user.userPhone" type="text" tabindex="3" required>
						</label>
					</div>					
					<div>
						<label>
							<input placeholder="地址:" name="bean.user.userAddress" type="text" tabindex="3" required>
						</label>
					</div>					
					<div class="sky_form1">
							<ul>
								<li><label class="radio left"><input type="radio" name="bean.user.userGender" value="1" checked=""><i></i>男</label></li>
								<li><label class="radio"><input type="radio" name="bean.user.userGender" value="0"><i></i>女</label></li>
								<div class="clearfix"></div>
							</ul>
					</div>					
					<div>
						<label>
							<input placeholder="密码" type="password" tabindex="4" required>
						</label>
					</div>						
					<div>
						<label>
							<input placeholder="确认密码" name="bean.user.userPassword" type="password" tabindex="4" required>
						</label>
					</div>
					 
					<div>
						<input type="submit" value="提交注册" id="register-submit">
					</div>
					 
				</form>
				<!-- /Form -->
			 </div>
		 </div>
		 <div class="registration_left">
			 <h2>已注册用户</h2>
			 <div class="registration_form">
			 <!-- Form -->
				<form id="registration_form" action="${pageContext.request.contextPath}/com/userLogin.action" method="post">
					<div>
						<label>
							<input placeholder="账号:" type="text" name="name" tabindex="3" required>
						</label>
					</div>
					<div>
						<label>
							<input placeholder="密码:" type="password" name="password" tabindex="4" required>
						</label>
					</div>						
					<div>
						<input type="submit" value="登录" id="register-submit">
					</div>
				</form>
			 <!-- /Form -->
			 </div>
		 </div>
		 <div class="clearfix"></div>
	 </div>
</div>
<jsp:include page="./foot.jsp"></jsp:include> 
</body>
</html>