<option value=""><@s.text name="select_city" /></option> 
<#if selectedCityId??>
	<#list cityDropList as city>	
		<#if city.id==selectedCityId >
		   <option value="${city.id!""}" selected >${city.name!""},${city.provinceName!""},${city.countryName!""}</option> 
		<#else>
		  <option value="${city.id!""}">${city.name!""},${city.provinceName!""},${city.countryName!""}</option> 
		</#if>  
	</#list>
<#else>
	 <#list cityDropList as city>	
		  <option value="${city.id!""}">${city.name!""},${city.provinceName!""},${city.countryName!""}</option> 
	 </#list>
</#if>	