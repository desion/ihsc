<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="channel.listInfo" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
        
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
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/> 
 <div id="container">         
    <#include "../header.ftl"/>
    <div id="view">
     <div  class="gridview">
     	 <label><@s.text name="channel.tips" /></label>
         <#assign pagebarAction="BS004_10">
         <#include "../components/pagebar.ftl">
         <div>
	         <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	            <tbody>
	                <tr>
	                  <td>
				          <table class="datalist" width="100%" cellspacing="0" cellpadding="0" >
				            <tr>            
				                <th width="3%"><label></label></th>
				                <th width="20%"><label><@s.text name="channel.channelName" /></label></th>   
				                <th width="20%"><label><@s.text name="channel.sname" /></label></th>
				                <th width="16%"><label><@s.text name="channel.balance" /></label></th> 
				                <th width="16%"><label><@s.text name="channel.deposited" /></label></th>
				                <th width="10%"><label><@s.text name="channel.charge" /></label></th>
				                <th width="10%"><label><@s.text name="channel.stat" /></label></th>
				                <th width="5%"><label><@s.text name="channel.topup" /></label></th>
				                <th width="5%"><label><@s.text name="channel.update" /></label></th> 
				                <th width="10%"><label><@s.text name="channel.charge.record" /></label></th> 
				            </tr>
				            <#list channelList as channel>
				            <tr>
				                <td><input type="radio" id="${channel.id!""}" name="rad" /></td>
				                <td><label>${channel.channelName!""}</label></td>
				                <td><label>${channel.sname!""}</label></td>
				                <td>
				                <#if channel.balance <= channel.alertThreshold>
				                	<font color="blue">${channel.balance?if_exists?string.number}</font>
				                <#elseif channel.balance <= channel.suspendThreshold>
				                	<font color="red">${channel.balance?if_exists?string.number}</font>
				                <#else>
				                	<font >${channel.balance?if_exists?string.number}</font>
				                </#if>
				                </td>
				                <td><label>${channel.deposited?if_exists?string.number}</label></td>
				                <td><a href="BS004_25?channel.id=${channel.id!""}"><@s.text name="channel.charge" /></a></td>
				                <td>
				                	<a href="#"><@s.text name="channel.stat" /></a>|
				                	<a href="#"><@s.text name="channel.chart" /></a>
				                </td>
				                <td><a href="BS005_10?channel.id=${channel.id!""}"><@s.text name="channel.detail.info" /></a></td>
				                <td><a href="#"><@s.text name="btn_modify" /></a></td>
				                <td><a href="BS004_70?channel.id=${channel.id!""}"><@s.text name="channel.charge.record" /></a></td>
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
        <form id="mdfForm" action="BS004_20" method="post">
	        <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
	        <input type="hidden"  id="psize"   name="pageSize" value="${pageSize!0}" />
        </form>
     </div> 
    </div>
  </div>       
 </#escape>
</body>
</html>