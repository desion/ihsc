<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="FA001_72_title" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
    function search(url){
       var form=document.getElementById("searchForm");
       form.action=url;
       form.submit();
    }
    
    function enterKeyDown(event,url){  
     if (event.keyCode == 13){  
          event.returnValue=false;  
          event.cancel = true;  
          search(url)
      }  
    }
    
</script>
</head>
<body onkeydown="enterKeyDown(event,'FA001_12')">
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container">
    <#include "../header.ftl"/>
    <div id="view">
        <div class="fields">
            <form id="searchForm" method="post">
		        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		           <tbody>
		              <tr>
		                <td>             
			                <table width="50%" align="center" class="field_tbl" >
			                    <tbody>
			                        <tr>
			                            <td width="30%" class="lcell"><label><@s.text name="fault.managementId" /></label></td>
			                            <td width="70%">
			                                <input type="text" name="fault.managementId" value="${fault.managementId!""}" maxLength="60" size="50"/ >
			                                <label style="color:red"><@s.text name="input_needed" /></label>
			                            </td>
			                        </tr>
			                    </tbody>
			                </table>
		                </td>
		              </tr>
		           </tbody>
		        </table>                  
            </form>
            <div class="btn_row">
             <#if loginUser.hasPermission("FA001_12")>
                <button type="button" onClick="search('FA001_12')" ><@s.text name="btn_search" /></button>
             </#if>
            </div>
        </div>
    </div>
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>