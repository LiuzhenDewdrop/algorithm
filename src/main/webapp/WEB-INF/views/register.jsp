<%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-2-27
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="include/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8" />
	<title>信息技术中心竞技场</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- ================== BEGIN BASE CSS STYLE ================== -->
	<link href="${ctx}/resources/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${ctx}/resources/plugin/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="${ctx}/resources/css/style.min.css" rel="stylesheet" />
	<!-- ================== END BASE CSS STYLE ================== -->
	<script src="${ctx}/resources/js/jquery/jquery-1.9.1.min.js"></script>
</head>
<script type="text/javascript">
	/**
	 * 登录名验证
	 */
	function checkLoginName() {
		if (!$('#loginName').val()) {
			$('#loginText').html("登录名不能为空").css({
				color: '#ff5858'
			});
			$('#loginImg').show();
			return;
		}
		$.ajax({
			type:"POST",
			data:$('#loginName'),
			url:"${ctx}/register/checkLoginName",
			async: false,
			success:function(data){
				if (data) {
					$('#loginText').html("恭喜,该登录名可用!").css({
						color: '#00ff00'
					});
					$('#loginImg').hide();
				} else {
					$('#loginText').html("很遗憾,该登录名已被注册!").css({
						color: '#ff5858'
					});
					$('#loginImg').show();
				}
			}
		});
	}

	/**
	 * 用户名验证
	 */
	function checkUserName() {
		if (!$('#userName').val()) {
			$('#userText').html("用户名不能为空").css({
				color: '#ff5858'
			});
			$('#userImg').show();
			return;
		}
		$.ajax({
			type:"POST",
			data:$('#userName'),
			url:"${ctx}/register/checkUserName",
			async: false,
			success:function(data){
				if (data) {
					$('#userText').html("恭喜,该用户名可用!").css({
						color: '#00ff00'
					});
					$('#userImg').hide();
				} else {
					$('#userText').html("很遗憾,该用户名已被注册!").css({
						color: '#ff5858'
					});
					$('#userImg').show();
				}
			}
		});
	}

	/**
	 * 密码校验
	 */
	function checkPsw1(index) {
		if (index) { // onblur
			if (!$('#psw1').val()) {
				$('#psw1Text').html("密码不能为空").css({
					color: '#ff5858'
				});
				$('#psw1Img').show();
				return;
			}
			if (!/^[0-9a-zA-Z+-_]+$/.test($('#psw1').val())) {
				$('#psw1Text').html("密码包括数字、字母、+、-、_").css({
					color: '#ff5858'
				});
				$('#psw1Img').show();
				return;
			}
			if ($('#psw2').val()) {
				if ($('#psw1').val() == $('#psw2').val()) {
					$('#psw2Text').html("两次密码相同").css({
						color: '#00ff00'
					});
					$('#psw2Img').hide();
				} else {
					$('#psw2Text').html("两次密码不相同").css({
						color: '#ff5858'
					});
					$('#psw2Img').show();
					return;
				}
			}
			$('#psw1Text').html("");
			$('#psw1Img').hide();
		} else { // onfocus
			$('#psw1Text').html("密码长度为1-16位,可以包括数字、字母、+、-、_").css({
				color: '#ffffff'
			});
			$('#psw1Img').hide();
			return;
		}
	}

	/**
	 * 两次密码相同验证
	 */
	function checkPsw2() {
		if (!$('#psw2').val()) {
			$('#psw2Text').html("密码不能为空").css({
				color: '#ff5858'
			});
			$('#psw2Img').show();
			return;
		}
		if ($('#psw1').val() == $('#psw2').val()) {
			$('#psw2Text').html("两次密码相同").css({
				color: '#00ff00'
			});
			$('#psw2Img').hide();
		} else {
			$('#psw2Text').html("两次密码不相同").css({
				color: '#ff5858'
			});
			$('#psw2Img').show();
			return;
		}
	}
	
	/**
	 * 注册
	 */
	function register() {
		if (!$('#loginName').val().replace(/(^\s*)|(\s*$)/g, '')) {
			$('#loginText').html("登录名不能为空").css({
				color: '#ff5858'
			});
			$('#loginImg').show();
			alert("登录名不能为空");
			return;
		}
		if (!$('#psw1').val()) {
			$('#psw1Text').html("密码不能为空").css({
				color: '#ff5858'
			});
			$('#psw1Img').show();
			$('#psw1').focus();
			return;
		}
		if (!$('#psw2').val()) {
			$('#psw2Text').html("密码不能为空").css({
				color: '#ff5858'
			});
			$('#psw2Img').show();
			$('#psw2').focus();
			return;
		}
		if (!$('#userName').val().replace(/(^\s*)|(\s*$)/g, '')) {
			$('#userText').html("用户名不能为空").css({
				color: '#ff5858'
			});
			$('#userImg').show();
			$('#userName').focus();
			return;
		}
		$.ajax({
			type:"POST",
			data:$('#registerForm').serialize(),
			url:"${ctx}/register/submit",
			async: false,
			success:function(data){
				if (data == 1) {
					window.location.href = "${ctx}/home"
					setTimeout(function(){
						window.location.href = '${ctx}/home';
					},1);
				} else {
					alert(data);
				}
			}
		});
	}
