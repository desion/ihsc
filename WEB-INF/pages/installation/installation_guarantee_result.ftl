<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="PR005_74_title" /></title>
<#include "../common_header.ftl"/>
</head>
<body>
<#escape x as x?html>
<#include "../components/error_reference.ftl"/>
<div id="container">
    <#include "../header.ftl"/>
    <div id="view" >
      <div class="fields">
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>          
		            <table width="40%" align="center" class="field_tbl" >
		                <tbody>
		                    <tr>
		                        <th colspan="2"><label><@s.text name="guarantee_result" /></label></th>
		                    </tr> 
		                    <tr>
		                        <td width="30%" class="lcell"><label><@s.text name="install.manufactureNo" /></label></td>
		                        <td width="70%"><label>${install.manufactureNo!""}</label></td>
		                    </tr>
		                    <tr>
                                <td width="30%" class="lcell"><label><@s.text name="install.installDate" /></label></td>
                                <td width="70%"><label>${install.installDate!""}</label></td>
                            </tr> 
                            <tr>
                                <td width="30%" class="lcell"><label><@s.text name="install.guaranteeStartDate" /></label></td>
                                <td width="70%"><label>${install.guaranteeStartDate!""}</label></td>
                            </tr>
                            <tr>
                                <td width="30%" class="lcell"><label><@s.text name="install.guaranteeEndDate" /></label></td>
                                <td width="70%"><label>${install.guaranteeEndDate!""}</label></td>
                            </tr>                            
                            
		                    <tr>
		                        <td width="30%" class="lcell"><label><@s.text name="guarantee" /></label></td>
		                        <td width="70%">
		                            <#if guaranteeFlg>
		                                <label><@s.text name="in_guarantee" /></label>
		                            <#else>
		                                <label style="color:red"><@s.text name="out_guarantee" /></label>
		                            </#if>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td width="30%" class="lcell"><label><@s.text name="install.productModel" /></label></td>
		                        <td width="70%"><label>${install.model!""}</label></td>
		                    </tr>
		                    <tr>
		                        <td width="30%" class="lcell"><label><@s.text name="install.productCategory" /></label></td>
		                        <td width="70%"><label>${install.productCategoryName!""}</label></td>
		                    </tr>
		                </tbody>
		            </table>
                 </td>
              </tr>
            </tbody>
          </table>  		            
       </div>
    </div>
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>