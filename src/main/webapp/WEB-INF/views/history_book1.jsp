<%--
  Created by IntelliJ IDEA.
  User: Liuzhen
  Date: 2017-3-2
  Time: 10:15
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
	<!-- ================== codemirror 相关 ================== -->
	<link rel="stylesheet" href="${ctx}/resources/plugin/codemirror/lib/codemirror.css">
	<script src="${ctx}/resources/plugin/codemirror/lib/codemirror.js"></script>
	<!--主题-->
	<link rel="stylesheet" href="${ctx}/resources/plugin/codemirror/theme/eclipse.css">
	<link rel="stylesheet" href="${ctx}/resources/plugin/codemirror/theme/erlang-dark.css">
	<link rel="stylesheet" href="${ctx}/resources/plugin/codemirror/theme/xq-light.css">
	<link rel="stylesheet" href="${ctx}/resources/plugin/codemirror/theme/bespin.css">
	<link rel="stylesheet" href="${ctx}/resources/plugin/codemirror/theme/night.css">
	<link rel="stylesheet" href="${ctx}/resources/plugin/codemirror/theme/seti.css">
	<link rel="stylesheet" href="${ctx}/resources/plugin/codemirror/theme/elegant.css">
	<!--语言-->
	<script src="${ctx}/resources/plugin/codemirror/mode/clike/clike.js"></script>
	<script src="${ctx}/resources/plugin/codemirror/mode/javascript/javascript.js"></script>
	<script src="${ctx}/resources/plugin/codemirror/mode/php/php.js"></script>
	<script src="${ctx}/resources/plugin/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<script src="${ctx}/resources/plugin/codemirror/mode/xml/xml.js"></script>
	<!--行号及括号匹配-->
	<script src="${ctx}/resources/plugin/codemirror/addon/selection/active-line.js"></script>
	<script src="${ctx}/resources/plugin/codemirror/addon/edit/matchbrackets.js"></script>
	<!--支持代码折叠-->
	<link rel="stylesheet" href="${ctx}/resources/plugin/codemirror/addon/fold/foldgutter.css"/>
	<script src="${ctx}/resources/plugin/codemirror/addon/fold/foldcode.js"></script>
	<script src="${ctx}/resources/plugin/codemirror/addon/fold/foldgutter.js"></script>
	<script src="${ctx}/resources/plugin/codemirror/addon/fold/brace-fold.js"></script>
	<script src="${ctx}/resources/plugin/codemirror/addon/fold/comment-fold.js"></script>

	<style type="text/css">
		.CodeMirror {border: 1px solid rgb(204, 208, 212); font-size:14px; height:400px;}
	</style>
