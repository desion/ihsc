<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="FA001_10_title" /></title>
<#include "../common_header.ftl"/>
</head>
<script type="text/javascript">

    function changeAction(url,type)
    {
        var form=document.getElementById("faultListForm");
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
   
    function executeAction(url){
        var objId=$(":radio[name=fau_radio]:checked").attr("id");
        $("#faultId").val(objId);
        changeAction(url);
    }
    
    function exportMngId(url) {
        <#if loginUser.hasPermission("FA001_36") || loginUser.hasPermission("FA001_60")>
            var test = $(":radio[name=fau_radio]:checked").parent().next().next().eq(0).text();
        <#else>
            var test = $(":radio[name=fau_radio]:checked").parent().next().eq(0).text();
        </#if>
        $("#managementId").val(test);
        var objId=$(":radio[name=fau_radio]:checked").attr("id");
        $("#faultId").val(objId);
        var listSize = $(":checkbox[name='finishChkList']:checked").length;
        if(listSize<=0){
            alert("<@s.text name="BSE02134" />")
        }else{
           var form=document.getElementById("faultListForm");
           form.action=url;
           form.submit();
        }
    }
    
    function selectedFinish(url){
      var listSize = $(":checkbox[name='finishChkList']:checked").length;
      if(listSize<=0){
         alert("<@s.text name="BSE02132" />")
      }else{
        var flag= confirm("<@s.text name="BSC00008" />");
        if(flag==true){
          executeAction(url);
        }
      }
    }
    function checkOthers(self){
      var flag=true;
      $("input[type=checkbox][name=finishChkList]").each(function(){
           if($(this)!=self && $(this).attr("checked")==false){
              flag=false;
              return false;
           }
      });
      return flag;
    } 
    
  function setSelectAllCbxChecked(){
      var flag=true;
      var listSize = $(":checkbox[name='finishChkList']:checked").length;
      if(listSize>0){
	      $("input[type=checkbox][name=finishChkList]").each(function(){
	           if($(this).attr("checked")==false){
	              flag=false;
	              return false;
	           }
	      }); 
      }else{
        flag=false;
      }
      if(flag==true){
         $("#selectAll").attr("checked","checked");
      }
      
   }
       
       
    $(document).ready(function(){
      setSelectAllCbxChecked();
      
      $("#selectAll").click(function(){
            if($(this).attr("checked")==true){
               $("input[type=checkbox][name=finishChkList]").attr("checked","checked");
            }else{
                $("input[type=checkbox][name=finishChkList]").removeAttr("checked");
            }
        });
        
       $("input[type=checkbox][name=finishChkList]").each(function(){
          $(this).click(function(){
              if($(this).attr("checked")==false){
                 $("#selectAll").removeAttr("checked");
              }else{
                 if(checkOthers(this)){
                    $("#selectAll").attr("checked","checked")
                 }
              }
           });
         });
         
        var sort = $("#sort").val();
        var sortType = $("#sortType").val();
        if(sort != "" && sortType != ""){
            var qText = "a[name='sortName'][value='"+sort+"']";
            if(sortType == "asc"){
              $(qText).append("<img src='../../../images/orderup.png'></img>");
            }else if(sortType == "desc"){
              $(qText).append("<img src='../../../images/orderdown.png'></img>");
            } 
        }
        
        $("a[name='sortName']").each(function(){        
          var sortTypeIn=$("#sortType").val();
            $(this).click(function(){
               if(sortTypeIn=="desc"){
                   $("#sortType").val("asc");
               }else if(sortTypeIn == "asc"){
                   $("#sortType").val("desc");
               }else{
                   $("#sortType").val("asc");
               }
                $("#sort").val($(this).attr("value"));
                changeAction("FA001_10");
            });

        });
        
        $("#pageSizeSel").change(function(){
            $("#psize").val($(this).val());
            changeAction("FA001_10");
        });
        
        
        $("#toPageSel").change(function(){
            $("#currPage").val($(this).val());
            changeAction("FA001_10");
        });
       
        $("tr").mouseover(function(){  
            $(this).addClass("over");}).mouseout(function(){ 
        $(this).removeClass("over");})  

   
        var radios = $("input[type=radio][name=fau_radio]");
        var radioSize= radios.length;
    
        var btnDetailObj=$("#btnDetail");
        var btnExportObj=$("#btnExport");
        var btnFinishObj=$("#btnFinish");

        if(radioSize>0){
            btnDetailObj.removeAttr("disabled");
            btnExportObj.removeAttr("disabled");
            btnFinishObj.removeAttr("disabled");
            var firstRad=$(":radio[name=fau_radio]:eq(0)")
            firstRad.attr("checked",true);
            $("#faultId").val(firstRad.attr("id"));
        }else{
            btnDetailObj.attr("disabled","disabled");
            btnExportObj.attr("disabled","disabled");
            btnFinishObj.attr("disabled","disabled");
        }
        
        <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
          $(":radio[name='fau_radio'][id="+${fault.id!-1}+"]").attr("checked","checked");
        </#if>
    });
</script>

<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container">
    <#include "../header.ftl"/>
    <div id="view" >
	    <div class="gridview">
	        <#assign pagebarAction="FA001_10">
	        <#include "../components/pagebar.ftl">
	        <div>
		        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		           <tbody>
		              <tr>
		                <td>  	        
				            <table class="datalist" width="100%" cellspacing="0" cellpadding="0">
				                <form id="faultListForm" method="post">
				                <tbody>
				                    <tr>
				                        <th width=4%>&nbsp;</th>
				                        <#if loginUser.hasPermission("FA001_36") || loginUser.hasPermission("FA001_60")>
				                           <th width=7%><input type="checkbox" id="selectAll" /><label><@s.text name="select_all" /></th>
				                        </#if>
				                        <th width=16%><a name="sortName" value="managementId"><@s.text name="fault.managementId" /></a></th>
				                        <th width=8%><a name="sortName" value="faultType"><@s.text name="fault.faultTypeName" /></a></th>
				                        <th width=9%><a name="sortName" value="occurDate"><@s.text name="fault.occurDate" /></a></th>
				                        <th width=8%><a name="sortName" value="productCategoryName"><@s.text name="fault.productCategoryName" /></a></th>
				                        <th width=12%><a name="sortName" value="model"><@s.text name="fault.model" /></a></th>
				                        <th width=8%><a name="sortName" value="manufactureNo"><@s.text name="fault.manufactureNo" /></a></th>
				                    
				                        <th width=13%><a name="sortName" value="subComInstallPlace"><@s.text name="fault.sub_com_install_place" /></a></th>
				                        
				                        <th width=7%><a name="sortName" value="repairState"><@s.text name="fault.repairState" /></a></th>
				                        <#if loginUser.hasPermission("FA001_32")>
				                            <th width=8%><@s.text name="fault.deleted" /></th>
				                        </#if>
				                    </tr>
				                    <#list faultList as fau>
				                    <tr>
				                        <td><input type="radio" id="${fau.id!""}" name="fau_radio"></td>
				                        <#if loginUser.hasPermission("FA001_36") || loginUser.hasPermission("FA001_60")>
				                        <!-- 
				                            <#if fau.repairState==1 || fau.deleted==1>
                                             <td><label/></td>
                                           <#else>
                                                <#if finishChkList?? && finishChkList.contains("${fau.id}")>
                                                    <td><input type="checkbox" name="finishChkList" value="${fau.id!""}" checked></td>
                                                <#else>
                                                    <td><input type="checkbox" name="finishChkList" value="${fau.id!""}" ></td>
                                                </#if>
                                            </#if> 
				                         -->
				                                <#if finishChkList?? && finishChkList.contains("${fau.id}")>
				                                    <td><input type="checkbox" name="finishChkList" value="${fau.id!""}" checked></td>
				                                <#else>
				                                    <td><input type="checkbox" name="finishChkList" value="${fau.id!""}" ></td>
				                                </#if>
				                           
				                        </#if>
				                        <td><a href="FA001_11?fault.id=${fau.id!"-1"}&amp;faultActionFrom=BS010_00">${fau.managementId!""}</a></td>
				                        <td><label>${fau.faultTypeName!""}</label></td>
				                        <td><label>${fau.occurDate!""}</label></td>
				                        <td><label>${fau.productCategoryName!""}</label></td>
				                        <td><label>${fau.model!""}</label></td>
				                        <td><a href="PR005_11?install.id=${fau.installationId!"-1"}">${fau.manufactureNo!""}</a></td>
				                        <td><label>${fau.subCompany!""}${fau.installPlace!""}</label></td>
				                        
				                        <td>
				                        <#if fau.repairState == 0>
				                            <label class="needed"><@s.text name="not_finish" /></label>
				                        <#else>
				                            <label>&nbsp;</label>
				                        </#if>
				                        </td>
				                        <#if loginUser.hasPermission("FA001_32")>
				                            <td>
				                            <#if fau.deleted == 0>
				                                <label>&nbsp;</label>
				                            <#else>
				                                <label class="needed"><@s.text name="deleted" /></label>
				                            </#if>
				                            </td>
				                        </#if>
				                    </tr>
				                    </#list>
				                </tbody>
				            </table>
		                 </td>
		              </tr>
		           </tbody>
		        </table>  				            
	        </div>
	        
	        <br>
	        
	        <div class="btn_row">
	            <#if loginUser.hasPermission("FA001_60")>
	                <button type="button" id="btnExport" onClick="exportMngId('FA001_60')" ><@s.text name="btn_export" /></button>
	            </#if>
	            <#if loginUser.hasPermission("FA001_11")>
	               <button type="button" id="btnDetail" onClick="executeAction('FA001_11')" ><@s.text name="btn_detail" /></button>
	             </#if>  
	            <#if loginUser.hasPermission("FA001_36")>
	                <button type="button" style="width:130px;" id="btnFinish" onClick="selectedFinish('FA001_36')" ><@s.text name="button.finishSelect" /></button>
	            </#if>
	        </div>
	        
	            <input type="hidden" id="faultId" name="fault.id" />
	            <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
	            <input type="hidden" id="psize" name="pageSize" value="${pageSize!0}" />
	            <input type="hidden" name="fault.managementId" value="${fault.managementId!""}"/ >
	            <input type="hidden" name="fault.faultType" value="${fault.faultType!""}"/ >
	            <input type="hidden" name="fault.appearance" value="${fault.appearance!""}"/ >
	            <input type="hidden" name="fault.reason" value="${fault.reason!""}"/ >
	            <input type="hidden" name="fault.strategy" value="${fault.strategy!""}"/ >
	            <input type="hidden" name="fault.errorCode" value="${fault.errorCode!""}"/ >
	            <input type="hidden" name="fault.faultPart" value="${fault.faultPart!""}"/ >
	            <input type="hidden" name="fault.faultPartType" value="${fault.faultPartType!""}"/ >
	            <input type="hidden" name="fault.isState" value="${fault.isState!""}"/ >
	            <input type="hidden" name="fault.supportType" value="${fault.supportType!""}"/ >
	            <input type="hidden" name="fault.occurDate" value="${fault.occurDate!""}"/ >
	            <input type="hidden" name="fault.reportDate" value="${fault.reportDate!""}"/ >
	            <input type="hidden" name="fault.finishDate" value="${fault.finishDate!""}"/ >
	            <input type="hidden" name="fault.operation" value="${fault.operation!""}"/ >
	            <input type="hidden" name="fault.repairCompanyId" value="${fault.repairCompanyId!""}"/ >
	            <input type="hidden" name="fault.operatorName" value="${fault.operatorName!""}"/ >
	            <input type="hidden" name="fault.supporterId" value="${fault.supporterId!""}"/ >
	            <input type="hidden" name="fault.handleDetail" value="${fault.handleDetail!""}"/ >
	            <input type="hidden" name="fault.result" value="${fault.result!""}"/ >
	            <input type="hidden" name="fault.note" value="${fault.note!""}"/ >
	            <input type="hidden" name="fault.repairState" value="${fault.repairState!""}"/ >
	            <input type="hidden" name="faultActionFrom" value="FA001_10"/ >
	            <input id="sort" type="hidden" name="fault.sort" value="${fault.sort!""}"/ >
	            <input id="sortType" type="hidden" name="fault.sortType" value="${fault.sortType!""}"/ >
	            <input type="hidden" id="managementId" name="managementId" value="${managementId!""}"/>
	        </form>
      </div>
   </div> 
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>