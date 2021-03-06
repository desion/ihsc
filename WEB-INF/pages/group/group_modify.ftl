<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="group.modify" /></title>
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
   
    $(document).ready(function(){
     
      setSelectAllCbxChecked();
         
      $("#selectAll").click(function(){
            if($(this).attr("checked")==true){
               $("input[type=checkbox][name=accessActionList]").attr("checked","checked");
            }else
            {
                $("input[type=checkbox][name=accessActionList]").removeAttr("checked");
            }
          });
          
      $("input[type=checkbox][name=accessActionList]").each(function(){
          $(this).click(function(){
              if($(this).attr("checked")==false){
                 $("#selectAll").removeAttr("checked");
              }else{
                 if(checkOthers(this)){
                    $("#selectAll").attr("checked","checked")
                 }
              }
           });
       });
   });
   
   function checkOthers(self){
      var flag=true;
      $("input[type=checkbox][name=accessActionList]").each(function(){
           if($(this)!=self && $(this).attr("checked")==false){
              flag=false;
              return false;
           }
      });
      return flag;
   }
   
   function setSelectAllCbxChecked(){
      var flag=true;
      $("input[type=checkbox][name=accessActionList]").each(function(){
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
         <form id="mdfForm" action="BS006_31" method="post">	    
	      <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	         <tbody>
	            <tr>
	               <td>    	   
					    <table width="70%" class="field_tbl" align="center">
					        <tr>
					             <td class="lcell"><label><@s.text name="group.name" /></label></td>
					             <td>
					                <input type="text" name="group.name"  value="${group.name!""}"  size="40" maxLength="50"/>
					                <label class="needed"><@s.text name="input_needed" /></label>
					             </td>
					        </tr>
					        <tr>
					             <td class="lcell"><label><@s.text name="group.description"  /></label></td>
					             <td><input type="text" name="group.description"  value="${group.description!""}" size="40"  maxLength="90"/></td>
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
				           <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0">
				              <tr>
				                  <th width="10%" ><input type="checkbox" id="selectAll" /><label><@s.text name="select_all" /></label></td>
				                  <th width="90%"  style="text-align:center" align="center" ><label><@s.text name="group.accessList" /></label></td>
				              </tr>
				              <tr>
				                 <td colspan="2" >
				                    <div style="height:600px;overflow-y:scroll;overflow-x:hidden">
				                       <table class="field_tbl" width="98%">
				                           <@s.action name="access_action_list" executeResult="true" ignoreContextParams="true">   
				                               <@s.param name="selectedGroupId">${group.id!-1}</@s.param> 
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
		   <div class="gridview" style="margin:10px auto;">
		       <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		           <tbody>
		              <tr>
		                <td>   		   
				           <table class="field_tbl" width="70%" cellspacing="0" cellpadding="0">
				               <tr>
				                  <th colspan="2"  style="text-align:center" align="center" ><label><@s.text name="group.dataFilterList" /></label></td>
				               </tr>
				                   <@s.action name="filter_action_list" executeResult="true" ignoreContextParams="true">  
				                      <@s.param name="selectedGroupId">${group.id!-1}</@s.param>  
				                   </@s.action>
				           </table> 
		                </td>
		              </tr>
		           </tbody>
		          </table>  				           
		      </div>  
		       <input type="hidden" name="group.id"  value="${group.id!""}" />
	           <input type="hidden" name="group.exclusiveKey" value="${group.exclusiveKey!-1}"  />
	        </form> 
	        <div class="btn_row" >
		       <#if loginUser.hasPermission("BS006_31")>       
	               <button type="button" onClick="changeAction('BS006_31')" ><@s.text name="btn_modify" /></button>
	           </#if>
	        </div> 
	   </div>  
   </div> 
</div>	    
</#escape>     
</body>
</html>