<%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-3-7
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="include/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
	 * 更改用户设置
	 */
	function changeUser(id) {
		var prompt = window.prompt("把该用户变更为？\n1、管理员\n2、答题者\n3、出题者\n4、判题者\n5、参赛者\n6、启用\n0、禁用\n请选择对应的数字：");
		if (!/^[0-6]$/.test(prompt)) {
			alert("请选择正确的数字！");
			return false;
		}
		$.ajax({
			type:"POST",
			data:{userId: id, index: prompt},
			url:"${ctx}/user/changeUser",
			async: false,
			success:function(data){
				alert(data);
				window.location.reload();
			}
		});
	}
	
	/**
	 * 重置密码
	 * @param id
	 */
	function setDftPsw(id) {
		var prompt = window.prompt("请输入密码（包括数字、字母、+、-、_）", "123456");
		if (!/^[0-9a-zA-Z+-_]{1,16}$/.test(prompt)) {
			alert("密码格式不正确，长度为1-16位，可以包括数字、字母、+、-、_");
			return false;
		}
		$.ajax({
			type:"POST",
			data:{userId: id, psw: prompt},
			url:"${ctx}/user/setDftPsw",
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
					<li><a href="${ctx}/library">题库</a></li>
					<li class="active"><a href="${ctx}/user">龙虎榜</a></li>
					<li><a href="${ctx}/library/history">答题历史</a></li>
					<li class="dropdown navbar-user" style="margin-left:80px;">
						<a href="${ctx}/user/toUserInfo" class="dropdown-toggle" data-toggle="dropdown">
							<img src="<c:choose><c:when test="${empty loginUser.photoUrl}">${preUrl}${dftPreUrl}</c:when>
								<c:otherwise>${preUrl}${loginUser.photoUrl}</c:otherwise>
								</c:choose>" alt="<c:choose><c:when test="${empty loginUser.headPhoto}">头像</c:when>
								<c:otherwise>${loginUser.headPhoto}</c:otherwise>
								</c:choose>">
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
		<h1 class="page-header mrt30">技能排行榜<small> 高手竞技之实时排行榜</small></h1>
		<c:forEach var="user" items="${userList}">
			<div class="row topListBox">
				<div class="col-md-10">
					<a href="${ctx}/user/toUserInfo?id=${user.id}">
						<div class="forum-icon">
							<img src="<c:choose><c:when test="${empty user.photoUrl}">${preUrl}${dftPreUrl}</c:when><c:otherwise>${preUrl}${user.photoUrl}</c:otherwise></c:choose>"
								 alt="${user.userName}">
						</div>
					</a>
					<a href="${ctx}/user/toUserInfo?id=${user.id}"><b class="namebox">${user.userName} <c:if test="!${empty user.profession}"> | ${user.profession}</c:if></b></a>
					<div class="forum-sub-title">${user.selfIntroduction}</div>
				</div>
				<c:if test="${user.id == loginUser.id}">您在这里</c:if>
				<div class="col-md-1 forum-info">
					<span class="views-number">
						${user.rank}
					</span>
					<div class="direction">
						<small>排名</small>
					</div>
				</div>
				<div class="col-md-1 forum-info">
						<span class="views-number">
							${user.levelName}
						</span>
					<div class="direction">
						<small>等级</small>
					</div>
				</div>
				<div class="col-md-1 forum-info">
					<span class="views-number">
						${user.experiencePoint}
					</span>
					<div class="direction">
						<small>分数</small>
					</div>
				</div>
				<div class="col-md-1 forum-info">
					<span class="views-number">
						${user.roleName}
					</span>
				</div>
				<c:if test="${loginUser.roleId == 1}">
					<div class="direction">
						<button type="button" onclick="changeUser(${user.id})">操作</button>
					</div>
					<div class="direction">
						<button type="button" onclick="setDftPsw(${user.id})">重置密码</button>
					</div>
				</c:if>
				<c:if test="${!user.isActivity}">
					<div class="col-md-1 forum-info">
							<span class="views-number">
									禁用
							</span>
					</div>
				</c:if>
			</div>
		</c:forEach>

	</div>
	<!-- end #content -->

	<div class="header navbar navbar-inverse text-center lineHeight50" style="margin-top:20px; line-height:50px;">
		<span>copyright 123445</span>
	</div>
</div>
</body>
</html>
