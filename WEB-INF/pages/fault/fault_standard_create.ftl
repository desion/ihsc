<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="FA001_20_title" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
function changeAction(url) {
    var flag= confirm("<@s.text name="BSC00006" />");
    if(flag==true){
        var form=document.getElementById("createForm");
        form.action=url;
        form.submit();  
    }
}
function addRow() {
    //添加一行
    var iCurr = $("#faultSparesTab tr").length - 2;
    if (iCurr < 15) {
        var newTr = "";
        newTr = '<tr id="tr_faultSpares' + iCurr + '">';
        newTr += '<td>' + (iCurr + 1) + '</td>'; 
        newTr += '<td><input type="text" name="faultSparesList['+ iCurr +'].name" maxLength="30" size="9" /></td>';
        newTr += '<td><input type="text" name="faultSparesList['+ iCurr +'].pictureNo" maxLength="30" size="9" /></td>';
        newTr += '<td><input type="text" name="faultSparesList['+ iCurr +'].warehouse" maxLength="30" size="9" /></td>';
        newTr += '<td><input type="text" name="faultSparesList['+ iCurr +'].no" maxLength="8" size="9" /></td>';
        newTr += '<td><input type="text" name="faultSparesList['+ iCurr +'].applyDate" maxLength="10" size="9" /></td>';
        newTr += '<td><input type="text" name="faultSparesList['+ iCurr +'].deliverDate" maxLength="10" size="9" /></td>';
        newTr += '<td><input type="text" name="faultSparesList['+ iCurr +'].replaceDate" maxLength="10" size="9" /></td>';
        newTr += '<td><input type="text" name="faultSparesList['+ iCurr +'].receiveDate" maxLength="10" size="9" /></td>';
        newTr += '</tr>'
        $("#faultSparesTab_body").append(newTr);
    }
}
function delRow() {
    var iCurr = $("#faultSparesTab tr").length - 2;
    if (iCurr > 1) {
        $("#tr_faultSpares" + (iCurr -1)).remove();
    }
}
$(document).ready(function(){

  $("#occurDate").datepicker();
    $("#finishDate").datepicker();
    $("input[type=radio][id=applicationVersionSel]").click(function(){
        if ($(":radio[id=applicationVersionSel]:checked:eq(0)").val() == "1") {
            $("#applicationVersionDetail1Label").text("<@s.text name="create.fault.label.applicationVersionDetail1.apVersion" />");
            $("#applicationVersionDetail2Label").text("<@s.text name="create.fault.label.applicationVersionDetail2.spVersion" />");
        } else {
            $("#applicationVersionDetail1Label").text("<@s.text name="create.fault.label.applicationVersionDetail1.version" />");
            $("#applicationVersionDetail2Label").text("<@s.text name="create.fault.label.applicationVersionDetail2.botai" />");
        }
    });
    
     
    
});
function ShowDetailPhase() {
    $("#detailPhase").toggle();
    if ($("#detailPhaseImg").attr("src") == "../../../images/plus.png") {
        $("#detailPhaseImg").attr("src", "../../../images/minus.png");
    } else {
        $("#detailPhaseImg").attr("src", "../../../images/plus.png");
    }
}
function ShowStrategyDetail() {
    $("#tr_strategyDetail").toggle();
}
function textCounter(field) {
    var maxlimit = 800;
    if (field.value.length > maxlimit){
        field.value = field.value.substring(0, maxlimit);
    } 
}

