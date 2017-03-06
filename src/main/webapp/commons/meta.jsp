<!-- 作用：定义公共meta参数、引入公共js、css等资源文件 -->
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>

<%--
校验框架文件 及 后台页面常用功能文件 导入
校验框架文件要先于JQ之前引入，它用的是Prototype框架，与JQ有冲突，如果先引入JQ，总是起不到任何作用。
而且prototype_for_validation.js要先于另外两个JS文件引入，因为在另外两个JS文件里有用到它，如果不先引入的话，另外两个JS文件就报错了(其实在测试中只有tooltips.js报错了)
--%>
<link type="text/css" rel="stylesheet" href="${root}/resources/script/rapid-validation/validation.css" />
<link type="text/css" rel="stylesheet" href="${root}/resources/script/rapid-validation/tooltips.css" />

<script type="text/javascript" src="${root}/resources/script/rapid-validation/prototype_for_validation.js"></script>
<script type="text/javascript" src="${root}/resources/script/rapid-validation/tooltips.js"></script>
<script type="text/javascript" src="${root}/resources/script/rapid-validation/validation_cn.js" charset="UTF-8"></script>

<script type="text/javascript" src="${root}/resources/script/rest.js"></script>
<script type="text/javascript" src="${root}/resources/script/application.js"></script>

<%-- JQuery引入 --%>
<script type="text/javascript" src="${root}/resources/script/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${root}/resources/script/jquery.form.js"></script>

<%-- 日期控件导入 --%>
<script type="text/javascript" src="${root}/resources/script/My97DatePicker/WdatePicker.js"></script>

<%-- 分页控件导入 --%>
<link type="text/css" rel="stylesheet" href="${root}/resources/script/simpletable/simpletable.css" />
<script type="text/javascript" src="${root}/resources/script/simpletable/simpletable.js"></script>

<%-- 弹出框控件导入 --%>
<link type="text/css" rel="stylesheet" href="${root}/resources/script/artDialog/default.css" />
<script type="text/javascript" src="${root}/resources/script/artDialog/artDialog.js"></script>

<%-- 富文本控件导入, 在需要时再引入，
要记得将/resources/script/ueditor/_examples/editor_api.js中的baseURL变量的值 修改为该控件的存放目录，
并精确到_src目录，如本项目中是：baseURL = '/resources/script/ueditor/_src/';
而在引入时，window.UEDITOR_HOME_URL的值只需要精确到控件根目录即可，如下：

<script type="text/javascript" charset="utf-8">
	window.UEDITOR_HOME_URL = "${root}/resources/script/ueditor/";  //UEDITOR_HOME_URL、config、all这三个顺序不能改变
</script>
<script type="text/javascript" charset="utf-8" src="${root}/resources/script/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/resources/script/ueditor/editor_all.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/resources/script/ueditor/_examples/editor_api.js"></script>
 --%>
