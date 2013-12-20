<div id="head">
    <div id="head_l">
        <li><a href="<@s.text name="zkcl_url" />"><@s.text name="zkcl" /> </a></li>
        <li><a href="<@s.text name="zkcl_tall_url" />"><@s.text name="zkcl_tmall" /> </a></li>
     </div>
     <div id="head_r">
         <ul>
            <li>
                <a>
                   <@s.text name="welcome_msg" /> 
                    <label>${loginUser.name!""}</label>
                </a>
             </li>
            <li> <a href="BS008_02"><@s.text name="logout" /></a></li>
         </ul>
      </div>
</div>
<div id="logo">
         <img src="images/canpinzx_banner.gif" style="width:100%;"/>   
</div>
<div id="header">
     <div id="menu_header">
            <ul>
 
                <li style="background:none"><a href="BS010_00"><@s.text name="home_page" /></a>
                </li>
                <#if loginUser.hasPermission("PR003_00") || 
                     loginUser.hasPermission("PR001_10") || 
                     loginUser.hasPermission("PR005_50") || 
                     loginUser.hasPermission("PR005_70") ||
                     loginUser.hasPermission("PR005_10") ||
                     loginUser.hasPermission("PR004_10") ||
                     loginUser.hasPermission("PR005_71") ||
                     loginUser.hasPermission("PR005_72") ||
                     loginUser.hasPermission("PR005_73") ||
                     loginUser.hasPermission("PR006_10") >
                     
                    <li><label><@s.text name="product_label" /></label>
                        <ul class="select-free">
                            <!--[if lte IE 6.5]><iframe></iframe><![endif]-->
                        <#if loginUser.hasPermission("PR002_10")>
                            <li><a href="PR002_10"><@s.text name="product_search_label" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("PR001_10")>
                            <li><a href="PR001_10"><@s.text name="product_category_list_label" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("PR005_50")>
                            <li><a href="PR005_50"><@s.text name="import_use_situation_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("PR005_70")>
                            <li><a href="PR005_70"><@s.text name="explore_use_situation_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("PR005_10")>
                            <li><a href="PR005_10?install.affirmFlag=0"><@s.text name="unconfirm_use_situation_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("PR005_10")>
                            <li><a href="PR005_10"><@s.text name="all_use_situation_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("PR004_10")>
                            <li><a href="PR004_10"><@s.text name="change_apply_lis_labelt" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("PR005_71")>
                            <li><a href="PR005_71"><@s.text name="query_use_situation_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("PR005_72")>
                            <li><a href="PR005_72"><@s.text name="manufacture_no_query_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("PR005_73")>
                            <li><a href="PR005_73"><@s.text name="guarantee_query_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("PR006_10")>
                            <li><a href="PR006_10"><@s.text name="product_document_manager_label" /></a></li>       
                        </#if>
                        </ul>
                    </li>
                </#if>  
               <#if loginUser.hasPermission("FA001_70") || 
                    loginUser.hasPermission("FA001_10") || 
                    loginUser.hasPermission("FA001_71") || 
                    loginUser.hasPermission("FA001_72") ||
                    loginUser.hasPermission("FA001_50") ||
                    loginUser.hasPermission("FA001_82") ||
                    loginUser.hasPermission("BS011_10") >
                 
                    <li><label><@s.text name="fault_label" /></label>
                        <ul class="select-free">
                            <!--[if lte IE 6.5]><iframe></iframe><![endif]-->
                        <#if loginUser.hasPermission("FA001_70")>
                            <li><a href="FA001_70"><@s.text name="explore_fault_info_label" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("FA001_10")>
                            <li><a href="FA001_10?fault.repairState=0"><@s.text name="uncompleted_label" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("FA001_10")>
                            <li><a href="FA001_10?fault.supporterId=${loginUser.id!-1}&amp;fault.repairState=0"><@s.text name="self_support_uncompleted_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("FA001_71")>
                            <li><a href="FA001_71"><@s.text name="fault_query_info_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("FA001_72")>
                            <li><a href="FA001_72"><@s.text name="fault_code_query_label" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("FA001_50")>
                            <li><a href="FA001_50"><@s.text name="import_fault_info_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("FA001_82")>
                            <li><a href="FA001_82"><@s.text name="weekly_report" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("BS011_10")>
                            <li><a href="BS011_10"><@s.text name="faultPart_list_label" /></a></li>
                        </#if>
                        </ul>
                    </li>
                 </#if>   
                <#if loginUser.hasPermission("BS005_10") || 
                    loginUser.hasPermission("BS005_70") || 
                    loginUser.hasPermission("BS001_10") || 
                    loginUser.hasPermission("BS002_10") ||
                    loginUser.hasPermission("BS003_10") ||
                    loginUser.hasPermission("BS004_10") ||
                    loginUser.hasPermission("BS009_10") ||
                    loginUser.hasPermission("BS012_10") >
                     
                    <li><label><@s.text name="company_label" /></label>
                        <ul class="select-free">
                            <!--[if lte IE 6.5]><iframe></iframe><![endif]-->
                        <#if loginUser.hasPermission("BS005_10")>
                            <li><a href="/BS005_10"><@s.text name="all_company_list_label" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("BS005_70")>
                            <li><a href="/BS005_70"><@s.text name="company_query_label" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("BS001_10")>
                            <li><a href="/BS001_10"><@s.text name="company_type_list_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("BS002_10")>
                            <li><a href="/BS002_10"><@s.text name="country_list_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("BS003_10")>
                            <li><a href="/BS003_10"><@s.text name="province_list_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("BS004_10")>
                            <li><a href="/BS004_10"><@s.text name="city_list_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("BS004_70")>
                            <li><a href="/BS004_70"><@s.text name="city_query_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("BS009_10")>
                            <li><a href="/BS009_10"><@s.text name="message_list_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("BS012_10")>
                            <li><a href="/BS012_10"><@s.text name="bank_list_label" /></a></li>       
                        </#if>
                        </ul>
                    </li>
                </#if>  
                <#if loginUser.hasPermission("BS007_10") || 
                    loginUser.hasPermission("BS007_11") || 
                    loginUser.hasPermission("BS007_13") || 
                    loginUser.hasPermission("BS007_38") ||
                    loginUser.hasPermission("BS007_32") ||
                    loginUser.hasPermission("BS007_20") ||
                    loginUser.hasPermission("BS006_10") >
                    <li><label><@s.text name="user_label" /></label>
                        <ul class="select-free">
                            <!--[if lte IE 6.5]><iframe></iframe><![endif]-->
                        <#if loginUser.hasPermission("BS007_10")>
                            <li><a href="BS007_10"><@s.text name="user_list_label" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("BS007_11")>
                            <li><a href="BS007_11"><@s.text name="online_user_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("BS007_13")>
                            <li><a href="BS007_13"><@s.text name="user_query_label" /></a></li>
                        </#if>
                        <#if loginUser.hasPermission("BS007_38")>
                            <li><a href="BS007_38"><@s.text name="modify_self_info_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("BS007_32")>
                            <li><a href="BS007_32"><@s.text name="modify_password_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("BS006_10")>
                            <li><a href="BS006_10"><@s.text name="group_list_label" /></a></li>       
                        </#if>
                        <#if loginUser.hasPermission("BS007_20")>
                            <li><a href="BS007_20"><@s.text name="create_user_label" /></a></li>       
                        </#if>
                        </ul>
                    </li>
                </#if>     
            </ul>
     </div>
  </div>   
