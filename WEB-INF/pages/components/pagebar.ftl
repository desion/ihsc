<div class="pagerbar" >
               <div style="float:left;">
                 <#if (currPage>1) >
                   <div>
                     <button type="button" onClick="changeAction('${pagebarAction}','first')" ><@s.text name="first_page" /></button>
                   </div>  
                   <div>
                     <button type="button" onClick="changeAction('${pagebarAction}','pre')" ><@s.text name="pre_page" /></button>
                   </div>   
                 <#else>
                   <div>
                     <button type="button" onClick="changeAction('${pagebarAction}','first')" disabled="disabled" ><@s.text name="first_page" /></button>
                   </div>  
                   <div>  
                     <button type="button" onClick="changeAction('${pagebarAction}','pre')" disabled="disabled" ><@s.text name="pre_page" /></button>
                   </div>   
                 </#if>
                 <#if (currPage<totalPages) >  
                   <div>
                     <button type="button" onClick="changeAction('${pagebarAction}','next')" ><@s.text name="next_page" /></button>
                   </div>  
                   <div>  
                     <button type="button" onClick="changeAction('${pagebarAction}','last')"  ><@s.text name="last_page" /></button>
                   </div>  
                  <#else>
                   <div>
                     <button type="button" onClick="changeAction('${pagebarAction}','next')" disabled="disabled"><@s.text name="next_page" /></button>
                   </div>  
                   <div>
                     <button type="button" onClick="changeAction('${pagebarAction}','last')" disabled="disabled"><@s.text name="last_page" /></button>
                   </div>  
                  </#if>  
                    <div> 
                     <select id="toPageSel">
                        <#if currPage??>
                        <#list 1..totalPages as page>
                           <#if page==currPage >
                              <option value="${page}" selected>${page}</option> 
                           <#else>
                              <option value="${page}" >${page}</option> 
                           </#if>
                        </#list>
                        <#else>
                            <#list 1..totalPages as page>
                                  <option value="${page}" >${page}</option> 
                            </#list>
                        </#if>
                     </select>
                     </div>
                     <div>
                      <label><@s.text name="slash" /></label>
                      <label>${totalPages!0}</label> 
                      <label><@s.text name="page" /></label>
                      <label><@s.text name="count_per_page" /></label>
                     </div>
                     <div>
                       <select id="pageSizeSel">
                        	<#if pageSize??>
                        	<#list pageSizeList as psize>
                        	   <#if psize==pageSize >
                        	      <option value="${psize}" selected>${psize}</option> 
                        	   <#else>
                        	      <option value="${psize}" >${psize}</option> 
                        	   </#if>
                        	</#list>
                        	<#else>
                        	  <#list pageSizeList as psize>
                        	      <option value="${psize}" >${psize}</option> 
                        	  </#list>
                        	</#if>
                       </select>
                     </div>
                    <div>
                     <label><@s.text name="record_count">
                        <@s.param value="${totalCount!0}" />
                      </@s.text></label>
                    </div>
               </div>   
            </div>