<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="user.login" /></title>
<#include "common_header.ftl"/>
<script src="js/common/html5.js"></script>
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
<body onkeydown="enterKeyDown(event,'BS008_01')" >
<div id="loginbg">
	<img src="images/login_bg.jpg"/> 
</div>
<#escape x as x!""?html>
 <div id="container_small">
    <div class="wrap">
        <form name="loginForm" id="loginForm"  method="post" action="">
           <section class="loginForm">
               <header>
                  <h1><@s.text name="user_login_label" /></h1>
               </header>
            <fieldset>
              <lable class="loginFormlabel"><@s.text name="user.name" /></lable>
              <div class="inputWrap">
                  <input style="background:url(images/user.png) 4px 5px no-repeat" type="text" name="user.name" id="user.name" value="${user.name}" placeholder="<@s.text name="user.name" />" autofocus required>
               </div>
               <lable class="loginFormlabel"><@s.text name="user.passwordLabel" /></lable>
               <div class="inputWrap">
                   <input style="background:url(images/passwd.png) 4px 5px no-repeat" type="password" name="user.password" placeholder="<@s.text name="user.passwordLabel" />" required>
               </div>
               <lable class="loginFormlabel"><@s.text name="user.validLabel" /></lable>
               <div class="inputWrap_yz" >
                   <input style="width:170px; background:url(images/passwd.png) 4px 5px no-repeat" type="text" name="user.valid" placeholder="<@s.text name="user.validLabel" />" >
               </div>
            </fieldset>
            <fieldset>
            <a href="#"><@s.text name="user.registLabel" /></a>
                 <input type="submit" value="<@s.text name="valid" />" >
                 <input type="submit" value="<@s.text name="login" />" onClick="changeAction('BS008_01')"></input>
                 
            </fieldset>
            <input type="hidden" name="goTo" value="${Request.goTo!""}" />
          </section>
          <div align="center" class="error">
          <@s.fielderror />
          <@s.actionerror />
        </form>
         
    </div>
  </div>
</#escape>
</body>