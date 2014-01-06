<#if company?? && company.comTypeList?? && (company.comTypeList?size>0) >
    <#list comTypeDropList as comType>  
      <#assign flag=0>
          <#list company.comTypeList as comTypeId>
             <#if comTypeId==comType.id>
                <#assign flag=1>
                <#break>
              </#if>
          </#list>    
        <#if flag==1 >
           <input type="checkbox" name="company.comTypeList" value="${comType.id!""}" checked>${comType.name!""}
        <#else>
           <input type="checkbox" name="company.comTypeList" value="${comType.id!""}">${comType.name!""}
        </#if>  
    </#list>
<#else>    
    <#list comTypeDropList as comType> 
           <input type="checkbox" name="company.comTypeList" value="${comType.id!""}">${comType.name!""}
    </#list>         
</#if>