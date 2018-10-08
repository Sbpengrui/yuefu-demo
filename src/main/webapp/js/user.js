/**
 * 
 */
$(function() {
	loadData();
});

function loadData() {

}

function loadUserLoginList() {
	$.ajax({
		type: "POST",
		url: "/demo/user/getUserLoginList",
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function(result) {
			$("#user_id").empty();
			$("#user_id").prepend("<option >请选择</option>"); //添加第一个option值
			if(result) {
				var list = result.list;
				for(var i = 0; i < list.length; i++) {
					$("#user_id").append("<option value='" + list[i].id + "'>" + list[i].userName + "</option>");
				}
			}
		},
		error: function(result) {
			alert("获取数据失败");
		}
	});
}

function doSearch() {
	$('#dg').datagrid('load', {
		firstName: $('#first_name').val(),
		lastName: $('#last_name').val()
	});
}

function newUser() {
	$('#dlg').window('open').window('setTitle', 'Edit User');
	$('#fm').form('clear');
	$("#user_id_div").show();
	loadUserLoginList();
	localStorage.setItem("user_id",-1);
}

function destroyUser() {
	var row = $('#dg').datagrid('getSelections');
	if(row) {
		var ids = '';
		for(var i = 0; i < row.length; i++) {
			ids += row[i].id + ',';
		}
		debugger;
		$.messager.confirm('Confirm', '是否删除用户？', function(r) {
			if(r) {
				$.post('/demo/user/delectUsers', {
					ids: ids
				}, function(result) {
					if(result.success) {
						$.messager.alert('Success', '删除用户成功！');
						$('#dg').datagrid('reload'); // reload the user data
					} else {
						$.messager.show({ // show error message
							title: 'Error',
							msg: result.success
						});
					}
				}, 'json');
			}
		});
	}
}

function editUser() {

	var row = $('#dg').datagrid('getSelected');
	if(row) {
		loadUserLoginList();
		$("#user_id_div").hide();
		$('#dlg').window('open').window('setTitle', 'Edit User');
		
		//		$('#id').val(row.id);
		$('#fm').form('load', row);
		$('#user_id').val(row.user_id);
		localStorage.setItem("user_id",row.user_id);
	} else {
		$.messager.alert('提示', '请选择一条记录！');
	}
}

//var init_table = function (table,params) {
//    //通过ajax请求生成的新的datagrid的列名
//    $.ajax({
//        url:"getUserList", //获取列名后台接口
//        type:"GET",
//        //dataType:'json',
//        //data:{table:table},
//        rownumbers: true, //行号
//        pagination: true, //分页控件
//        pageSize: 20,
//        pageList: [10, 20, 30, 50, 100, 150, 200, 300, 500],
//        success:function(data){
//            //获取表头数据成功后，使用easyUi的datagrid去生成表格
//            $('#'+table).datagrid({
//                url: "/data/search/gs/data", //获取数据后台接口
//                method:"GET",
//                contentType: "application/json",
//                columns:data,//外层ajax请求的表头json
//                queryParams:null,
//                rownumbers: true, //行号
//                pagination: false, //分页控件
//                pageSize: 20,
//                pageList: [10, 20, 30, 50, 100, 150, 200, 300, 500],
//                striped:true,
//                loadMsg:"正在努力加载数据,表格渲染中...",
//                onLoadSuccess: function (data) {
//                    console.log(data);
//                    if (data == null){
//                        //自定义页面信息加载框
//                        msgShow("error","请求数据为空!",'warning');
//                    }
//                },
//            });
//        },
//        error:function(e){
//            msgShow("error","请求数据出错!",'error');
//        }
//    });
//}

function dataValidate() {
	var user_id = $("#user_id").val();
	var first_name = $("input[name='first_name']").val();
	var last_name = $("input[name='last_name']").val();
	var phone = $("input[name='phone']").val();
	var email = $("input[name='email']").val();

	if(!$.trim(user_id)) {
		return false;
	} else if(!$.trim(first_name)) {
		return false;
	} else if(!$.trim(last_name)) {
		return false;
	} else if(!$.trim(phone)) {
		return false;
	} else if(!$.trim(email)) {
		return false;
	}
	return true;
}

function saveUser() {
	var flag = dataValidate();

	if(!flag) {
		alert("请检查输入");
		return false;
	} else {
		var id = $("#id").val();
		var user_id = localStorage.getItem("user_id");
		if(user_id == -1){
			user_id = $("#user_id").val();
		}
		var first_name = $("input[name='first_name']").val();
		var last_name = $("input[name='last_name']").val();
		var phone = $("input[name='phone']").val();
		var email = $("input[name='email']").val();

		var userDetails = {
			id: id,
			userId: user_id,
			firstName: first_name,
			lastName: last_name,
			phone: phone,
			email: email
		};
		debugger;
		$.ajax({
			type: "POST",
			url: "/demo/user/saveOrUpdateUser",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(userDetails),
			success: function(result) {
				if(result.success) {
					$.messager.alert('Success', '保存成功！');
					$('#dlg').window('close');
					$('#dg').datagrid('reload');
					$("#dg").datagrid("clearSelections");
				}
			},
			error: function(result) {}
		});
	}

}