</script>
<body>
<div class="login-cover">
	<div class="login-cover-image"><img src="${ctx}/resources/img/login-bg/bg-4.jpg" data-id="login-cover-image" alt="" /></div>
	<div class="login-cover-bg"></div>
</div>
<!-- begin #page-container -->
<div id="page-container">
	<!-- begin login -->
	<div class="login login-v2" data-pageload-addclass="animated flipInX">
		<div class="login-content">
			<form id="registerForm" action="" method="POST" class="margin-bottom-0">
				<div class="form-group m-b-20 err_tips_box">
					<input type="text" name="loginName" id="loginName" class="form-control input-lg" placeholder="请输入登录名" onblur="checkLoginName()"/>
					<p class="err_tips" ><img id="loginImg" src="${ctx}/resources/img/u_loginErrorTip.png" alt="" /><span id="loginText"></span></p>
				</div>
				<div class="form-group m-b-20 err_tips_box">
					<input type="password" id="psw1" name="loginPassword" class="form-control input-lg" placeholder="请输入密码" maxlength="16" onfocus="checkPsw1(0)" onblur="checkPsw1(1)"/>
					<p class="err_tips" ><img id="psw1Img" src="${ctx}/resources/img/u_loginErrorTip.png" alt="" /><span id="psw1Text"></span></p>
				</div>
				<div class="form-group m-b-20 err_tips_box">
					<input type="password" id="psw2" class="form-control input-lg" placeholder="请再次输入密码" maxlength="16" onblur="checkPsw2()"/>
					<p class="err_tips" ><img id="psw2Img" src="${ctx}/resources/img/u_loginErrorTip.png" alt="" /><span id="psw2Text"></span></p>
				</div>
				<div class="form-group m-b-20 err_tips_box">
					<input type="text" name="userName" id="userName" class="form-control input-lg" placeholder="请输入用户昵称" onblur="checkUserName()"/>
					<p class="err_tips" ><img id="userImg" src="${ctx}/resources/img/u_loginErrorTip.png" alt="" /><span id="userText"></span></p>
				</div>
				<div class="login-buttons">
					<button type="submit" class="btn btn-success btn-block btn-lg" onclick="register()">注册</button>
				</div>
				<div class="m-t-20 width-xs pull-right text-right">
					<a href="${ctx}/login">已有账号，去登录！</a>
				</div>
				<div class="progress-lg"></div>
			</form>
		</div>
	</div>

</div>
<!-- end page container -->
</body>
</html>
