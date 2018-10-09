<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css" />
	</head>
	<jsp:include page="common.jsp" />

	<body>

		<div id="login">
			<h1>Login</h1>
			<form method="post" action="login/login" class="login_form">
				<ul>
					<li>
						<img class="icon" src="./jquery-easyui-1.6.2/themes/icons/user.png" alt="" />
						<input type="text" required="required" placeholder="用户名" name="userName" autocomplete="off" />
					</li>
					<li>
						<img class="icon" src="./jquery-easyui-1.6.2/themes/icons/lock_key.png" alt="" />
						<input type="password" required="required" placeholder="密码" name="password" autocomplete="off" />
					</li>
					<li>
						<input type="text" style="width: 150px;float: left;padding-left: 40px;" required="required" placeholder="验证码" name="valicode"></input>
						<img style="margin-left: 0px;" id="valicodeImage" src="base/getPicStream" onclick="reloadPic()" />
					</li>
					<li>
						<button class="but" type="submit">登录</button>
					</li>
					
						<p style="color: red;font-size: 14px;font-weight: bold;">${msg}</p>

				</ul>

			</form>
		</div>
	</body>
	<script type="text/javascript">
		function reloadPic() {
			$("#valicodeImage").attr('src', "base/getPicStream?" + new Date().getTime());
		}
	</script>

</html>