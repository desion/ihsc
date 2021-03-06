<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="fault_handle_view" /></title>
<#include "../common_header.ftl"/>

<script type="text/javascript">
function changeAction(url)
    {
        var form=document.getElementById("modifyForm");
        form.action=url;
        form.submit();
    }
function printAction(url)
    {
        var form=document.getElementById("printForm");
        form.action=url;
        form.submit();
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
        <div>
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>           
			            <table class="field_tbl" width="100%" align="center">
			                <tr>
			                    <th colspan="2"><@s.text name="fault_handle_information" /></th>
			                </tr>
			                <tr>
			                    <td class="lcell" width="20%"><label><@s.text name="manageId" /></label></td>
			                    <td width="80%"><label>${fault.managementId!""}</label></td>
			                </tr>
			                <tr>
			                    <td class="lcell" width="20%"><label><@s.text name="occur_time" /></label></td>
			                    <td width="80%"><label>${fault.occurDate!""} ${fault.occurTime!""}</label></td>
			                </tr>
			                <tr>
			                    <td class="lcell" width="20%"><label><@s.text name="repair_company" /></label></td>
			                    <td width="80%"><label>${fault.repairCompanyName!""}</label></td>
			                </tr>
			                <tr>
			                    <td class="lcell" width="20%"><label><@s.text name="city" /></label></td>
			                    <td width="80%"><label>${fault.cityName!""}</label></td>
			                </tr>
			                <tr>
			                    <td class="lcell" width="20%"><label><@s.text name="customer" /></label></td>
			                    <td width="80%"><label>${fault.customerName!""}</label></td>
			                </tr>
			                <tr>
			                    <td class="lcell" width="20%"><label><@s.text name="sub_and_installPlace" /></label></td>
			                    <td width="80%"><label>${fault.subCompany!""} ${fault.installPlace!""}</label></td>
			                </tr>
			                <tr>
			                    <td class="lcell" width="20%"><label><@s.text name="machine_type" /></label></td>
			                    <td width="80%"><label>${fault.productCategoryName!""}</label></td>
			                </tr>
			                <tr>
			                    <td class="lcell" width="20%"><label><@s.text name="model" /></label></td>
			                    <td width="80%"><label>${fault.model!""}</label></td>
			                </tr>
			            </table>
	                </td>
	              </tr>
	           </tbody>
	          </table>  			            
        </div>
        <div style="margin:10px auto;">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>           
			            <table class="field_tbl" width="100%" align="center" style="table-layout:fixed">
		                <tr>
		                    <td class="lcell" width="20%"><label><@s.text name="faultHandle.operateCompanyId" /></label></td>
		                    <td width="80%"><label>${faultHandle.operateCompanyName!""}</label></td>
		                </tr>
		                <tr>
		                    <td class="lcell" width="20%"><label><@s.text name="faultHandle.operatorName" /></label></td>
		                    <td width="80%"><label>${faultHandle.operatorName!""}</label></td>
		                </tr>
		                <tr>
		                    <td class="lcell" width="20%"><label><@s.text name="faultHandle.supporterId" /></label></td>
		                    <td width="80%"><label>${faultHandle.supporterFamily!""} ${faultHandle.supporterGiven!""}</label></td>
		                </tr>
		                <tr>
		                    <td class="lcell" width="20%"><label><@s.text name="faultHandle.handleDetail" /></label></td>
		                    <td width="620" style="word-wrap:break-word">${faultHandle.handleDetail!""}</td>
		                </tr>
		                <tr>
		                    <td class="lcell" width="20%"><label><@s.text name="faultHandle.handleType" /></label></td>
		                    <td width="80%"><label>${faultHandle.handleTypeName!""}</label></td>
		                </tr>
		                <tr>
		                    <td class="lcell" width="20%"><label><@s.text name="faultHandle.startTime" /></label></td>
		                    <td width="80%"><label>${faultHandle.startDate!""} ${faultHandle.startTime!""}</label></td>
		                </tr>
		                <tr>
		                    <td class="lcell" width="20%"><label><@s.text name="faultHandle.endTime" /></label></td>
		                    <td width="80%"><label>${faultHandle.endDate!""} ${faultHandle.endTime!""}</label></td>
		                </tr>
		                <tr>
		                    <td class="lcell" width="20%"><label><@s.text name="faultHandle.status" /></label></td>
		                    <td width="620" style="word-wrap:break-word">${faultHandle.status!""}</td>
		                </tr>
		                <tr>
		                    <td class="lcell" width="20%"><label><@s.text name="faultHandle.note" /></label></td>
		                    <td width="620" style="word-wrap:break-word">${faultHandle.note!""}</td>
		                </tr>
		            </table>
                </td>
              </tr>
           </tbody>
          </table>  		            
        </div>
        <div class="btn_row">
        <#if loginUser.hasPermission("FA002_30")>
            <button type="button" style="width:120px" onClick="changeAction('FA002_30')" ><@s.text name="btn_modify" /></button>
        </#if> 
        <#if loginUser.hasPermission("FA002_80")>    
            <button type="button" style="width:120px" onClick="printAction('FA002_80')" ><@s.text name="btn_print" /></button>
        </#if> 
        <#if loginUser.hasPermission("FA002_10")>    
            <button type="button" style="width:120px" onClick="changeAction('FA002_10')" ><@s.text name="btn_return" /></button>
        </#if> 
        </div>
        <form id="modifyForm" method="post">
            <input type="hidden" name="FaultHandle.id" value="${FaultHandle.id!""}">
            <input type="hidden" name="faultHandle.faultId" value="${faultHandle.faultId!""}">
            <input type="hidden" name="fault.id" value="${faultHandle.faultId!""}">
        </form>
        <form id="printForm" method="post" target="_blank">
            <input type="hidden" name="FaultHandle.id" value="${FaultHandle.id!""}">
        </form>
    </div>
  </div>
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>