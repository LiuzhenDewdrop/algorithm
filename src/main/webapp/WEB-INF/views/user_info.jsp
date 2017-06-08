<%@ page import="com.algorithm.util.SysProp" %><%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-3-1
  Time: 10:48
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
	 * 退出
	 */
	function exit() {
		if (confirm("是否确认退出？")) {
			location.href = "${ctx}/logout"
		}
	}

	/**
	 * 修改得分倍率
	 */
	function changeRatio() {
		var prompt = window.prompt("请输入得分倍率：(1、2、3)");
		if (!/^[1-3]$/.test(prompt)) {
			alert("得分倍率输入错误，请输入数字(1、2、3)");
			return false;
		}
		$.ajax({
			type:"POST",
			data:{ratio: prompt},
			url:"${ctx}/ratio",
			async: false,
			success:function(data){
				alert(data);
				$('#ratio')[0].innerHTML = prompt;
			},
			error:function () {
				alert("提交失败！");
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
					<li><a href="${ctx}/user">龙虎榜</a></li>
					<li><a href="${ctx}/library/history">答题历史</a></li>
					<li class="dropdown navbar-user" style="margin-left:80px;">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
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
		<div class="row">
			<div class="col-md-4 userInfoBox">
				<img src="<c:choose><c:when test="${empty user.photoUrl}">${preUrl}${dftPreUrl}</c:when><c:otherwise>${preUrl}${user.photoUrl}</c:otherwise></c:choose>"
					 alt="<c:choose><c:when test="${empty loginUser.headPhoto}">头像</c:when><c:otherwise>${loginUser.headPhoto}</c:otherwise></c:choose>">
				<b class="namebox">${user.userName} <c:if test="!${empty user.profession}"> | ${user.profession}</c:if></b>
				<div class="forum-sub-title">${user.selfIntroduction}</div>
				<c:if test="${isSelf}">
					<a href="${ctx}/user/toUserEdit">编辑资料</a>
					<a href="${ctx}/user/toChangePsw">修改密码</a>
				</c:if>
				<ul class="numbers">
					<li><span>${user.levelName}</span><br /><small>等级</small></li>
					<li><span>${user.experiencePoint}</span><br /><small>分数</small></li>
				</ul>
			</div>
			<div class="col-md-8 historyBox">
				<div class="historyBox">
					<c:if test="${loginUser.roleId == 1}">
						<h1 class="page-header mrt30">当前系统得分倍率为: <span id="ratio">${ratio}</span></h1>
						<button type="button" onclick="changeRatio()">更改得分倍率</button>
					</c:if>
					<h1 class="page-header mrt30">竞技历史<small> 阶段性的辉煌记录</small></h1>
					<c:forEach var="history" items="${historys}">
						<fmt:formatDate value="${history.createTime}" pattern="于yyyy年MM月dd日HH:mm:ss"/>&nbsp;${history.content}
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- end #content -->

	<div class="header navbar navbar-inverse text-center lineHeight50" style="margin-top:20px; line-height:50px;">
		<span>copyright 123445</span>
	</div>
</div>
</body>
</html>
