<option value=""><@s.text name="select_isState" /></option> 
<#if selectedIsState??>
	<#list isStateList as isState>
		<#if isState.id==selectedIsState >
		   <option value="${isState.id!""}" selected >${isState.name!""}</option>
		<#else>
		  <option value="${isState.id!""}">${isState.name!""}</option> 
		</#if>
	</#list>
<#else>
	 <#list isStateList as isState>
		 <option value="${isState.id!""}">${isState.name!""}</option>
	 </#list>
</#if>