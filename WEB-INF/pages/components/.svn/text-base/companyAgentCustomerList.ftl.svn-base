 <#if companyCustomerAgentList?? && (companyCustomerAgentList?size>0)>
	   <#list customerList as customer>
		    <#assign flag=0>
		    <#list companyCustomerAgentList as agentId>
		 	  <#if agentId==customer.id>
				<#assign flag=1>
				<#break>
			  </#if>
		    </#list>	
		    <#if flag==1>
		      <tr>
			    <td width="4%"><input type="checkbox" name="companyCustomerAgentList" value="${customer.id!""}" checked/></td>
				<td width="96%"><label>${customer.shortName!""}</label></td>
			  </tr>
			<#else>
			    <tr>
				   <td width="4%"><input type="checkbox" name="companyCustomerAgentList" value="${customer.id!""}"/></td>
				   <td width="96%"><label>${customer.shortName!""}</label></td>
				</tr>  
		    </#if>	 
	   </#list>
 <#else>
      <#if modifyFlag?? && modifyFlag==1>
          <#list customerList as customer> 
                <tr>
                  <td width="4%"><input type="checkbox" name="companyCustomerAgentList" value="${customer.id!""}"/></td>
                  <td width="96%"><label>${customer.shortName!""}</label></td>
                </tr>
           </#list>     
       <#else>	   
	       <#if agentComId??&&selectedCustomers??>
				  <#list customerList as customer>
				     <#assign flag=0>
				 	  <#list selectedCustomers as selectedCustomer>
				 	       <#if selectedCustomer.customerId==customer.id>
							  <#assign flag=1>
						      <#break>
						    </#if>
				       </#list>	
				       <#if flag==1>
				          <tr>
							<td width="4%"><input type="checkbox" name="companyCustomerAgentList" value="${customer.id!""}" checked/></td>
							<td width="96%"><label>${customer.shortName!""}</label></td>
						  </tr>
					   <#else>
						 <tr>
						    <td width="4%"><input type="checkbox" name="companyCustomerAgentList" value="${customer.id!""}"/></td>
						    <td width="96%"><label>${customer.shortName!""}</label></td>
						 </tr>  
					   </#if>	 
				  </#list>
			 <#else>
			     <#list customerList as customer>	
		            <tr>
			          <td width="4%"><input type="checkbox" name="companyCustomerAgentList" value="${customer.id!""}"/></td>
			          <td width="96%"><label>${customer.shortName!""}</label></td>
		            </tr>
	             </#list>    
			 </#if>	  
	   </#if>		   
 </#if>		

