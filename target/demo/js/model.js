$(function() {

	localStorage.setItem('用户列表', '/demo/index.html');
	localStorage.setItem('菜单树', '/demo/menu/menuTree.html');

	$("#content").bind('contextmenu', function(e) {
		e.preventDefault();
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
	});

	$.ajax({
		type: "post",
		url: "/demo/menu/getMenuList",
		dataType: "json",
		data: {
			level: '1'
		},
		success: function(result) {

			if(result) {
				$.each(result, function(i, n) {

					if(i == 5) {
						$('#menu').accordion('add', {
							title: n.menuName,
							iconCls: n.icon,
							selected: true,
							content: '<div  name="' + n.menuName + '" style="overflow:auto;padding:10px;"><ul id="top-' + n.id + '" class="easyui-tree wu-side-tree"></ul>'
						});
					} else {
						$('#menu').accordion('add', {
							title: n.menuName,
							iconCls: n.icon,
							selected: false,
							content: '<div  name="' + n.menuName + '" style="overflow:auto;padding:10px;"><ul id="top-' + n.id + '" class="easyui-tree wu-side-tree"></ul>'
						});
					}
				});
				loadSubMenu();
			} else {
				$.messager.alert('error', '数据请求失败！');
			}
		},
		error: function() {
			$.messager.alert('error', '数据请求失败！');
		}
	});

});

function loadSubMenu() {
	$("ul[id^='top-']").each(function(i) {
		var ulId = $(this).attr('id');
		var menuId = ulId.replace('top-', '');
		$.ajax({
			type: "post",
			url: "/demo/menu/getMenuList",
			dataType: "json",
			data: {
				level: '2',
				parentId: menuId
			},
			success: function(result) {
				if(result) {
					var htmlStr = "";
					$.each(result, function(i, n) {
						//						htmlStr += '<li iconCls="icon-users"> <a href = "#" data-icon ="' + n.icon 
						//						+ '" onclick="addNewTab(\'' + n.menuName + '\',\'' + n.url + '\',' + n.remote +')">' + n.menuName + '</a></li>';

						htmlStr += '<li><div id="_easyui_tree_' + n.id + '" class="tree-node">' +
							'<span class="tree-indent"></span>' +
							'<span class="tree-icon tree-file ' + n.icon + '"></span><span class="tree-title">' +
							'<a href="#" onclick="addNewTab(\'' + n.menuName + '\',\'' + n.url + '\',' + n.remote + ')">' +
							n.menuName + '</a></span></div>';
					});
					$("ul#" + ulId).append(htmlStr);

				} else {
					$.messager.alert('error', '数据请求失败！');
				}
			},
			error: function() {
				$.messager.alert('error', '数据请求失败！');
			}
		});
	});
}

/*function showMenu(event) {
	debugger;
	event.preventDefault();
	$('#mm').menu('show', {
		left: event.pageX,
		top: event.pageY
	});
}*/

/**
 * tabname 添加菜单选项
 * Param title 名称
 * Param href 链接
 * Param iframe 链接跳转方式（true为iframe，false为href）
 */
function addNewTab(tabname, url, iframe) {
	//创建一个新的窗口，在mainlayout上  
	if(!$("#mainTabs").tabs('getTab', tabname)) {
		localStorage.setItem(tabname, url);
		if(iframe == 0) {
			$("#mainTabs").tabs('add', {
				title: tabname,
				selected: true,
				closable: true,
				content: "<iframe id='xxx' src='" + url + "' style='width:100%;height:100%'  frameborder='no' border='0' marginwidth='0' marginheight='0' scrolling='yes' />"
			});
		} else {
			$("#mainTabs").tabs('add', {
				title: tabname,
				href: url,
				closable: true,
				selected: true
			});
		}

	} else {
		$('#mainTabs').tabs('select', tabname);
	}
}

