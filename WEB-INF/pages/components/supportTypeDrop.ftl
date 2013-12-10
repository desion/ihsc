<option value=""><@s.text name="select_supportType" /></option> 
<#if selectedSupportType??>
	<#list supportTypeList as supportType>
		<#if supportType.id==selectedSupportType >
		   <option value="${supportType.id!""}" selected >${supportType.name!""}</option>
		<#else>
		  <option value="${supportType.id!""}">${supportType.name!""}</option> 
		</#if>
	</#list>
<#else>
	 <#list supportTypeList as supportType>
		 <option value="${supportType.id!""}">${supportType.name!""}</option>
	 </#list>
</#if>