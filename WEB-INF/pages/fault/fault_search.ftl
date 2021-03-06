<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="FA001_71_title" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
    function loadAction(url)
    {
       var form=document.getElementById("searchForm");
       form.action=url;
       form.submit();
    }

    $(document).ready(function(){
        $("#occurDate").datepicker();
        $("#reportDate").datepicker();
        $("#finishDate").datepicker();
        $("#reset").click(function() {
            $('input[type="text"]').val("");
            $("select").val("");
            $('input[type="radio"]').attr("checked",'');
        });
        
        $("#topProCateDrop").change(function(){
           var faultMachineType = $(this).val();
           faultPartChange(faultMachineType);
        });
        
    });
    
    
  function faultPartChange(faultMachineType){
       var params = "";
       if(faultMachineType!=""){
        params =  "faultMachineType="+faultMachineType;
       }
       $.ajax({
              url: 'faultPart_drop',
              type: 'GET',
              data: params,
              dataType: 'html',
              cache:false,
              timeout: 1000,
              success: function(data, textStatus){                 
                $("#faultPartDrop").html(data);
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
    <div id="view" >
        <div class="fields">
            <form id="searchForm" method="post">
		        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		           <tbody>
		              <tr>
		                <td>  
					        <table width="70%" align="center" class="field_tbl" >
			                    <tbody>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.managementId" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="fault.managementId" value="${fault.managementId!""}" maxLength="80" size="80"/ >
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell" ><label><@s.text name="fault.faultType" /></label></td>
			                            <td width="80%">
			                                <select name="fault.faultType" >
			                                    <@s.action name="faultType_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedFaultType">${fault.faultType!""}</@s.param>
			                                    </@s.action>
			                                </select>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.appearance" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="fault.appearance" value="${fault.appearance!""}" maxLength="80" size="80"/ >
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.reason" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="fault.reason" value="${fault.reason!""}" maxLength="80" size="80"/ >
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.strategy" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="fault.strategy" value="${fault.strategy!""}" maxLength="80" size="80"/ >
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.errorCode" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="fault.errorCode" value="${fault.errorCode!""}" maxLength="80" size="80"/ >
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
					         <table width="70%" align="center" class="field_tbl" >
			                    <tbody>
			                       <tr>
                                       <td width="20%" class="lcell" ><label><@s.text name="fault.faultMachineType" /></label></td>
                                       <td width="80%">
                                           <select name="fault.faultMachineType" id="topProCateDrop" >
                                               <@s.action name="topProductCategory_drop" executeResult="true" ignoreContextParams="true">
                                                    <@s.param name="selectedProductCategoryName">${fault.faultMachineType!""}</@s.param>
                                               </@s.action>
                                           </select>
                                       </td>
                                   </tr>
			                       <tr>
                                        <td width="20%" class="lcell" ><label><@s.text name="fault.faultPart" /></label></td>
                                        <td width="80%">
                                            <select name="fault.faultPart" id="faultPartDrop">
                                                <@s.action name="faultPart_drop" executeResult="true" ignoreContextParams="true">
                                                    <@s.param name="selectedFaultPart">${fault.faultPart!""}</@s.param>
                                                </@s.action>
                                            </select>
                                        </td>
                                    </tr>
			                        <tr>
			                            <td width="20%" class="lcell" ><label><@s.text name="fault.faultPartType" /></label></td>
			                            <td width="80%">
			                                <select name="fault.faultPartType" >
			                                    <@s.action name="faultPartType_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedFaultPartType">${fault.faultPartType!""}</@s.param>
			                                    </@s.action>
			                                </select>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell" ><label><@s.text name="fault.isState" /></label></td>
			                            <td width="80%">
			                                <select name="fault.isState" >
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
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell" ><label><@s.text name="fault.supportType" /></label></td>
			                            <td width="80%">
			                                <select name="fault.supportType" >
			                                    <@s.action name="supportType_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedSupportType">${fault.supportType!""}</@s.param>
			                                    </@s.action>
			                                </select>
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
			                <table width="70%" align="center" class="field_tbl" >
			                    <tbody>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.occurDate" /></label></td>
			                            <td width="80%">
			                                <input id="occurDate" type="text" name="fault.occurDate" value="${fault.occurDate!""}" maxLength="10" />
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.reportDate" /></label></td>
			                            <td width="80%">
			                                <input id="reportDate" type="text" name="fault.reportDate" value="${fault.reportDate!""}" maxLength="10" />
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.finishDate" /></label></td>
			                            <td width="80%">
			                                <input id="finishDate" type="text" name="fault.finishDate" value="${fault.finishDate!""}" maxLength="10" />
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
					         <table width="70%" align="center" class="field_tbl" >
			                    <tbody>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.operation" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="fault.operation" value="${fault.operation!""}" maxLength="80" size="80"/ >
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.repairCompanyId" /></label></td>
			                            <td width="80%">
			                                <select name="fault.repairCompanyId" >
			                                    <@s.action name="installCompany_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedComId">${fault.repairCompanyId!"-1"}</@s.param>
			                                    </@s.action>
			                                </select>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell" ><label><@s.text name="fault.operatorName" /></label></td>
			                            <td width="80%">
			                                <input type="text" id="operatorName" name="fault.operatorName" value="${fault.operatorName!""}" maxLength="40" size="80" / >
			                                <select id="userName" onchange="operatorName.value=userName.value">
			                                    <@s.action name="userName_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedUserName">${fault.operatorName!""}</@s.param>
			                                    </@s.action>
			                                </select>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell" ><label><@s.text name="fault.supporterId" /></label></td>
			                            <td width="80%">
			                                <select name="fault.supporterId" >
			                                    <@s.action name="user_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedUser">${fault.supporterId!""}</@s.param>
			                                    </@s.action>
			                                </select>
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
			                <table width="70%" align="center" class="field_tbl" >
			                    <tbody>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.handleDetail" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="fault.handleDetail" value="${fault.handleDetail!""}" maxLength="80" size="80"/ >
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.result" /></label></td>
			                            <td width="80%">
			                                <select name="fault.resultId">
			                                    <@s.action name="faultStatus_drop" executeResult="true" ignoreContextParams="true">
			                                        <@s.param name="selectedFaultStatusId">${fault.resultId!""}</@s.param>
			                                    </@s.action>
			                                </select>
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.note" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="fault.note" value="${fault.note!""}" maxLength="80" size="80"/ >
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="fault.finishedOrNot" /></label></td>
			                            <td width="80%">
			                                <#if fault.repairState?? && fault.repairState == 1>
			                                    <input type="radio" name="fault.repairState" value="1" checked/><@s.text name="fault.finished" />&nbsp;
			                                <#else>
			                                    <input type="radio" name="fault.repairState" value="1" /><@s.text name="fault.finished" />&nbsp;
			                                </#if>
			                                <#if fault.repairState?? && fault.repairState == 0>
			                                    <input type="radio" name="fault.repairState" value="0" checked/><@s.text name="fault.notFinished" />
			                                <#else>
			                                    <input type="radio" name="fault.repairState" value="0" /><@s.text name="fault.notFinished" />
			                                </#if>
			                            </td>
			                        </tr>
			                    </tbody>
			                </table>
		                </td>
		              </tr>
		           </tbody>
		        </table>                  
              </form>
              <div class="btn_row">
                 <button type="button" id="reset" ><@s.text name="btn_reset" /></button>
                 <#if loginUser.hasPermission("FA001_13")>  
                    <button type="button" onClick="loadAction('FA001_13')" ><@s.text name="btn_search" /></button>
                 </#if>
                 <#if loginUser.hasPermission("FA001_60")>    
                    <button type="button" onClick="loadAction('FA001_60')" ><@s.text name="btn_export" /></button>
                 </#if>   
             </div>

        </div>
    </div>
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>