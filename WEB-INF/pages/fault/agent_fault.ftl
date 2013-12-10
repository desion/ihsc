<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="FA001_70_title" /></title>
<#include "../common_header.ftl"/>  
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container">
    <#include "../header.ftl"/>
    <div id="view" >
        <div class="fields">
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>         
		            <table width="70%" align="center" class="field_tbl" >
		                <tbody>
		                    <tr>
		                        <td width="40%" class="lcell" align="center"><label><@s.text name="all_agent" /></label></td>
		                        <td width="60%">
		                          <#if loginUser.hasPermission("FA001_10")>
		                            <@s.a href="FA001_10"><@s.text name="all_label" />&nbsp;<@s.text name="fault_list" /></@s.a>
		                         <#else>
		                            <@s.text name="all_label" />&nbsp;<@s.text name="fault_list" />
		                         </#if>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td width="40%" class="lcell" align="center"><label><@s.text name="finish_label" /></label></td>
		                        <td width="60%">
		                         <#if loginUser.hasPermission("FA001_10")>
		                            <@s.a href="FA001_10?fault.repairState=1"><@s.text name="finish" />&nbsp;<@s.text name="fault_list" /></@s.a>
		                         <#else>
		                             <@s.text name="finish" />&nbsp;<@s.text name="fault_list" />
		                         </#if>   
		                        </td>
		                    </tr>
		                    <tr>
		                        <td width="40%" class="lcell" align="center"><label><@s.text name="not_finish_label" /></label></td>
		                        <td width="60%">
		                           <#if loginUser.hasPermission("FA001_10")>
		                              <@s.a href="FA001_10?fault.repairState=0"><@s.text name="not_finish" />&nbsp;<@s.text name="fault_list" /></@s.a>
		                           <#else>
		                              <@s.text name="not_finish" />&nbsp;<@s.text name="fault_list" />
		                           </#if>  
		                        </td>
		                    </tr>
		                </tbody>
		            </table>
                </td>
              </tr>
            </tbody>
           </table>              
            <br>
	       <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	          <tbody>
	             <tr>
	               <td>              
			            <table width="70%" align="center" class="field_tbl" >
			                <tbody>
			                    <tr>
			                        <td width="40%" class="lcell" align="center"><label><@s.text name="person_finish_label" /></label></td>
			                        <td width="60%">
			                         <#if loginUser.hasPermission("FA001_10")>
			                            <@s.a href="FA001_10?fault.supporterId=${loginUser.id!-1}&fault.repairState=1"><@s.text name="person_finish" />&nbsp;<@s.text name="fault_list" /></@s.a>
			                         <#else>
			                            <@s.text name="person_finish" />&nbsp;<@s.text name="fault_list" />
			                         </#if>   
			                        </td>
			                    </tr>
			                    <tr>
			                        <td width="40%" class="lcell" align="center"><label><@s.text name="person_not_finish_label" /></label></td>
			                        <td width="60%">
			                          <#if loginUser.hasPermission("FA001_10")>
			                            <@s.a href="FA001_10?fault.supporterId=${loginUser.id!-1}&fault.repairState=0"><@s.text name="person_not_finish" />&nbsp;<@s.text name="fault_list" /></@s.a>
			                          <#else>
			                            <@s.text name="person_not_finish" />&nbsp;<@s.text name="fault_list" />
			                          </#if>
			                        </td>
			                    </tr>
			                </tbody>
			            </table>
	                </td>
	              </tr>
	           </tbody>
	          </table>             
	        <br>
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>               
			            <table width="70%" align="center" class="field_tbl" >
			                <#list compList as comp>
			                <tr>
			                    <td width="40%" class="lcell" align="center"><label>${comp.mainCompanyName!""}</label></td>
			                    <td width="40%">
			                     <#if loginUser.hasPermission("FA001_10")>
			                        <@s.a href="FA001_10?fault.repairCompanyId=${comp.id!-1}">${comp.shortName!""}&nbsp;<@s.text name="fault_list" /></@s.a>
			                     <#else>
			                        ${comp.shortName!""}&nbsp;<@s.text name="fault_list" />
			                     </#if>
			                    </td>
			                    <td width="10%" align="center">
			                      <#if loginUser.hasPermission("FA001_10")>
			                         <@s.a href="FA001_10?fault.repairCompanyId=${comp.id!-1}&fault.repairState=0"><@s.text name="not_finish" /></@s.a>
			                      <#else>
			                         <@s.text name="not_finish" />
			                      </#if>  
			                    </td>
			                    <td width="10%" align="center">
			                      <#if loginUser.hasPermission("FA001_10")>
			                        <@s.a href="FA001_10?fault.repairCompanyId=${comp.id!-1}&fault.repairState=1"><@s.text name="finish" /></@s.a>
			                      <#else>
			                        <@s.text name="finish" />
			                      </#if>  
			                    </td>   
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