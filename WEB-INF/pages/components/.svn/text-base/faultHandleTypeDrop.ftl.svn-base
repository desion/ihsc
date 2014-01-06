<option value=""><@s.text name="select_faultHandleType" /></option> 
<#if selectedFaultHandleType??>
    <#list handleTypelist as handleType>
        <#if handleType.handleType==selectedFaultHandleType >
           <option value="${handleType.handleType!""}" selected >${handleType.handleTypeName!""}</option>
        <#else>
          <option value="${handleType.handleType!""}">${handleType.handleTypeName!""}</option> 
        </#if>
    </#list>
<#else>
     <#list handleTypelist as handleType>
         <option value="${handleType.handleType!""}">${handleType.handleTypeName!""}</option>
     </#list>
</#if>