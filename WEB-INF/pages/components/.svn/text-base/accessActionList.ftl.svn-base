 <#if  accessActionList?? && (accessActionList?size>0)>
	  <#list actionList as accessAction>
	     <#assign flag=0>
	 	  <#list accessActionList as selectedActionId>
	 	       <#if selectedActionId==accessAction.id>
				  <#assign flag=1>
			      <#break>
			    </#if>
	       </#list>	
	       <#if flag==1>
	          <tr>
				<td width="10%"><input type="checkbox" name="accessActionList" value="${accessAction.id!""}" checked/></td>
				<td width="90%"><label>${accessAction.description!""}</label></td>
			  </tr>
		   <#else>
			 <tr>
			   <td width="10%"><input type="checkbox" name="accessActionList" value="${accessAction.id!""}"  /></td>
			   <td width="90%"><label>${accessAction.description!""}</label></td>
			 </tr>  
		   </#if>	 
	   </#list>
 <#else>
     <#if modifyFlag?? && modifyFlag==1>
	     <#list actionList as accessAction>
	         <tr>
	            <td width="10%"><input type="checkbox" name="accessActionList" value="${accessAction.id!""}" /></td>
	            <td width="90%"><label>${accessAction.description!""}</label></td>
	          </tr>
	     </#list>                
     <#else>
         <#if selectedGroupId??>
		    <#list actionList as accessAction>
				  <#if accessAction.groupId??>
				      <tr>
					    <td width="10%"><input type="checkbox" name="accessActionList" value="${accessAction.id!""}" checked/></td>
				        <td width="90%"><label>${accessAction.description!""}</label></td>
					  </tr>
				  <#else>
						<tr>
						  <td width="10%"><input type="checkbox" name="accessActionList" value="${accessAction.id!""}" /></td>
				          <td width="90%"><label>${accessAction.description!""}</label></td>
						</tr>  
				  </#if>	 
		    </#list>
	     <#else>
	        <#list actionList as accessAction>
		        <tr>
			        <td width="10%"><input type="checkbox" name="accessActionList" value="${accessAction.id!""}" /></td>
			        <td width="88%"><label>${accessAction.description!""}</label></td>
		        </tr>
	        </#list>
         </#if>
    </#if>	
</#if>	
