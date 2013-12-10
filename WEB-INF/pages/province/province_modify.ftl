<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="province.modify" /></title>
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
<div id="container">        
  <#include "../header.ftl"/>
    <div id="view">
       <div class="fields"  cellspacing="0" cellpadding="0"> 
          <form id="mdfForm" action="BS003_31" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>            
			            <table align="center" width="60%" class="field_tbl" >
			               <tbody>
			                  <tr>
			                     <td width="20%" class="lcell"><label><@s.text name="province.name" /></label></td>
			                     <td width="80%">
			                        <input type="text" name="province.name" value="${province.name!""}" size="60" maxLength="40" />
			                        <label  class="needed"><@s.text name="input_needed" /></label>
			                     </td>
			                 </tr>
                             <tr>
                               <td width="20%" class="lcell"><label><@s.text name="province.shortName" /></label></td>
                               <td width="80%">
                                   <input type="text" name="province.shortName"  value="${province.shortName!""}"  size="60" maxLength="60"/>
                                   <label class="needed"><@s.text name="input_needed" /></label>
                               </td>
                             </tr>  			                 
			                 <tr>
			                    <td class="lcell"><label><@s.text name="province.countryId" /></label></td>
			                    <td> 
			                       <select id="provinceDrop" name="province.countryId"  value="${province.countryId!""}"  >
			                          <@s.action name="country_drop" executeResult="true" ignoreContextParams="true">
			                              <@s.param name="selectedCountryId">${province.countryId!"-1"}</@s.param>
			                          </@s.action>
			                       </select>
			                      <label class="needed"><@s.text name="select_needed" /></label>
			                   </td>
			                </tr>
			              </tbody> 
			           </table>
	                </td>
	              </tr>
	           </tbody>
	          </table> 			           
              <input type="hidden" name="province.id"  value="${province.id!""}" />
              <input type="hidden" name="province.exclusiveKey" value="${province.exclusiveKey!""}"  />
         </form>
         <div class="btn_row">
	        <#if loginUser.hasPermission("BS003_31")>
	           <button type="button" onClick="changeAction('BS003_31')"><@s.text name="btn_modify" /></button>
	        </#if>
         </div>
       </div>
     </div>  
   <#include "../footer.ftl"/>   
 </div>
</#escape>                         
</body>
</html>