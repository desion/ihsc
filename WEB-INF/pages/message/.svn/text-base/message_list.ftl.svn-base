<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<title><@s.text name="message.listInfo" /></title>
<head>
<#include "../common_header.ftl"/>
<script type="text/javascript">
        
   function changeAction(url)
   {
      $("#mdfForm:first").attr("action",url).submit();
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
	}
	else{
	    btnDelObj.attr("disabled","disabled");
		btnMdfObj.attr("disabled","disabled");
	} 
	
    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("#" + ${message.id!""}).attr("checked", "checked");
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
	       <div class="gridview" align="center" >
	          <div>
			      <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
			         <tbody>
			            <tr>
			               <td> 	       
						       <table class="datalist" width="90%" cellspacing="0" cellpadding="0">
						           <tr>			
									    <th width="4%"><label/></th>
										<th width="96%"><label><@s.text name="message.content" /></label></th>			
								   </tr>
								   <#list messageList as msg>
									  <tr>
									      <td><input type="radio" id="${msg.id!""}" name="rad" value="${msg.exclusiveKey!""}"  /></td>
										  <td><pre>${msg.content!""}</pre></td>
									  </tr>
								   </#list>
							   </table>
			               </td>
			             </tr>
			          </tbody>
			       </table>
		       </div>    						   
			    <div class="btn_row" >
			      <#if loginUser.hasPermission("BS009_20")>
			        <button type="button" onClick="changeAction('BS009_20')" ><@s.text name="btn_add" /></button>
			      </#if>
			      <#if loginUser.hasPermission("BS009_11")>  
					<button type="button" id="btnDel" onClick="executeAction('BS009_11')"  ><@s.text name="btn_detail"  /></button>
			      </#if>
				</div>
	   
		       <form id="mdfForm" action="" method="post">
		          <input type="hidden" id="ObjId" name="message.id"  />
	              <input type="hidden" id="exclusiveKey" name="message.exclusiveKey" />
	           </form>
	     </div>
	  </div>
    <#include "../footer.ftl"/>   
  </div>
</#escape>   	  
</body>
</html>