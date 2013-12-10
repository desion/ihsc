<option value=""><@s.text name="select_platform" /></option> 
<#if selectedPlatform??>
    <#list platformList as platform>    
        <#if platform.id==selectedPlatform >
           <option value="${platform.id!""}" selected >${platform.name!""}</option> 
        <#else>
          <option value="${platform.id!""}">${platform.name!""}</option> 
        </#if>  
    </#list>
<#else>
     <#list platformList as platform> 
          <option value="${platform.id!""}">${platform.name!""}</option> 
     </#list>
</#if>  