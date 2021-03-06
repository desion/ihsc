<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="user.login" /></title>
<#include "common_header.ftl"/>
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
</head>
<body onkeydown="enterKeyDown(event,'BS008_01')">
<#escape x as x!""?html>
 <div id="container_small">
	<div id="head">
	    <div id="head_l">
	        <li><a href="<@s.text name="hitache_in_japan_url" />"><@s.text name="hitache_in_japan" /> </a></li>
	        <li><a href="<@s.text name="hitache_in_china_url" />"><@s.text name="hitache_in_china" /> </a></li>
	        <li><a href="<@s.text name="ihsc_label_url" />"><@s.text name="ihsc_label" /> </a></li>
	     </div>
	</div>
	<div id="logo">
	    <ul>
	         <li id="self_logo"><img src="/images/sitename.jpg" /></li>
	         <li id="hitachi_logo"><img src="/images/inspirelogo.png" /></li>
	    </ul>
	</div>    
    <div id="login_img">
            <img src="/images/login_bj1.jpg" alt="" width="765" height="168" />
    </div>
    <div id="login_title"><@s.text name="user_login_label" /></div>
    <div id="login_con_small">
	    <div class="box">
	      <form name="loginForm" id="loginForm" method="post" action="">
	        <div class="loginform_row">
	             <label><@s.text name="user.name" /></label>
                 <input type="text" name="user.name" maxlength="20" value="${user.name}" />
	        </div>
	        <div class="loginform_row">
                <label><@s.text name="user.passwordLabel" /></label>
                <input type="password" name="user.password" maxlength="20" value="" />
	       </div>
	       <div class="loginform_row" style="text-align:center;">
	           <button type="button" onClick="changeAction('BS008_01')"><@s.text name="login" /></button>
	       </div>
	           <input type="hidden" name="goTo" value="${Request.goTo!""}" />
	       <div align="center" class="error">
             <@s.fielderror />
              <@s.actionerror />
           </div>    
	     </form>
	   </div>	
	</div>
    <#include "footer.ftl"/> 
  </div>
</#escape>
</body>