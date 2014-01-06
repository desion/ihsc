<option value=""><@s.text name="select_accu_type" /></option> 
<#if selectedAccuType??>
 <#list [0,1,2,3] as type>
   <#if selectedAccuType==type >
      <#if type==0>
         <option value="${type}" selected><@s.text name="accumulate.type.add"/></option> 
      <#elseif type==1>
         <option value="${type}" selected><@s.text name="accumulate.type.minus"/></option>
      <#elseif type==2>
         <option value="${type}" selected><@s.text name="accumulate.type.strike"/></option>
       <#else>
         <option value="${type}" selected><@s.text name="accumulate.type.other"/></option> 
       </#if>  
   <#else>
   	  <#if type==0>
         <option value="${type}" ><@s.text name="accumulate.type.add"/></option> 
      <#elseif status==1>
         <option value="${type}" ><@s.text name="accumulate.type.minus"/></option>
      <#elseif status==2>
         <option value="${type}" ><@s.text name="accumulate.type.strike"/></option>
       <#else>
         <option value="${type}" ><@s.text name="accumulate.type.other"/></option> 
       </#if>  
   </#if>
 </#list>
<#else>
  <#list [0,1,2,3] as type>
   	  <#if type==0>
         <option value="${type}" ><@s.text name="accumulate.type.add"/></option> 
      <#elseif type==1>
         <option value="${type}" ><@s.text name="accumulate.type.minus"/></option>
      <#elseif type==2>
         <option value="${type}" ><@s.text name="accumulate.type.strike"/></option>
       <#else>
         <option value="${type}" ><@s.text name="accumulate.type.other"/></option> 
       </#if>  
 </#list>
</#if>