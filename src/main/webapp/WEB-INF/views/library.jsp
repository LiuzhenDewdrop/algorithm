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
	 * 跳转答题页面
	 * @param id	题目id
	 * @param level	题目难度
	 */
	function toAnswerBook(id, level) {
		if (${loginUser.roleId == 2 || loginUser.roleId == 3} && ${level - loginUser.userLevel > 3}) {
			alert("这道题目的等级太高了，请您先刷刷小怪练练级吧~!");
			return;
		}
		location.href = "${ctx}/library/toAnswerBook?id=" + id;
	}

	/**
	 * 前去出题
	 */
	function jump2NewSubject() {
		location.href = "${ctx}/library/toAdd";
	}
	
	/**
	 * 前去竞赛专题
	 */
	function jump2Match() {
		location.href = "${ctx}/match";
	}

	/**
	 * 去阅卷
	 */
	function tojudge(id) {
		location.href = "${ctx}/judge/toAdd?id=" + id;
	}

	/**
	 * 改变题目模式
	 * @param id
	 * @param mode	[废弃:0, 普通:1, 竞赛:2]
	 */
	function updateMode(id, mode) {
		if (!id || !mode) {
			alert("请选择正确的题目!");
			return false;
		}
		$.ajax({
			type:"POST",
			data:{"id" : id, "subMode" : mode},
			url:"${ctx}/library/updateMode",
			async: false,
			success:function(data){
				alert(data);
				location.reload();
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
		<h1 class="page-header mrt30">
			<c:choose>
				<c:when test="${level == 1}">初出茅庐</c:when>
				<c:when test="${level == 2}">渐入佳境</c:when>
				<c:when test="${level == 3}">炉火纯青</c:when>
				<c:when test="${level == 4}">登峰造极</c:when>
				<c:otherwise>藏经阁</c:otherwise>
			</c:choose>
			<small> 选择适合您的题进行解答,完成后会获得相应的分值</small>
			<c:if test="${loginUser.roleId == 1 || loginUser.roleId == 3}">
				<button type="button" style="float: right;" onclick="jump2NewSubject()">去出题</button>
			</c:if>
			<c:if test="${loginUser.roleId == 1}">
				<button type="button" style="float: right;" onclick="jump2Match()">前往竞赛专题</button>
			</c:if>
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
				<c:choose>
					<c:when test="${loginUser.roleId != 4}">
						<th class="project-status">
							分值
						</th>
					</c:when>
					<c:otherwise>
						<th class="project-status">
							用户名
						</th>
						<th class="project-status">
							答题时间
						</th>
					</c:otherwise>
				</c:choose>
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
					<c:choose>
						<c:when test="${loginUser.roleId != 4}">
							<td class="project-status">
								<span>${sub.relativeScore}</span>
							</td>
						</c:when>
						<c:otherwise>
							<th class="project-status">
								<span>${sub.userName}</span>
							</th>
							<th class="project-status">
								<span><fmt:formatDate value="${sub.createTime}" pattern="yyyy年MM月dd日HH:mm:ss"/></span>
							</th>
						</c:otherwise>
					</c:choose>
					<td class="project-status">
						<span>${sub.subTypeName}<small><c:if test="${sub.subMode == 0}">废弃题</c:if><c:if test="${sub.subMode == 2}">竞赛题</c:if></small></span>
					</td>
					<td class="project-actions">
						<c:choose>
							<c:when test="${loginUser.roleId != 4}">
								<button type="button" class="btn btn-success m-r-5" onclick="toAnswerBook(${sub.id}, ${sub.subLevel})"><i class="fa fa-cogs" ></i> 解 答</button>
								<c:if test="${loginUser.roleId == 1 || loginUser.roleId == 3}">
									<c:choose>
										<c:when test="${sub.subMode == 0}">
											<button type="button" class="btn btn-success m-r-5" onclick="updateMode(${sub.id}, 1)"><i class="fa fa-cogs" ></i> 启 用</button>
										</c:when>
										<c:when test="${sub.subMode == 1}">
											<button type="button" class="btn btn-success m-r-5" onclick="updateMode(${sub.id}, 0)"><i class="fa fa-cogs" ></i> 废 弃</button>
											<button type="button" class="btn btn-success m-r-5" onclick="updateMode(${sub.id}, 2)"><i class="fa fa-cogs" ></i> To Match</button>
										</c:when>
										<c:otherwise>
											<button type="button" class="btn btn-success m-r-5" onclick="updateMode(${sub.id}, 0)"><i class="fa fa-cogs" ></i> 废 弃</button>
											<button type="button" class="btn btn-success m-r-5" onclick="updateMode(${sub.id}, 1)"><i class="fa fa-cogs" ></i> To Normal</button>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-success m-r-5" onclick="tojudge(${sub.id})"><i class="fa fa-cogs" ></i> 阅 卷</button>
							</c:otherwise>
						</c:choose>
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
