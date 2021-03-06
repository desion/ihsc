<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="document.detail" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
    function changeAction(url){
      var form=document.getElementById("detailForm");
      form.action=url;
      form.submit();
    }
    
    $(document).ready(function(){

       $(":checkbox").each(function(){
           $(this).click(function(){
              if($(this).attr("checked")==true){
                  $(this).removeAttr("checked");
              }else{
                  $(this).attr("checked","checked");
              }
           });
       });
       
    });      
      
</script>
</head>
<body>
<#include "../components/error_reference.ftl"/>
<div id="container">
  <#include "../header.ftl"/>
   <div id="view">
      <div class="fields">  
       <form id="detailForm" method="post" enctype ="multipart/form-data">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                 <td>           
			              <table width="70%" align="center" class="field_tbl" style="table-layout:fixed">
			                  <tbody> 
			                     <tr>
			                          <td class="lcell" width="15%"><label><@s.text name="document.name"  /></label></td>
			                          <td width="85%"><label>${document.name!""}</label></td>
			                      </tr>
			                      <tr>
			                          <td class="lcell" width="15%"><label><@s.text name="document.type"  /></label></td>
			                          <td width="85%"><label>${document.docTypeName!""}</label></td>
			                      </tr>
			                      <tr>
			                            <td width="15%" class="lcell"><label><@s.text name="document.title" /></label></td>
			                            <td width="90%"><label>${document.title!""}</label></td>
			                      </tr> 
			                      <tr>
			                          <td width="15%" class="lcell" ><label><@s.text name="document.summary" /></label></td>
			                          <td width="85%" style="word-break:break-all;word-wrap:break-word;"><label>${document.summary!""}</label></td>
			                      </tr> 
			                      <tr>
			                          <td width="15%" class="lcell"><label><@s.text name="document.publishDate" /></label></td>
			                          <td width="85%"><label>${document.publishDate!""}</label></td>
			                      </tr>  
			                  </tbody>
			              </table>
		              </td>
		            </tr>
		         </tbody>
		     </table> 
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                     <td>   		     
			             <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0" style="margin:10px auto">
			               <tr>
			                 <th style="text-align:center"><label><@s.text name="document.productAndCategory" /></label></th>
			               </tr>
			               <tr>
			                 <td>
			                   <div style="height:300px;overflow-y:scroll;overflow-x:hidden">
			                      <table class="field_tbl" width="97%">
			                          <@s.action name="document_product_category_agent_tree_detail" executeResult="true" ignoreContextParams="true"> 
			                             <@s.param name="documentId">${document.id!-1}</@s.param> 
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
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                     <td>                         
			            <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0" style="margin:10px auto">
			                 <tr>
			                      <th colspan="2" style="text-align:center">
			                         <label><@s.text name="document.agent" /></label>
			                      </th>
			                 </tr>
			                 <tr>
			                     <td colspan="2">
			                       <div style="height:300px;overflow-y:scroll;overflow-x:hidden">
			                          <table class="field_tbl" width="97%">
			                          <#if loginUser.filter("company_mng_all_data")>
			                            <@s.action name="document_company_list" executeResult="true" ignoreContextParams="true">
			                                <@s.param name="documentId">${document.id!-1}</@s.param>
			                            </@s.action>
			                          <#else>
										  <tr>
							                <td width="10%"><input type="checkbox" name="companyIdList" value="${loginUser.companyID!"-1"}" checked/></td>
							                <td width="90%"><label>${loginUser.companyName!""}</label></td>
							              </tr>
			                          </#if>
			                          </table>
			                        </div>  
			                     </td>
			                  </tr>   
			             </table>
                      </td>
                    </tr>
                 </tbody>
            </table>
        </form>
       <#if loginUser.hasPermission("PR006_10")>          
          <div class="btn_row">
            <button type="button" onClick="changeAction('PR006_10')" ><@s.text name="btn_return" /></button>
          </div>
       </#if>   
     </div>
   </div>
 <#include "../footer.ftl"/>
</div>
</body>
</html>