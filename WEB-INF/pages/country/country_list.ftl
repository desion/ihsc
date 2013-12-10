<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="country.listInfo" /></title>
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
	   var objId=$(":radio[name=rad]:checked:eq(0)").attr("nid");
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
	}
	else{
		btnDelObj.attr("disabled","disabled");
		btnMdfObj.attr("disabled","disabled");
	} 
	
    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("input[type=radio][nid=" + ${country.id!-1}+"]").attr("checked", "checked");
    </#if>
   });
   
</script>
</head>
<body>
<#escape x as x!""?html>
<#include "../components/error_reference.ftl"/>
  <div id="container">
    <#include "../header.ftl"/>
       <div id="view" >  
		   <div class="gridview">
		     <div>
		       <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		         <tbody>
		            <tr>
		              <td>    		   
						 <table class="datalist" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<th width="4%"></th>
								<th width="24%"><label><@s.text name="country.name" /></label></th>
								<th width="24%"><label><@s.text name="country.nameEn" /></label></th>
								<th width="24%"><label><@s.text name="country.shortName" /></label></th>
								<th width="24%"><label><@s.text name="country.shortNameEn" /></label></th>
							</tr>
							<#list countryList as cty>
							  <tr>
								<td ><input type="radio" nid="${cty.id}" name="rad" value="${cty.exclusiveKey}"  /></td>
								<td ><label>${cty.name}</label></td>
								<td ><label>${cty.nameEn}</label></td>
								<td ><label>${cty.shortName}</label></td>
								<td ><label>${cty.shortNameEn}</label></td>
							  </tr>
						    </#list>
						</table>
	                </td>
	              </tr>
	             </tbody>
	           </table>
	         </div>  						
		    <div class="btn_row"/>
			   <#if loginUser.hasPermission("BS002_20")>
				<button type="button" onClick="changeAction('BS002_20')" ><@s.text name="btn_add" /></button>
			   </#if>
			   <#if loginUser.hasPermission("BS002_30")> 
				<button type="button" id="btnMdf" onClick="executeAction('BS002_30')" ><@s.text name="btn_modify" /></button>
			   </#if>
			   <#if loginUser.hasPermission("BS002_40")> 
				<button type="button" id="btnDel" onClick="deleteData('BS002_40')"  ><@s.text name="btn_delete_forever"  /></button>
			   </#if> 
		  </div>
		  <form id="mdfForm" action="BS002_20" method="post">
			     <input type="hidden" id="ObjId" name="country.id" />
				 <input type="hidden" id="exclusiveKey" name="country.exclusiveKey" />
		  </form>
	    </div>
      </div>
    <#include "../footer.ftl"/>   
  </div>
</#escape>
</body>
</html>