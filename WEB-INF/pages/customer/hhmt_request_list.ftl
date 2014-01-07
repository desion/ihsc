<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="customer.listInfo" /></title>
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
         <#assign pagebarAction="BS002_10">
         <#include "../components/pagebar.ftl">
         <div>
	         <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	            <tbody>
	                <tr>
	                  <td>
				          <table class="datalist" width="100%" cellspacing="0" cellpadding="0" >
				            <tr>            
				                <th width="3%"><label></label></th>
				                <th width="10%"><label><@s.text name="customer.customerName" /></label></th>   
				                <th width="10%"><label><@s.text name="customer.sname" /></label></th>
				                <th width="5%"><label><@s.text name="customer.balance" /></label></th> 
				                <th width="10%"><label><@s.text name="customer.userName" /></label></th>
				                <th width="5%"><label><@s.text name="customer.totalCharge" /></label></th>
				                <th width="5%"><label><@s.text name="customer.totalCost" /></label></th>
				                <th width="5%"><label><@s.text name="customer.lastCharge" /></label></th>
				                <th width="10%"><label><@s.text name="customer.lastChargeTime" /></label></th> 
				                <th width="5%"><label><@s.text name="customer.status" /></label></th> 
				            </tr>
				            <#list customerList as customer>
				            <tr>
				                <td><input type="radio" id="${customer.id!""}" name="rad" /></td>
				                <td><label>${customer.customerName!""}</label></td>
				                <td><label>${customer.sname!""}</label></td>
				                <td>
				                <#if customer.balance == 0>
				                	<font color="red">${customer.balance?if_exists?string.number}</font>
				                <#else>
				                	${customer.balance?if_exists?string.number}
				                </#if>
				                </td>
				                <td><label>${customer.userName!""}</label></td>
				                <td>${customer.totalCharge?if_exists?string.number}</td>
				                <td>${customer.totalCharge - customer.balance}</td>
				                <td>${customer.lastCharge?if_exists?string.number}</td>
				                <td>${customer.lastChargeTime!""}</td>
				                <td>${customer.status!""}</td>
				            </tr>
				           </#list>
				        </table>
	                 </td>
	               </tr>
	            </tbody>
	         </table>
         </div>    			        
         <div class="btn_row">
	           <#if loginUser.hasPermission("BS002_20")>
	             <button type="button" onClick="changeAction('BS002_20')" ><@s.text name="btn_add" /></button>
	           </#if> 
	          <#if loginUser.hasPermission("BS004_30")>
	            <button type="button" id="btnMdf" onClick="executeAction('BS004_30')" ><@s.text name="btn_modify" /></button>
	          </#if> 
	          <#if loginUser.hasPermission("BS004_40")>
	            <button type="button" id="btnDel" onClick="deleteData('BS004_40')"  ><@s.text name="btn_delete_forever"  /></button>
	          </#if>   
        </div>
        <form id="mdfForm" action="BS002_10" method="post">
	        <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
	        <input type="hidden"  id="psize"   name="pageSize" value="${pageSize!0}" />
        </form>
     </div> 
    </div>
  </div>       
 </#escape>
</body>
</html>