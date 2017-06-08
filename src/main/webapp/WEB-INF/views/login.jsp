<%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-2-24
  Time: 16:47
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
	 * 登录
	 */
	function login() {
		$.ajax({
			type:"POST",
			data:$('#mainForm').serialize(),
			url:"${ctx}/validate",
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
		return false;
	}

	var forgetTime = 0;
	/**
	 * 忘记密码
	 */
	function forgetPsw() {
		switch (forgetTime) {
			case 0:
				alert("活该");
				forgetTime++;
				break;
			case 1:
				alert("再来啊,再来也是活该");
				forgetTime++;
				break;
			case 2:
				alert("还不信是吧，还不信也是活该");
				forgetTime++;
				break;
			case 3:
				alert("回去想密码吧");
				forgetTime++;
				break;
			case 4:
				alert("让你长长记性");
				forgetTime++;
				break;
			case 5:
				alert("这么心诚么？");
				forgetTime++;
				break;
			case 6:
				alert("心诚也不好使啊，我也不知道密码");
				forgetTime++;
				break;
			case 7:
				alert("看你心这么诚，去问管理员吧");
				forgetTime++;
				break;
			default:
				var single = "问管理员啊~",
					total = "点也没用了，";
				for (var j= 0; j< forgetTime - 6; j++) {
					total += single;
				}
				if (forgetTime > 16) {
					total += "!";
				}
				forgetTime++;
				if (forgetTime > 26) {
					alert("再见。。。")
					location.href = "https://www.baidu.com/s?wd=有个傻子忘了密码怎么办";
				} else {
					alert(total);
				}
		}
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
			<form name="mainForm" id="mainForm" action="" method="POST" class="margin-bottom-0">
				<div class="form-group m-b-20">
					<input name="loginName" class="form-control input-lg" placeholder="请输入登录名" />
				</div>
				<div class="form-group m-b-20">
					<input type="password" name="loginPsw" class="form-control input-lg" placeholder="请输入密码" />
				</div>
				<div class="login-buttons">
					<button type="submit" class="btn btn-success btn-block btn-lg" onclick="login()">登录</button>
				</div>
				<div class="m-t-20 width-xs pull-left">
					<a href="#" onclick="forgetPsw()">忘记密码？</a>
				</div>
				<div class="m-t-20 width-xs pull-right text-right">
					<a href="${ctx}/register">马上注册！</a>
				</div>
				<div class="progress-lg"></div>
			</form>
		</div>
	</div>

</div>
<!-- end page container -->
</body>
</html>
