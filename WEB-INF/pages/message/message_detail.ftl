<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="message.detail" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
   	  form.submit();
   }
   
   function deleteData(url){
       var flag= confirm("<@s.text name="BSC00003" />");
       if(flag==true){
          changeAction(url);
        }
   }
   
  function isAllChecked(){
      var flag=true;
      $("input[type=checkbox][name=companyIdList]").each(function(){
           if($(this).attr("checked")==false){
              flag=false;
              return false;
           }
      });
      return flag;
   }
   
    $(document).ready(function(){
        if(isAllChecked()){
            $("#selectAll").attr("checked","checked");
         }
         $(":checkbox").each(function(){
            $(this).click(function(){
               if($(this).attr("checked")==true){
                   $(this).removeAttr("checked");
               }else{
                   $(this).attr("checked","checked");
               }
            });
        });
    });
   
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
 <div id="container" >
   <#include "../header.ftl"/> 
    <div id="view"> 
      <div class="fields">
	    <form id="mdfForm" action="BS009_21" method="post">
          <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>    	     
			        <table width="70%" class="field_tbl" align="center" style="table-layout:fixed">
				       <tr>
		                  <td width="10%" class="lcell"><label><@s.text name="message.content" /></label></td>
					       <td width="90%" style="word-break:break-all;word-wrap:break-word;">
					           ${message.content!""}
					           <input type="hidden" name="message.content" value="${message.content!""}"/>
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
			                        <#if loginUser.hasPermission("BS009_31")>
				                        <@s.action name="message_company_list" executeResult="true" ignoreContextParams="true"> 
				                             <@s.param name="messageId">${message.id!-1}</@s.param>
				                        </@s.action>
			                        <#else>
			                            <td width="10%"><input type="checkbox" name="companyIdList"  checked/></td>
                                        <td width="90%"><label>${loginUser.companyName!""}</label></td>
			                        </#if>
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
            <input type="hidden" id="ObjId" name="message.id" value="${message.id!-1}" />
            <input type="hidden" id="exclusiveKey" name="message.exclusiveKey" value="${message.exclusiveKey!-1}"/>
	   </form>
        <div class="btn_row" >
           <#if loginUser.hasPermission("BS009_30")>       
               <button type="button" onClick="changeAction('BS009_30')" ><@s.text name="btn_modify" /></button>
           </#if>
           <#if loginUser.hasPermission("BS009_40")>       
               <button type="button" onClick="deleteData('BS009_40')" ><@s.text name="btn_delete_forever" /></button>
           </#if>
        </div>
      </div>	   
	</div>
  <#include "../footer.ftl"/>  
 </div>
</#escape>   
</body>
</html>