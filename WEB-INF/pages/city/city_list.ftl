<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="city.listInfo" /></title>
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
    
    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("#" + ${city.id!-1}).attr("checked", "checked");
    </#if>
    
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
         <#assign pagebarAction="BS004_10">
         <#include "../components/pagebar.ftl">
         <div>
	         <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	            <tbody>
	                <tr>
	                  <td>
				          <table class="datalist" width="100%" cellspacing="0" cellpadding="0" >
				            <tr>            
				                <th width="4%"><label></label></th>
				                <th width="20%"><label><@s.text name="city.countryName" /></label></th>   
				                <th width="20%"><label><@s.text name="city.provinceName" /></label></th>
				                <th width="16%"><label><@s.text name="city.name" /></label></th> 
				                <th width="16%"><label><@s.text name="city.shortName" /></label></th> 
				                <th width="12%"><label><@s.text name="city.postCode" /></label></th>        
				                <th width="12%"><label><@s.text name="city.telCode" /></label></th>                 
				            </tr>
				            <#list cityList as ct>
				            <tr>
				                <td><input type="radio" id="${ct.id!""}" name="rad" value="${ct.exclusiveKey!""}"  /></td>
				                <td><label>${ct.countryName!""}</label></td>
				                <td><label>${ct.provinceName!""}</label></td>
				                <td><label>${ct.name!""}</label></td>
				                <td><label>${ct.shortName!""}</label></td>
				                <td><label>${ct.postCode!""}</label></td>
				                <td><label>${ct.telCode!""}</label></td>
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
	        <input type="hidden" id="ObjId" name="city.id" />
	        <input type="hidden" id="exclusiveKey" name="city.exclusiveKey" />
	        <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
	        <input type="hidden"  id="psize"   name="pageSize" value="${pageSize!0}" />
	        <input type="hidden"  name="city.countryId" value="${city.countryId!""}"/>
            <input type="hidden"  name="city.provinceId" value="${city.provinceId!""}" />
            <input type="hidden"  name="city.name" value="${city.name!""}" />
        </form>
     </div> 
    </div>
     <#include "../footer.ftl"/>
  </div>       
 </#escape>
</body>
</html>