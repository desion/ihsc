<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <#setting number_format="0.#">
<title><@s.text name="channelDetail.create" /></title>
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
        <form id="mdfForm" action="BS005_21" method="post">
        <input type="hidden" name="channel.id" value="${channel.id!""}"></input>
        <input type="hidden" name="channelDetail.channelId" value="${channel.id!""}"></input>
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>
		           <table width="60%" class="field_tbl" align="center">
		            <tbody>
		             <tr>
		               <td width="20%" class="lcell">
		               		<label><@s.text name="channelDetail.sname" /></label>
		               	</td>
		               <td width="80%">
		                   <label>${channelDetail.sname!""}</label>
		                   <input type="hidden" name="channelDetail.sname"  value="${channelDetail.sname!""}" />
		               </td>
		             </tr>
		            <tr>
                       <td width="20%" class="lcell"><label><@s.text name="channelDetail.name" /></label></td>
                       <td width="80%">
                       	   <label>${channelDetail.name!""}</label>
                       	   <input type="hidden" name="channelDetail.name"  value="${channelDetail.name!""}" />
                       </td>
                     </tr>
		             <tr>
		                <td class="lcell"><label><@s.text name="channelDetail.value" /></label></td>
		                <td>
		                  <input type="text" name="channelDetail.valueStr"  value="${channelDetail.valueStr!""}"  size="30" maxLength="18"/>
		                  <label class="needed"><@s.text name="channelDetail.value.tips" /></label>
		               </td>
		            </tr>
		            <tr>
		              <td class="lcell"><label><@s.text name="channelDetail.province" /></label></td>
		              <td>
		                  <select name="channelDetail.provinceId" >
			                 <@s.action name="province_only_drop" executeResult="true" ignoreContextParams="true">
			                     <@s.param name="selectedProvinceId">${channelDetail.provinceId!"-1"}</@s.param>
			                 </@s.action>
			              </select>
		                  <label class="needed"><@s.text name="select_needed" /></label>
		              </td>
		              
		            </tr>
		            
		            <tr>
		              <td class="lcell"><label><@s.text name="channelDetail.operator" /></label></td>
		              <td>
			              <select name="channelDetail.operator" style="width:100px;"  >
			                   <#include "../components/operatorDrop.ftl">
			              </select>
			              <input type="hidden" name="selectOperator" value="${channelDetail.operator!""}"/>
		                  <label class="needed"><@s.text name="select_needed" /></label>
		              </td>
		              
		            </tr>
		            <tr>
		              <td class="lcell"><label><@s.text name="channelDetail.priority" /></label></td>
		              <td>
		                  <input type="text" name="channelDetail.priority"  value="${channelDetail.priority!""}"  size="6" maxLength="2"/>
		                  <label class="needed"><@s.text name="input_needed" /></label>
		              </td>
		              
		            </tr>
		            <tr>
		              <td class="lcell"><label><@s.text name="channelDetail.repeat" /></label></td>
		              <td>
		                  <input type="text" name="channelDetail.repeat"  value="${channelDetail.repeat!""}"  size="6" maxLength="2"/>
		                  <label class="needed"><@s.text name="channelDetail.repeat.tips" /></label>
		              </td>
		            </tr>
		            
		             <tr>
		              <td class="lcell"><label><@s.text name="channelDetail.discount" /></label></td>
		              <td>
		              	  <#if channelDetail.discount??>
		                  	<input type="text" name="channelDetail.discount"  value="${channelDetail.discount?if_exists?string.number}"  size="6" maxLength="4"/>
		                  <#else>
		                  	<input type="text" name="channelDetail.discount"  value="${channelDetail.discount!""}"  size="6" maxLength="4"/>
		                  </#if>
		                  <label class="needed"><@s.text name="input_needed" /></label>
		              </td>
		            </tr>
		            
		            <tr>
		              <td class="lcell"><label><@s.text name="channelDetail.comments" /></label></td>
		              <td>
		                  <input type="text" name="channelDetail.comments"  value="${channelDetail.comments!""}"  size="45" maxLength="50"/>
		              </td>
		            </tr>
		            
		            <tr>
		              <td class="lcell"><label><@s.text name="channelDetail.status" /></label></td>
		              <td>
		                  <input type="radio" name="channelDetail.status" value="0" checked/><@s.text name="channelDetail.status.disable" />&nbsp;
		                  <input type="radio" name="channelDetail.status" value="1" /><@s.text name="channelDetail.status.enable" />
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
           <#if loginUser.hasPermission("BS005_21")>
              <button type="button" onClick="changeAction('BS005_21')" ><@s.text name="btn_add" /></button>
           </#if> 
        </div>
     </div>
     
  </div>
</div>
</#escape>   
</body>
</html>