<option value=""><@s.text name="select_faultMachineType" /></option> 
<#if faultPart.faultMachineType??>
 <#list ['ATM','G-ABIO','STANDARD'] as faultMachineType>
   <#if faultMachineType=="ATM" >
      <#if faultPart.faultMachineType==faultMachineType>
         <option value="${faultMachineType}" selected><@s.text name="faultPart.atm"/></option> 
       <#else>
         <option value="${faultMachineType}" ><@s.text name="faultPart.atm"/></option> 
       </#if>  
   <#elseif faultMachineType=='G-ABIO'>
   	  <#if  faultPart.faultMachineType==faultMachineType>
         <option value="${faultMachineType}" selected><@s.text name="faultPart.gabio"/></option> 
       <#else>
         <option value="${faultMachineType}" ><@s.text name="faultPart.gabio"/></option> 
       </#if> 
    <#else>  
       <#if  faultPart.faultMachineType==faultMachineType>
         <option value="${faultMachineType}" selected><@s.text name="faultPart.standard"/></option> 
       <#else>
         <option value="${faultMachineType}" ><@s.text name="faultPart.standard"/></option> 
       </#if>   
    </#if>
 </#list>
<#else>
    <option value="ATM" ><@s.text name="faultPart.atm"/></option> 
    <option value="G-ABIO" ><@s.text name="faultPart.gabio"/></option> 
    <option value="STANDARD" ><@s.text name="faultPart.standard"/></option> 
</#if>