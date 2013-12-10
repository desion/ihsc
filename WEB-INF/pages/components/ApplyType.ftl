<option value=""><@s.text name="select_applyType" /></option> 
<#if selectedapplyType??>
    <#list list as instApply>    
        <#if instApply.type==selectedapplyType >
           <option value="${instApply.type!""}" selected >${instApply.typeName!""}</option> 
        <#else>
          <option value="${instApply.type!""}">${instApply.typeName!""}</option> 
        </#if>  
    </#list>
<#else>
     <#list list as instApply> 
          <option value="${instApply.type!""}">${instApply.typeName!""}</option> 
     </#list>
</#if>  