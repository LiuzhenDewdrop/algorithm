<%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-4-6
  Time: 14:21
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
<script type="application/javascript">
	/**
	 * 退出
	 */
	function exit() {
		if (confirm("是否确认退出？")) {
			location.href = "${ctx}/logout"
		}
	}


	/**
	 * 提交
	 */
	function submitForm() {
		if (!$('#matchTitle').val()) {
			alert("输入题目标题");
			return;
		}
		if (!$('#matchContent').val() || $('#matchContent').val() == "请输入描述内容") {
			alert("输入描述内容");
			return;
		}
		$.ajax({
			type:"POST",
			data:$('#newSubForm').serialize(),
			url:"${ctx}/match/add",
			async: false,
			success:function(data){
				alert(data);
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
					<li class="active"><a href="${ctx}/library">题库</a></li>
					<li><a href="${ctx}/user">龙虎榜</a></li>
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
		<h1 class="page-header mrt30">议事厅<small>这里是您发起竞赛的场所</small></h1>
		<form id="newSubForm" action="" method="POST" class="margin-bottom-0">
			<div>标题<small>为本次竞赛起个名字吧</small></div>
			<input type="text" name="matchTitle" id="matchTitle" class="form-control input-lg" placeholder="请输入竞赛标题" />
			<div>描述<small>简要描述本次竞赛吧</small></div>
			<div class="form-group m-b-20 err_tips_box">
				<textarea name="matchContent" id="matchContent" class="form-control input-lg"
							 onfocus="if(value=='请输入描述内容'){value=''}"
							 onblur="if (value ==''){value='请输入描述内容'}" >请输入描述内容</textarea>
			</div>
			<button type="button" onclick="submitForm()">保存</button>
			<button type="button" onclick="history.back()">返回</button>
		</form>

	</div>
	<!-- end #content -->
	<div class="header navbar navbar-inverse text-center lineHeight50" style="margin-top:20px; line-height:50px;">
		<span>copyright 123445</span>
	</div>
</div>
</body>
</html>
