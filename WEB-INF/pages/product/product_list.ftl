<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="list.product.label.title" /></title>
<#include "../common_header.ftl"/>

<script type="text/javascript">
$(function() {
    $( "#dialog" ).dialog({
    	autoOpen: false,
    	resizable: false,
    	modal: true,
    	height: 200,
      	width: 400
    });
    $( "#create-user" )
      .click(function() {
        $( "#dialog" ).dialog( "open" );
      });
  });
 
  function showDialog(pindex,pid){
	 $("#dialog").dialog("open");
	 var exclusiveKey = $("#"+pid).val();
	 $("#dialog_pid").val(pid);
	 $("#dialog_pid_label").text(pid);
	 $("#dialog_price").val($("#price_" + pindex).text());
	 $("#dialog_status").val($("#status_" + pindex).val());
	 $("#dialog_exclusiveKey").val(exclusiveKey);
	 $("#dialog_pindex").val(pindex);
	 alert($("#dialog_pindex").val());
 	 return true;
  }
    function dtlAction() {
        $("#checkedProductId").val($(":radio[name=rad]:checked:eq(0)").attr("id"));
        $("#pId").val($(":radio[name=rad]:checked:eq(0)").attr("id"));
        $("#pExclusiveKey").val($(":radio[name=rad]:checked:eq(0)").val());
        var form=document.getElementById("mdfForm");
        form.action="PR002_11?actionForward=PR002_10";
        form.submit();
         
    }

    function changeAction(url) {
        $("#checkedProductId").val($(":radio[name=rad]:checked:eq(0)").attr("id"));
        var form=document.getElementById("mdfForm");
        form.action="url";
        form.submit();
         
    }
    
    function changeDialogAction(url) {
        $("#checkedProductId").val($(":radio[name=rad]:checked:eq(0)").attr("id"));
        $("#pPrice").val($("#dialog_price").val());
        $("#pStatus").val($("#dialog_status").val());
        var form=document.getElementById("actionForm");
        form.action="url";
        form.submit();
    }
    
    function submitPriceAction(url,pid,exclukey,value) {
        $("#pId").val(pid);
        $("#pExclusiveKey").val(exclukey);
        $("#pPrice").val(value);
        var form=document.getElementById("mdfForm");
        form.action="url";
        form.submit();
         
    }
    
    function changeSearchAction(url)
   {
      var form=document.getElementById("actionForm");
      form.action=url;
      form.submit();
   }
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

 $(document).ready(function(){
 	
       $("#btnAdddialog").click(function(){
       	   var price = $("#dialog_price").val();
	 	   var status = $("#dialog_status").val();
	 	   var excluskey= $("#dialog_exclusiveKey").val();
	 	   var dereason= $("#dialog_delistreason").val();
	 	   var pid = $("#dialog_pid").val();
	 	   var pindex = $("#dialog_pindex").val();
           var params = "product.id="+pid+"&product.status="+status
              +"&product.price="+price+"&product.exclusiveKey="+excluskey
              + "&product.delistreason="+dereason+"&product.index="+pindex;
              alert(params);
		   $.ajax({
			  url: 'PR002_31',
			  type: 'POST',
			  data: params,
			  dataType: 'text',
			  timeout: 1000,
			  cache:false,
			  success: function(data, textStatus){
			  	$("#out").html(data);
			  	var index = $(data).find("index").text();
			  	var pid = $(data).find("id").text();
			  	var price = $(data).find("price").text();
			  	var status = $(data).find("status").text();
			  	alert(price);
	 			$("#price_" + index).text(price)
	 			$("#statusName_" + index).text(status)
			  }
			});
       
       });
     
    
    $("#pageSizeSel").change(function(){
		$("#psize").val($(this).val());
		changeAction("PR002_10");
	});
	
	
	$("#toPageSel").change(function(){
		$("#currPage").val($(this).val());
		changeAction("PR002_10");
	});

    $("tr").mouseover(function(){  
    $(this).addClass("over");}).mouseout(function(){ 
    $(this).removeClass("over");})
    //set the table style
    
    var radios = $("input[type=radio][name=rad]");
    var radioSize= radios.length;

    var btnDtlObj=$("#btnDtl");
    var btnMdfObj=$("#btnMdf");
    var btnDelObj=$("#btnDel");
    var btnDelFObj=$("#btnDelF");
    if (radioSize>0) {
        btnDelObj.removeAttr("disabled");
        btnDelFObj.removeAttr("disabled");
        btnMdfObj.removeAttr("disabled");
        var firstRad=$(":radio[name=rad]:eq(0)");
        firstRad.attr("checked",true);
        $("#pId").val(firstRad.attr("id"));
        $("#pExclusiveKey").val(firstRad.val());
    } else {
        btnDtlObj.attr("disabled","disabled");
        btnDelObj.attr("disabled","disabled");
        btnDelFObj.attr("disabled","disabled");
        btnMdfObj.attr("disabled","disabled");
    }

    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("#" + ${checkedProductId!-1}).attr("checked", "checked");
    </#if>

    });
	