</head>
<script type="text/javascript">
	var CodeEditor;
	$(function(){
		var CodeConfig = {
			//代码折叠
			lineWrapping:true,
			foldGutter: true,
			gutters:["CodeMirror-linenumbers", "CodeMirror-foldgutter"],
			//行号及括号匹配
			lineNumbers: true,
			matchBrackets: true,

			styleActiveLine: true,
			textWrapping: true,
			//语言及主题
			mode: "text/x-java",
			theme:"eclipse"
		};
		CodeEditor = CodeMirror.fromTextArea(document.getElementById("code"), CodeConfig);
		$(".selectItem").change(function(){
			var sign = $(this).attr("id");
			if(sign == "langs"){
				CodeEditor.setOption("mode", $(this).find("option:selected").attr("data-val"));
			}
			if(sign == "skin"){
				CodeEditor.setOption("theme", $(this).find("option:selected").attr("data-val"));
			}
		})
		resetCode();
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
	 * 写入默认代码
	 */
	function writeDftCode() {
		var code = "/* package whatever; don't place package name! */\r\n" +
			"\r\n" +
			"import java.util.*;\r\n" +
			"import java.lang.*;\r\n" +
			"import java.io.*;\r\n" +
			"\r\n" +
			"/* Name of the class has to be \"Main\" only if the class is public. */\r\n" +
			"class MinxinIde\r\n" +
			"{\r\n" +
			"	public static void main (String[] args) throws java.lang.Exception\r\n" +
			"	{\r\n" +
			"		// your code goes here\r\n" +
			"		Scanner sc = new Scanner(System.in);\r\n" +
			"		System.out.println(sc.nextLine());\r\n" +
			"	}\r\n" +
			"}";
		CodeEditor.setValue(code);
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
	 * 试运行
	 */
	function runForm() {
		$('#code').val(CodeEditor.getValue());
		$('#input').val($('#inputArea').val());
		$.ajax({
			type : "POST",
			data : $('#codeForm').serialize(),
			url : "${ctx}/library/calculate",
			async : true,
			success : function(data) {
				$('#output').val(data.output);
				$('#runTime').val(data.runTime);
				$('#errMsg').val(data.errMsg);
				return false;
			}
		});
		return false;
	}

	/**
	 * 重置
	 */
	function resetCode() {
		<c:choose>
		<c:when test="${empty userAnswer}">
			writeDftCode();
		</c:when>
		<c:otherwise>
//			CodeEditor.setValue(decodeURI($('#answer').val()));
			CodeEditor.setValue(unescape(decodeURI($('#answer').val().replace(/\+/g, "%20"))));
		</c:otherwise>
		</c:choose>
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
				<div id="tips" style="display: none;">输入样例：${subject.exampleInput}<br />输出样例：${subject.exampleOutput}</div>
			</li>
		</ul>
		<form name="codeForm" id="codeForm" method="post" action="">
			<input type="hidden" name="userAnswerId" value="${userAnswer.id}"/>
			<input type="hidden" name="subId" value="${subject.id}"/>
			<input type="hidden" id="status" name="status" value="${userAnswer.status}"/>
			<input type="hidden" id="answer" value="${userAnswer.answerCode}"/>
			<input type="hidden" name="subType" value="${subject.subType}"/>
			<input type="hidden" id="input" name="input"/>
			<h1 class="page-header">
				Coding<small> 您可以选择不同的<xxx style="text-decoration:line-through">编程语言</xxx>主题风格</small>
				<ul class="codeLangSel">
					<li>语言选择:
						<select class="form-control selectItem" id="langs">
							<option data-val="text/x-java" selected>Java</option>
							<option disabled="disabled">待研发</option>
							<%--
							<option data-val="text/x-objectivec">Object-C</option>
							<option data-val="application/x-httpd-php">PHP</option>
							<option data-val="text/x-csrc">C</option>
							<option data-val="text/x-c++src">C++</option>
							<option data-val="text/javascript">JavaScript</option>
							--%>
						</select>
					</li>
					<li>使用主题:
						<select class="form-control selectItem" id="skin">
							<option data-val="eclipse" selected>eclipse</option>
							<option data-val="erlang-dark">erlang-dark</option>
							<option data-val="xq-light">xq-light</option>
							<option data-val="bespin">bespin</option>
							<option data-val="night">night</option>
							<option data-val="seti">seti</option>
							<option data-val="elegant">elegant</option>
						</select>
					</li>
				</ul>
			</h1>
			<textarea id="code" name="code"></textarea>
			<h1 class="page-header">Input<small> 输入参数并运行您的代码</small></h1>
			<div class="testRuning">
				<textarea id="inputArea"></textarea>
			</div>
			<div class="testRunAction">
				<button type="button" class="btn btn-success m-r-5 m-b-5" onclick="runForm()">运 行</button>
				<button type="button" class="btn btn-danger m-r-5 m-b-5" onclick="resetCode()">重 置</button>
				<button type="button" class="btn btn-danger m-r-5 m-b-5" onclick="writeDftCode()">初始化</button>
			</div>
			<h1 class="page-header">Output<small> 代码输出结果</small></h1>
			<div class="testRuning">
				<textarea id="output" name="output"></textarea>
			</div>
			<h1 class="page-header">RunTime<small> 代码运行时间(ms)</small></h1>
			<div class="testRuning">
				<textarea id="runTime" name="runTime"></textarea>
			</div>
			<h1 class="page-header">ErrorMsg<small> 错误信息帮助您修正代码</small></h1>
			<div class="testRuning">
				<textarea id="errMsg" name="errMsg"></textarea>
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
