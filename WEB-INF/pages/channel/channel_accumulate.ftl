<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="channelAccu.create" /></title>
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
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container" >
  <#include "../header.ftl"/>
  
   <div id="view">
      <div class="fields">  
        <form id="mdfForm" action="BS004_26" method="post">
        <input type="hidden" name="channelAccumu.channelId" value="${channelAccumu.channelId!""}">
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>
		           <table width="60%" class="field_tbl" align="center">
		            <tbody>
		             <tr>
		               <td width="20%" class="lcell"><label><@s.text name="channelAccu.channelName" /></label></td>
		               <td width="80%">
		               		<label>${channelAccumu.channelName!""}</label>
		                    <input type="hidden" name="channelAccumu.channelName"  value="${channelAccumu.channelName!""}" />
		               </td>
		             </tr>
		            <tr>
                       <td width="20%" class="lcell"><label><@s.text name="channelAccu.channelSName" /></label></td>
                       <td width="80%">
                       		<label>${channelAccumu.channelSName!""}</label>
                            <input type="hidden" name="channelAccumu.channelSName"  value="${channelAccumu.channelSName!""}"/>
                       </td>
                     </tr>
		             <tr>
		                <td class="lcell"><label><@s.text name="channelAccu.amount" /></label></td>
		                <td>
		                  
		                  <#if (channelAccumu.amount)??>
		                  	<input type="text" name="channelDetail.discount"  value="${channelAccumu.amount?if_exists?string.number}"  size="10" maxLength="9"/>
		                  <#else>
		                  	<input type="text" name="channelAccumu.amount"  value="${channelAccumu.amount!""}"  size="10" maxLength="9"/>
		                  </#if>
		                  <label class="needed"><@s.text name="input_needed" /></label>
		               </td>
		            </tr>
		            <tr>
		              <td class="lcell"><label><@s.text name="channelAccu.type" /></label></td>
		              <td>
		                 <select name="channelAccumu.type" style="width:100px;"  >
			                   <#include "../components/accumulateType.ftl">
			              </select>
			              <input type="hidden" name="selectedAccuType" value="${channelAccumu.type!""}"/>
		                  <label class="needed"><@s.text name="select_needed" /></label>
		              </td>
		              
		            </tr>
		            <tr>
		              <td class="lcell"><label><@s.text name="channelAccu.comments" /></label></td>
		              <td>
		                  <input type="text" name="channelAccumu.comments"  value="${channelAccumu.comments!""}"  size="30" maxLength="20"/>
		                  <label class="needed"><@s.text name="input_needed" /></label>
		              </td>
		              
		            </tr>
		          </tbody> 
		        </table>
		       </td>
		      </tr>
		     </tbody>
		    </table>    
       </form>
       <div class="btn_row">
           <#if loginUser.hasPermission("BS004_26")>
              <button type="button" onClick="changeAction('BS004_26')" ><@s.text name="btn_add" /></button>
           </#if> 
        </div>
     </div>
     
  </div>
</div>
</#escape>   
</body>
</html>