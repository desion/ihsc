<option value=""><@s.text name="select_order_status" /></option> 
<#if topupRecord.status??>
 <#list [0,1,2,3] as status>
   <#if topupRecord.status==status >
      <#if status==0>
         <option value="${status}" selected><@s.text name="order.status.create"/></option> 
      <#elseif status==1>
         <option value="${status}" selected><@s.text name="order.status.underway"/></option>
      <#elseif status==2>
         <option value="${status}" selected><@s.text name="order.status.success"/></option>
       <#else>
         <option value="${status}" selected><@s.text name="order.status.fail"/></option> 
       </#if>  
   <#else>
   	  <#if status==0>
         <option value="${status}" ><@s.text name="order.status.create"/></option> 
      <#elseif status==1>
         <option value="${status}" ><@s.text name="order.status.underway"/></option>
      <#elseif status==2>
         <option value="${status}" ><@s.text name="order.status.success"/></option>
       <#else>
         <option value="${status}" ><@s.text name="order.status.fail"/></option> 
       </#if>  
   </#if>
 </#list>
<#else>
  <#list [0,1,2,3] as status>
   	  <#if status==0>
         <option value="${status}" ><@s.text name="order.status.create"/></option> 
      <#elseif status==1>
         <option value="${status}" ><@s.text name="order.status.underway"/></option>
      <#elseif status==2>
         <option value="${status}" ><@s.text name="order.status.success"/></option>
       <#else>
         <option value="${status}" ><@s.text name="order.status.fail"/></option> 
       </#if>  
 </#list>
</#if>