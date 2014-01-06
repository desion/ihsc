<option value=""><@s.text name="select_notice" /></option> 
<#if topupRecord??&& topupRecord.notify??>
 <#list [0,1] as status>
   <#if topupRecord.notify==status >
      <#if status==0>
         <option value="${status}" selected><@s.text name="order.status.notice"/></option> 
      <#elseif status==1>
         <option value="${status}" selected><@s.text name="order.status.noticed"/></option>
       </#if>  
   <#else>
   	  <#if status==0>
         <option value="${status}" ><@s.text name="order.status.notice"/></option> 
      <#elseif status==1>
         <option value="${status}" ><@s.text name="order.status.noticed"/></option>
       </#if>  
   </#if>
 </#list>
<#else>
  <#list [0,1] as status>
   	  <#if status==0>
         <option value="${status}" ><@s.text name="order.status.notice"/></option> 
      <#elseif status==1>
         <option value="${status}" ><@s.text name="order.status.noticed"/></option>
       </#if>  
 </#list>
</#if>