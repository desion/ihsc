
<#if documentId?? && documentAgentProductCateList?? && documentAgentProductList?? >
  <#list levelOneProductCateList as levelOneProductCatetory>
          <#if documentAgentProductCateList.contains(levelOneProductCatetory.id)>
	          <tr>
	              <td>
	                 <label><@s.text name="arrow_label" /></label> 
	                 <label>${levelOneProductCatetory.name!""}</label>
	              </td>
	           </tr>
          </#if>
       <#if levelOneProductCatetory.childCategoryList??>
          <#list levelOneProductCatetory.childCategoryList as levelTwoProductCatetory>
                  <#if documentAgentProductCateList.contains(levelTwoProductCatetory.id)>
	                  <tr>
		                   <td>
		                      &nbsp;&nbsp;&nbsp;&nbsp;                      
		                      <label><@s.text name="arrow_label" /></label> 
		                      <label>${levelTwoProductCatetory.name!""}</label> 
		                    </td> 
		              </tr>                          
                  </#if>
               <#if levelTwoProductCatetory.childCategoryList??>
                   <#list levelTwoProductCatetory.childCategoryList as levelThreeProductCatetory>
                         <#if documentAgentProductCateList.contains(levelThreeProductCatetory.id)>
                          <tr>
                               <td>
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                               
                                  <label><@s.text name="arrow_label" /></label>   
                                  <label>${levelThreeProductCatetory.name!""}</label>
                                </td> 
                           </tr>           
                         </#if>
                      <#if levelThreeProductCatetory.childProductList??>
                           <#list levelThreeProductCatetory.childProductList as leafProduct>
                               <#if documentAgentProductList.contains(leafProduct.id)>
                                    <tr>
                                      <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label><@s.text name="arrow_label" /></label>   
                                        <label>${leafProduct.model!""}</label>
                                      </td> 
                                  </tr>                                           
                              </#if>   
                           </#list>
                      </#if>  
                   </#list>  
               </#if>
           </#list>
        </#if>
  </#list>
</#if>	   

