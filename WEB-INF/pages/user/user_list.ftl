<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="user.listInfo" /></title>
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
       var objId=$(":radio[name=rad]:checked:eq(0)").attr("nid");
        $("#ObjId").val(objId);
        $("#exclusiveKey").val(exclusiveKey);
       changeAction(url);
   }
   
   
   $(document).ready(function(){
   
   $("#pageSizeSel").change(function(){
		$("#psize").val($(this).val());
		changeAction("BS007_10");
	});
	
	
	$("#toPageSel").change(function(){
		$("#currPage").val($(this).val());
		changeAction("BS007_10");
	});
 
    
    $("input[type=radio][name=rad]").click(function(){      
        $("#userId").val($(this).attr("nid"));
    });
   
	var radios = $("input[type=radio][name=rad]");
	var radioSize= radios.length;
	
	var btnDetailObj=$("#btnDetail");

	if(radioSize>0){
		btnDetailObj.removeAttr("disabled");
	    var firstRad=$(":radio[name=rad]:eq(0)")
	    firstRad.attr("checked",true);
	}
	else{
	    btnDetailObj.attr("disabled","disabled");
	} 
    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("input[type=radio][nid=" + ${user.id!-1}+"]").attr("checked", "checked");
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
	  <div  class="gridview"  style="margin:0px auto;" >
        <#assign pagebarAction="BS007_10">
          <#include "../components/pagebar.ftl">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>           
						 <table class="datalist" width="100%" cellspacing="0" cellpadding="0" >
							 <tr>
							       <th width="6%"></th>
								   <th width="10%"><label><@s.text name="user.name" /></label></th>
		                           <th width="10%"><label><@s.text name="user.fullName" /></label></th>
		                           <th width="24%"><label><@s.text name="user.email" /></label></th>
		                           <th width="20%"><label><@s.text name="user.department" /></label></th>
		                       <#if loginUser.hasPermission("BS006_10")> 
								   <th width="10%"><label><@s.text name="group" /></label></th>
							   </#if>
							   <#if loginUser.hasPermission("BS007_36")>    
								   <th width="10%"><label><@s.text name="user.locked" /></label></th>
							   </#if>
							   <#if loginUser.hasPermission("BS007_34")> 	   
								   <th width="10%"><label><@s.text name="user.deleted" /></label></th>
							   </#if>	   
							</tr>
							<#list userList as usr>
								<tr>
								    <td><input type="radio" nid="${usr.id!""}" name="rad" value="${usr.exclusiveKey!""}"/></td>
									<td><label>${usr.name!""}</label></td>
									<td><label>${usr.fullName!""}</label></td>
									<td><label><a href="mailto:${usr.email!""}">${usr.email!""}</a></label></td>
									<td><label>${usr.department!""}</label></td>
									<#if loginUser.hasPermission("BS006_10")>   
									   <td><label>${usr.groupName!""}</label></td>
									</#if>
									<#if loginUser.hasPermission("BS007_36")>   
										<#if usr.locked==1>
										   <td><label class="needed"><@s.text name="locked" /></label></td>
										<#else>
										   <td></td>
										</#if>
								   </#if>		
								   <#if loginUser.hasPermission("BS007_34")>   
										<#if usr.deleted==1>
					                       <td><label class="needed"><@s.text name="deleted" /></label></td>
					                    <#else>
					                       <td></td>
					                    </#if>
				                   </#if> 
								</tr>
						  </#list>
					  </table>
	                </td>
	              </tr>
	           </tbody>
	          </table> 					  
			  <div class="btn_row">
				  <#if loginUser.hasPermission("BS007_20")>
					<button  type="button" onClick="changeAction('BS007_20')" ><@s.text name="btn_add" /></button>
				  </#if>
				  <#if loginUser.hasPermission("BS007_12")> 	
					<button type="button" id="btnDetail" onClick="executeAction('BS007_12')"  ><@s.text name="btn_detail" /></button>
				 </#if>	
			 </div>
	         <form id="mdfForm" action="mdfPage" method="post">
	            <input type="hidden" id="ObjId" name="user.id"  />
	            <input type="hidden" id="exclusiveKey" name="user.exclusiveKey" />
	            <input type="hidden"  name="user.name" value="${user.name!""}" />
	            <input type="hidden"  name="user.familyName" value="${user.familyName!""}"  />
	            <input type="hidden"  name="user.givenName" value="${user.givenName!""}"  />
	            <input type="hidden"  name="user.companyID" value="${user.companyID!""}"  />
	            <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
	            <input type="hidden"  id="psize"   name="pageSize" value="${pageSize!0}" />
	         </form>			 
		 </div> 
		</div> 
     <#include "../footer.ftl"/>  		 
  </div>	
</#escape>     	
</body>
</html>