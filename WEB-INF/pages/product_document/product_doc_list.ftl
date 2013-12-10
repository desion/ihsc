<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<title><@s.text name="document.listInfo" /></title>
<head>
<#include "../common_header.ftl"/>
<script type="text/javascript">
        
   function changeAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
   	  form.submit();
   	  
   } 

   function deleteAction(url){
       var flag= confirm("<@s.text name="BSC00003" />");
       if(flag==true){
          executeAction(url);
        }
   }
   
   function executeAction(url){
       var documentId=$(":radio[name=rad]:checked:eq(0)").attr("id");
       var documentName=$(":radio[name=rad]:checked:eq(0)").val();
       $("#fid").val(documentId);
       $("#fname").val(documentName);
       changeAction(url);
   }
   
   $(document).ready(function(){
	var radios = $("input[type=radio][name=rad]");
	var radioSize= radios.length;

	var btnDetail=$("#btnDetail");
	var btnDownload=$("#btnDownload");
	var btnDelete=$("#btnDelete");
	if(radioSize>0){
		btnDetail.removeAttr("disabled");
		btnDownload.removeAttr("disabled");
		btnDelete.removeAttr("disabled");
	    var firstRad=$(":radio[name=rad]:eq(0)")
	    firstRad.attr("checked",true);
	}
	else{
	    btnDetail.attr("disabled","disabled");
	    btnDownload.attr("disabled","disabled");
	    btnDelete.attr("disabled","disabled");
	}
	
   $(":button[name='btnDownload']").each(function(){
      $(this).click(function(){
          $("#fid").val($(this).attr("did"));
          $("#fname").val($(this).attr("dname"));
          changeAction('PR006_60');
      });
   });
           
    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("#" + ${document.id!-1}).attr("checked", "checked");
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
          <div class="gridview">
	         <div>
		         <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		           <tbody>
		              <tr>
		                <td>         
					         <table class="datalist" width="100%" cellspacing="0" cellpadding="0">
					            <tr>			
								    <th width="6%"><label/></th>
									<th width="20%"><label><@s.text name="document.name" /></label></th>
									<th width="20%"><label><@s.text name="document.title" /></label></th>
									<th width="29%"><label><@s.text name="document.summary" /></label></th>
					                <th width="11%"><label><@s.text name="document.publishDate" /></label></th>
					                <th width="14%"><label><@s.text name="document.type" /></label></th>
								</tr>
								<#list documentList as document>
								  <tr>
								    <td><input type="radio" id="${document.id!""}" name="rad" value="${document.name!""}" /></td>
									<td><label>${document.name!""}</label></td>
									<td><label>${document.title!""}</label></td>
									<td><label>${document.summary!""}</label></td>
									<td><label>${document.publishDate!""}</label></td>
									<td><label>${document.docTypeName!""}</label></td>           
								  </tr>
							     </#list>
							  </table>
		                   </td>
		                </tr>
		            </tbody>
		          </table> 					  
			</div>
		    <div class="btn_row" >
		      <#if loginUser.hasPermission("PR006_20")>
	            <button type="button" id="btnAdd" onClick="changeAction('PR006_20')" ><@s.text name="btn_add" /></button>
	          </#if>
	          
	          <#if loginUser.hasPermission("PR006_11")>
	            <button type="button" id="btnDetail" onClick="executeAction('PR006_11')" ><@s.text name="btn_detail" /></button>
	          </#if>
	          
	          <#if loginUser.hasPermission("PR006_60")>
	            <button type="button" id="btnDownload" onClick="executeAction('PR006_60')" ><@s.text name="btn_download" /></button>
	          </#if>
	          
	          <#if loginUser.hasPermission("PR006_41")>
	            <button type="button" id="btnDelete" onClick="deleteAction('PR006_41')" ><@s.text name="btn_delete" /></button>
	         </#if>
	         
	           <#if loginUser.hasPermission("PR006_70")>
	            <button type="button"  onClick="executeAction('PR006_70')" ><@s.text name="btn_search" /></button>
	         </#if>
			</div>
			<form id="mdfForm" action="PR006_11" method="post">
				<input type="hidden" id="fid" name="document.id" value="${document.id!-1}" />
		        <input type="hidden" id="fname" name="document.name" value="${document.name!""}" />
		    </form>
	     </div> 
       </div>		 
     <#include "../footer.ftl"/>   
  </div>
</#escape>  	  
</body>
</html>