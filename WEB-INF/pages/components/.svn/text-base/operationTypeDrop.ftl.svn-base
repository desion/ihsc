<option value=""><@s.text name="select_operationType" /></option> 
<#if selectedOperationTypeId??>
	<#list operationTypeDropList as operationType>
		<#if operationType.id == selectedOperationTypeId >
		    <option value="${operationType.id!""}" selected >${operationType.name!""}</option>
		<#else>
		    <option value="${operationType.id!""}">${operationType.name!""}</option> 
		</#if>
	</#list>
<#else>
	 <#list operationTypeDropList as operationType>
		 <option value="${operationType.id!""}">${operationType.name!""}</option>
	 </#list>
</#if>