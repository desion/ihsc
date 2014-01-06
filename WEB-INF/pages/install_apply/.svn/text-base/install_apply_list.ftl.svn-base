<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="installation_apply_list" /></title>
<#include "../common_header.ftl"/>
</head>
<script>
function changeAction(url,type)
   {
      var form=document.getElementById("instApplyForm");
      var currentPage=document.getElementById("currPage");
     if(type!='undefined')
     {
          switch(type)
          {
            case "first":
                 currentPage.value=1;
                 break;
            case "pre":
                 currentPage.value=${currPage-1};
                 break;
            case "next":
                 currentPage.value=${currPage+1};
                 break;
            case "last":
                 currentPage.value=${totalPages};
          }
    }
    
      form.action=url;
      form.submit();
      
   }
   
   function deleteAction(url){
        var flag= confirm("<@s.text name="BSC00003"/>");
        if(flag==true){
            executeAction(url);
        }
    }
   
   function executeAction(url){
        var objId=$(":radio[name=inst_apply_radio]:checked").attr("id");
        var objExclusiveKey=$(":radio[name=inst_apply_radio]:checked").attr("exclusiveKey");
        $("#installApplyId").val(objId);
        $("#exclusiveKey").val(objExclusiveKey);
        changeAction(url);
    }
   
    $(document).ready(function(){
   
	    $("#pageSizeSel").change(function(){
	        $("#psize").val($(this).val());
	        changeAction("PR004_10");
	    });
    
    
	    $("#toPageSel").change(function(){
	        $("#currPage").val($(this).val());
	        changeAction("PR004_10");
	    });

	    $("tr").mouseover(function(){  
	        $(this).addClass("over");}).mouseout(function(){ 
	    $(this).removeClass("over");})  

	    var radios = $("input[type=radio][name=inst_apply_radio]");
	    var radioSize= radios.length;
    
	    var btnDetailObj=$("#btnDetail");
	    var btnDeleteObj=$("#btnDelete");
	    var btnbtnModifyObj=$("#btnModify");

	    if(radioSize>0){
	        btnDetailObj.removeAttr("disabled");
	        btnDeleteObj.removeAttr("disabled");
	        btnbtnModifyObj.removeAttr("disabled");
		    var firstRad=$(":radio[name=inst_apply_radio]:eq(0)");
	        firstRad.attr("checked",true);
	    }
	    else{
	        btnDetailObj.attr("disabled","disabled");
	        btnDeleteObj.attr("disabled","disabled");
	        btnbtnModifyObj.attr("disabled","disabled");
	    } 
	    
	    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
          $(":radio[name='inst_apply_radio'][id="+${instApply.id!-1}+"]").attr("checked","checked");
       </#if>
    });
</script>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container">
  <#include "../header.ftl"/>
   <div id="view"> 
      <div class="gridview">
        <#assign pagebarAction="PR004_10">
        <#include "../components/pagebar.ftl">
           <div>
		       <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		          <tbody>
		            <tr>
		              <td>      
						  <table class="datalist" width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
						      <tr>
						        <th width="4%">&nbsp;</th>
						        <th width="25%"><@s.text name="repairCompanyName" /></th>
						        <th width="20%"><@s.text name="manufactureNo" /></th>
						        <th width="23%"><@s.text name="applyTypeName" /></th>
						        <th width="15%"><@s.text name="applyerName" /></th>
						        <th width="13%"><@s.text name="applyDate" /></th>
						      </tr>
						      <#list instApplyList as list>
						      <tr>
						        <td><input type="radio" name="inst_apply_radio" id="${list.id!""}" exclusiveKey="${list.exclusiveKey!""}"></td>
						        <td><label>${list.nowRepairCompanyName!""}</label></td>
						        <td><label>${list.manufactureNo!""}</label></td>
						        <td><label>${list.typeName!""}</label></td>
						        <td><label>${list.applyerName!""}</label></td>
						        <td><label>${list.applyDate!""}</label></td>
						      </tr>
						      </#list>
						   </table>
		                 </td>
		               </tr>
		            </tbody>
		         </table> 
	         </div>   				    
		    <div class="btn_row">
		    <#if loginUser.hasPermission("PR004_11")>
                <button id="btnDetail" type="button" onClick="executeAction('PR004_11')" ><@s.text name="btn_detail" /></button>
		    </#if>
		    <#if loginUser.hasPermission("PR004_30")>
                <button id="btnModify" type="button" onClick="executeAction('PR004_30')" ><@s.text name="btn_modify" /></button>
            </#if>
            <#if loginUser.hasPermission("PR004_40")>
                <button id="btnDelete" type="button" onClick="deleteAction('PR004_40')" ><@s.text name="btn_delete_forever" /></button>
		    </#if>
		    </div>
		    <form id="instApplyForm" method="post">
		      <input type="hidden" id="installApplyId" name="instApply.id" />
		      <input type="hidden" id="exclusiveKey" name="instApply.exclusiveKey" />
		      <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
		      <input type="hidden" id="psize"   name="pageSize" value="${pageSize!0}" />
		    </form>
        </div>
    </div>
  <#include "../footer.ftl"/>   
</div>
</#escape>
</body>
</html>