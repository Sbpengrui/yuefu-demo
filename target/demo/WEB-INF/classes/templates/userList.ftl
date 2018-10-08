<#list userList as userDetails>
	<div title="Title${userDetails_index + 1}:${userDetails.firstName} " data-options="iconCls:'icon-man'" style="overflow:auto;padding:10px;">
		<h3 style="color:#0099FF;">${userDetails.firstName}</h3>
		<p>${userDetails.id}/${userDetails.userId}/${userDetails.firstName}/${userDetails.lastName}
			<br> ${userDetails.phone}/${userDetails.email}
		</p>
	</div>
</#list>
