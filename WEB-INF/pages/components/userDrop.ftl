<option value=""><@s.text name="select_user" /></option> 
<#if selectedUser??>
    <#list userList as user>
        <#if user.id==selectedUser >
            <#if user.companyID == 0> 
                <option value="${user.id!""}" selected >${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;&nbsp;</option>
            <#else>
                <option value="${user.id!""}" selected >${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;${user.companyName!""}</option>
            </#if>
        <#else>
           <#if user.companyID == 0> 
               <option value="${user.id!""}" >${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;&nbsp;</option> 
           <#else>
              <option value="${user.id!""}" >${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;${user.companyName!""}</option>            
           </#if>
        </#if>
    </#list>
<#else>
     <#list userList as user>
        <#if user.companyID == 0> 
            <option value="${user.id!""}" >${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;&nbsp;</option>
        <#else>
           <option value="${user.id!""}" >${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;${user.companyName!""}</option>      
        </#if>
     </#list>
</#if>