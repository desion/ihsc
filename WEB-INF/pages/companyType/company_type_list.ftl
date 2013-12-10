<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="companyType.listInfo" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   
  
   
   function changeAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
      form.submit();
      
   } 
   
     function deleteData(url){
       var flag= confirm('<@s.text name="BSC00003" />');
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
    var btnMdfObj=$("#btnMdf")
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
        $("#" + ${companyType.id!-1}).attr("checked", "checked");
    </#if>
   });
   
   
 
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
 <div id="container">
    <#include "../header.ftl"/>  
       <div id="view" >       
        <div class="gridview" >
          <div>
	         <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>           
			           <table class="datalist" width="100%" cellspacing="0" cellpadding="0">
				            <tr>
				                <th width="4%" ><label></label></th>
				                <th width="48%" ><label><@s.text name="companyType.name" /></label></th>
				                <th width="48%"><label><@s.text name="companyType.description" /></label></th>
				            </tr>
				            <#list comTypeList as comType>
					            <tr>
					                <td><input type="radio" id="${comType.id}" name="rad" value="${comType.exclusiveKey}"  /></td>
					                <td><label>${comType.name!""}</label></td>
					                <td><label>${comType.description!""}</label></td>
					            </tr>
			                </#list>
			           </table>
	                </td>
	              </tr>
	             </tbody>
	           </table>
		    </div>       		           
	        <div class="btn_row">
	            <#if loginUser.hasPermission("BS001_20")>
	              <button type="button" onClick="changeAction('BS001_20')" ><@s.text name="btn_add" /></button>
	            </#if>  
	            <#if loginUser.hasPermission("BS001_30")>  
	              <button type="button" id="btnMdf" onClick="executeAction('BS001_30')" ><@s.text name="btn_modify" /></button>
	            </#if>  
	            <#if loginUser.hasPermission("BS001_40")>  
	              <button type="button" id="btnDel" onClick="deleteData('BS001_40')"  ><@s.text name="btn_delete_forever"  /></button>
	           </#if>  
	        </div>
	         <form id="mdfForm" action="BS001_20" method="post">
	            <input type="hidden" id="ObjId" name="companyType.id" />
	            <input type="hidden" id="exclusiveKey" name="companyType.exclusiveKey" />
	        </form>
        </div>
     </div>        
  <#include "../footer.ftl"/>   
</div> 
</#escape>        
</body>
</html>