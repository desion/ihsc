<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="installation_apply_view" /></title>
<#include "../common_header.ftl"/>

<script>
function changeAction(url,type)
   {
      var form=document.getElementById("instApplyForm");
      form.action=url;
      form.submit();
   }
   
function doApply(url)
    {
	    var flag= confirm("<@s.text name="message.confirm.doApply"/>");
	    if(flag==true){
	        changeAction(url);
	    }
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
								<td width="82%" colspan=3><label>${instApply.typeName!""}</label></td>
							</tr>
							<tr>
								<td class="lcell" width="18%"><label><@s.text name="applyerName" /></label></td>
								<td width="32%"><label>${instApply.applyerName!""}</label></td>
								<td class="lcell" width="18%"><label><@s.text name="applyDate" /></label></td>
								<td width="32%"><label>${instApply.applyDate!""}</label></td>
			                </tr>
							<tr>
								<td class="lcell" width="18%"><label><@s.text name="applyerNote" /></label></td>
								<td width="620" colspan=3 style="word-wrap:break-word"><pre>${instApply.applyerNote!""}</pre></td>
							</tr>
							<tr>
								<td class="lcell" width="18%"><label><@s.text name="adminNote" /></label></td>
								<td width="620" colspan=3 style="word-wrap:break-word"><pre>${instApply.adminNote!""}</pre></td>
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
            <div id="detailInstall" style="display:none;position:relative;">
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                    <td>              
						<table style="margin-top:0px;margin-bottom:0px;table-layout:fixed" width="90%" align="center" class="field_tbl" >
							<tbody>
								<tr>
									<td style="border-top:0px;" width="18%" class="lcell" ><label><@s.text name="productCategory" /></label></td>
									<td style="border-top:0px;" width="32%"><label>${install.productCategoryName!""}</label></td>
									<td style="border-top:0px;" width="18%" class="lcell" ><label><@s.text name="productModel" /></label></td>
									<td style="border-top:0px;" width="32%"><label>${install.model!""}</label></td>
								</tr>
								<tr>
									<td width="18%" class="lcell"><label><@s.text name="manufactureNo" /></label></td>
									<td width="32%"><label>${install.manufactureNo!""}</label></td>
									<td width="18%" class="lcell"><label><@s.text name="customerCompany" /></label></td>
									<td width="32%"><label>${install.customerName!""}</label></td>
								</tr>
								<tr>
									<td width="18%" class="lcell"><label><@s.text name="customerProvince" /></label></td>
									<td width="32%"><label>${install.customerProvince!""}</label></td>
									<td width="18%" class="lcell"><label><@s.text name="customerCity" /></label></td>
									<td width="32%"><label>${install.customerCity!""}</label></td>
								</tr>
								<tr>
									<td width="18%" class="lcell"><label><@s.text name="saleContractCompName" /></label></td>
									<td width="32%"><label>${install.saleContractCompName!""}</label></td>
									<td width="18%" class="lcell"><label><@s.text name="installCompName" /></label></td>
									<td width="32%"><label>${install.installCompName!""}</label></td>
								</tr>
								<tr>
								<td width="18%" class="lcell"><label><@s.text name="repairCompanyName" /></td>
								<td width="82%" colspan=3><label>${install.nowRepairCompanyName!""}</label></td>
								</tr>
			                    <#if loginUser.filter("install_mng_all_data") || install.firstRepairCompanyId == install.nowRepairCompanyId>
			                        <tr>
			                            <td width="18%" class="lcell" aligh="right"><label><@s.text name="indentureNo" /></label></td>
			                            <td width="82%" colspan=3><label>${install.indentureNo!""}</label></td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="fobDate" /></label></td>
			                            <td width="32%"><label>${install.fobDate!""}</label></td>
			                            <td width="18%" class="lcell"><label><@s.text name="installDate" /></label></td>
			                            <td width="32%"><label>${install.installDate!""}</label></td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="openDate" /></label></td>
			                            <td width="32%"><label>${install.openDate!""}</label></td>
			                            <td width="18%" class="lcell"><label><@s.text name="acceptDate" /></label></td>
			                            <td width="32%"><label>${install.acceptDate!""}</label></td>
			                        </tr>
			                        <tr>
			                            <td width="18%" class="lcell"><label><@s.text name="installer" /></label></td>
			                            <td width="32%"><label>${install.installer!""}</label></td>
			                            <td width="18%" class="lcell"><label><@s.text name="installerId" /></label></td>
			                            <td width="32%"><label>${install.installerId!""}</label></td>
			                        </tr>
			                    </#if>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="branchCompanyName" /></label></td>
			                        <td width="82%" colspan=3><label>${install.branchCompanyName!""}</label></td>
			                    </tr>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="purpose" /></label></td>
			                        <td width="82%" colspan=3><label>${install.purposeName!""}</label></td>
			                    </tr>
			                   <#if install.faultMachineType?? && install.faultMachineType=="ATM">      
				                    <tr>
				                        <td width="18%" class="lcell"><label><@s.text name="brmEpVer" /></label></td>
				                        <td width="32%"><label>${install.brmEpVer!""}</label></td>
				                        <td width="18%" class="lcell"><label><@s.text name="bvEpVer" /></label></td>
				                        <td width="32%"><label>${install.bvEpVer!""}</label></td>
				                    </tr>
				               <#elseif  install.faultMachineType?? && install.faultMachineType=="G-ABIO">
                                    <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="mainEpVer" /></label></td>
                                        <td width="32%"><label>${install.brmEpVer!""}</label></td>
                                        <td width="18%" class="lcell"><label><@s.text name="bhclEpVer" /></label></td>
                                        <td width="32%"><label>${install.bhclEpVer!""}</label></td>
                                    </tr>				               
                                    <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="trclEpVer" /></label></td>
                                        <td width="32%"><label>${install.trclEpVer!""}</label></td>
                                        <td width="18%" class="lcell"><label><@s.text name="bidEpVer" /></label></td>
                                        <td width="32%"><label>${install.bvEpVer!""}</label></td>
                                    </tr>				               
				               <#else> 
                                    <tr>
                                        <td width="18%" class="lcell"><label><@s.text name="epVer" /></label></td>
                                        <td width="32%"><label>${install.brmEpVer!""}</label></td>
                                        <td width="18%" class="lcell"><label></label></td>
                                        <td width="32%"><label>${install.bvEpVer!""}</label></td>
                                    </tr>				                 
				               </#if>     
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="keyNo" /></label></td>
			                        <td width="82%" colspan=3><label>${install.keyNo!""}</label></td>
			                    </tr>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="note" /></label></td>
			                        <td width="82%" colspan=3 style="word-break:break-all;word-wrap:break-word"><label>${install.note!""}</label></td>
			                    </tr>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="OS" /></label></td>
			                        <td width="32%"><label>${install.osName!""}</label></td>
                                    <td width="18%" class="lcell"><label><@s.text name="osPermit" /></label></td>
                                    <td width="32%"><label>${install.osPermitName!""}</label></td>
			                    </tr>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="platform" /></label></td>
			                        <td width="32%"><label>${install.platformName!""}</label></td>
                                    <td width="18%" class="lcell"><label><@s.text name="platformRev" /></label></td>
                                    <td width="32%"><label>${install.platformRev!""}</label></td>   			                        
			                    </tr>
                             <#if install.faultMachineType?? && install.faultMachineType=="G-ABIO">
                                <tr>
                                    <td width="18%" class="lcell"><label><@s.text name="EnIssueSole" /></label></td>
                                    <td width="32%"><label>${install.jpr!""}</label></td>                                   
                                    <td width="18%" class="lcell"><label><@s.text name="bid" /></label></td>
                                    <td width="32%"><label>${install.spr!""}</label></td>
                                </tr>
                                <tr>
                                    <td width="18%" class="lcell"><label><@s.text name="passUnitF" /></label></td>
                                    <td width="32%"><label>${install.mcu!""}</label></td>                               
                                    <td width="18%" class="lcell"><label><@s.text name="passUnitR" /></label></td>
                                    <td width="32%"><label>${install.deskey!""}</label></td>
                                </tr>
                                <tr>
                                    <td width="18%" class="lcell"><label><@s.text name="escrowUnit" /></label></td>
                                    <td width="82%" colspan="3"><label>${install.hcm!""}</label></td>
                                </tr>
                                <tr>
                                    <td width="18%" class="lcell"><label><@s.text name="flatPoss" /></label></td>
                                    <td width="82%" colspan="3"  style="word-break:break-all;word-wrap:break-word"><label>${install.bv!""}</label></td>
                                </tr>                             
                             <#elseif install.faultMachineType?? && install.faultMachineType=="ATM">
			                    <tr>
                                    <td width="18%" class="lcell"><label><@s.text name="jpr" /></label></td>
                                    <td width="32%"><label>${install.jpr!""}</label></td>       		                    
			                        <td width="18%" class="lcell"><label><@s.text name="spr" /></label></td>
			                        <td width="32%"><label>${install.spr!""}</label></td>
			                    </tr>
			                    <tr>
                                    <td width="18%" class="lcell"><label><@s.text name="mcu" /></label></td>
                                    <td width="32%"><label>${install.mcu!""}</label></td>			                    
			                        <td width="18%" class="lcell"><label><@s.text name="deskey" /></label></td>
			                        <td width="32%"><label>${install.deskey!""}</label></td>
			                    </tr>
			                    <tr>
                                    <td width="18%" class="lcell"><label><@s.text name="hcm" /></label></td>
                                    <td width="82%" colspan="3"><label>${install.hcm!""}</label></td>
                                </tr>
			                    <tr>
			                        <td width="18%" class="lcell"><label><@s.text name="bv" /></label></td>
			                        <td width="82%" colspan="3"  style="word-break:break-all;word-wrap:break-word"><label>${install.bv!""}</label></td>
			                    </tr>
			                  <#else>
                                <tr>
                                    <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                    <td width="32%"><label>${install.jpr!""}</label></td>                                   
                                    <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                    <td width="32%"><label>${install.spr!""}</label></td>
                                </tr>
                                <tr>
                                    <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                    <td width="32%"><label>${install.mcu!""}</label></td>                               
                                    <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                    <td width="32%"><label>${install.deskey!""}</label></td>
                                </tr>
                                <tr>
                                    <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                    <td width="82%" colspan="3"><label>${install.hcm!""}</label></td>
                                </tr>
                                <tr>
                                    <td width="18%" class="lcell"><label>&nbsp;</label></td>
                                    <td width="82%" colspan="3"  style="word-break:break-all;word-wrap:break-word"><label>${install.bv!""}</label></td>
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
								<td width="40%"><label>${instApply.guaranteeStartDate!""}</label></td>
							</tr>
							<tr>
								<td class="lcell" width="18%"><label><@s.text name="guaranteeEndDate" /></label></td>
								<td width="32%"><label>${install.guaranteeEndDate!""}</label></td>
								<input type="hidden" name="install.guaranteeEndDate" value="${install.guaranteeEndDate!""}">
								<td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
								<td width="40%"><label>${instApply.guaranteeEndDate!""}</label></label></td>
							</tr>
							<tr>
								<td class="lcell" width="18%" ><label><@s.text name="guaranteePeriod" /></label></td>
								<td width="32%" ><label>${install.guaranteePeriod!""}</label></td>
								<input type="hidden" name="install.guaranteePeriod" value="${install.guaranteePeriod!""}">
								<td width="10%" align="center" ><label><@s.text name="rightLine" /></label></td>
								<td width="40%" ><label>${instApply.guaranteePeriod!""}</label></td>
							</tr>
							<tr>
								<td class="lcell" width="18%"><label><@s.text name="installPlace" /></label></td>
								<td width="32%"><label>${install.installPlace!""}</label></td>
								<input type="hidden" name="install.installPlace" value="${install.installPlace!""}">
								<td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
								<td width="40%"><label>${instApply.installPlace!""}</label></td>
							</tr>
							<tr>
								<td class="lcell" width="18%"><label><@s.text name="instPlaceTypeName" /></label></td>
								<td width="32%"><label>${install.instPlaceTypeName!""}</label></td>
								<input type="hidden" name="install.instPlaceTypeName" value="${install.instPlaceTypeName!""}">
								<td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
								<td width="40%"><label>${instApply.instPlaceTypeName!""}</label></td>
							</tr>
							<tr>
								<td class="lcell" width="18%"><label><@s.text name="useStatusId" /></label></td>
								<td width="32%"><label>${install.useStatus!""}</label></td>
								<input type="hidden" name="install.useStatus" value="${install.useStatus!""}">
								<td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
								<td width="40%"><label>${instApply.useStatus!""}</label></td>
							</tr>
							<tr>
								<td width="18%" class="lcell"><label><@s.text name="contact" /></label></td>
				                <td width="32%"><label>${install.contact!""}</label></td>
				                <input type="hidden" name="install.contact" value="${install.contact!""}">
			                    <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                    <td width="40%"><label>${instApply.contact!""}</label></td>
				            </tr>
				            <tr>
				                <td width="18%" class="lcell"><label><@s.text name="officePhone" /></label></td>
				                <td width="32%"><label>${install.officePhone!""}</td>
				                <input type="hidden" name="install.officePhone" value="${install.officePhone!""}">
			                    <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                    <td width="40%"><label>${instApply.officePhone!""}</label></td>
				            </tr>
			                <tr>
				                <td width="18%" class="lcell"><label><@s.text name="mobilePhone" /></label></td>
				                <td width="32%"><label>${install.mobilePhone!""}</td>
				                <input type="hidden" name="install.mobilePhone" value="${install.mobilePhone!""}">
			                    <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                    <td width="40%"><label>${instApply.mobilePhone!""}</label></td>
				            </tr>
				            <tr>
				                <td width="18%" class="lcell"><label><@s.text name="fax" /></label></td>
				                <td width="32%"><label>${install.fax!""}</td>
				                <input type="hidden" name="install.fax" value="${install.fax!""}">
			                    <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                    <td width="40%"><label>${instApply.fax!""}</label></td>
				            </tr>
			                <tr>
				                <td width="18%" class="lcell"><label><@s.text name="email" /></label></td>
				                <td width="32%"><label>${install.email!""}</td>
				                <input type="hidden" name="install.email" value="${install.email!""}">
			                    <td width="10%" align="center"><label><@s.text name="rightLine" /></label></td>
			                    <td width="40%"><label>${instApply.email!""}</label></td>
				            </tr></tr>
						</table>
                    </td>
                  </tr>
               </tbody>
            </table> 						
	        <form id="instApplyForm" method="post">
	            <input type="hidden" name="instApply.id" value="${instApply.id!""}"/>
	            <input type="hidden" name="instApply.exclusiveKey" value="${instApply.exclusiveKey!""}"/>
	            <input type="hidden" name="install.exclusiveKey" value="${install.exclusiveKey!""}"/>
	            <input type="hidden" value="${install.firstRepairCompanyId!""}" name="install.firstRepairCompanyId" >
                <input type="hidden" value="${install.nowRepairCompanyId!""}" name="install.nowRepairCompanyId" >
	        </form>						
			<div class="btn_row">
		        <#if loginUser.hasPermission("PR004_70")>
		            <button type="button" onClick="doApply('PR004_70')"><@s.text name="btn_doApply" /></button> 
		        </#if>
	        </div>
		</div>
    </div>
    <#include "../footer.ftl"/>   
</div>
</#escape>
</body>
</html>