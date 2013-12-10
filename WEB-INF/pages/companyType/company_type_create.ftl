<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="companyType.create" /></title>
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
<div id="container">
  <#include "../header.ftl"/>        
   <div id="view" >
     <div class="fields">
       <form id="mdfForm" action="BS001_21" method="post">
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>   
			      <table width="60%" class="field_tbl" align="center">
			        <tbody>
				      <tr>
				         <td width="20%" class="lcell"><label><@s.text name="companyType.name" /></label></td>
				         <td width="80%">
				            <input type="text" name="companyType.name" value="${companyType.name!""}"  size="60" maxLength="40"/>
				            <label class="needed" ><@s.text name="input_needed" /></label> 
				         </td>
				       </tr>
				       <tr>
				          <td class="lcell"><label><@s.text name="companyType.description"  /></label></td>
				          <td>
				             <input type="text" name="companyType.description"  value="${companyType.description!""}" size="60" maxLength="80" />
				             <label class="needed" ><@s.text name="input_needed" /></label>
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
	     <#if loginUser.hasPermission("BS001_21")>
	        <button onClick="changeAction('BS001_21')" ><@s.text name="btn_add" /></button>
	     </#if>
     </div>
   </div>
 </div>
 <#include "../footer.ftl"/>   
</div>
</#escape>
</body>
</html>