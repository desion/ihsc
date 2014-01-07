<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="customer.create" /></title>
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
        <form id="mdfForm" action="BS002_21" method="post">
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>
		           <table width="60%" class="field_tbl" align="center">
		            <tbody>
		             <tr>
		               <td width="20%" class="lcell"><label><@s.text name="customer.customerName" /></label></td>
		               <td width="80%">
		                   <input type="text" name="customer.customerName"  value="${customer.customerName!""}"  size="30" maxLength="40"/>
		                   <label class="needed"><@s.text name="input_needed" /></label>
		               </td>
		             </tr>
		            <tr>
                       <td width="20%" class="lcell"><label><@s.text name="customer.sname" /></label></td>
                       <td width="80%">
                           <input type="text" name="customer.sname"  value="${customer.sname!""}"  size="30" maxLength="20"/>
                           <label class="needed"><@s.text name="input_needed" /></label>
                       </td>
                     </tr>
                     <tr>
                       <td width="20%" class="lcell"><label><@s.text name="customer.userName" /></label></td>
                       <td width="80%">
                           <input type="text" name="customer.userName"  value="${customer.userName!""}"  size="30" maxLength="20"/>
                           <label class="needed"><@s.text name="input_needed" /></label>
                       </td>
                     </tr>
		             <tr>
		                <td class="lcell"><label><@s.text name="customer.mobile" /></label></td>
		                <td>
		                  <input type="text" name="customer.mobile"  value="${customer.mobile!""}"  size="12" maxLength="11"/>
		                  <label class="needed"><@s.text name="default.message" /></label>
		               </td>
		            </tr>
		            <tr>
		              <td class="lcell"><label><@s.text name="customer.email" /></label></td>
		              <td>
		                  <input type="text" name="customer.email"  value="${customer.email!""}"  size="30" maxLength="30"/>
		                  <label class="needed"><@s.text name="default.message" /></label>
		              </td>
		              
		            </tr>
		            <tr>
		              <td class="lcell"><label><@s.text name="customer.callbackUrl" /></label></td>
		              <td>
		                  <input type="text" name="customer.callbackUrl"  value="${customer.callbackUrl!""}"  size="50" maxLength="100"/>
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
           <#if loginUser.hasPermission("BS002_21")>
              <button type="button" onClick="changeAction('BS002_21')" ><@s.text name="btn_add" /></button>
           </#if> 
        </div>
     </div>
     
  </div>
</div>
</#escape>   
</body>
</html>