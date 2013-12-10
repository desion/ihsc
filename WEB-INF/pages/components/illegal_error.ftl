<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="illegal_error_label" /></title>
<#include "../common_header.ftl"/>
</head>
<body>
<#include "../components/error_reference.ftl"/>
<div id="container">
    <#include "../header.ftl"/>
    <div style="width:100%;height:20%;font-size:40px;font-weight:bold ;color:red ;margin:100px auto" align="center">
        <label><@s.text name="illegal_error" /></label>
    </div>
    <#include "../footer.ftl"/>
</div>
</body>
</html>