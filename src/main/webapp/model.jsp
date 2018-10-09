<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 <link rel="icon" href="${pageContext.request.contextPath}/img/logo.ico" type="img/x-ico" />
		<title>easyui基本框架搭建</title>
		<!-- 引入EasyUI的样式文件-->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.6.2/themes/default/easyui.css" type="text/css" />
		<!-- 引入EasyUI的图标样式文件-->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.6.2/themes/icon.css" type="text/css" />

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.6.2/themes/wu.css" />
		<!-- 引入JQuery -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.6.2/jquery.min.js"></script>
		<!-- 引入EasyUI -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.6.2/jquery.easyui.min.js"></script>
		<!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.6.2/locale/easyui-lang-zh_CN.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/user.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/model.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/menu/menuTree.js"></script>

	</head>

	<body class="easyui-layout" data-options="fit:true" >
		<!--顶部-->
		<div class="wu-header" data-options="region:'north',border:false,split:true">
			<div class="wu-header-left">
				<h1>EasyUI Web Admin</h1>
			</div>
			<div class="wu-header-right">
				<p><strong class="easyui-tooltip" style="color: #000000;" title="2条未读消息">${user.userName}</strong>，欢迎您！</p>
				<p>
					<a href="#">网站首页</a>|
					<a href="#">支持论坛</a>|
					<a href="#">帮助中心</a>|
					<a href="javascript:logout()">安全退出</a>
				</p>
			</div>
		</div>
		<!--左侧菜单start-->
		<div region="west" title="导航菜单" split="true" collapsed="false" style="width: 200px;" border="false">
			<div class="easyui-panel" data-options="fit:true">
				<!--手风琴菜单-->
				<div id="menu" class="easyui-accordion" data-options="multiple:false,fit:true">
					<!--<div title="Language" style="overflow:auto;padding:10px;">
						<ul class="easyui-tree wu-side-tree">
							<li iconCls="icon-users">
								<a href="#" data-icon="icon-users" onclick="addNewTab('用户列表','/demo/index.html',0)">用户列表</a>
							</li>
							<li iconCls="icon-users">
								<a href="#" data-icon="icon-users" onclick="addNewTab('list','/demo/list.html',0)">list</a>
							</li>
							<li iconCls="icon-users">
								<a href="#" data-icon="icon-users" onclick="addNewTab('name3','xxx.xxx.action')">指定地址1</a>
							</li>
						</ul>

					</div>
					<div title="Java" style="padding:10px;">

						<p>A programming language is a formal language designed to communicate instructions to a machine, particularly a computer. Programming languages can be used to create programs that control the behavior of a machine and/or to express algorithms precisely.</p>
					</div>
					<div title="C#" style="padding:10px;">
						<p>Jae world.</p>
					</div>
					<div title="Ruby" style="padding:10px;">
						<p>Jae world.</p>
					</div>
					<div title="Fortran" style="padding:10px;">
						<p>Jae world.</p>
					</div>-->
				</div>
				<!--手风琴菜单end-->
			</div>
		</div>
		<!--中间主要区域-->
		<div id="content" data-options="region:'center'">
			<div id="mainTabs" class="easyui-tabs tabs-container" data-options="border:false,fit:true">
				<div title="用户列表" data-options="href:'/demo/index.html',closable:true,iconCls:'icon-tip',cls:'pd3'"></div>
				<div title="菜单树" data-options="href:'/demo/menu/menuTree.html',selected:true,closable:true,iconCls:'icon-arrow-nsew',cls:'pd3'"></div>
			</div>
			<div id="mm" class="easyui-menu" style="width:150px;">
				<div id="mm-refresh" iconCls="icon-refresh" onclick="reFresh()">刷新</div>
				<div id="mm-tabclose" iconCls="icon-bin-closed" onclick="closeTab()">关闭</div>
				<div id="mm-tabcloseall" onclick="closeAll()">关闭全部</div>
				<div id="mm-tabcloseother" onclick="closeOther()">关闭其他</div>
				<div class="menu-sep"></div>
				<div id="mm-tabcloseright" onclick="closeRight()">关闭右侧标签</div>
				<div id="mm-tabcloseleft" onclick="closeLeft()">关闭左侧标签</div>
				
			</div>
		</div>

		<!--页脚-->
		<div region="south" style="text-align: center;height: 50px;">
			<h3>XX公司</h3>
		</div>

	</body>

</html>