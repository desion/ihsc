<option value=""><@s.text name="select_osPermit" /></option> 
<#if selectedOsPermit??>
    <#list osPermitList as osPermit>    
        <#if osPermit.id == selectedOsPermit >
           <option value="${osPermit.id!""}" selected >${osPermit.name!""}</option> 
        <#else>
          <option value="${osPermit.id!""}">${osPermit.name!""}</option> 
        </#if>  
    </#list>
<#else>
     <#list osPermitList as osPermit> 
          <option value="${osPermit.id!""}">${osPermit.name!""}</option> 
     </#list>
</#if>  