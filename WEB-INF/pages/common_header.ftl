<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />  
<meta http-equiv="Expires" content="-1" />


<link rel="stylesheet" type="text/css" href="css/themes/base/ui.core.css" />
<link rel="stylesheet" type="text/css" href="css/themes/base/ui.theme.css" />
<link rel="stylesheet" type="text/css" href="css/themes/base/ui.datepicker.css" />
<link rel="stylesheet" type="text/css" href="css/${locale}/style.css"  />

<script type="text/javascript" src="js/jquery/jquery-1.3.1.js" ></script>
<script type="text/javascript" src="js/jquery/ui.core.js" ></script>
<script type="text/javascript" src="js/jquery/ui.datepicker.js" ></script>
<script type="text/javascript" src="js/jquery/i18n/ui.datepicker-zh-CN.js" ></script>


<script type="text/javascript">
    $(document).ready(function(){
      /**
       window.resizeTo(1024,768); 
       
       $(window).resize(function(){
          window.resizeTo(1024,768); 
       });**/
       
        $("#menu_header>ul>li").click(
            function(){$(this).children("ul").slideDown()}
        );
        $("#menu_header>ul>li").hover(
            function(){}, function(){$(this).children("ul").slideUp("fast")}
        );
        $(".datalist tr").mouseover(
            function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");}
        );
        $.datepicker.setDefaults({
            showMonthAfterYear: false,
            dateFormat: "yy/mm/dd",
            showOn: "button", 
            changeMonth: true,
            changeYear: true,
            buttonImage: "/images/calendar.gif", 
            buttonImageOnly: true
            }, $.datepicker.regional['zh-CN']);
	    $("table.datalist tr ").each(function(){
		     $(this).click(function(){
		        $(this).find("td > input[type=radio]").attr("checked","checked");
	
		       /** if($(this).find("td > input[type=checkbox]").attr("checked")==true){
		           $(this).find("td > input[type=checkbox]").removeAttr("checked");
		        }else{
		          $(this).find("td > input[type=checkbox]").attr("checked","checked");
		        }**/
		        
		     });
	    });
        setErrorFields();
    });
    function setErrorFields() {
        var errorFields = new Array (
             <#if !(fieldErrors.isEmpty())>
                <#list fieldErrors.keySet() as fieldName>
                "${fieldName}", 
                </#list>
             </#if>
                "" 
        );
        $.each(errorFields, function(i, n) {
            if (n != "") {
                $('input[name="' + n + '"]').addClass("error_field");
                $('select[name="' + n + '"]').addClass("error_field");
                $('textarea[name="' + n + '"]').addClass("error_field");
            }
        });
    }
    function unVisible() {
        $("#errDiv").hide();
    }
</script>
