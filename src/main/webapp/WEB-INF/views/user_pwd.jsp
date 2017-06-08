<%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-3-1
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="include/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	 * 退出
	 */
	function exit() {
		if (confirm("是否确认退出？")) {
			location.href = "${ctx}/logout"
		}
	}
	/**
	 * 原始密码校验
	 */
	function checkPsw0(index) {
		if (index) { // onblur
			if (!$('#psw0').val()) {
				$('#psw0Text').html("密码不能为空").css({
					color: '#ff5858'
				});
				$('#psw0Img').show();
				return;
			}
			if (!/^[0-9a-zA-Z+-_]+$/.test($('#psw0').val())) {
				$('#psw0Text').html("密码包括数字、字母、+、-、_").css({
					color: '#ff5858'
				});
				$('#psw0Img').show();
				return;
			}
			$.ajax({
				type:"POST",
				data:$('#mainForm').serialize(),
				url:"${ctx}/user/checkPsw?loginPsw=" + $('#psw0').val(),
				async: false,
				success:function(data){
					if (data) {
						$('#psw0Text').html("原始密码正确!").css({
							color: '#00ff00'
						});
						$('#psw0Img').hide();
					} else {
						$('#psw0Text').html("原始密码错误!").css({
							color: '#ff5858'
						});
						$('#psw0Img').show();
					}
				}
			});
			return;
		} else { // onfocus
			$('#psw0Text').html("密码长度为1-16位,可以包括数字、字母、+、-、_").css({
				color: '#000000'
			});
			$('#psw0Img').hide();
			return;
		}
	}

	/**
	 * 新密码校验
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
	 * 更改密码
	 */
	function changePsw() {
		$.ajax({
			type:"POST",
			data:$('#pswForm').serialize(),
			url:"${ctx}/user/changePsw",
			async: false,
			success:function(data){
				if (data == 1) {
					window.location.href = "${ctx}/user/toUserInfo"
					setTimeout(function(){
						window.location.href = '${ctx}/user/toUserInfo';
					},1);
				} else {
					$('#psw0Text').html(data).css({
						color: '#ff5858'
					});
					$('#psw0Img').show();
				}
			}
		});
	}
</script>
<body>
<div id="page-container" class="page-sidebar-fixed page-header-fixed">
	<!-- begin #header -->
	<div id="header" class="header navbar navbar-default navbar-fixed-top">
		<div class="container-fluid bodyWidth">
			<div class="navbar-header logoBox">
				<a href="${ctx}/home" class="navbar-brand"></a>
			</div>
			<div class="collapse navbar-collapse" id="header-navbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${ctx}/home">首页</a></li>
					<li><a href="${ctx}/library">题库</a></li>
					<li><a href="#topList.html">龙虎榜</a></li>
					<li><a href="${ctx}/library/history">答题历史</a></li>
					<li class="dropdown navbar-user" style="margin-left:80px;">
						<a href="${ctx}/user/toUserInfo" class="dropdown-toggle" data-toggle="dropdown">
							<img src="<c:choose><c:when test="${empty loginUser.photoUrl}">${preUrl}${dftPreUrl}</c:when><c:otherwise>${preUrl}${loginUser.photoUrl}</c:otherwise></c:choose>"
								 alt="<c:choose><c:when test="${empty loginUser.headPhoto}">头像</c:when><c:otherwise>${loginUser.headPhoto}</c:otherwise></c:choose>">
							<span class="hidden-xs">${loginUser.userName}</span>
						</a>
					</li>
					<li><a onclick="exit()">退出</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- end #header -->
	<!-- begin #content -->
	<div id="content" class="content bodyWidth m-auto">
		<div class="personal-box">
			<h2>修改密码</h2>
			<form id="pswForm" action="" method="post">
				<table class="personal-table">
					<tr>
						<td><label>原始密码：</label></td>
						<td>
							<input type="password" id="psw0" name="oldPassword" class="form-control input-lg" placeholder="请输入原始密码" maxlength="16" onfocus="checkPsw0(0)" onblur="checkPsw0(1)"/>
							<p class="err_tips" ><img id="psw0Img" src="${ctx}/resources/img/u_loginErrorTip.png" alt="" /><span id="psw0Text"></span></p>
						</td>
					</tr>
					<tr>
						<td><label>新密码：</label></td>
						<td>
							<input type="password" id="psw1" name="newPassword" class="form-control input-lg" placeholder="请输入新密码" maxlength="16" onfocus="checkPsw1(0)" onblur="checkPsw1(1)"/>
							<p class="err_tips" ><img id="psw1Img" src="${ctx}/resources/img/u_loginErrorTip.png" alt="" /><span id="psw1Text"></span></p>
						</td>
					</tr>
					<tr>
						<td><label>确认密码：</label></td>
						<td>
							<input type="password" id="psw2" class="form-control input-lg" placeholder="请再次输入新密码" maxlength="16" onblur="checkPsw2()"/>
							<p class="err_tips" ><img id="psw2Img" src="${ctx}/resources/img/u_loginErrorTip.png" alt="" /><span id="psw2Text"></span></p>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<button type="button" onclick="changePsw()" class="btn btn-success btn-block btn-lg">保存</button>
							<button type="button" class="btn btn-success btn-block btn-lg" onclick="history.back()">返回</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- end #content -->

	<div class="header navbar navbar-inverse text-center lineHeight50" style="margin-top:20px; line-height:50px;">
		<span>copyright 123445</span>
	</div>
</div>
</body>
</html>
