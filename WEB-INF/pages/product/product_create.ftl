<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><@s.text name="create.product.label.title" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
function changeAction(url) {
    var flag= confirm("<@s.text name="BSC00006" />");
    if(flag==true){
      var form=document.getElementById("mdfForm");
      form.action=url;
      form.submit();  
    }
}
</script>
</head>
<#include "../components/error_reference.ftl"/>
<body>
<#escape x as x?html>
<div id="container">
  <#include "../header.ftl"/>
   <div id="view" >
     <div class="fields">
      <form id="mdfForm" action="PR002_20" method="post">
        <div>
            <table>
                <#noescape>
                    <td><@s.text name="create.product.label.productCategory" />${navigationString!""}</td>
                </#noescape>
            </table>
        </div>
       
        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
           <tbody>
              <tr>
                <td>           
                    <table width="70%" align="center" class="field_tbl">
                        <tbody>
                            <tr>
                                <td width="20%" class="lcell"><@s.text name="create.product.label.model" /></td>
                                <td width="80%">
                                    <input type="text" name="product.model" value="${product.model!""}" maxLength="30" size="30"/>
                                    &nbsp;<label class="needed"><@s.text name="input_needed" /></label>
                                </td>
                            </tr>
                            <tr>
                                <td width="20%" class="lcell"><@s.text name="create.product.label.producer" /></td>
                                <td width="80%">
                                    <select id="string_producer_id" name="string_producer_id">
                                        <@s.action name="manufacturer_drop" executeResult="true" ignoreContextParams="true">
                                            <@s.param name="selectedComId">${string_producer_id!""}</@s.param>
                                        </@s.action>
                                    </select>
                                    &nbsp;<label class="needed"><@s.text name="select_needed" /></label>
                                </td>
                            </tr>
                            <tr>
                                <td width="20%" class="lcell"><@s.text name="create.product.label.category" /></td>
                                <td width="80%">
                                    ${product.productCategoryName!"&nbsp;"}
                                    <input type="hidden" name="product.productCategoryName" value="${product.productCategoryName!""}">
                                    <input type="hidden" name="product.productCategoryId" value="${product.productCategoryId!""}">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                  </td>
                </tr>
            </tbody>
           </table>                     
            <br>
            
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                    <td>              
                        <table width="70%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.operationType" /></td>
                                    <td width="80%">
                                        <select id="strOperationType" name="strOperationType">
                                            <@s.action name="operationType_drop" executeResult="true" ignoreContextParams="true">
                                                <@s.param name="selectedOperationTypeId">${strOperationType!""}</@s.param>
                                            </@s.action>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                      </td>
                    </tr>
                 </tbody>
               </table>                 
            <br>
            
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                    <td>              
                        <table width="70%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.length" /></td>
                                    <td width="80%"><input type="text" name="product.length" value="${product.length!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.width" /></td>
                                    <td width="80%"><input type="text" name="product.width" value="${product.width!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.height" /></td>
                                    <td width="80%"><input type="text" name="product.height" value="${product.height!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.weight" /></td>
                                    <td width="80%"><input type="text" name="product.weight" value="${product.weight!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.guardLevel" /></td>
                                    <td width="80%"><input type="text" name="product.guardLevel" value="${product.guardLevel!""}" maxLength="80" size="80"/></td>
                                </tr>
                            </tbody>
                        </table>
                      </td>
                    </tr>
                 </tbody>
               </table>                         
            <br>
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                    <td>              
                        <table width="70%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.runtimeTemp" /></td>
                                    <td width="80%"><input type="text" name="product.runtimeTemp" value="${product.runtimeTemp!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.standbyTemp" /></td>
                                    <td width="80%"><input type="text" name="product.standbyTemp" value="${product.standbyTemp!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.humidity" /></td>
                                    <td width="80%"><input type="text" name="product.humidity" value="${product.humidity!""}" maxLength="80" size="80"/></td>
                                </tr>
                            </tbody>
                        </table>
                      </td>
                    </tr>
                 </tbody>
               </table>                         
            <br>
            
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                    <td>               
                        <table width="70%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.voltage" /></td>
                                    <td width="80%"><input type="text" name="product.voltage" value="${product.runtimeTemp!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.frequency" /></td>
                                    <td width="80%"><input type="text" name="product.frequency" value="${product.frequency!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.waveFrequency" /></td>
                                    <td width="80%"><input type="text" name="product.waveFrequency" value="${product.waveFrequency!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.grounding" /></td>
                                    <td width="80%"><input type="text" name="product.grounding" value="${product.grounding!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.impactCurrent" /></td>
                                    <td width="80%"><input type="text" name="product.impactCurrent" value="${product.impactCurrent!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.runtimePower" /></td>
                                    <td width="80%"><input type="text" name="product.runtimePower" value="${product.runtimePower!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.standbyPower" /></td>
                                    <td width="80%"><input type="text" name="product.standbyPower" value="${product.standbyPower!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.lightPower" /></td>
                                    <td width="80%"><input type="text" name="product.lightPower" value="${product.lightPower!""}" maxLength="80" size="80"/></td>
                                </tr>
                            </tbody>
                        </table>
                      </td>
                    </tr>
                 </tbody>
              </table>                      
            <br>
            
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                    <td>              
                        <table width="70%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.runtimeHeat" /></td>
                                    <td width="80%"><input type="text" name="product.runtimeHeat" value="${product.runtimeHeat!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.standbyHeat" /></td>
                                    <td width="80%"><input type="text" name="product.standbyHeat" value="${product.standbyHeat!""}" maxLength="80" size="80"/></td>
                                </tr>
                            </tbody>
                        </table>
                      </td>
                    </tr>
                 </tbody>
             </table>                       
            <br>
            
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                    <td>              
                        <table width="70%" align="center" class="field_tbl">
                            <tbody>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.customerLcd" /></td>
                                    <td width="80%"><input type="text" name="product.customerLcd" value="${product.customerLcd!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.customerInput1" /></td>
                                    <td width="80%"><input type="text" name="product.customerInput1" value="${product.customerInput1!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.customerInput2" /></td>
                                    <td width="80%"><input type="text" name="product.customerInput2" value="${product.customerInput2!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.customerInput3" /></td>
                                    <td width="80%"><input type="text" name="product.customerInput3" value="${product.customerInput3!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.customerInput4" /></td>
                                    <td width="80%"><input type="text" name="product.customerInput4" value="${product.customerInput4!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.customerInput5" /></td>
                                    <td width="80%"><input type="text" name="product.customerInput5" value="${product.customerInput5!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.customerLoudspeaker" /></td>
                                    <td width="80%">
                                        <#if (customerLoudspeakerSel?? && customerLoudspeakerSel == "1")>
                                            <input type="radio" id="radio_cl" name="customerLoudspeakerSel" value="1" checked/><@s.text name="create.product.label.customerLoudspeaker.has" />
                                            <input type="radio" id="radio_cl" name="customerLoudspeakerSel" value="0"/><@s.text name="create.product.label.customerLoudspeaker.hasnot" />
                                        <#elseif (customerLoudspeakerSel?? && customerLoudspeakerSel == "0")>
                                            <input type="radio" id="radio_cl" name="customerLoudspeakerSel" value="1"/><@s.text name="create.product.label.customerLoudspeaker.has" />
                                            <input type="radio" id="radio_cl" name="customerLoudspeakerSel" value="0" checked/><@s.text name="create.product.label.customerLoudspeaker.hasnot" />
                                        <#else>
                                            <input type="radio" id="radio_cl" name="customerLoudspeakerSel" value="1"/><@s.text name="create.product.label.customerLoudspeaker.has" />
                                            <input type="radio" id="radio_cl" name="customerLoudspeakerSel" value="0"/><@s.text name="create.product.label.customerLoudspeaker.hasnot" />
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.customerVoiceWizard" /></td>
                                    <td width="80%">
                                        <#if (customerVoiceWizardSel?? && customerVoiceWizardSel == "1")>
                                            <input type="radio" id="radio_cvw" name="customerVoiceWizardSel" value="1" checked/><@s.text name="create.product.label.customerVoiceWizard.has" />
                                            <input type="radio" id="radio_cvw" name="customerVoiceWizardSel" value="0"/><@s.text name="create.product.label.customerVoiceWizard.hasnot" />
                                        <#elseif (customerVoiceWizardSel?? && customerVoiceWizardSel == "0")>
                                            <input type="radio" id="radio_cvw" name="customerVoiceWizardSel" value="1"/><@s.text name="create.product.label.customerVoiceWizard.has" />
                                            <input type="radio" id="radio_cvw" name="customerVoiceWizardSel" value="0" checked/><@s.text name="create.product.label.customerVoiceWizard.hasnot" />
                                        <#else>
                                            <input type="radio" id="radio_cvw" name="customerVoiceWizardSel" value="1"/><@s.text name="create.product.label.customerVoiceWizard.has" />
                                            <input type="radio" id="radio_cvw" name="customerVoiceWizardSel" value="0"/><@s.text name="create.product.label.customerVoiceWizard.hasnot" />
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.administratorLcd" /></td>
                                    <td width="80%"><input type="text" name="product.administratorLcd" value="${product.administratorLcd!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.administratorInput" /></td>
                                    <td width="80%"><input type="text" name="product.administratorInput" value="${product.administratorInput!""}" maxLength="80" size="80"/></td>
                                </tr>
                                <tr>
                                    <td width="20%" class="lcell"><@s.text name="create.product.label.operationSystem" /></td>
                                    <td width="80%"><input type="text" name="product.operationSystem" value="${product.operationSystem!""}" maxLength="80" size="80"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                  </tr>
               </tbody>
             </table> 
            <input type="hidden" id="actionForwardP" name="actionForwardP" value="${actionForwardP!""}"/>
            <input type="hidden" name="navigationString" value="${navigationString!""}"/>
            <input type="hidden" name="productCategoryId" value="${productCategoryId!""}"/>          
        </form>                                 
       <div class="btn_row">
         <#if loginUser.hasPermission("PR002_21")>
            <button type="button" onClick="changeAction('PR002_21')"><@s.text name="btn_add" /></button>
         </#if> 
       </div>
    </div>
   </div>  
    <#include "../footer.ftl"/>
</div>
</#escape>
</body>
</html>