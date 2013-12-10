<option value=""><@s.text name="select_company" /></option> 
<#if selectedComId??>
	<#list comDropList as com>	
		<#if com.id==selectedComId >
		   <option value="${com.id!""}" selected >${com.shortName!""}</option> 
		<#else>
		  <option value="${com.id!""}">${com.shortName!""}</option> 
		</#if>  
	</#list>
<#else>
	 <#list comDropList as com>	
		 <option value="${com.id!""}">${com.shortName!""}</option> 
	 </#list>
</#if>	