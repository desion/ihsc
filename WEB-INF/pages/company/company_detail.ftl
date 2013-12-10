<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="company.detail" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
   	  form.submit();
   }
   
   function userListAction(url)
   {
      var form=document.getElementById("comIdForm");
      form.action=url;
      form.submit();
   }
  

   
   function changeActionConfirm(url,msg){
    var flag= confirm(msg);
       if(flag==true){
          changeAction(url);
        }
   }
   function deleteData(url){
       var msg = "<@s.text name="BSC00003" />";
       changeActionConfirm(url,msg);
   }
   
  function deleteDataForTemp(url){
       var msg = "<@s.text name="BSC00002" />";
       changeActionConfirm(url,msg);
   }
   
   function recover(url){
       var msg = "<@s.text name="BSC00004" />";
       changeActionConfirm(url,msg);
   }
   
   function ShowDetailInstall(divId,imageId) {
	   $("#"+divId).toggle();
	   if ($("#"+imageId).attr("src") == "../../../images/plus.png") {
	        $("#"+imageId).attr("src", "../../../images/minus.png");
	   } else {
	        $("#"+imageId).attr("src", "../../../images/plus.png");
	   }
    }
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container" >
 <#include "../header.ftl"/>
  <div id="view">
   <div class="fields">
    <form id="mdfForm" action="BS005_30" method="post">
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>             
			         <table width="70%" class="field_tbl" align="center">
				        <tr>
						   <th align="center" colspan="2" ><label><@s.text name="company.info" /></label></th>
						</tr> 
					    <tr>
						    <td class="lcell" width="20%"><label><@s.text name="company.code" /></label></td>
						    <td width="80%" ><label>${company.code!""}</label></td>
						    <input type="hidden" name="company.code"  value="${company.code!""}" />
						</tr>  
						<tr>
			                <td class="lcell"><label><@s.text name="company.companyTypeName" /></label></td>
			                <td><label>${company.companyTypeName!""}</label></td>
			                <input type="hidden" name="company.companyTypeName" value="${company.companyTypeName!""}" />
			            </tr> 
						<tr>
			                <td class="lcell"><label><@s.text name="company.shortName" /></label></td>
			                <td><label>${company.shortName!""}</label></td>
			                <input type="hidden" name="company.shortName"  value="${company.shortName!""}" />
			            </tr>
			           
					    <tr>
					        <td class="lcell"><label><@s.text name="company.mainCompanyName" /></label></td>
					        <td  ><label>${company.companyNameShow!""}</label></td>
					        <input type="hidden" name="company.companyNameShow" value="${company.companyNameShow!""}" />
					    </tr>

			  
					    <tr>
					        <td class="lcell"><label><@s.text name="company.address1" /></label></td>
					        <td ><label>${company.address1!""}</label></td>
					        <input type="hidden" name="company.address1" value="${company.address1!""}" />
					    </tr>
					    <tr>
					        <td class="lcell"><label><@s.text name="company.address2" /></label></td>
					        <td><label>${company.address2!""}</label></td>
					        <input type="hidden" name="company.address2" value="${company.address2!""}" />
					    </tr>		    
					    <tr>
					        <td class="lcell"><label><@s.text name="company.cityName" /></label></td>
			                <td><label>${company.cityName!""}</label></td>
			                <input type="hidden" name="company.cityName" value="${company.cityName!""}" />
					    </tr>
					    <tr>
			                <td class="lcell"><label><@s.text name="company.zipCode" /></label></td>
			                <td><label>${company.zipCode!""}</label></td>
			                <input type="hidden" name="company.zipCode" value="${company.zipCode!""}" />
			            </tr>
					    <tr>
					        <td class="lcell"><label><@s.text name="company.tel1" /></label></td>
					        <td><label>${company.tel1!""}</label></td>
					        <input type="hidden" name="company.tel1" value="${company.tel1!""}" />
					    </tr>
					    <tr>
					        <td class="lcell"><label><@s.text name="company.tel2" /></label></td>
					        <td><label>${company.tel2!""}</label></td>
					        <input type="hidden" name="company.tel2" value="${company.tel2!""}" />
					    </tr>
					    <tr>
					        <td class="lcell"><label><@s.text name="company.fax" /></label></td>
					        <td><label>${company.fax!""}</label></td>
					        <input type="hidden" name="company.fax" value="${company.fax!""}" />
					    </tr>
					    <tr>
					        <td class="lcell"><label><@s.text name="company.homePage" /></label></td>
					        <td style="word-break:break-all;word-wrap:break-word"><label>${company.homePage!""}</label></td>
					        <input type="hidden" name="company.homePage" value="${company.homePage!""}" />
					    </tr>
					    <tr>
					        <td class="lcell"><label><@s.text name="company.email" /></label></td>
					        <td><label>${company.email!""}</label></td>
					        <input type="hidden" name="company.email" value="${company.email!""}" />
					    </tr>
					</table>
                </td>
              </tr>
           </tbody>
        </table> 
        <#if company.typeId.indexOf(",3,") !=-1  && ((company.id == loginUser.companyID) || (loginUser.filter("company_mng_all_data")))> 	    
         <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>                   
                    <table style="margin-bottom:0px" width="90%" align="center" class="field_tbl">
                        <tbody>
                            <tr>
                                <th style="border-right:none" width="90%" class="lcell" align="center"><@s.text name="company.agentProduct" /></th>
                                <th style="border-left:none" width="10%" align="right" class="lcell">
                                    <a href="javascript:ShowDetailInstall('agentProductDiv','agentProductImg')">
                                        <img id="agentProductImg" style="vertical-align:top;horizontal-align:center;border:0px none;" src="../../../images/plus.png"></img>
                                    </a>
                                </th>
                            </tr>
                        </tbody>
                    </table>
                </td>
              </tr>
           </tbody>
         </table>  		   		   
		 <div id="agentProductDiv" class="gridview" style="display:none">
	         <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	            <tbody>
	               <tr>
	                 <td>     		   
			            <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0" >
			                <tr>
			                 <td>
			                   <div style="height:300px;overflow-y:scroll;overflow-x:hidden">
			                      <table class="field_tbl" width="98%">
			                          <@s.action name="product_category_agent_tree_detail" executeResult="true" ignoreContextParams="true"> 
			                             <@s.param name="agentComId">${company.id!-1}</@s.param>
			                          </@s.action>
			                      </table>
			                    </div>  
			                 </td>
			              </tr>   
		               </table>
	                </td>
	              </tr>
	           </tbody>
	         </table> 		               
	      </div>
	    </#if>
      
        <#if company.typeId.indexOf(",3,") !=-1 && ((company.id == loginUser.companyID) || (loginUser.filter("company_mng_all_data")))>       
         <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>                   
                    <table style="margin-bottom:0px" width="90%" align="center" class="field_tbl">
                        <tbody>
                            <tr>
                                <th style="border-right:none" width="90%" class="lcell" align="center"><@s.text name="company.agent" /></th>
                                <th style="border-left:none" width="10%" align="right" class="lcell">
                                    <a href="javascript:ShowDetailInstall('agentSaleDiv','agentSaleImg')">
                                        <img id="agentSaleImg" style="vertical-align:top;horizontal-align:center;border:0px none;" src="../../../images/plus.png"></img>
                                    </a>
                                </th>
                            </tr>
                        </tbody>
                    </table>
                </td>
              </tr>
           </tbody>
         </table>              
         <div id="agentSaleDiv" class="gridview" style="display:none">
           <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
             <tbody>
               <tr>
                  <td>            
                      <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0">
                         <tr>
                            <td>
                              <div style="height:300px;overflow-y:scroll;overflow-x:hidden">
                                 <table class="field_tbl" width="98%">
                                     <#list agentSaleCustomerList as saleCustomer>   
                                        <tr><td><label>${saleCustomer.shortName!""}</label></td></tr>
                                     </#list>    
                                 </table>
                               </div>  
                            </td>
                         </tr>   
                      </table>
                   </td>
                </tr>
            </tbody>
          </table>                         
        </div>
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>                   
                    <table style="margin-bottom:0px" width="90%" align="center" class="field_tbl">
                        <tbody>
                            <tr>
                                <th style="border-right:none" width="90%" class="lcell" align="center"><@s.text name="company.customer" /></th>
                                <th style="border-left:none" width="10%" align="right" class="lcell">
                                    <a href="javascript:ShowDetailInstall('agentBankDiv','agentBankImg')">
                                        <img id="agentBankImg" style="vertical-align:top;horizontal-align:center;border:0px none;" src="../../../images/plus.png"></img>
                                    </a>
                                </th>
                            </tr>
                        </tbody>
                    </table>
                </td>
              </tr>
           </tbody>
         </table>          
         <div id="agentBankDiv" class="gridview" style="display:none">
           <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
             <tbody>
               <tr>
                  <td>            
                      <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0">
	                      <tr>
	                         <td>
	                           <div style="height:300px;overflow-y:scroll;overflow-x:hidden">
	                              <table class="field_tbl" width="98%">
	                                 <#list agentBankCustomerLst as bankCustomer>   
	                                    <tr><td><label>${bankCustomer.shortName!""}</label></td></tr>
	                                 </#list>    
	                              </table>
	                            </div>  
	                         </td>
	                     </tr>   
                       </table>
                   </td>
                </tr>
            </tbody>
          </table>                         
        </div>
     </#if>
           <input type="hidden" name="company.typeId" value="${company.typeId!""}" />
	       <input type="hidden" name="company.deleted" value="${company.deleted!-1}" />
	       <input type="hidden" name="company.id" value="${company.id!-1}" />
           <input type="hidden" name="company.exclusiveKey" value="${company.exclusiveKey!-1}"  />
        </form>  
          
        <form id="comIdForm">
            <input type="hidden" name="user.companyID" value="${company.id!-1}" />
        </form> 
         
	   <div class="btn_row">
	       <#if company.deleted==0>      
	          <#if loginUser.hasPermission("BS005_30")>       
                 <button type="button" onClick="changeAction('BS005_30')" ><@s.text name="btn_modify" /></button>
              </#if>   
           </#if>
           <#if loginUser.hasPermission("BS005_22")>         
                <button type="button" onClick="changeAction('BS005_22')" ><@s.text name="btn_template_add" /></button>
           </#if>
           
           <#if company.deleted==0>        
             <#if loginUser.hasPermission("BS005_33")>      
               <button type="button" onClick="deleteDataForTemp('BS005_33')" ><@s.text name="btn_delete" /></button>
             </#if>
             <#if loginUser.hasPermission("BS005_37")>               
                <button type="button" onClick="changeAction('BS005_37')"  style="width:90px" ><@s.text name="btn_customer_edit" /></button>
             </#if>    
           <#else>
             <#if loginUser.hasPermission("BS005_34")>      
               <button type="button" onClick="recover('BS005_34')" ><@s.text name="btn_recovery" /></button>
             </#if>  
             <#if loginUser.hasPermission("BS005_40")>              
                <button type="button" onClick="deleteData('BS005_40')" ><@s.text name="btn_delete_forever" /></button>
             </#if>
                  
           </#if>
           <#if loginUser.hasPermission("BS007_10")>  
               <button type="button" onClick="userListAction('BS007_10')" ><@s.text name="btn_company_user" /></button>
           </#if>
        </div>        

     </div>  
   </div>  
  <#include "../footer.ftl"/>        
</div>
</#escape>  
</body>
</html>