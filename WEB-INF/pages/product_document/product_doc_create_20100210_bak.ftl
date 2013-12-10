<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="document.create" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
    function upload(url)
    {
       var form=document.getElementById("uploadForm");
       form.action=url;
       form.submit();
    }
    
    function changeAction(url){
      var form=document.getElementById("downloadForm");
      form.action=url;
      form.submit();
    }
    
    $(document).ready(function(){
         $("#publishDate").datepicker();
         
         $("#selectAll").click(function(){
            if($(this).attr("checked")==true){
               $("input[type=checkbox][name=companyIdList]").attr("checked","checked");
            }else{
                $("input[type=checkbox][name=companyIdList]").removeAttr("checked");
            }
          });
          
          $("input[type=checkbox][name=companyIdList]").each(function(){
               $(this).click(function(){
                  if($(this).attr("checked")==false){
                     $("#selectAll").removeAttr("checked");
                  }else{
                     if(checkOthers(this)){
                        $("#selectAll").attr("checked","checked")
                     }
                  }
               });
          });
          checkAll();
          
        $(":checkbox[name='interfaceDocProCateList']").each(function(){
           $(this).click(function(){
               checkedNode(this);
           });
         });
    });
    
    function checkedNode(self){
       var parent_id=$(self).attr("parentId");
       var self_id=$(self).attr("selfId"); 
       if(parent_id!='0'){
           //如果选中当前节点为选中状态，则选中其父节点
	       if($(self).attr("checked")==true){  
	          checkParent(self);
	       }
           //选中或取消选中所有子节点
           checkChild(self)	         
	   }else{
            // 如果当前节点为第一层节点，选中或取消选中所有子节点
           checkChild(self)      	   
	   
	   }
    }
    
    function checkParent(self){
       var parent_id=$(self).attr("parentId");
       var self_id=$(self).attr("selfId"); 
       if(parent_id!='0'){
           $(":checkbox[name='interfaceDocProCateList'][selfId='"+parent_id+"']").each(function(){
              $(this).attr("checked","checked");
               checkParent(this);        
           });
       }
    }
    
    function checkChild(self){
      var parent_id=$(self).attr("parentId");
      var self_id=$(self).attr("selfId"); 
      $(":checkbox[name='interfaceDocProCateList'][parentId='"+self_id+"']").each(function(){
          if($(self).attr("checked")==true){
             $(this).attr("checked","checked");
          }else{
             $(this).removeAttr("checked");        
          }
          checkChild(this);        
      });      
    }
  

    
      
  function checkOthers(self){
      var flag=true;
      $("input[type=checkbox][name=companyIdList]").each(function(){
           if($(this)!=self && $(this).attr("checked")==false){
              flag=false;
              return false;
           }
      });
      return flag;
   }
   
   function checkAll(){
     var allChecked = true;
      $("input[type=checkbox][name=companyIdList]").each(function(){
           if($(this).attr("checked")==false){
              allChecked=false;
              return false;
           }
      });
      
      if(allChecked == true){
        $("#selectAll").attr("checked","checked")
      }else{
         $("#selectAll").removeAttr("checked");
      }
   }
       
