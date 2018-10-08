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
						//$('#content').panel('refresh', '/demo/user/list?page=' + pageNumber + '&pageSize=' + pageSize); //重新加载数据
						setTimeout(function() {
							$('#box').pagination('loaded'); //分页加载完成时刷新按钮停止
						}, 1000);
					},
					showRefresh: false

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
		dataType: "html",
		data: {
			page: pageNumber,
			pageSize: pageSize
		},
		success: function(result) {
			if(result) {
				$("#aa").empty();
				$("#aa").append(result);
				//				$.parser.parse("#content");
				$.parser.parse("#aa");
			} else {
				$.messager.alert('error', '数据获取失败');
			}
		},
		error: function() {
			$.messager.alert('error', '数据获取失败2');
		}
	});
}