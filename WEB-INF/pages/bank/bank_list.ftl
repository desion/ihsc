<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="bank.listInfo" /></title>
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
       var exclusiveKey=$(":radio[name=rad]:checked:eq(0)").val();
       var objId=$(":radio[name=rad]:checked:eq(0)").attr("id");
       $("#ObjId").val(objId);
       $("#exclusiveKey").val(exclusiveKey);
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
        $("#exclusiveKey").val(firstRad.val());
    }
    else{
        btnDelObj.attr("disabled","disabled");
        btnMdfObj.attr("disabled","disabled");
    } 
    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("#" + ${bank.id!-1}).attr("checked", "checked");
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
				                <th width="6%"><label></label></th>
				                <th width="50%"><label><@s.text name="bank.name" /></label></th>
				                <th width="44%"><label><@s.text name="bank.shortName" /></label></th>
      
				             </tr>
				             <#list bankList as bnk>
				                <tr>
				                   <td><input type="radio" id="${bnk.id!""}" name="rad" value="${bnk.exclusiveKey!""}"  /></td>
				                   <td><label>${bnk.name!""}</label></td>
				                   <td><label>${bnk.shortName!""}</label></td>
				                  
				                </tr>
				             </#list>
				          </table>
	                  </td>
	                </tr>
	             </tbody>
	           </table>			          
        </div>
         <div class="btn_row">
	        <#if loginUser.hasPermission("BS012_20")>
	            <button type="button" onClick="changeAction('BS012_20')" ><@s.text name="btn_add" /></button>
	        </#if>
	        <#if loginUser.hasPermission("BS012_30")>
	            <button type="button" id="btnMdf" onClick="executeAction('BS012_30')" ><@s.text name="btn_modify" /></button>
	        </#if>
	        <#if loginUser.hasPermission("BS012_40")>
	            <button type="button" id="btnDel" onClick="deleteData('BS012_40')"  ><@s.text name="btn_delete_forever"  /></button>
	        </#if>
         </div>
         <form id="mdfForm" action="BS012_20" method="post">
            <input type="hidden" id="ObjId" name="bank.id" />
            <input type="hidden" id="exclusiveKey" name="bank.exclusiveKey" />
         </form>
       </div>
     </div> 
   <#include "../footer.ftl"/>   
 </div>
</#escape>        
</body>
</html>