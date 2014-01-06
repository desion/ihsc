<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="FA001_50_title" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
    function upload(url)
    {
       var form=document.getElementById("uploadForm");
       form.action=url;
       form.submit();
    }
</script>
</head>
<body>
<#include "../components/error_reference.ftl"/>
<div id="container">
    <#include "../header.ftl"/>
    <div id="view">
        <div class="fields">
            <form id="uploadForm" method="post" enctype ="multipart/form-data">
		        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
		           <tbody>
		              <tr>
		                <td>                
			                <table width="60%" align="center" class="field_tbl" >
			                    <tbody>
			                        <br>
			                        <br>
			                        <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="import_file" /></label></td>
			                            <td width="80%">
			                                <input type="file" name="upload" size="70%" contentEditable="false" />
			                            </td>
			                        </tr>
			                    </tbody>
			                </table>
		                </td>
		              </tr>
		           </tbody>
		        </table> 			                
            </form>
           <#if loginUser.hasPermission("FA001_51")>         
            <div class="btn_row">
                <button type="button" onClick="upload('FA001_51')" ><@s.text name="btn_import" /></button>
            </div>
          </#if>  
        </div>
    </div>
    <#include "../footer.ftl"/>
</div>
</body>
</html>