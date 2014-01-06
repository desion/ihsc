 <#if companyIdList?? && (companyIdList?size>0)>
	   <#list companyList as company>
		    <#assign flag=0>
		    <#list companyIdList as companyId>
		 	  <#if companyId==company.id>
				<#assign flag=1>
				<#break>
			  </#if>
		    </#list>	
		    <#if flag==1>
		      <tr>
			    <td width="10%"><input type="checkbox" name="companyIdList" value="${company.id!""}" checked/></td>
				<td width="90%"><label>${company.shortName!""}</label></td>
			  </tr>
			<#else>
			    <tr>
				   <td width="10%"><input type="checkbox" name="companyIdList" value="${company.id!""}"/></td>
				   <td width="90%"><label>${company.shortName!""}</label></td>
				</tr>  
		    </#if>	 
	   </#list>
  <#else>
       <#if modifyFlag?? && modifyFlag==1>
           <#list companyList as company>  
               <tr>
                  <td width="10%"><input type="checkbox" name="companyIdList" value="${company.id!""}"/></td>
                  <td width="90%"><label>${company.shortName!""}</label></td>
               </tr>
           </#list>   
       <#else>
	       <#if messageId??&&selectedCompanyList??>
				<#list companyList as company>
				     <#assign flag=0>
				 	  <#list selectedCompanyList as selectedCompany>
				 	       <#if selectedCompany.companyId==company.id>
							  <#assign flag=1>
						      <#break>
						    </#if>
				       </#list>	
				       <#if flag==1>
				          <tr>
							<td width="10%"><input type="checkbox" name="companyIdList" value="${company.id!""}" checked/></td>
							<td width="90%"><label>${company.shortName!""}</label></td>
						  </tr>
					   <#else>
						 <tr>
						    <td width="10%"><input type="checkbox" name="companyIdList" value="${company.id!""}"/></td>
						    <td width="90%"><label>${company.shortName!""}</label></td>
						 </tr>  
					   </#if>	 
				  </#list>
			<#else>
			     <#list companyList as company>	
		            <tr>
			          <td width="10%"><input type="checkbox" name="companyIdList" value="${company.id!""}"/></td>
			          <td width="90%"><label>${company.shortName!""}</label></td>
		            </tr>
	             </#list>    
			</#if>	  
      </#if>
  </#if>    		   
	

