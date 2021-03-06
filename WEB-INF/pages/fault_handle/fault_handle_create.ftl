<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="fault_handle_create" /></title>
<#include "../common_header.ftl"/>

<script type="text/javascript">
    function changeAction(url)
    {
        var flag= confirm("<@s.text name="BSC00006" />");
        if(flag==true){
            var form=document.getElementById("addForm");
            form.action=url;
            form.submit();  
        }
    }
    $(document).ready(function(){
        $("#startDate").datepicker();
        $("#endDate").datepicker();
    });
    function textCounter(field,left) {
        var maxlimit = 400;
        if (field.value.length > maxlimit){
            field.value = field.value.substring(0, maxlimit);
        } 
        $("#" + left).text(maxlimit - field.value.length);
    }
</script>
<script type="text/javascript" src="js/jquery/erp_time.js" ></script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container">
<#include "../header.ftl"/>
  <div id="view" >
    <div class="fields">
    <form id="addForm" method="post">
        <div>
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>          
			            <table class="field_tbl" width="100%" align="center">
			              <tr>
			                <th colspan="4"><@s.text name="fault_handle_information" /></th>
			              </tr>
			              <tr>
			                <td class="lcell" width="20%"><label><@s.text name="faultHandle.supporterId" /></label></td>
			                <td width="80%" colspan="3">
			                    <select name="faultHandle.supporterId" style="width:400px">
			                        <@s.action name="user_drop" executeResult="true" ignoreContextParams="true">
			                            <@s.param name="selectedUser">${faultHandle.supporterId!""}</@s.param>
			                        </@s.action>
			                    </select>&nbsp;<label class="needed"><@s.text name="selectNeeded" /></label>
			                </td>
			              </tr>
			              <tr>
			                <td class="lcell" width="20%"><label><@s.text name="faultHandle.operateCompanyId" /></label></td>
			                <td width="80%" colspan="3">
			                    <select name="faultHandle.operateCompanyId" style="width:400px">
                                   <@s.action name="installCompany_drop" executeResult="true" ignoreContextParams="true">
                                        <@s.param name="selectedComId">${faultHandle.operateCompanyId!""}</@s.param>
                                   </@s.action>
			                    </select>&nbsp;<label class="needed"><@s.text name="selectNeeded" /></label>
			                </td>
			              </tr>
			              <tr>
			                <td class="lcell" width="20%"><label><@s.text name="faultHandle.operatorName" /></label></td>
			                <td width="80%" colspan="3">
			                    <input id="operatorName" type="text" name="faultHandle.operatorName" value="${faultHandle.operatorName!""}" maxLength="40" size="40"><br>
			                    <select id="userName" onchange="operatorName.value=userName.value" style="width:400px">
			                        <@s.action name="userName_drop" executeResult="true" ignoreContextParams="true">
			                            <@s.param name="selectedUserName">${faultHandle.operatorName!""}</@s.param>
			                        </@s.action>
			                    </select>&nbsp;<label class="needed"><@s.text name="selectNeeded" /></label>
			                </td>
			              </tr>
			              <tr>
			                <td class="lcell" width="20%"><label><@s.text name="faultHandle.handleDetail" /></label></td>
			                <td width="80%" colspan="3">
			                    <textarea id="handleDetail" name="faultHandle.handleDetail" rows="2" cols="50" style="overflow-y:auto" 
			                    onPropertyChange=textCounter(this,'detailLeft') oninput=textCounter(this,'detailLeft')>${faultHandle.handleDetail!""}</textarea>
			                        &nbsp;<label class="needed"><@s.text name="writeNeeded2" /></label>
			                        &nbsp;<label class="needed"><@s.text name="leftCount" /><span id="detailLeft">400</span></label>
			                </td>
			              </tr>
			              <tr>
			                <td class="lcell" width="20%"><label><@s.text name="faultHandle.handleType" /></label></td>
			                <td width="80%" colspan="3">
			                    <select name="faultHandle.handleType" style="width:400px">
			                        <@s.action name="faultHandleType_drop" executeResult="true" ignoreContextParams="true">
			                            <@s.param name="selectedFaultHandleType">${faultHandle.handleType!""}</@s.param>
			                        </@s.action>
			                    </select>&nbsp;<label class="needed"><@s.text name="selectNeeded" /></label>
			                </td>
			              </tr>
			              <tr>
			                <td class="lcell" width="20%"><label><@s.text name="faultHandle.startDate" /></label></td>
			                <td width="30%">
			                    <input id="startDate" type="text" name="faultHandle.startDate" value="${faultHandle.startDate!""}" maxLength="10" size="20">
			                        &nbsp;<label class="needed"><@s.text name="writeNeeded" /></label>
			                </td>
			                <td class="lcell" width="20%"><label><@s.text name="faultHandle.startTime" /></label></td>
			                <td width="30%">
			                    <input id="startTime" type="text" name="faultHandle.startTime" value="${faultHandle.startTime!""}" maxLength="5" size="20">
			                    <button type="button"  onclick="openTimeWin('startTime')" style="margin:0 5px;width:60px;height:24px;line-height:24px;" ><@s.text name="btn_set" /></button>
			                </td>
			              </tr>
			              <tr>
			                <td class="lcell" width="20%"><label><@s.text name="faultHandle.endDate" /></label></td>
			                <td width="30%">
			                    <input id="endDate" type="text" name="faultHandle.endDate" value="${faultHandle.endDate!""}" maxLength="10" size="20">
			                        &nbsp;<label class="needed"><@s.text name="writeNeeded" /></label>
			                </td>
			                <td class="lcell" width="20%"><label><@s.text name="faultHandle.endTime" /></label></td>
			                <td width="30%">
			                    <input id="endTime" type="text" name="faultHandle.endTime" value="${faultHandle.endTime!""}" maxLength="5" size="20">
			                    <button type="button"  onclick="openTimeWin('endTime')" style="margin:0 5px;width:60px;height:24px;line-height:24px;" ><@s.text name="btn_set" /></button>
			                </td>
			              </tr>
			              <tr>
			                <td class="lcell" width="20%" ><label><@s.text name="faultHandle.status" /></label></td>
			                <td width="80%" colspan="3">
			                    <textarea name="faultHandle.status" rows="2" cols="50" style="overflow-y:auto" 
			                    onPropertyChange=textCounter(this,'resultLeft') oninput=textCounter(this,'resultLeft')>${faultHandle.status!""}</textarea>
			                        &nbsp;<label class="needed"><@s.text name="writeNeeded2" /></label>
			                        &nbsp;<label class="needed"><@s.text name="leftCount" /><span id="resultLeft">400</span></label>
			                </td>
			              </tr>
			              <tr>
			                <td class="lcell" width="20%" ><label><@s.text name="faultHandle.note" /></label></td>
			                <td width="80%" colspan="3">
			                    <textarea name="faultHandle.note" rows="2" cols="50" style="overflow-y:auto" 
			                    onPropertyChange=textCounter(this,'noteLeft') oninput=textCounter(this,'noteLeft')>${faultHandle.note!""}</textarea>
			                        &nbsp;<label class="needed"><@s.text name="writeNeeded3" /></label>
			                        &nbsp;<label class="needed"><@s.text name="leftCount" /><span id="noteLeft">400</span></label>
			                </td>
			              </tr>
			            </table>
	               </td>
	             </tr>
	          </tbody>
	       </table>  			            
        </div>
        <input type="hidden" name="fault.id" value="${fault.id!""}">
        <input type="hidden" name="faultHandle.faultId" value="${fault.id!""}">
        <input type="hidden" id="timeName" />
      </form>
      <div class="btn_row">
      <#if loginUser.hasPermission("FA002_21")>
            <button type="button" onClick="changeAction('FA002_21')" ><@s.text name="btn_add" /></button>
      </#if>
      </div>
    </div>
  </div>
    <#include "../components/popTimeWin.ftl"/>
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>