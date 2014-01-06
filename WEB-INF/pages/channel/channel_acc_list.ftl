<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="channelAccu.listInfo" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   $(document).ready(function(){   
       $("#StartDate").datepicker();
       $("#EndDate").datepicker();         
    });     
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
   
   function changeSearchAction(url)
   {
      var form=document.getElementById("actionForm");
      form.action=url;
      form.submit();
   }
   function executeAction(url){
       var exclusiveKey=$(":radio[name=rad]:checked:eq(0)").val();
       var objId=$(":radio[name=rad]:checked:eq(0)").attr("id");
        $("#ObjId").val(objId);
        $("#exclusiveKey").val(exclusiveKey);
       changeAction(url);
   }
   
   function deleteData(url){
       var flag= confirm("<@s.text name="BSC00003" />");
       if(flag==true){
          executeAction(url);
        }
   }
   
   $(document).ready(function(){
   
   $("#pageSizeSel").change(function(){
        $("#psize").val($(this).val());
        changeAction("BS004_10");
    });
    
    
    $("#toPageSel").change(function(){
        $("#currPage").val($(this).val());
        changeAction("BS004_10");
    });
   
    var radios = $("input[type=radio][name=rad]");
    var radioSize= radios.length;
    
    var btnDelObj=$("#btnDel");
    var btnMdfObj=$("#btnMdf")
    if(radioSize>0){
        btnDelObj.removeAttr("disabled");
        btnMdfObj.removeAttr("disabled");
        var firstRad=$(":radio[name=rad]:eq(0)")
        firstRad.attr("checked",true);
    }
    else{
        btnDelObj.attr("disabled","disabled");
        btnMdfObj.attr("disabled","disabled");
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
        margin:0 auto;
    }
 
    h1,h2,h3{
		font-size:12px;
		margin:0;
		padding:0;
	}
	
</style>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/> 
 <div id="container">         
    <#include "../header.ftl"/>
     <div id="view">
        <div class="fields" cellspacing="0" cellpadding="0">
          <form id="actionForm" action="BS004_70" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>		  
						 <table width="60%" class="field_tbl" align="center">
							<tr>
							   <td width="11%" class="lcell"><label><@s.text name="channelAccu.startDate" /></label></td>
		                       <td width="14%">
		                            <input id="StartDate" type="text" name="channelAccumu.startDate" value="${channelAccumu.startDate!""}" maxLength="10"/>
		                        </td>
		                       <td width="11%" class="lcell"><label><@s.text name="channelAccu.endDate" /></label></td>
		                       <td width="14%">
		                            <input id="EndDate" type="text" name="channelAccumu.endDate" value="${channelAccumu.endDate!""}" maxLength="10" />
		                       </td>   
							   <td width="11%" class="lcell"><label><@s.text name="channelAccu.channelSName" /></label></td>
							   <td width="14%"><input type="text" name="channelAccumu.channelSName" value="${channelAccumu.channelSName!""}" size="15px" maxLength="10" /></td>
							   <td width="11%" class="lcell"><label><@s.text name="channelAccu.type" /></label></td>
			                   <td width="14%">
									<select name="channelAccumu.type" style="width:80px;"  >
			                        		<#include "../components/accumulateType.ftl">
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
              <#if loginUser.hasPermission("PR002_10")>
                 <button type="button" onClick="changeSearchAction('BS004_70')"><@s.text name="btn_search" /></button>
              </#if> 
           </div>	            
      </div>
     <div  class="gridview">
         <#assign pagebarAction="BS004_70">
         <#include "../components/pagebar.ftl">
         <div>
	         <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	            <tbody>
	                <tr>
	                  <td>
				          <table class="datalist" width="100%" cellspacing="0" cellpadding="0" >
				            <tr>            
				                <th width="3%"><label></label></th>
				                <th width="10%"><label><@s.text name="channelAccu.channelName" /></label></th>   
				                <th width="5%"><label><@s.text name="channelAccu.channelSName" /></label></th>
				                <th width="5%"><label><@s.text name="channelAccu.balanceSnap" /></label></th>
				                <th width="5%"><label><@s.text name="channelAccu.amount" /></label></th> 
				                <th width="5%"><label><@s.text name="channelAccu.balance" /></label></th>
				                <th width="5%"><label><@s.text name="channelAccu.type" /></label></th>
				                <th width="8%"><label><@s.text name="channelAccu.userName" /></label></th>
				                <th width="10%"><label><@s.text name="channelAccu.optime" /></label></th> 
				                <th width="20%"><label><@s.text name="channelAccu.comments" /></label></th>
				            </tr>
				            <#list channelAccumuList as channelAccu>
				            <tr>
				                <td><input type="radio" id="${channelAccu.channelId!""}" name="rad" /></td>
				                <td>${channelAccu.channelName!""}</td>
				                <td>${channelAccu.channelSName!""}</td>
				                <td>${channelAccu.balanceSnap?if_exists?string.number}</td>
				                <td>${channelAccu.amount?if_exists?string.number}</td>
				                <td>${channelAccu.blance?if_exists?string.number}</td>
				                <td>${channelAccu.type!""}</td>
				                <td>${channelAccu.userName!""}</td>
				                <td>${channelAccu.opTime!""}</td>
				                <td>${channelAccu.commnets!""}</td>
				            </tr>
				           </#list>
				        </table>
	                 </td>
	               </tr>
	            </tbody>
	         </table>
         </div>    			        
         <div class="btn_row">
	           <#if loginUser.hasPermission("BS004_20")>
	             <button type="button" onClick="changeAction('BS004_20')" ><@s.text name="btn_add" /></button>
	           </#if> 
	          <#if loginUser.hasPermission("BS004_30")>
	            <button type="button" id="btnMdf" onClick="executeAction('BS004_30')" ><@s.text name="btn_modify" /></button>
	          </#if> 
	          <#if loginUser.hasPermission("BS004_40")>
	            <button type="button" id="btnDel" onClick="deleteData('BS004_40')"  ><@s.text name="btn_delete_forever"  /></button>
	          </#if>   
        </div>
        <form id="mdfForm" action="BS004_70" method="post">
	        <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
	        <input type="hidden"  id="psize"   name="pageSize" value="${pageSize!0}" />
        </form>
     </div> 
    </div>
  </div>       
 </#escape>
</body>
</html>