<option value=""><@s.text name="select_country" /></option> 
<#if selectedCountryId??>
	<#list countryDropList as country>	
		<#if country.id==selectedCountryId >
		   <option value="${country.id!""}" selected >${country.shortName!""}</option> 
		<#else>
		  <option value="${country.id!""}">${country.shortName!""}</option> 
		</#if>  
	</#list>
<#else>
	<#list countryDropList as country>			
		  <option value="${country.id!""}">${country.shortName!""}</option> 
	</#list>
</#if>	