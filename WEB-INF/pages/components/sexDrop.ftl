<#if user.sex??>
 <#list [0,1] as sex>
   <#if user.sex==sex >
      <#if sex==0>
         <option value="${sex}" selected><@s.text name="male"/></option> 
       <#else>
         <option value="${sex}" selected><@s.text name="female"/></option> 
       </#if>  
   <#else>
   	  <#if sex==0>
         <option value="${sex}" ><@s.text name="male"/></option> 
       <#else>
         <option value="${sex}" ><@s.text name="female"/></option> 
       </#if>  
   </#if>
 </#list>
<#else>
  <#list [0,1] as sex> 
   	  <#if sex==0>
         <option value="${sex}" ><@s.text name="male"/></option> 
       <#else>
         <option value="${sex}" ><@s.text name="female"/></option> 
       </#if>  
  </#list>
</#if>