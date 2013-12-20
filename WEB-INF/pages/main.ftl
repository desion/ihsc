<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="mainPage" /></title>
<#include "common_header.ftl"/>
<link rel="stylesheet" type="text/css" href="css/themes/base/ui.tabs.css" />
<script type="text/javascript" src="js/jquery/ui.tabs.js" ></script>
<script type="text/javascript">
    $(document).ready(function(){   
       $("#tabs").tabs();  
       $("#StartDate").datepicker();
       $("#EndDate").datepicker();         
     });
    
    
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
	          $(qText).append("<img src='images/orderup.png'></img>");
	        }else if(sortType == "desc"){
	          $(qText).append("<img src='images/orderdown.png'></img>");
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
       
    });
</script>

<style type="text/css" mce_bogus="1">  
	table th{
        white-space: nowrap;
	}
	table td{
		white-space: nowrap;
	}
	body,table{
		font-size:12px;
	}
    table{
        empty-cells:show; 
        border-collapse: collapse;
        margin:0 auto;
    }
 
    h1,h2,h3{
		font-size:12px;
		margin:0;
		padding:0;
	}
	table.tab_css_1{
		border:1px solid #cad9ea;
		color:#666;
	}
	table.tab_css_1 th {
		background-image: url("th_bg1.gif");
		background-repeat:repeat-x;
		height:30px;
	}
	table.tab_css_1 td,table.tab_css_1 th{
		border:1px solid #cad9ea;
		padding:0 1em 0;
	}
	table.tab_css_1 tr.tr_css{
		background-color:#f5fafe;
		height:30px;
	} 
