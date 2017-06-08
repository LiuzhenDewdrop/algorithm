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
//	$(function () {
//		var file = $('#photoFile');
//		file.on('change', function( e ){
//			var name = e.currentTarget.files[0].name;
//			$('#headPhoto').val(name);
//		});
//	});
	/**
	 * 退出
	 */
	function exit() {
		if (confirm("是否确认退出？")) {
			location.href = "${ctx}/logout"
		}
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
		if ($('#userName').val() == '${loginUser.userName}') {
			$('#userText').html("提示： 原始用户名").css({
				color: '#000000'
			});
			$('#userImg').hide();
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
	 * 邮箱验证
	 */
	function checkEmail() {
		if (/[a-zA-Z0-9]+([.-_][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)+/.test($('#email').val())) {
			$('#emailText').html("该邮箱可用").css({
				color: '#00ff00'
			});
			$('#emailImg').hide();
		} else {
			$('#emailText').html("请输入正确的邮箱").css({
				color: '#ff5858'
			});
			$('#emailImg').show();
		}
	}
	/**
	 * 获取图片的浏览器访问url
	 */
	function getPhotoURL(file) {
		var url = null;
		if (window.createObjectURL != undefined) {
			url = window.createObjectURL(file)
		} else if (window.URL != undefined) {
			url = window.URL.createObjectURL(file)
		} else if (window.webkitURL != undefined) {
			url = window.webkitURL.createObjectURL(file)
		}
		return url;
	}
	/**
	 * 选取图片
	 */
	function changePhoto() {
		$('#dftHead').hide();
		$('#preHead').show();
		var file = $('#photoFile')[0].files[0],
			fileUrl = getPhotoURL(file);
		$("#photo").attr("src", fileUrl);
		var filesrc = $('#photoFile').val(),
			startIndex = filesrc.lastIndexOf("\\") + 1,
			endIndex = filesrc.lastIndexOf("."),
			fileName = filesrc.substring(startIndex, endIndex);
		$('#headPhoto').val(fileName);
		$('#headPhoto').select();
	}
	/**
	 * 还原图片
	 */
	function returnPhoto() {
		$('#preHead').hide();
		$('#dftHead').show();
		$("#photo").attr("src", '${preUrl}${loginUser.photoUrl}');
		$('#headPhoto').val('${loginUser.headPhoto}');
		$('#photoFile').val(null)
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
			<h2>个人设置</h2>
			<form action="${ctx}/user/edit" method="post" enctype="multipart/form-data">
				<input type="hidden" id="photoUrl" name="photoUrl" value="${loginUser.photoUrl}"/>
				<table class="personal-table">
				<tr>
					<td><label>昵 称：</label></td>
					<td>
						<input type="text" name="userName" id="userName" value="${loginUser.userName}" placeholder="请输入用户名" onblur="checkUserName()"/>
						<p class="err_tips" ><img id="userImg" src="${ctx}/resources/img/u_loginErrorTip.png" alt="" /><span id="userText"></span></p>
					</td>
				</tr>
				<tr>
					<td><label>性 别：</label></td>
					<td>
						<select name="sex" value="${loginUser.sex}">
							<option value="">--请选择--</option>
							<c:forEach var="sex" items="${kvSex}">
							<option value="${sex.key}" <c:if test="${loginUser.sex == sex.key}">selected="selected"</c:if>>${sex.value}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>血 型：</label></td>
					<td>
						<select name="bloodGroup" value="${loginUser.bloodGroup}">
							<option value="">--请选择--</option>
							<c:forEach var="bg" items="${kvBloodGroup}">
							<option value="${bg.key}" <c:if test="${loginUser.bloodGroup == bg.key}">selected="selected"</c:if>>${bg.value}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>星 座：</label></td>
					<td>
						<select name="starSign" value="${loginUser.starSign}">
							<option value="">--请选择--</option>
							<c:forEach var="star" items="${kvStarSign}">
								<option value="${star.key}" <c:if test="${loginUser.starSign == star.key}">selected="selected"</c:if>>${star.value}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>邮 箱：</label></td>
					<td>
						<input type="text" id="email" name="email" value="${loginUser.email}" maxlength="50" onblur="checkEmail()"/>
						<p class="err_tips" ><img id="emailImg" src="${ctx}/resources/img/u_loginErrorTip.png" alt="" /><span id="emailText"></span></p>
					</td>
				</tr>
				<tr>
					<td><label>职 务：</label></td>
					<td>
						<input type="text" name="profession" value="${loginUser.profession}" maxlength="16"/>
					</td>
				</tr>
				<tr>
					<td><label>个性签名：</label></td>
					<td>
						<input type="text" name="autograph" value="${loginUser.autograph}" maxlength="16"/>
					</td>
				</tr>
				<tr>
					<td><label>个人简介：</label></td>
					<td>
						<textarea maxlength="200" name="selfIntroduction" style="width: 400px;">${loginUser.selfIntroduction}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<label id="dftHead">头像：</label>
						<label id="preHead" style="display: none;">预览：<br /><button type="button" onclick="returnPhoto()">还原头像</button></label>
					</td>
					<td>
						<img id="photo" src="<c:choose><c:when test="${empty loginUser.photoUrl}">${preUrl}${dftPreUrl}</c:when><c:otherwise>${preUrl}${loginUser.photoUrl}</c:otherwise></c:choose>"
							 alt="<c:choose><c:when test="${empty loginUser.headPhoto}">头像</c:when><c:otherwise>${loginUser.headPhoto}</c:otherwise></c:choose>">
					</td>
				</tr>
					<tr>
						<td><label></label></td>
						<td><input type="text" id="headPhoto" name="headPhoto" value="${loginUser.headPhoto}"/></td>
					</tr>
				<tr>
					<td><label></label></td>
					<td><input type="file" id="photoFile" name="photoFile" accept="image/gif,image/jpeg,image/jpg,image/png" onchange="changePhoto()"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<button type="submit" class="btn btn-success btn-block btn-lg">保存</button>
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
