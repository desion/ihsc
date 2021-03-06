<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="faultPart.create" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var flag= confirm("<@s.text name="BSC00006" />");
      if(flag==true){
        var form=document.getElementById("mdfForm");
        form.action=url;
        form.submit();  
      }
   } 
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container" >
  <#include "../header.ftl"/>
    <div id="view">
       <div class="fields">
          <form id="mdfForm" action="BS011_21" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>           
			             <table width="60%" class="field_tbl" align="center">
	                        <tr>
                               <td width="20%" class="lcell" ><label><@s.text name="faultPart.faultMachineType" /></label></td>
                               <td width="80%">
                                  <select  name="faultPart.faultMachineType"  value="${faultPart.faultMachineType!""}" style="width:150px"  >
                                    <#include "../components/faultMachineTypeDrop.ftl">
                                  </select>
                                  <label  class="needed"><@s.text name="select_needed" /></label>
                               </td>
                            </tr>
				            <tr>
				                <td width="20%" class="lcell"><label><@s.text name="faultPart.name" /></label></td>
				                <td width="80%">
				                   <input type="text" name="faultPart.name"  value="${faultPart.name!""}"  size="60" maxLength="40"/>
				                   <label  class="needed"><@s.text name="input_needed" /></label>
				                </td>
				             </tr>
			             </table>
	                 </td>
	              </tr>
	           </tbody>
	        </table> 			             
          </form>
	      <div class="btn_row">
	         <#if loginUser.hasPermission("BS011_21")>
	           <button  type="button"  onClick="changeAction('BS011_21')" ><@s.text name="btn_add" /></button>
	         </#if>
	      </div>          
       </div>
    </div> 
  <#include "../footer.ftl"/>   
 </div>
</#escape>        
</body>
</html>