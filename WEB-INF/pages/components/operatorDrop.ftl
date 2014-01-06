<option value=""><@s.text name="select_operator" /></option> 
<#if product??&&product.operator??>
 <#list [0,1,2] as operator>
   <#if product.operator==operator >
      <#if operator==0>
         <option value="${operator}" selected><@s.text name="chinamobile"/></option> 
      <#elseif operator==1>
         <option value="${operator}" selected><@s.text name="chinaunicom"/></option>
      <#else>
         <option value="${operator}" selected><@s.text name="telecom"/></option>
       </#if>  
   <#else>
   	  <#if operator==0>
         <option value="${operator}" ><@s.text name="chinamobile"/></option> 
      <#elseif operator==1>
         <option value="${operator}" ><@s.text name="chinaunicom"/></option>
      <#else>
         <option value="${operator}" ><@s.text name="telecom"/></option>
       </#if>  
   </#if>
 </#list>
 <#elseif topupRecord??&&topupRecord.operator??>
 <#list [0,1,2] as operator>
   <#if topupRecord.operator==operator >
      <#if operator==0>
         <option value="${operator}" selected><@s.text name="chinamobile"/></option> 
      <#elseif operator==1>
         <option value="${operator}" selected><@s.text name="chinaunicom"/></option>
      <#else>
         <option value="${operator}" selected><@s.text name="telecom"/></option>
       </#if>  
   <#else>
   	  <#if operator==0>
         <option value="${operator}" ><@s.text name="chinamobile"/></option> 
      <#elseif operator==1>
         <option value="${operator}" ><@s.text name="chinaunicom"/></option>
      <#else>
         <option value="${operator}" ><@s.text name="telecom"/></option>
       </#if>  
   </#if>
 </#list>
 <#elseif selectOperator??>
 <#list [0,1,2] as operator>
   <#if selectOperator==operator >
      <#if operator==0>
         <option value="${operator}" selected><@s.text name="chinamobile"/></option> 
      <#elseif operator==1>
         <option value="${operator}" selected><@s.text name="chinaunicom"/></option>
      <#else>
         <option value="${operator}" selected><@s.text name="telecom"/></option>
       </#if>  
   <#else>
   	  <#if operator==0>
         <option value="${operator}" ><@s.text name="chinamobile"/></option> 
      <#elseif operator==1>
         <option value="${operator}" ><@s.text name="chinaunicom"/></option>
      <#else>
         <option value="${operator}" ><@s.text name="telecom"/></option>
       </#if>  
   </#if>
 </#list>
<#else>
  <#list [0,1,2] as operator>
   	  <#if operator==0>
         <option value="${operator}" ><@s.text name="chinamobile"/></option> 
      <#elseif operator==1>
         <option value="${operator}" ><@s.text name="chinaunicom"/></option>
      <#else>
         <option value="${operator}" ><@s.text name="telecom"/></option>
      </#if>  
 </#list>
</#if>