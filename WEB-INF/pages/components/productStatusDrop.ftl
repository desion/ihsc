<option value=""><@s.text name="select_product_status" /></option> 
<#if product.status??>
 <#list [0,1,2,3] as status>
   <#if product.status==status >
      <#if status==0>
         <option value="${status}" selected><@s.text name="sadd"/></option> 
      <#elseif status==1>
         <option value="${status}" selected><@s.text name="slist"/></option>
      <#elseif status==2>
         <option value="${status}" selected><@s.text name="schange"/></option>
       <#else>
         <option value="${status}" selected><@s.text name="sdelist"/></option> 
       </#if>  
   <#else>
   	  <#if status==0>
         <option value="${status}" ><@s.text name="sadd"/></option> 
      <#elseif status==1>
         <option value="${status}" ><@s.text name="slist"/></option>
      <#elseif status==2>
         <option value="${status}" ><@s.text name="schange"/></option>
       <#else>
         <option value="${status}" ><@s.text name="sdelist"/></option> 
       </#if>  
   </#if>
 </#list>
<#else>
  <#list [0,1,2,3] as status>
   	  <#if status==0>
         <option value="${status}" ><@s.text name="sadd"/></option> 
      <#elseif status==1>
         <option value="${status}" ><@s.text name="slist"/></option>
      <#elseif status==2>
         <option value="${status}" ><@s.text name="schange"/></option>
       <#else>
         <option value="${status}" ><@s.text name="sdelist"/></option> 
       </#if>  
 </#list>
</#if>