<option value=""><@s.text name="select_source" /></option> 
<#if topupRecord??&& topupRecord.source??>
 <#list [0,1,2,3] as source>
   <#if topupRecord.source==source >
      <#if source==0>
         <option value="${source}" selected><@s.text name="zhichong"/></option> 
      <#elseif source==1>
         <option value="${source}" selected><@s.text name="tmall"/></option>
      <#elseif source==2>
         <option value="${source}" selected><@s.text name="fix"/></option>
      <#elseif source==3>
         <option value="${source}" selected><@s.text name="batchfix"/></option>
       </#if>  
   <#else>
   	  <#if source==0>
         <option value="${source}" ><@s.text name="zhichong"/></option> 
      <#elseif source==1>
         <option value="${source}" ><@s.text name="tmall"/></option>
      <#elseif source==2>
         <option value="${source}" ><@s.text name="fix"/></option>
      <#elseif source==3>
         <option value="${source}" ><@s.text name="batchfix"/></option>
       </#if>  
   </#if>
 </#list>
<#else>
  <#list [0,1,2,3] as source>
   	  <#if source==0>
         <option value="${source}" ><@s.text name="zhichong"/></option> 
      <#elseif source==1>
         <option value="${source}" ><@s.text name="tmall"/></option>
      <#elseif source==2>
         <option value="${source}" ><@s.text name="fix"/></option>
      <#elseif source==3>
         <option value="${source}" ><@s.text name="batchfix"/></option>
       </#if>  
 </#list>
</#if>