<option value=""><@s.text name="select_purpose" /></option> 
<#if selectedPurposeId??>
	<#list purposeList as purpose>
		<#if purpose.id==selectedPurposeId >
		   <option value="${purpose.id!""}" selected >${purpose.name!""}</option>
		<#else>
		  <option value="${purpose.id!""}">${purpose.name!""}</option> 
		</#if>
	</#list>
<#else>
	 <#list purposeList as purpose>
		 <option value="${purpose.id!""}">${purpose.name!""}</option>
	 </#list>
</#if>