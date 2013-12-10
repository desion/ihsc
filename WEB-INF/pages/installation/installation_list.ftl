<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="PR005_10_title" /></title>
<#include "../common_header.ftl"/>
</head>
<script type="text/javascript">

    function changeAction(url,type)
    {
        var form=document.getElementById("instlistForm");
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
        var objId=$(":radio[name=inst_radio]:checked").attr("id");
        $("#installId").val(objId);
        changeAction(url);
    }
    
    function exportManuNo(url) {
     <#if loginUser.hasPermission("PR005_37") || loginUser.hasPermission("PR005_60")>
         var test = $(":radio[name=inst_radio]:checked").parent().next().next().next().next().next().next().eq(0).text();
     <#else>
         var test = $(":radio[name=inst_radio]:checked").parent().next().next().next().next().next().eq(0).text();
     </#if>
     $("#manufactureNo").val(test);
      var listSize = $(":checkbox[name='affirmChkList']:checked").length;
      if(listSize<=0){
         alert("<@s.text name="BSE01731" />")
      }else{
	       var form=document.getElementById("instlistForm");
	       form.action=url;
	       form.submit();
    
      }
    }
    
    
    function selectedAffirm(url){
      var listSize = $(":checkbox[name='affirmChkList']:checked").length;
      if(listSize<=0){
         alert("<@s.text name="BSE01722" />")
      }else{
       var flag= confirm("<@s.text name="BSC00008" />");
       if(flag==true){
          changeAction(url);
        }
      }
    }
   
   function checkOthers(self){
      var flag=true;
      $("input[type=checkbox][name=affirmChkList]").each(function(){
           if($(this)!=self && $(this).attr("checked")==false){
              flag=false;
              return false;
           }
      });
      return flag;
    } 
    
  function setSelectAllCbxChecked(){
      var flag=true;
      var listSize = $(":checkbox[name='affirmChkList']:checked").length;
      if(listSize>0){
	      $("input[type=checkbox][name=affirmChkList]").each(function(){
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
               $("input[type=checkbox][name=affirmChkList]").attr("checked","checked");
            }else{
                $("input[type=checkbox][name=affirmChkList]").removeAttr("checked");
            }
        });
        
       $("input[type=checkbox][name=affirmChkList]").each(function(){
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
	            changeAction("PR005_10");
	        });

        });
        
        $("#pageSizeSel").change(function(){
            $("#psize").val($(this).val());
            changeAction("PR005_10");
        });
        
        
        $("#toPageSel").change(function(){
            $("#currPage").val($(this).val());
            changeAction("PR005_10");
        });
       
        $("tr").mouseover(function(){  
            $(this).addClass("over");}).mouseout(function(){ 
        $(this).removeClass("over");})  

        var radios = $("input[type=radio][name=inst_radio]");
        var radioSize= radios.length;
    
        var btnDetailObj=$("#btnDetail");
        var btnExportObj=$("#btnExport");
        var btnAffirmObj=$("#btnAffirm");
        if(radioSize>0){
            btnDetailObj.removeAttr("disabled");
            btnExportObj.removeAttr("disabled");
            btnAffirmObj.removeAttr("disabled");
            var firstRad=$(":radio[name=inst_radio]:eq(0)")
            firstRad.attr("checked",true);
            $("#installId").val(firstRad.attr("id"));
        }else{
            btnDetailObj.attr("disabled","disabled");
            btnExportObj.attr("disabled","disabled");
            btnAffirmObj.attr("disabled","disabled");
            
        } 
        
       <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
          $(":radio[name='inst_radio'][id="+${install.id!-1}+"]").attr("checked","checked");
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
       <#assign pagebarAction="PR005_10">
        <#include "../components/pagebar.ftl">
          <div>
	           <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	              <tbody>
	                <tr>
	                  <td>  
				            <table class="datalist" width="100%" cellspacing="0" cellpadding="0">
				            <form id="instlistForm" method="post">
				                <tbody>
				                    <tr>
				                        <th width=5%>&nbsp;</th>
				                        <#if loginUser.hasPermission("PR005_37") ||loginUser.hasPermission("PR005_60")>
				                           <th width=7%><input type="checkbox" id="selectAll" /><label><@s.text name="select_all" /></label></th>
				                        </#if>
				                        <th width=12%><a name="sortName" value="model"><@s.text name="inst.model" /></a></th>
				                        <th width=12%><a name="sortName" value="customerId"><@s.text name="inst.customerName" /></a></th>
                                        <th width=11%><a name="sortName" value="branchCompanyName"><@s.text name="inst.branchCompanyName" /></a></th>
				                        <th width=9%><a name="sortName" value="installDate"><@s.text name="inst.installDate" /></a></th>
				                        <th width=9%><a name="sortName" value="manufactureNo"><@s.text name="inst.manufactureNo" /></a></th>
				                        <th width=11%><a name="sortName" value="nowRepairCompanyId"><@s.text name="inst.nowRepairCompanyName" /></a></th>
				                        <th width=8%><a name="sortName" value="useStatusId"><@s.text name="inst.useStatus" /></a></th>
				                        <th width=8%><a name="sortName" value="affirmFlag"><@s.text name="inst.affirm" /></a></th>
				                        <#if loginUser.hasPermission("PR005_34")>
				                            <th width=8%><@s.text name="inst.deleted" /></th>
				                        </#if>
				                    </tr>
				                    <#list installationList as inst>
				                    <tr id="test${inst.id!""}">
				                        <td><input type="radio" id="${inst.id!""}" value="${inst.productCategoryId!""}" name="inst_radio"></td>
				                        <#if loginUser.hasPermission("PR005_37") ||loginUser.hasPermission("PR005_60")>
				                        <!-- 
					                          2009/08/25 comment
					                          <#if inst.affirmFlag==1 || inst.deleted==1>
	                                            <td><label/></td>
	                                          <#else>
	                                            <td><input type="checkbox" name="affirmChkList" value="${inst.id!""}" ></td>
	                                          </#if>  
				                         -->
				                          <#if affirmChkList?? && affirmChkList.contains("${inst.id}")>
				                            <td><input type="checkbox" name="affirmChkList" value="${inst.id!""}" checked></td>
				                          <#else>
				                            <td><input type="checkbox" name="affirmChkList" value="${inst.id!""}" ></td>
				                          </#if>
				                        </#if>
				                        <td><label>${inst.model!""}</label></td>
				                        <td><label>${inst.customerName!""}</label></td>
				                        <td><label>${inst.branchCompanyName!""}</label></td>
				                        <td><label>${inst.installDate!""}</label></td>
				                        <td><label>${inst.manufactureNo!""}</label></td>
				                        <td><label>${inst.nowRepairCompanyName!""}</label></td>
				                        <td><label>${inst.useStatus!""}</label></td>
				                        <td>
				                        <#if inst.affirmFlag == 0>
				                            <label class="needed"><@s.text name="not_affirm" /></label>
				                        <#else>
				                            <label><@s.text name="affirm" /></label>
				                        </#if>
				                        </td>
				                        <#if loginUser.hasPermission("PR005_34")>
				                            <td>
				                            <#if inst.deleted == 0>
				                                <label>&nbsp;</label>
				                            <#else>
				                                <label class="needed"><@s.text name="deleted" /></label>
				                            </#if>
				                            </td>
				                        </#if>
				                    </tr>
				                    </#list>
				                </tbody>
						            <input type="hidden" id="installId" name="install.id" />
						            <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
						            <input type="hidden" id="psize" name="pageSize" value="${pageSize!0}" />
						            <input type="hidden" name="install.productId" value="${install.productId!""}"/>
						            <input type="hidden" name="install.manufactureNo" value="${install.manufactureNo!""}"/ >
						            <input type="hidden" name="install.customerId" value="${install.customerId!""}"/ >
						            <input type="hidden" name="install.saleContractCompId" value="${install.saleContractCompId!""}"/ >
						            <input type="hidden" name="install.installCompId" value="${install.installCompId!""}"/ >
						            <input type="hidden" name="install.firstRepairCompanyId" value="${install.firstRepairCompanyId!""}"/ >
						            <input type="hidden" name="install.nowRepairCompanyId" value="${install.nowRepairCompanyId!""}"/ >
						            <input type="hidden" name="install.indentureNo" value="${install.indentureNo!""}"/ >
						            <input type="hidden" name="install.fobDate" value="${install.fobDate!""}"/ >
						            <input type="hidden" name="install.installDate" value="${install.installDate!""}"/ >
						            <input type="hidden" name="install.openDate" value="${install.openDate!""}"/ >
						            <input type="hidden" name="install.acceptDate" value="${install.acceptDate!""}"/ >
						            <input type="hidden" name="install.installer" value="${install.installer!""}"/ >
						            <input type="hidden" name="install.installerId" value="${install.installerId!""}"/ >
						            <input type="hidden" name="install.guaranteeStartDate" value="${install.guaranteeStartDate!""}"/ >
						            <input type="hidden" name="install.guaranteeEndDate" value="${install.guaranteeEndDate!""}"/ >
						            <input type="hidden" name="install.guaranteePeriod" value="${install.guaranteePeriod!""}"/ >
						            <input type="hidden" name="install.branchCompanyName" value="${install.branchCompanyName!""}"/ >
						            <input type="hidden" name="install.installPlace" value="${install.installPlace!""}"/ >
						            <input type="hidden" name="install.userCompanyBankId" value="${install.userCompanyBankId!""}"/ >
						            <input type="hidden" name="install.customerProvinceId" value="${install.customerProvinceId!""}"/ >
						            <input type="hidden" name="install.customerCityId" value="${install.customerCityId!""}"/ >   
						            <input type="hidden" name="install.instPlaceTypeId" value="${install.instPlaceTypeId!""}"/ >
						            <input type="hidden" name="install.useStatusId" value="${install.useStatusId!""}"/ >
						            <input type="hidden" name="install.purpose" value="${install.purpose!""}"/ >
						            <input type="hidden" name="install.brmEpVer" value="${install.brmEpVer!""}"/ >
						            <input type="hidden" name="install.bvEpVer" value="${install.bvEpVer!""}"/ >
						            <input type="hidden" name="install.keyNo" value="${install.keyNo!""}"/ >
						            <input type="hidden" name="install.note" value="${install.note!""}"/ >
						            <input type="hidden" name="install.affirmFlag" value="${install.affirmFlag!""}"/>
						            <input type="hidden" name="install.manufactureNoStart" value="${install.manufactureNoStart!""}"/>
						            <input type="hidden" name="install.manufactureNoEnd" value="${install.manufactureNoEnd!""}"/>
						            <input id="productCateId" type="hidden" name="install.productCategoryId" value="${install.productCategoryId!""}"/>
						            <input id="sort" type="hidden" name="install.sort" value="${install.sort!""}"/ >
						            <input id="sortType" type="hidden" name="install.sortType" value="${install.sortType!""}"/ >
				                    <input type="hidden" id="manufactureNo" name="manufactureNo" value="${manufactureNo!""}"/>
						        </form>
				            </table> 
	                     </td>
	                  </tr>
	               </tbody>
	             </table>
	        </div>       			               
	        <div class="btn_row">
	            <#if loginUser.hasPermission("PR005_60")>
	                <button type="button" id="btnExport" onClick="exportManuNo('PR005_60')" ><@s.text name="btn_export" /></button>
	            </#if>
	            <#if loginUser.hasPermission("PR005_11")>
	               <button type="button" id="btnDetail" onClick="executeAction('PR005_11')" ><@s.text name="btn_detail" /></button>
	            </#if>
	            <#if loginUser.hasPermission("PR005_37")>
	               <button type="button" id="btnAffirm" onClick="selectedAffirm('PR005_37')" ><@s.text name="btn_select_affirm" /></button>
	            </#if>
	        </div>   
         </div>
      </div>   
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>