<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="companyType.modify" /></title>
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
    <div id="view" >
	   <div class="fields"  cellspacing="0" cellpadding="0">
	     <form id="mdfForm" action="BS001_31" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>  	     
				       <table align="center" width="60%" class="field_tbl" >
				         <tbody>
				           <tr>
				              <td width="20%" class="lcell"><label><@s.text name="companyType.name" /></label></td>
				              <td width="80%">
				                 <input type="text" name="companyType.name" value="${companyType.name!""}"  size="60" maxLength="40" />
				                 <label class="needed" ><@s.text name="input_needed" /></label>
				              </td>
				           </tr>
				           <tr>
				              <td class="lcell"><label><@s.text name="companyType.description"  /></label></td>
				              <td>
				                 <input type="text" name="companyType.description" value="${companyType.description!""}" size="60" maxLength="80" />
				                 <label class="needed" ><@s.text name="input_needed" /></label>
				              </td>
				           </tr>
				         </tbody> 
				       </table>
	                </td>
	              </tr>
	           </tbody>
	         </table>  				       
	         <input type="hidden" name="companyType.id"  value="${companyType.id!""}" />
             <input type="hidden" name="companyType.exclusiveKey" value="${companyType.exclusiveKey!""}"  />
	     </form>
	     <div class="btn_row">
	       <#if loginUser.hasPermission("BS001_31")>  
	          <button type="button" onClick="changeAction('BS001_31')"><@s.text name="btn_modify" /></button>
	       </#if>    
	     </div>	    
	  </div>
	</div>
  <#include "../footer.ftl"/>    
</div>	
</#escape>   		
</body>
</html>