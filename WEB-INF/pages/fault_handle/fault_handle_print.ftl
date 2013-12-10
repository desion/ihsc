<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="fault_handle_view" /></title>
<#include "../common_header.ftl"/>  
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div style="width:100%;">
    <table class="field_tbl" width="100%" align="center" >
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
</div>
<div style="width:100%;margin:20px 0;">
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
            <td width="500" style="word-wrap:break-word">${faultHandle.handleDetail!""}</td>
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
            <td width="80%" style="word-wrap:break-word">${faultHandle.status!""}</td>
        </tr>
        <tr>
            <td class="lcell" width="20%"><label><@s.text name="faultHandle.note" /></label></td>
            <td width="80%" style="word-wrap:break-word">${faultHandle.note!""}</td>
        </tr>
    </table>
</div>
</#escape>
</body>
</html>