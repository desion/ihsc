<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="list.productCategory.title" /></title>
<#include "../common_header.ftl"/>

<script type="text/javascript">

    var detailUrl;
    var deleteMsg;
    function dtlAction() {
        var form=document.getElementById("mdfForm");
        $("checkedPcId").val($(":radio[name=rad]:checked:eq(0)").attr("id"));
        if (detailUrl == "PR002_10") {
            $("#pcId").val($("#parentId").val());
            var pcId=$(":radio[name=rad]:checked:eq(0)").attr("id");
            form.action=detailUrl + "?productCategoryId=" + pcId + "&actionForwardP=PR001_10&fromSelfFlg=false";
        } else {
            var exclusiveKey=$(":radio[name=rad]:checked:eq(0)").val();
            var pcId=$(":radio[name=rad]:checked:eq(0)").attr("id");
            $("#pcId").val(pcId);
            $("#exclusiveKey").val(exclusiveKey);
            form.action=detailUrl;
        }
        form.submit();
    }

    function changeAction(url) {
        var exclusiveKey=$(":radio[name=rad]:checked:eq(0)").val();
        var pcId=$(":radio[name=rad]:checked:eq(0)").attr("id");
        $("#pcId").val(pcId);
        $("#exclusiveKey").val(exclusiveKey);
        $("#checkedPcId").val(pcId);
        var form=document.getElementById("mdfForm");
        form.action=url;
        form.submit();
         
    }

    var delEventFlg;
    function deleteData(){
        var flag= confirm(deleteMsg);
        if (flag==true) {
            var exclusiveKey=$(":radio[name=rad]:checked:eq(0)").val();
            var pcId=$(":radio[name=rad]:checked:eq(0)").attr("id");
            $("#pcId").val(pcId);
            $("#exclusiveKey").val(exclusiveKey);
            changeAction(delEventFlg);
        }
    }
    
    function deleteDataF(url) {
        var flag= confirm("<@s.text name="message.confirm.delete.forever" />");
        if (flag==true) {
            changeAction(url);
        }
    }

    $(document).ready(function(){

    $("tr").mouseover(function(){  
    $(this).addClass("over");}).mouseout(function(){ 
    $(this).removeClass("over");})
    //set the table style

    $("input[type=radio][name=rad]").click(function(){
        var iRow;
        var iCurr;
        var radObj = $(document.getElementsByName("rad"));
        for (iRow = 0; iRow < radObj.length; iRow++) {
            if (radObj[iRow].checked) {
                iCurr = iRow;
                break;
            }
        }
        if ($("#delRight").val() == "1") {
            if ($(":input[id=dtlDelFlg[" + iCurr + "]]").val() == 0) {
                $("#btnDel").text("<@s.text name="btn_delete" />");
                deleteMsg = "<@s.text name="message.confirm.delete.business" />";
                if ($("#delFRight").val() == "1") {
                    $("#btnDelF").attr("disabled","disabled");
                }
                delEventFlg = "PR001_32";
            } else {
                $("#btnDel").text("<@s.text name="btn_recovery" />");
                deleteMsg = "<@s.text name="message.confirm.recover" />";
                if ($("#delFRight").val() == "1") {
                    $("#btnDelF").removeAttr("disabled");
                }
                delEventFlg = "PR001_33";
            }
        }
        $("#pcName").val($(":input[id=dtlName[" + iCurr + "]]").val());
    });

    $("table.datalist tr ").each(function(){
        $(this).click(function(){
            var iRow;
            var iCurr;
            var radObj = $(document.getElementsByName("rad"));
            for (iRow = 0; iRow < radObj.length; iRow++) {
                if (radObj[iRow].checked) {
                    iCurr = iRow;
                    break;
                }
            }
            if ($("#delRight").val() == "1") {
                if ($(":input[id=dtlDelFlg[" + iCurr + "]]").val() == 0) {
                    $("#btnMdf").removeAttr("disabled");
                    $("#btnDel").text("<@s.text name="btn_delete" />");
                    deleteMsg = "<@s.text name="message.confirm.delete.business" />";
                    if ($("#delFRight").val() == "1") {
                        $("#btnDelF").attr("disabled","disabled");
                    }
                    delEventFlg = "PR001_32";
                } else {
                    $("#btnMdf").attr("disabled","disabled");
                    $("#btnDel").text("<@s.text name="btn_recovery" />");
                    deleteMsg = "<@s.text name="message.confirm.recover" />";
                    if ($("#delFRight").val() == "1") {
                        $("#btnDelF").removeAttr("disabled");
                    }
                    delEventFlg = "PR001_33";
                }
            }
            $("#pcName").val($(":input[id=dtlName[" + iCurr + "]]").val());
        });
    });

    var radios = $("input[type=radio][name=rad]");
    var radioSize= radios.length;

    var btnDtlBtnFlgObj=$("#dtlBtnFlg");
    var btnDtlObj=$("#btnDtl");
    var btnMdfObj=$("#btnMdf");
    var btnDelObj=$("#btnDel");
    var btnDelFObj=$("#btnDelF");
    if (radioSize>0) {
        detailUrl = btnDtlBtnFlgObj.val();
        if (btnDtlObj != undefined) {
            btnDtlObj.removeAttr("disabled");
        }
        if (btnDelObj != undefined) {
            btnDelObj.removeAttr("disabled");
        }
        if (btnDelFObj != undefined) {
            btnDelFObj.removeAttr("disabled");
        }
        if (btnMdfObj != undefined) {
            btnMdfObj.removeAttr("disabled");
        }
        var firstRad=$(":radio[name=rad]:eq(0)");
        firstRad.attr("checked",true);
        if ($("#delRight").val() == "1") {
            if ($(":input[id=dtlDelFlg[0]]").val() == 0) {
                $("#btnMdf").removeAttr("disabled");
                $("#btnDel").text("<@s.text name="btn_delete" />");
                deleteMsg = "<@s.text name="message.confirm.delete.business" />";
                if ($("#delFRight").val() == "1") {
                    $("#btnDelF").attr("disabled","disabled");
                }
                delEventFlg = "PR001_32";
            } else {
                $("#btnMdf").attr("disabled","disabled");
                $("#btnDel").text("<@s.text name="btn_recovery" />");
                deleteMsg = "<@s.text name="message.confirm.recover" />";
                if ($("#delFRight").val() == "1") {
                    $("#btnDelF").removeAttr("disabled");
                }
                delEventFlg = "PR001_33";
            }
        }
        $("#pcName").val($(":input[id=dtlName[0]]").val());
    } else {
        if (btnDtlObj != undefined) {
            btnDtlObj.attr("disabled","disabled");
        }
        if (btnDelObj != undefined) {
            btnDelObj.attr("disabled","disabled");
        }
        if (btnDelFObj != undefined) {
            btnDelFObj.attr("disabled","disabled");
        }
        if (btnMdfObj != undefined) {
            btnMdfObj.attr("disabled","disabled");
        }
    }
    var preDeletedFlg = $("#preDeletedFlg");
    var btnAddObj=$("#btnAdd");
    var btnDelObj=$("#btnDel");
    if (btnAddObj != undefined && btnDelObj != undefined) {
        if (preDeletedFlg.val() == 1) {
            btnAddObj.attr("disabled","disabled");
            btnDelObj.attr("disabled","disabled");
        }
    }

    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()>
        $("#" + ${checkedPcId!-1}).attr("checked", "checked");
         
                if($("#" + ${checkedPcId!-1}).parent().next().next().next().find("input").val() == 0) {
                    $("#btnMdf").removeAttr("disabled");
                    $("#btnDel").text("<@s.text name="btn_delete" />");
                    deleteMsg = "<@s.text name="message.confirm.delete.business" />";
                    if ($("#delFRight").val() == "1") {
                         $("#btnDelF").attr("disabled","disabled");
                    }
                   delEventFlg = "PR001_32";
                } else {
                    $("#btnMdf").attr("disabled","disabled");
                    $("#btnDel").text("<@s.text name="btn_recovery" />");
                    deleteMsg = "<@s.text name="message.confirm.recover" />";
                    if ($("#delFRight").val() == "1") {
                        $("#btnDelF").removeAttr("disabled");
                    }
                    delEventFlg = "PR001_33";
                }
            
           $("#pcName").val($("#" + ${checkedPcId!-1}).parent().next().find("input").val());
            
     
    </#if>

    });

