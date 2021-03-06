<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="installation_apply_modify" /></title>
<#include "../common_header.ftl"/>

<script type="text/javascript">

    $(document).ready(function(){
        hide();
        typeLoad("${instApply.type!""}");
        textCounter('applyerNote','applyerNoteLeft');
        textCounter('adminNote','adminNoteLeft');
    });

function changeAction(url,type)
   {
      var flag= confirm("<@s.text name="BSC00007" />");
      if(flag==true){
          var form=document.getElementById("instApplyForm");
          form.action=url;
          form.submit();  
      }
   }
function typeChange(type)
    {
        switch(type)
        {
            case "":
            case "6":
                clear();
                hide();
                break;
            case "1":
                clear();
                hide();
                $("#useStatusId").show();
                $("#useStatusId").val("1");
                $("#useStatusId").focus();
                break;
            case "2":
                clear();
                hide();
                $("#useStatusId").show();
                $("#useStatusId").val("2");
                $("#useStatusId").focus();
                break;
            case "3":
                clear();
                hide();
                $("#installPlace").show();
                $("#instPlaceTypeId").show();
                $("#installPlace").focus();
                break;
            case "4":
                clear();
                hide();                    
                $("#guaranteeStartDate").show();
                $("#guaranteeEndDate").show();
                $("#guaranteePeriod").show();
                $("#guaranteeStartDate").datepicker();
                $("#guaranteeEndDate").datepicker();
                $("#guaranteeStartDate").focus();
                break;
            case "5":
                clear();
                hide();   
                $("#contact").show();
                $("#officePhone").show();
                $("#mobilePhone").show();
                $("#fax").show();
                $("#email").show();
                break;
        }
    }
    
function clear()
   {
        $("#guaranteeStartDate").val("");
        $("#guaranteeEndDate").val("");
        $("#guaranteePeriod").val("");
        $("#installPlace").val("");
        $("#instPlaceTypeId").val("");
        $("#useStatusId").val("");
        $("#guaranteeStartDate").datepicker("destroy");
        $("#guaranteeEndDate").datepicker("destroy");
        $("#contact").val("");
        $("#officePhone").val("");
        $("#mobilePhone").val("");
        $("#fax").val("");
        $("#email").val("");
   }
   
function hide()
   {
        $("#guaranteeStartDate").hide();
        $("#guaranteeEndDate").hide();
        $("#guaranteePeriod").hide();
        $("#installPlace").hide();
        $("#instPlaceTypeId").hide();
        $("#useStatusId").hide();
        $("#contact").hide();
        $("#officePhone").hide();
        $("#mobilePhone").hide();
        $("#fax").hide();
        $("#email").hide();
   }

function typeLoad(type)
    {
        switch(type)
        {
            case "1":
            case "2":
               $("#useStatusId").show();
               break;
            case "3":
                $("#installPlace").show();
                $("#instPlaceTypeId").show();
                break;
            case "4":
                $("#guaranteeStartDate").show();
                $("#guaranteeEndDate").show();
                $("#guaranteePeriod").show();
                $("#guaranteeStartDate").datepicker();
                $("#guaranteeEndDate").datepicker();
                break;
           case "5":
                $("#contact").show();
                $("#officePhone").show();
                $("#mobilePhone").show();
                $("#fax").show();
                $("#email").show();
                break;
            case "":
            case "6":
                break;
        }
    }
function textCounter(field,left) 
    {
        var maxlimit = 80;
        if ($("#" + field).val().length > maxlimit){
            $("#" + field).val($("#" + field).val().substring(0, maxlimit));
        }
        $("#" + left).text(maxlimit - $("#" + field).val().length);
    }
