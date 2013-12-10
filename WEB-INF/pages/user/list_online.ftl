<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title><@s.text name="user.listOnline" /></title>
  <#include "../common_header.ftl"/>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container">        
 <#include "../header.ftl"/>
  <div id="view">
   <div class="gridview">
      <div>
         <@s.text name="online_user_no"/>：${onLineUserList?size} 
      </div> 
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>        
				   <table class="datalist" width="100%" cellspacing="0" cellpadding="0" >
						<tr>
							<th width="20%"><label><@s.text name="user.name" /></label></th>
							<th width="40%"><label><@s.text name="user.lastLoginIp" /></label></th>
							<th width="40%"><label><@s.text name="user.lastLoginTime" /></label></th>
						</tr>
						<#list onLineUserList as user>
						   <tr>
							   <td><label>${user.name!""}</label></td>
							   <td><label>${user.lastLoginIP!""}</label></td>
							   <td><label>${user.lastLoginTime!""}</label></td>
						   </tr>
						</#list>
				     </table>
                 </td>
              </tr>
            </tbody>
          </table>  				     
      </div>
   </div>  
   <#include "../footer.ftl"/> 	 
</div>
</#escape>      
</body>
</html>