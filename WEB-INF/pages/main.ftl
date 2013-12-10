<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="mainPage" /></title>
<#include "common_header.ftl"/>
<link rel="stylesheet" type="text/css" href="css/themes/base/ui.tabs.css" />
<script type="text/javascript" src="js/jquery/ui.tabs.js" ></script>
<script type="text/javascript">
    $(document).ready(function(){   
       $("#tabs").tabs();           
     });
 
</script>
</head>
<body >
<#escape x as x?html>
<div id="container">
  <#include "header.ftl"/> 
  <div style="position: relative;z-index:0">
      <#if loginUser.hasPermission("BS009_70")>
             <@s.action name="BS009_70" executeResult="true" ignoreContextParams="true">
             </@s.action> 
      </#if>
   <div id="tabs" align="center">
        <ul>
            <li><a href="#tabs-1" style="color:#0b559b"><@s.text name="unconfirmInstallInfo" /></a></li>
            <li><a href="#tabs-2" style="color:#0b559b"><@s.text name="unconfirmFaultInfo" /></a></li>
            <li><a href="#tabs-3" style="color:#0b559b"><@s.text name="unconfirmInstallaApplyInfot" /></a></li>
         </ul>
     
	     <div id="tabs-1" align="center" class="gridview"  style="margin-bottom:0px; width:95%">
	       <table width="100%" class="table_line">
             <tr>
                 <td>
			         <table class="datalist" width="100%" cellspacing="0" cellpadding="0">
				         <tr>
				            
				            <th style="width:14%"><@s.text name="install.model" /></th>
				            <th style="width:17%"><@s.text name="install.customerName" /></th>
				            <th style="width:12%"><@s.text name="install.installDate" /></th>
				            <th style="width:8%"><@s.text name="install.manufactureNo" /></th>
				            <th style="width:14%"><@s.text name="install.installPlace" /></th>
				            <th style="width:15%"><@s.text name="install.nowRepairCompanyName" /></th>
				            <th style="width:10%"><@s.text name="install.useStatus" /></th>
				            <th style="width:10%"><@s.text name="install.affirm" /></th>
				         </tr>
				         <#list installationList as inst>
			                <tr>
			                    <td><label>${inst.model!""}</label></td>
			                    <td><label>${inst.customerName!""}</label></td>
			                    <td><label>${inst.installDate!""}</label></td>
			                    <td><a href="PR005_11?install.id=${inst.id!"-1"}">${inst.manufactureNo!""}</a></td>
			                    <td><label>${inst.branchCompanyName!""} </label></td>
			                    <td><label>${inst.nowRepairCompanyName!""}</label></td>
			                    <td><label>${inst.useStatus!""}</label></td>
			                    <td><label class="needed"><@s.text name="unconfirmed" /></label></td>
			                </tr>
			            </#list>
			          </table>
	               </td>
	             </tr>
	        </table>
	        <#if !(installationList?size < 20)>
	           <p align="left"><a href="/PR005_10?install.affirmFlag=0"><@s.text name="more" /></a></p>
	        </#if>   
		  </div>
	  
		  <div id="tabs-2" align="center" class="gridview"  style="margin-bottom:0px; width:95%">
            <table width="100%" class="table_line">
               <tr>
                   <td>
					  <table class="datalist" width="100%" cellspacing="0" cellpadding="0" >
			            <tr>
			                <th width="18%"><@s.text name="fault.managementId" /></th>
			                <th width="12%"><@s.text name="fault.occurDate" /></th>
			                <th width="14%"><@s.text name="fault.model" /></th>
			                <th width="9%"><@s.text name="fault.manufactureNo" /></th>
			                <th width="9%"><@s.text name="fault.faultType" /></th>
			                <th width="15%"><@s.text name="fault.customerName" /></th>
			                <th width="15%"><@s.text name="fault.installPlace" /></th>
			                <th width="8%"><@s.text name="fault.repairState" /></th>
			            </tr>
			            <#list faultList as fault>
			                <tr>
			                    <td><a href="FA001_11?fault.id=${fault.id!"-1"}&amp;faultActionFrom=BS010_00">${fault.managementId!""}</a></td>
			                    <td><label>${fault.occurDate!""}</label></td>
			                    <td><label>${fault.model!""}</label></td>
			                    <td><a href="PR005_11?install.id=${fault.installationId!"-1"}">${fault.manufactureNo!""}</a></td>
			                    <td><label>${fault.faultTypeName!""}</label></td>
			                    <td><label>${fault.customerName!""}</label></td>
			                    <td><label>${fault.subCompany!""}</label></td>
			                    <td><label class="needed"><@s.text name="uncompleted" /></label></td>
			                </tr>
			            </#list>
			          </table>
                   </td>
                 </tr>
             </table>
             <#if !(faultList?size < 20)>
                <p align="left"><a href="/FA001_10?fault.repairState=0"><@s.text name="more" /></a></p>
             </#if> 			          
	       </div>
	       
	       <div id="tabs-3" align="center" class="gridview"  style="margin-bottom:0px; width:95%">
            <table width="100%" class="table_line">
               <tr>
                  <td>	       
			          <table class="datalist" width="100%" cellspacing="0" cellpadding="0" >
			            <tr>
			                <th width="34%"><@s.text name="installApply.nowRepairCompanyName" /></th>
			                <th width="10%"><@s.text name="installApply.manufactureNo" /></th>
			                <th width="24%"><@s.text name="installApply.applyTypeName" /></th>
			                <th width="16%"><@s.text name="installApply.applyerName" /></th>
			                <th width="16%"><@s.text name="installApply.applyDate" /></th>
			            </tr>
					    <#list applyInstallList as applyInstall>
					      <tr>
					        <td><label>${applyInstall.nowRepairCompanyName!""}</label></td>
					        <td><a href="PR004_11?instApply.id=${applyInstall.id!"-1"}&amp;instApply.exclusiveKey=${applyInstall.exclusiveKey!"-1"}">${applyInstall.manufactureNo!""}</a></td>
					        <td><label>${applyInstall.typeName!""}</label></td>
					        <td><label>${applyInstall.applyerName!""}</label></td>
					        <td><label>${applyInstall.applyDate!""}</label></td>
					      </tr>
					    </#list>
			          </table>
                   </td>
                 </tr>
             </table>
             <#if !(applyInstallList?size < 20)>	
                <p align="left"><a href="/PR004_10"><@s.text name="more" /></a></p>
             </#if>	        
	      </div>   
    </div>
  </div>
   <#include "footer.ftl"/>  
     
 </div>     
 <#include "components/error_reference.ftl"/> 
</#escape>
</body>
</html>