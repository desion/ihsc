<option value=""><@s.text name="select_faultStatus" /></option> 
<#if selectedFaultStatusId??>
	<#list faultStatusDropList as faultStatus>
		<#if faultStatus.id == selectedFaultStatusId >
		    <option value="${faultStatus.id!""}" selected >${faultStatus.name!""}</option>
		<#else>
		    <option value="${faultStatus.id!""}">${faultStatus.name!""}</option> 
		</#if>
	</#list>
<#else>
	 <#list faultStatusDropList as faultStatus>
		 <option value="${faultStatus.id!""}">${faultStatus.name!""}</option>
	 </#list>
</#if>