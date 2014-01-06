<option value=""><@s.text name="select_province" /></option> 
<#if selectedProvinceId??>
    <#list provinceDropList as province>  
        <#if province.id==selectedProvinceId >
            <option value="${province.id!""}" selected>${province.name!""}</option> 
        <#else>
           <option value="${province.id!""}" >${province.name!""}</option> 
        </#if>  
    </#list>
<#else>
     <#list provinceDropList as province>  
         <option value="${province.id!""}" >${province.name!""}</option> 
     </#list> 
</#if>  