</script>

</head>
<#include "../components/error_reference.ftl"/>
<body>
<#escape x as x?html>
<div id="container">
<form id="mdfForm" action="PR001_20" method="post">
    <#include "../header.ftl"/>
    <div id="view" >
       <div>
          <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>           
		            <table>
		                <td><a href="PR001_10?actionForward=PR001_10"><@s.text name="list.productCategory.label.productCategory" /></a></td>
		                <#list navigationList as naviList>
		                    <td>${"\g"}</td>
		                    <td><a href="PR001_11?productCategory.id=${naviList.id!""}&actionForward=PR001_10">${naviList.name!""}</a></td>
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
			                        <td width="12%" class="lcell"><@s.text name="list.productCategory.label.productCategory.name" /></td>
			                        <#if (productCategoryName == "")>
			                            <td align="left" width="88%"><@s.text name="list.productCategory.label.productCategory" /></td>
			                        <#else>
			                            <td align="left" width="88%">${productCategoryName!""}</td>
			                        </#if>
			                    </tr>
			                    <tr>
			                        <td width="12%" class="lcell"><@s.text name="list.productCategory.label.productCategory.description" /></td>
			                        <#if (productCategoryDescription == "")>
			                            <td align="left" width="88%">&nbsp;</td>
			                        <#else>
			                            <td align="left" width="88%">${productCategoryDescription!""}</td>
			                        </#if>
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
			                        <th><@s.text name="list.productCategory.name" /></th>
			                        <th><@s.text name="list.productCategory.description" /></th>
			                        <#if (delRight == "1")>
			                            <th width="15%"><@s.text name="list.productCategory.deleted" /></th>
			                        </#if>
			                    </tr>
			                    <#list productCategoryList as pcList>
			                    <tr>
			                        <td><input type="radio" id="${pcList.id}" name="rad" value="${pcList.exclusiveKey}"  /></td>
			                        <td><input type="hidden" id="dtlName[${pcList_index}]" value="${pcList.name!""}" />${pcList.name!""}</td>
			                        <td><input type="hidden" id="dtlDescription[${pcList_index}]" value="${pcList.description!""}">${pcList.description!""}</td>
			                        <#if (delRight == "1")>
			                            <#if (pcList.deleted == 1)>
			                                <td><input type="hidden" id="dtlDelFlg[${pcList_index}]" value="1" /><label class="needed"><@s.text name="list.productCategory.label.deleted" /></label></td>
			                            <#else>
			                                <td><input type="hidden" id="dtlDelFlg[${pcList_index}]" value="0" />&nbsp;</td>
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
                <#if (dtlBtnFlg == "PR001_11" && loginUser.hasPermission("PR001_11")) || (dtlBtnFlg == "PR002_10" && loginUser.hasPermission("PR002_10"))>
                    <button type="button" id="btnDtl" onClick="dtlAction()" ><@s.text name="btn_next_level" /></button>
                </#if>
                <#if loginUser.hasPermission("PR001_20")>
                    <button type="button" id="btnAdd" onClick="changeAction('PR001_20')" ><@s.text name="btn_add" /></button>
                </#if>
                <#if loginUser.hasPermission("PR001_30")>
                    <button type="button" id="btnMdf" onClick="changeAction('PR001_30')" ><@s.text name="btn_modify" /></button>
                </#if>
                <#if (delRight == "1")>
                    <button type="button" id="btnDel" onClick="deleteData()"  ><@s.text name="btn_delete"  /></button>
                </#if>
                <#if (delFRight == "1")>
                   <#if loginUser.hasPermission("PR001_40")>
                        <button type="button" id="btnDelF" onClick="deleteDataF('PR001_40')" ><@s.text name="btn_delete_forever" /></button>
                   </#if> 
                </#if>
            </div>
        </div>

        <input type="hidden" id="actionForward" name="actionForward" value="${actionForward!""}"/>
        <input type="hidden" name="productCategoryName" value="${productCategoryName!""}"/>
        <input type="hidden" name="productCategoryDescription" value="${productCategoryDescription!""}"/>
        <input type="hidden" id="delRight" name="delRight" value="${delRight!""}"/>
        <input type="hidden" id="delFRight" name="delFRight" value="${delFRight!""}"/>
        <input type="hidden" id="dtlBtnFlg" name="dtlBtnFlg" value="${dtlBtnFlg}"/>
        <input type="hidden" id="pcId" name="productCategory.id" />
        <input type="hidden" id="pcName" name="productCategory.name" />
        <input type="hidden" id="pcDescription" name="productCategory.description" />
        <input type="hidden" id="exclusiveKey" name="productCategory.exclusiveKey" />
        <input type="hidden" id="parentId" name="preProductCategory.id" value="${preProductCategory.id!""}"/>
        <input type="hidden" name="preProductCategory.name" value="${preProductCategory.name!""}"/>
        <input type="hidden" id="preDeletedFlg" name="preProductCategory.deleted" value="${preProductCategory.deleted!0}"/>
        <input type="hidden" id="checkedPcId" name="checkedPcId" />
    </div>
    <#include "../footer.ftl"/>
</form>
</div>
</#escape>
</body>
</html>