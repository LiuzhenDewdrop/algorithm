<%@ page import="com.algorithm.entity.User" %>
<%@ page import="com.algorithm.util.BaseUtil" %>
<%@ page import="com.algorithm.util.SysProp" %><%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-2-23
  Time: 10:23
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
					<li class="active"><a href="${ctx}/home">首页</a></li>
					<li><a href="${ctx}/library">题库</a></li>
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
		<h2 class="content-title">精彩题库</h2>
		<!-- begin row -->
		<div class="row">
			<!-- begin col-3 -->
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon"><i class="fa fa-desktop"></i></div>
					<div class="stats-info">
						<h4>初出茅庐</h4>
						<p>${subCount.newborn}%</p>
					</div>
					<div class="stats-link">
						<a href="${ctx}/library?index=1">查看详情 <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>
			<!-- end col-3 -->
			<!-- begin col-3 -->
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-blue">
					<div class="stats-icon"><i class="fa fa-chain-broken"></i></div>
					<div class="stats-info">
						<h4>渐入佳境</h4>
						<p>${subCount.warrior}%</p>
					</div>
					<div class="stats-link">
						<a href="${ctx}/library?index=2">查看详情 <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>
			<!-- end col-3 -->
			<!-- begin col-3 -->
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-purple">
					<div class="stats-icon"><i class="fa fa-users"></i></div>
					<div class="stats-info">
						<h4>炉火纯青</h4>
						<p>${subCount.hero}%</p>
					</div>
					<div class="stats-link">
						<a href="${ctx}/library?index=3">查看详情 <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>
			<!-- end col-3 -->
			<!-- begin col-3 -->
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-red">
					<div class="stats-icon"><i class="fa fa-clock-o"></i></div>
					<div class="stats-info">
						<h4>登峰造极</h4>
						<p>${subCount.king}%</p>
					</div>
					<div class="stats-link">
						<a href="${ctx}/library?index=4">查看详情 <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>
			<!-- end col-3 -->
		</div><!-- end row -->
		<h2 class="content-title"><a href="/user">技术大咖</a></h2>
		<div class="row">

			<c:forEach var="user" items="${users}">
				<!-- begin col-4 -->
				<div class="col-md-3 col-sm-3">
					<!-- begin team -->
					<div class="team">
						<a href="${ctx}/user/toUserInfo?id=${user.id}">
							<div class="image flipInX contentAnimated" data-animation="true" data-animation-type="flipInX">
								<img src="<c:choose><c:when test="${empty user.photoUrl}">${preUrl}${dftPreUrl}</c:when><c:otherwise>${preUrl}${user.photoUrl}</c:otherwise></c:choose>"
									 alt="<c:choose><c:when test="${empty loginUser.headPhoto}">头像</c:when><c:otherwise>${loginUser.headPhoto}</c:otherwise></c:choose>">
							</div>
						</a>
							<div class="info">
								<h3 class="name">${user.userName}</h3>
								<div class="title text-theme">${user.profession}</div>
								<p>${user.selfIntroduction}</p>
							</div>
					</div>
					<!-- end team -->
				</div>
				<!-- end col-4 -->
			</c:forEach>
		</div>
	</div>
	<!-- end #content -->

	<div class="header navbar navbar-inverse text-center lineHeight50" style="margin-top:20px; line-height:50px;">
		<span>copyright 123445</span>
	</div>
</div>
</body>
</html>
