<option value=""><@s.text name="select_faultPartType" /></option> 
<#if selectedFaultPartType??>
	<#list faultPartTypeList as faultPartType>
		<#if faultPartType.id==selectedFaultPartType >
		   <option value="${faultPartType.id!""}" selected >${faultPartType.name!""}</option>
		<#else>
		  <option value="${faultPartType.id!""}">${faultPartType.name!""}</option> 
		</#if>
	</#list>
<#else>
	 <#list faultPartTypeList as faultPartType>
		 <option value="${faultPartType.id!""}">${faultPartType.name!""}</option>
	 </#list>
</#if>