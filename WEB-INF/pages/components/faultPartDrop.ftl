<option value=""><@s.text name="select_faultPart" /></option> 
<#if selectedFaultPart??>
	<#list faultPartList as faultPart>
		<#if faultPart.id==selectedFaultPart >
		 <#if faultPart.faultMachineType='STANDARD'>
		     <option value="${faultPart.id!""}" selected ><@s.text name="standard"/> ${faultPart.name!""}</option>
		  <#else>
		     <option value="${faultPart.id!""}" selected >${faultPart.faultMachineType!""} ${faultPart.name!""}</option>
		  </#if>
		<#else>
		   <#if faultPart.faultMachineType='STANDARD'>
		      <option value="${faultPart.id!""}"><@s.text name="standard"/>  ${faultPart.name!""}</option> 
		   <#else>
		      <option value="${faultPart.id!""}">${faultPart.faultMachineType!""} ${faultPart.name!""}</option> 
		   </#if>
		</#if>
	</#list>
<#else>
	 <#list faultPartList as faultPart>
	    <#if faultPart.faultMachineType='STANDARD'>
		   <option value="${faultPart.id!""}"><@s.text name="standard"/>  ${faultPart.name!""}</option> 
		<#else>
           <option value="${faultPart.id!""}">${faultPart.faultMachineType!""} ${faultPart.name!""}</option> 
        </#if>
	 </#list>
</#if>