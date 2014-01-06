<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="channelDetail.listInfo" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
        
   function changeAction(url,type)
   {
      var form=document.getElementById("mdfForm");
      var currentPage=document.getElementById("currPage");
     if(type!='undefined')
     {
          switch(type)
          {
            case "first":
                 currentPage.value=1;
                 break;
            case "pre":
                 currentPage.value=${currPage-1};
                 break;
            case "next":
                 currentPage.value=${currPage+1};
                 break;
            case "last":
                 currentPage.value=${totalPages};
          }
    }
    
      form.action=url;
      form.submit();
      
   } 
   
   
   function executeAction(url){
       var exclusiveKey=$(":radio[name=rad]:checked:eq(0)").val();
       var objId=$(":radio[name=rad]:checked:eq(0)").attr("id");
        $("#ObjId").val(objId);
        $("#exclusiveKey").val(exclusiveKey);
       changeAction(url);
   }
   
   function deleteData(url){
       var flag= confirm("<@s.text name="BSC00003" />");
       if(flag==true){
          executeAction(url);
        }
   }
   
   $(document).ready(function(){
   
   $("#pageSizeSel").change(function(){
        $("#psize").val($(this).val());
        changeAction("BS004_10");
    });
    
    
    $("#toPageSel").change(function(){
        $("#currPage").val($(this).val());
        changeAction("BS004_10");
    });
   
    var radios = $("input[type=radio][name=rad]");
    var radioSize= radios.length;
    
    var btnDelObj=$("#btnDel");
    var btnMdfObj=$("#btnMdf")
    if(radioSize>0){
        btnDelObj.removeAttr("disabled");
        btnMdfObj.removeAttr("disabled");
        var firstRad=$(":radio[name=rad]:eq(0)")
        firstRad.attr("checked",true);
    }
    else{
        btnDelObj.attr("disabled","disabled");
        btnMdfObj.attr("disabled","disabled");
    } 
    
    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("#" + ${city.id!-1}).attr("checked", "checked");
    </#if>
    
   });
   
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/> 
 <div id="container">         
    <#include "../header.ftl"/>
    <div id="view">
     <div  class="gridview">
         <#assign pagebarAction="BS005_10">
         <#include "../components/pagebar.ftl">
         <div>
	         <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	            <tbody>
	                <tr>
	                  <td>
				          <table class="datalist" width="100%" cellspacing="0" cellpadding="0" >
				            <tr>            
								<th width="5%"><input type="checkbox" id="selectAll" /></th>
				                <th width="20%"><label><@s.text name="channelDetail.name" /></label></th>   
				                <th width="5%"><label><@s.text name="channelDetail.sname" /></label></th>
				                <th width="5%"><label><@s.text name="channelDetail.operator" /></label></th>
				                <th width="5%"><label><@s.text name="channelDetail.value" /></label></th> 
				                <th width="5%"><label><@s.text name="channelDetail.province" /></label></th>
				                <th width="5%"><label><@s.text name="channelDetail.priority" /></label></th>
				                <th width="5%"><label><@s.text name="channelDetail.discount" /></label></th>
				                <th width="5%"><label><@s.text name="channelDetail.status" /></label></th> 
				                <th width="20%"><label><@s.text name="channelDetail.comments" /></label></th> 
				            </tr>
				            <#list channelDetailList as channelDetail>
				            <tr>
				                <#if affirmChkList?? && affirmChkList.contains("${channelDetail.id}")>
									<td><input type="checkbox" name="affirmChkList" value="${channelDetail.id!""}" checked></td>
								<#else>
									<td><input type="checkbox" name="affirmChkList" value="${channelDetail.id!""}" ></td>
								</#if>
				                <td><label>${channelDetail.name!""}</label></td>
				                <td><label>${channelDetail.sname!""}</label></td>
				                <td><label>${channelDetail.operatorName!""}</label></td>
				                <td><label>${channelDetail.value!""}</label></td>
				                <td><label>${channelDetail.province!""}</label></td>
				                <td><label>${channelDetail.priority!""}</label></td>
				                <td><label>${channelDetail.discount?if_exists?string.number}</label></td>
				                <td><label>${channelDetail.statusName!""}</label></td>
				                <td><label>${channelDetail.comments!""}</label></td>
				            </tr>
				           </#list>
				        </table>
	                 </td>
	               </tr>
	            </tbody>
	         </table>
         </div>    			        
         <div class="btn_row">
	           <#if loginUser.hasPermission("BS005_20")>
	             <button type="button" onClick="changeAction('BS005_20')" ><@s.text name="btn_add" /></button>
	           </#if> 
	          <#if loginUser.hasPermission("BS004_30")>
	            <button type="button" id="btnMdf" onClick="executeAction('BS004_30')" ><@s.text name="btn_modify" /></button>
	          </#if> 
	          <#if loginUser.hasPermission("BS004_40")>
	            <button type="button" id="btnDel" onClick="deleteData('BS004_40')"  ><@s.text name="btn_delete_forever"  /></button>
	          </#if>   
        </div>
        <form id="mdfForm" action="BS004_20" method="post">
	        <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
	        <input type="hidden"  id="psize"   name="pageSize" value="${pageSize!0}" />
	        <input type="hidden"  id="channelId"   name="channel.id" value="${channel.id!""}" />
	        <input type="hidden"  id="preAction"   name="preAction" value="BS004_20" />
        </form>
     </div> 
    </div>
  </div>       
 </#escape>
</body>
</html>