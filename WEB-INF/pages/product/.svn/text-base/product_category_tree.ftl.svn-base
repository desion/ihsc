<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="product_category_tree" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url,id,model)
   {
      var form=document.getElementById("myForm");
      $("#productId").val(id);
      $("#model").val(model);
      form.action=url;
      form.submit();
   }
   
   function addInstallAction(url,id,model,productCategoryId)
   {
      $("#productCategoryId").val(productCategoryId);
      changeAction(url,id,model);
   }
</script>
</head>
<body>
<#escape x as x!""?html>
<#include "../components/error_reference.ftl"/>
<div id="container">
 <#include "../header.ftl"/>
  <div id="view">
  <div class="fields">
    <div style="width:100%; margin:0px auto;">
      <table class="tree_tbl" cellspacing="0" cellpadding="0">
        <tr>
          <th class="title-cell-tree" colspan="2" align="center"><@s.text name="product_category_tree" /></th>
        </tr>
        <#list levelOneList as level1>
         <tr>
           <td class="tree-cell-left" width="15%" valign="top"><a href="PR001_11?productCategory.id=${level1.id}&amp;actionForward=PR003_00">${level1.name}</a></td>
             <td width="85%" valign="top">
               <table class="tree_tbl" cellspacing="0" cellpadding="0">
                 <#if map.get(level1)?exists>
                 <#list map.get(level1) as level2>
                 <tr>
                   <td class="tree-cell-left" width="20%" valign="top"><a href="PR001_11?productCategory.id=${level2.id}&amp;actionForward=PR003_00">${level2.name}</a></td>
                   <td width="80%" valign="top">
                     <table class="tree_tbl" cellspacing="0" cellpadding="0">
                     <#if map.get(level2)?exists>
                     <#list map.get(level2) as level3>
                       <tr>
                         <td class="tree-cell-left" width="30%" valign="top"><a href="PR002_10?productCategoryId=${level3.id}&amp;actionForwardP=PR003_00">${level3.name}</a></td>
                         <td width="70%" valign="top">
                           <table class="tree_tbl" cellspacing="0" cellpadding="0">
                           <#if map.get(level3)?exists>
                           <#list map.get(level3) as level4>
                             <tr>
                               <td class="tree-cell-left" width="60%" valign="top"><a href="PR002_11?product.id=${level4.id}&amp;productCategoryId=${level4.productCategoryId}&amp;product.exclusiveKey=${level4.exclusiveKey}&amp;actionForwardP=PR003_00">${level4.model}</a></td>
                               <td class="tree-cell" width="40%" valign="middle">
                                 <#if loginUser.hasPermission("PR005_10")>
                                   <button onClick="changeAction('PR005_10','${level4.id}', '')"><@s.text name="btn_list"/></button>
                                 </#if>
                                 <#if loginUser.hasPermission("PR005_20")>
                                    <button onClick="addInstallAction('PR005_20','${level4.id}','${level4.model}','${level4.productCategoryId}')"><@s.text name="btn_add"/></button>
                                 </#if>
                               </td>
                             </tr>
                           </#list>
                           <#else>
                             <tr>
                              <td class="tree-cell-left" width="60%" valign="top"><@s.text name="nonModel" align="center"/></td>
                              <td class="tree-cell" width="40%">&nbsp;</td>
                             </tr>
                           </#if>
                           </table>
                         </td>
                       </tr>
                     </#list>
                     <#else>
                      <tr>
                       <td class="tree-cell-left" width="30%" valign="top">&nbsp;</td>
                       <td width="70%" valign="top">
                           <table class="tree_tbl" cellspacing="0" cellpadding="0">
                             <tr>
                               <td class="tree-cell-left" width="60%" valign="top">&nbsp;</td>
                               <td class="tree-cell" width="40%" valign="top">&nbsp;</td>
                             </tr>
                           </table>
                       </td>
                      </tr>
                     </#if>
                     </table>
                   </td>
                 </tr>
                 </#list>
                 <#else>
                 <tr>
                   <td class="tree-cell-left" width="20%" valign="top">&nbsp;</td>
                   <td width="80%" valign="top">
                   <table class="tree_tbl" cellspacing="0" cellpadding="0">
                     <tr>
                         <td class="tree-cell-left" width="30%" valign="top">&nbsp;</td>
                         <td width="70%" valign="top">
	                       <table class="tree_tbl" cellspacing="0" cellpadding="0">
	                         <tr>
                               <td class="tree-cell-left" width="60%" valign="top">&nbsp;</td>
                               <td class="tree-cell" width="40%" valign="top">&nbsp;</td>
	                         </tr>
	                       </table>
                         </td>
                     </tr>    
                   </table>
                 </tr>
                 </#if>
               </table>
             </td>
           </tr>
        </#list>
        <tr>
	        <td height="1" bgcolor="#666666" colspan="2">
	        </td>  
        </tr>
      </table>
    </div>
	<form id="myForm" method="post" action="">
	    <input id="productId" type="hidden" value="" name="install.productId"/>
	    <input id="model" type="hidden" value="" name="install.model"/>
	    <input id="productCategoryId" type="hidden" value="" name="install.productCategoryId"/>
	</form>
  </div>
 </div>  
 <#include "../footer.ftl"/>     
</div>
</#escape>    
</body>
</html>