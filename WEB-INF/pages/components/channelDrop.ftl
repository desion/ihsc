<option value=""><@s.text name="select_channel" /></option> 
<#if selectedChannelId??>
    <#list channelDropList as channel>  
        <#if channel.id==selectedChannelId>
            <option value="${channel.id!""}" selected>${channel.name!""}</option> 
        <#else>
           <option value="${channel.id!""}" >${channel.name!""}</option> 
        </#if>  
    </#list>
<#else>
     <#list provinceDropList as province>  
         <option value="${channel.id!""}" >${channel.name!""}</option> 
     </#list> 
</#if>  