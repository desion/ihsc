<option value=""><@s.text name="select_group" /></option> 
<#if selectedGroupId??>
	<#list groupDropList as gp>	
		<#if gp.id==selectedGroupId >
		   <option value="${gp.id!""}" selected >${gp.name!""}</option> 
		<#else>
		  <option value="${gp.id!""}">${gp.name!""}</option> 
		</#if>  
	</#list>
<#else>
	 <#list groupDropList as gp>	
		  <option value="${gp.id!""}">${gp.name!""}</option> 
	</#list>
</#if>	