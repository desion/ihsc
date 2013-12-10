function openTimeWin(timeName){
    
    $('#timeName').val(timeName);
    var topOffset = $("#"+timeName).offset().top;
    $('#light').css("top",topOffset);
    var timeVal = $("#"+timeName).val();
    if(timeVal!="undefined" && timeVal!=""){
        var hour="";
        var minute="";
        if(timeVal.length==4){
           hour=timeVal.substring(0,2);
           minute=timeVal.substring(2,4);
        }else if(timeVal.length==5){
           hour=timeVal.substring(0,2);
           minute=timeVal.substring(3,5);
        }else{
           hour="00";
           minute="00";
        }
        $("#hour").val(hour);
        $("#minute").val(minute);
    }else{
        $("#hour").val("00");
        $("#minute").val("00");
    }
    
    $("#light").fadeIn("slow");  
    $("#fade").fadeIn("slow");  
  }
  
  function closeTimeWin(){
    $("#light").fadeOut("slow");  
    $("#fade").fadeOut("slow");  
  }
  
  function setTime(){
    var timeVal = $("#hour").val()+":"+$("#minute").val();
    var timeName = $("#timeName").val();
    $("#"+timeName).val(timeVal);
    closeTimeWin();
  }
  