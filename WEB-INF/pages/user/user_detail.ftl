<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="user.detail" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   function changeAction(url)
   {
      var form=document.getElementById("mdfForm");
      form.action=url;
   	  form.submit();
   }
   
   function changeActionConfirm(url,msg){
    var flag= confirm(msg);
       if(flag==true){
          changeAction(url);
        }
    }
   
     function deleteForever(url){
   	   var msg = "<@s.text name="BSC00003" />";
   	   changeActionConfirm(url,msg);
     }
     
     function deleteForTemp(url){
       var msg = "<@s.text name="BSC00002" />";
       changeActionConfirm(url,msg);
     }

     function recovery(url){
       var msg = "<@s.text name="BSC00004" />";
       changeActionConfirm(url,msg);
     }
     
            
     
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container">          
  <#include "../header.ftl"/>
     <div id="view" >
      <div class="fields">
       <form id="mdfForm" action="BS007_30" method="post">
         <div>
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td>       
					    <table width="70%" class="field_tbl" align="center">
					         <tr>
				                <th colspan="2" ><label><@s.text name="user.info" /></label></th>
				             </tr>
						     <tr>
							    <td width="20%" class="lcell"><label><@s.text name="user.name" /></label></td>
							    <td width="80%"><label>${user.name!""}</label></td>
							    <input type="hidden" name="user.name" value="${user.name!""}" />
						      </tr>
						      <tr>
							      <td class="lcell"><label><@s.text name="user.fullName" /></label></td>
							      <td><label>${user.fullName!""}</label></td>
							      <input type="hidden" name="user.familyName" value="${user.fullName!""}" />
							  </tr>
							   <tr>
							        <td class="lcell"><label><@s.text name="user.sex" /></label></td>
							        <input type="hidden" name="user.sex" value="${user.sex!""}" />
							      <#if user.sex==0>
							        <td><@s.text name="male"/></td>
							      <#else>
							        <td><@s.text name="female"/></td>
							      </#if>
							    </tr>
							    <tr>
							        <td class="lcell"><label><@s.text name="user.mobilePhone" /></label></td>
							        <td><label>${user.mobilePhone!""}<label></td>
							        <input type="hidden" name="user.mobilePhone" value="${user.mobilePhone!""}" />
							    </tr>
							    <tr>
							        <td class="lcell"><label><@s.text name="user.email" /></label></td>
							        <td><label>${user.email!""}<label></td>
							        <input type="hidden" name="user.email" value="${user.email!""}" /> 
							    </tr>
							    <tr>
							        <td class="lcell"><label><@s.text name="user.department" /></label></td>
							        <td><label>${user.department!""}<label></td>
							        <input type="hidden" name="user.department" value="${user.department!""}" /> 
							    </tr>
							    <#if loginUser.hasPermission("BS006_10")>
								    <tr>
									    <td class="lcell"><@s.text name="user.groupId"  /></td>
									    <td><label>${user.groupName!""}<label></td>
									    <input type="hidden" name="user.groupName" value="${user.groupName!""}" /> 
						             </tr>
			                    </#if>    
			               </table>
		                </td>
		              </tr>
		           </tbody>
		        </table> 
	         </div>
	        <br/>  		               
		    <#if loginUser.hasPermission("BS007_36") && loginUser.hasPermission("BS007_34")>
		        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		           <tbody>
		              <tr>
		                <td> 		    
						    <table width="70%" class="field_tbl" align="center">
						        <tr>
				                  <th colspan="2" ><label><@s.text name="user.maintainInfo" /></label></th>
				               </tr>
					           <tr>
				                   <td width="20%" class="lcell"><label><@s.text name="user.locked" /></label></td>
				                   <#if user.locked==0>
				                     <td width="80%"><label/></td>
				                   <#else>
				                     <td width="80%"><label class="needed"><@s.text name="locked" /></label></td>
				                   </#if>  
					           </tr>
					           <tr>
					              <td  class="lcell"><label><@s.text name="user.deleted" /></label></td>
					              <#if user.deleted==0>
				                     <td><label/></td>
				                  <#else>
				                     <td><label class="needed"><@s.text name="deleted" /></label></td>
				                  </#if>
					           </tr>
					        </table>
	                     </td>
	                  </tr>
	               </tbody>
	            </table>  				        
		     <#elseif !loginUser.hasPermission("BS007_36") && !loginUser.hasPermission("BS007_34")>
		      
		     <#else>
	            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	               <tbody>
	                  <tr>
	                    <td>		     
				          <table width="70%" class="field_tbl" align="center">
		                    <tr>
		                      <th colspan="2" ><label><@s.text name="user.maintainInfo" /></label></th>
		                   </tr>
		                   <#if loginUser.hasPermission("BS007_36")>
			                   <tr>
			                       <td width="20%" class="lcell"><label><@s.text name="user.locked" /></label></td>
			                       
			                       <#if user.locked==0>
			                         <td width="80%"><label/></td>
			                       <#else>
			                         <td width="80%"><label class="needed"><@s.text name="locked" /></label></td>
			                       </#if>  
			                   </tr>
			                </#if>
			                <#if loginUser.hasPermission("BS007_34")>   
			                   <tr>
			                      <td width="20%" class="lcell"><label><@s.text name="user.deleted" /></label></td>
			                      <#if user.deleted==0>
			                         <td width="80%"><label/></td>
			                      <#else>
			                         <td width="80%"><label class="needed"><@s.text name="deleted" /></label></td>
			                      </#if>
			                   </tr>
		                    </#if>
		                </table>
                    </td>
                  </tr>
               </tbody>
              </table> 		                
		    </#if> 
		       <input type="hidden" name="user.deleted" value="${user.deleted!-1}" />
               <input type="hidden" name="user.locked" value="${user.locked!-1}" />
              <input type="hidden" name="user.id" value="${user.id!-1}" />
              <input type="hidden" name="user.exclusiveKey" value="${user.exclusiveKey!-1}" />
         </form>               
		 <div class="btn_row">
		     <#if user.deleted==0> 
			   <#if loginUser.hasPermission("BS007_30")>
	              <button type="button" onClick="changeAction('BS007_30')" ><@s.text name="btn_modify" /></button>
	           </#if>
	         </#if>  
	         <#if loginUser.hasPermission("BS007_22")>  
	            <button type="button" onClick="changeAction('BS007_22')" ><@s.text name="btn_template_add" /></button>
	         </#if>  
	         
             <#if user.locked==0>
                <#if loginUser.hasPermission("BS007_36")>
                    <button type="button" id="btn_lock" onClick="changeAction('BS007_36')" ><@s.text name="btn_lock" /></button>
                </#if> 
             <#else>
                <#if loginUser.hasPermission("BS007_37")>
                    <button type="button" id="btn_unlock" onClick="changeAction('BS007_37')" ><@s.text name="btn_unlock" /></button>
                </#if> 
             </#if>
             
             <#if user.deleted==0>
                <#if loginUser.hasPermission("BS007_34")>
                    <button type="button" id="btn_delete" onClick="deleteForTemp('BS007_34')"  ><@s.text name="btn_delete" /></button>
                </#if>  
             <#else>  
	            <#if loginUser.hasPermission("BS007_35")>
	                <button type="button" id="btn_recovery" onClick="recovery('BS007_35')"  ><@s.text name="btn_recovery" /></button>
	            </#if>  
	            <#if loginUser.hasPermission("BS007_40")>
	                <button type="button" onClick="deleteForever('BS007_40')" ><@s.text name="btn_delete_forever" /></button>
	            </#if> 
            </#if>  
         </div>
       </div> 
     </div>
   <#include "../footer.ftl"/>
 </div>    
</#escape>           
</body>
</html>