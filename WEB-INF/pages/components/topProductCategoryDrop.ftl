<option value=""><@s.text name="select_productCategory" /></option> 
<#if selectedProductCategoryName??>
    <#list productCategoryList as productCategory>
		<#if productCategory.name==selectedProductCategoryName >
		  <#if productCategory.name=='STANDARD'>
              <option value="${productCategory.name!""}" selected >${productCategory.name!""}</option>
           <#else>
              <option value="${productCategory.name!""}" selected >${productCategory.name!""}</option>
           </#if>
		<#else>
            <option value="${productCategory.name!""}">${productCategory.name!""}</option> 
		</#if>
	</#list>
<#else>
     <#list productCategoryList as productCategory>
		   <option value="${productCategory.name!""}">${productCategory.name!""}</option> 
	 </#list>
</#if>