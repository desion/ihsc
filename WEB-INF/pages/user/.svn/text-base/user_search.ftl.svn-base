<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="user.search" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">

 function changeAction(url)
   {
      var form=document.getElementById("actionForm");
      form.action=url;
      form.submit();
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
		  <form id="actionForm" action="BS007_14" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>		  
						 <table width="60%" class="field_tbl" align="center">
							<tr>
							   <td width="20%" class="lcell"><label><@s.text name="user.name" /></label></td>
							   <td width="80%"><input type="text" name="user.name" size="60" maxLength="20" /></td>
							</tr>
							<tr>
			                   <td class="lcell"><label><@s.text name="user.familyName" /></label></td>
			                   <td><input type="text" name="user.familyName" size="60" maxLength="20" /></td>
			                </tr>
			                <tr>
			                   <td class="lcell"><label><@s.text name="user.givenName" /></label></td>
			                   <td><input type="text" name="user.givenName"  size="60" maxLength="20" /></td>
			                </tr>
			                <tr>
			                   <td class="lcell"><label><@s.text name="user.companyID"  /></label></td>
			                   <td>
			                      <select name="user.companyID" >
			                       <@s.action name="company_drop" executeResult="true" ignoreContextParams="true">
			                          <@s.param name="selectedComId">${user.companyID!"-1"}</@s.param>
			                       </@s.action>
			                       </select>
			                    </td>
			                </tr>
						  </table>
		               </td>
		            </tr>
		         </tbody>
		      </table>  						  
		   </form>	
		   <div class="btn_row">
              <#if loginUser.hasPermission("BS007_14")>
                 <button type="button" onClick="changeAction('BS007_14')"><@s.text name="btn_search" /></button>
              </#if> 
           </div>
		</div>
	 </div>	
   <#include "../footer.ftl"/> 
 </div>
</#escape>               
</body>
</html>