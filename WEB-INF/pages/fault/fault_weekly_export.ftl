<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="fault_weekly_query_export_label" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
      form.submit();
   }
   
   $(document).ready(function(){
       $("#baseDate").datepicker();
    });        
   
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container" >
  <#include "../header.ftl"/>
   <div id="view">
      <div class="fields">  
         <form id="mdfForm" action="FA001_81" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>             
			             <table width="70%" class="field_tbl" align="center">
				            <tr>
	                           <td width="20%" class="lcell"><@s.text name="fault.baseDate" /></td>
	                           <td width="80%">
	                                <input id="baseDate" type="text" name="strBaseDate"  maxLength="10" size="14" />
	                            </td>
	                        </tr>  			             
                            <tr>
                                <td width="20%" class="lcell"><@s.text name="fault.faultType" /></td>
                                <td width="80%" >
                                    <select name="faultType">
                                        <@s.action name="faultType_drop" executeResult="true" ignoreContextParams="true">
                                            <@s.param name="selectedFaultType">${faultType!-1}</@s.param>
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
	          <#if loginUser.hasPermission("FA001_81")>       
	               <button type="button" onClick="changeAction('FA001_81')" ><@s.text name="btn_export" /></button>
	          </#if>
	      </div>
      </div>
   </div>
 <#include "../footer.ftl"/> 
</div>
</#escape> 
</body>
</html>