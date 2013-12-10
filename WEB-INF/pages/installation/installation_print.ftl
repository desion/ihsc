<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${install.manufactureNo!""}<@s.text name="installation_label" /></title>
<#include "../common_header.ftl"/>  
</head>
<body>
<#escape x as x?html>
<div id="container">
  <div  style="margin:20px auto;">
    <table width="100%" align="center" class="field_tbl" >
        <tr>
            <th colspan="4" ><label><@s.text name="installation_view" /></label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell" ><label><@s.text name="install.productCategory" /></label></td>
            <td width="32%"><label>${install.productCategoryName!""}</label></td>
            <td width="18%" class="lcell" ><label><@s.text name="install.pruductModel" /></label></td>
            <td width="32%"><label>${install.model!""}</label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.manufactureNo" /></label></td>
            <td width="32%"><label>${install.manufactureNo!""}</label></td>
            <td width="18%" class="lcell"><label><@s.text name="install.customerCompany" /></label></td>
            <td width="32%"><label>${install.customerName!""}</label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.customerProvince" /></label></td>
            <td width="32%"><label>${install.customerProvince!""}</label></td>
            <td width="18%" class="lcell"><label><@s.text name="install.customerCity" /></label></td>
            <td width="32%"><label>${install.customerCity!""}</label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.saleContractCompId" /></label></td>
            <td width="32%"><label>${install.saleContractCompName!""}</label></td>
            <td width="18%" class="lcell"><label><@s.text name="install.installCompanyName" /></label></td>
            <td width="32%"><label>${install.installCompName!""}</label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.repairCompanyName" /></td>
            <td width="82%" colspan=3><label>${install.nowRepairCompanyName!""}</label></td>
        </tr>
    </table>

    <br>

    <table class="field_tbl" width="100%" align="center">
        <#if loginUser.filter("install_mng_all_data") || install.firstRepairCompanyId == install.nowRepairCompanyId>
            <tr>
                <td width="18%" class="lcell" aligh="right"><label><@s.text name="install.indentureNo" /></label></td>
                <td width="82%" colspan=3><label>${install.indentureNo!""}</label></td>
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.fobDate" /></label></td>
                <td width="32%"><label>${install.fobDate!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.installDate" /></label></td>
                <td width="32%"><label>${install.installDate!""}</label></td>
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.openDate" /></label></td>
                <td width="32%"><label>${install.openDate!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.acceptDate" /></label></td>
                <td width="32%"><label>${install.acceptDate!""}</label></td>
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.installer" /></label></td>
                <td width="32%"><label>${install.installer!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.installerId" /></label></td>
                <td width="32%"><label>${install.installerId!""}</label></td>
            </tr>
        </#if>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.guaranteeStartDate" /></label></td>
            <td width="32%"><label>${install.guaranteeStartDate!""}</label></td>
            <td width="18%" class="lcell"><label><@s.text name="install.guaranteeEndDate" /></label></td>
            <td width="32%"><label>${install.guaranteeEndDate!""}</label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.guaranteePeriod" /></label></td>
            <td width="82%" colspan=3><label>${install.guaranteePeriod!""}</label></td>
        </tr>
    </table>

    <br>

    <table class="field_tbl" width="100%" align="center">
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.branchCompanyName" /></label></td>
            <td width="82%" colspan=3><label>${install.branchCompanyName!""}</label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.installPlace" /></label></td>
            <td width="82%" colspan=3><label>${install.installPlace!""}</label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.instPlaceTypeId" /></label></td>
            <td width="32%"><label>${install.instPlaceTypeName!""}</label></td>
            <td width="18%" class="lcell"><label><@s.text name="install.useStatusId" /></label></td>
            <td width="32%"><label>${install.useStatus!""}</label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.purpose" /></label></td>
            <td width="32%"><label>${install.purposeName!""}</label></td>
            <td width="18%" class="lcell"><label><@s.text name="install.contact" /></label></td>
            <td width="32%"><label>${install.contact!""}</label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.officePhone" /></label></td>
            <td width="32%"><label>${install.officePhone!""}</td>
            <td width="18%" class="lcell"><label><@s.text name="install.mobilePhone" /></label></td>
            <td width="32%"><label>${install.mobilePhone!""}</td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.fax" /></label></td>
            <td width="32%"><label>${install.fax!""}</td>
            <td width="18%" class="lcell"><label><@s.text name="install.email" /></label></td>
            <td width="32%"><label>${install.email!""}</td>
        </tr>
    </table>
    
    <br>

    <table class="field_tbl" width="100%" align="center" style="table-layout:fixed">
       <#if install.faultMachineType?? && install.faultMachineType=="ATM">
	        <tr>
	            <td width="18%" class="lcell"><label><@s.text name="install.brmEpVer" /></label></td>
	            <td width="32%"><label>${install.brmEpVer!""}</label></td>
	            <td width="18%" class="lcell"><label><@s.text name="install.bvEpVer" /></label></td>
	            <td width="32%"><label>${install.bvEpVer!""}</label></td>
	        </tr>
       <#elseif  install.faultMachineType?? && install.faultMachineType=="G-ABIO">
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.mainEpVer" /></label></td>
                <td width="32%"><label>${install.brmEpVer!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.bhclEpVer" /></label></td>
                <td width="32%"><label>${install.bhclEpVer!""}</label></td>
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.trclEpVer" /></label></td>
                <td width="32%"><label>${install.trclEpVer!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.bidEpVer" /></label></td>
                <td width="32%"><label>${install.bvEpVer!""}</label></td>
            </tr>         
       <#else>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.epVer" /></label></td>
                <td width="32%"><label>${install.brmEpVer!""}</label></td>
                <td width="18%" class="lcell"><label></label></td>
                <td width="32%"><label>${install.bvEpVer!""}</label></td>
            </tr>           
       </#if>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.keyNo" /></label></td>
            <td width="82%" colspan=3><label>${install.keyNo!""}</label></td>
        </tr>
        <tr>
            <td width="18%" class="lcell"><label><@s.text name="install.note" /></label></td>
            <td width="82%" colspan=3 style="word-break:break-all;word-wrap:break-word"><label>${install.note!""}</label></td>
        </tr>
    </table>
    
    <br>
    
    <table width="100%" align="center" class="field_tbl" style="table-layout:fixed">
        <tbody>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.OS" /></label></td>
                <td width="32%"><label>${install.osName!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.osPermit" /></label></td>
                <td width="32%"><label>${install.osPermitName!""}</label></td>
            
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.platform" /></label></td>
                <td width="32%"><label>${install.platformName!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.platformRev" /></label></td>
                <td width="32%"><label>${install.platformRev!""}</label></td>
            </tr>
           <#if install.faultMachineType?? && install.faultMachineType=="G-ABIO"> 
             <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.EnIssueSole" /></label></td>
                <td width="32%"><label>${install.jpr!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.bid" /></label></td>
                <td width="32%"><label>${install.spr!""}</label></td>
           
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.passUnitF" /></label></td>
                <td width="32%"><label>${install.mcu!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.passUnitR" /></label></td>
                <td width="32%"><label>${install.deskey!""}</label></td>
           
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.escrowUnit" /></label></td>
                <td width="82%" colspan="3"><label>${install.hcm!""}</label></td>
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.flatPoss" /></label></td>
                <td width="82%" colspan="3" style="word-break:break-all;word-wrap:break-word"><label>${install.bv!""}</label></td>
            </tr>          
           
           <#elseif install.faultMachineType?? && install.faultMachineType=="ATM">    
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.jpr" /></label></td>
                <td width="32%"><label>${install.jpr!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.spr" /></label></td>
                <td width="32%"><label>${install.spr!""}</label></td>
           
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.mcu" /></label></td>
                <td width="32%"><label>${install.mcu!""}</label></td>
                <td width="18%" class="lcell"><label><@s.text name="install.deskey" /></label></td>
                <td width="32%"><label>${install.deskey!""}</label></td>
           
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.hcm" /></label></td>
                <td width="82%" colspan="3"><label>${install.hcm!""}</label></td>
            </tr>
            <tr>
                <td width="18%" class="lcell"><label><@s.text name="install.bv" /></label></td>
                <td width="82%" colspan="3" style="word-break:break-all;word-wrap:break-word"><label>${install.bv!""}</label></td>
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
                <td width="82%" colspan="3" style="word-break:break-all;word-wrap:break-word"><label>${install.bv!""}</label></td>
            </tr>          
          </#if>  
        </tbody>
    </table>
   </div>  
</div>
</#escape>
</body>
</html>