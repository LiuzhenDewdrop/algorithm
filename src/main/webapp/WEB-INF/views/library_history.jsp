<%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-3-1
  Time: 10:02
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
	 * 跳转答题历史页面
	 * @param id	题目id
	 */
	function toHistoryBook(id) {
		location.href = "${ctx}/library/toHistoryBook?id=" + id;
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
					<li class="active"><a href="${ctx}/library/history">答题历史</a></li>
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
		<h1 class="page-header mrt30">
			光辉史
			<small> 记录您答过的题，点击以查看</small>
		</h1>
		<table class="table table-hover">
			<thead>
			<tr style="background:#eff4f8;">
				<th class="project-status">
					等级
				</th>
				<th class="project-title">
					题目描述
				</th>
				<th class="project-status">
					得分
				</th>
				<th class="project-status">
					答题时间
				</th>
				<th class="project-status">
					类型
				</th>
				<th class="project-actions">
					操作
				</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="sub" items="${subList}">
				<tr>
					<td class="project-status">
						<span>${sub.subLevel}</span>
					</td>
					<td class="project-title">
						<b>${sub.subTitle}</b>
						<br />
						<small>${sub.subContent}</small>
					</td>
					<td class="project-status">
						<span>${sub.relativeScore}</span>
					</td>
					<th class="project-status">
						<span><fmt:formatDate value="${sub.createTime}" pattern="yyyy年MM月dd日HH:mm:ss"/></span>
					</th>
					<td class="project-status">
						<span>${sub.subTypeName}<small><c:if test="${sub.subMode == 0}">废弃题</c:if><c:if test="${sub.subMode == 2}">竞赛题</c:if></small></span>
					</td>
					<td class="project-actions">
						<button type="button" class="btn btn-success m-r-5" onclick="toHistoryBook(${sub.id})"><i class="fa fa-cogs" ></i> 查 看</button>
					<%--<c:if test="${sub.subLevel}-${loginUser.userLevel}>3">disabled="disabled"</c:if>--%>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- end #content -->
	<div class="header navbar navbar-inverse text-center lineHeight50" style="margin-top:20px; line-height:50px;">
		<span>copyright 123445</span>
	</div>
</div>
</body>
</html>