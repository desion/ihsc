<#if  scrollMessageList?? && (scrollMessageList?size>0)>
  <div id="scrollDiv">
   <div class="marquee_dd">
   <marquee direction="up" behavior="scroll" scrollamount="3" scrolldelay="200" align="center" height="54">
        <center>
            <#list scrollMessageList as msg>
                <pre style="color: red">${msg.content!""}</pre>
            </#list>
        </center>  
   </marquee>
   </div>
 </div>
</#if>

