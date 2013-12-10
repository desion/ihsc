<option value=""><@s.text name="select_instPlaceType" /></option> 
<#if selectedTypeId??>
    <#list list as inst>    
        <#if inst.instPlaceTypeId==selectedTypeId >
           <option value="${inst.instPlaceTypeId!""}" selected >${inst.instPlaceTypeName!""}</option> 
        <#else>
          <option value="${inst.instPlaceTypeId!""}">${inst.instPlaceTypeName!""}</option> 
        </#if>  
    </#list>
<#else>
     <#list list as inst> 
          <option value="${inst.instPlaceTypeId!""}">${inst.instPlaceTypeName!""}</option> 
     </#list>
</#if>