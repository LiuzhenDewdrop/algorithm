<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-3-8
  Time: 15:57
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
	$(function () {
		loadOptions();
	})
	
	/**
	 * 退出
	 */
	function exit() {
		if (confirm("是否确认退出？")) {
			location.href = "${ctx}/logout"
		}
	}

	/**
	 * 加载选项
	 */
	function loadOptions() {
		var arr = JSON.parse('${subject.exampleInput}');
		$(arr).each(function (i, item) {
			$('#options').append("<input name='check' type='checkbox' value='" + item.key + "' " + (($('#code').val() && $('#code').val().indexOf(item.key) >= 0) ? "checked='checked'" : "") + " />" + item.key + ". " + item.value + "<br />");
		});
	}

	/**
	 * 查看提示
	 */
	function tips() {
		if ($('#tipsBtn').val() == "1") {
			$('#tipsBtn').val(0);
			$('#tips').show();
			$('#tipsBtn').html("Hide Tips");
		} else {
			$('#tipsBtn').val(1);
			$('#tips').hide();
			$('#tipsBtn').html("Show Tips");
		}
	}

	/**
	 * 重置
	 */
	function resetCode() {
		$("input[name= 'check']:checkbox").each(function (i, item) {
			if ($('#code').val() && $('#code').val().indexOf(item.value) >= 0) {
				$(item).prop("checked", "checked");
			} else {
				$(item).prop("checked", false);
			}
		});
	}

	/**
	 * 初始化
	 */
	function writeDftCode() {
		$("input[name= 'check']:checkbox").prop("checked", false);
	}

	/**
	 * 获取结果
	 */
	function getResult() {
		var result = "",
			arr = $("input[name='check']:checked");
		arr.each(function (i, item) {
			result += item.value;
		});
		return result;
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
		<ul class="attached-document clearfix">
			<li>
				<div class="document-name">#${subject.id} ${subject.subTitle}<button type="button" id="tipsBtn" style="float: right;" onclick="tips()" value="1">Show Tips</button></div>
				<div class="document-file">${subject.subContent}</div>
				<div id="tips" style="display: none;">提示信息：${subject.exampleOutput}</div>
			</li>
		</ul>
		<form name="codeForm" id="codeForm" method="post" action="">
			<input type="hidden" name="userAnswerId" value="${userAnswer.id}"/>
			<input type="hidden" name="subId" value="${subject.id}"/>
			<input type="hidden" id="status" name="status" value="${userAnswer.status}"/>
			<input type="hidden" name="subType" value="${subject.subType}"/>
			<input type="hidden" id="code" name="code" value="${userAnswer.answerCode}"/>
			<div id="options"></div>
			<div class="testRunAction">
				<button type="button" class="btn btn-danger m-r-5 m-b-5" onclick="resetCode()">重 置</button>
				<button type="button" class="btn btn-danger m-r-5 m-b-5" onclick="writeDftCode()">初始化</button>
			</div>
		</form>
	</div>
	<!-- end #content -->

	<div class="header navbar navbar-inverse text-center lineHeight50" style="margin-top:20px; line-height:50px;">
		<span>copyright 123445</span>
	</div>
</div>
</body>
</html>
