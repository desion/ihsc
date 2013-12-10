<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="user.changePasword" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var flag= confirm("<@s.text name="BSC00007" />");
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
		 <form id="mdfForm" action="BS007_33" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>   		 
			            <table width="50%" class="field_tbl" align="center">
			                <tr>
			                   <td width="20%" class="lcell"><label><@s.text name="user.oldPassword"  /></label></td>
			                   <td width="80%">
			                     <input type="password" name="user.oldPassword" size="40" maxLength="20" /> 
			                     <label class="needed"><@s.text name="input_needed" /></label>
			                   </td>
			                </tr>
			                
			                <tr>
			                   <td class="lcell"><label><@s.text name="user.newPassword" /></label></td>
			                   <td>
			                     <input type="password" name="user.newPassword" size="40" maxLength="20" /> 
			                     <label class="needed"><@s.text name="input_needed" /></label>
			                   </td>
			                </tr>
			                
			                <tr>
			                   <td class="lcell"><label><@s.text name="user.repeatPassword" /></label></td>
			                   <td>
			                     <input type="password" name="user.repeatPassword" size="40" maxLength="20" />
			                     <label class="needed"><@s.text name="input_needed" /></label>
			                   </td>
			                </tr>
			             </table>
	                 </td>
	              </tr>
	            </tbody>
	         </table>  			             
          </form>
          <div class="btn_row">
            <#if loginUser.hasPermission("BS007_33")>
              <button type="button" onClick="changeAction('BS007_33')" ><@s.text name="btn_modify" /></button>
            </#if> 
         </div>
       </div>
      </div>  
   <#include "../footer.ftl"/> 
 </div>
</#escape>       
</body>
</html>