function ShowDetailInstall() {
    $("#detailInstall").toggle();
    if ($("#detailInstallImg").attr("src") == "../../../images/plus.png") {
        $("#detailInstallImg").attr("src", "../../../images/minus.png");
    } else {
        $("#detailInstallImg").attr("src", "../../../images/plus.png");
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
            <form id="instApplyForm" method="post">
		        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		           <tbody>
		              <tr>
		                <td>              
							<table class="field_tbl" width="90%" align="center">
								<tr>
			                        <th colspan="4"><@s.text name="applyTable" /></th>
								</tr>
								<tr>
			                        <td class="lcell" width="18%"><label><@s.text name="applyTypeName" /></label></td>
									<td width="82%" colspan=3>
										<select id="type" name="instApply.type" onChange="typeChange(this.value)">
											<@s.action name="applyType_drop" executeResult="true" ignoreContextParams="true">
				                                <@s.param name="selectedapplyType">${instApply.type!""}</@s.param>
											</@s.action>
										</select>&nbsp;<label class="needed"><@s.text name="selectNeeded" /></label>
									</td>
								</tr>
								<tr>
									<td class="lcell" width="18%"><label><@s.text name="applyerName" /></label></td>
									<td width="32%"><label>${loginUser.name!""}</label></td>
									<td class="lcell" width="18%"><label><@s.text name="applyDate" /></label></td>
									<td width="32%"><label>${instApply.applyDate!""}</label></td>
									<input type="hidden" name="instApply.applyDate" value="${instApply.applyDate!""}">
								</tr>
								<tr>
									<td class="lcell" width="18%" ><label><@s.text name="applyerNote" /></label></td>
									<td width="82%" colspan=3>
										<textarea id="applyerNote" rows="2" cols="70" name="instApply.applyerNote" style="overflow-y:auto"
											onPropertyChange=textCounter('applyerNote','applyerNoteLeft') oninput=textCounter('applyerNote','applyerNoteLeft')>${instApply.applyerNote!""}</textarea>
											&nbsp;<label class="needed"><@s.text name="maxCount" /></label>
											&nbsp;<label class="needed"><@s.text name="leftCount" /><span id="applyerNoteLeft">80</span></label>
									</td>
								</tr>
								<tr>
									<td class="lcell" width="18%"><label><@s.text name="adminNote" /></label></td>
									<td width="82%" colspan=3>
										<textarea id="adminNote" rows="2" cols="70" name="instApply.adminNote" style="overflow-y:auto"
											onPropertyChange=textCounter('adminNote','adminNoteLeft') oninput=textCounter('adminNote','adminNoteLeft')>${instApply.adminNote!""}</textarea> 
											&nbsp;<label class="needed"><@s.text name="maxCount" /></label>
											&nbsp;<label class="needed"><@s.text name="leftCount" /><span id="adminNoteLeft">80</span></label>
									</td>
								</tr>
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
			                <table style="margin-bottom:0px" width="90%" align="center" class="field_tbl">
			                    <tbody>
			                        <tr>
			                            <th style="border-right:none" width="90%" class="lcell" align="center"><@s.text name="installation_view" /></th>
			                            <th style="border-left:none" width="10%" align="right" class="lcell">
			                                <a href="javascript:ShowDetailInstall()">
			                                    <img id="detailInstallImg" style="vertical-align:top;horizontal-align:center;border:0px none;" src="../../../images/plus.png"></img>
			                                </a>
			                            </th>
			                        </tr>
			                    </tbody>
			                </table>
                         </td>
                      </tr>
                   </tbody>
                </table> 			                
                <div id="detailInstall" style="display:none">
                <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
                   <tbody>
                      <tr>
                        <td>                 
				            <table  width="90%" align="center" class="field_tbl"  style="table-layout:fixed">
				                <tbody>
					                <tr>
					                    <td class="lcell" width="18%" class="lcell" ><label><@s.text name="productCategory" /></label></td>
					                    <td width="32%"><label>${install.productCategoryName!""}</label></td>
						                    <input type="hidden" value="${install.productCategoryName!""}" name="install.productCategoryName" >
					                    <td class="lcell" width="18%" class="lcell" ><label><@s.text name="productModel" /></label></td>
					                    <td width="32%"><label>${install.model!""}</label></td>
						                    <input type="hidden" value="${install.model!""}" name="install.model" >
					                </tr>
					                <tr>
					                    <td width="18%" class="lcell"><label><@s.text name="manufactureNo" /></label></td>
					                    <td width="32%"><label>${install.manufactureNo!""}</label></td>
						                    <input type="hidden" value="${install.manufactureNo!""}" name="install.manufactureNo" >
					                    <td width="18%" class="lcell"><label><@s.text name="customerCompany" /></label></td>
					                    <td width="32%"><label>${install.customerName!""}</label></td>
						                    <input type="hidden" value="${install.customerName!""}" name="install.customerName" >
					                </tr>
					                <tr>
					                    <td width="18%" class="lcell"><label><@s.text name="customerProvince" /></label></td>
					                    <td width="32%"><label>${install.customerProvince!""}</label></td>
						                    <input type="hidden" value="${install.customerProvince!""}" name="install.customerProvince" >
					                    <td width="18%" class="lcell"><label><@s.text name="customerCity" /></label></td>
					                    <td width="32%"><label>${install.customerCity!""}</label></td>
						                    <input type="hidden" value="${install.customerCity!""}" name="install.customerCity" >
					                </tr>
					                <tr>
					                    <td width="18%" class="lcell"><label><@s.text name="saleContractCompName" /></label></td>
					                    <td width="32%"><label>${install.saleContractCompName!""}</label></td>
						                    <input type="hidden" value="${install.saleContractCompName!""}" name="install.saleContractCompName" >
					                    <td width="18%" class="lcell"><label><@s.text name="installCompName" /></label></td>
					                    <td width="32%"><label>${install.installCompName!""}</label></td>
						                    <input type="hidden" value="${install.installCompName!""}" name="install.installCompName" >
					                </tr>
					                <tr>
					                    <td width="18%" class="lcell"><label><@s.text name="repairCompanyName" /></td>
					                    <td width="82%" colspan=3><label>${install.nowRepairCompanyName!""}</label></td>
						                    <input type="hidden" value="${install.nowRepairCompanyName!""}" name="install.nowRepairCompanyName" >
						                    <input type="hidden" value="${install.firstRepairCompanyId!""}" name="install.firstRepairCompanyId" >
						                    <input type="hidden" value="${install.nowRepairCompanyId!""}" name="install.nowRepairCompanyId" >
					                </tr>
					                <#if loginUser.filter("install_mng_all_data") || install.firstRepairCompanyId == install.nowRepairCompanyId>
					                    <tr>
					                        <td width="18%" class="lcell" aligh="right"><label><@s.text name="indentureNo" /></label></td>
					                        <td width="82%" colspan=3><label>${install.indentureNo!""}</label></td>
						                        <input type="hidden" value="${install.indentureNo!""}" name="install.indentureNo" >
					                    </tr>
					                    <tr>
					                        <td width="18%" class="lcell"><label><@s.text name="fobDate" /></label></td>
					                        <td width="32%"><label>${install.fobDate!""}</label></td>
						                        <input type="hidden" value="${install.fobDate!""}" name="install.fobDate" >
					                        <td width="18%" class="lcell"><label><@s.text name="installDate" /></label></td>
					                        <td width="32%"><label>${install.installDate!""}</label></td>
						                        <input type="hidden" value="${install.installDate!""}" name="install.installDate" >
					                    </tr>
					                    <tr>
					                        <td width="18%" class="lcell"><label><@s.text name="openDate" /></label></td>
					                        <td width="32%"><label>${install.openDate!""}</label></td>
						                        <input type="hidden" value="${install.openDate!""}" name="install.openDate" >
					                        <td width="18%" class="lcell"><label><@s.text name="acceptDate" /></label></td>
					                        <td width="32%"><label>${install.acceptDate!""}</label></td>
						                        <input type="hidden" value="${install.acceptDate!""}" name="install.acceptDate" >
					                    </tr>
					                    <tr>
					                        <td width="18%" class="lcell"><label><@s.text name="installer" /></label></td>
					                        <td width="32%"><label>${install.installer!""}</label></td>
						                        <input type="hidden" value="${install.installer!""}" name="install.installer" >
					                        <td width="18%" class="lcell"><label><@s.text name="installerId" /></label></td>
					                        <td width="32%"><label>${install.installerId!""}</label></td>
						                        <input type="hidden" value="${install.installerId!""}" name="install.installerId" >
					                    </tr>
					                </#if>
					                <tr>
					                    <td width="18%" class="lcell"><label><@s.text name="branchCompanyName" /></label></td>
					                    <td width="82%" colspan=3><label>${install.branchCompanyName!""}</label></td>
						                    <input type="hidden" value="${install.branchCompanyName!""}" name="install.branchCompanyName" >
					                </tr>
					                <tr>
					                    <td width="18%" class="lcell"><label><@s.text name="purpose" /></label></td>
					                    <td width="82%" colspan=3><label>${install.purposeName!""}</label></td>
						                    <input type="hidden" value="${install.purposeName!""}" name="install.purposeName" >
					                    
					                </tr>
                                  <#if install.faultMachineType?? && install.faultMachineType=="ATM"> 
                                    <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="brmEpVer" /></label></td>
                                        <td width="32%"><label>${install.brmEpVer!""}</label></td>
                                            <input type="hidden" value="${install.brmEpVer!""}" name="install.brmEpVer" >
                                        <td width="18%" class="lcell"><label><@s.text name="bvEpVer" /></label></td>
                                        <td width="32%"><label>${install.bvEpVer!""}</label></td>
                                            <input type="hidden" value="${install.bvEpVer!""}" name="install.bvEpVer" >
                                    </tr>                                 
                                  <#elseif  install.faultMachineType?? && install.faultMachineType=="G-ABIO">
                                   <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="mainEpVer" /></label></td>
                                        <td width="32%"><label>${install.brmEpVer!""}</label></td>
                                            <input type="hidden" value="${install.brmEpVer!""}" name="install.brmEpVer" >
                                        <td width="18%" class="lcell"><label><@s.text name="bhclEpVer" /></label></td>
                                        <td width="32%"><label>${install.bhclEpVer!""}</label></td>
                                            <input type="hidden" value="${install.bhclEpVer!""}" name="install.bhclEpVer" >
                                    </tr>                                   
                                   <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="trclEpVer" /></label></td>
                                        <td width="32%"><label>${install.trclEpVer!""}</label></td>
                                            <input type="hidden" value="${install.trclEpVer!""}" name="install.trclEpVer" >
                                        <td width="18%" class="lcell"><label><@s.text name="bidEpVer" /></label></td>
                                        <td width="32%"><label>${install.bvEpVer!""}</label></td>
                                            <input type="hidden" value="${install.bvEpVer!""}" name="install.bvEpVer" >
                                    </tr>                                   
                                  <#else> 
                                     <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="epVer" /></label></td>
                                        <td width="32%"><label>${install.brmEpVer!""}</label></td>
                                            <input type="hidden" value="${install.brmEpVer!""}" name="install.brmEpVer" >
                                        <td width="18%" class="lcell"><label></label></td>
                                        <td width="32%"><label>${install.bvEpVer!""}</label></td>
                                            <input type="hidden" value="${install.bvEpVer!""}" name="install.bvEpVer" >
                                    </tr>                                   
                                  </#if>        					                

					                <tr>
					                    <td width="18%" class="lcell"><label><@s.text name="keyNo" /></label></td>
					                    <td width="82%" colspan=3><label>${install.keyNo!""}</label></td>
						                    <input type="hidden" value="${install.keyNo!""}" name="install.keyNo" >
					                </tr>
					                <tr>
					                    <td width="18%" class="lcell"><label><@s.text name="note" /></label></td>
					                    <td width="82%" colspan=3 style="word-break:break-all;word-wrap:break-word"><label>${install.note!""}</label></td>
						                    <input type="hidden" value="${install.note!""}" name="install.note" >
					                </tr>
				                    <tr>
				                        <td width="18%" class="lcell"><label><@s.text name="OS" /></label></td>
				                        <td width="32%"><label>${install.osName!""}</label></td>
					                    <input type="hidden" value="${install.osName!""}" name="install.osName" >
                                        <td width="18%" class="lcell"><label><@s.text name="osPermit" /></label></td>
                                        <td width="32%"><label>${install.osPermitName!""}</label></td>
                                        <input type="hidden" value="${install.osPermitName!""}" name="install.osPermitName" >   
				                    </tr>
				                    <tr>
				                        <td width="18%" class="lcell"><label><@s.text name="platform" /></label></td>
				                        <td width="32%"><label>${install.platformName!""}</label></td>
					                    <input type="hidden" value="${install.platformName!""}" name="install.platformName" >
                                        <td width="18%" class="lcell"><label><@s.text name="platformRev" /></label></td>
                                        <td width="32%"><label>${install.platformRev!""}</label></td>
                                        <input type="hidden" value="${install.platformRev!""}" name="install.platformRev" > 					                    
				                    </tr>
				                    
				               <#if install.faultMachineType?? && install.faultMachineType=="G-ABIO">
				                    <tr>
				                        <td width="18%" class="lcell"><label><@s.text name="EnIssueSole" /></label></td>
                                        <td width="32%"><label>${install.jpr!""}</label></td>
                                        <input type="hidden" value="${install.jpr!""}" name="install.jpr" >     
				                        <td width="18%" class="lcell"><label><@s.text name="bid" /></label></td>
				                        <td width="32%"><label>${install.spr!""}</label></td>
					                    <input type="hidden" value="${install.spr!""}" name="install.spr" >
				                    </tr>
				                    <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="passUnitF" /></label></td>
                                        <td width="32%"><label>${install.mcu!""}</label></td>
                                        <input type="hidden" value="${install.mcu!""}" name="install.mcu" >				                    
				                        <td width="18%" class="lcell"><label><@s.text name="passUnitR" /></label></td>
				                        <td width="32%"><label>${install.deskey!""}</label></td>
					                    <input type="hidden" value="${install.deskey!""}" name="install.deskey" >
				                    </tr>
                                    <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="escrowUnit" /></label></td>
                                        <td width="82%" colspan="3"><label>${install.hcm!""}</label></td>
                                        <input type="hidden" value="${install.hcm!""}" name="install.hcm" >
                                    </tr>                    
				                    <tr>
				                        <td width="18%" class="lcell"><label><@s.text name="flatPoss" /></label></td>
				                        <td width="82%" colspan="3" style="word-break:break-all;word-wrap:break-word"><label>${install.bv!""}</label></td>
					                    <input type="hidden" value="${install.bv!""}" name="install.bv" >
				                    </tr>
				                   <#elseif install.faultMachineType?? && install.faultMachineType=="ATM">
				                    <tr>
				                        <td width="18%" class="lcell"><label><@s.text name="jpr" /></label></td>
                                        <td width="32%"><label>${install.jpr!""}</label></td>
                                        <input type="hidden" value="${install.jpr!""}" name="install.jpr" >     
				                        <td width="18%" class="lcell"><label><@s.text name="spr" /></label></td>
				                        <td width="32%"><label>${install.spr!""}</label></td>
					                    <input type="hidden" value="${install.spr!""}" name="install.spr" >
				                    </tr>
				                    <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="mcu" /></label></td>
                                        <td width="32%"><label>${install.mcu!""}</label></td>
                                        <input type="hidden" value="${install.mcu!""}" name="install.mcu" >				                    
				                        <td width="18%" class="lcell"><label><@s.text name="deskey" /></label></td>
				                        <td width="32%"><label>${install.deskey!""}</label></td>
					                    <input type="hidden" value="${install.deskey!""}" name="install.deskey" >
				                    </tr>
                                    <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="hcm" /></label></td>
                                        <td width="82%" colspan="3"><label>${install.hcm!""}</label></td>
                                        <input type="hidden" value="${install.hcm!""}" name="install.hcm" >
                                    </tr>                    
				                    <tr>
				                        <td width="18%" class="lcell"><label><@s.text name="bv" /></label></td>
				                        <td width="82%" colspan="3" style="word-break:break-all;word-wrap:break-word"><label>${install.bv!""}</label></td>
					                    <input type="hidden" value="${install.bv!""}" name="install.bv" >
				                    </tr>
				                <#else>  
                                    <tr>
                                        <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                        <td width="32%"><label>${install.jpr!""}</label></td>
                                        <input type="hidden" value="${install.jpr!""}" name="install.jpr" >     
                                        <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                        <td width="32%"><label>${install.spr!""}</label></td>
                                        <input type="hidden" value="${install.spr!""}" name="install.spr" >
                                    </tr>
                                    <tr>
                                        <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                        <td width="32%"><label>${install.mcu!""}</label></td>
                                        <input type="hidden" value="${install.mcu!""}" name="install.mcu" >                                 
                                        <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                        <td width="32%"><label>${install.deskey!""}</label></td>
                                        <input type="hidden" value="${install.deskey!""}" name="install.deskey" >
                                    </tr>
                                    <tr>
                                        <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                        <td width="82%" colspan="3"><label>${install.hcm!""}</label></td>
                                        <input type="hidden" value="${install.hcm!""}" name="install.hcm" >
                                    </tr>                    
                                    <tr>
                                        <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                        <td width="82%" colspan="3" style="word-break:break-all;word-wrap:break-word"><label>${install.bv!""}</label></td>
                                        <input type="hidden" value="${install.bv!""}" name="install.bv" >
                                    </tr>				                  
				                </#if>   
				                </tbody>
				            </table>
                         </td>
                      </tr>
                   </tbody>
                </table> 				            
                </div>
                <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
                   <tbody>
                      <tr>
                        <td>                 
			                <table class="field_tbl" width="90%" align="center">
			                  <tr>
			                      <th colspan="2"><label><@s.text name="applyBefor" /></label></th>
			                      <th><label>&nbsp;</label></th>
			                      <th><label><@s.text name="applyafter" /></label></th>
			                  </tr>
			                    <tr>
			                        <td class="lcell" width="18%"><label><@s.text name="guaranteeStartDate" /></label></td>
			                        <td width="32%"><label>${install.guaranteeStartDate!""}</label></td>
			                        <input type="hidden" name="install.guaranteeStartDate" value="${install.guaranteeStartDate!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <input id="guaranteeStartDate" type="text" name="instApply.guaranteeStartDate" value="${instApply.guaranteeStartDate!""}" maxLength="10" size="20"/>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="lcell" width="18%"><label><@s.text name="guaranteeEndDate" /></label></td>
			                        <td width="32%"><label>${install.guaranteeEndDate!""}</label></td>
			                        <input type="hidden" name="install.guaranteeEndDate" value="${install.guaranteeEndDate!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <input id="guaranteeEndDate" type="text" name="instApply.guaranteeEndDate" value="${instApply.guaranteeEndDate!""}" maxLength="10" size="20"/>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="lcell" width="18%"><label><@s.text name="guaranteePeriod" /></label></td>
			                        <td width="32%"><label>${install.guaranteePeriod!""}</label></td>
			                        <input type="hidden" name="install.guaranteePeriod" value="${install.guaranteePeriod!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <input type="text" id="guaranteePeriod" name="instApply.guaranteePeriod" value="${instApply.guaranteePeriod!""}" maxLength="2" size="5">
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="lcell" width="18%"><label><@s.text name="installPlace" /></label></td>
			                        <td width="32%"><label>${install.installPlace!""}</label></td>
			                        <input type="hidden" name="install.installPlace" value="${install.installPlace!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <input type="text" id="installPlace" name="instApply.installPlace" value="${instApply.installPlace!""}" maxLength="40" style="width:80%">
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="lcell" width="18%"><label><@s.text name="instPlaceTypeName" /></label></td>
			                        <td width="32%"><label>${install.instPlaceTypeName!""}</label></td>
			                        <input type="hidden" name="install.instPlaceTypeName" value="${install.instPlaceTypeName!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <select id="instPlaceTypeId" name="instApply.instPlaceTypeId" style="width:150px">
			                                <@s.action name="installPlaceType_drop" executeResult="true" ignoreContextParams="true">
			                                    <@s.param name="selectedTypeId">${instApply.instPlaceTypeId!""}</@s.param>
			                                </@s.action>
			                           </select>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="lcell" width="18%"><label><@s.text name="useStatusId" /></label></td>
			                        <td width="32%"><label>${install.useStatus!""}</label></td>
			                        <input type="hidden" name="install.useStatus" value="${install.useStatus!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <select id="useStatusId" name="instApply.useStatusId" style="width:150px">
			                                <@s.action name="userStatus_drop" executeResult="true" ignoreContextParams="true">
			                                    <@s.param name="selectedStatusId">${instApply.useStatusId!""}</@s.param>
			                                </@s.action>
			                           </select>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="contact" /></label></td>
			                        <td width="32%"><label>${install.contact!""}</label></td>
			                        <input type="hidden" name="install.contact" value="${install.contact!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <input type="text" id="contact" name="instApply.contact" value="${instApply.contact!""}" maxLength="40" size="30">
			                        </td>
			                    </tr>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="officePhone" /></label></td>
			                        <td width="32%"><label>${install.officePhone!""}</td>
			                        <input type="hidden" name="install.officePhone" value="${install.officePhone!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <input type="text" id="officePhone" name="instApply.officePhone" value="${instApply.officePhone!""}" maxLength="20" size="30">
			                        </td>
			                    </tr>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="mobilePhone" /></label></td>
			                        <td width="32%"><label>${install.mobilePhone!""}</td>
			                        <input type="hidden" name="install.mobilePhone" value="${install.mobilePhone!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <input type="text" id="mobilePhone" name="instApply.mobilePhone" value="${instApply.mobilePhone!""}" maxLength="20" size="30">
			                        </td>
			                    </tr>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="fax" /></label></td>
			                        <td width="32%"><label>${install.fax!""}</td>
			                        <input type="hidden" name="install.fax" value="${install.fax!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <input type="text" id="fax" name="instApply.fax" value="${instApply.fax!""}" maxLength="20" size="30">
			                        </td>
			                    </tr>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="email" /></label></td>
			                        <td width="32%"><label>${install.email!""}</td>
			                        <input type="hidden" name="install.email" value="${install.email!""}">
			                        <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                        <td width="40%">
			                            <input type="text" id="email" name="instApply.email" value="${instApply.email!""}" maxLength="20" size="30">
			                        </td>
			                    </tr>
			                </table>
                         </td>
                      </tr>
                   </tbody>
                </table> 			                
                <input type="hidden" value="${instApply.id!""}" name="instApply.id" />
                <input type="hidden" value="${instApply.exclusiveKey!""}" name="instApply.exclusiveKey" />
                <input type="hidden" value="${instApply.installId!""}" name="instApply.installId" />
                <input type="hidden" name="install.faultMachineType" value="${install.faultMachineType!""}" />
            </form>
			<div class="btn_row">
			<#if loginUser.hasPermission("PR004_31")>
			    <button type="button" onClick="changeAction('PR004_31')"><@s.text name="btn_modify" /></button>
			</#if>
			</div>
        </div>
    </div>
    <#include "../footer.ftl"/>   
</div>
</#escape>
</body>
</html>