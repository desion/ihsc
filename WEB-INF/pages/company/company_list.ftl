<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="company.listInfo" /></title>
<#include "../common_header.ftl"/>
<script type="text/javascript">
   
   function changeAction(url,type)
   {
      var form=document.getElementById("mdfForm");
      var currentPage=document.getElementById("currPage");
     if(type!='undefined')
     {
          switch(type)
          {
            case "first":
                 currentPage.value=1;
                 break;
            case "pre":
                 currentPage.value=${currPage-1};
                 break;
            case "next":
                 currentPage.value=${currPage+1};
                 break;
            case "last":
                 currentPage.value=${totalPages};
          }
    }
    
      form.action=url;
      form.submit();
      
   } 

   function executeAction(url){
       var exclusiveKey=$(":radio[name=rad]:checked:eq(0)").val();
       var objId=$(":radio[name=rad]:checked:eq(0)").attr("id");
        $("#ObjId").val(objId);
        $("#exclusiveKey").val(exclusiveKey);
       changeAction(url);
   }
   
    function addAction(url){
        $("#ObjId").val("");
        $("#exclusiveKey").val("");
       changeAction(url);
   }
    
   $(document).ready(function(){
        var sort = $("#sort").val();
        var sortType = $("#sortType").val();
        if(sort != "" && sortType != ""){
            var qText = "a[name='sortName'][value='"+sort+"']";
            if(sortType == "asc"){
              $(qText).append("<img src='../../../images/orderup.png'></img>");
            }else if(sortType == "desc"){
              $(qText).append("<img src='../../../images/orderdown.png'></img>");
            } 
        }
        
        $("a[name='sortName']").each(function(){   
          var sortTypeIn=$("#sortType").val();
            $(this).click(function(){
               if(sortTypeIn=="desc"){
                   $("#sortType").val("asc");
               }else if(sortTypeIn == "asc"){
                   $("#sortType").val("desc");
               }else{
                   $("#sortType").val("asc");
               }
                $("#sort").val($(this).attr("value"));
                changeAction("BS005_10");
            });

        });
        
   $("#pageSizeSel").change(function(){
        $("#psize").val($(this).val());
        changeAction("BS005_10");
    });
    
    
    $("#toPageSel").change(function(){
        $("#currPage").val($(this).val());
        changeAction("BS005_10");
    });

   
    var radios = $("input[type=radio][name=rad]");
    var radioSize= radios.length;
    
    var btnDetail=$("#btnDetail");
    var btnDelete=$("#btnDelete");
    if(radioSize>0){
        btnDetail.removeAttr("disabled");
        btnDelete.removeAttr("disabled");
        var firstRad=$(":radio[name=rad]:eq(0)")
        firstRad.attr("checked",true);
    }
    else{
        btnDetail.attr("disabled","disabled");
        btnDelete.attr("disabled","disabled");
    } 
    
    <#if !fieldErrors.isEmpty() || !actionMessages.isEmpty() || !actionErrors.isEmpty()   >
        $("#" + ${company.id!-1}).attr("checked", "checked");
    </#if>
   });
   
</script>
</head>
<body >
<#escape x as x?html>
<#include "../components/error_reference.ftl"/> 
<div id="container">
<#include "../header.ftl"/>   
  <div id="view">      
     <div class="gridview" >
         <#assign pagebarAction="BS005_10">
         <#include "../components/pagebar.ftl">
         <div>
            <table cellspacing="0" cellpadding="0" border="0" class="table_line2">
               <tbody>
                  <tr>
                    <td>           
                        <table class="datalist" width="100%" cellspacing="0" cellpadding="0" >
                         <tr>
                            <th width="6%" ><label/></th>
                            <th width="22%"><a name="sortName" value="comName"><@s.text name="company.nameLbl" /></a></th>
                            <th width="19%"><a name="sortName" value="comShortName"><@s.text name="company.shortName" /></a></th>
                            <th width="9%"><a name="sortName" value="cityName"><@s.text name="company.cityName" /></a></th>
                            <th width="13%"><@s.text name="company.tel" /></th>
                            <#if loginUser.hasPermission("BS005_33")>              
                              <th width="22%"><a name="sortName" value="comTypeName"><@s.text name="company.companyTypeName" /></a></th>
                              <th width="9%"><a name="sortName" value="comDeleted"><@s.text name="company.deleted" /></a></th>
                            <#else>
                               <th width="31%"><a name="sortName" value="comTypeName"><@s.text name="company.companyTypeName" /></a></th>
                            </#if>  
                         </tr>
                         <#list comList as com>
                             <tr>
                                <td><input type="radio" id="${com.id}" name="rad" value="${com.exclusiveKey}"  /></td>
                                <td><label>${com.mainCompanyName!""}${com.subCompanyName!""}</label></td>
                                <td><label>${com.shortName!""}</label></td>
                                <td><label>${com.cityName!""}</label></td>
                                <td><label>${com.tel1!""}</label></td>
                                <td><label>${com.companyTypeName!""}</label></td>
                                <#if loginUser.hasPermission("BS005_33")>              
                                   <#if com.deleted==1>
                                      <td><label class="needed"><@s.text name="deleted" /></label></td>
                                   <#else>
                                      <td><label/></td>
                                   </#if>
                                </#if>
                             </tr>
                        </#list>
                     </table>
                    </td>
                  </tr>
               </tbody>
              </table>                   
        </div>
        <div class="btn_row">
          <#if loginUser.hasPermission("BS005_20")>
            <button type="button" onClick="addAction('BS005_20')" ><@s.text name="btn_add" /></button>
          </#if>
          <#if loginUser.hasPermission("BS005_12")>
            <button type="button" id="btnDetail" onClick="executeAction('BS005_12')"  ><@s.text name="btn_detail" /></button>
          </#if>
        </div>
         
        <form id="mdfForm" action="" method="post">
            <input type="hidden" id="ObjId" name="company.id" />
            <input type="hidden" id="exclusiveKey" name="company.exclusiveKey" />
            <input type="hidden" id="currPage" name="currPage" value="${currPage!0}" />
            <input type="hidden"  id="psize"   name="pageSize" value="${pageSize!0}" />
            <input type="hidden"  name="company.mainCompanyName" value="${company.mainCompanyName!""}"/>
            <input type="hidden"  name="company.subCompanyName" value="${company.subCompanyName!""}"/>
            <input type="hidden"  name="company.shortName" value="${company.shortName!""}"/>
            <input type="hidden"  name="company.typeId" value="${company.typeId!""}" />
            <input type="hidden"  name="company.cityId" value="${company.cityId!""}" />
            <input type="hidden"  name="company.provinceId" value="${company.provinceId!""}" />
            <input type="hidden"  name="company.code" value="${company.code!""}" />
            <input id="sort"     type="hidden" name="company.sort" value="${company.sort!""}"/ >
            <input id="sortType" type="hidden" name="company.sortType" value="${company.sortType!""}"/ >
        </form>                     
     </div> 
    </div> 
   <#include "../footer.ftl"/>  
 </div>     
</#escape>
</body>
</html>