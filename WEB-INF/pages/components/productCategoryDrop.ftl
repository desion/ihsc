<option value=""><@s.text name="select_productCategory" /></option> 
<#if selectedProductCategoryId??>
    <#list productCategoryList as productCategory>
		<#if productCategory.id==selectedProductCategoryId >
            <option value="${productCategory.id!""}" selected >${productCategory.name!""}</option>
		<#else>
            <option value="${productCategory.id!""}">${productCategory.name!""}</option> 
		</#if>
	</#list>
<#else>
     <#list productCategoryList as productCategory>
		   <option value="${productCategory.id!""}">${productCategory.name!""}</option> 
	 </#list>
</#if>