function reFresh() {
	var currTab = $("#mainTabs").tabs('getSelected');
	var tabTitle = $("#mainTabs").tabs('getSelected').panel('options').title;
	var url = localStorage.getItem(tabTitle);

	currTab.panel('open').panel('refresh', url);
	/*	$("#mainTabs").tabs('update', {
			tab: currTab,
			options: {
				title: tabTitle,
				selected: true,
				closable: true,
				content: "<iframe id='xxx' src='" + url + "' style='width:100%;height:100%'  frameborder='no' border='0' marginwidth='0' marginheight='0' scrolling='yes' />"
			}

		});*/
}

function closeTab() {
	var tabTitle = $("#mainTabs").tabs('getSelected').panel('options').title;
	localStorage.removeItem(tabTitle);
	$('#mainTabs').tabs('close', tabTitle);
}

function closeOther() {
	var tiles = new Array();
	var tablist = $('#mainTabs').tabs('tabs');
	var len = tablist.length;
	var currtab = $('#mainTabs').tabs('getSelected');
	var index = $('#mainTabs').tabs('getTabIndex', currtab);
	var tabTitle = $("#mainTabs").tabs('getSelected').panel('options').title;
	if(len > 0) {
		for(var j = 0; j < len; j++) {
			var title = tablist[j].panel('options').title;
			tiles.push(title);
		}
		for(var i = tablist.length - 1; i > index; i--) {
			$('#mainTabs').tabs('close', tiles[i]);
			localStorage.removeItem(tiles[i]);
		}
		var num = index - 1;
		if(num < 0) {
			return;
		} else {
			for(var i = num; i >= 0; i--) {
				$('#mainTabs').tabs('close', tiles[i]);
				localStorage.removeItem(tiles[i]);
			}
			$("#mainTabs").tabs("select", tabTitle);
		}
	}
}

function closeAll() {
	var tiles = new Array();
	var tabs = $('#mainTabs').tabs('tabs');
	var len = tabs.length;
	if(len > 0) {
		for(var j = 0; j < len; j++) {
			var a = tabs[j].panel('options').title;
			tiles.push(a);
		}
		for(var i = 0; i < tiles.length; i++) {
			$('#mainTabs').tabs('close', tiles[i]);
			localStorage.removeItem(tiles[i]);
		}
	}
}

function closeRight() {
	var tiles = new Array();
	var tablist = $('#mainTabs').tabs('tabs');
	var len = tablist.length;
	var currtab = $('#mainTabs').tabs('getSelected');
	var index = $('#mainTabs').tabs('getTabIndex', currtab);
	var tabTitle = $("#mainTabs").tabs('getSelected').panel('options').title;
	if(len > 0) {
		for(var j = 0; j < len; j++) {
			var title = tablist[j].panel('options').title;
			tiles.push(title);
		}
		for(var i = tablist.length - 1; i > index; i--) {
			$('#mainTabs').tabs('close', tiles[i]);
			localStorage.removeItem(tiles[i]);
		}
	}
}

function closeLeft() {
	var tiles = new Array();
	var tablist = $('#mainTabs').tabs('tabs');
	var len = tablist.length;
	var currtab = $('#mainTabs').tabs('getSelected');
	var index = $('#mainTabs').tabs('getTabIndex', currtab);
	var tabTitle = $("#mainTabs").tabs('getSelected').panel('options').title;
	if(len > 0) {
		for(var j = 0; j < len; j++) {
			var title = tablist[j].panel('options').title;
			tiles.push(title);
		}
		var num = index - 1;
		if(num < 0) {
			return;
		} else {
			for(var i = num; i >= 0; i--) {
				$('#mainTabs').tabs('close', tiles[i]);
				localStorage.removeItem(tiles[i]);
			}
			$("#mainTabs").tabs("select", tabTitle);
		}
	}
}

function logout() {

	$.ajax({
		type: "post",
		url: "/demo/user/logout",
		dataType: "json",
		success: function(result) {
			if(result.HttpCode == 'OK') {
				alert("退出成功");
				window.location.href = "/demo/login.jsp";
			}
		},
		error: function() {
			$.messager.alert('error', '数据请求失败！');
		}
	});
}