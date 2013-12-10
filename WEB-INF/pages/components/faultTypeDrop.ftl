<option value=""><@s.text name="select_faultType" /></option> 
<#if selectedFaultType??>
	<#list faultTypeList as faultType>
		<#if faultType.id==selectedFaultType >
		   <option value="${faultType.id!""}" selected >${faultType.name!""}</option>
		<#else>
		  <option value="${faultType.id!""}">${faultType.name!""}</option> 
		</#if>
	</#list>
<#else>
	 <#list faultTypeList as faultType>
		 <option value="${faultType.id!""}">${faultType.name!""}</option>
	 </#list>
</#if>