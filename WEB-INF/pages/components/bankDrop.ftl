<option value=""><@s.text name="select_bank" /></option> 
<#if selectedBankId??>
    <#list bankList as bank>    
        <#if bank.id == selectedBankId >
           <option value="${bank.id!""}" bankType="${bank.cateFlag!""}" selected >${bank.name!""}</option> 
        <#else>
          <option value="${bank.id!""}" bankType="${bank.cateFlag!""}">${bank.name!""}</option> 
        </#if>  
    </#list>
<#else>
     <#list bankList as bank>    
           <option value="${bank.id!""}" bankType="${bank.cateFlag!""}">${bank.name!""}</option> 
     </#list>
</#if>  