//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import cn.com.bhh.erp.business.CompanyBusiness;
import cn.com.bhh.erp.common.StringUtil;
import cn.com.bhh.erp.entity.Company;
import cn.com.bhh.erp.entity.User;

import com.opensymphony.xwork2.ActionContext;

/**
 * this class has the functions such as: list,create,modify and delete company,
 * and direct to the detail company
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class CompanyAction extends BaseAction  {
    private Company company = new Company();
    private List<Company> comList = new ArrayList<Company>();
    private List<Company> agentBankCustomerLst = new ArrayList<Company>();//DB中该公司所代理的银行公司列表
    private List<Company> agentSaleCustomerList = new ArrayList<Company>();//DB中改公司代理的销售商公司列表
    private List<Integer> companyCustomerAgentList = new ArrayList<Integer>();
    private List<Integer> productCategoryAgentList = new ArrayList<Integer>();
    private List<Integer> bankCustomerList = new ArrayList<Integer>();//画面中所选取的银行公司
    private Integer bankId;
    private Integer provinceId;
    private Integer cityId;
    private Integer saleComId;
    private Integer modifyFlag=0;
    private String municipality;
    /**
     * list the company by page
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param
     * @return String
     * @throws Exception
     */
    public String listCompany() throws Exception {
        
        CompanyBusiness companyBusiness = new CompanyBusiness();
        setTotalCount(companyBusiness.getCompanyCounts(company,loginUser));

        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            return INPUT;
        }

        comList = companyBusiness.getComByPage(currPage, pageSize, company,loginUser);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to the company create page, and do some handles
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createCompanyPre() throws Exception {
        company = new Company();
        //获取直辖市列表
        return SUCCESS;
    }

    /**
     * create the company
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createCompany() throws Exception {
        
       
        CompanyBusiness companyBusiness = new CompanyBusiness();
        company.setCreatorId(loginUser.getId());
        company.setModifierId(loginUser.getId());
        company.setMunicipality(getMunicipality());
        companyBusiness.createCompany(company, companyCustomerAgentList, productCategoryAgentList);

        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }
 
    
    
    public void validateCreateCompany() {
        companyValidate();
    }
    
    public void  validateModifyCompany(){
        companyValidate();
    }
    
    public void  validateModifyCompanyAsHistory(){
        companyValidate();
    }
    
    private void companyValidate(){
        List<Integer> comTypeList = company.getComTypeList();
        List<String> newErrors= new ArrayList<String>();
        if(comTypeList.size() <= 0){
            newErrors.add("BSE00001,company.typeId");
            setActionErrors(getMessageText(newErrors));
        }
       if(comTypeList.size() == 1 && comTypeList.contains(new Integer(4))){
          if(company.getBankId()==null){
              newErrors.add("BSE00001,company.bankName");
              setActionErrors(getMessageText(newErrors));
          }
          
       }else{ 
            String mainCompanyName = company.getCompanyNameShow();
            if(mainCompanyName==null || "".equals(mainCompanyName.trim())){
              newErrors.add("BSE00001,company.name");
              setActionErrors(getMessageText(newErrors));
            }else{
                 if(mainCompanyName.trim().length() >40 ){
                     newErrors.add("BSE00027,company.name,1,40");
                     setActionErrors(getMessageText(newErrors));
                 }else{
                     company.setMainCompanyName(mainCompanyName);
                 }
           }
       }
        company.setTypeId(StringUtil.arrayToString(comTypeList));

    }
    


    /**
     * direct to the modify company info page
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyCompanyPre() throws Exception {

        if (null == company.getId() || null == company.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        
        CompanyBusiness companyBusiness = new CompanyBusiness();
        Company dbCompany = companyBusiness.searchCompanyById(company,true);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        company = dbCompany;
        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * direct to the template add page
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String templateAddCompanyPre() throws Exception {
        if (null == company.getId() || null == company.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }
        CompanyBusiness companyBusiness = new CompanyBusiness();
        Company dbCompany = companyBusiness.searchCompanyById(company,false);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        company = dbCompany;
        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * modify the company
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyCompany() throws Exception {
        if (null == company.getId() || null == company.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

    
        CompanyBusiness companyBusiness = new CompanyBusiness();
        company.setMunicipality(getMunicipality());
        companyBusiness.modifyCompanyInfo(company, loginUser, companyCustomerAgentList, productCategoryAgentList);

        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * modify the company as history
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyCompanyAsHistory() throws Exception {
        if (null == company.getId() || null == company.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }
              
        CompanyBusiness companyBusiness = new CompanyBusiness();
        company.setMunicipality(getMunicipality());
        companyBusiness.modifyCompanyAsHistory(company, loginUser, companyCustomerAgentList, productCategoryAgentList);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * delete the company for temp
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String deleteCompanyForTemp() throws Exception {
        if (null == company.getId() || null == company.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }
        CompanyBusiness companyBusiness = new CompanyBusiness();
        companyBusiness.deleteCompanyForTemp(company);

        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            company.setDeleted(0);
            modifyFlag=1;
            return INPUT;
        }

        ActionContext context = ActionContext.getContext();
        Map app = context.getApplication();
        HashSet onlineUsers = (HashSet) app.get(UserAction.ONLINE_USERS);

        if (onlineUsers != null) {
            Iterator it = onlineUsers.iterator();
            while (it.hasNext()) {
                try {
                    Map session = (Map) it.next();
                    User onLineUser = (User) session.get(UserAction.USER);

                    if (onLineUser != null && company.getId() != null) {
                        if (onLineUser.getCompanyID().compareTo(company.getId()) == 0) {
                            it.remove();
                            session.clear();
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }

        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * recover company
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String recoverCompany() throws Exception {
        if (null == company.getId() || null == company.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }
        CompanyBusiness companyBusiness = new CompanyBusiness();
        companyBusiness.recoverCompany(company);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            company.setDeleted(1);
            modifyFlag=1;
            return INPUT;
        }
        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * direct to detail company info page
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String directToDetail() throws Exception {
        if (null == company.getId()) {
            return ILLEGAL_ERR;
        }

        CompanyBusiness companyBusiness = new CompanyBusiness();
        Company dbCompany = companyBusiness.searchCompanyById(company,false);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        //获取该公司代理的销售商列表
        agentSaleCustomerList = companyBusiness.getAgentCustomerByAgentId(company.getId(), 1);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
       
        //获取该公司代理的银行公司列表
        this.agentBankCustomerLst = companyBusiness.getAgentCustomerByAgentId(company.getId(), 0);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        
        company = dbCompany;
        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * the operation of deleting company
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String deleteCompany() throws Exception {

        if (null == company.getId() || null == company.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        CompanyBusiness companyBusiness = new CompanyBusiness();
        companyBusiness.deleteCompany(company);

        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        modifyFlag=0;
        return SUCCESS;
    }
    

    /**
     * the operation of excuting search
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String searchCompany() throws Exception {
        if(company.getTypeId()!=null && !"".equals(company.getTypeId())){
            company.setTypeId(","+company.getTypeId()+",");
        }
        return SUCCESS;
    }

    /**
     * direct to the company search page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String searchCompanyPre() throws Exception {
        return SUCCESS;
    }

    /**
     * direct to customer edit page.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String companyCustomerEditPre() throws Exception {
        if (null == company.getId() || null == company.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }
        CompanyBusiness companyBusiness = new CompanyBusiness();
        Company dbCompany = companyBusiness.searchCompanyById(company,true);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            return INPUT;
        }
        company = dbCompany;
        agentBankCustomerLst = companyBusiness.getAgentCustomerByAgentId(company.getId(),0);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            return INPUT;
        }
        
        return SUCCESS;
    }
    /**
     * 根据画面银行，省份，城市下拉框的选择条件从DB中返回相应
     * 银行数据到画面中。
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
     public String appendBank() throws Exception {
         if(company.getBankId()==null){
             List<String> newErrors= new ArrayList<String>();
             newErrors.add("BSE00001,company.bankName");
             setActionMessages(getMessageText(newErrors));
             return INPUT;
         }
         CompanyBusiness companyBusiness = new CompanyBusiness();
         Integer selectedProvinceId = company.getProvinceId();
         Integer selectedCityId = company.getCityId();
         List<Company> bankCustomers = companyBusiness.getCompanyCustomer(bankId,selectedProvinceId,selectedCityId,saleComId);
         
      
         List<Integer> newBankCustomerList = new ArrayList<Integer>();
         if(bankCustomerList!=null && bankCustomerList.size()>0){
             newBankCustomerList.addAll(bankCustomerList);
             for(Company bankCustomer:bankCustomers){
                 Integer dbBankCusomerId = bankCustomer.getId();
                 //重复的客户公司不加入
                 if(!bankCustomerList.contains(dbBankCusomerId)){
                     newBankCustomerList.add(dbBankCusomerId);
                 }
             }
         }else{
             for(Company bankCustomer:bankCustomers){
                 newBankCustomerList.add(bankCustomer.getId());
             }
         }
         
         companyBusiness.editCompanyBankCustomer(company.getId(), newBankCustomerList);
         if (companyBusiness.hasErrors()) {
             setActionMessages(getMessageText(companyBusiness.getErrors()));
             return INPUT;
         }
       
         return SUCCESS;
     }
     
   /**
    * 根据画面银行，省份，城市下拉框的选择条件从DB中返回相应
    * 银行数据到画面中。
    * @auther  xiangzq
    * @version 1.0
    * @since   1.0
    * @return  String
    * @throws  Exception
    */
