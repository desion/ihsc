<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="PR005_71_title" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
    function loadAction(url)
    {
       var form=document.getElementById("searchForm");
       form.action=url;
       form.submit();
    }

    $(document).ready(function(){
        $("#fobDate").datepicker();
        $("#installDate").datepicker();
        $("#openDate").datepicker();
        $("#acceptDate").datepicker();
        $("#guaranteeStartDate").datepicker();
        $("#guaranteeEndDate").datepicker();
        $("#reset").click(function() {
            $('input[type="text"]').val("");
            $("select").val("");
            $('input[type="radio"]').attr("checked",'');
        });
        
        $("#bankDrop").change(function(){
            userCompanyChange();
        });
        
        $("#cityDrop").change(function(){
            userCompanyChange();
        });
        
        $("#provinceDrop").change(function(){
           userCompanyChange(); 
           var provinceId = $(this).val();
           cityChange(provinceId);
        });
        
    });
    
    function cityChange(provinceId){
          var selectedCityId = $("#cityDrop").val()
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
                $("#cityDrop").val(selectedCityId);
              }
            });
       }
            
       function userCompanyChange(){
          var params = "1=1"
          var bankId = $("#bankDrop").val();
          if(bankId != ""){
            params = params + "&bankId="+bankId;
          }
          var provinceId = $("#provinceDrop").val();
          if(provinceId != ""){
            params = params + "&provinceId="+provinceId;
          }              
          var cityId = $("#cityDrop").val();
          if(cityId != ""){
            params = params + "&cityId="+cityId;
          }  
              
          var selectedCustomerId = $("#customerDrop").val();
          $.ajax({
              url: 'customer_drop',
              type: 'GET',
              data: params,
              dataType: 'html',
              cache:false,
              timeout: 1000,
              success: function(data, textStatus){
                $("#customerDrop").html(data);
                $("#customerDrop").val(selectedCustomerId);
              }
          });  
      }
