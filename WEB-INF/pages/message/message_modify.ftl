<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="message.modify" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">   
   function changeAction(url)
   {
      var flag= confirm("<@s.text name="BSC00007" />");
      if(flag==true){
          var form=document.getElementById("mdfForm");
          form.action=url;
          form.submit();  
      } 
   }   
   
   function modifyAction(url){
   
   
   }
   
  $(document).ready(function(){
         
         setSelectAllCbxChecked();
         
         $("#selectAll").click(function(){
            if($(this).attr("checked")==true){
               $("input[type=checkbox][name=companyIdList]").attr("checked","checked");
            }else{
                $("input[type=checkbox][name=companyIdList]").removeAttr("checked");
            }
          });
          
          $("input[type=checkbox][name=companyIdList]").each(function(){
               $(this).click(function(){
                  if($(this).attr("checked")==false){
                     $("#selectAll").removeAttr("checked");
                  }else{
                     if(isOtherChecked(this)){
                        $("#selectAll").attr("checked","checked")
                     }
                  }
               });
          });
     });
   
   function isOtherChecked(self){
      var flag=true;
      $("input[type=checkbox][name=companyIdList]").each(function(){
           if($(this)!=self && $(this).attr("checked")==false){
              flag=false;
              return false;
           }
      });
      return flag;
   }
   
   function setSelectAllCbxChecked(){
      var flag=true;
      $("input[type=checkbox][name=companyIdList]").each(function(){
           if($(this).attr("checked")==false){
              flag=false;
              return false;
           }
      }); 
      if(flag==true){
         $("#selectAll").attr("checked","checked");
      }
      
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
	  <form id="mdfForm" action="BS009_31" method="post">
	    <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>    	    
				       <table width="70%" class="field_tbl" align="center">
				          <tr>
				             <td class="lcell"><label><@s.text name="message.content" /></label></td>
				             <td>
				                <textarea type="text" name="message.content" cols="50" rows="4" >${message.content!""}</textarea>
				                <label class="needed"><@s.text name="input_needed" /></label>
				             </td>
				          </tr>
				        </table>
                   </td>
                </tr>
             </tbody>
          </table>  				        
	 
	    <div class="gridview" style="margin:10px auto;">
	       <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                 <td>  	    
			             <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0" >
			               <tr>
			                     <th width="10%" ><input type="checkbox" id="selectAll" /><label><@s.text name="select_all" /></label></th>
			                     <th with="90%" style="text-align:center">
			                        <label><@s.text name="message.company" /></label>
			                     </th>
			                  </tr>
			               <tr>
			                 <td colspan="2" >
			                   <div style="height:500px;overflow-y:scroll;overflow-x:hidden">
			                      <table class="datalist" width="98%">
			                        <@s.action name="message_company_list" executeResult="true" ignoreContextParams="true"> 
			                             <@s.param name="messageId">${message.id!-1}</@s.param>
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
	       <input type="hidden" name="message.id"  value="${message.id!""}" />
           <input type="hidden" name="message.exclusiveKey" value="${message.exclusiveKey!-1}"  />
	   </form>
	    <div class="btn_row" >
          <#if loginUser.hasPermission("BS009_31")>       
              <button type="button" onClick="changeAction('BS009_31')" ><@s.text name="btn_modify" /></button>
          </#if>
       </div>
     </div>  
	</div>
  <#include "../footer.ftl"/>  
 </div>	  
</#escape>       
</body>
</html>