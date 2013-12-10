<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="user.modify" /></title>
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
    <div id="view" >
       <div class="fields">
		  <form id="mdfForm" action="BS007_31" method="post">
		      <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		          <tbody>
		             <tr>
		               <td>  		   
						    <table width="70%" class="field_tbl" align="center">
							    <tr>
								    <td width="20%" class="lcell"><label><@s.text name="user.name" /></label></td>
								    <td width="80%">
								       <input type="text" name="user.name"  value="${user.name!""}"  size="60" maxLength="20" />
								       <label  class="needed"><@s.text name="input_needed" /></label>
								    </td>
								</tr>
								
							    <tr>
								   <td class="lcell"><label><@s.text name="user.fullName" /></label></td>
								   <td>
								      <input type="text" name="user.fullName"  value="${user.fullName!""}" size="60"  maxLength="20"/>
								      <label  class="needed"><@s.text name="input_needed" /></label>
								   </td>
							    </tr>
							    
							    <tr>
				                    <td class="lcell"><label><@s.text name="user.newPassword" /></label></td>
				                    <td>
				                      <input type="password" name="user.newPassword"    size="60" maxLength="20"/>
				                    </td>
				                </tr>
				                <tr>
				                    <td class="lcell"><label><@s.text name="user.repeatPassword" /></label></td>
				                    <td>
				                       <input type="password" name="user.repeatPassword"   size="60" maxLength="20"/>
				                    </td>
				                </tr>
				                
							    <tr>
							        <td class="lcell"><label><@s.text name="user.sex" /></label></td>
							        <td>
							          <select  name="user.sex"  value="${user.sex!""}" width="200" >
						                <#include "../components/sexDrop.ftl">
						              </select>
							        </td>
							    </tr>
							    <tr>
							        <td class="lcell"><label><@s.text name="user.mobilePhone" /></label></td>
							        <td><input type="text" name="user.mobilePhone"  value="${user.mobilePhone!""}" size="60"  maxLength="60"/></td>
							    </tr>
							    <tr>
							        <td class="lcell"><label><@s.text name="user.email" /></label></td>
							        <td><input type="text" name="user.email"  value="${user.email!""}"  size="60" maxLength="80"/></td>
							    </tr>
							    <tr>
							        <td class="lcell"><label><@s.text name="user.department" /></label></td>
							        <td><input type="text" name="user.department"  value="${user.department!""}" size="60" maxLength="40"/></td>
							    </tr>
							    
							    
							     <tr>
								    <td class="lcell"><@s.text name="user.groupId"  /></td>
								    <td>
								      <select id="groupDrop" name="user.groupId"  value="${user.groupId!-1}" >
									       <@s.action name="group_drop" executeResult="true" ignoreContextParams="true">
									          <@s.param name="selectedGroupId">${user.groupId!-1}</@s.param>
									       </@s.action>
								       </select>
								       <label  class="needed"><@s.text name="select_needed" /></label>
					   			    </td>
					            </tr>

						    </table>
                       </td>
                   </tr>
               </tbody>
             </table>
                 <input type="hidden" name="user.id" value="${user.id!-1}" />
                  <input type="hidden" name="user.exclusiveKey" value="${user.exclusiveKey!-1}" />
                 <input type="hidden" name="currPage" value="${currPage!0}" />
                 <input type="hidden" name="pageSize" value="${pageSize!0}" />            					    
		  </form>
		  <div class="btn_row">
	        <#if loginUser.hasPermission("BS007_31")>
	            <button type="button" onClick="changeAction('BS007_31')" ><@s.text name="btn_modify" /></button>
	        </#if>
          </div>
          
	   </div>
	 </div>	
 </div>
</#escape>  		
</body>
</html>