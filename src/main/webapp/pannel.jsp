<div id="aa" class="easyui-accordion" style="width:300px;height:200px;">
	<div title="Title1" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
		<h3 style="color:#0099FF;">Accordion for jQuery</h3>
		<p>Accordion is a part of easyui framework for jQuery. It lets you define your accordion component on web page more easily.</p>
	</div>
	<div title="Title2" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">
		content2
	</div>
	<div title="Title3">
		content3
	</div>
</div>

<div id="aa" class="easyui-accordion" style="width:300px;height:200px;">
	<#list userList as userDetails>
		<div title="Title${userDetails_index + 1}:${userDetails.firstName} " data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
			<h3 style="color:#0099FF;">${userDetails.firstName}</h3>
			<p>${userDetails.id}/${userDetails.userId}/${userDetails.firstName}/${userDetails.lastName}
				<br> ${userDetails.phone}/${userDetails.email}
			</p>
		</div>
	</#list>
</div>