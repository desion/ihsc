<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="mainPage" /></title>
<#include "common_header.ftl"/>
<link rel="stylesheet" type="text/css" href="css/themes/base/ui.tabs.css" />
<script type="text/javascript" src="js/jquery/ui.tabs.js" ></script>
<script type="text/javascript">
    $(document).ready(function(){   
       $("#StartDate").datepicker();
       $("#EndDate").datepicker();         
     });
     
    
   function changeSearchAction(url)
   {
      var form=document.getElementById("actionForm");
      form.action=url;
      form.submit();
   }
   
   function changeMdfAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
      form.submit();
   }
   
   function updateStatus(type){
   		selectedAffirm();
   		if(type == 1){
   			//recharge
   			var flag= confirm("<@s.text name="BSC00008" />");
       		if(flag==true){
          		changeAction(url);
        	}
   		}else if(type == 2){
   			//success
   			var flag= confirm("<@s.text name="BSC00009" />");
   			if(flag==true){
          		changeAction(url);
        	}
   		}else if(type == 3){
   			//fail
   			var flag= confirm("<@s.text name="BSC00010" />");
   			if(flag==true){
          		changeAction(url);
        	}
   		}
   }
   
   function changeStatus(type){
   		var listSize = $(":checkbox[name='affirmChkList']:checked").length;
      	if(listSize<=0){
         	alert("<@s.text name="BSE01722" />")
         	return false;
      	}else if(listSize > 5){
      	 	alert("<@s.text name="BSE01721" />")
      	 	return false;
     	}
   		$("#statusType").val(type);
		$.ajax({
			  url: 'BS010_03',
			  type: 'POST',
			  data: $("#mdfForm").serialize(),
			  dataType: 'text',
			  timeout: 1000,
			  cache:false,
			  success: function(data, textStatus){
			  	var ret = $(data).find("ret").text();
			  	if(ret == 1){
			  		changeSearchAction('BS010_00');
			  	}else{
			  		alert("<@s.text name="BSE01710" />")
			  	}
			  }
		  });
   }
   
    function changeAction(url,type)
    {
        var form=document.getElementById("mdfForm");
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
    
    function exportData(url) {
     <#if loginUser.hasPermission("BS010_02") || loginUser.hasPermission("BS010_00")>
         var form=document.getElementById("actionForm");
         form.action=url;
         form.submit();
     <#else>
         alert("<@s.text name="BSE01731" />")
     </#if>
    }
    
    
    function selectedAffirm(){
      var listSize = $(":checkbox[name='affirmChkList']:checked").length;
      if(listSize<=0){
         alert("<@s.text name="BSE01722" />")
         return false;
      }else if(listSize > 5){
      	 alert("<@s.text name="BSE01721" />")
      	 return false;
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
            changeAction("BS010_00");
        });
        
        
        $("#toPageSel").change(function(){
            $("#currPage").val($(this).val());
            changeAction("BS010_00");
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
            //btnDetailObj.removeAttr("disabled");
            //btnExportObj.removeAttr("disabled");
            btnAffirmObj.removeAttr("disabled");
            var firstRad=$(":radio[name=inst_radio]:eq(0)")
            firstRad.attr("checked",true);
            $("#installId").val(firstRad.attr("id"));
        }else{
            //btnDetailObj.attr("disabled","disabled");
            //btnExportObj.attr("disabled","disabled");
            btnAffirmObj.attr("disabled","disabled");
            
        } 
       
    });
    
    setInterval(function() {Push();},60000);
    
    function Push(){
    	  var currTotal = $("#currTotal").val();
		  $.ajax({
			  url: 'BS010_01',
			  type: 'POST',
			  data: $("#actionForm").serialize(),
			  dataType: 'text',
			  timeout: 1000,
			  cache:false,
			  success: function(data, textStatus){
			  	var newNum = $(data).find("totalNum").text();
			  	if(newNum > currTotal){
			  		var diff = newNum - currTotal;
			  		var message = diff + "<@s.text name="note.message" />";
			  		$("#newOrderNote").text(message);
			  		$("#newOrderNote").attr("style","");
			  	}
			  }
		  });
    }
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
	
	a.notes {
		background: #fefded;
		color: #F48C12;
		border: 1px solid #f9f2a7;
		display: block;
		height: 20px;
		line-height: 23px;
		text-align: center;
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
		<a class="notes" id="newOrderNote" style="display:none;" href="javascript:void(0);" onClick="changeSearchAction('BS010_00')"></a>
		<div id="view">
		    <div class="fields" cellspacing="0" cellpadding="0">
        	    <form id="actionForm" action="BS010_00" method="post">
	               <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	                <tbody>
	                  <tr>
	                    <td>		  
						 <table width="60%" class="field_tbl" align="center">
							<tr>
							   <td width="10%" class="lcell"><label><@s.text name="order.phoneNo" /></label></td>
							   <td width="10%"><input type="text" name="topupRecord.phoneNum" value="${topupRecord.phoneNum!""}" size="11px" maxLength="11" /></td>
							   
							   <td width="10%" class="lcell"><label><@s.text name="order.topupPhone" /></label></td>
							   <td width="10%"><input type="text" name="topupRecord.topupPhone" value="${topupRecord.topupPhone!""}" size="11px" maxLength="11" /></td>
							   
							   <td width="10%" class="lcell"><label><@s.text name="order.price" /></label></td>
			                   <td width="10%"><input type="text" name="topupRecord.sum" value="${topupRecord.sum!""}" size="10px" maxLength="3" /></td>
							   
							   <td width="10%" class="lcell"><label><@s.text name="order.province"  /></label></td>
			                   <td width="10%">
			                      <select name="topupRecord.province" >
			                       <@s.action name="province_only_drop" executeResult="true" ignoreContextParams="true">
			                          <@s.param name="selectedProvinceId">${topupRecord.province!"-1"}</@s.param>
			                       </@s.action>
			                       </select>
			                    </td>
			                    
							</tr>
							<tr>
								<td width="14%" class="lcell"><label><@s.text name="order.startDate" /></label></td>
		                        <td width="11%">
		                             <input id="StartDate" type="text" name="topupRecord.startDate" value="${topupRecord.startDate!""}" maxLength="10" />
		                        </td>
		                        <td width="14%" class="lcell"><label><@s.text name="order.endDate" /></label></td>
		                        <td width="11%">
		                             <input id="EndDate" type="text" name="topupRecord.endDate" value="${topupRecord.endDate!""}" maxLength="10" />
		                        </td>
		                        <td width="14%" class="lcell"><label><@s.text name="order.requestNo" /></label></td>
			                    <td width="11%"><input type="text" name="topupRecord.requestNo" value="${topupRecord.requestNo!""}" size="13px" maxLength="13" /></td>
			                    
								<td width="10%" class="lcell"><label><@s.text name="order.status" /></label></td>
			                    <td width="10%">
									<select name="topupRecord.status" style="width:150px;"  >
			                        		<#include "components/orderStatusDrop.ftl">
			                      	</select>
								</td>
							</tr>
							<tr>
								<td width="10%" class="lcell"><label><@s.text name="order.operator" /></label></td>
			                    <td width="10%">
									<select name="topupRecord.operator" style="width:150px;"  >
			                        		<#include "components/operatorDrop.ftl">
			                      	</select>
								</td>
								
								<td width="10%" class="lcell"><label><@s.text name="order.proxy"  /></label></td>
								<td width="10%">
			                      <select name="topupRecord.proxy" style="width:150px;">
			                       <@s.action name="channel_drop" executeResult="true" ignoreContextParams="true">
			                          <@s.param name="selectedChannelId">${topupRecord.proxy!"-1"}</@s.param>
			                       </@s.action>
			                       </select>
			                    </td>
			                    
			                    <td width="10%" class="lcell"><label><@s.text name="order.notify" /></label></td>
			                    <td width="10%">
									<select name="topupRecord.notify" style="width:150px;"  >
			                        		<#include "components/noticeDrop.ftl">
			                      	</select>
								</td>
								
								<td width="10%" class="lcell"><label><@s.text name="order.source" /></label></td>
			                    <td width="10%">
									<select name="topupRecord.source" style="width:150px;"  >
			                        		<#include "components/sourceDrop.ftl">
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
		   	 <#if loginUser.hasPermission("BS010_02")>
				 <button type="button" id="btnExport" onClick="exportData('BS010_02')" ><@s.text name="btn_export" /></button>
			  </#if>
              <#if loginUser.hasPermission("BS010_00")>
                 <button type="button" onClick="changeSearchAction('BS010_00')"><@s.text name="btn_search" /></button>
              </#if> 
           </div>	            
        </div>  
        <form id="mdfForm" action="BS010_00" method="post">   
		    <div id="tabs-1" align="center" class="gridview"  style="margin-bottom:0px; width:100%">
				<#assign pagebarAction="BS010_00">
				<#include "components/pagebar.ftl">
				<div style="margin-bottom:0px; width:100%;overflow: auto;">
						<table cellspacing="0" cellpadding="0" border="0" class="table_line2">
							<tbody>
								<tr>
									<td>  
										<table class="datalist" width="100%" cellspacing="0" cellpadding="0">
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
														<th style="width:15%"><a name="sortName" value="createTime"><@s.text name="order.createTime" /></a></th>
														<th style="width:15%"><a name="sortName" value="updateTime"><@s.text name="order.updateTime" /></a></th>
														<th style="width:8%"><a name="sortName" value="status"><@s.text name="order.status" /></a></th>
														<th style="width:8%"><a name="sortName" value="notify"><@s.text name="order.notify" /></a></th>
														<th style="width:10%"><a name="sortName" value="source"><@s.text name="order.source" /></a></th>
														<th style="width:10%"><a name="sortName" value="proxy"><@s.text name="order.proxy" /></a></th>
														<th style="width:10%"><a name="sortName" value="operator"><@s.text name="order.operator" /></a></th>
														<th style="width:6%"><a name="sortName" value="salePrice"><@s.text name="order.salePrice" /></a></th>
														<th style="width:6%"><a name="sortName" value="inPrice"><@s.text name="order.inPrice" /></a></th>
														<th style="width:6%"><a name="sortName" value="profit"><@s.text name="order.profit" /></a></th>
													</tr>
													<#list topupOrderList as topup>
													<tr id="test${topup.systemNo!""}">
														<td><a href="javascript:void(0);" >${topup_index+1}</a></td>
														<#if loginUser.hasPermission("PR005_37") ||loginUser.hasPermission("PR005_60")>
															<#if affirmChkList?? && affirmChkList.contains("${topup.systemNo}")>
																<td><input type="checkbox" name="affirmChkList" value="${topup.systemNo!""}" checked></td>
															<#else>
																<td><input type="checkbox" name="affirmChkList" value="${topup.systemNo!""}" ></td>
															</#if>
														</#if>
														<td>${topup.phoneNum!""}</td>
														<td>${topup.provinceName!""}</td>
														<td>${topup.topupPhone!""}</td>
														<td>${topup.sum!""}</td>
														<td>${topup.requestNo!""}</td>
														<td>${topup.createTime!""}</td>
														<td>${topup.updateTime!""}</td>
														<#if topup.status == 0>
															<td>${topup.statusName!""}</td>
														<#elseif topup.status == 1>
															<td><font color="#8B668B">${topup.statusName!""}</font></td>
														<#elseif topup.status == 2>
															<td><font color="green">${topup.statusName!""}</font></td>
														<#else>
															<td><font color="red">${topup.statusName!""}</font></td>
														</#if>
														<#if topup.notify == 0>
															<td><font color="red">${topup.noticeName!""}</font></td>
														<#else>
															<td><font color="green">${topup.noticeName!""}</font></td>
														</#if>
														<td>${topup.sourceName!""}</td>
														<td>${topup.proxy!""}</td>
														<td>${topup.operatorName!""}</td>
														<td>${topup.salePrice?if_exists?string.number}</td>
														<td>${topup.inPrice?if_exists?string.number}</td>
														<td>${topup.profit?if_exists?string.number}</td>
													</tr>
													</#list>
											</tbody>
										</table> 
									</td>
								</tr>
							</tbody>
						</table>
					</div> 
				</div> 
				<input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
	    		<input type="hidden"  id="psize"   name="pageSize" value="${pageSize!0}" /> 
	    		<input type="hidden"  id="currTotal"   name="currentTotalNum" value="${currentTotalNum!0}" />
	    		<input type="hidden"  id="statusType"   name="statusType" value="${statusType!0}" />    
	        </form>
	    		  	
				<div class="btn_row">
					<#if loginUser.hasPermission("BS010_00")>
						<button type="button" id="btn_update_success" onClick="changeStatus(2)" ><@s.text name="btn_update_success" /></button>
					</#if>
					<#if loginUser.hasPermission("BS010_00")>
						<button type="button" id="btn_update_fail" onClick="changeStatus(3)" ><@s.text name="btn_update_fail" /></button>
					</#if>
					<#if loginUser.hasPermission("PR005_37")>
						<button type="button" id="btn_update_retopup" onClick="changeStatus(1)" ><@s.text name="btn_update_retopup" /></button>
					</#if>
				</div>           
			</div>   
		</div>
	</div>
 </div>
 <div id="dialog" title="Order Detail">
 </div>     
 <#include "components/error_reference.ftl"/> 
</#escape>
</body>
</html>