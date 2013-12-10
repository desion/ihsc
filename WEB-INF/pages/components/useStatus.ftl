<option value=""><@s.text name="select_useStatus" /></option> 
<#if selectedStatusId??>
	<#list statusList as status>
		<#if status.id==selectedStatusId >
		   <option value="${status.id!""}" selected >${status.name!""}</option>
		<#else>
		  <option value="${status.id!""}">${status.name!""}</option> 
		</#if>
	</#list>
<#else>
	 <#list statusList as status>
		 <option value="${status.id!""}">${status.name!""}</option>
	 </#list>
</#if>