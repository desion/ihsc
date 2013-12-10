<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="list.product.label.title" /></title>
<#include "../common_header.ftl"/>

<script type="text/javascript">

    function dtlAction() {
        $("#checkedProductId").val($(":radio[name=rad]:checked:eq(0)").attr("id"));
        $("#pId").val($(":radio[name=rad]:checked:eq(0)").attr("id"));
        $("#pExclusiveKey").val($(":radio[name=rad]:checked:eq(0)").val());
        var form=document.getElementById("mdfForm");
        form.action="PR002_11?actionForward=PR002_10";
        form.submit();
         
    }

    function changeAction(url) {
        $("#checkedProductId").val($(":radio[name=rad]:checked:eq(0)").attr("id"));
        $("#pId").val($(":radio[name=rad]:checked:eq(0)").attr("id"));
        $("#pExclusiveKey").val($(":radio[name=rad]:checked:eq(0)").val());
        var form=document.getElementById("mdfForm");
        form.action=url;
        form.submit();
         
    }

    $(document).ready(function(){

    $("tr").mouseover(function(){  
    $(this).addClass("over");}).mouseout(function(){ 
    $(this).removeClass("over");})
    //set the table style

    var radios = $("input[type=radio][name=rad]");
    var radioSize= radios.length;

    var btnDtlObj=$("#btnDtl");
    var btnMdfObj=$("#btnMdf");
    var btnDelObj=$("#btnDel");
    var btnDelFObj=$("#btnDelF");
    if (radioSize>0) {
        btnDelObj.removeAttr("disabled");
        btnDelFObj.removeAttr("disabled");
        btnMdfObj.removeAttr("disabled");
        var firstRad=$(":radio[name=rad]:eq(0)");
        firstRad.attr("checked",true);
        $("#pId").val(firstRad.attr("id"));
        $("#pExclusiveKey").val(firstRad.val());
    } else {
        btnDtlObj.attr("disabled","disabled");
        btnDelObj.attr("disabled","disabled");
        btnDelFObj.attr("disabled","disabled");
        btnMdfObj.attr("disabled","disabled");
    }

    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("#" + ${checkedProductId!-1}).attr("checked", "checked");
    </#if>

    });

</script>

</head>
<#include "../components/error_reference.ftl"/>
<body>
<#escape x as x?html>
<div id="container">
<form id="mdfForm" action="PR002_10" method="post">
    <#include "../header.ftl"/>
    <div id="view">
      <div>
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>          
		            <table>
		                <td><a href="PR001_10?actionForward=PR001_10"><@s.text name="list.product.label.productCategory" /></a></td>
		                <#list navigationList as naviList>
		                    <td>${"\g"}</td>
		                    <#if (naviList_index != 2)>
		                        <td><a href="PR001_11?productCategory.id=${naviList.id!""}&actionForward=PR001_10">${naviList.name!""}</a></td>
		                    <#else>
		                        <td><a href="PR002_10?productCategoryId=${naviList.id!""}&actionForwardP=PR001_10&fromSelfFlg=true">${naviList.name!""}</a></td>
		                    </#if>
		                </#list>
		            </table>
                 </td>
              </tr>
           </tbody>
          </table>             
        </div>
        <div class="fields" cellspacing="0" cellpadding="0">
	       <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>         
			            <table width="100%" class="field_tbl">
			                <tbody>
			                    <tr>
			                        <td width="12%" class="lcell"><@s.text name="list.product.label.productCategory.name" /></td>
			                        <td width="88%">${productCategoryName!""}</td>
			                    </tr>
			                    <tr>
			                        <td width="12%" class="lcell"><@s.text name="list.product.label.productCategory.description" /></td>
			                        <td width="88%">${productCategoryDescription!""}</td>
			                    </tr>
			                </tbody>
			            </table>
	                 </td>
	              </tr>
	           </tbody>
	        </table>  			            
        </div>
        
        <div class="gridview">
            <div>
	           <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	               <tbody>
	                  <tr>
	                    <td>             
			                <table class="datalist" width="100%" cellspacing="0" cellpadding="0">
			                    <tr>
			                        <th width="8%"><@s.text name="radio_label" /></th>
			                        <th><@s.text name="list.product.table.model" /></th>
			                        <th><@s.text name="list.product.table.producer" /></th>
			                        <#if (delRight == "1")>
			                            <th width="15%"><@s.text name="list.product.table.deleted" /></th>
			                        </#if>
			                    </tr>
			                    <#list productList as pList>
			                    <tr>
			                        <td><input type="radio" id="${pList.id}" name="rad" value="${pList.exclusiveKey}"  /></td>
			                        <td><input type="hidden" id="dtlName[${pList_index}]" value="${pList.model!""}">${pList.model!""}</td>
			                        <td><input type="hidden" id="dtlProducer[${pList_index}]" value="${pList.producerName!""}">${pList.producerName!""}</td>
			                        <#if (delRight == "1")>
			                            <#if (pList.deleted == 1)>
			                                <td><input type="hidden" id="dtlDelFlg[${pList_index}]" value="1"><label class="needed"><@s.text name="list.product.label.deleted" /></label></td>
			                            <#else>
			                                <td><input type="hidden" id="dtlDelFlg[${pList_index}]" value="0">&nbsp;</td>
			                            </#if>
			                        </#if>
			                    </tr>
			                    </#list>
			                </table>
	                     </td>
	                  </tr>
	               </tbody>
	            </table>  			                
            </div>
            <br>
            <div class="btn_row">
                <#if loginUser.hasPermission("PR002_20")>
                    <#if productCategoryDeletedFlg == "1">
                        <button type="button" id="btnAdd" onClick="changeAction('PR002_20')" disabled><@s.text name="btn_add" /></button>
                    <#else>
                        <button type="button" id="btnAdd" onClick="changeAction('PR002_20')" ><@s.text name="btn_add" /></button>
                    </#if>
                </#if>
                <#if loginUser.hasPermission("PR002_11")>
                    <button type="button" id="btnDtl" onClick="dtlAction()" ><@s.text name="btn_detail" /></button>
                </#if>
            </div>
        </div>
        <input type="hidden" id="actionForwardP" name="actionForwardP" value="${actionForwardP!""}"/>
        <input type="hidden" name="productCategoryId" value="${productCategoryId!""}"/>
        <input type="hidden" name="productCategoryName" value="${productCategoryName!""}"/>
        <input type="hidden" name="productCategoryDescription" value="${productCategoryDescription!""}"/>
        <input type="hidden" id="pId" name="product.id" />
        <input type="hidden" id="pProducer" name="product.producer" />
        <input type="hidden" id="pExclusiveKey" name="product.exclusiveKey" />
        <input type="hidden" id="checkedProductId" name="checkedProductId" />
        <input type="hidden" id="productCategoryDeletedFlg" name="productCategoryDeletedFlg" value="${productCategoryDeletedFlg}"/>
    </div>
    <#include "../footer.ftl"/>
</form>
</div>
</#escape>
</body>
</html>