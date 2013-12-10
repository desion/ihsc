<option value=""><@s.text name="select_user" /></option>
<#if selectedUserName??>
    <#list userList as user>
        <#if (user.familyName + user.givenName)==selectedUserName >
	        <#if user.companyID == 0> 
	           <option value="${user.familyName!""}${user.givenName!""}" selected >${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;&nbsp;</option> 
	        <#else>
	          <option value="${user.familyName!""}${user.givenName!""}" selected >${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;${user.companyName!""}</option> 
	       </#if>      
        <#else>
          <#if user.companyID == 0> 
            <option value="${user.familyName!""}${user.givenName!""}">${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;&nbsp;</option>  
          <#else>
            <option value="${user.familyName!""}${user.givenName!""}">${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;${user.companyName!""}</option>  
          </#if>      
        </#if>
    </#list>
<#else>
     <#list userList as user>
         <#if user.companyID == 0> 
            <option value="${user.familyName!""}${user.givenName!""}">${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;&nbsp;</option> 
         <#else>
            <option value="${user.familyName!""}${user.givenName!""}">${user.familyName!""}&nbsp;${user.givenName!""}&nbsp;(${user.name!""})&nbsp;${user.companyName!""}</option> 
         </#if>   
     </#list>
</#if>