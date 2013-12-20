<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><@s.text name="user.login" /></title>
<#include "common_header.ftl"/>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<script type="text/javascript">
    $(document).ready(function() {
        $('input[name="user.name"]').focus();
    });
    
    function changeAction(url) {
        $("#loginForm").attr('action', url).submit(); 
    }
    function enterKeyDown(event,url)  
    {  
     if (event.keyCode == 13)  
     {  
          event.returnValue=false;  
          event.cancel = true;  
          changeAction(url)
      }  
    }
</script>

<link rel="stylesheet" type="text/css" href="css/zh_CN/loginstyles.css" />
<link rel="stylesheet" type="text/css" href="css/zh_CN/style.css" />

<!--[if lte IE 8]>
<script src="js/common/html5.js"></script>
<![endif]-->
</head>

<body>
    <#escape x as x!""?html>
    <div class="aysw-pagewrap" style="width:1024px;margin:0 auto;">
	    <form id="form_login" name="loginForm" action="BS008_01" method="post">
	    <div class="aysw-body pt20">
		 <div class="page-login">
			  <a href="http://www.aliyun.com">
			     <h1 class="logo"></h1>
			  </a>
		 <div class="main">
		 <div class="banner">
		     <img src="images/login_index.jpg" />
		 </div>
		 <div  class="loginpanel loginpanel-novcode" >
	         <div class="inner">
				<div class="panelbody">
					<div class="header"></div>
				 <div class="body">
				  <div class="row">
					    <div class="caption"><@s.text name="user.name" /></div>
					    <div class="content">
					        <div class="textbox">
								<input autocomplete="off" type="text" name="user.name" id="user.name" value="${user.name}"/>
								<div class="bubble">
								<span class="arrow"></span>
								<span class="text"></span>
							 </div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="caption"><@s.text name="user.passwordLabel" /></div>
						<div class="content">
							<div class="textbox">
								<input id="password" type="password"  name="user.password" />
									<div class="bubble">
										<span class="arrow"></span>
										<span class="text">aa</span>
									</div>
						</div>
					</div>
				</div>
	            <div class="row buttons" id="row_btn">
					<input type="hidden" name="goTo" value="${Request.goTo!""}" />
					<input type="submit" value="<@s.text name="login" />" onClick="changeAction('BS008_01')"></input>
					<a href="/forget/forget.htm"><@s.text name="user.validLabel" /></a>
					<div class="bubble">
						<span class="arrow"></span>
						<span class="text"></span>
					</div>
				</div>
				<div align="center" class="error">
         			<@s.fielderror />
          			<@s.actionerror />
          		</div>
			</div>
			<div class="footer">
				<a href="" class="register"></a>
			</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
	<footer class="aysw-footer">
	<div class="aysw-inner">
		<div class="aysw-footer-copyright">
    <a href="http://www.bjzkcl.com/" target="_blank">
        <@s.text name="aboutus" />
    </a>
    <i>
    </i>
    <a href="http://zkclcz.tmall.com/" target="_blank">
         <@s.text name="tmalladd" />
    </a>
    <br />
    <@s.text name="copyright" />
</div>
</div>
</footer>
	</div>
	</#escape>
</body>
</html>