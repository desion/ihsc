<option value=""><@s.text name="select_OS" /></option> 
<#if selectedOS??>
    <#list OSList as OS>    
        <#if OS.id==selectedOS >
           <option value="${OS.id!""}" selected >${OS.name!""}</option> 
        <#else>
          <option value="${OS.id!""}">${OS.name!""}</option> 
        </#if>  
    </#list>
<#else>
     <#list OSList as OS> 
          <option value="${OS.id!""}">${OS.name!""}</option> 
     </#list>
</#if>  