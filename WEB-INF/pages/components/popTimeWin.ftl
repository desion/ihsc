<div id="light" class="white_content">
   <p align="center">
         <select id="hour">
            <#list 0..23 as h>
                <#if h<10>
                    <option value="0${h}" >0${h}</option>
                 <#else>
                    <option value="${h}" >${h}</option>
                </#if>
             </#list>          
        </select>   
        <select id="minute">
            <#list 0..59 as m>
                <#if m<10>
                    <option value="0${m}" >0${m}</option>
                 <#else>
                    <option value="${m}" >${m}</option>
                </#if>
             </#list>          
        </select>
    </p>
    <p align="center">
        <button  onclick="setTime()"><@s.text name="btn_ok" /></button>&nbsp;&nbsp;
        <button  onclick="closeTimeWin()"><@s.text name="btn_cancel" /></button>
    </p>    
 </div>
<div id="fade" class="black_overlay"></div>