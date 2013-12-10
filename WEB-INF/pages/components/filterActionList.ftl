
 <#if  filterActionList?? && (filterActionList?size>0)>
      <#list actionList as filterAction>
         <#assign flag=0>
          <#list filterActionList as selectedActionId>
               <#if selectedActionId==filterAction.id>
                  <#assign flag=1>
                  <#break>
                </#if>
           </#list> 
           <#if flag==1>
              <tr>
                <td width="8%"><input type="checkbox" name="filterActionList" value="${filterAction.id!""}" checked/></td>
                <td width="92%"><label>${filterAction.description!""}</label></td>
              </tr>
           <#else>
             <tr>
               <td width="8%"><input type="checkbox" name="filterActionList" value="${filterAction.id!""}"  /></td>
               <td width="92%"><label>${filterAction.description!""}</label></td>
             </tr>  
           </#if>    
       </#list>
 <#else>
       <#if modifyFlag?? && modifyFlag==1>
           <#list actionList as filterAction>
                 <tr>
                     <td width="8%"><input type="checkbox" name="filterActionList" value="${filterAction.id!""}" /></td>
                     <td width="92%"><label>${filterAction.description!""}</label></td>
                 </tr>
           </#list>
       <#else>   
           <#if selectedGroupId??>
              <#list actionList as filterAction>
                    <#if filterAction.groupId??>
                        <tr>
                          <td width="8%"><input type="checkbox" name="filterActionList" value="${filterAction.id!""}" checked/></td>
                          <td width="92%"><label>${filterAction.description!""}</label></td>
                        </tr>
                    <#else>
                          <tr>
                            <td width="4%"><input type="checkbox" name="filterActionList" value="${filterAction.id!""}" /></td>
                            <td width="92%"><label>${filterAction.description!""}</label></td>
                          </tr>  
                    </#if>     
              </#list>
           <#else>
              <#list actionList as filterAction>
                  <tr>
                      <td width="8%"><input type="checkbox" name="filterActionList" value="${filterAction.id!""}" /></td>
                      <td width="92%"><label>${filterAction.description!""}</label></td>
                  </tr>
              </#list>
           </#if>
      </#if>   
</#if>  
