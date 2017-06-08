<%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-3-8
  Time: 18:13
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
		// 监听题目类型切换
		$("input[name= 'subType']:radio").click(function () {
			$('#ansList').empty();
			var subType = $("input[name= 'subType']:checked").val();
			$('#subType1').hide();
			$('#subType2').hide();
			$('#subType3').hide();
			$('#subType' + subType).show();
			if (subType == 1) {
				$('#ansList').append("<div>输入：<textarea name='ansList[0].ansInput'></textarea>输出：<textarea name='ansList[0].ansOutput'></textarea>占比：<input type='text' name='ansList[0].proportion' onblur='isNum(this.value)'/><button type='button' onclick='delAnswer(this)'>删除该答案</button></div>");
				$('#addBtn').show();
			} else {
				$('#ansList').append("<div>请输入" + (subType == 2 ? "答案" : "得分点") + "：<textarea name='ansList[0].ansOutput'></textarea></div>");
				$('#addBtn').hide();
			}
		});
		initSubLevel();
		initSubScore();
	});

	/**
	 * 退出
	 */
	function exit() {
		if (confirm("是否确认退出？")) {
			location.href = "${ctx}/logout"
		}
	}

	/**
	 * 初始化题目等级下拉菜单
	 */
	function initSubLevel() {
		for (var i= 0; i < 30; i ++) {
			$('#subLevel').append("<option value='" + (i + 1) + "'>" + (i + 1) + "</option>");
		}
	}

	/**
	 * 初始化题目得分下拉菜单
	 */
	function initSubScore() {
		for (var i= 0; i < 3; i ++) {
			$('#subScore').append("<option value='" + (10 - i) * 10 + "'>" + (10 - i) * 10 + "</option>");
		}
	}

	/**
	 * 添加选项
	 */
	function addOption() {
		var length = $('#input2').children().length;
		if (length >25) {
			alert("最多26个选项~！");
			return;
		}
		$('#input2').append("<div><font>选项" + String.fromCharCode(65 + length) + "：</font><input type='text'/> <button type='button' onclick='delOption(this)'>删除该选项</button></div>");
	}

	/**
	 * 删除选项
	 */
	function delOption(btn) {
		$(btn).parent().remove();
		$('#input2 > div > font').each(function (i, item) {
			item.innerHTML = "选项" + String.fromCharCode(65 + i) + "：";
		});
	}

	/**
	 * 添加答案
	 */
	function addAnswer() {
		var length = $('#ansList').children().length;
		$('#ansList').append("<div>输入：<textarea name='ansList[" + length + "].ansInput'></textarea>输出：<textarea name='ansList[" + length + "].ansOutput'></textarea>占比：<input type='text' name='ansList[" + length + "].proportion' onblur='isNum(this.value)'/><button type='button' onclick='delAnswer(this)'>删除该答案</button></div>");
	}

	/**
	 * 删除答案
	 */
	function delAnswer(btn) {
		$(btn).parent().remove();
		$('#ansList > div').each(function (i, item) {
			var arr = $(item).find('textarea');
			arr[0].name = "ansList[" + i + "].ansInput";
			arr[1].name = "ansList[" + i + "].ansOutput";
			$(item).find('input').prop("name", "ansList[" + i + "].proportion");
		});
	}
	
	/**
	 * 判断“占比”是否是合理的数字
	 */
	function isNum(p) {
		if (!/^(0\.\d{1,2}|1(.0+)?)$/.test(p)) {
			alert("要填个至多两位小数的数字，取值范围(0, 1]");
			return false;
		}
		return true;
	}

	/**
	 * 判断占比加和是否为1
	 */
	function isOne() {
		var total = 0,
			flag = false;
		$('#ansList > div > input').each(function (i, item) {
			if (isNum(item.value)) {
				total += item.value;
			} else {
				flag = true;
				return false;
			}
		});
		if (flag) {
			return false;
		}
		if (total != 1) {
			alert("各占比加和应为1");
			return false;
		}
		return true;
	}

	/**
	 * 检查答案列表
	 */
	function checkAnsList() {
		var flag = false;
		$('#ansList > div > textarea').each(function (i, item) {
			if(!item.value) {
				alert("答案不能为空");
				flag = true;
				return false;
			}
		});
		return !flag;
	}

	/**
	 * 获取json数组
	 */
	function getJsonArr() {
		var flag = false,
			input = '[';
		$('#input2 > div > input').each(function (i, item) {
			if (!item.value) {
				alert("选项不能为空");
				flag = true;
				return false;
			}
			input = input + '{"key":"' + String.fromCharCode(65 + i) + '","value":"' + item.value + '"},';
		});
		if (flag) {
			return false;
		}
		return input.substring(0, input.length - 1) + ']';
	}

	/**
	 * 提交
	 */
	function submitForm() {
		if (!$('#subLevel').val()) {
			alert("请选择题目等级");
			return;
		}
		if (!$('#subScore').val()) {
			alert("请选择题目分值");
			return;
		}
		if (!$('#subTitle').val()) {
			alert("输入题目标题");
			return;
		}
		if (!$('#subContent').val() || $('#subContent').val() == "请输入题干内容") {
			alert("输入题干内容");
			return;
		}
		var subType = $("input[name= 'subType']:checked").val();

		if (subType == 1) {
			$('#exampleInput').val($('#input1').val() ? $('#input1').val() : "无");
			$('#exampleOutput').val($('#output1').val() ? $('#output1').val() : "无");
			if (!isOne()) {
				return;
			}
		} else if (subType == 2) {
			var input = getJsonArr();
			if (!input) {
				return;
			}
			$('#exampleInput').val(input);
			$('#exampleOutput').val($('#output2').val() ? $('#output2').val() : "无");
		} else if (subType == 3) {
			$('#exampleOutput').val($('#output3').val() ? $('#output3').val() : "无");
		}
		if (!checkAnsList()) {
			return;
		}
		$.ajax({
			type:"POST",
			data:$('#newSubForm').serialize(),
			url:"${ctx}/library/add",
			async: false,
			success:function(data){
				if (data == 1) {
					alert("成功！");
				} else {
					alert(data);
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
		<h1 class="page-header mrt30">国子监<small>这里是您出题的场所</small></h1>
		<form id="newSubForm" action="" method="POST" class="margin-bottom-0">
			<input type="hidden" id="exampleInput" name="exampleInput"/>
			<input type="hidden" id="exampleOutput" name="exampleOutput"/>
			<div>Question：<small>题目</small></div>
			<div>
				题目等级：
				<select id="subLevel" name="subLevel">
					<option selected="selected" disabled="disabled" value="">请选择</option>
				</select>
			</div>
			<div>
				题目分值：
				<select id="subScore" name="subScore">
					<option selected="selected" disabled="disabled" value="">请选择</option>
				</select>
			</div>
			<div>
				题目类型：
				<input name="subType" type="radio" value="1" checked="checked"/>编程题
				<input name="subType" type="radio" value="2" />客观题
				<input name="subType" type="radio" value="3" />主观题
			</div>
			<div>
				是否为竞赛题：
				<input name="subMode" type="radio" value="1" checked="checked"/>普通题
				<input name="subMode" type="radio" value="2" />竞赛题
			</div>
			<div class="form-group m-b-20 err_tips_box">
				标题：<input type="text" name="subTitle" id="subTitle" class="form-control input-lg" placeholder="请输入题目标题" />
			</div>
			<div class="form-group m-b-20 err_tips_box">
				题干：<textarea name="subContent" id="subContent" class="form-control input-lg"
						  onfocus="if(value=='请输入题干内容'){value=''}"
						  onblur="if (value ==''){value='请输入题干内容'}" >请输入题干内容</textarea>
			</div>
			<div id="subType1">
				Input tips：
				<textarea id="input1">

				</textarea>
				<br />
				Output tips：
				<textarea id="output1">

				</textarea>
			</div>
			<div id="subType2" style="display: none;">
				<div id="input2">
					<div><font>选项A：</font><input type="text"/> <button type="button" onclick="delOption(this)">删除该选项</button></div>
				</div>
				<button type="button" onclick="addOption()">添加选项</button>
				<br />
				提示信息：
				<textarea id="output2">可能是单选，也可能是多选~</textarea>
			</div>
			<div id="subType3" style="display: none;">
				提示信息：
				<textarea id="output3"></textarea>
			</div>
			<div>Answer：<small>答案</small></div>
			<div id="ansList" name="ansList">
				<div>
					输入：<textarea name="ansList[0].ansInput"></textarea>
					输出：<textarea name="ansList[0].ansOutput"></textarea>
					占比：<input type="text" name="ansList[0].proportion" onblur="isNum(this.value)"/>
					<button type="button" onclick="delAnswer(this)">删除该答案</button>
				</div>
			</div>
			<button type="button" id="addBtn" onclick="addAnswer()">添加答案用例</button>
			<div></div>
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
