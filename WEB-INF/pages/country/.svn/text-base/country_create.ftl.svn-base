<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="country.create" /></title>
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
				     <table width="70%" class="field_tbl" align="center">
				       <tbody>
				         <tr>
				            <td width="20%" class="lcell"><label><@s.text name="country.name" /></label></td>
				            <td width="80%">
				                <input type="text" name="country.name" value="${country.name!""}" size="80" maxLength="40"/> 
				                <label class="needed"><@s.text name="input_needed" /></label>
				            </td>
				         </tr>	    
				         <tr>
			                <td class="lcell"><label><@s.text name="country.nameEn" /></label></td>
			                <td>
			                   <input type="text" name="country.nameEn"value="${country.nameEn!""}" size="80"  maxLength="120"/> 
			                   <label class="needed"><@s.text name="input_needed" /></label>
			                </td>
			            </tr>        
				        <tr>
				          <td class="lcell"><label><@s.text name="country.shortName"  /></label></td>
				          <td>
				             <input type="text" name="country.shortName"  value="${country.shortName!""}" size="80" maxLength="20" /> 
				             <label class="needed"><@s.text name="input_needed" /></label>
				          </td>
				         </tr>
				         <tr>
				            <td class="lcell"><label><@s.text name="country.shortNameEn"  /></label></td>
				            <td>
				               <input type="text" name="country.shortNameEn"  value="${country.shortNameEn!""}" size="80" maxLength="60" /> 
				               <label  class="needed"><@s.text name="input_needed" /></label>
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
	            <button type="button" onClick="changeAction('BS002_21')"><@s.text name="btn_add" /></button>
	       </#if> 
	    </div>	   
	  </div>
	 </div> 

   <#include "../footer.ftl"/>  
</div>  
</#escape>    
</body>
</html>