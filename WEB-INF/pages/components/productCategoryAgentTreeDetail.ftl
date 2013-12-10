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
          <#if flag==1>
          <td>
	          <label><@s.text name="arrow_label" /></label> 
	          <label>${levelOneProductCatetory.name!""}</label>
          </td>          
          </#if>
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
                    <#if flag==1>
                    <td>
	                    &nbsp;&nbsp;&nbsp;&nbsp;                    
	                    <label><@s.text name="arrow_label" /></label> 
	                    <label>${levelTwoProductCatetory.name!""}</label>
                    </td>                   
                    </#if>
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
	                     <#if flag==1>
	                       <td>
	                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	                         <label><@s.text name="arrow_label" /></label>   
	                         <label>${levelThreeProductCatetory.name!""}</label>
	                       </td>   
	                    </#if>
                   </tr>
                 </#list>  
               </#if>
           </#list>
      </#if>
   </#list>
 </#if>