</script>
</head>
<body>
<#include "../components/error_reference.ftl"/>
<div id="container">
  <#include "../header.ftl"/>
   <div id="view" >
      <div class="fields">  
       <form id="uploadForm" method="post" enctype ="multipart/form-data">
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	               <tr>
	                  <td>            
			              <table width="70%" align="center" class="field_tbl" >
			                  <tbody> 
					              <tr>
					                  <td class="lcell"><label><@s.text name="document.type"  /></label></td>
					                  <td>
					                     <select id="documentTypeDrop" name="document.docTypeId" >
					                        <@s.action name="documentType_drop" executeResult="true" ignoreContextParams="true">
					                           <@s.param name="selectedDocumentTypeId">${document.docTypeId!"-1"}</@s.param>
					                        </@s.action>
					                     </select>
					                     <label class="needed"><@s.text name="select_needed" /></label>
					                  </td>
					              </tr>
					              
			                      <tr>
			                            <td width="20%" class="lcell"><label><@s.text name="document.title" /></label></td>
			                            <td width="80%">
			                               <input type="text" name="document.title"  value="${document.title!""}" size="60" maxLength="80"/>
			                                <label class="needed"><@s.text name="input_needed" /></label>
			                            </td>
			                      </tr> 
			                      
			                      <tr>
			                          <td width="20%" class="lcell"><label><@s.text name="document.summary" /></label></td>
			                          <td width="80%">
			                              <textarea type="text" name="document.summary" cols="50" rows="8">${document.summary!""}</textarea>
			                          </td>
			                      </tr> 
			                        
			                      <tr>
			                          <td width="20%" class="lcell"><label><@s.text name="document.publishDate" /></label></td>
			                          <td width="80%">
			                              <input type="text" id="publishDate" name="document.publishDate"  value="${document.publishDate!""}" size="10" maxLength="10"/>
			                          </td>
			                      </tr>  
			                      
			                      <tr>                       
			                            <td width="20%" class="lcell"><label><@s.text name="document.importFile" /></label></td>
			                            <td width="80%">
			                                <input type="file" name="docData" size="40%" contentEditable="false" />
			                                 <label class="needed"><@s.text name="select_needed" /></label>
			                            </td>
			                            <input type="hidden" name="document.productId" value="${document.productId!-1}" />
			                            <input type="hidden" name="document.id" value="${document.id!-1}" />
			                            <input type="hidden" name="document.model" value="${document.model!""}" />
			                      </tr>
			                  </tbody>
			              </table>
		               </td>
		            </tr>
		         </tbody>
		     </table> 
	        <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
	           <tbody>
	              <tr>
	                <td> 		      
			             <table  class="field_tbl" width="70%" cellspacing="0" cellpadding="0" style="margin:10px auto">
			               <tr>
			                 <th style="text-align:center"><label><@s.text name="document.productAndCategory" /></label></th>
			               </tr>
			               <tr>
			                 <td>
			                   <div style="height:300px;overflow-y:scroll;overflow-x:hidden">
			                      <table class="field_tbl" width="97%">
			                          <@s.action name="document_product_category_agent_tree" executeResult="true" ignoreContextParams="true"> 
			                          </@s.action>
			                      </table>
			                    </div>  
			                 </td>
			              </tr>   
			            </table>
                       </td>
                    </tr>
                 </tbody>
             </table> 
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                    <td>                         
		              <table  class="field_tbl" width="70%" cellspacing="0"  style="margin:10px auto">
			              <tr>
			                  <th width="10%" ><input type="checkbox" id="selectAll" /><label><@s.text name="select_all" /></label></th>
			                  <th width="90%" style="text-align:center">
			                     <label><@s.text name="document.agent" /></label>
			                  </th>
			              </tr>
			              <tr>
			                 <td colspan="2">
			                   <div style="height:300px;overflow-y:scroll;overflow-x:hidden" cellspacing="0" cellpadding="0" >
			                      <table class="field_tbl" width="97%">
			                        <@s.action name="document_company_list" executeResult="true" ignoreContextParams="true">
			                            <@s.param name="productId">${document.productId!-1}</@s.param> 
			                            <@s.param name="documentId">${document.id!-1}</@s.param>
			                        </@s.action>
			                      </table>
			                    </div>  
			                 </td>
			              </tr>   
		              </table>
                   </td>
                 </tr>
               </tbody>
           </table>  
        </form>
        <#if loginUser.hasPermission("PR006_21")>        
          <div class="btn_row">
             <button type="button" onClick="upload('PR006_21')" ><@s.text name="btn_add" /></button>
          </div>
        </#if>          
      </div>
    </div>
    <#include "../footer.ftl"/>
</div>
</body>
</html>