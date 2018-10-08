function deleteMenu() {

}

function editMenu() {

}

function expandAll(id) {
	$("#" + id).tree('expandAll');
}

function collapseAll(id) {
	$("#" + id).tree('collapseAll');
}

function addMenu() {
	$('#menu-modal').window('open').window('setTitle', 'Add Menu');
	$('#menu-form').form('clear');
	loadCombotree();
	$("#mm1").find("div").each(function(i){
		$(this).click(function(){
			var text = $(this).text();
			var icon = $(this).attr('iconCls');
			$("#enable").text(text);
			$("#enable").attr('iconCls',icon);
		});
	});
}

function loadCombotree() {
	$('#menu-combotree').combotree({
		url: "/demo/menu/getMenuTreeGird",
		loadFilter: function(data) {
			return data;
		},
		onBeforeExpand: function(node) {
			$('#menu-combotree').combotree("tree").tree("options").url =
				"/demo/menu/getMenuTreeGird?id=" + node.id;
		}
//		,
//		onSelect: function(node) {
//			$('#groupid').val(node.id);
//		}
	});

}