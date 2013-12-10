<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="company.create" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var flag= confirm("<@s.text name="BSC00006" />");
      if(flag==true){
         var form=document.getElementById("mdfForm");
         form.action=url;
         form.submit();  
      }
   }
 
    $(document).ready(function(){
      var initChecked = checkCompanyTypeSelected();
      if(initChecked){
          $("#comMainNameDrop").show();
          $("#comMainNameTxt").attr("readonly","readonly");
           
      }else{
          $("#comMainNameDrop").hide(); 
          $("#comMainNameTxt").removeAttr("readonly");   
      }
       
     var repairCheckedSize = $(":checkbox[name='company.comTypeList'][value='3']:checked").length;
     if(repairCheckedSize >0){
        $("#agentProductDiv").show();
        $("#agentCustomerDiv").show();
     }else{
        $("#agentProductDiv").hide();
        $("#agentCustomerDiv").hide();
     }

     var provinceId = $("#provinceDrop").val();
     
     $(":checkbox[name='company.comTypeList']").each(function(){

        $(this).click(function(){
              
         var checked = checkCompanyTypeSelected();
         if(checked){
             $("#comMainNameDrop").show();
             $("#comMainNameTxt").attr("readonly","readonly");
             changeCompanyName();
         }else{
             $("#comMainNameDrop").hide(); 
             $("#comMainNameTxt").removeAttr("readonly"); 
             $("#comMainNameTxt").val("");  
         }
         
         //判断是否选择了维修商
           var comTypeId = $(this).val();
           if(comTypeId == 3){
	           var comTypeChecked = $(this).attr("checked");
	           if(comTypeChecked == true){
	              $("#agentProductDiv").show();
	              $("#agentCustomerDiv").show();
	           }else{
	              $("input[type=checkbox][name=productCategoryAgentList]").removeAttr("checked");
                  $("input[type=checkbox][name=companyCustomerAgentList]").removeAttr("checked");
	              $("#agentProductDiv").hide();
	              $("#agentCustomerDiv").hide();
	           }
            }
         
        });
      });
      
     
      $(":checkbox[name='productCategoryAgentList']").each(function(){
        $(this).click(function(){
           checkedNode(this);
        });
      });
      
       $("#bankDrop").change(function(){
          changeCompanyName();
       });
       
       $("#provinceDrop").change(function(){
           var provinceId = $(this).val();
           var params = "provinceId="+provinceId;
           $.ajax({
              url: 'city_drop',
              type: 'GET',
              data: params,
              dataType: 'html',
              timeout: 1000,
              success: function(data, textStatus){
                $("#cityDrop").html(data);
                if(checkCompanyTypeSelected()){
                   changeCompanyName();
                }
              }
         });
          
       });
       
       $("#cityDrop").change(function(){
          if(checkCompanyTypeSelected()){
            changeCompanyName();
          }
       });
           
   }); 
   

    function checkedNode(self){
       var parent_id=$(self).attr("parentId");
       var self_id=$(self).attr("selfId"); 
        //if parent node exsits,should check whether the parent node is checked
       if(parent_id!='0'){ 
           var parentNode=$(":checkbox[name='productCategoryAgentList'][selfId='"+parent_id+"']"); 
           //The parent node is checked,if the current node is checked,then the child node should be checked.
           if(parentNode.attr("checked")==true){    
                $(":checkbox[name='productCategoryAgentList'][parentId='"+self_id+"']").each(function(){            
                    if($(self).attr("checked")==true){    
                       $(this).attr("checked","checked");
                    }else{
                       $(this).removeAttr("checked");    
                    }
                    checkedNode(this);    
                });
           }else{
                //if parent node is not checked,then current node and its child node should not be checked.
                $(self).removeAttr("checked");
                $(":checkbox[name='productCategoryAgentList'][parentId='"+self_id+"']").each(function(){    
                       $(this).removeAttr("checked");    
                         checkedNode(this);        
                });
            }
       }else{
            //if the current node do not have parent node,judging the child node should be checked or not
            // according to the current node 
           $(":checkbox[name='productCategoryAgentList'][parentId='"+self_id+"']").each(function(){
                if($(self).attr("checked")==true){
                   $(this).attr("checked","checked");
                }else{
                    $(this).removeAttr("checked");        
                }
                 checkedNode(this);        
           });       
       }
    }
   //公司分类中如果中，当最终用户选中，并且其他都没选中的时候，银行才显示
   function checkCompanyTypeSelected(){
      var bankVisible = false;
      var fourCheckedSize = $(":checkbox[name='company.comTypeList'][value='4']:checked").length;
      var otherCheckedSize = $(":checkbox[name='company.comTypeList'][value!='4']:checked").length;

      if(fourCheckedSize ==1 && otherCheckedSize==0){
        bankVisible = true;
      }
      return bankVisible;
    }      
    //根据银行，省份，城市下拉菜单的选择，改变公司名称    
    function changeCompanyName(){
        var municipality = $("#municipality").val();//获取直辖市列表
        var bankName = $("#bankDrop").children("option:selected").text();  
        var provinceName = $("#provinceDrop").children("option:selected").text().split(",");
        provinceName = provinceName[0];
        
        var cityName = $("#cityDrop").children("option:selected").text().split(",");
        cityName = cityName[0];
        
        var bankId = $("#bankDrop").val();
        var provinceId = $("#provinceDrop").val();
        var cityId = $("#cityDrop").val();
        if(bankId=="" && provinceId=="" && cityId==""){
           $("#comMainNameTxt").val("");
           $("#comShortNameTxt").val("");
        }else{
	        if(bankId==""){
	            bankName = "";
	        }
	        if(provinceId==""){
	            provinceName="";
	        }
	        
	        if(cityId == ""){
	            cityName="";
	        }
	        if(municipality.indexOf(provinceName)!=-1){
	           cityName="";
	        }
           
	       $("#comMainNameTxt").val(bankName+provinceName+cityName+"分行");
	       $("#comShortNameTxt").val(bankName+provinceName+cityName+"分行");
        }
        
       
    }
    
    function cityChange(provinceId){
      var selectedCityId = $("#cityDrop").val()
      var params = "provinceId="+provinceId;
      $.ajax({
          url: 'city_drop',
          type: 'GET',
          data: params,
          dataType: 'html',
          timeout: 1000,
          success: function(data, textStatus){
            $("#cityDrop").html(data);
            $("#cityDrop").val(selectedCityId);
          }
        });
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
         <form id="mdfForm" action="BS005_21" method="post">
          <div>
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
                <tbody>
                    <tr>
                      <td>           
                          <table width="70%" class="field_tbl" align="center">
                            <tr>
                               <th  colspan="2" ><label><@s.text name="company.info" /></label></th>
                            </tr> 
                            <tr>
                                <td width="20%" class="lcell"><label><@s.text name="company.code" /></label></td>
                                <td width="80%">
                                   <input type="text" name="company.code"  value="${company.code!""}"  size="60"  maxLength="60" />
                                   <label class="needed" ><@s.text name="input_needed" /></label> 
                                </td>
                            </tr>
                            <tr>
                                <td class="lcell"><label><@s.text name="company.companyTypeName" /></label></td>
                                <td>
                                   <@s.action name="companyType_check" executeResult="true" ignoreContextParams="true">
                                       <@s.param name="comId">${company.id!-1}</@s.param>
                                     </@s.action>
                                  <label class="needed" ><@s.text name="select_needed" /></label> 
                                </td>
                            </tr>
                            <tr>
                               <td class="lcell"><label><@s.text name="company.shortName" /></label></td>
                               <td>
                                  <input id="comShortNameTxt" type="text" name="company.shortName"  value="${company.shortName!""}" size="60" maxLength="20"/>
                                   <label class="needed" ><@s.text name="input_needed" /></label> 
                               </td>
                            </tr>
                            <tr>
                                <td class="lcell"><label id="nameLbl"><@s.text name="company.nameLbl" /></label></td>
                                <td>
                                   <input id="comMainNameTxt" type="text" name="company.companyNameShow"  value="${company.companyNameShow!""}" size="60" maxLength="40"/>
                                   <label  class="needed" ><@s.text name="input_needed" /></label> 
                                </td>
                            </tr>
                            
                            <tr id="comMainNameDrop">
                                <td class="lcell"><label><@s.text name="company.bankName" /></label></td>
                                <td>
                                  <select id="bankDrop" name="company.bankId"  value="${company.bankId!""}"  >
                                     <@s.action name="bank_drop" executeResult="true" ignoreContextParams="true">
                                       <@s.param name="selectedBankId">${company.bankId!-1}</@s.param>
                                    </@s.action>
                                  </select>
                                  <label class="needed" ><@s.text name="select_needed" /></label> 
                                </td>
                            </tr>                           
                            <tr>
                              <td class="lcell"><label><@s.text name="company.provinceName" /></label></td>
                                <td>
                                   <select id="provinceDrop" name="company.provinceId"  value="${company.provinceId!""}"  >
                                     <@s.action name="province_drop" executeResult="true" ignoreContextParams="true">
                                        <@s.param name="selectedProvinceId">${company.provinceId!"-1"}</@s.param>
                                     </@s.action>
                                   </select>
                                   <label class="needed" ><@s.text name="select_needed" /></label> 
                                </td>
                            </tr>                           
                            <tr>
                              <td class="lcell"><label><@s.text name="company.cityName" /></label></td>
                                <td>
                                  <select  id="cityDrop" name="company.cityId"  value="${company.cityId!""}"  >
                                     <@s.action name="city_drop" executeResult="true" ignoreContextParams="true">
                                       <@s.param name="selectedCityId">${company.cityId!-1}</@s.param>
                                    </@s.action>
                                  </select>
                                  <label class="needed" ><@s.text name="select_needed" /></label> 
                                </td>
                            </tr>                          
                            <tr>
                                <td class="lcell"><label><@s.text name="company.address1" /></label></td>
                                <td>
                                  <input type="text" name="company.address1"  value="${company.address1!""}" size="60"  maxLength="40"/>
                                  <label class="needed" ><@s.text name="input_needed" /></label> 
                                </td>
                            </tr>
                            <tr>
                                <td class="lcell"><label><@s.text name="company.address2" /></label></td>
                                <td><input type="text" name="company.address2"  value="${company.address2!""}" size="60"  maxLength="40"/></td>
                            </tr>           
                        
                            <tr>
                                <td class="lcell"><label><@s.text name="company.zipCode" /></label></td>
                                <td><input type="text" name="company.zipCode"  value="${company.zipCode!""}"  size="60" maxLength="20"/></td>
                            </tr>
                            <tr>
                                <td class="lcell"><label><@s.text name="company.tel1" /></label></td>
                                <td>
                                   <input type="text" name="company.tel1"  value="${company.tel1!""}" size="60"  maxLength="60"/>
                                   <label class="needed" ><@s.text name="input_needed" /></label> 
                                </td>
                            </tr>
                            <tr>
                                <td class="lcell"><label><@s.text name="company.tel2" /></label></td>
                                <td><input type="text" name="company.tel2"  value="${company.tel2!""}" size="60"  maxLength="60"/></td>
                            </tr>
                            <tr>
                                <td class="lcell"><label><@s.text name="company.fax" /></label></td>
                                <td><input type="text" name="company.fax"  value="${company.fax!""}"  size="60" maxLength="60"/></td>
                            </tr>
                            <tr>
                                <td class="lcell"><label><@s.text name="company.homePage" /></label></td>
                                <td><input type="text" name="company.homePage"  value="${company.homePage!""}" size="60"  maxLength="240"/></td>
                            </tr>
                            <tr>
                                <td class="lcell"><label><@s.text name="company.email" /></label></td>
                                <td><input type="text" name="company.email"  value="${company.email!""}" size="60"  maxLength="80"/></td>
                            </tr>
                        </table>
                     </td>
                 </tr>
             </tbody>
          </table> 
          </div>                
          <div id="agentProductDiv" class="gridview" style="margin:10px auto;">
           <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
                <tbody>
                    <tr>
                      <td>  
                         <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0" >
                           <tr>
                             <th style="text-align:center"><label><@s.text name="company.agentProduct" /></label></th>
                           </tr>
                           <tr>
                             <td>
                               <div style="height:300px;overflow-y:scroll;overflow-x:hidden">
                                  <table class="field_tbl" width="98%">
                                      <@s.action name="product_category_agent_tree" executeResult="true" ignoreContextParams="true"> 
                                         <@s.param name="agentComId">${company.id!-1}</@s.param>
                                      </@s.action>
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
          
         <div id="agentCustomerDiv" class="gridview" style="margin:10px auto;">
           <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
                <tbody>
                    <tr>
                      <td>           
                          <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0" >
                              <tr>
                                  <th colspan="2" style="text-align:center">
                                     <label><@s.text name="company.agent" /></label>
                                  </th>
                              </tr>
                              <tr>
                                 <td>
                                   <div style="height:300px;overflow-y:scroll;overflow-x:hidden">
                                      <table class="field_tbl" width="98%">
                                        <@s.action name="company_agent_customer_list" executeResult="true" ignoreContextParams="true"> 
                                            <@s.param name="agentComId">${company.id!-1}</@s.param>
                                        </@s.action>
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
           <input type="hidden" id="municipality" name="municipality" value="${municipality!""}" />
          </form>
          <div class="btn_row">
            <#if loginUser.hasPermission("BS005_21")>       
                <button type="button" onClick="changeAction('BS005_21')" ><@s.text name="btn_add" /></button>
            </#if>
          </div>
      </div>
  </div>
 <#include "../footer.ftl"/>  
</div>
</#escape>
</body>
</html>