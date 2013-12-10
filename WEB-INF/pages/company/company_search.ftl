<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="company.search" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
      form.submit();
   }
   
   $(document).ready(function(){
   
      $("#provinceDrop").change(function(){
           var provinceId = $(this).val();
           var params = "provinceId="+provinceId;
           $.ajax({
              url: 'city_drop',
              type: 'GET',
              data: params,
              dataType: 'html',
              cache:false,
              timeout: 1000,
              success: function(data, textStatus){
                $("#cityDrop").html(data);
              }
            });
       
       });
       
      if($("#comType").val()==4){
         $("#nameLbl").text("<@s.text name="company.mainCompanyName" />");
         $("#subComName").show();
      }else{
        $("#nameLbl").text("<@s.text name="company.nameLbl" />");
        $("#subComNameInput").val("");
        $("#subComName").hide();
        
      }
   
     $("#comType").change(function(){
       if ($(this).val() == "4") {
            $("#nameLbl").text("<@s.text name="company.mainCompanyName" />");
             $("#subComName").show();
        } else {
            $("#nameLbl").text("<@s.text name="company.nameLbl" />");
            $("#subComNameInput").val("");
            $("#subComName").hide();
            
        }
      });
  
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
         <form id="mdfForm" action="BS005_11" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>             
			             <table width="70%" class="field_tbl" align="center">
			                <tr>
			                    <td class="lcell"><label><@s.text name="company.companyTypeName" /></label></td>
			                    <td>
			                      <select id="comType" name="company.typeId"  value="${company.typeId!""}" width="200" >
			                         <@s.action name="companyType_drop" executeResult="true" ignoreContextParams="true">
			                           <@s.param name="selectedComTypeId">${company.typeId!-1}</@s.param>
			                        </@s.action>
			                      </select>
			                    </td>
			                </tr>
                            <tr>
                              <td class="lcell"><label><@s.text name="company.provinceName" /></label></td>
                                <td>
	                               <select id="provinceDrop" name="company.provinceId"  value="${company.provinceId!""}"  >
	                                 <@s.action name="province_drop" executeResult="true" ignoreContextParams="true">
	                                    <@s.param name="selectedProvinceId">${company.provinceId!"-1"}</@s.param>
	                                 </@s.action>
	                               </select>
                                </td>
                            </tr>			                
			                <tr>
			                  <td class="lcell"><label><@s.text name="company.cityName" /></label></td>
			                    <td>
			                      <select  id="cityDrop" name="company.cityId"  value="${company.cityId!""}"  >
			                         <@s.action name="city_drop" executeResult="true" ignoreContextParams="true">
			                           <@s.param name="selectedCityId">${company.cityId!-1}</@s.param>
			                        </@s.action>
			                      </select>
			                    </td>
			                </tr>
                            <tr>
                               <td class="lcell"><label><@s.text name="company.code" /></label></td>
                               <td>
                                  <input type="text" name="company.code"  value="${company.code!""}" size="60"  maxLength="60"/>
                               </td>
                            </tr>			                
			                <tr>
			                   <td class="lcell"><label><@s.text name="company.shortName" /></label></td>
			                   <td>
			                      <input type="text" name="company.shortName"  value="${company.shortName!""}" size="60"  maxLength="20"/>
			                   </td>
			                </tr>
			                <tr>
			                    <td class="lcell"><label id="nameLbl"><@s.text name="company.mainCompanyName" /></label></td>
			                    <td>
			                       <input type="text" name="company.mainCompanyName"  value="${company.mainCompanyName!""}"  size="60" maxLength="20"/>
			                    </td>
			                </tr>
			                <tr id="subComName" style="display:none">
			                    <td class="lcell"><label><@s.text name="company.subCompanyName" /></label></td>
			                    <td>
			                       <input type="text" id="subComNameInput" name="company.subCompanyName"  value="${company.subCompanyName!""}"  size="60" maxLength="20"/>
			                    </td>
			                </tr>
			                <tr>
			                    <td class="lcell"><label><@s.text name="company.range" /></label></td>
			                    <td>
			                      <input type="radio" name="company.customerFlag" value="0" checked/><label><@s.text name="company.allCompanys" /></label>
			                      <input type="radio" name="company.customerFlag" value="1"/><label><@s.text name="company.customers" /></label>
			                    </td>
			                </tr>
			              </table> 
		              </td>
		           </tr>
		        </tbody>
		    </table>  			               
          </form>
	      <div class="btn_row">
	          <#if loginUser.hasPermission("BS005_11")>       
	               <button type="button" onClick="changeAction('BS005_11')" ><@s.text name="btn_search" /></button>
	          </#if>
	      </div>
      </div>
   </div>
 <#include "../footer.ftl"/> 
</div>
</#escape> 
</body>
</html>