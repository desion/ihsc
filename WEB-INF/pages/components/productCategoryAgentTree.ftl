 <#if productCategoryAgentList?? && (productCategoryAgentList?size>0)>
	  <#list levelOneProductCateList as levelOneProductCatetory>
		       <#assign flag=0>
		       <#list  productCategoryAgentList as pageSelectProCate>
		          <#if pageSelectProCate==levelOneProductCatetory.id>
	                 <#assign flag=1>
	                    <#break>
	               </#if>
		       </#list>      
	          <tr>
				<td>
				  <#if flag==1>
				    <input type="checkbox" selfId="${levelOneProductCatetory.id!""}" name="productCategoryAgentList" value="${levelOneProductCatetory.id!""}"  parentId="0"  checked/>
				  <#else>
				    <input type="checkbox" selfId="${levelOneProductCatetory.id!""}" name="productCategoryAgentList" value="${levelOneProductCatetory.id!""}"  parentId="0" />
				  </#if>
				  <label><@s.text name="arrow_label" /></label> 
				  <label>${levelOneProductCatetory.name!""}</label></td>
			  </tr>
			  <#if levelOneProductCatetory.childCategoryList??>
				      <#list levelOneProductCatetory.childCategoryList as levelTwoProductCatetory>
				          <#assign flag=0>
	                        <#list  productCategoryAgentList as pageSelectProCate>
	                        <#if pageSelectProCate==levelTwoProductCatetory.id>
	                            <#assign flag=1>
	                            <#break>
	                        </#if>
	                     </#list>     
			           <tr>
                         <td>
                           &nbsp;&nbsp;&nbsp;&nbsp;
                            <#if flag==1>
                                 <input type="checkbox" selfId="${levelTwoProductCatetory.id!""}" name="productCategoryAgentList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}"  checked/>
                            <#else>
                               <input type="checkbox" selfId="${levelTwoProductCatetory.id!""}" name="productCategoryAgentList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}" />
                            </#if>
                           <label><@s.text name="arrow_label" /></label> 
                           <label>${levelTwoProductCatetory.name!""}</label>
                         </td>
                       </tr>
                       <#if levelTwoProductCatetory.childCategoryList??>
                         <#list levelTwoProductCatetory.childCategoryList as levelThreeProductCatetory>
                            <#assign flag=0>
                            <#list  productCategoryAgentList as pageSelectProCate>
                              <#if pageSelectProCate==levelThreeProductCatetory.id>
                                <#assign flag=1>
                                <#break>
                              </#if>
                         </#list>                              
                           <tr>
                              <td>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <#if flag==1>
                                   <input type="checkbox" selfId="${levelThreeProductCatetory.id!""}" name="productCategoryAgentList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}"  checked/>
                                <#else>
                                  <input type="checkbox" selfId="${levelThreeProductCatetory.id!""}" name="productCategoryAgentList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}" />
                                </#if>
                               <label><@s.text name="arrow_label" /></label>   
                               <label>${levelThreeProductCatetory.name!""}</label>
                             </td>
                           </tr>
                         </#list>  
                       </#if>
			       </#list>
			  </#if>
	    </#list>
  <#else>  
     <#if modifyFlag?? && modifyFlag==1>
	     <#list levelOneProductCateList as levelOneProductCatetory>
	              <tr>
	                <td>
	                <input type="checkbox" selfId="${levelOneProductCatetory.id!""}" name="productCategoryAgentList" value="${levelOneProductCatetory.id!""}"  parentId="0" />
	                 <label><@s.text name="arrow_label" /></label> 
	                <label>${levelOneProductCatetory.name!""}</label></td>
	              </tr>
	              <#if levelOneProductCatetory.childCategoryList??>
	                   <#list levelOneProductCatetory.childCategoryList as levelTwoProductCatetory>
	                       <tr>
	                         <td>
	                           &nbsp;&nbsp;&nbsp;&nbsp;
	                           <input type="checkbox" selfId="${levelTwoProductCatetory.id!""}" name="productCategoryAgentList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}" />
	                           <label><@s.text name="arrow_label" /></label> 
	                           <label>${levelTwoProductCatetory.name!""}</label>
	                         </td>
	                       </tr>
	                       <#if levelTwoProductCatetory.childCategoryList??>
	                         <#list levelTwoProductCatetory.childCategoryList as levelThreeProductCatetory>
	                           <tr>
	                              <td>
	                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                               <input type="checkbox" selfId="${levelThreeProductCatetory.id!""}" name="productCategoryAgentList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}" />
	                               <label><@s.text name="arrow_label" /></label>   
	                               <label>${levelThreeProductCatetory.name!""}</label>
	                             </td>
	                           </tr>
	                         </#list>  
	                       </#if>
	                   </#list>
	              </#if>
	      </#list>     
     <#else>
		    <#if agentComId??&&companyAgentProductCateList??>
		        <#list levelOneProductCateList as levelOneProductCatetory>
	               <#assign flag=0>
	               <#list  companyAgentProductCateList as dbSelectProCate>
	                  <#if dbSelectProCate==levelOneProductCatetory.id>
	                     <#assign flag=1>
	                        <#break>
	                   </#if>
	               </#list>      
	              <tr>
	                <td>
	                  <#if flag==1>
	                    <input type="checkbox" selfId="${levelOneProductCatetory.id!""}" name="productCategoryAgentList" value="${levelOneProductCatetory.id!""}"  parentId="0"  checked/>
	                  <#else>
	                    <input type="checkbox" selfId="${levelOneProductCatetory.id!""}"  name="productCategoryAgentList" value="${levelOneProductCatetory.id!""}"  parentId="0" />
	                  </#if>
	                  <label><@s.text name="arrow_label" /></label> 
	                  <label>${levelOneProductCatetory.name!""}</label></td>
	              </tr>
	              <#if levelOneProductCatetory.childCategoryList??>
	                      <#list levelOneProductCatetory.childCategoryList as levelTwoProductCatetory>
	                          <#assign flag=0>
	                            <#list  companyAgentProductCateList as dbSelectProCate>
	                            <#if dbSelectProCate==levelTwoProductCatetory.id>
	                                <#assign flag=1>
	                                <#break>
	                            </#if>
	                         </#list>     
	                       <tr>
	                         <td>
	                           &nbsp;&nbsp;&nbsp;&nbsp;
	                            <#if flag==1>
	                                 <input type="checkbox" selfId="${levelTwoProductCatetory.id!""}" name="productCategoryAgentList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}"  checked/>
	                            <#else>
	                               <input type="checkbox" selfId="${levelTwoProductCatetory.id!""}" name="productCategoryAgentList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}" />
	                            </#if>
	                          
	                           <label><@s.text name="arrow_label" /></label> 
	                           <label>${levelTwoProductCatetory.name!""}</label>
	                         </td>
	                       </tr>
	                       <#if levelTwoProductCatetory.childCategoryList??>
	                         <#list levelTwoProductCatetory.childCategoryList as levelThreeProductCatetory>
	                            <#assign flag=0>
	                            <#list  companyAgentProductCateList as dbSelectProCate>
	                              <#if dbSelectProCate==levelThreeProductCatetory.id>
	                                <#assign flag=1>
	                                <#break>
	                              </#if>
	                         </#list>                              
	                           <tr>
	                              <td>
	                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                <#if flag==1>
	                                   <input type="checkbox" selfId="${levelThreeProductCatetory.id!""}" name="productCategoryAgentList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}"  checked/>
	                                <#else>
	                                  <input type="checkbox" selfId="${levelThreeProductCatetory.id!""}" name="productCategoryAgentList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}" />
	                                </#if>
	                              
	                               <label><@s.text name="arrow_label" /></label>   
	                               <label>${levelThreeProductCatetory.name!""}</label>
	                             </td>
	                           </tr>
	                         </#list>  
	                       </#if>
	                   </#list>
	              </#if>
	           </#list>
		    <#else>
		        <#list levelOneProductCateList as levelOneProductCatetory>
	              <tr>
	                <td>
	                <input type="checkbox"  selfId="${levelOneProductCatetory.id!""}" name="productCategoryAgentList" value="${levelOneProductCatetory.id!""}"  parentId="0" />
	                 <label><@s.text name="arrow_label" /></label> 
	                <label>${levelOneProductCatetory.name!""}</label></td>
	              </tr>
	              <#if levelOneProductCatetory.childCategoryList??>
	                   <#list levelOneProductCatetory.childCategoryList as levelTwoProductCatetory>
	                       <tr>
	                         <td>
	                           &nbsp;&nbsp;&nbsp;&nbsp;
	                           <input type="checkbox" selfId="${levelTwoProductCatetory.id!""}" name="productCategoryAgentList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}" />
	                           <label><@s.text name="arrow_label" /></label> 
	                           <label>${levelTwoProductCatetory.name!""}</label>
	                         </td>
	                       </tr>
	                       <#if levelTwoProductCatetory.childCategoryList??>
	                         <#list levelTwoProductCatetory.childCategoryList as levelThreeProductCatetory>
	                           <tr>
	                              <td>
	                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                               <input type="checkbox" selfId="${levelThreeProductCatetory.id!""}" name="productCategoryAgentList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}" />
	                               <label><@s.text name="arrow_label" /></label>   
	                               <label>${levelThreeProductCatetory.name!""}</label>
	                             </td>
	                           </tr>
	                         </#list>  
	                       </#if>
	                   </#list>
	              </#if>
	            </#list>
		    </#if>
	 </#if>
</#if>	   