//    public String appendBanks() throws Exception {
//        
//        HttpServletResponse response = ServletActionContext.getResponse();
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("UTF-8");
//        PrintWriter writer = response.getWriter();
//        CompanyBusiness companyBusiness = new CompanyBusiness();
//        List<Company> bankCustomers = companyBusiness.getCompanyCustomer(bankId,provinceId,cityId,saleComId);
//        int bankSize = bankCustomers.size();
//        StringBuffer jsonData = new StringBuffer();
//        for(int i = 0;i<bankSize;i++){
//            Company bankCom = bankCustomers.get(i);
//            if(i==0 && i == bankSize-1){
//                jsonData.append("{"+bankCom.getId()+":\""+bankCom.getShortName()+"\"}");
//            }else{
//                if(i == 0){
//                    jsonData.append("{"+bankCom.getId()+":\""+bankCom.getShortName()+"\",");
//                }else if(i == bankSize-1){
//                    jsonData.append(bankCom.getId()+":\""+bankCom.getShortName()+"\"}");
//                }else{
//                    jsonData.append(bankCom.getId()+":\""+bankCom.getShortName()+"\",");
//                }
//                
//            }
//        }
//        writer.write(jsonData.toString());
//        return null;
//    }
    
    /**
     * 点击保存时，将画面中表中的银行数据，加入公司客户关系表中
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String companyCustomerDelete() throws Exception {
        if (null == company.getId()) {
            return ILLEGAL_ERR;
        }
        CompanyBusiness companyBusiness = new CompanyBusiness();

        companyBusiness.editCompanyBankCustomer(company.getId(), bankCustomerList);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            return INPUT;
        }
        
        return SUCCESS;
    }
    
    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return the comList
     */
    public List<Company> getComList() {
        return comList;
    }

    /**
     * @param comList the comList to set
     */
    public void setComList(List<Company> comList) {
        this.comList = comList;
    }


    /**
     * @return the productCategoryAgentList
     */
    public List<Integer> getProductCategoryAgentList() {
        return productCategoryAgentList;
    }

    /**
     * @param productCategoryAgentList the productCategoryAgentList to set
     */
    public void setProductCategoryAgentList(List<Integer> productCategoryAgentList) {
        this.productCategoryAgentList = productCategoryAgentList;
    }

    /**
     * @return the companyCustomerAgentList
     */
    public List<Integer> getCompanyCustomerAgentList() {
        return companyCustomerAgentList;
    }

    /**
     * @param companyCustomerAgentList the companyCustomerAgentList to set
     */
    public void setCompanyCustomerAgentList(List<Integer> companyCustomerAgentList) {
        this.companyCustomerAgentList = companyCustomerAgentList;
    }
    
    /**
     * @return the agentSaleCustomerList
     */
    public List<Company> getAgentSaleCustomerList() {
        return agentSaleCustomerList;
    }

    /**
     * @param agentSaleCustomerList the agentSaleCustomerList to set
     */
    public void setAgentSaleCustomerList(List<Company> agentSaleCustomerList) {
        this.agentSaleCustomerList = agentSaleCustomerList;
    }

    /**
     * @return the bankCustomerList
     */
    public List<Integer> getBankCustomerList() {
        return bankCustomerList;
    }

    /**
     * @param bankCustomerList the bankCustomerList to set
     */
    public void setBankCustomerList(List<Integer> bankCustomerList) {
        this.bankCustomerList = bankCustomerList;
    }

    
    /**
     * @return the agentBankCustomerLst
     */
    public List<Company> getAgentBankCustomerLst() {
        return agentBankCustomerLst;
    }

    /**
     * @param agentBankCustomerLst the agentBankCustomerLst to set
     */
    public void setAgentBankCustomerLst(List<Company> agentBankCustomerLst) {
        this.agentBankCustomerLst = agentBankCustomerLst;
    }

    /**
     * @return the modifyFlag
     */
    public Integer getModifyFlag() {
        return modifyFlag;
    }

    /**
     * @param modifyFlag the modifyFlag to set
     */
    public void setModifyFlag(Integer modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    /**
     * @return the bankId
     */
    public Integer getBankId() {
        return bankId;
    }

    /**
     * @param bankId the bankId to set
     */
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    /**
     * @return the provinceId
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * @param provinceId the provinceId to set
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * @return the cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * @param cityId the cityId to set
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * @return the saleComId
     */
    public Integer getSaleComId() {
        return saleComId;
    }

    /**
     * @param saleComId the saleComId to set
     */
    public void setSaleComId(Integer saleComId) {
        this.saleComId = saleComId;
    }

    /**
     * @return the municipality
     */
    public String getMunicipality() {
        municipality = getText("municipality");
        return municipality;
    }

    /**
     * @param municipality the municipality to set
     */
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }


}
