<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="bank.create" /></title>
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
        <form id="mdfForm" action="BS004_21" method="post">
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>
		           <table width="60%" class="field_tbl" align="center">
		            <tbody>
		             <tr>
		               <td width="20%" class="lcell"><label><@s.text name="bank.name" /></label></td>
		               <td width="80%">
		                   <input type="text" name="bank.name"  value="${bank.name!""}"  size="60" maxLength="40"/>
		                   <label class="needed"><@s.text name="input_needed" /></label>
		               </td>
		             </tr>
		            <tr>
                       <td width="20%" class="lcell"><label><@s.text name="bank.shortName" /></label></td>
                       <td width="80%">
                           <input type="text" name="bank.shortName"  value="${bank.shortName!""}"  size="60" maxLength="60"/>
                           <label class="needed"><@s.text name="input_needed" /></label>
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
           <#if loginUser.hasPermission("BS012_21")>
              <button type="button" onClick="changeAction('BS012_21')" ><@s.text name="btn_add" /></button>
           </#if> 
        </div>
     </div>
     
  </div>
  <#include "../footer.ftl"/>   
</div>
</#escape>   
</body>
</html>