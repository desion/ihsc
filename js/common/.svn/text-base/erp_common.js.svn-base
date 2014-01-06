 function changeAction(url,formId)
 {    
     $("#"+formId+":first").attr("action",url).submit(); 
 }
 
  function executeAction(url,formId,radName,objId,exlusiveKey){
       var exclusiveKey=$(":radio[name="+radName+"]:checked:eq(0)").val();
       var objId=$(":radio[name="+radName+"]:checked:eq(0)").attr("id");
        $("#"+objId).val(objId);
        $("#"+exlusiveKey).val(exclusiveKey);
       changeAction(url,formId);
   }
   
   function deleteData(url,fromId,errMsg){
       var flag= confirm(errMsg);
       if(flag==true){
          executeAction(url,formId);
        }
   }
   
   function setCheckboxDisabled(){
     $(":checkbox").each(function(){
          $(this).click(function(){
              if($(this).attr("checked")==true){
                  $(this).removeAttr("checked");
              }else{
                  $(this).attr("checked","checked");
              }
            });
        });
   }
   
   
  function setSelectAllCbxChecked(selectAllCbxId,cbxName){
      var flag=true;
      $("input[type=checkbox][name="+cbxName+"]").each(function(){
           if($(this).attr("checked")==false){
              flag=false;
              return false;
           }
      }); 
      if(flag==true){
         $("#"+selectAllCbxId).attr("checked","checked");
      }
      
   }
   
   function setCheckBoxChecked(selectAllCbxId,cbxName){
      var selectAllId="#"+selectAllCbxId;
      var cbxFilter="input[type=checkbox][name="+cbxName+"]";
      $(selectAllId).click(function(){
            if($(this).attr("checked")==true){
               $(cbxFilter).attr("checked","checked");
            }else{
                $(cbxFilter).removeAttr("checked");
            }
      });
          
      $(cbxFilter).each(function(){
           $(this).click(function(){
              if($(this).attr("checked")==false){
                 $(selectAllId).removeAttr("checked");
              }else{
                 if(checkOthers(this,cbxName)){
                    $(selectAllId).attr("checked","checked")
                 }
              }
           });
       });
   }
   
 
   function checkOthers(self,cbxName){
      var flag=true;
      $("input[type=checkbox][name="+cbxName+"]").each(function(){
           if($(this)!=self && $(this).attr("checked")==false){
              flag=false;
              return false;
           }
      });
      return flag;
   }