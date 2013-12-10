<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="document.search" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
      form.submit();
   }
   
   $(document).ready(function(){
       $("#publishDate").datepicker();
    });
    
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container" >
  <#include "../header.ftl"/>
   <div id="view">
      <div class="fields">  
         <form id="mdfForm" action="PR006_12" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>          
			             <table width="70%" class="field_tbl" align="center">
			                  <tr>
			                      <td class="lcell"><label><@s.text name="document.productCategory"  /></label></td>
			                      <td>
			                         <select name="document.productCategoryId" >
			                            <@s.action name="productCategory_drop" executeResult="true" ignoreContextParams="true">
			                               <@s.param name="selectedProductCategoryId">${document.productCategoryId!"-1"}</@s.param>
			                            </@s.action>
			                         </select>               
			                      </td>
			                  </tr> 
			                           
			                  <tr>
			                      <td class="lcell"><label><@s.text name="document.productModel"  /></label></td>
			                      <td>
			                         <select  name="document.productId" >
			                            <@s.action name="productID_drop" executeResult="true" ignoreContextParams="true">
			                               <@s.param name="selectedProductId">${document.productId!"-1"}</@s.param>
			                            </@s.action>
			                         </select>               
			                      </td>
			                  </tr>                                     
			                  <tr>
			                      <td class="lcell"><label><@s.text name="document.type"  /></label></td>
			                      <td>
			                         <select  name="document.docTypeId" >
			                            <@s.action name="documentType_drop" executeResult="true" ignoreContextParams="true">
			                               <@s.param name="selectedDocumentTypeId">${document.docTypeId!"-1"}</@s.param>
			                            </@s.action>
			                         </select>               
			                      </td>
			                  </tr> 
			                         
			                  <tr>
			                      <td width="20%" class="lcell"><label><@s.text name="document.name" /></label></td>
			                      <td width="80%">
			                          <input type="text" name="document.name"  value="${document.name!""}" size="40" maxLength="40"/>
			                      </td>
			                  </tr> 
			                  <tr>
			                      <td width="20%" class="lcell"><label><@s.text name="document.title" /></label></td>
			                      <td width="80%">
			                          <input type="text" name="document.title"  value="${document.title!""}" size="60" maxLength="80"/>
			                      </td>
			                  </tr> 
			                  
			                  <tr>
			                      <td width="20%" class="lcell"><label><@s.text name="document.summary" /></label></td>
			                      <td width="80%">
			                          <input type="text" name="document.summary"  value="${document.summary!""}" size="60" maxLength="80"/>
			                      </td>
			                  </tr> 
			                    
			                  <tr>
			                      <td width="20%" class="lcell"><label><@s.text name="document.publishDate" /></label></td>
			                      <td width="80%">
			                          <input type="text" id="publishDate" name="document.publishDate"  value="${document.publishDate!""}" size="10" maxLength="10" />
			                      </td>
			                  </tr>  
			              </table> 
		              </td>
		            </tr>
		         </tbody>
		      </table>  			               
          </form>
          <div class="btn_row">
             <#if loginUser.hasPermission("PR006_12")>       
                <button type="button" onClick="changeAction('PR006_12')" ><@s.text name="btn_search" /></button>
             </#if>
          </div>
      </div>
   </div>
 <#include "../footer.ftl"/> 
</div>
</#escape> 
</body>
</html>