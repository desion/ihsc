<#if ((actionMessages?exists && actionMessages?size > 0) || (actionErrors?exists && actionErrors?size > 0) || (fieldErrors?exists && fieldErrors?size > 0))  >
<div id="errDiv" >
  <div id="err_title"> <@s.text name="error_info" /></div>
  <div id="err_con">
   <!--[if lte IE 6.5]><iframe></iframe><![endif]-->
      <@s.actionmessage />
       <@s.fielderror />
       <@s.actionerror />
  </div>
  <div class="btn_row" style="margin:5px auto; text-align:center">
    <button type="button" style="width:55px;" onClick="unVisible()" >
      <@s.text name="btn_ok" />
    </button>
  </div>
</div>    
</#if>