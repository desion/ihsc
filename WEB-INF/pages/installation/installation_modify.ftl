<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="PR005_31_title" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
    function modifyAction(url)
    {
       var flag= confirm("<@s.text name="BSC00007" />");
       if(flag==true){
          var form=document.getElementById("modifyForm");
          form.action=url;
          form.submit();  
       }
    }
    
    function modifyDialog(url){
        var flag= confirm("<@s.text name="BSC00005" />");
        if(flag==true){
            var form=document.getElementById("modifyForm");
            form.action=url;
            form.submit();  
        }
    }

    $(document).ready(function(){
        $("#fobDate").datepicker();
        $("#installDate").datepicker();
        $("#openDate").datepicker();
        $("#acceptDate").datepicker();
        $("#guaranteeStartDate").datepicker();
        $("#guaranteeEndDate").datepicker();
        
        var agentCompanyId = $("#installComDrop").val();
        var bankId = $("#bankDrop").val();
       
        
        //刷新销售商，城市，用户公司三个下拉菜单的数据
        saleCompanyChange(agentCompanyId);
        provinceChange(bankId);

        $("#installComDrop").change(function(){
           userCompanyChange();  
           var agentComId = $(this).val();
           saleCompanyChange(agentComId);
            
        });
        
        $("#bankDrop").change(function(){
            var bankId = $(this).val();
            provinceChange(bankId);
        });
        
        $("#cityDrop").change(function(){
            userCompanyChange();
        });
        
        $("#provinceDrop").change(function(){
           var provinceId = $(this).val();
           cityChange(provinceId);
        });
                
                
    });
    
     function saleCompanyChange(agentComId){
       var selectedSaleComId = $("#saleCompanyDrop").val()
       var params = "agentComId="+agentComId;
       $.ajax({
              url: 'saleCompany_drop',
              type: 'GET',
              data: params,
              dataType: 'html',
              cache:false,
              timeout: 1000,
              success: function(data, textStatus){
                $("#saleCompanyDrop").html(data);
                $("#saleCompanyDrop").val(selectedSaleComId);
              }
       });
         
     }
     
     function provinceChange(bankId){
         var selectedProvinceId = $("#provinceDrop").val()
         var params = "useFlag=1";
         if(bankId != ""){
           params = params + "&bankId="+bankId;
         }
       
        $.ajax({
           url: 'province_drop',
           type: 'GET',
           data: params,
           dataType: 'html',
           cache:false,
           timeout: 1000,
           success: function(data, textStatus){
             $("#provinceDrop").html(data);
             $("#provinceDrop").val(selectedProvinceId);
             cityChange(selectedProvinceId);
           }
         }); 
      }
      
      function cityChange(provinceId){
          var selectedCityId = $("#cityDrop").val()
          var bankId = $("#bankDrop").val();
          var params = "useFlag=1";
          if(bankId != ""){
            params = params + "&bankId="+bankId;
          }
          if(provinceId != ""){
            params = params + "&provinceId="+provinceId;
          }
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
                userCompanyChange()
              }
          });
       } 
                    
           
       function userCompanyChange(){
              var params = "1=1"
              var agentCompanyId = $("#installComDrop").val();
              if(agentCompanyId != ""){
                params = params + "&agentComId="+agentCompanyId;
              }
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
            <form id="modifyForm" action="PR005_31" method="post">
		        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		           <tbody>
		              <tr>
		                <td>               
			                <table width="90%" align="center" class="field_tbl" >
			                    <tbody>
			                        <tr>
			                            <th colspan="4" ><label><@s.text name="installation_create" /></label></th>
			                        </tr> 
			                        <tr>
			                            <td width="18%" class="lcell" ><label><@s.text name="install.productId" /></label></td>
			                            <td width="82%" colspan=3>
			                                <label>${install.model!""}</label>
			                                <input type="hidden" name="install.model" value="${install.model!""}"/>
			                                <input type="hidden" name="install.productId" value="${install.productId!""}"/>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.manufactureNo" /></label></td>
			                             <td width="82%" colspan=3>
			                                <label>${install.manufactureNo!""}</label>
			                                <input type="hidden" name="install.manufactureNo" value="${install.manufactureNo!""}"/>
			                            </td>
			                        </tr>

                                    <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="install.installCompanyName" /></label></td>
                                        <td width="32%">
                                        <#if install.affirmFlag == 0>
                                            <select id="installComDrop" name="install.installCompId" onChange="repaireComp.value=installComDrop.value" style="width:120px">
                                                <@s.action name="installCompany_drop" executeResult="true" ignoreContextParams="true">
                                                    <@s.param name="selectedComId">${install.installCompId!"-1"}</@s.param>
                                                    <@s.param name="productCategoryId">${install.productCategoryId!"-1"}</@s.param>
                                                </@s.action>
                                            </select>
                                             <input type="hidden" name="install.productCategoryId" value="${install.productCategoryId!""}"/>
                                            &nbsp;<label style="color:red"><@s.text name="select_needed" /></label>
                                        <#else>
                                            <label>${install.installCompName!""}</label>
                                            <input type="hidden" name="install.installCompId" value="${install.installCompId!""}"/>
                                            <input type="hidden" name="install.installCompName" value="${install.installCompName!""}"/>
                                        </#if>
                                        </td>
                                        <td width="18%" class="lcell"><label><@s.text name="install.nowRepairCompanyName" /></td>
                                        <td width="32%">
                                            <#if install.affirmFlag == 0>
                                                <select id="repaireComp" disabled="true">
                                                    <@s.action name="AgentRepaireComp_drop" executeResult="true" ignoreContextParams="true">
                                                        <@s.param name="selectedComId">${install.nowRepairCompanyId!"-1"}</@s.param>
                                                    </@s.action>
                                                </select>
                                                <input type="hidden" name="install.nowRepairCompanyId" value="${install.nowRepairCompanyId!""}"/>
                                            <#else>
                                                <select name="install.nowRepairCompanyId" >
                                                    <@s.action name="installCompany_drop" executeResult="true" ignoreContextParams="true">
                                                        <@s.param name="selectedComId">${install.nowRepairCompanyId!"-1"}</@s.param>
                                                    </@s.action>
                                                </select>
                                                &nbsp;<label style="color:red"><@s.text name="select_needed" /></label>
                                            </#if>
                                        </td>
                                    </tr>
                                    
                                   <#if install.affirmFlag == 0>
                                        <tr>
                                            <td width="18%" class="lcell"><label><@s.text name="install.userCompanyFilter" /></label></td>
                                            <td width="82%" colspan=3>
                                          
                                                  <select id="bankDrop" name="install.userCompanyBankId" style="width:180px" >
                                                     <@s.action name="bank_drop" executeResult="true" ignoreContextParams="true">
                                                       <@s.param name="selectedBankId">${install.userCompanyBankId!"-1"}</@s.param>            
                                                    </@s.action>
                                                  </select>

                                                  <select id="provinceDrop" name="install.userCompanyProvinceId"  style="width:120px">
                                                     <@s.action name="province_drop" executeResult="true" ignoreContextParams="true">
                                                        <@s.param name="selectedProvinceId">${install.userCompanyProvinceId!"-1"}</@s.param>
                                                      <@s.param name="useFlag">1</@s.param>
                                                     </@s.action>
                                                   </select>
                
                                                   <select  id="cityDrop" name="install.customerCityId" style="width:150px">
                                                       <@s.action name="city_drop" executeResult="true" ignoreContextParams="true" >
                                                         <@s.param name="selectedCityId">${install.customerCityId!"-1"}</@s.param>
                                                         <@s.param name="useFlag">1</@s.param>
                                                       </@s.action>
                                                    </select>
                                                       
                                            </td>
                                        </tr>
                                    </#if>
                                                                                                           
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.customerId" /></label></td>
			                            <td width="32%">
			                            <#if install.affirmFlag == 0>
			                                <select id="customerDrop" name="install.customerId" style="width:252px">
			                                    <@s.action name="customer_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedComId">${install.customerId!"-1"}</@s.param>
			                                    </@s.action>
			                                </select>
			                                &nbsp;<label style="color:red"><@s.text name="select_needed" /></label>
			                            <#else>
			                                <label>${install.customerName!""}</label>
			                                <input type="hidden" name="install.customerId" value="${install.customerId!""}"/>
			                                <input type="hidden" name="install.customerName" value="${install.customerName!""}"/>
			                            </#if>
			                            </td>
			                            <td width="18%" class="lcell"><label><@s.text name="install.saleContractCompId" /></label></td>
			                            <td width="32%">
			                            <#if install.affirmFlag == 0>
			                                <select id="saleCompanyDrop" name="install.saleContractCompId" style="width:120px">
			                                    <@s.action name="saleCompany_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedComId">${install.saleContractCompId!"-1"}</@s.param>
			                                    </@s.action>
			                                </select>
			                                &nbsp;<label style="color:red"><@s.text name="select_needed" /></label>
			                            <#else>
			                                <label>${install.saleContractCompName!""}</label>
			                                <input type="hidden" name="install.saleContractCompId" value="${install.saleContractCompId!""}"/>
			                                <input type="hidden" name="install.saleContractCompName" value="${install.saleContractCompName!""}"/>
			                            </#if>
			                            </td>
			                        </tr>
			            
			                    </tbody>
			                </table>
		                 </td>
		              </tr>
		           </tbody>
		        </table>         
                <br>
                <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
                   <tbody>
                      <tr>
                        <td>         
			                <table width="90%" align="center" class="field_tbl" >
			                    <tbody>
			                        <tr>
			                            <td width="18%" class="lcell" aligh="right"><label><@s.text name="install.indentureNo" /></label></td>
			                            <td width="82%" colspan=3>
			                            <#if install.affirmFlag == 0>
			                                <input type="text" name="install.indentureNo" value="${install.indentureNo!""}" maxLength="20" size="40"/>
			                            <#else>
			                                <label>${install.indentureNo!""}</label>
			                                <input type="hidden" name="install.indentureNo" value="${install.indentureNo!""}"/>
			                            </#if>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.fobDate" /></label></td>
			                            <td width="32%">
			                            <#if install.affirmFlag == 0>
			                                <input id="fobDate" type="text" name="install.fobDate" value="${install.fobDate!""}" maxLength="10" />
			                            <#else>
			                                <label>${install.fobDate!""}</label>
			                                <input type="hidden" name="install.fobDate" value="${install.fobDate!""}"/>
			                            </#if>
			                            </td>
			                            <td width="18%" class="lcell"><label><@s.text name="install.installDate" /></label></td>
			                            <td width="32%">
			                            <#if install.affirmFlag == 0>
			                                <input id="installDate" type="text" name="install.installDate" value="${install.installDate!""}" maxLength="10" />
			                                &nbsp;<label style="color:red"><@s.text name="input_needed" /></label>
			                            <#else>
			                                <label>${install.installDate!""}</label>
			                                <input type="hidden" name="install.installDate" value="${install.installDate!""}"/>
			                            </#if>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.openDate" /></label></td>
			                            <td width="32%">
			                            <#if install.affirmFlag == 0>
			                                <input id="openDate" type="text" name="install.openDate" value="${install.openDate!""}" maxLength="10" />
			                            <#else>
			                                <label>${install.openDate!""}</label>
			                                <input type="hidden" name="install.openDate" value="${install.openDate!""}"/>
			                            </#if>
			                            </td>
			                            <td width="18%" class="lcell"><label><@s.text name="install.acceptDate" /></label></td>
			                            <td width="32%">
			                            <#if install.affirmFlag == 0>
			                                <input id="acceptDate" type="text" name="install.acceptDate" value="${install.acceptDate!""}" maxLength="10" />
			                            <#else>
			                                <label>${install.acceptDate!""}</label>
			                                <input type="hidden" name="install.acceptDate" value="${install.acceptDate!""}"/>
			                            </#if>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.installer" /></label></td>
			                            <td width="82%" colspan=3>
			                            <#if install.affirmFlag == 0>
			                                <input type="text" name="install.installer" value="${install.installer!""}" maxLength="40" style="width:90%"/>
			                            <#else>
			                                <label>${install.installer!""}</label>
			                                <input type="hidden" name="install.installer" value="${install.installer!""}"/>
			                            </#if>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.installerId" /></label></td>
			                            <td width="82%" colspan=3>
			                            <#if install.affirmFlag == 0>
			                                <input type="text" name="install.installerId" value="${install.installerId!""}" maxLength="40" style="width:90%"/>
			                            <#else>
			                                <label>${install.installerId!""}</label>
			                                <input type="hidden" name="install.installerId" value="${install.installerId!""}"/>
			                            </#if>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.guaranteeStartDate" /></label></td>
			                            <td width="32%">
			                            <#if install.affirmFlag == 0>
			                                <input id="guaranteeStartDate" type="text" name="install.guaranteeStartDate" value="${install.guaranteeStartDate!""}" maxLength="10" />
			                                &nbsp;<label style="color:red"><@s.text name="input_needed" /></label>
			                            <#else>
			                                <label>${install.guaranteeStartDate!""}</label>
			                                <input type="hidden" name="install.guaranteeStartDate" value="${install.guaranteeStartDate!""}"/>
			                            </#if>
			                            </td>
			                            <td width="18%" class="lcell"><label><@s.text name="install.guaranteeEndDate" /></label></td>
			                            <td width="32%">
			                            <#if install.affirmFlag == 0>
			                                <input id="guaranteeEndDate" type="text" name="install.guaranteeEndDate" value="${install.guaranteeEndDate!""}" maxLength="10" />
			                                &nbsp;<label style="color:red"><@s.text name="input_needed" /></label>
			                            <#else>
			                                <label>${install.guaranteeEndDate!""}</label>
			                                <input type="hidden" name="install.guaranteeEndDate" value="${install.guaranteeEndDate!""}"/>
			                            </#if>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.guaranteePeriod" /></label></td>
			                            <td width="82%" colspan=3>
			                            <#if install.affirmFlag == 0>
			                                <input type="text" name="install.guaranteePeriod" value="${install.guaranteePeriod!""}" maxLength="2" size="10"/>
			                            <#else>
			                                <label>${install.guaranteePeriod!""}</label>
			                                <input type="hidden" name="install.guaranteePeriod" value="${install.guaranteePeriod!""}"/>
			                            </#if>
			                            </td>
			                        </tr>
			                    </tbody>
			                </table>
                         </td>
                      </tr>
                   </tbody>
                </table>         
                <br>
                <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
                   <tbody>
                      <tr>
                        <td>          
			                <table width="90%" align="center" class="field_tbl" >
			                    <tbody>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.branchCompanyName" /></label></td>
			                            <td width="82%" colspan=3>
			                            	<input type="text" name="install.branchCompanyName" value="${install.branchCompanyName!""}" maxLength="40" style="width:75%"/>
			                            	&nbsp;<label style="color:red"><@s.text name="brance_needed" /></label>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.installPlace" /></label></td>
			                            <td width="82%" colspan=3><input type="text" name="install.installPlace" value="${install.installPlace!""}" maxLength="40" style="width:90%"/></td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.instPlaceTypeId" /></label></td>
			                            <td width="32%">
			                                <select name="install.instPlaceTypeId" style="width:175px">
			                                    <@s.action name="installPlaceType_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedTypeId">${install.instPlaceTypeId!""}</@s.param>
			                                    </@s.action>
			                                </select>
			                                &nbsp;<label style="color:red"><@s.text name="select_needed" /></label>
			                            </td>
			                            <td width="18%" class="lcell"><label><@s.text name="install.useStatusId" /></label></td>
			                            <td width="32%">
			                                <select name="install.useStatusId" style="width:175px">
			                                    <@s.action name="userStatus_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedStatusId">${install.useStatusId!""}</@s.param>
			                                    </@s.action>
			                                </select>
			                                &nbsp;<label style="color:red"><@s.text name="select_needed" /></label>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.purpose" /></label></td>
			                            <td width="32%">
			                                <select name="install.purpose" style="width:175px">
			                                    <@s.action name="purpose_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedPurposeId">${install.purpose!""}</@s.param>
			                                    </@s.action>
			                                </select>
			                                &nbsp;<label style="color:red"><@s.text name="select_needed" /></label>
			                            </td>
			                            <td width="18%" class="lcell"><label><@s.text name="install.contact" /></label></td>
			                            <td width="32%"><input type="text" name="install.contact" value="${install.contact!""}" maxLength="40" style="width:90%"/></td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.officePhone" /></label></td>
			                            <td width="32%"><input type="text" name="install.officePhone" value="${install.officePhone!""}" maxLength="20" style="width:90%"/></td>
			                            <td width="18%" class="lcell"><label><@s.text name="install.mobilePhone" /></label></td>
			                            <td width="32%"><input type="text" name="install.mobilePhone" value="${install.mobilePhone!""}" maxLength="20" style="width:90%"/></td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.fax" /></label></td>
			                            <td width="32%"><input type="text" name="install.fax" value="${install.fax!""}" maxLength="20" style="width:90%"/></td>
			                            <td width="18%" class="lcell"><label><@s.text name="install.email" /></label></td>
			                            <td width="32%"><input type="text" name="install.email" value="${install.email!""}" maxLength="20" style="width:90%"/></td>
			                        </tr>
			                    </tbody>
			                </table>
                         </td>
                      </tr>
                   </tbody>
                </table>                  
                <br>
                
                 <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
                   <tbody>
                      <tr>
                        <td>          
			                <table width="90%" align="center" class="field_tbl" style="table-layout:fixed">
			                    <tbody>
			                    <#if install.faultMachineType?? && install.faultMachineType=="ATM">
                                   <tr>
                                       <td width="18%" class="lcell"><label><@s.text name="install.brmEpVer" /></label></td>
                                       <td width="32%">
                                       <#if install.affirmFlag == 0>
                                            <input type="text" name="install.brmEpVer" value="${install.brmEpVer!""}" maxLength="20" size="40"/>
                                        <#else>
                                            <label>${install.brmEpVer!""}</label>
                                            <input type="hidden" name="install.brmEpVer" value="${install.brmEpVer!""}"/>
                                        </#if>
                                        </td>
                                        <td width="18%" class="lcell"><label><@s.text name="install.bvEpVer" /></label></td>
                                        <td width="32%">
                                        <#if install.affirmFlag == 0>
                                            <input type="text" name="install.bvEpVer" value="${install.bvEpVer!""}" maxLength="20" size="40"/>
                                        <#else>
                                            <label>${install.bvEpVer!""}</label>
                                            <input type="hidden" name="install.bvEpVer" value="${install.bvEpVer!""}"/>
                                        </#if>
                                        </td>
                                   </tr>			                    
			                     <#elseif  install.faultMachineType?? && install.faultMachineType=="G-ABIO">
                                   <tr>
                                       <td width="18%" class="lcell"><label><@s.text name="install.mainEpVer" /></label></td>
                                       <td width="32%">
                                       <#if install.affirmFlag == 0>
                                            <input type="text" name="install.brmEpVer" value="${install.brmEpVer!""}" maxLength="20" size="40"/>
                                        <#else>
                                            <label>${install.brmEpVer!""}</label>
                                            <input type="hidden" name="install.brmEpVer" value="${install.brmEpVer!""}"/>
                                        </#if>
                                        </td>
                                        <td width="18%" class="lcell"><label><@s.text name="install.bhclEpVer" /></label></td>
                                        <td width="32%">
                                        <#if install.affirmFlag == 0>
                                            <input type="text" name="install.bhclEpVer" value="${install.bhclEpVer!""}" maxLength="20" size="40"/>
                                        <#else>
                                            <label>${install.bvEpVer!""}</label>
                                            <input type="hidden" name="install.bhclEpVer" value="${install.bhclEpVer!""}"/>
                                        </#if>
                                        </td>
                                   </tr>
                                   <tr>
                                       <td width="18%" class="lcell"><label><@s.text name="install.trclEpVer" /></label></td>
                                       <td width="32%">
                                       <#if install.affirmFlag == 0>
                                            <input type="text" name="install.trclEpVer" value="${install.trclEpVer!""}" maxLength="20" size="40"/>
                                        <#else>
                                            <label>${install.brmEpVer!""}</label>
                                            <input type="hidden" name="install.trclEpVer" value="${install.trclEpVer!""}"/>
                                        </#if>
                                        </td>
                                        <td width="18%" class="lcell"><label><@s.text name="install.bidEpVer" /></label></td>
                                        <td width="32%">
                                        <#if install.affirmFlag == 0>
                                            <input type="text" name="install.bvEpVer" value="${install.bvEpVer!""}" maxLength="20" size="40"/>
                                        <#else>
                                            <label>${install.bvEpVer!""}</label>
                                            <input type="hidden" name="install.bvEpVer" value="${install.bvEpVer!""}"/>
                                        </#if>
                                        </td>
                                   </tr>    			                      
			                     <#else> 
			                       <tr>
                                       <td width="18%" class="lcell"><label><@s.text name="install.epVer" /></label></td>
                                       <td width="32%">
                                       <#if install.affirmFlag == 0>
                                            <input type="text" name="install.brmEpVer" value="${install.brmEpVer!""}" maxLength="20" size="40"/>
                                        <#else>
                                            <label>${install.brmEpVer!""}</label>
                                            <input type="hidden" name="install.brmEpVer" value="${install.brmEpVer!""}"/>
                                        </#if>
                                        </td>
                                        <td width="18%" class="lcell"><label></label></td>
                                        <td width="32%">
                                        <#if install.affirmFlag == 0>
                                            <input type="text" name="install.bvEpVer" value="${install.bvEpVer!""}" maxLength="20" size="40"/>
                                        <#else>
                                            <label>${install.bvEpVer!""}</label>
                                            <input type="hidden" name="install.bvEpVer" value="${install.bvEpVer!""}"/>
                                        </#if>
                                        </td>
                                   </tr>
			                     </#if>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.keyNo" /></label></td>
			                            <td width="82%" colspan=3>
			                            <#if install.affirmFlag == 0>
			                                <input type="text" name="install.keyNo" value="${install.keyNo!""}" maxLength="20" style="width:90%"/>
			                            <#else>
			                                <label>${install.keyNo!""}</label>
			                                <input type="hidden" name="install.keyNo" value="${install.keyNo!""}"/>
			                            </#if>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="install.note" /></label></td>
			                            <td width="82%" colspan=3 style="word-break:break-all;word-wrap:break-word">
			                            <#if install.affirmFlag == 0>
			                                <input type="text" name="install.note" value="${install.note!""}" maxLength="800" size="120"/>
			                            <#else>
			                                <label>${install.note!""}</label>
			                                <input type="hidden" name="install.note" value="${install.note!""}"/>
			                            </#if>
			                            </td>
			                        </tr>
			                    </tbody>
			                </table>
                         </td>
                      </tr>
                   </tbody>
                </table>                
                <br>
                
               <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
                   <tbody>
                      <tr>
                        <td> 
			               <table width="90%" align="center" class="field_tbl" style="table-layout:fixed">
			                    <tbody>
			                        <#if install.affirmFlag == 0>
				                        <tr>
				                            <td width="18%" class="lcell"><label><@s.text name="install.OS" /></label></td>
				                            <td width="32%">
				                                <select name="install.os" style="width:80%">
				                                    <@s.action name="OS_drop" executeResult="true" ignoreContextParams="true">
				                                        <@s.param name="selectedOS">${install.os!""}</@s.param>
				                                    </@s.action>
				                                </select>
				                            </td>
                                            <td width="18%" class="lcell"><label><@s.text name="install.osPermit" /></label></td>
	                                        <td width="32%">
	                                            <select name="install.osPermitId" style="width:80%">
	                                                <@s.action name="osPermit_drop" executeResult="true" ignoreContextParams="true">
	                                                    <@s.param name="selectedOsPermit">${install.osPermitId!""}</@s.param>
	                                                </@s.action>
	                                            </select>
	                                        </td>				                            
				                        </tr>
				                        <tr>
                                            <td width="18%" class="lcell"><label><@s.text name="install.platform" /></label></td>
				                            <td width="32%">
				                                <select name="install.platform" style="width:80%">
				                                    <@s.action name="platform_drop" executeResult="true" ignoreContextParams="true">
				                                        <@s.param name="selectedPlatform">${install.platform!""}</@s.param>
				                                    </@s.action>
				                                </select>
				                            </td>
				                            <td width="18%" class="lcell"><label><@s.text name="install.platformRev" /></label></td>
                                            <td width="32%">
                                                <input type="text" name="install.platformRev" value="${install.platformRev!""}" maxLength="20" style="width:80%"/>
                                            </td>   
				                        </tr>
				                       <#if install.faultMachineType?? && install.faultMachineType=="G-ABIO">
	                                        <tr>
	                                            <td width="18%" class="lcell"><label><@s.text name="install.EnIssueSole" /></label></td>
	                                            <td width="32%"><input type="text" name="install.jpr" value="${install.jpr!""}" maxLength="20" style="width:80%"/></td>                                     
	                                            <td width="18%" class="lcell"><label><@s.text name="install.bid" /></label></td>
	                                            <td width="32%"><input type="text" name="install.spr" value="${install.spr!""}" maxLength="20" style="width:80%"/></td>
	                                        </tr>
	                                        <tr>
	                                            <td width="18%" class="lcell"><label><@s.text name="install.passUnitF" /></label></td>
	                                            <td width="32%"><input type="text" name="install.mcu" value="${install.mcu!""}" maxLength="20" style="width:80%"/></td>                                     
	                                            <td width="18%" class="lcell"><label><@s.text name="install.passUnitR" /></label></td>
	                                            <td width="32%"><input type="text" name="install.deskey" value="${install.deskey!""}" maxLength="20" style="width:80%"/></td>
	                                        </tr>
	                                        <tr>
	                                            <td width="18%" class="lcell"><label><@s.text name="install.escrowUnit" /></label></td>
	                                            <td width="82%" colspan="3"><input type="text" name="install.hcm" value="${install.hcm!""}" maxLength="20" style="width:32%"/></td>                                            
	                                        </tr>
	                                        <tr>
	                                            <td width="18%" class="lcell"><label><@s.text name="install.flatPoss" /></label></td>
	                                            <td width="82%" colspan="3"><input type="text" name="install.bv" value="${install.bv!""}" maxLength="200" size="120" /></td>
	                                        </tr>    
                                        <#elseif install.faultMachineType?? && install.faultMachineType=="ATM">
	                                        <tr>
	                                            <td width="18%" class="lcell"><label><@s.text name="install.jpr" /></label></td>
	                                            <td width="32%"><input type="text" name="install.jpr" value="${install.jpr!""}" maxLength="20" style="width:80%"/></td>                                     
	                                            <td width="18%" class="lcell"><label><@s.text name="install.spr" /></label></td>
	                                            <td width="32%"><input type="text" name="install.spr" value="${install.spr!""}" maxLength="20" style="width:80%"/></td>
	                                        </tr>
	                                        <tr>
	                                            <td width="18%" class="lcell"><label><@s.text name="install.mcu" /></label></td>
	                                            <td width="32%"><input type="text" name="install.mcu" value="${install.mcu!""}" maxLength="20" style="width:80%"/></td>                                     
	                                            <td width="18%" class="lcell"><label><@s.text name="install.deskey" /></label></td>
	                                            <td width="32%"><input type="text" name="install.deskey" value="${install.deskey!""}" maxLength="20" style="width:80%"/></td>
	                                        </tr>
	                                        <tr>
	                                            <td width="18%" class="lcell"><label><@s.text name="install.hcm" /></label></td>
	                                            <td width="82%" colspan="3"><input type="text" name="install.hcm" value="${install.hcm!""}" maxLength="20" style="width:32%"/></td>                                            
	                                        </tr>
	                                        <tr>
	                                            <td width="18%" class="lcell"><label><@s.text name="install.bv" /></label></td>
	                                            <td width="82%" colspan="3"><input type="text" name="install.bv" value="${install.bv!""}" maxLength="200" size="120" /></td>
	                                        </tr>
	                                   <#else>                                            
                                            <tr>
                                                <td width="18%" class="lcell"><label></label></td>
                                                <td width="32%"><input type="text" name="install.jpr" value="${install.jpr!""}" maxLength="20" style="width:80%"/></td>                                     
                                                <td width="18%" class="lcell"><label></label></td>
                                                <td width="32%"><input type="text" name="install.spr" value="${install.spr!""}" maxLength="20" style="width:80%"/></td>
                                            </tr>
                                            <tr>
                                                <td width="18%" class="lcell"><label></label></td>
                                                <td width="32%"><input type="text" name="install.mcu" value="${install.mcu!""}" maxLength="20" style="width:80%"/></td>                                     
                                                <td width="18%" class="lcell"><label></label></td>
                                                <td width="32%"><input type="text" name="install.deskey" value="${install.deskey!""}" maxLength="20" style="width:80%"/></td>
                                            </tr>
                                            <tr>
                                                <td width="18%" class="lcell"><label></label></td>
                                                <td width="82%" colspan="3"><input type="text" name="install.hcm" value="${install.hcm!""}" maxLength="20" style="width:32%"/></td>                                            
                                            </tr>
                                            <tr>
                                                <td width="18%" class="lcell"><label></label></td>
                                                <td width="82%" colspan="3"><input type="text" name="install.bv" value="${install.bv!""}" maxLength="200" size="120" /></td>
                                            </tr>                                      
                                       </#if>     

				                    <#else>
				                        <tr>
				                            <td width="18%" class="lcell"><label><@s.text name="install.OS" /></label></td>
				                            <td width="32%">
				                                <label>${install.osName!""}</label>
				                                <input type="hidden" name="install.os" value="${install.os!""}"/>
				                                <input type="hidden" name="install.osName" value="${install.osName!""}"/>
				                            </td>
				                            <td width="18%" class="lcell"><label><@s.text name="install.osPermit" /></label></td>
                                            <td width="32%">
                                                <label>${install.osPermitName!""}</label>
                                                <input type="hidden" name="install.osPermitId" value="${install.osPermitId!""}"/>
                                                <input type="hidden" name="install.osPermitName" value="${install.osPermitName!""}"/>
                                            </td>
				                        </tr>
				                        <tr>
                                            <td width="18%" class="lcell"><label><@s.text name="install.platform" /></label></td>
                                            <td width="32%">
                                                <label>${install.platformName!""}</label>
                                                <input type="hidden" name="install.platform" value="${install.platform!""}"/>
                                                <input type="hidden" name="install.platformName" value="${install.platformName!""}"/>
                                            </td>
                                           <td width="18%" class="lcell"><label><@s.text name="install.platformRev" /></label></td>
                                            <td width="32%">
                                                <label>${install.platformRev!""}</label>
                                                <input type="hidden" name="install.platformRev" value="${install.platformRev!""}"/>
                                            </td>        
                                        </tr>
			                        <#if install.faultMachineType?? && install.faultMachineType=="G-ABIO">	
                                        <tr>
                                            <td width="18%" class="lcell"><label><@s.text name="install.EnIssueSole" /></label></td>
                                            <td width="32%">
                                                <label>${install.jpr!""}</label>
                                                <input type="hidden" name="install.jpr" value="${install.jpr!""}"/>
                                            </td>                                                                
                                            <td width="18%" class="lcell"><label><@s.text name="install.bid" /></label></td>
                                            <td width="32%">
                                                <label>${install.spr!""}</label>
                                                <input type="hidden" name="install.spr" value="${install.spr!""}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="18%" class="lcell"><label><@s.text name="install.passUnitF" /></label></td>
                                            <td width="32%">
                                                <label>${install.mcu!""}</label>
                                                <input type="hidden" name="install.mcu" value="${install.mcu!""}"/>
                                            </td>                                       
                                            <td width="18%" class="lcell"><label><@s.text name="install.passUnitR" /></label></td>
                                            <td width="32%">
                                                <label>${install.deskey!""}</label>
                                                <input type="hidden" name="install.deskey" value="${install.deskey!""}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                           <td width="18%" class="lcell"><label><@s.text name="install.escrowUnit" /></label></td>
                                           <td width="82%" colspan="3">
                                               <label>${install.hcm!""}</label>
                                               <input type="hidden" name="install.hcm" value="${install.hcm!""}"/>
                                           </td>
                                        </tr>    
                                        <tr>
                                            <td width="18%" class="lcell"><label><@s.text name="install.flatPoss" /></label></td>
                                            <td width="82%" colspan="3" style="word-break:break-all;word-wrap:break-word">
                                                <label>${install.bv!""}</label>
                                                <input type="hidden" name="install.bv" value="${install.bv!""}"/>
                                            </td>
                                        </tr>			
			                        
			                         <#elseif install.faultMachineType?? && install.faultMachineType=="ATM">
			                            <tr>
                                            <td width="18%" class="lcell"><label><@s.text name="install.jpr" /></label></td>
                                            <td width="32%">
                                                <label>${install.jpr!""}</label>
                                                <input type="hidden" name="install.jpr" value="${install.jpr!""}"/>
                                            </td>                                     		                            
			                                <td width="18%" class="lcell"><label><@s.text name="install.spr" /></label></td>
			                                <td width="32%">
			                                    <label>${install.spr!""}</label>
			                                    <input type="hidden" name="install.spr" value="${install.spr!""}"/>
			                                </td>
			                            </tr>
			                            <tr>
                                            <td width="18%" class="lcell"><label><@s.text name="install.mcu" /></label></td>
                                            <td width="32%">
                                                <label>${install.mcu!""}</label>
                                                <input type="hidden" name="install.mcu" value="${install.mcu!""}"/>
                                            </td>			                            
			                                <td width="18%" class="lcell"><label><@s.text name="install.deskey" /></label></td>
			                                <td width="32%">
			                                    <label>${install.deskey!""}</label>
			                                    <input type="hidden" name="install.deskey" value="${install.deskey!""}"/>
			                                </td>
			                            </tr>
			                            <tr>
			                               <td width="18%" class="lcell"><label><@s.text name="install.hcm" /></label></td>
                                           <td width="82%" colspan="3">
                                               <label>${install.hcm!""}</label>
                                               <input type="hidden" name="install.hcm" value="${install.hcm!""}"/>
                                           </td>
                                        </tr>    
			                            <tr>
			                                <td width="18%" class="lcell"><label><@s.text name="install.bv" /></label></td>
			                                <td width="82%" colspan="3" style="word-break:break-all;word-wrap:break-word">
			                                    <label>${install.bv!""}</label>
			                                    <input type="hidden" name="install.bv" value="${install.bv!""}"/>
			                                </td>
			                            </tr>
			                          <#else>  
                                        <tr>
                                            <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                            <td width="32%">
                                                <label>${install.jpr!""}</label>
                                                <input type="hidden" name="install.jpr" value="${install.jpr!""}"/>
                                            </td>                                                                       
                                            <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                            <td width="32%">
                                                <label>${install.spr!""}</label>
                                                <input type="hidden" name="install.spr" value="${install.spr!""}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                            <td width="32%">
                                                <label>${install.mcu!""}</label>
                                                <input type="hidden" name="install.mcu" value="${install.mcu!""}"/>
                                            </td>                                       
                                            <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                            <td width="32%">
                                                <label>${install.deskey!""}</label>
                                                <input type="hidden" name="install.deskey" value="${install.deskey!""}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                           <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                           <td width="82%" colspan="3">
                                               <label>${install.hcm!""}</label>
                                               <input type="hidden" name="install.hcm" value="${install.hcm!""}"/>
                                           </td>
                                        </tr>    
                                        <tr>
                                            <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                            <td width="82%" colspan="3" style="word-break:break-all;word-wrap:break-word">
                                                <label>${install.bv!""}</label>
                                                <input type="hidden" name="install.bv" value="${install.bv!""}"/>
                                            </td>
                                        </tr>			                          
			                          </#if>
			                        </#if>
			                    </tbody>
			                </table>
		                </td>
		              </tr>
		           </tbody>
		         </table>                 
                <br>
                
                <input type="hidden" name="install.exclusiveKey" value="${install.exclusiveKey!""}"/>
                <input type="hidden" name="install.id" value="${install.id!""}"/>
                <input type="hidden" name="install.affirmFlag" value="${install.affirmFlag!""}"/>
              </form>
              <div class="btn_row">
                    <#if loginUser.hasPermission("PR005_31")>
                        <button type="button" onClick="modifyAction('PR005_31')" style="width:100px;"><@s.text name="btn_modify_label" /></button>
                    </#if>    
                    <#if loginUser.hasPermission("PR005_36")>
                       <button type="button" onClick="modifyDialog('PR005_36')" style="width:100px;"><@s.text name="btn_history_modify" /></button>
                    </#if>
              </div>
        </div>
    </div>
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>