</script>
</head>
<body>
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
		                <table width="70%" align="center" class="field_tbl" >
		                    <tbody>
		                        <tr>
		                            <td width="20%" class="lcell" ><label><@s.text name="install.productId" /></label></td>
		                            <td width="80%">
		                                <select name="install.productId" >
		                                    <@s.action name="productID_drop" executeResult="true" ignoreContextParams="true">
		                                        <@s.param name="selectedProductId">${install.productId!""}</@s.param>
		                                    </@s.action>
		                                </select>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.manufactureNo" /></label></td>
		                            <td width="80%">
		                                <input type="text" name="install.manufactureNo" value="${install.manufactureNo!""}" maxLength="10" size="40"/ >
		                            </td>
		                        </tr>
                                <tr>
                                    <td width="15%" class="lcell"><label><@s.text name="install.userCompanyFilter" /></label></td>
                                    <td width="85%">
                                      <select id="bankDrop" name="install.userCompanyBankId"  >
                                         <@s.action name="bank_drop" executeResult="true" ignoreContextParams="true">
                                           <@s.param name="selectedBankId">${install.userCompanyBankId!"-1"}</@s.param>
                                        </@s.action>
                                      </select>	
                                    </td>
                               </tr>                 	                        
                                <tr>
                                    <td width="20%" class="lcell"><label><@s.text name="install.customerProvinceId" /></label></td>
                                    <td width="80%">
                                      <select id="provinceDrop" name="install.customerProvinceId"  value="${install.customerProvinceId!""}"  >
                                        <@s.action name="province_drop" executeResult="true" ignoreContextParams="true">
                                           <@s.param name="selectedProvinceId">${install.customerProvinceId!"-1"}</@s.param>
                                        </@s.action>
                                      </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><label><@s.text name="install.customerCityId" /></label></td>
                                    <td width="80%">
                                       <select  id="cityDrop" name="install.customerCityId"  value="${install.customerCityId!""}"  >
                                          <@s.action name="city_drop" executeResult="true" ignoreContextParams="true">
                                             <@s.param name="selectedCityId">${install.customerCityId!-1}</@s.param>
                                          </@s.action>
                                       </select>
                                    </td>
                                </tr>		                        
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.customerId" /></label></td>
		                            <td width="80%">
		                                <select id="customerDrop" name="install.customerId" >
		                                    <@s.action name="customer_drop" executeResult="true" ignoreContextParams="true">
		                                        <@s.param name="selectedComId">${install.customerId!"-1"}</@s.param>
		                                    </@s.action>
		                                </select>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.saleContractCompId" /></label></td>
		                            <td width="80%">
		                                <select name="install.saleContractCompId" >
		                                    <@s.action name="saleCompany_drop" executeResult="true" ignoreContextParams="true">
		                                        <@s.param name="selectedComId">${install.saleContractCompId!"-1"}</@s.param>
		                                    </@s.action>
		                                </select>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.installCompId" /></td>
		                            <td width="80%">
		                                <select name="install.installCompId" >
		                                    <@s.action name="installCompany_drop" executeResult="true" ignoreContextParams="true">
		                                        <@s.param name="selectedComId">${install.installCompId!"-1"}</@s.param>
		                                    </@s.action>
		                                </select>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.nowRepairCompanyName" /></td>
		                            <td width="80%">
		                                <select name="install.nowRepairCompanyId" >
		                                    <@s.action name="installCompany_drop" executeResult="true" ignoreContextParams="true">
		                                        <@s.param name="selectedComId">${install.nowRepairCompanyId!"-1"}</@s.param>
		                                    </@s.action>
		                                </select>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell" aligh="right"><label><@s.text name="install.indentureNo" /></label></td>
		                            <td width="80%">
		                                <input type="text" name="install.indentureNo" value="${install.indentureNo!""}" maxLength="20" size="40"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.fobDate" /></label></td>
		                            <td width="80%">
		                                <input id="fobDate" type="text" name="install.fobDate" value="${install.fobDate!""}" maxLength="10" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.installDate" /></label></td>
		                            <td width="80%">
		                                <input id="installDate" type="text" name="install.installDate" value="${install.installDate!""}" maxLength="10" />
		                            </td>
		                        </tr>
		                        
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.openDate" /></label></td>
		                            <td width="80%">
		                                <input id="openDate" type="text" name="install.openDate" value="${install.openDate!""}" maxLength="10" />
		                            </td>
		                        </tr>
		                        
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.acceptDate" /></label></td>
		                            <td width="80%">
		                                <input id="acceptDate" type="text" name="install.acceptDate" value="${install.acceptDate!""}" maxLength="10" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.installer" /></label></td>
		                            <td width="80%">
		                                <input type="text" name="install.installer" value="${install.installer!""}" maxLength="40" size="80"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.installerId" /></label></td>
		                            <td width="80%">
		                                <input type="text" name="install.installerId" value="${install.installerId!""}" maxLength="40" size="80"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.guaranteeStartDate" /></label></td>
		                            <td width="80%">
		                                <input id="guaranteeStartDate" type="text" name="install.guaranteeStartDate" value="${install.guaranteeStartDate!""}" maxLength="10" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.guaranteeEndDate" /></label></td>
		                            <td width="80%">
		                                <input id="guaranteeEndDate" type="text" name="install.guaranteeEndDate" value="${install.guaranteeEndDate!""}" maxLength="10" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.guaranteePeriod" /></label></td>
		                            <td width="80%">
		                                <input type="text" name="install.guaranteePeriod" value="${install.guaranteePeriod!""}" maxLength="2" size="10"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.branchCompanyName" /></label></td>
		                            <td width="80%"><input type="text" name="install.branchCompanyName" value="${install.branchCompanyName!""}" maxLength="40" size="80"/></td>
		                        </tr>
		                        
	
		                        
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.installPlace" /></label></td>
		                            <td width="80%"><input type="text" name="install.installPlace" value="${install.installPlace!""}" maxLength="40" size="80"/></td>
		                        </tr>
		                        
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.instPlaceTypeId" /></label></td>
		                            <td width="80%">
		                                <select name="install.instPlaceTypeId" style="width:175px">
		                                    <@s.action name="installPlaceType_drop" executeResult="true" ignoreContextParams="true">
		                                        <@s.param name="selectedTypeId">${install.instPlaceTypeId!""}</@s.param>
		                                    </@s.action>
		                                </select>
		                            </td>
		                        </tr>
		                        
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.useStatusId" /></label></td>
		                            <td width="80%">
		                                <select name="install.useStatusId" style="width:175px">
		                                    <@s.action name="userStatus_drop" executeResult="true" ignoreContextParams="true">
		                                        <@s.param name="selectedStatusId">${install.useStatusId!""}</@s.param>
		                                    </@s.action>
		                                </select>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.purpose" /></label></td>
		                            <td width="80%">
		                                <select name="install.purpose" style="width:175px">
		                                    <@s.action name="purpose_drop" executeResult="true" ignoreContextParams="true">
		                                        <@s.param name="selectedPurposeId">${install.purpose!""}</@s.param>
		                                    </@s.action>
		                                </select>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.keyNo" /></label></td>
		                            <td width="80%">
		                                <input type="text" name="install.keyNo" value="${install.keyNo!""}" maxLength="20" size="40"/>
		                            </td>
		                        </tr>
		  
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.note" /></label></td>
		                            <td width="80%">
		                                <input type="text" name="install.note" value="${install.note!""}" maxLength="800" size="80"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.affirmFlag" /></label></td>
		                            <td width="80%">
		                                <#if install.affirmFlag?? && install.affirmFlag == 1>
		                                    <input type="radio" name="install.affirmFlag" value="1" checked/><@s.text name="affirm" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.affirmFlag" value="1" /><@s.text name="affirm" />&nbsp;
		                                </#if>
		                                <#if install.affirmFlag?? && install.affirmFlag == 0>
		                                    <input type="radio" name="install.affirmFlag" value="0" checked/><@s.text name="not_affirm" />
		                                <#else>
		                                    <input type="radio" name="install.affirmFlag" value="0" /><@s.text name="not_affirm" />
		                                </#if>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td width="20%" class="lcell"><label><@s.text name="install.sort" /></label></td>
		                            <td width="80%">
		                                <#if install.sort?? && install.sort == "productId">
		                                    <input type="radio" name="install.sort" value="productId" checked/><@s.text name="install.productId" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="productId" /><@s.text name="install.productId" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "manufactureNo">
		                                    <input type="radio" name="install.sort" value="manufactureNo" checked/><@s.text name="install.manufactureNo" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="manufactureNo" /><@s.text name="install.manufactureNo" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "customerId">
		                                    <input type="radio" name="install.sort" value="customerId" checked/><@s.text name="install.customerId" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="customerId" /><@s.text name="install.customerId" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "saleContractCompId">
		                                    <input type="radio" name="install.sort" value="saleContractCompId" checked/><@s.text name="install.saleContractCompId" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="saleContractCompId" /><@s.text name="install.saleContractCompId" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "installCompId">
		                                    <input type="radio" name="install.sort" value="installCompId" checked/><@s.text name="install.installCompId" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="installCompId" /><@s.text name="install.installCompId" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "nowRepairCompanyId">
		                                    <input type="radio" name="install.sort" value="nowRepairCompanyId" checked/><@s.text name="install.nowRepairCompanyId" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="nowRepairCompanyId" /><@s.text name="install.nowRepairCompanyId" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "indentureNo">
		                                    <input type="radio" name="install.sort" value="indentureNo" checked/><@s.text name="install.indentureNo" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="indentureNo" /><@s.text name="install.indentureNo" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "fobDate">
		                                    <input type="radio" name="install.sort" value="fobDate" checked/><@s.text name="install.fobDate" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="fobDate" /><@s.text name="install.fobDate" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "installDate">
		                                    <input type="radio" name="install.sort" value="installDate" checked/><@s.text name="install.installDate" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="installDate" /><@s.text name="install.installDate" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "openDate">
		                                    <input type="radio" name="install.sort" value="openDate" checked/><@s.text name="install.openDate" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="openDate" /><@s.text name="install.openDate" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "acceptDate">
		                                    <input type="radio" name="install.sort" value="acceptDate" checked/><@s.text name="install.acceptDate" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="acceptDate" /><@s.text name="install.acceptDate" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "installer">
		                                    <input type="radio" name="install.sort" value="installer" checked/><@s.text name="install.installer" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="installer" /><@s.text name="install.installer" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "installerId">
		                                    <input type="radio" name="install.sort" value="installerId" checked/><@s.text name="install.installerId" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="installerId" /><@s.text name="install.installerId" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "guaranteeStartDate">
		                                    <input type="radio" name="install.sort" value="guaranteeStartDate" checked/><@s.text name="install.guaranteeStartDate" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="guaranteeStartDate" /><@s.text name="install.guaranteeStartDate" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "guaranteeEndDate">
		                                    <input type="radio" name="install.sort" value="guaranteeEndDate" checked/><@s.text name="install.guaranteeEndDate" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="guaranteeEndDate" /><@s.text name="install.guaranteeEndDate" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "guaranteePeriod">
		                                    <input type="radio" name="install.sort" value="guaranteePeriod" checked/><@s.text name="install.guaranteePeriod" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="guaranteePeriod" /><@s.text name="install.guaranteePeriod" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "branchCompanyName">
		                                     <input type="radio" name="install.sort" value="branchCompanyName" checked/><@s.text name="install.branchCompanyName" />&nbsp;
		                                <#else>
		                                     <input type="radio" name="install.sort" value="branchCompanyName" /><@s.text name="install.branchCompanyName" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "customerProvinceId">
		                                     <input type="radio" name="install.sort" value="customerProvinceId" checked/><@s.text name="install.customerProvinceId" />&nbsp;
		                                <#else>
		                                     <input type="radio" name="install.sort" value="customerProvinceId" /><@s.text name="install.customerProvinceId" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "customerCityId">
		                                     <input type="radio" name="install.sort" value="customerCityId" checked/><@s.text name="install.customerCityId" />&nbsp;
		                                <#else>
		                                     <input type="radio" name="install.sort" value="customerCityId" /><@s.text name="install.customerCityId" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "installPlace">
		                                    <input type="radio" name="install.sort" value="installPlace" checked/><@s.text name="install.installPlace" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="installPlace" /><@s.text name="install.installPlace" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "instPlaceTypeId">
		                                    <input type="radio" name="install.sort" value="instPlaceTypeId" checked/><@s.text name="install.instPlaceTypeId" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="instPlaceTypeId" /><@s.text name="install.instPlaceTypeId" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "useStatusId">
		                                    <input type="radio" name="install.sort" value="useStatusId" checked/><@s.text name="install.useStatusId" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="useStatusId" /><@s.text name="install.useStatusId" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "purpose">
		                                    <input type="radio" name="install.sort" value="purpose" checked/><@s.text name="install.purpose" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="purpose" /><@s.text name="install.purpose" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "keyNo">
		                                    <input type="radio" name="install.sort" value="keyNo" checked/><@s.text name="install.keyNo" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="keyNo" /><@s.text name="install.keyNo" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "note">
		                                    <input type="radio" name="install.sort" value="note" checked/><@s.text name="install.note" />&nbsp;
		                                <#else>
		                                    <input type="radio" name="install.sort" value="note" /><@s.text name="install.note" />&nbsp;
		                                </#if>
		                                <#if install.sort?? && install.sort == "affirmFlag">
		                                   <input type="radio" name="install.sort" value="affirmFlag" checked/><@s.text name="install.affirmFlag" /> 
		                                <#else>
		                                    <input type="radio" name="install.sort" value="affirmFlag" /><@s.text name="install.affirmFlag" />
		                                </#if>
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
                  <button type="button" id="reset" ><@s.text name="btn_reset" /></button>
                  <#if loginUser.hasPermission("PR005_13")>  
                        <button type="button" onClick="loadAction('PR005_13')" ><@s.text name="btn_search1" /></button>
                  </#if>     
                  <#if loginUser.hasPermission("PR005_60")>
                        <button type="button" onClick="loadAction('PR005_60')" ><@s.text name="btn_export" /></button>
                  </#if>
            </div>

    </div>
   </div>
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>