</script>

</head>
<#include "../components/error_reference.ftl"/>
<body>
<#escape x as x?html>
<div id="container">
	<#include "../header.ftl"/>
    <div id="view">
        <div class="fields" cellspacing="0" cellpadding="0">
        	<form id="actionForm" action="PR002_10" method="post">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>		  
						 <table width="60%" class="field_tbl" align="center">
							<tr>
							   <td width="14%" class="lcell"><label><@s.text name="product.value" /></label></td>
							   <td width="11%"><input type="text" name="product.value" value="${product.value!""}" size="10px" maxLength="3" /></td>
							   <td width="14%" class="lcell"><label><@s.text name="product.status" /></label></td>
			                   <td width="11%">
									<select name="product.status" style="width:150px;"  >
			                        		<#include "../components/productStatusDrop.ftl">
			                      	</select>
								</td>
								<td width="14%" class="lcell"><label><@s.text name="product.id" /></label></td>
			                   <td width="11%"><input type="text" name="product.id" value="${product.id!""}" size="10px" maxLength="10" /></td>
							   <td width="14%" class="lcell"><label><@s.text name="product.zone"  /></label></td>
			                   <td width="11%">
			                      <select name="product.zone" >
			                       <@s.action name="province_only_drop" executeResult="true" ignoreContextParams="true">
			                          <@s.param name="selectedProvinceId">${product.zone!"-1"}</@s.param>
			                       </@s.action>
			                       </select>
			                    </td>
							</tr>
						  </table>
		               </td>
		            </tr>
		         </tbody>
		      </table>  						  
		   </form>
		   <div class="btn_row">
		   	  <#if loginUser.hasPermission("PR002_20")>
                 <button type="button" id="btnAdd" onClick="changeDialogAction('PR002_31')" ><@s.text name="btn_add" /></button>
              </#if>
              <#if loginUser.hasPermission("PR002_10")>
                 <button type="button" onClick="changeSearchAction('PR002_10')"><@s.text name="btn_search" /></button>
              </#if> 
           </div>	            
        </div>
     <form id="mdfForm" action="PR002_10" method="post">
        <div class="gridview">
        	<#assign pagebarAction="PR002_10">
            <#include "../components/pagebar.ftl">
            <div>
	           <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	               <tbody>
	                  <tr>
	                    <td>             
			                <table class="datalist" width="100%" cellspacing="0" cellpadding="0">
			                    <tr>
			                        <th width="5%"><@s.text name="radio_label" /></th>
			                        <th><@s.text name="list.product.table.id" /></th>
			                        <th><@s.text name="list.product.table.title" /></th>
			                        <th><@s.text name="list.product.table.zone" /></th>
			                        <th><@s.text name="list.product.table.operator" /></th>
			                        <th><@s.text name="list.product.table.value" /></th>
			                        <th><@s.text name="list.product.table.price" /></th>
			                        <th><@s.text name="list.product.table.status" /></th>
			                        <th><@s.text name="list.product.table.opuser" /></th>
			                        <th><@s.text name="list.product.table.optime" /></th>
			                        <th><@s.text name="list.product.table.delistreason" /></th>
			                    </tr>
			                    <#list productList as pList>
			                    <tr>
			                        <td><input type="radio" id="${pList.id}" name="rad" value="${pList.exclusiveKey}"  /></td>
			                        <td>${pList.id!""}</td>
			                        <td>${pList.title!""}</td>
			                        <td><label id="zoneName_${pList_index}">${pList.zoneName!""}</label></td>
			                        <td><label id="operatorName_${pList_index}">${pList.operatorName!""}</label></td>
			                        <td><label id="value_${pList_index}">${pList.value!""}</label></td>
			                        <td><button type="button" id="price_${pList_index}" name="btnPrice" onclick="return showDialog(${pList_index},${pList.id})">${pList.price?if_exists?string.number}</button></td>
			                        <td>
							             <button type="button" id="statusName_${pList_index}" onClick="changeAction('PR002_31')" >${pList.statusName!""}</button>
							        	 <input type="hidden" id = "status_${pList_index}" value= "${pList.status!""}"></input>
							        </td>
			                        <td><label id="opUserName_${pList_index}">${pList.opUserName!""}</label></td>
			                        <td><label id="operateTime_${pList_index}">${pList.operateTime!""}</label></td>
			                        <td><label id="delistReason_${pList_index}">${pList.delistReason!""}</label></td>
			                    </tr>
			                    </#list>
			                </table>
	                     </td>
	                  </tr>
	               </tbody>
	            </table>  			                
            </div>
        </div>
        <input type="hidden" id="pExclusiveKey" name="product.exclusiveKey" />
        <input type="hidden" id="checkedProductId" name="checkedProductId" />
        <input type="hidden" id="pPrice" name="product.price" />
        <input type="hidden" id="pStatus" name="product.status" />
        <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
	    <input type="hidden"  id="psize"   name="pageSize" value="${pageSize!0}" />
    </div>
  </form>
  
  <div id="dialog" title="Basic dialog">
  	<form id="actionForm" action="PR002_31" method="post">
  	 <input type="hidden" id="checkedProductId" name="checkedProductId" />
  	 <input id="dialog_exclusiveKey" type="hidden" name="product.exclusiveKey" value="${product.exclusiveKey!""}"/>
		<table width="60%" class="field_tbl" align="center">
			<tr>
				<td width="25%" class="lcell"><label><@s.text name="product.id" /></label></td>
			    <td width="25%">
			    	<label id="dialog_pid_label"></label>
			    	<input id="dialog_pid" type="hidden" name="product.id" value="${product.id!""}" />
			    	<input id="dialog_pindex" type="hidden" name="product.index" value="${product.index!""}"/>
			    </td>
			</tr>
			<tr>
				<td width="25%" class="lcell"><label><@s.text name="product.price" /></label></td>
			    <td width="25%"><input id="dialog_price" name="product.price" type="text"  maxLength="10" /></td>
			</tr>
			<tr>
				<td width="25%" class="lcell"><label><@s.text name="product.status" /></label></td>
			    <td width="25%">
				    <select name="product.status"  id="dialog_status" >
			           <#include "../components/productStatusDrop.ftl">
			        </select>
				</td>
			</tr>
			<tr>
				<td width="25%" class="lcell"><label><@s.text name="product.delistreason" /></label></td>
			    <td width="45%"><input id="dialog_delistreason" name="product.delistReason" type="text"  maxLength="20" /></td>
			</tr>
		</table>
		<div class="btn_row">
		    <button type="button" id="btnAdddialog"  ><@s.text name="btn_modify" /></button>
		</div>
	</form>
  </div>
</div>
<div id="out"></div>
</#escape>
</body>
</html>