</style>
</head>
<body >
	<#escape x as x?html>
	<div id="container">
	<#include "header.ftl"/> 
	<div style="position: relative;z-index:0">
		<#if loginUser.hasPermission("BS009_70")>
             <@s.action name="BS009_70" executeResult="true" ignoreContextParams="true">
             </@s.action> 
		</#if>
		<div id="view">
		    <div class="fields" cellspacing="0" cellpadding="0">
        	<form id="actionForm" action="PR002_10" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>		  
						 <table width="60%" class="field_tbl" align="center">
							<tr>
							   <td width="10%" class="lcell"><label><@s.text name="order.phoneNo" /></label></td>
							   <td width="10%"><input type="text" name="topupRecord.phoneNo" value="${topupRecord.phoneNo!""}" size="11px" maxLength="11" /></td>
							   
							   <td width="10%" class="lcell"><label><@s.text name="order.topupPhone" /></label></td>
							   <td width="10%"><input type="text" name="topupRecord.topupPhone" value="${topupRecord.topupPhone!""}" size="11px" maxLength="11" /></td>
							   
							   <td width="10%" class="lcell"><label><@s.text name="order.price" /></label></td>
			                   <td width="10%"><input type="text" name="topupRecord.price" value="${topupRecord.price!""}" size="10px" maxLength="3" /></td>
							   
							   <td width="10%" class="lcell"><label><@s.text name="order.province"  /></label></td>
			                   <td width="10%">
			                      <select name="order.province" >
			                       <@s.action name="province_only_drop" executeResult="true" ignoreContextParams="true">
			                          <@s.param name="selectedProvinceId">${topupRecord.province!"-1"}</@s.param>
			                       </@s.action>
			                       </select>
			                    </td>
			                    
							</tr>
							<tr>
								<td width="14%" class="lcell"><label><@s.text name="order.startDate" /></label></td>
		                        <td width="11%">
		                             <input id="StartDate" type="text" name="StartDate" value="${topupRecord.startDate!""}" maxLength="10" />
		                        </td>
		                        <td width="14%" class="lcell"><label><@s.text name="order.endDate" /></label></td>
		                        <td width="11%">
		                             <input id="EndDate" type="text" name="EndDate" value="${topupRecord.endDate!""}" maxLength="10" />
		                        </td>
		                        <td width="14%" class="lcell"><label><@s.text name="order.requestNo" /></label></td>
			                    <td width="11%"><input type="text" name="topupRecord.requestNo" value="${topupRecord.requestNo!""}" size="10px" maxLength="10" /></td>
								<td width="10%" class="lcell"><label><@s.text name="order.status" /></label></td>
			                    <td width="10%">
									<select name="topupRecord.status" style="width:150px;"  >
			                        		<#include "components/orderStatusDrop.ftl">
			                      	</select>
								</td>
							</tr>
						  </table>
		               </td>
		            </tr>
		         </tbody>
		      </table>  						  
		   </form>
		   <div class="btn_row">
		   	  <#if loginUser.hasPermission("PR002_20")>
                 <button type="button" id="btnAdd" onClick="changeDialogAction('PR002_31')" ><@s.text name="btn_add" /></button>
              </#if>
              <#if loginUser.hasPermission("PR002_10")>
                 <button type="button" onClick="changeSearchAction('PR002_10')"><@s.text name="btn_search" /></button>
              </#if> 
           </div>	            
          </div>     
		 <div id="tabs-1" align="center" class="gridview"  style="margin-bottom:0px; width:100%">
				<#assign pagebarAction="PR005_10">
				<#include "components/pagebar.ftl">
				<div style="margin-bottom:0px; width:100%;overflow: auto;">
					
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
															<th width=7%><input type="checkbox" id="selectAll" /></th>
														</#if>
														<th style="width:12%"><a name="sortName" value="phoneNo"><@s.text name="order.phoneNo" /></a></th>
														<th style="width:6%"><a name="sortName" value="province"><@s.text name="order.province" /></a></th>
														<th style="width:12%"><a name="sortName" value="topupPhone"><@s.text name="order.topupPhone" /></a></th>
														<th style="width:8%"><a name="sortName" value="price"><@s.text name="order.price" /></a></th>
														<th style="width:15%"><a name="sortName" value="requestNo"><@s.text name="order.requestNo" /></a></th>
														<th style="width:15%"><a name="sortName" value="requestNo"><@s.text name="order.requestNo" /></a></th>
														<th style="width:15%"><a name="sortName" value="createTime"><@s.text name="order.createTime" /></a></th>
														<th style="width:15%"><a name="sortName" value="updateTime"><@s.text name="order.updateTime" /></a></th>
														<th style="width:8%"><a name="sortName" value="status"><@s.text name="order.status" /></a></th>
														<th style="width:10%"><a name="sortName" value="source"><@s.text name="order.source" /></a></th>
														<th style="width:10%"><a name="sortName" value="source"><@s.text name="order.source" /></a></th>
														<th style="width:10%"><a name="sortName" value="operator"><@s.text name="order.operator" /></a></th>
														<th style="width:6%"><a name="sortName" value="salePrice"><@s.text name="order.salePrice" /></a></th>
														<th style="width:6%"><a name="sortName" value="inPrice"><@s.text name="order.inPrice" /></a></th>
														<th style="width:6%"><a name="sortName" value="profit"><@s.text name="order.profit" /></a></th>
													</tr>
													<#list topupOrderList as topup>
													<tr id="test${topup.systemNo!""}">
														<td><label>${topup_index+1}</label></td>
														<#if loginUser.hasPermission("PR005_37") ||loginUser.hasPermission("PR005_60")>
															<#if affirmChkList?? && affirmChkList.contains("${topup.systemNo}")>
																<td><input type="checkbox" name="affirmChkList" value="${topup.systemNo!""}" checked></td>
															<#else>
																<td><input type="checkbox" name="affirmChkList" value="${topup.systemNo!""}" ></td>
															</#if>
														</#if>
														<td><label>${topup.phoneNum!""}</label></td>
														<td><label>${topup.provinceName!""}</label></td>
														<td><label>${topup.topupPhone!""}</label></td>
														<td><label>${topup.sum!""}</label></td>
														<td><label>${topup.requestNo!""}</label></td>
														<td><label>${topup.requestNo!""}</label></td>
														<td>${topup.createTime!""}</td>
														<td>${topup.updateTime!""}</td>
														<td><label>${topup.status!""}</label></td>
														<td><label>${topup.source!""}</label></td>
														<td><label>${topup.proxy!""}</label></td>
														<td><label>${topup.operatorName!""}</label></td>
														<td><label>${topup.salePrice?if_exists?string.number}</label></td>
														<td><label>${topup.inPrice?if_exists?string.number}</label></td>
														<td><label>${topup.profit?if_exists?string.number}</label></td>
													</tr>
													</#list>
											</tbody>
						            
											</form>
										</table> 
									</td>
								</tr>
							</tbody>
						</table>
					</div> 
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
	</div>
 </div>     
 <#include "components/error_reference.ftl"/> 
</#escape>
</body>
</html>