 <#if (interfaceDocProCateList?? && (interfaceDocProCateList?size>0)) 
    || (interfaceDocProductList?? && (interfaceDocProductList?size>0)) >
	  <#list levelOneProductCateList as levelOneProductCatetory>  
          <tr>
			  <td>
			     <#if interfaceDocProCateList.contains(levelOneProductCatetory.id)>
			         <input type="checkbox" selfId="${levelOneProductCatetory.id!""}" level="1" name="interfaceDocProCateList" value="${levelOneProductCatetory.id!""}"  parentId="0"  checked/>
			     <#else>
			         <input type="checkbox" selfId="${levelOneProductCatetory.id!""}" level="1"  name="interfaceDocProCateList" value="${levelOneProductCatetory.id!""}"  parentId="0" />
			     </#if>
			     <label><@s.text name="arrow_label" /></label> 
			     <label>${levelOneProductCatetory.name!""}</label></td>
		  </tr>
		  <#if levelOneProductCatetory.childCategoryList??>
		       <#list levelOneProductCatetory.childCategoryList as levelTwoProductCatetory>  
	              <tr>
                     <td>
	                      &nbsp;&nbsp;&nbsp;&nbsp;
	                      <#if interfaceDocProCateList.contains(levelTwoProductCatetory.id)>
	                          <input type="checkbox" selfId="${levelTwoProductCatetory.id!""}" level="2"  name="interfaceDocProCateList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}"  checked/>
	                      <#else>
	                          <input type="checkbox" selfId="${levelTwoProductCatetory.id!""}" level="2"  name="interfaceDocProCateList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}" />
	                      </#if>
	                      <label><@s.text name="arrow_label" /></label> 
	                      <label>${levelTwoProductCatetory.name!""}</label>
                     </td>
               </tr>
               <#if levelTwoProductCatetory.childCategoryList??>
                    <#list levelTwoProductCatetory.childCategoryList as levelThreeProductCatetory>                   
                        <tr>
                            <td>
	                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                             <#if interfaceDocProCateList.contains(levelThreeProductCatetory.id)>
	                                 <input type="checkbox" selfId="${levelThreeProductCatetory.id!""}" level="3" name="interfaceDocProCateList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}"  checked/>
	                             <#else>
	                                <input type="checkbox" selfId="${levelThreeProductCatetory.id!""}" level="3"  name="interfaceDocProCateList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}" />
	                             </#if>
	                                <label><@s.text name="arrow_label" /></label>   
	                                <label>${levelThreeProductCatetory.name!""}</label>
                             </td>
                        </tr>
                        <#if levelThreeProductCatetory.childProductList??>
                             <#list levelThreeProductCatetory.childProductList as leafProduct>
                                 <tr>
                                     <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <#if interfaceDocProductList.contains(leafProduct.id)>
                                             <input type="checkbox" level="4"  selfId="${leafProduct.id!""}" name="interfaceDocProductList" value="${leafProduct.id!""}"  parentId="${levelThreeProductCatetory.id!""}" checked/>
                                        <#else>
                                             <input type="checkbox" level="4"  selfId="${leafProduct.id!""}" name="interfaceDocProductList" value="${leafProduct.id!""}"  parentId="${levelThreeProductCatetory.id!""}" />
                                        </#if>  
                                        <label><@s.text name="arrow_label" /></label>   
                                        <label>${leafProduct.model!""}</label>
                                     </td>
                                 </tr>                                 
                            </#list>
                       </#if>
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
                 <input type="checkbox"   level="1" selfId="${levelOneProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelOneProductCatetory.id!""}"  parentId="0" />
                 <label><@s.text name="arrow_label" /></label> 
                 <label>${levelOneProductCatetory.name!""}</label>
              </td>
           </tr>
           <#if levelOneProductCatetory.childCategoryList??>
               <#list levelOneProductCatetory.childCategoryList as levelTwoProductCatetory>
                   <tr>
                      <td>
                         &nbsp;&nbsp;&nbsp;&nbsp;
                         <input type="checkbox" level="2"  selfId="${levelTwoProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}" />
                         <label><@s.text name="arrow_label" /></label> 
                         <label>${levelTwoProductCatetory.name!""}</label>
                      </td>
                   </tr>
                   <#if levelTwoProductCatetory.childCategoryList??>
                       <#list levelTwoProductCatetory.childCategoryList as levelThreeProductCatetory>
	                       <tr>
	                          <td>
		                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                           <input type="checkbox" level="3"  selfId="${levelThreeProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}" />
		                           <label><@s.text name="arrow_label" /></label>   
		                           <label>${levelThreeProductCatetory.name!""}</label>
	                         </td>
	                       </tr>
	                       <#if levelThreeProductCatetory.childProductList??>
	                           <#list levelThreeProductCatetory.childProductList as leafProduct>
	                              <tr>
	                                  <td>
		                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                                  <input type="checkbox" level="4"  selfId="${leafProduct.id!""}" name="interfaceDocProductList" value="${leafProduct.id!""}"  parentId="${levelThreeProductCatetory.id!""}" />
		                                  <label><@s.text name="arrow_label" /></label>   
		                                  <label>${leafProduct.model!""}</label>
	                                  </td>
	                              </tr>                                 
	                           </#list>
	                       </#if>
                       </#list>  
                   </#if>
               </#list>
            </#if>
        </#list>  
     <#else>
		    <#if documentId?? && documentAgentProductCateList?? && documentAgentProductList?? >
		          <#list levelOneProductCateList as levelOneProductCatetory>
	                  <tr>
	                      <td>
	                          <#if documentAgentProductCateList.contains(levelOneProductCatetory.id)>
	                               <input type="checkbox" level="1" selfId="${levelOneProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelOneProductCatetory.id!""}"  parentId="0"  checked/>
	                          <#else>
	                               <input type="checkbox" level="1" selfId="${levelOneProductCatetory.id!""}"  name="interfaceDocProCateList" value="${levelOneProductCatetory.id!""}"  parentId="0" />
	                          </#if>
	                          <label><@s.text name="arrow_label" /></label> 
	                          <label>${levelOneProductCatetory.name!""}</label></td>
	                   </tr>
	                   <#if levelOneProductCatetory.childCategoryList??>
	                      <#list levelOneProductCatetory.childCategoryList as levelTwoProductCatetory>
	                           <tr>
	                              <td>
	                                  &nbsp;&nbsp;&nbsp;&nbsp;
	                                  <#if documentAgentProductCateList.contains(levelTwoProductCatetory.id)>
	                                       <input type="checkbox" level="2" selfId="${levelTwoProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}"  checked/>
	                                  <#else>
	                                       <input type="checkbox" level="2" selfId="${levelTwoProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}" />
	                                  </#if>
	                                  <label><@s.text name="arrow_label" /></label> 
	                                  <label>${levelTwoProductCatetory.name!""}</label>
	                              </td>
	                           </tr>
	                           <#if levelTwoProductCatetory.childCategoryList??>
	                               <#list levelTwoProductCatetory.childCategoryList as levelThreeProductCatetory>
	                                   <tr>
	                                       <td>
	                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                           <#if documentAgentProductCateList.contains(levelThreeProductCatetory.id)>
	                                               <input type="checkbox" level="3" selfId="${levelThreeProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}"  checked/>
	                                           <#else>
	                                               <input type="checkbox" level="3" selfId="${levelThreeProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}" />
	                                           </#if>
	                                           <label><@s.text name="arrow_label" /></label>   
	                                           <label>${levelThreeProductCatetory.name!""}</label>
	                                        </td>
                                       </tr>
	                                  <#if levelThreeProductCatetory.childProductList??>
                                           <#list levelThreeProductCatetory.childProductList as leafProduct>
                                               <tr>
                                                   <td>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <#if documentAgentProductList.contains(leafProduct.id)>
                                                            <input type="checkbox" level="4" selfId="${leafProduct.id!""}" name="interfaceDocProductList" value="${leafProduct.id!""}"  parentId="${levelThreeProductCatetory.id!""}" checked/>
                                                        <#else>
                                                            <input type="checkbox" level="4" selfId="${leafProduct.id!""}" name="interfaceDocProductList" value="${leafProduct.id!""}"  parentId="${levelThreeProductCatetory.id!""}" />
                                                        </#if>   
                                                            <label><@s.text name="arrow_label" /></label>   
                                                            <label>${leafProduct.model!""}</label>
                                                    </td>
                                               </tr>                                 
                                           </#list>
                                      </#if>  
	                               </#list>  
	                           </#if>
	                       </#list>
	                    </#if>
	              </#list>
		    <#else>
		        <#list levelOneProductCateList as levelOneProductCatetory>
	                 <tr>
	                     <td>
	                     <input type="checkbox"  level="1" selfId="${levelOneProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelOneProductCatetory.id!""}"  parentId="0" />
	                     <label><@s.text name="arrow_label" /></label> 
	                     <label>${levelOneProductCatetory.name!""}</label></td>
	                 </tr>
	                 <#if levelOneProductCatetory.childCategoryList??>
	                       <#list levelOneProductCatetory.childCategoryList as levelTwoProductCatetory>
	                           <tr>
	                               <td>
	                                   &nbsp;&nbsp;&nbsp;&nbsp;
	                                   <input type="checkbox" level="2" selfId="${levelTwoProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelTwoProductCatetory.id!""}"  parentId="${levelOneProductCatetory.id!""}" />
	                                   <label><@s.text name="arrow_label" /></label> 
	                                   <label>${levelTwoProductCatetory.name!""}</label>
	                               </td>
	                           </tr>
	                           <#if levelTwoProductCatetory.childCategoryList??>
	                                <#list levelTwoProductCatetory.childCategoryList as levelThreeProductCatetory>
	                                    <tr>
	                                         <td>
	                                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                             <input type="checkbox" level="3" selfId="${levelThreeProductCatetory.id!""}" name="interfaceDocProCateList" value="${levelThreeProductCatetory.id!""}"  parentId="${levelTwoProductCatetory.id!""}" />
	                                             <label><@s.text name="arrow_label" /></label>   
	                                             <label>${levelThreeProductCatetory.name!""}</label>
	                                         </td>
	                                    </tr>
	                                    <#if levelThreeProductCatetory.childProductList??>
	                                          <#list levelThreeProductCatetory.childProductList as leafProduct>
	                                                <tr>
		                                                 <td>
		                                                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                                                      <input type="checkbox" level="4" selfId="${leafProduct.id!""}" name="interfaceDocProductList" value="${leafProduct.id!""}"  parentId="${levelThreeProductCatetory.id!""}" />
		                                                      <label><@s.text name="arrow_label" /></label>   
		                                                      <label>${leafProduct.model!""}</label>
		                                                 </td>
                                                    </tr>	                               
	                                          </#list>
	                                     </#if>
	                                 </#list>  
	                             </#if>
	                        </#list>
	                   </#if>
	             </#list>
		    </#if>
	 </#if>
</#if>	   

