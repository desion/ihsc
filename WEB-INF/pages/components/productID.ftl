<option value=""><@s.text name="select_product" /></option> 
<#if selectedProductId??>
    <#list productList as product>
		<#if product.id==selectedProductId >
            <option value="${product.id!""}" selected >${product.model!""}</option>
		<#else>
            <option value="${product.id!""}">${product.model!""}</option> 
		</#if>
	</#list>
<#else>
	 <#list productList as product>
		 <option value="${product.id!""}">${product.model!""}</option>
	 </#list>
</#if>