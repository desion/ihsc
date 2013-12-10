<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="fault_handle_list" /></title>
<#include "../common_header.ftl"/>

<script type="text/javascript">
    function changeAction(url)
    {
        var form=document.getElementById("listForm");
        form.action=url;
        form.submit();
    }
    function deleteAction(url){
        var flag= confirm("<@s.text name="BSC00003"/>");
        if(flag==true){
            executeAction(url);
        }
    }
    function executeAction(url){
        var objId=$(":radio[name=rad]:checked").attr("id");
        $("#selectId").val(objId);
        changeAction(url);
    }

    $(document).ready(function(){
        var radios = $("input[type=radio][name=rad]");
        var radioSize= radios.length;
    
        var btnDetailObj=$("#btnDetail");
        var btnDeleteObj=$("#btnDelete");
        var btnbtnModifyObj=$("#btnModify");

        if(radioSize>0){
            btnDetailObj.removeAttr("disabled");
            btnDeleteObj.removeAttr("disabled");
            btnbtnModifyObj.removeAttr("disabled");
            var firstRad=$(":radio[name=rad]:eq(0)");
            firstRad.attr("checked",true);
        }
        else{
            btnDetailObj.attr("disabled","disabled");
            btnDeleteObj.attr("disabled","disabled");
            btnbtnModifyObj.attr("disabled","disabled");
        }
    });
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container">
<#include "../header.ftl"/>
  <div id="view" >
    <div class="gridview">
        <div>
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2" >
           <tbody>
              <tr>
                <td>           
		            <table class="field_tbl" style="table-layout:fixed" width="80%" align="center" >
		            <tr>
		                <th width="5%">&nbsp;</th>
		                <th width="20%"><@s.text name="handle_date" /></th>
		                <th width="20%"><@s.text name="handle_operator" /></th>
		                <th colspan="5" width="55%"><@s.text name="handle_detail" /></th>
		            </tr>
		            <#list faultHandleList as list>
		            <tr>
		                <td rowspan="3" width="5%"><input type="radio" id="${list.id!""}" name="rad"></td>
		                <td rowspan="3" width="20%"><label>${list.startDate!""}<br>${list.startTime!""}</label></td>
		                <td rowspan="3" width="20%"><label>${list.operateCompanyName!""}</label></td>
		                <td width="10%"><label><@s.text name="handle" /></label></td>
		                <td width="45%" colspan="4" style="word-break:break-all;word-wrap:break-word">${list.handleDetail!""}</td>
		            </tr>
		            <tr>
		                <td width="10%"><label><@s.text name="result" /></label></td>
		                <td width="90%" colspan="4" style="word-break:break-all;word-wrap:break-word">${list.status!""}</td>
		            </tr>
		            <tr>
		                <td width="10%"><label><@s.text name="note" /></label></td>
		                <td width="90%" colspan="4" style="word-break:break-all;word-wrap:break-word">${list.note!""}</td>
		            </tr>
		            </#list>
		           </table>
                </td>
              </tr>
           </tbody>
          </table>  		            
        </div>
        <form id="listForm" method="post">
            <input type="hidden" id="selectId" name="faultHandle.id">
        </form>
        <div class="btn_row">
        <#if loginUser.hasPermission("FA002_11")>
            <button id="btnDetail" type="button" onClick="executeAction('FA002_11')" ><@s.text name="btn_detail" /></button>
        </#if>
        <#if loginUser.hasPermission("FA002_30")>    
            <button id="btnModify" type="button" onClick="executeAction('FA002_30')" ><@s.text name="btn_modify" /></button>
        </#if>
        <#if loginUser.hasPermission("FA002_40")>
            <button id="btnDelete" type="button" onClick="deleteAction('FA002_40')" ><@s.text name="btn_delete_forever" /></button>
        </#if>
        </div>        
    </div>
  </div>
<#include "../footer.ftl"/>   
</div>
</#escape>
</body>
</html>