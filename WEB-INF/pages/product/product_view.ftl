<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="detail.product.label.title" /></title>
<#include "../common_header.ftl"/>
<script>
function changeAction(url){
    var form=document.getElementById("mdfForm");
    form.action=url;
    form.submit();
}

function deleteData(){
    var flag= confirm("<@s.text name="BSC00002" />");
    if (flag==true) {
        changeAction('PR002_32');
    }
}

function recoverData(){
    var flag= confirm("<@s.text name="BSC00004" />");
    if (flag==true) {
        changeAction('PR002_33');
    }
}

function deleteDataF() {
    var flag= confirm("<@s.text name="BSC00003" />");
    if (flag==true) {
        changeAction('PR002_40');
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
       <form id="mdfForm" action="PR002_11" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>                
			            <table width="70%" align="center" class="field_tbl">
			                <tbody>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="detail.product.label.length" /></td>
			                        <td width="80%">${product.length!""}</td>
			                    </tr>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="detail.product.label.width" /></td>
			                        <td width="80%">${product.width!""}</td>
			                    </tr>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="detail.product.label.height" /></td>
			                        <td width="80%">${product.height!""}</td>
			                    </tr>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="detail.product.label.weight" /></td>
			                        <td width="80%">${product.weight!""}</td>
			                    </tr>
			                    <tr>
			                        <td width="20%" class="lcell"><@s.text name="detail.product.label.guardLevel" /></td>
			                        <td width="80%">${product.guardLevel!""}</td>
			                    </tr>
			                </tbody>
			            </table>
                     </td>
                 </tr>
               </tbody>
            </table>  			            
            
		    <input type="hidden" id="actionForward" name="actionForwardP" value="${actionForwardP!""}"/>
		    <input type="hidden" name="navigationString" value="${navigationString!""}"/>
		    <input type="hidden" name="productCategoryId" value="${productCategoryId!""}"/>
		    <input type="hidden" name="product.id" value="${product.id!""}"/>
		    <input type="hidden" name="product.exclusiveKey" value="${product.exclusiveKey!""}"/>
		    <input type="hidden" id="checkedProductId" name="checkedProductId" value="${checkedProductId!-1}"/>            
         </form> 
         <div class="btn_row">
                <#if loginUser.hasPermission("PR002_30")>
                    <#if (product.deleted == 0)>
                        <button type="button" id="btnMdf" onClick="changeAction('PR002_30')" ><@s.text name="btn_modify" /></button>
                    <#else>
                        <button type="button" id="btnMdf" onClick="changeAction('PR002_30')" disabled><@s.text name="btn_modify" /></button>
                    </#if>
                </#if>
                <#if loginUser.hasPermission("PR002_32")>
                    <#if (product.deleted == 0)>
                        <button type="button" id="btnDel" onClick="deleteData()"  ><@s.text name="btn_delete"  /></button>
                    <#else>
                        <#if productCategoryDeletedFlg == "1">
                            <button type="button" id="btnDel" onClick="recoverData()" disabled><@s.text name="btn_recovery"  /></button>
                        <#else>
                            <button type="button" id="btnDel" onClick="recoverData()" ><@s.text name="btn_recovery"  /></button>
                        </#if>
                    </#if>
                </#if>
                <#if (loginUser.hasPermission("PR002_40") && product.deleted == 1)>
                    <button type="button" id="btnDelF" onClick="deleteDataF()" ><@s.text name="btn_delete_forever" /></button>
                </#if>
            </div>
        </div>
    </div>
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>