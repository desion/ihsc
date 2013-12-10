<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="faultPart.listInfo" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
      form.submit();
      
   } 
    function deleteData(url){
       var flag= confirm("<@s.text name="BSC00003" />");
       if(flag==true){
          executeAction(url);
        }
       
   }
   function executeAction(url){
       var objId=$(":radio[name=rad]:checked:eq(0)").attr("id");
       $("#ObjId").val(objId);
       changeAction(url);
   }  
   
  $(document).ready(function(){
    var radios = $("input[type=radio][name=rad]");
    var radioSize= radios.length;   
    var btnDelObj=$("#btnDel");
    var btnMdfObj=$("#btnMdf");
    if(radioSize>0){
        btnDelObj.removeAttr("disabled");
        btnMdfObj.removeAttr("disabled");
        var firstRad=$(":radio[name=rad]:eq(0)")
        firstRad.attr("checked",true);
        $("#ObjId").val(firstRad.attr("id"));
    }
    else{
        btnDelObj.attr("disabled","disabled");
        btnMdfObj.attr("disabled","disabled");
    } 
    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("#" + ${faultPart.id!-1}).attr("checked", "checked");
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
      <div class="gridview">
       <div>
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                 <td>          
				         <table  class="datalist" width="100%"  cellspacing="0" cellpadding="0">
				             <tr>            
				                <th width="8%"><label></label></th>
				                <th width="46%"><label><@s.text name="faultPart.name" /></label></th>
				                <th width="46%"><label><@s.text name="faultPart.faultMachineType" /></label></th>
      
				             </tr>
				             <#list faultPartList as fp>
				                <tr>
				                   <td><input type="radio" id="${fp.id!""}" name="rad" /></td>
				                   <td><label>${fp.name!""}</label></td>
				                   <#if fp.faultMachineType?? && fp.faultMachineType=='STANDARD'>
				                      <td><label><@s.text name="faultPart.standard"/></label></td>   
				                   <#else>
				                      <td><label>${fp.faultMachineType!""}</label></td>       
				                   </#if>
				                </tr>
				             </#list>
				          </table>
	                  </td>
	                </tr>
	             </tbody>
	           </table>			          
        </div>
         <div class="btn_row">
	        <#if loginUser.hasPermission("BS011_20")>
	            <button type="button" onClick="changeAction('BS011_20')" ><@s.text name="btn_add" /></button>
	        </#if>
	        <#if loginUser.hasPermission("BS011_30")>
	            <button type="button" id="btnMdf" onClick="executeAction('BS011_30')" ><@s.text name="btn_modify" /></button>
	        </#if>
	        <#if loginUser.hasPermission("BS011_40")>
	            <button type="button" id="btnDel" onClick="deleteData('BS011_40')"  ><@s.text name="btn_delete_forever"  /></button>
	        </#if>
         </div>
         <form id="mdfForm" action="BS011_20" method="post">
            <input type="hidden" id="ObjId" name="faultPart.id" />
         </form>
       </div>
     </div> 
   <#include "../footer.ftl"/>   
 </div>
</#escape>        
</body>
</html>