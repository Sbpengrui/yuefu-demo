$(function() {
	loadPagination();
	getData(1, 10);
});

function loadPagination() {
	$.ajax({
		type: "post",
		url: "/demo/user/listCount",
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function(result) {
			if(result) {
				$('#box').pagination({
					total: result.count, //总记录数,也就是数据库总条数
					pageSize: 10, //每页显示条数,就是每页显示多少条
					pageNumber: 1, //创建的时候显示的页数。默认值为1。
					pageList: [10, 20, 30], //每页显示多少条的选择
					onSelectPage: function(pageNumber, pageSize) { //点击分页时触发

						$('#box').pagination('loading'); //在加载分页时刷新按钮旋转
						getData(pageNumber, pageSize);
						//						$("#content").html('');
						//$('#pannel').panel('refresh', '/demo/user/list?page=' + pageNumber + '&pageSize=' + pageSize); //重新加载数据
						setTimeout(function() {
							$('#box').pagination('loaded'); //分页加载完成时刷新按钮停止
						}, 1000);
					},
					showRefresh: true

				});
			} else {
				$.messager.alert('error', '数据获取失败');
			}
		},
		error: function() {
			$.messager.alert('error', '数据获取失败1');
		}
	});
}

function getData(pageNumber, pageSize) {
	$.ajax({
		type: "post",
		url: "/demo/user/list",
		dataType: "json",
		data: {
			page: pageNumber,
			pageSize: pageSize
		},
		success: function(result) {
			if(result) {
				debugger;
				var count = result.count;
				
				var list = result.userList;
				var htmlStr = '<div id="aa" class="easyui-accordion" style="width:100%;">';
				for(var i = 0; i < list.length; i++) {
					htmlStr += '<div title="Title' + i + list[i].firstName + '" data-options="iconCls:\'icon-man\'" style="overflow:auto;padding:10px;">' +
						'<h3 style="color:#0099FF;">' + list[i].firstName + '</h3>' +
						'<p>' + list[i].id + '/' + list[i].userId + '/' + list[i].firstName + '/' + list[i].lastName +
						'<br>' + list[i].phone + '/' + list[i].email + '</p>' +
						'</div>'
				}
				htmlStr += '</div>';
				$("#pannel").empty();
				$("#pannel").append(htmlStr);
				$.parser.parse("#pannel");
				$('#box').pagination('refresh', {
					total: count
				});
				//				$.parser.parse("#aa");
			} else {
				$.messager.alert('error', '数据获取失败');
			}
		},
		error: function() {
			$.messager.alert('error', '数据获取失败2');
		}
	});
}