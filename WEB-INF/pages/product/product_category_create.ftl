<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="create.productCategory.title" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
function changeAction(url) {
    var flag= confirm("<@s.text name="BSC00006" />");
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
        <form id="mdfForm" action="PR001_21" method="post">      
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
			            <table width="70%" align="center" class="field_tbl">
			                <tbody>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="create.parentProductCategoryName" /></td>
			                        <#if (preProductCategory.name != "") >
			                            <td width="80%">${preProductCategory.name!""}</td>
			                        <#else>
			                            <td width="80%"><@s.text name="create.productCategory" /></td>
			                        </#if>
			                    </tr>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="create.productCategoryName" /></td>
			                        <td width="80%">
			                            <input type="text" name="productCategory.name"  value="${productCategory.name!""}" maxLength="30" size="60"/>
			                            &nbsp;<label class="needed"><@s.text name="input_needed" /></label>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="create.productCategorydescription" /></td>
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
	        <input type="hidden" name="preProductCategory.id" value="${preProductCategory.id!""}"/>
	        <input type="hidden" name="preProductCategory.name" value="${preProductCategory.name!""}"/>
	        <input type="hidden" name="preProductCategory.parentId" value="${preProductCategory.parentId!""}"/>
	        <input type="hidden" id="preDeletedFlg" name="preProductCategory.deleted" value="${preProductCategory.deleted!0}"/>	         
	    </form>     			            
        <div class="btn_row">
             <#if loginUser.hasPermission("PR001_21")>
                <button type="button" onClick="changeAction('PR001_21')"><@s.text name="btn_add" /></button>
             </#if>   
        </div>
     </div>
   </div>
  <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>