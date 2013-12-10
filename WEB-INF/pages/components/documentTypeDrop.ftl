<option value=""><@s.text name="select_documentType" /></option> 
<#if selectedDocumentTypeId??>
	<#list docTypeDropList as documentType>	
		<#if documentType.id==selectedDocumentTypeId >
		   <option value="${documentType.id!""}" selected >${documentType.name!""}</option> 
		<#else>
		  <option value="${documentType.id!""}">${documentType.name!""}</option> 
		</#if>  
	</#list>
<#else>
	 <#list docTypeDropList as documentType>	
		  <option value="${documentType.id!""}">${documentType.name!""}</option> 
	 </#list>
</#if>	