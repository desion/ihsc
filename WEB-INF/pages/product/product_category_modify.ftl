<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="modify.productCategory.title" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
function changeAction(url){
    var flag= confirm("<@s.text name="BSC00007" />");
    if(flag==true){
      var form=document.getElementById("mdfForm");
      form.action=url;
      form.submit();  
    }
}
</script>
</head>
<#include "../components/error_reference.ftl"/>
<body>
<#escape x as x?html>
<div id="container">
 <#include "../header.ftl"/>
   <div id="view" >
    <div class="fields">
      <form id="mdfForm" action="PR001_31" method="post">    
          <div class="pc_nav">
            <table>
                <#noescape>
                    <td><@s.text name="create.productCategory" />${navigationString!""}</td>
                </#noescape>
            </table>
          </div>

	       <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>               
			            <table width="70%" class="field_tbl">
			                <tbody>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="modify.parentProductCategoryName" /></td>
			                        <#if (preProductCategory.name != "") >
			                            <td width="80%">${preProductCategory.name!""}</td>
			                        <#else>
			                            <td width="80%"><@s.text name="modify.productCategory" /></td>
			                        </#if>
			                    </tr>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="modify.productCategoryName" /></td>
			                        <td width="80%">
			                            <input type="text" name="productCategory.name"  value="${productCategory.name!""}" maxLength="30" size="60"/>
			                            &nbsp;<label class="needed"><@s.text name="input_needed" /></label>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="modify.productCategorydescription" /></td>
			                        <td width="80%">
			                            <input type="text" name="productCategory.description"  value="${productCategory.description!""}" maxLength="80" size="80"/>
			                            &nbsp;<label class="needed"><@s.text name="input_needed" /></label>
			                        </td>
			                    </tr>
			                </tbody>
			            </table>
                   </td>
                 </tr>
              </tbody>
            </table> 
	        <input type="hidden" id="actionForward" name="actionForward" value="${actionForward!""}"/>
	        <input type="hidden" name="navigationString" value="${navigationString!""}"/>
	        <input type="hidden" name="productCategory.id" value="${productCategory.id!""}"/>
	        <input type="hidden" name="productCategory.parentId" value="${productCategory.parentId!""}"/>
	        <input type="hidden" name="productCategory.exclusiveKey" value="${productCategory.exclusiveKey!""}"/>
	        <input type="hidden" name="preProductCategory.id" value="${preProductCategory.id!""}"/>
	        <input type="hidden" name="preProductCategory.name" value="${preProductCategory.name!""}"/>
	        <input type="hidden" name="preProductCategory.parentId" value="${preProductCategory.parentId!""}"/>
	        <input type="hidden" id="preDeletedFlg" name="preProductCategory.deleted" value="${preProductCategory.deleted!0}"/>
	        <input type="hidden" name="checkedPcId" value="${checkedPcId!""}" />            
          </form>    			            
          <div class="btn_row">
            <#if loginUser.hasPermission("PR001_31")>
                <button type="button" onClick="changeAction('PR001_31')"><@s.text name="btn_modify" /></button>
            </#if>
          </div>
       </div>
    </div>
  <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>