<option value=""><@s.text name="select_companyType" /></option> 
<#if selectedComTypeId??>
    <#list comTypeDropList as comType>  
        <#if comType.id==selectedComTypeId >
           <option value="${comType.id!""}" selected >${comType.name!""}</option> 
        <#else>
          <option value="${comType.id!""}">${comType.name!""}</option> 
        </#if>  
    </#list>
<#else>
     <#list comTypeDropList as comType> 
          <option value="${comType.id!""}">${comType.name!""}</option> 
     </#list>
</#if>  