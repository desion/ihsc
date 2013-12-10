<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="city.search" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
      form.submit();
   }

   $(document).ready(function(){
       $("#countryDrop").change(function(){
           var countryId = $(this).val();
           var params = "countryId="+countryId;
		   $.ajax({
			  url: 'province_drop',
			  type: 'GET',
			  data: params,
			  dataType: 'html',
			  timeout: 1000,
			  cache:false,
			  success: function(data, textStatus){
			    $("#provinceDrop").html(data);
			  }
			});
       
       });
     
     });


</script>
</head>
<body>
<#include "../components/error_reference.ftl"/>
<div id="container" >
  <#include "../header.ftl"/>
   <div id="view">
      <div class="fields">  
         <form id="mdfForm" action="BS004_11" method="post">
           <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
             <tbody>
                <tr>
                  <td>           
		             <table width="70%" class="field_tbl" align="center">
		                 <tr>
		                    <td class="lcell"><label><@s.text name="city.countryName"  /></label></td>
		                    <td>
		                        <select id="countryDrop" name="city.countryId"  >
		                          <@s.action name="country_drop" executeResult="true" ignoreContextParams="true">
		                             <@s.param name="selectedCountryId">${city.countryId!"-1"}</@s.param>
		                          </@s.action>
		                       </select>
		                    </td>
		                </tr>
		                <tr>
			               <td class="lcell"><label><@s.text name="city.provinceName" /></label></td>
			               <td>
			                  <select id="provinceDrop" name="city.provinceId"  value="${city.provinceId!""}"  >
			                   <@s.action name="province_drop" executeResult="true" ignoreContextParams="true">
			                      <@s.param name="selectedProvinceId">${city.provinceId!"-1"}</@s.param>
			                   </@s.action>
			                  </select>
			              </td>
		               </tr>
		               <tr>
		                   <td class="lcell"><label><@s.text name="city.name" /></label></td>
		                   <td>
		                      <input type="text" name="city.name"  value="${city.name!""}" size="60"  maxLength="40"/>
		                   </td>
		               </tr>
		             </table>
                 </td>
               </tr>
             </tbody>
           </table> 		               
          </form>
	      <div class="btn_row">
	          <#if loginUser.hasPermission("BS004_11")>       
	               <button type="button" onClick="changeAction('BS004_11')" ><@s.text name="btn_search" /></button>
	          </#if>
	      </div>
      </div>
   </div>
 <#include "../footer.ftl"/> 
</div>
</body>
</html>