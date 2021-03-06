<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="PR005_72_title" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
    function search1(url)
    {
       var form=document.getElementById("searchForm1");
       form.action=url;
       form.submit();
    }
    
    function search2(url)
    {
       var form=document.getElementById("searchForm2");
       form.action=url;
       form.submit();
    }
</script>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container">
  <#include "../header.ftl"/>
    <div id="view">
        <div class="fields">
            <form id="searchForm1" method="post">
		        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		           <tbody>
		              <tr>
		                <td>               
			                <table width="40%" align="center" class="field_tbl" >
			                    <tbody>
			                        <tr>
			                            <th colspan="2" ><label><@s.text name="manufactureNo_search" /></label></th>
			                        </tr>
			                        
	                                <tr>
	                                   <td class="lcell"><label><@s.text name="install.productCategoryId"  /></label></td>
	                                   <td>
	                                      <select name="install.productCategoryId" >
	                                         <@s.action name="productCategory_drop" executeResult="true" ignoreContextParams="true">
	                                            <@s.param name="selectedProductCategoryId">${install.productCategoryId!"-1"}</@s.param>
	                                            <@s.param name="cateLevel">3</@s.param>
	                                         </@s.action>
	                                      </select>
	                                      <label class="needed" ><@s.text name="select_needed" /></label>                
	                                   </td>
	                               </tr>
			                        
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="install.manufactureNo" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="install.manufactureNo" value="${install.manufactureNo!""}" maxLength="10" size="40"/ >
			                                <label style="color:red"><@s.text name="input_needed" /></label>
			                            </td>
			                        </tr>
			                    </tbody>
			                </table>
		                </td>
		              </tr>
		           </tbody>
		        </table>                  
                <br>
             </form>  
             
             <div class="btn_row">
                 <#if loginUser.hasPermission("PR005_12")>
                    <button type="button" onClick="search1('PR005_12')" style="width:120px;"><@s.text name="btn_search" /></button>
                 </#if>   
             </div>
            <form id="searchForm2" method="post">
               <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
                   <tbody>
                      <tr>
                        <td>             
			                <table width="40%" align="center" class="field_tbl" >
			                    <tbody>
			                        <tr>
			                            <th colspan="2" ><label><@s.text name="manufactureNo_range_search" /></label></th>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="manufactureNo_start" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="install.manufactureNoStart" value="${install.manufactureNoStart!""}" maxLength="10" size="40"/ >
			                            </td>
			                        </tr>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="manufactureNo_end" /></label></td>
			                            <td width="80%">
			                                <input type="text" name="install.manufactureNoEnd" value="${install.manufactureNoEnd!""}" maxLength="10" size="40"/ >
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
                <#if loginUser.hasPermission("PR005_10")>
                    <button type="button" onClick="search2('PR005_10')" style="width:120px;"><@s.text name="btn_search" /></button>
                </#if> 
             </div>
        </div>
    </div>
   </div> 
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>