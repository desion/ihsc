<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="company.customerEdit" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
  function changeAction(url){
       var form=document.getElementById("mdfForm");
       form.action=url;
       form.submit();  
  }
  

  function appendCustomerAction(url){
      var msg = "<@s.text name="BSC00010" />";
      var flag= confirm(msg);
      if(flag==true){
          changeAction(url);
      }
  
  }
  
  function deleteCustomerAction(url){
      var msg = "<@s.text name="BSC00011" />";
      var flag= confirm(msg);
      if(flag==true){
          changeAction(url);
      }
  }
  
    
 $(document).ready(function(){ 
   $("#saleComId").val("");
   $("#bankId").val(""); 
   $("#bankDrop").val(""); 
   $("#bankDrop").change(function(){
       var params = "useFlag=1";
       var bankId = $(this).val();
       var bankType = $("#bankDrop").children("option:selected").attr("bankType");
       if(bankType!=undefined){
	        if(bankType==0 && bankId != ""){
	           params = params + "&bankId="+bankId;
	           $("#saleComId").val("");
	           $("#bankId").val(bankId);
	           
	        }else if(bankType==1 && bankId != ""){
	           params = params + "&saleComId="+bankId;
	           $("#saleComId").val(bankId);
	           $("#bankId").val("");
	        }      
          
       }else{
            $("#saleComId").val("");
            $("#bankId").val("");
       }

       $.ajax({
          url: 'province_drop',
          type: 'GET',
          data: params,
          dataType: 'html',
          cache:false,
          timeout: 1000,
          success: function(data, textStatus){
            $("#provinceDrop").html(data);
              cityChange();
          }
       });       
   });
       
   $("#provinceDrop").change(function(){
        cityChange();
      
   });
   
   $("#cityDrop").change(function(){
        bankCompanyChange()
   });
        
 }); 
 

	//根据银行，省份，城市下拉菜单的内容，动态变换银行下拉菜单中的数据
   function bankCompanyChange(){
          var params = "1=1"
          var bankType = $("#bankDrop").children("option:selected").attr("bankType");
          var bankId = $("#bankDrop").val();
          if(bankType==0 && bankId != ""){
               params = params + "&bankId="+bankId;
          }else if(bankType==1 && bankId != ""){
               params = params + "&saleComId="+bankId;
          }
        
          var provinceId = $("#provinceDrop").val();
          if(provinceId != ""){
            params = params + "&provinceId="+provinceId;
          }              
          var cityId = $("#cityDrop").val();
          if(cityId != ""){
            params = params + "&cityId="+cityId;
          }  

          $.ajax({
              url: 'bank_customer_drop',
              type: 'GET',
              data: params,
              dataType: 'html',
              cache:false,
              timeout: 1000,
              success: function(data, textStatus){
                $("#bankCustomerDrop").html(data);
              }
          });  
     }
     
     //根据银行和省份的改变而改变城市列表中的值
     function cityChange(){
           var params = "useFlag=1"
           var provinceId = $("#provinceDrop").val();
	       var bankId = $("#bankDrop").val();
	       var bankType = $("#bankDrop").children("option:selected").attr("bankType");
          
          if(bankType==0 && bankId != ""){
               params = params + "&bankId="+bankId;
          }else if(bankType==1 && bankId != ""){
               params = params + "&saleComId="+bankId;
          }
	
	      if(provinceId != ""){
            params = params + "&provinceId="+provinceId;
          } 
	       $.ajax({
	          url: 'city_drop',
	          type: 'GET',
	          data: params,
	          dataType: 'html',
	          cache:false,
	          timeout: 1000,
	          success: function(data, textStatus){
	            $("#cityDrop").html(data);
	             bankCompanyChange()
	          }
	     });
     
     }
    //全选
	function selectAll(){
	   $("input[type=checkbox][name=bankCustomerList]").attr("checked","checked");
	}
	//取消全选
	function unSelectAll(){
	   $("input[type=checkbox][name=bankCustomerList]").removeAttr("checked");
	}     
   
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container" >
 <#include "../header.ftl"/>
   <div id="view">
      <div class="fields">  
	    <form id="mdfForm" action="BS005_31" method="post">
	     <div>
          <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
             <tbody>
                <tr>
                  <td>     	    
				      <table width="70%" class="field_tbl" align="center">
		                <tr>
                           <th align="center" colspan="2" ><label><@s.text name="company.info" /></label></th>
                        </tr> 
                        <tr>
                            <td class="lcell" width="20%"><label><@s.text name="company.code" /></label></td>
                            <td width="80%" ><label>${company.code!""}</label></td>
                            <input type="hidden" name="company.code"  value="${company.code!""}" />
                        </tr>  
                        <tr>
                            <td class="lcell"><label><@s.text name="company.companyTypeName" /></label></td>
                            <td><label>${company.companyTypeName!""}</label></td>
                            <input type="hidden" name="company.companyTypeName" value="${company.companyTypeName!""}" />
                        </tr> 
                        <tr>
                            <td class="lcell"><label><@s.text name="company.shortName" /></label></td>
                            <td><label>${company.shortName!""}</label></td>
                            <input type="hidden" name="company.shortName"  value="${company.shortName!""}" />
                        </tr>
                       
                        <tr>
                            <td class="lcell"><label><@s.text name="company.mainCompanyName" /></label></td>
                            <td  ><label>${company.companyNameShow!""}</label></td>
                            <input type="hidden" name="company.companyNameShow" value="${company.companyNameShow!""}" />
                        </tr>
                        
                        <tr id="comMainNameDrop">
                            <td class="lcell"><label><@s.text name="filter_opitons" /></label></td>
                            <td>
                              <select id="bankDrop" name="company.bankId"  value="${company.bankId!""}"  style="width:150px">
                                 <@s.action name="bank_drop" executeResult="true" ignoreContextParams="true">
                                   <@s.param name="selectedBankId">${bankId!-1}</@s.param>
                                   <@s.param name="useFlag">1</@s.param>
                                   <@s.param name="cateFlag">1</@s.param>
                                </@s.action>
                              </select>
                              <label class="needed" ><@s.text name="select_needed" /></label> 
                              <select id="provinceDrop" name="company.provinceId"  value="${company.provinceId!""}" style="width:150px">
                                 <@s.action name="province_drop" executeResult="true" ignoreContextParams="true">
                                    <@s.param name="selectedProvinceId">${provinceId!"-1"}</@s.param>
                                    <@s.param name="useFlag">1</@s.param>
                                 </@s.action>
                              </select>
                              
                              <select  id="cityDrop" name="company.cityId"  value="${company.cityId!""}"  style="width:180px">
                                 <@s.action name="city_drop" executeResult="true" ignoreContextParams="true">
                                   <@s.param name="selectedCityId">${cityId!-1}</@s.param>
                                   <@s.param name="useFlag">1</@s.param>
                                </@s.action>
                              </select>                               
                            </td>
                        </tr> 
                        <tr>
                            <td class="lcell"><label class="needed" ><@s.text name="filterred_data" /></label></td>
                            <td>
                                <select id="bankCustomerDrop" style="width:200px">
                                    <@s.action name="bank_customer_drop" executeResult="true" ignoreContextParams="true">
                                    </@s.action>
                                </select>   
                            </td>  
                         </tr>         
				    </table>
                </td>
              </tr>
           </tbody>
         </table> 	
         </div>    
          
         <div class="gridview" style="margin:10px auto;">
           <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
             <tbody>
            
               <tr>
                  <td>            
			          <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0">
			              <tr>
			                  <th colspan="2" style="text-align:center">
			                     <label><@s.text name="company.customer" /></label>
			                  </th>
			              </tr>
			              <tr>
			                 <td>
			                   <div style="height:300px;overflow-y:scroll;overflow-x:hidden">
			                      <table id ="bankCustomer" class="field_tbl" width="98%">
						             <#list agentBankCustomerLst as bankCustomer>   
						                <tr id="${bankCustomer.id}">
						                  <td width="8%"><input type="checkbox" name="bankCustomerList" value="${bankCustomer.id!""}" checked/></td>
						                  <td width="92%"><label>${bankCustomer.shortName!""}</label></td>
						                </tr>
						             </#list>    
			                      </table>
			                    </div>  
			                 </td>
			             </tr>   
			           </table>
                   </td>
                </tr>
            </tbody>
          </table>  			           
        </div>	
             <input type="hidden"  id = "saleComId" name="saleComId" />
             <input type="hidden"  id = "bankId" name="bankId" />
             <input type="hidden"  name="company.id" value="${company.id!-1}" />
             <input type="hidden"  name="company.exclusiveKey" value="${company.exclusiveKey!-1}"  />
		</form>
	    <div class="btn_row" >
	      <button type="button" onClick = "selectAll()" ><@s.text name="select_all" /></button>
	      <button type="button" onClick = "unSelectAll()" ><@s.text name="un_select_all" /></button>
	      <#if loginUser.hasPermission("BS005_39")>
	           <button type="button" onClick="deleteCustomerAction('BS005_39')"><@s.text name="delete_not_selected" /></button>
	       </#if>
          <#if loginUser.hasPermission("BS005_38")>
             <button onClick="appendCustomerAction('BS005_38')" ><@s.text name="btn_add_com" /></button>
          </#if>
          <#if loginUser.hasPermission("BS005_12")>
             <button type="button" onClick="changeAction('BS005_12')"><@s.text name="btn_return" /></button>
          </#if>
	    </div>		
	</div>
  </div>
 <#include "../footer.ftl"/> 
</div>
</#escape>		
</body>
</html>