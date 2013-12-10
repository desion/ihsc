<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${fault.model!""}<@s.text name="FA001_80_title" /></title>
<#include "../common_header.ftl"/>  
<script type="text/javascript">
function ShowDetailPhase() {
    $("#detailPhase").toggle();
    if ($("#detailPhaseImg").attr("src") == "../../../images/plus.png") {
        $("#detailPhaseImg").attr("src", "../../../images/minus.png");
    } else {
        $("#detailPhaseImg").attr("src", "../../../images/plus.png");
    }
}
function ShowStrategyDetail() {
    $("#td_strategyDetail1").toggle();
    $("#td_strategyDetail2").toggle();
}
</script>
</head>
<#include "../components/error_reference.ftl"/>
<body>
<#escape x as x?html>
<div id="container">
<form id="viewForm" method="post" enctype ="multipart/form-data">
    <div id="view">
        <div class="fields">
            <table style="margin-bottom:0px" width="100%" align="center" class="field_tbl">
                <tbody>
                    <tr>
                        <td style="border-top:0px;border-left:0px;border-right:0px;" colspan="6">
                            ${fault.model!""}<@s.text name="FA001_80_title" />
                        </td>
                    </tr>
                    <tr>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.purpose" /></td>
                        <td width="21%">${fault.purpose!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.faultType" /></td>
                        <td width="21%">${fault.faultTypeName!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.managementId" /></td>
                        <td width="22%">${fault.managementId!""}</td>
                    </tr>
                    <tr>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.occurDate" /></td>
                        <td width="21%">${fault.occurDate!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.occurTime" /></td>
                        <td width="21%">${fault.occurTime!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.repairCompany" /></td>
                        <td width="22%">${fault.repairCompanyName!""}</td>
                    </tr>
                    <tr>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.cityName" /></td>
                        <td width="21%">${fault.cityName!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.customerName" /></td>
                        <td width="21%">${fault.customerName!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.operator" /></td>
                        <td width="22%">${fault.operatorName!""}</td>
                    </tr>
                    <tr>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.productCategoryName" /></td>
                        <td width="21%">${fault.productCategoryName!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.model" /></td>
                        <td width="55%" colspan="3">${fault.model!""}</td>
                    </tr>
                    <tr>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.manufactureNo" /></td>
                        <td width="21%">${fault.manufactureNo!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.installDate" /></td>
                        <td width="21%">${fault.installDate!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.guaranteeDate" /></td>
                        <td width="22%">${fault.guaranteeDate!""}</td>
                    </tr>
                    <tr>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.gabio.mainEpver" /></td>
                        <td width="21%">${fault.mainEpver!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.gabio.bidEpver" /></td>
                        <td width="21%">${fault.bidEpver!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.repairState" /></td>
                        <td width="22%">
                            <#if fault.repairState == 0>
                                <@s.text name="view.fault.label.repairState.notfinish" />
                            <#else>
                                <@s.text name="view.fault.label.repairState.finish" />
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.isState" /></td>
                        <td width="21%">${fault.isState!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.supportType" /></td>
                        <td width="21%">${fault.supportTypeName!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.reportDate" /></td>
                        <td width="22%">${fault.reportDate!""}&nbsp;${fault.reportTime!""}</td>
                    </tr>
                    <tr>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.applicationVersion" /></td>
                        <td width="21%">
                            <#if (fault.applicationVersion?? && fault.applicationVersion == 0)>
                                <input type="radio" id="applicationVersionSel" name="fault.applicationVersion" value="0" checked disabled/><@s.text name="view.fault.label.hitachiAP" />
                            <#else>
                                <input type="radio" id="applicationVersionSel" name="fault.applicationVersion" value="0" disabled/><@s.text name="view.fault.label.hitachiAP" />
                            </#if>
                            <#if (fault.applicationVersion?? && fault.applicationVersion == 1)>
                                <input type="radio" id="applicationVersionSel" name="fault.applicationVersion" value="1" checked disabled/><@s.text name="view.fault.label.unificationAP" />
                            <#else>
                                <input type="radio" id="applicationVersionSel" name="fault.applicationVersion" value="1" disabled/><@s.text name="view.fault.label.unificationAP" />
                            </#if>
                        </td>
                        <td width="12%" class="lcell">
                            <#if (fault.applicationVersion?? && fault.applicationVersion == 1)>
                                <@s.text name="view.fault.label.applicationVersionDetail1.apVersion" />
                            <#else>
                                <@s.text name="view.fault.label.applicationVersionDetail1.version" />
                            </#if>
                        </td>
                        <td width="21%">${fault.applicationVersionDetail1!""}</td>
                        <td width="12%" class="lcell">
                            <#if (fault.applicationVersion?? && fault.applicationVersion == 1)>
                                <@s.text name="view.fault.label.applicationVersionDetail2.spVersion" />
                            <#else>
                                <@s.text name="view.fault.label.applicationVersionDetail2.botai" />
                            </#if>
                        </td>
                        <td width="22%">${fault.applicationVersionDetail2!""}</td>
                    </tr>
                    <tr>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.faultPart" /></td>
                        <td width="21%">${fault.faultPartName!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.faultPartType" /></td>
                        <td width="21%">${fault.faultPartTypeName!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.finishDate" /></td>
                        <td width="22%">${fault.finishDate!""}&nbsp;${fault.finishTime!""}</td>
                    </tr>
                    <tr>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.supporter" /></td>
                        <td width="21%">${fault.supporterName!""}</td>
                        <td width="12%" class="lcell"><@s.text name="view.fault.label.follower" /></td>
                        <td width="55%" colspan="3">${fault.followerName!""}</td>
                    </tr>
                </tbody>
            </table>
            <table style="margin-top:0px;table-layout:fixed" width="100%" align="center" class="field_tbl">
                <tbody>
                    <tr>
                        <td width="2%" class="lcell" rowspan="8"><@s.text name="view.fault.label.one" /></td>
                        <td width="10%" class="lcell"><@s.text name="view.fault.label.occurCondiction1" /></td>
                        <td width="88%" colspan="4">
                            <#if fault.occurCondiction1?? && fault.occurCondiction1 == 0>
                                <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="0" checked disabled/><@s.text name="view.fault.label.oc1.deviceStarting" />
                            <#else>
                                <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="0" disabled/><@s.text name="view.fault.label.oc1.deviceStarting" />
                            </#if>
                            <#if fault.occurCondiction1?? && fault.occurCondiction1 == 1>
                                <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="1" checked disabled/><@s.text name="view.fault.label.oc1.trading" />
                            <#else>
                                <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="1" disabled/><@s.text name="view.fault.label.oc1.trading" />
                            </#if>
                            <#if fault.occurCondiction1?? && fault.occurCondiction1 == 2>
                                <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="2" checked disabled/><@s.text name="view.fault.label.oc1.staffHandling" />
                            <#else>
                                <input type="radio" id="occurCondiction1" name="fault.occurCondiction1" value="2" disabled/><@s.text name="view.fault.label.oc1.staffHandling" />
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td width="10%" class="lcell"><@s.text name="view.fault.label.occurCondiction2" /></td>
                        <td width="90%" colspan="4">
                            <#if fault.occurCondiction2?? && fault.occurCondiction2 == 0>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="0" checked disabled/><@s.text name="view.fault.label.oc2.pay" />
                            <#else>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="0" disabled/><@s.text name="view.fault.label.oc2.pay" />
                            </#if>
                            <#if fault.occurCondiction2?? && fault.occurCondiction2 == 1>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="1" checked disabled/><@s.text name="view.fault.label.oc2.save" />
                            <#else>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="1" disabled/><@s.text name="view.fault.label.oc2.save" />
                            </#if>
                            <#if fault.occurCondiction2?? && fault.occurCondiction2 == 2>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="2" checked disabled/><@s.text name="view.fault.label.oc2.balanceConfirm" />
                            <#else>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="2" disabled/><@s.text name="view.fault.label.oc2.balanceConfirm" />
                            </#if>
                            <#if fault.occurCondiction2?? && fault.occurCondiction2 == 3>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="3" checked disabled/><@s.text name="view.fault.label.oc2.loading" />
                            <#else>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="3" disabled/><@s.text name="view.fault.label.oc2.loading" />
                            </#if>
                            <#if fault.occurCondiction2?? && fault.occurCondiction2 == 4>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="4" checked disabled/><@s.text name="view.fault.label.oc2.recovery" />
                            <#else>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="4" disabled/><@s.text name="view.fault.label.oc2.recovery" />
                            </#if>
                            <#if fault.occurCondiction2?? && fault.occurCondiction2 == 5>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="5" checked disabled/><@s.text name="view.fault.label.oc2.testing" />
                            <#else>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="5" disabled/><@s.text name="view.fault.label.oc2.testing" />
                            </#if>
                            <#if fault.occurCondiction2?? && fault.occurCondiction2 == 6>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="6" checked disabled/><@s.text name="view.fault.label.oc2.waiting" />
                            <#else>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="6" disabled/><@s.text name="view.fault.label.oc2.waiting" />
                            </#if>
                            <#if fault.occurCondiction2?? && fault.occurCondiction2 == 7>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="7" checked disabled/><@s.text name="view.fault.label.oc2.other" />
                            <#else>
                                <input type="radio" id="occurCondiction2" name="fault.occurCondiction2" value="7" disabled/><@s.text name="view.fault.label.oc2.other" />
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td width="10%" class="lcell"><@s.text name="view.fault.label.gabio.ecErrorCode" /></td>
                        <td width="39%">${fault.ecErrorCode!""}</td>
                        <td width="10%" class="lcell"><@s.text name="view.fault.label.gabio.mtcCode" /></td>
                        <td width="39%" colspan="2">${fault.mtcCode!""}</td>
                    </tr>
                    <tr>
                        <td width="10%" class="lcell"><@s.text name="view.fault.label.appearance" /></td>
                        <td width="90%" colspan="4">${fault.appearance!""}</td>
                    </tr>
                    <tr>
                        <td width="10%" class="lcell"><@s.text name="view.fault.label.reason" /></td>
                        <td width="90%" colspan="4">${fault.reason!""}</td>
                    </tr>
                    <tr>
                        <td width="10%" style="border-bottom:none" class="lcell"><@s.text name="view.fault.label.strategy" /></td>
                        <td width="71%" style="border-bottom:none;border-right:none" colspan="3">${fault.strategy!""}</td>
                        <td width="17%" style="border-bottom:none;border-left:none" align="right">
                            <a href="javascript:ShowStrategyDetail()">
                                <img id="strategyDetailImg" style="vertical-align:top;horizontal-align:center;border:0px none;" src="../../../images/detail.png"></img>
                            </a>
                        </td>
                    </tr>
                    <tr id="tr_strategyDetail">
                        <td id="td_strategyDetail1" width="10%" style="display:none" class="lcell"><@s.text name="view.fault.label.strategyDetail" /></td>
                        <td id="td_strategyDetail2" width="88%" style="display:none;word-break:break-all;word-wrap:break-word" colspan="4">${fault.strategyDetail!""}</td>
                    </tr>
                    <tr>
                        <td width="10%" class="lcell"><@s.text name="view.fault.label.result" /></td>
                        <td width="88%" colspan="4">${fault.result!""}</td>
                    </tr>
                </tbody>
            </table>
            <table style="margin-bottom:0px" width="100%" align="center" class="field_tbl">
                <tbody>
                    <tr>
                        <td style="border-right:none" width="90%" class="lcell">
                            <b><@s.text name="view.fault.label.detailPhase" /></b>
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
                <table style="margin-top:0px;margin-bottom:0px;table-layout:fixed" width="100%" align="center" class="field_tbl">
                    <tbody>
                        <tr>
                            <td style="border-top:0px;" width="2%" class="lcell" rowspan="4"><@s.text name="view.fault.label.two" /></td>
                            <td style="border-top:0px;" width="18%" class="lcell" rowspan="4"><@s.text name="view.fault.label.operation" /></td>
                            <td style="border-top:0px;" width="5%" class="lcell"><@s.text name="view.fault.label.operation1" /></td>
                            <td style="border-top:0px;" width="35%">${fault.operation1!""}</td>
                            <td style="border-top:0px;" width="5%" class="lcell"><@s.text name="view.fault.label.operation2" /></td>
                            <td style="border-top:0px;" width="35%">${fault.operation2!""}</td>
                        </tr>
                        <tr>
                            <td width="5%" class="lcell"><@s.text name="view.fault.label.operation3" /></td>
                            <td width="35%">${fault.operation3!""}</td>
                            <td width="5%" class="lcell"><@s.text name="view.fault.label.operation4" /></td>
                            <td width="35%">${fault.operation4!""}</td>
                        </tr>
                        <tr>
                            <td width="5%" class="lcell"><@s.text name="view.fault.label.operation5" /></td>
                            <td width="35%">${fault.operation5!""}</td>
                            <td width="5%" class="lcell"><@s.text name="view.fault.label.operation6" /></td>
                            <td width="35%">${fault.operation6!""}</td>
                        </tr>
                        <tr>
                            <td width="5%" class="lcell"><@s.text name="view.fault.label.operation7" /></td>
                            <td width="35%">${fault.operation7!""}</td>
                            <td width="5%" class="lcell"><@s.text name="view.fault.label.operation8" /></td>
                            <td width="35%">${fault.operation8!""}</td>
                        </tr>
                    </tbody>
                </table>
                <table style="margin-top:0px;margin-bottom:0px;table-layout:fixed" width="100%" align="center" class="field_tbl">
                    <tbody>
                             <tr>
                                    <td width="2%" class="lcell" rowspan="4"><@s.text name="view.fault.label.three" /></td>
                                    <td width="18%" class="lcell"><@s.text name="view.fault.label.abnormalDisplay" /></td>
                                    <td width="10%" class="lcell" colspan="3"><@s.text name="create.fault.label.gabio.cashControlLight" /></td>
                                    <td width="30%">
                                        <#if fault.cashControlLight?? && fault.cashControlLight == 1>
                                            <input type="radio" id="cashControlLight" name="fault.cashControlLight" value="1" checked disabled/><@s.text name="view.fault.label.gabio.light.out" />
                                        <#else>
                                            <input type="radio" id="cashControlLight" name="fault.cashControlLight" value="1" disabled/><@s.text name="view.fault.label.gabio.light.out" />
                                        </#if>
                                        <#if fault.cashControlLight?? && fault.cashControlLight == 2>
                                            <input type="radio" id="cashControlLight" name="fault.cashControlLight" value="2" checked disabled/><@s.text name="view.fault.label.gabio.light.stayBright" />
                                        <#else>
                                            <input type="radio" id="cashControlLight" name="fault.cashControlLight" value="2" disabled/><@s.text name="view.fault.label.gabio.light.stayBright" />
                                        </#if>
                                        <#if fault.cashControlLight?? && fault.cashControlLight == 3>
                                            <input type="radio" id="cashControlLight" name="fault.cashControlLight" value="3" checked disabled/><@s.text name="view.fault.label.gabio.light.shine" />
                                        <#else>
                                            <input type="radio" id="cashControlLight" name="fault.cashControlLight" value="3" disabled/><@s.text name="view.fault.label.gabio.light.shine" />
                                        </#if>
                                        <#if fault.cashControlLight?? && fault.cashControlLight == 4>
                                            <input type="radio" id="cashControlLight" name="fault.cashControlLight" value="4" checked disabled/><@s.text name="view.fault.label.gabio.light.quickShine" />
                                        <#else>
                                            <input type="radio" id="cashControlLight" name="fault.cashControlLight" value="4" disabled/><@s.text name="view.fault.label.gabio.light.quickShine" />
                                        </#if>
                                    </td>
                                    <td width="10%" class="lcell" colspan="3"><@s.text name="view.fault.label.gabio.faultLight" /></td>
                                    <td width="30%">
                                        <#if fault.faultLight?? && fault.faultLight == 1>
                                            <input type="radio" id="faultLight" name="fault.faultLight" value="1" checked disabled/><@s.text name="view.fault.label.gabio.light.out" />
                                        <#else>
                                            <input type="radio" id="faultLight" name="fault.faultLight" value="1" disabled/><@s.text name="view.fault.label.gabio.light.out" />
                                        </#if>
                                        <#if fault.faultLight?? && fault.faultLight == 2>
                                            <input type="radio" id="faultLight" name="fault.faultLight" value="2" checked disabled/><@s.text name="view.fault.label.gabio.light.stayBright" />
                                        <#else>
                                            <input type="radio" id="faultLight" name="fault.faultLight" value="2" disabled/><@s.text name="view.fault.label.gabio.light.stayBright" />
                                        </#if>
                                        <#if fault.faultLight?? && fault.faultLight == 3>
                                            <input type="radio" id="faultLight" name="fault.faultLight" value="3" checked disabled/><@s.text name="view.fault.label.gabio.light.shine" />
                                        <#else>
                                            <input type="radio" id="faultLight" name="fault.faultLight" value="3" disabled/><@s.text name="view.fault.label.gabio.light.shine" />
                                        </#if>
                                        <#if fault.cashControlLight?? && fault.faultLight == 4>
                                            <input type="radio" id="faultLight" name="fault.faultLight" value="4" checked disabled/><@s.text name="view.fault.label.gabio.light.quickShine" />
                                        <#else>
                                            <input type="radio" id="faultLight" name="fault.faultLight" value="4" disabled/><@s.text name="view.fault.label.gabio.light.quickShine" />
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="18%" class="lcell" rowspan="2"><@s.text name="view.fault.label.gabio.statusDisplay" /></td>
                                    <td width="10%" class="lcell" colspan="3"><@s.text name="view.fault.label.gabio.shineLight" /></td>
                                    <td width="30%" style="word-break:break-all;word-wrap:break-word;">${fault.shineLight!""}</td>
                                    <td width="10%" class="lcell" colspan="3"><@s.text name="view.fault.label.gabio.stayShineLight" /></td>
                                    <td width="30%" style="word-break:break-all;word-wrap:break-word;">${fault.stayShineLight!""}</td>
                                </tr>
                                <tr>
                                    <td width="10%" class="lcell" colspan="3"><@s.text name="view.fault.label.gabio.outLight" /></td>
                                    <td width="70%" colspan="5" style="word-break:break-all;word-wrap:break-word;">${fault.outLight!""}</td>
                                </tr>
                                <tr>
                                    <td width="18%" class="lcell"><@s.text name="view.fault.label.gabio.onlineLight" /></td>
                                    <td width="10%" class="lcell" colspan="3"><@s.text name="view.fault.label.gabio.leftOnlineLight" /></td>
                                    <td width="30%">
                                        <#if fault.leftOnlineLight?? && fault.leftOnlineLight == 1>
                                            <input type="radio" id="leftOnlineLight" name="fault.leftOnlineLight" value="1" checked disabled/><@s.text name="view.fault.label.gabio.light.out" />
                                        <#else>
                                            <input type="radio" id="leftOnlineLight" name="fault.leftOnlineLight" value="1" disabled/><@s.text name="view.fault.label.gabio.light.out" />
                                        </#if>
                                       <#if fault.leftOnlineLight?? && fault.leftOnlineLight == 2>
                                            <input type="radio" id="leftOnlineLight" name="fault.leftOnlineLight" value="2" checked disabled/><@s.text name="view.fault.label.gabio.light.stayBright" />
                                        <#else>
                                            <input type="radio" id="leftOnlineLight" name="fault.leftOnlineLight" value="2" disabled/><@s.text name="view.fault.label.gabio.light.stayBright" />
                                        </#if>
                                       <#if fault.leftOnlineLight?? && fault.leftOnlineLight == 3>
                                            <input type="radio" id="leftOnlineLight" name="fault.leftOnlineLight" value="3" checked disabled/><@s.text name="view.fault.label.gabio.light.shine" />
                                        <#else>
                                            <input type="radio" id="leftOnlineLight" name="fault.leftOnlineLight" value="3" disabled/><@s.text name="view.fault.label.gabio.light.shine" />
                                        </#if>
                                 
                                    </td>
                                    <td width="10%" class="lcell" colspan="3"><@s.text name="view.fault.label.gabio.rightOnlineLight" /></td>
                                    <td width="30%">
                                        <#if fault.rightOnlineLight?? && fault.rightOnlineLight == 1>
                                            <input type="radio" id="rightOnlineLight" name="fault.rightOnlineLight" value="1" checked disabled/><@s.text name="view.fault.label.gabio.light.out" />
                                        <#else>
                                            <input type="radio" id="rightOnlineLight" name="fault.rightOnlineLight" value="1" disabled/><@s.text name="view.fault.label.gabio.light.out" />
                                        </#if>
                                       <#if fault.rightOnlineLight?? && fault.rightOnlineLight == 2>
                                            <input type="radio" id="rightOnlineLight" name="fault.rightOnlineLight" value="2" checked disabled/><@s.text name="view.fault.label.gabio.light.stayBright" />
                                        <#else>
                                            <input type="radio" id="rightOnlineLight" name="fault.rightOnlineLight" value="2" disabled/><@s.text name="view.fault.label.gabio.light.stayBright" />
                                        </#if>
                                       <#if fault.rightOnlineLight?? && fault.rightOnlineLight == 3>
                                            <input type="radio" id="rightOnlineLight" name="fault.rightOnlineLight" value="3" checked disabled/><@s.text name="view.fault.label.gabio.light.shine" />
                                        <#else>
                                            <input type="radio" id="rightOnlineLight" name="fault.rightOnlineLight" value="3" disabled/><@s.text name="view.fault.label.gabio.light.shine" />
                                        </#if>
                                    </td>
                                </tr>
                    </tbody>
                </table>
                <table style="margin-top:0px;table-layout:fixed" width="100%" align="center" class="field_tbl">
                    <tbody>
                        <tr>
                            <td width="2%" class="lcell" rowspan="8"><@s.text name="view.fault.label.four" /></td>
                            <td width="98%" colspan="4">
                                <@s.text name="view.fault.label.msg1" />
                                <#if fault.reset?? && fault.reset == 1>
                                    <input type="radio" id="reset" name="fault.reset" value="1" checked disabled/><@s.text name="view.fault.label.msg1.can" />
                                <#else>
                                    <input type="radio" id="reset" name="fault.reset" value="1" disabled/><@s.text name="view.fault.label.msg1.can" />
                                </#if>
                                <#if fault.reset?? && fault.reset == 0>
                                    <input type="radio" id="reset" name="fault.reset" value="0" checked disabled/><@s.text name="view.fault.label.msg1.cannot" />
                                <#else>
                                    <input type="radio" id="reset" name="fault.reset" value="0" disabled/><@s.text name="view.fault.label.msg1.cannot" />
                                </#if>
                                <@s.text name="view.fault.label.rightParentheses" />
                            </td>
                        </tr>
                        <tr>
                            <td width="98%" colspan="4">
                                <@s.text name="view.fault.label.msg2" />
                            </td>
                        </tr>
                        <tr>
                            <td width="98%" colspan="4">
                                <@s.text name="view.fault.label.gabio.msg3" />
                                <#if fault.cutPower?? && fault.cutPower == 1>
                                    <input type="radio" id="cutPower" name="fault.cutPower" value="1" checked disabled/><@s.text name="view.fault.label.msg3.can" />
                                <#else>
                                    <input type="radio" id="cutPower" name="fault.cutPower" value="1" disabled/><@s.text name="view.fault.label.msg3.can" />
                                </#if>
                                <#if fault.cutPower?? && fault.cutPower == 0>
                                    <input type="radio" id="cutPower" name="fault.cutPower" value="0" checked disabled/><@s.text name="view.fault.label.msg3.cannot" />
                                <#else>
                                    <input type="radio" id="cutPower" name="fault.cutPower" value="0" disabled/><@s.text name="view.fault.label.msg3.cannot" />
                                </#if>
                            </td>
                        </tr>
                        <tr>
                            <td width="98%" colspan="4">
                                <@s.text name="view.fault.label.msg4" />
                                <#if fault.rebootNormally?? && fault.rebootNormally == 1>
                                    <input type="radio" id="rebootNormally" name="fault.rebootNormally" value="1" checked disabled/><@s.text name="view.fault.label.msg4.normal" />
                                <#else>
                                    <input type="radio" id="rebootNormally" name="fault.rebootNormally" value="1" disabled/><@s.text name="view.fault.label.msg4.normal" />
                                </#if>
                                <#if fault.rebootNormally?? && fault.rebootNormally == 0>
                                    <input type="radio" id="rebootNormally" name="fault.rebootNormally" value="0" checked disabled/><@s.text name="view.fault.label.msg4.abnormal" />
                                <#else>
                                    <input type="radio" id="rebootNormally" name="fault.rebootNormally" value="0" disabled/><@s.text name="view.fault.label.msg4.abnormal" />
                                </#if>
                                <@s.text name="view.fault.label.rightParentheses" />
                            </td>
                        </tr>
                        <tr>
                            <td width="98%" colspan="4">
                                <@s.text name="view.fault.label.msg5" />
                            </td>
                        </tr>
                        <tr>
                            <td width="18%" class="lcell"><@s.text name="view.fault.label.gabio.errorNo" /></td>
                            <td width="80%" colspan="3">${fault.errorNo!""}</td>
                        </tr>
                        <tr>
                            <td width="18%" class="lcell"><@s.text name="view.fault.label.gabio.noRepon" /></td>
                            <td width="20%" colspan="3">${fault.noRepon!""}</td>
                        </tr>
                        <tr>
                            <td width="18%" class="lcell"><@s.text name="view.fault.label.gabio.otherDisplay" /></td>
                            <td width="20%" colspan="3">${fault.otherDisplay!""}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <table width="100%" align="center" class="field_tbl">
                <tbody>
                    <tr>
                        <td width="100%" class="lcell" colspan="4"><b><@s.text name="view.fault.label.five" /></b></td>
                    </tr>
                    <tr>
                        <td width="20%" class="lcell"><@s.text name="view.fault.label.traceInfomation" /></td>
                        <td width="30%">
                            <#if fault.traceInfomation?? && fault.traceInfomation == 1>
                                <input type="radio" id="traceInfomation" name="fault.traceInfomation" value="1" checked disabled/><@s.text name="view.fault.radio.label.has" />
                            <#else>
                                <input type="radio" id="traceInfomation" name="fault.traceInfomation" value="1" disabled/><@s.text name="view.fault.radio.label.has" />
                            </#if>
                            <#if fault.traceInfomation?? && fault.traceInfomation == 0>
                                <input type="radio" id="traceInfomation" name="fault.traceInfomation" value="0" checked disabled/><@s.text name="view.fault.radio.label.hasnot" />
                            <#else>
                                <input type="radio" id="traceInfomation" name="fault.traceInfomation" value="0" disabled/><@s.text name="view.fault.radio.label.hasnot" />
                            </#if>
                        </td>
                        <td width="20%" class="lcell"><@s.text name="view.fault.label.faultRecord" /></td>
                        <td width="30%">
                            <#if fault.faultRecord?? && fault.faultRecord == 1>
                                <input type="radio" id="faultRecord" name="fault.faultRecord" value="1" checked disabled/><@s.text name="view.fault.radio.label.has" />
                            <#else>
                                <input type="radio" id="faultRecord" name="fault.faultRecord" value="1" disabled/><@s.text name="view.fault.radio.label.has" />
                            </#if>
                            <#if fault.faultRecord?? && fault.faultRecord == 0>
                                <input type="radio" id="faultRecord" name="fault.faultRecord" value="0" checked disabled/><@s.text name="view.fault.radio.label.hasnot" />
                            <#else>
                                <input type="radio" id="faultRecord" name="fault.faultRecord" value="0" disabled/><@s.text name="view.fault.radio.label.hasnot" />
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" class="lcell"><@s.text name="view.fault.label.statistics" /></td>
                        <td width="30%">
                            <#if fault.statistics?? && fault.statistics == 1>
                                <input type="radio" id="statistics" name="fault.statistics" value="1" checked disabled/><@s.text name="view.fault.radio.label.has" />
                            <#else>
                                <input type="radio" id="statistics" name="fault.statistics" value="1" disabled/><@s.text name="view.fault.radio.label.has" />
                            </#if>
                            <#if fault.statistics?? && fault.statistics == 0>
                                <input type="radio" id="statistics" name="fault.statistics" value="0" checked disabled/><@s.text name="view.fault.radio.label.hasnot" />
                            <#else>
                                <input type="radio" id="statistics" name="fault.statistics" value="0" disabled/><@s.text name="view.fault.radio.label.hasnot" />
                            </#if>
                        </td>
                        <td width="20%" class="lcell"><@s.text name="view.fault.label.tradeLog" /></td>
                        <td width="30%">
                            <#if fault.tradeLog?? && fault.tradeLog == 1>
                                <input type="radio" id="tradeLog" name="fault.tradeLog" value="1" checked disabled/><@s.text name="view.fault.radio.label.has" />
                            <#else>
                                <input type="radio" id="tradeLog" name="fault.tradeLog" value="1" disabled/><@s.text name="view.fault.radio.label.has" />
                            </#if>
                            <#if fault.tradeLog?? && fault.tradeLog == 0>
                                <input type="radio" id="tradeLog" name="fault.tradeLog" value="0" checked disabled/><@s.text name="view.fault.radio.label.hasnot" />
                            <#else>
                                <input type="radio" id="tradeLog" name="fault.tradeLog" value="0" disabled/><@s.text name="view.fault.radio.label.hasnot" />
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" class="lcell"><@s.text name="view.fault.label.applicationVersion1" /></td>
                        <td width="80%" colspan="3">
                            <#if fault.applicationVersion1?? && fault.applicationVersion1 == 1>
                                <input type="radio" id="applicationVersion1" name="fault.applicationVersion1" value="1" checked disabled/><@s.text name="view.fault.radio.label.has" />
                            <#else>
                                <input type="radio" id="applicationVersion1" name="fault.applicationVersion1" value="1" disabled/><@s.text name="view.fault.radio.label.has" />
                            </#if>
                            <#if fault.applicationVersion1?? && fault.applicationVersion1 == 0>
                                <input type="radio" id="applicationVersion1" name="fault.applicationVersion1" value="0" checked disabled/><@s.text name="view.fault.radio.label.hasnot" />
                            <#else>
                                <input type="radio" id="applicationVersion1" name="fault.applicationVersion1" value="0" disabled/><@s.text name="view.fault.radio.label.hasnot" />
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" class="lcell"><@s.text name="view.fault.label.others" /></td>
                        <td width="80%" colspan="3">${fault.others!""}</td>
                    </tr>
                </tbody>
            </table>
            <table width="100%" align="center" class="field_tbl">
                <tbody>
                    <tr>
                        <td width="100%" class="lcell" colspan="4"><b><@s.text name="view.fault.label.six" /></b></td>
                    </tr>
                    <tr>
                        <td width="20%" class="lcell"><@s.text name="view.fault.label.applyDate" /></td>
                        <td width="30%">${fault.applyDate!""}</td>
                        <td width="20%" class="lcell"><@s.text name="view.fault.label.deliverDate" /></td>
                        <td width="30%">${fault.deliverDate!""}</td>
                    </tr>
                    <tr>
                        <td width="20%" class="lcell"><@s.text name="view.fault.label.replaceDate" /></td>
                        <td width="30%">${fault.replaceDate!""}</td>
                        <td width="20%" class="lcell"><@s.text name="view.fault.label.ihscReceiveDate" /></td>
                        <td width="30%">${fault.ihscReceiveDate!""}</td>
                    </tr>
                </tbody>
            </table>
            <#if (faultSparesList.size() > 0)>
                <table id="partUseTab" width="100%" align="center" class="field_tbl">
                    <tbody>
                        <tr>
                            <td width="100%" class="lcell" colspan="9">
                                <b><@s.text name="view.fault.label.seven" /></b>
                            </td>
                        </tr>
                        <tr>
                            <td width="4%" class="lcell"><@s.text name="view.fault.label.number" /></td>
                            <td width="12%" class="lcell"><@s.text name="view.fault.label.name" /></td>
                            <td width="12%" class="lcell"><@s.text name="view.fault.label.pictureNo" /></td>
                            <td width="12%" class="lcell"><@s.text name="view.fault.label.warehouse" /></td>
                            <td width="12%" class="lcell"><@s.text name="view.fault.label.no" /></td>
                            <td width="12%" class="lcell"><@s.text name="view.fault.label.applyDate" /></td>
                            <td width="12%" class="lcell"><@s.text name="view.fault.label.deliverDate" /></td>
                            <td width="12%" class="lcell"><@s.text name="view.fault.label.replaceDate" /></td>
                            <td width="12%" class="lcell"><@s.text name="view.fault.label.ihscReceiveDate" /></td>
                        </tr>
                        <#list faultSparesList as faultSpares>
                            <tr>
                                <td>${faultSpares_index + 1}</td>
                                <td>${faultSpares.name!""}</td>
                                <td>${faultSpares.pictureNo!""}</td>
                                <td>${faultSpares.warehouse!""}</td>
                                <td>${faultSpares.no!""}</td>
                                <td>${faultSpares.applyDate!""}</td>
                                <td>${faultSpares.deliverDate!""}</td>
                                <td>${faultSpares.replaceDate!""}</td>
                                <td>${faultSpares.receiveDate!""}</td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </#if>
        </div>
        <input type="hidden" name="fault.id" value="${fault.id!-1}"/>
        <input type="hidden" name="fault.agentId" value="${fault.agentId!-1}"/>
        <input type="hidden" name="fault.repairState" value="${fault.repairState!-1}"/>
        <input type="hidden" name="fault.faultPart" value="${fault.faultPart!-1}"/>
        <input type="hidden" name="fault.faultPartType" value="${fault.faultPartType!-1}"/>
        <input type="hidden" name="fault.isState" value="${fault.isState!""}"/>
        <input type="hidden" name="fault.supportType" value="${fault.supportType!-1}"/>
        <input type="hidden" name="fault.finishDate" value="${fault.finishDate!""}"/>
        <input type="hidden" name="fault.finishTime" value="${fault.finishTime!""}"/>
        <input type="hidden" name="fault.reason" value="${fault.reason!""}"/>
        <input type="hidden" name="fault.strategy" value="${fault.strategy!""}"/>
        <input type="hidden" name="fault.deleted" value="${fault.deleted!-1}"/>
        <input type="hidden" name="fault.exclusiveKey" value="${fault.exclusiveKey!-1}"/>
    </div>
</form>
</div>
</#escape>
</body>
</html>