</script>
<script type="text/javascript" src="js/jquery/erp_time.js" ></script>
</head>
<#include "../components/error_reference.ftl"/>
<body>
<#escape x as x?html>
<div id="container">
  <#include "../header.ftl"/>
    <div id="view">
        <div class="fields">
          <form id="createForm" method="post" enctype ="multipart/form-data">
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                    <td>         
                        <table width="80%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="100%" class="lcell" colspan="4"><b><@s.text name="create.fault.label.machineInfo" /></b></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.model" /></td>
                                    <td width="30%">${fault.model!""}<input type="hidden" name="fault.model" value="${fault.model!""}"/></td>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.productCategoryName" /></td>
                                    <td width="30%">${fault.productCategoryName!""}<input type="hidden" name="fault.productCategoryName" value="${fault.productCategoryName!""}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.cityName" /></td>
                                    <td width="30%">${fault.cityName!""}<input type="hidden" name="fault.cityName" value="${fault.cityName!""}"/></td>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.customerName" /></td>
                                    <td width="30%">${fault.customerName!""}<input type="hidden" name="fault.customerName" value="${fault.customerName!""}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.manufactureNo" /></td>
                                    <td width="30%">${fault.manufactureNo!""}<input type="hidden" name="fault.manufactureNo" value="${fault.manufactureNo!""}"/></td>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.purpose" /></td>
                                    <td width="30%">${fault.purpose!""}<input type="hidden" name="fault.purpose" value="${fault.purpose!""}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.guaranteeDate" /></td>
                                    <td width="30%">${fault.guaranteeDate!""}<input type="hidden" name="fault.guaranteeDate" value="${fault.guaranteeDate!""}"/></td>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.repairCompany" /></td>
                                    <td width="30%">
                                        ${fault.repairCompanyName!""}
                                        <input type="hidden" name="fault.repairCompanyName" value="${fault.repairCompanyName!""}"/>
                                        <input type="hidden" name="fault.repairCompanyId" value="${fault.repairCompanyId!""}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.standard.Epver" /></td>
                                    <td width="80%" colspan="3"><input type="text" name="fault.epVer" value="${fault.epVer!""}" maxLength="20" size="20"/></td>
                                </tr>
                            </tbody>
                        </table>
                        <table style="margin-bottom:0px" width="80%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="100%" class="lcell" colspan="4"><b><@s.text name="create.fault.label.basicInfo" /></b></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.faultType" /></td>
                                    <td width="80%" colspan="3">
                                        <select name="fault.faultType">
                                            <@s.action name="faultType_drop" executeResult="true" ignoreContextParams="true">
                                                <@s.param name="selectedFaultType">${fault.faultType!""}</@s.param>
                                            </@s.action>
                                        </select>
                                        &nbsp;<label class="needed"><@s.text name="select_needed" /></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.occurDate" /></td>
                                    <td width="30%">
                                        <input id="occurDate" type="text" name="fault.occurDate" value="${fault.occurDate!""}" maxLength="10" size="14" />
                                        &nbsp;<label class="needed"><@s.text name="input_needed" /></label>
                                    </td>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.occurTime" /></td>
                                    <td width="30%">
                                        <input id="occurTime" type="text" name="fault.occurTime" value="${fault.occurTime!""}" maxLength="5" size="5" />
                                        &nbsp;<label class="needed"><@s.text name="input_needed" /></label>
                                       <button type="button" id="btnOccur" onclick="openTimeWin('occurTime')" style="margin:0 5px;width:60px;height:24px;line-height:24px;" ><@s.text name="btn_set" /></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.operator" /></td>
                                    <td width="30%"><input type="text" id="operatorName" name="fault.operatorName" value="${fault.operatorName!""}" maxLength="40" size="20" /></td>
                                    <td width="50%" colspan="2">
                                        <select id="operatorSel" onchange="operatorName.value=operatorSel.value">
                                            <@s.action name="userName_drop" executeResult="true" ignoreContextParams="true"/>
                                        </select>
                                        &nbsp;<label class="needed"><@s.text name="input_needed" /></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.supporter" /></td>
                                    <td width="80%" colspan="3">
                                        <select name="fault.supporterId" >
                                            <@s.action name="user_drop" executeResult="true" ignoreContextParams="true">
                                                <@s.param name="selectedUser">${fault.supporterId!""}</@s.param>
                                            </@s.action>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.follower" /></td>
                                    <td width="80%" colspan="3">
                                        <select name="fault.followerId" >
                                            <@s.action name="user_drop" executeResult="true" ignoreContextParams="true">
                                                <@s.param name="selectedUser">${fault.followerId!""}</@s.param>
                                            </@s.action>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table style="margin-top:0px;margin-bottom:0px" width="80%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.faultPart" /></td>
                                    <td width="80%" colspan="3">
                                        <select name="fault.faultPart">
                                            <@s.action name="faultPart_drop" executeResult="true" ignoreContextParams="true">
                                                <@s.param name="selectedFaultPart">${fault.faultPart!""}</@s.param>
                                                <@s.param name="faultMachineType">${fault.faultMachineType!""}</@s.param>
                                            </@s.action>
                                        </select>
                                        &nbsp;<label class="needed"><@s.text name="select_needed_before_finish" /></label>
                                        &nbsp;<label class="needed"><@s.text name="create.fault.label.faultPart.msg" /></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.faultPartType" /></td>
                                    <td width="80%" colspan="3">
                                        <select name="fault.faultPartType">
                                            <@s.action name="faultPartType_drop" executeResult="true" ignoreContextParams="true">
                                                <@s.param name="selectedFaultPartType">${fault.faultPartType!""}</@s.param>
                                            </@s.action>
                                        </select>
                                        &nbsp;<label class="needed"><@s.text name="select_needed_before_finish" /></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.isState" /></td>
                                    <td width="80%" colspan="3">
                                        <select name="fault.isState">
                                            <option value=""><@s.text name="select_isState" /></option> 
                                            <#if fault.isState?? && fault.isState == "I">
                                                <option value="I" selected><@s.text name="iState" /></option>
                                            <#else>
                                                <option value="I"><@s.text name="iState" /></option>
                                            </#if>
                                            <#if fault.isState?? && fault.isState == "S">
                                                <option value="S" selected><@s.text name="sState" /></option>
                                            <#else>
                                                <option value="S"><@s.text name="sState" /></option>
                                            </#if>
                                        </select>
                                        &nbsp;<label class="needed"><@s.text name="select_needed_before_finish" /></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.applicationVersion" /></td>
                                    <td width="80%" colspan="3">
                                        <#if (fault.applicationVersion?? && fault.applicationVersion == 0)>
                                            <input type="radio" id="applicationVersionSel" name="fault.applicationVersion" value="0" checked/><@s.text name="create.fault.label.hitachiAP" />
                                            <input type="radio" id="applicationVersionSel" name="fault.applicationVersion" value="1"/><@s.text name="create.fault.label.unificationAP" />
                                        <#elseif (fault.applicationVersion?? && fault.applicationVersion == 1)>
                                            <input type="radio" id="applicationVersionSel" name="fault.applicationVersion" value="0"/><@s.text name="create.fault.label.hitachiAP" />
                                            <input type="radio" id="applicationVersionSel" name="fault.applicationVersion" value="1" checked/><@s.text name="create.fault.label.unificationAP" />
                                        <#else>
                                            <input type="radio" id="applicationVersionSel" name="fault.applicationVersion" value="0"/><@s.text name="create.fault.label.hitachiAP" />
                                            <input type="radio" id="applicationVersionSel" name="fault.applicationVersion" value="1"/><@s.text name="create.fault.label.unificationAP" />
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><label id="applicationVersionDetail1Label"><@s.text name="create.fault.label.applicationVersionDetail1.version" /></label></td>
                                    <td width="30%">
                                        <input id="applicationVersionDetail1" type="text" name="fault.applicationVersionDetail1" value="${fault.applicationVersionDetail1!""}" maxLength="20" size="20" />
                                    </td>
                                    <td width="20%" class="lcell"><label id="applicationVersionDetail2Label"><@s.text name="create.fault.label.applicationVersionDetail2.botai" /></label></td>
                                    <td width="30%">
                                        <input id="applicationVersionDetail2" type="text" name="fault.applicationVersionDetail2" value="${fault.applicationVersionDetail2!""}" maxLength="20" size="20" />
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.supportType" /></td>
                                    <td width="80%" colspan="3">
                                        <select name="fault.supportType">
                                            <@s.action name="supportType_drop" executeResult="true" ignoreContextParams="true">
                                                <@s.param name="selectedSupportType">${fault.supportType!""}</@s.param>
                                            </@s.action>
                                        </select>
                                        &nbsp;<label class="needed"><@s.text name="select_needed_before_finish" /></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.finishDate" /></td>
                                    <td width="80%" colspan="3">
                                        <input id="finishDate" type="text" name="fault.finishDate" value="${fault.finishDate!""}" maxLength="10" size="14" />
                                        &nbsp;<label class="needed"><@s.text name="input_needed_before_finish" /></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.finishTime" /></td>
                                    <td width="80%" colspan="3">
                                        <input id="finishTime" type="text" name="fault.finishTime" value="${fault.finishTime!""}" maxLength="5" size="5" />
                                        &nbsp;<label class="needed"><@s.text name="input_needed_before_finish" /></label>
                                        <button type="button" id="btnFinish" onclick="openTimeWin('finishTime')" style="margin:0 5px;width:60px;height:24px;line-height:24px;" ><@s.text name="btn_set" /></button>
                                   
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table style="margin-top:0px" width="80%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.occurCondiction1" /></td>
                                    <td width="80%" colspan="5">
                                        <#if fault.occurCondiction1?? && fault.occurCondiction1 == 0>
                                            <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="0" checked/><@s.text name="create.fault.label.oc1.deviceStarting" />
                                        <#else>
                                            <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="0"/><@s.text name="create.fault.label.oc1.deviceStarting" />
                                        </#if>
                                        <#if fault.occurCondiction1?? && fault.occurCondiction1 == 1>
                                            <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="1" checked/><@s.text name="create.fault.label.oc1.trading" />
                                        <#else>
                                            <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="1"/><@s.text name="create.fault.label.oc1.trading" />
                                        </#if>
                                        <#if fault.occurCondiction1?? && fault.occurCondiction1 == 2>
                                            <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="2" checked/><@s.text name="create.fault.label.oc1.staffHandling" />
                                        <#else>
                                            <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="2"/><@s.text name="create.fault.label.oc1.staffHandling" />
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.occurCondiction2" /></td>
                                    <td width="80%" colspan="5">
                                        <#if fault.occurCondiction2?? && fault.occurCondiction2 == 0>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="0" checked/><@s.text name="create.fault.label.oc2.pay" />
                                        <#else>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="0"/><@s.text name="create.fault.label.oc2.pay" />
                                        </#if>
                                        <#if fault.occurCondiction2?? && fault.occurCondiction2 == 1>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="1" checked/><@s.text name="create.fault.label.oc2.save" />
                                        <#else>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="1"/><@s.text name="create.fault.label.oc2.save" />
                                        </#if>
                                        <#if fault.occurCondiction2?? && fault.occurCondiction2 == 2>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="2" checked/><@s.text name="create.fault.label.oc2.balanceConfirm" />
                                        <#else>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="2"/><@s.text name="create.fault.label.oc2.balanceConfirm" />
                                        </#if>
                                        <#if fault.occurCondiction2?? && fault.occurCondiction2 == 3>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="3" checked/><@s.text name="create.fault.label.oc2.loading" />
                                        <#else>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="3"/><@s.text name="create.fault.label.oc2.loading" />
                                        </#if>
                                        <#if fault.occurCondiction2?? && fault.occurCondiction2 == 4>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="4" checked/><@s.text name="create.fault.label.oc2.recovery" />
                                        <#else>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="4"/><@s.text name="create.fault.label.oc2.recovery" />
                                        </#if>
                                        <#if fault.occurCondiction2?? && fault.occurCondiction2 == 5>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="5" checked/><@s.text name="create.fault.label.oc2.testing" />
                                        <#else>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="5"/><@s.text name="create.fault.label.oc2.testing" />
                                        </#if>
                                        <#if fault.occurCondiction2?? && fault.occurCondiction2 == 6>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="6" checked/><@s.text name="create.fault.label.oc2.waiting" />
                                        <#else>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="6"/><@s.text name="create.fault.label.oc2.waiting" />
                                        </#if>
                                        <#if fault.occurCondiction2?? && fault.occurCondiction2 == 7>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="7" checked/><@s.text name="create.fault.label.oc2.other" />
                                        <#else>
                                            <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="7"/><@s.text name="create.fault.label.oc2.other" />
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.errorCode" /></td>
                                    <td width="80%" colspan="3"><input id="errorCode" type="text" name="fault.errorCode" value="${fault.errorCode!""}" maxLength="10" size="14" /></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.appearance" /></td>
                                    <td width="80%" colspan="5">
                                        <input id="appearance" type="text" name="fault.appearance" value="${fault.appearance!""}" maxLength="40" size="70" />
                                        &nbsp;<label class="needed"><@s.text name="input_needed" /></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.reason" /></td>
                                    <td width="80%" colspan="5">
                                        <input id="reason" type="text" name="fault.reason" value="${fault.reason!""}" maxLength="40" size="70" />
                                        &nbsp;<label class="needed"><@s.text name="input_needed_before_finish" /></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.strategy" /></td>
                                    <td width="80%" colspan="5">
                                        <input id="strategy" type="text" name="fault.strategy" value="${fault.strategy!""}" maxLength="40" size="70" />
                                        <a href="javascript:ShowStrategyDetail()">
                                            <img id="strategyDetailImg" style="vertical-align:top;horizontal-align:center;border:0px none;" src="../../../images/detail.png"></img>
                                        </a>
                                        &nbsp;<label class="needed"><@s.text name="input_needed_before_finish" /></label>
                                    </td>
                                </tr>
                                <tr id="tr_strategyDetail" style="display:none">
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.strategyDetail" /></td>
                                    <td width="80%" colspan="5">
                                        <textarea id="strategyDetail" name="fault.strategyDetail" onPropertyChange=textCounter(this) oninput=textCounter(this) rows="3" cols="70" >${fault.strategyDetail!""}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.result" /></td>
                                    <td width="80%" colspan="5">
                                        <select name="fault.resultId">
                                            <@s.action name="faultStatus_drop" executeResult="true" ignoreContextParams="true">
                                                <@s.param name="selectedFaultStatusId">${fault.resultId!""}</@s.param>
                                            </@s.action>
                                        </select>
                                        &nbsp;<label class="needed"><@s.text name="select_needed" /></label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table style="margin-bottom:0px" width="80%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td style="border-right:none" width="90%" class="lcell">
                                        <b><@s.text name="create.fault.label.detailPhase" /></b>
                                    </td>
                                    <td style="border-left:none" width="10%" align="right" class="lcell">
                                        <a href="javascript:ShowDetailPhase()">
                                            <img id="detailPhaseImg" style="vertical-align:top;horizontal-align:center;border:0px none;" src="../../../images/plus.png"></img>
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div id="detailPhase" style="display:none;position:relative;">
                            <table style="margin-top:0px;margin-bottom:0px" width="80%" align="center" class="field_tbl">
                                <tbody>
                                    <tr>
                                        <td style="border-top:0px;" width="20%" class="lcell" rowspan="4"><@s.text name="create.fault.label.operation" /></td>
                                        <td style="border-top:0px;" width="5%" class="lcell"><@s.text name="create.fault.label.operation1" /></td>
                                        <td style="border-top:0px;" width="35%" colspan="3">
                                            <input id="operation1" type="text" name="fault.operation1" value="${fault.operation1!""}" maxLength="30" size="30" />
                                        </td>
                                        <td style="border-top:0px;" width="5%" class="lcell"><@s.text name="create.fault.label.operation2" /></td>
                                        <td style="border-top:0px;" width="35%" colspan="3">
                                            <input id="operation2" type="text" name="fault.operation2" value="${fault.operation2!""}" maxLength="30" size="30" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="5%" class="lcell"><@s.text name="create.fault.label.operation3" /></td>
                                        <td width="35%" colspan="3">
                                            <input id="operation3" type="text" name="fault.operation3" value="${fault.operation3!""}" maxLength="30" size="30" />
                                        </td>
                                        <td width="5%" class="lcell"><@s.text name="create.fault.label.operation4" /></td>
                                        <td width="35%" colspan="3">
                                            <input id="operation4" type="text" name="fault.operation4" value="${fault.operation4!""}" maxLength="30" size="30" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="5%" class="lcell"><@s.text name="create.fault.label.operation5" /></td>
                                        <td width="35%" colspan="3">
                                            <input id="operation5" type="text" name="fault.operation5" value="${fault.operation5!""}" maxLength="30" size="30" />
                                        </td>
                                        <td width="5%" class="lcell"><@s.text name="create.fault.label.operation6" /></td>
                                        <td width="35%" colspan="3">
                                            <input id="operation6" type="text" name="fault.operation6" value="${fault.operation6!""}" maxLength="30" size="30" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="5%" class="lcell"><@s.text name="create.fault.label.operation7" /></td>
                                        <td width="35%" colspan="3">
                                            <input id="operation7" type="text" name="fault.operation7" value="${fault.operation7!""}" maxLength="30" size="30" />
                                        </td>
                                        <td width="5%" class="lcell"><@s.text name="create.fault.label.operation8" /></td>
                                        <td width="35%" colspan="3">
                                            <input id="operation8" type="text" name="fault.operation8" value="${fault.operation8!""}" maxLength="30" size="30" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="20%" class="lcell"><@s.text name="create.fault.label.abnormalDisplay" /></td>
                                        <td width="15%" class="lcell" colspan="3"><@s.text name="create.fault.label.cashLeft" /></td>
                                        <td width="25%">
                                            <#if fault.cashLeft?? && fault.cashLeft == 1>
                                                <input type="radio" id="cashLeft" name="fault.cashLeft" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                            <#else>
                                                <input type="radio" id="cashLeft" name="fault.cashLeft" value="1"/><@s.text name="create.fault.radio.label.has" />
                                            </#if>
                                            <#if fault.cashLeft?? && fault.cashLeft == 0>
                                                <input type="radio" id="cashLeft" name="fault.cashLeft" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                            <#else>
                                                <input type="radio" id="cashLeft" name="fault.cashLeft" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                            </#if>
                                        </td>
                                        <td width="15%" class="lcell" colspan="3"><@s.text name="create.fault.label.exceptionDisplay" /></td>
                                        <td width="25%">
                                            <#if fault.exceptionDisplay?? && fault.exceptionDisplay == 1>
                                                <input type="radio" id="exceptionDisplay" name="fault.exceptionDisplay" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                            <#else>
                                                <input type="radio" id="exceptionDisplay" name="fault.exceptionDisplay" value="1"/><@s.text name="create.fault.radio.label.has" />
                                            </#if>
                                            <#if fault.exceptionDisplay?? && fault.exceptionDisplay == 0>
                                                <input type="radio" id="exceptionDisplay" name="fault.exceptionDisplay" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                            <#else>
                                                <input type="radio" id="exceptionDisplay" name="fault.exceptionDisplay" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                            </#if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="20%" class="lcell" rowspan="2"><@s.text name="create.fault.label.screenDisplay" /></td>
                                        <td width="15%" class="lcell" colspan="3"><@s.text name="create.fault.label.display" /></td>
                                        <td width="25%">
                                            <#if fault.display?? && fault.display == 1>
                                                <input type="radio" id="display" name="fault.display" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                            <#else>
                                                <input type="radio" id="display" name="fault.display" value="1"/><@s.text name="create.fault.radio.label.has" />
                                            </#if>
                                            <#if fault.display?? && fault.display == 0>
                                                <input type="radio" id="display" name="fault.display" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                            <#else>
                                                <input type="radio" id="display" name="fault.display" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                            </#if>
                                        </td>
                                        <td width="15%" class="lcell" colspan="3"><@s.text name="create.fault.label.backLight" /></td>
                                        <td width="25%">
                                            <#if fault.backLight?? && fault.backLight == 1>
                                                <input type="radio" id="backLight" name="fault.backLight" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                            <#else>
                                                <input type="radio" id="backLight" name="fault.backLight" value="1"/><@s.text name="create.fault.radio.label.has" />
                                            </#if>
                                            <#if fault.backLight?? && fault.backLight == 0>
                                                <input type="radio" id="backLight" name="fault.backLight" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                            <#else>
                                                <input type="radio" id="backLight" name="fault.backLight" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                            </#if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="15%" class="lcell" colspan="3"><@s.text name="create.fault.label.displayContent" /></td>
                                        <td width="65%" colspan="5">
                                            <input id="displayContent" type="text" name="fault.displayContent" value="${fault.displayContent!""}" maxLength="15" size="30" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="20%" class="lcell"><@s.text name="create.fault.label.touchBoardInput" /></td>
                                        <td width="15%" class="lcell" colspan="3"><@s.text name="create.fault.label.inputable" /></td>
                                        <td width="25%">
                                            <#if fault.inputable?? && fault.inputable == 1>
                                                <input type="radio" id="inputable" name="fault.inputable" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                            <#else>
                                                <input type="radio" id="inputable" name="fault.inputable" value="1"/><@s.text name="create.fault.radio.label.has" />
                                            </#if>
                                            <#if fault.inputable?? && fault.inputable == 0>
                                                <input type="radio" id="inputable" name="fault.inputable" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                            <#else>
                                                <input type="radio" id="inputable" name="fault.inputable" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                            </#if>
                                        </td>
                                        <td width="15%" class="lcell" colspan="3"><@s.text name="create.fault.label.knockSound" /></td>
                                        <td width="25%">
                                            <#if fault.knockSound?? && fault.knockSound == 1>
                                                <input type="radio" id="knockSound" name="fault.knockSound" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                            <#else>
                                                <input type="radio" id="knockSound" name="fault.knockSound" value="1"/><@s.text name="create.fault.radio.label.has" />
                                            </#if>
                                            <#if fault.knockSound?? && fault.knockSound == 0>
                                                <input type="radio" id="knockSound" name="fault.knockSound" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                            <#else>
                                                <input type="radio" id="knockSound" name="fault.knockSound" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                            </#if>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <table style="margin-top:0px" width="80%" align="center" class="field_tbl">
                                <tbody>
                                    <tr>
                                        <td width="100%" colspan="2">
                                            <@s.text name="create.fault.label.msg1" />
                                            <#if fault.reset?? && fault.reset == 1>
                                                <input type="radio" id="reset" name="fault.reset" value="1" checked/><@s.text name="create.fault.label.msg1.can" />
                                            <#else>
                                                <input type="radio" id="reset" name="fault.reset" value="1"/><@s.text name="create.fault.label.msg1.can" />
                                            </#if>
                                            <#if fault.reset?? && fault.reset == 0>
                                                <input type="radio" id="reset" name="fault.reset" value="0" checked/><@s.text name="create.fault.label.msg1.cannot" />
                                            <#else>
                                                <input type="radio" id="reset" name="fault.reset" value="0"/><@s.text name="create.fault.label.msg1.cannot" />
                                            </#if>
                                            <@s.text name="create.fault.label.rightParentheses" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" colspan="2">
                                            <@s.text name="create.fault.label.msg2" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" colspan="2">
                                            <@s.text name="create.fault.label.standard.msg3" />
                                            <#if fault.cutPower?? && fault.cutPower == 1>
                                                <input type="radio" id="cutPower" name="fault.cutPower" value="1" checked/><@s.text name="create.fault.label.msg3.can" />
                                            <#else>
                                                <input type="radio" id="cutPower" name="fault.cutPower" value="1"/><@s.text name="create.fault.label.msg3.can" />
                                            </#if>
                                            <#if fault.cutPower?? && fault.cutPower == 0>
                                                <input type="radio" id="cutPower" name="fault.cutPower" value="0" checked/><@s.text name="create.fault.label.msg3.cannot" />
                                            <#else>
                                                <input type="radio" id="cutPower" name="fault.cutPower" value="0"/><@s.text name="create.fault.label.msg3.cannot" />
                                            </#if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" colspan="2">
                                            <@s.text name="create.fault.label.msg4" />
                                            <#if fault.rebootNormally?? && fault.rebootNormally == 1>
                                                <input type="radio" id="rebootNormally" name="fault.rebootNormally" value="1" checked/><@s.text name="create.fault.label.msg4.normal" />
                                            <#else>
                                                <input type="radio" id="rebootNormally" name="fault.rebootNormally" value="1"/><@s.text name="create.fault.label.msg4.normal" />
                                            </#if>
                                            <#if fault.rebootNormally?? && fault.rebootNormally == 0>
                                                <input type="radio" id="rebootNormally" name="fault.rebootNormally" value="0" checked/><@s.text name="create.fault.label.msg4.abnormal" />
                                            <#else>
                                                <input type="radio" id="rebootNormally" name="fault.rebootNormally" value="0"/><@s.text name="create.fault.label.msg4.abnormal" />
                                            </#if>
                                            <@s.text name="create.fault.label.rightParentheses" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" colspan="2">
                                            <@s.text name="create.fault.label.msg5" />
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="25%" class="lcell"><@s.text name="create.fault.label.motionCounter" /></td>
                                        <td width="75%">
                                            <input id="motionCounter" type="text" name="fault.motionCounter" value="${fault.motionCounter!""}" maxLength="4" size="80" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="25%" class="lcell"><@s.text name="create.fault.label.errorNo" /></td>
                                        <td width="75%">
                                            <input id="errorNo" type="text" name="fault.errorNo" value="${fault.errorNo!""}" maxLength="10" size="80" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="25%" class="lcell"><@s.text name="create.fault.label.noRepon" /></td>
                                        <td width="75%">
                                            <input id="noRepon" type="text" name="fault.noRepon" value="${fault.noRepon!""}" maxLength="40" size="80" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="25%" class="lcell"><@s.text name="create.fault.label.standard.otherDisplay" /></td>
                                        <td width="75%">
                                            <input id="otherDisplay" type="text" name="fault.otherDisplay" value="${fault.otherDisplay!""}" maxLength="40" size="80" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <table width="80%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="100%" class="lcell" colspan="4"><b><@s.text name="create.fault.label.dataCollect" /></b></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.traceInfomation" /></td>
                                    <td width="30%">
                                        <#if fault.traceInfomation?? && fault.traceInfomation == 1>
                                            <input type="radio" id="traceInfomation" name="fault.traceInfomation" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                        <#else>
                                            <input type="radio" id="traceInfomation" name="fault.traceInfomation" value="1"/><@s.text name="create.fault.radio.label.has" />
                                        </#if>
                                        <#if fault.traceInfomation?? && fault.traceInfomation == 0>
                                            <input type="radio" id="traceInfomation" name="fault.traceInfomation" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                        <#else>
                                            <input type="radio" id="traceInfomation" name="fault.traceInfomation" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                        </#if>
                                    </td>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.faultRecord" /></td>
                                    <td width="30%">
                                        <#if fault.faultRecord?? && fault.faultRecord == 1>
                                            <input type="radio" id="faultRecord" name="fault.faultRecord" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                        <#else>
                                            <input type="radio" id="faultRecord" name="fault.faultRecord" value="1"/><@s.text name="create.fault.radio.label.has" />
                                        </#if>
                                        <#if fault.faultRecord?? && fault.faultRecord == 0>
                                            <input type="radio" id="faultRecord" name="fault.faultRecord" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                        <#else>
                                            <input type="radio" id="faultRecord" name="fault.faultRecord" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.statistics" /></td>
                                    <td width="30%">
                                        <#if fault.statistics?? && fault.statistics == 1>
                                            <input type="radio" id="statistics" name="fault.statistics" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                        <#else>
                                            <input type="radio" id="statistics" name="fault.statistics" value="1"/><@s.text name="create.fault.radio.label.has" />
                                        </#if>
                                        <#if fault.statistics?? && fault.statistics == 0>
                                            <input type="radio" id="statistics" name="fault.statistics" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                        <#else>
                                            <input type="radio" id="statistics" name="fault.statistics" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                        </#if>
                                    </td>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.tradeLog" /></td>
                                    <td width="30%">
                                        <#if fault.tradeLog?? && fault.tradeLog == 1>
                                            <input type="radio" id="tradeLog" name="fault.tradeLog" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                        <#else>
                                            <input type="radio" id="tradeLog" name="fault.tradeLog" value="1"/><@s.text name="create.fault.radio.label.has" />
                                        </#if>
                                        <#if fault.tradeLog?? && fault.tradeLog == 0>
                                            <input type="radio" id="tradeLog" name="fault.tradeLog" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                        <#else>
                                            <input type="radio" id="tradeLog" name="fault.tradeLog" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.applicationVersion1" /></td>
                                    <td width="80%" colspan="3">
                                        <#if fault.applicationVersion1?? && fault.applicationVersion1 == 1>
                                            <input type="radio" id="applicationVersion1" name="fault.applicationVersion1" value="1" checked/><@s.text name="create.fault.radio.label.has" />
                                        <#else>
                                            <input type="radio" id="applicationVersion1" name="fault.applicationVersion1" value="1"/><@s.text name="create.fault.radio.label.has" />
                                        </#if>
                                        <#if fault.applicationVersion1?? && fault.applicationVersion1 == 0>
                                            <input type="radio" id="applicationVersion1" name="fault.applicationVersion1" value="0" checked/><@s.text name="create.fault.radio.label.hasnot" />
                                        <#else>
                                            <input type="radio" id="applicationVersion1" name="fault.applicationVersion1" value="0"/><@s.text name="create.fault.radio.label.hasnot" />
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.fault.label.others" /></td>
                                    <td width="80%" colspan="3">
                                        <input id="others" type="text" name="fault.others" value="${fault.others!""}" maxLength="80" size="60" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <#if loginUser.hasPermission("FA001_34")>
                            <table id="faultSparesTab" width="80%" align="center" class="field_tbl">
                                <tbody id="faultSparesTab_body">
                                    <tr>
                                        <td style="border-right:none" width="86%" class="lcell" colspan="7">
                                            <b><@s.text name="create.fault.label.usedPart" /></b>
                                        </td>
                                        <td style="border-left:none" width="24%" align="right" class="lcell" colspan="2">
                                            <button type="button" style="height:20px;" onClick="addRow()" ><@s.text name="create.fault.button.addRow" /></button>
                                            <button type="button" style="height:20px;" onClick="delRow()" ><@s.text name="create.fault.button.delRow" /></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="4%" class="lcell"><@s.text name="create.fault.label.number" /></td>
                                        <td width="12%" class="lcell"><@s.text name="create.fault.label.name" /></td>
                                        <td width="12%" class="lcell"><@s.text name="create.fault.label.pictureNo" /></td>
                                        <td width="12%" class="lcell"><@s.text name="create.fault.label.warehouse" /></td>
                                        <td width="12%" class="lcell"><@s.text name="create.fault.label.no" /></td>
                                        <td width="12%" class="lcell"><@s.text name="create.fault.label.applyDate" /></td>
                                        <td width="12%" class="lcell"><@s.text name="create.fault.label.deliverDate" /></td>
                                        <td width="12%" class="lcell"><@s.text name="create.fault.label.replaceDate" /></td>
                                        <td width="12%" class="lcell"><@s.text name="create.fault.label.ihscReceiveDate" /></td>
                                    </tr>
                                    <#list faultSparesList as faultSpares>
                                        <tr id="tr_faultSpares${faultSpares_index}">
                                            <td>${faultSpares_index + 1}</td>
                                            <td><input type="text" name="faultSparesList[${faultSpares_index}].name" value="${faultSpares.name!""}" maxLength="30" size="9" /></td>
                                            <td><input type="text" name="faultSparesList[${faultSpares_index}].pictureNo" value="${faultSpares.pictureNo!""}" maxLength="30" size="9" /></td>
                                            <td><input type="text" name="faultSparesList[${faultSpares_index}].warehouse" value="${faultSpares.warehouse!""}" maxLength="30" size="9" /></td>
                                            <td><input type="text" name="faultSparesList[${faultSpares_index}].no" value="${faultSpares.no!""}" maxLength="8" size="9" /></td>
                                            <td><input type="text" name="faultSparesList[${faultSpares_index}].applyDate" value="${faultSpares.applyDate!""}" maxLength="10" size="9" /></td>
                                            <td><input type="text" name="faultSparesList[${faultSpares_index}].deliverDate" value="${faultSpares.deliverDate!""}" maxLength="10" size="9" /></td>
                                            <td><input type="text" name="faultSparesList[${faultSpares_index}].replaceDate" value="${faultSpares.replaceDate!""}" maxLength="10" size="9" /></td>
                                            <td><input type="text" name="faultSparesList[${faultSpares_index}].receiveDate" value="${faultSpares.receiveDate!""}" maxLength="10" size="9" /></td>
                                        </tr>
                                    </#list>
                                </tbody>
                            </table>
                       </#if>     
                    </td>
                  </tr>
               </tbody>
             </table>  
            <div class="btn_row">
              <#if loginUser.hasPermission("FA001_21")>
                <button type="button" onClick="changeAction('FA001_21')" ><@s.text name="btn_add" /></button>
              </#if>       
            </div>
    
            <input type="hidden" name="install.id" value="${install.id!-1}"/>
            <input type="hidden" name="fault.agentId" value="${fault.agentId!-1}"/>
            <input type="hidden" name="fault.faultMachineType" value="${fault.faultMachineType!""}"/>
            <input type="hidden" name="valErrPage" value="${valErrPage!""}"/>
            <input type="hidden" id="timeName" />
        </form>
      </div> 
    </div>
    <!-- method1 start -->
    <#include "../components/popTimeWin.ftl"/>
    <!-- method1 end -->
  <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>