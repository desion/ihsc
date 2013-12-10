
  <#if agents??>
	  <#list customerList as customer>
	     <#assign flag=0>
	 	  <#list agents as selectedCustomerId>
	 	       <#if selectedCustomerId==customer.id>
				  <#assign flag=1>
			      <#break>
			    </#if>
	       </#list>	
	       <#if flag==1>
	          <tr>
				<td><input type="checkbox" name="agents" value="${customer.id!""}" checked/></td>
				<td><label>${customer.name!""}</label></td>
			  </tr>
		   <#else>
			 <tr>
			    <td><input type="checkbox" name="agents" value="${customer.id!""}"/></td>
			    <td><label>${customer.name!""}</label></td>
			 </tr>  
		   </#if>	 
	   </#list>
 <#else>
	 <#list customerList as customer>	
		   <tr>
			 <td><input type="checkbox" name="agents" value="${customer.id!""}"/></td>
			 <td><label>${customer.name!""}</label></td>
		   </tr>
	   </#list>
 </#if>
 <p color="red">${agentComId!-100}</p>
 
