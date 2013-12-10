//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.bhh.erp.common.StringUtil;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.dao.AgentCustomerDao;
import cn.com.bhh.erp.dao.AgentProductDao;
import cn.com.bhh.erp.dao.BankDao;
import cn.com.bhh.erp.dao.CityDao;
import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.dao.CompanyTypeDao;
import cn.com.bhh.erp.dao.DocumentAgentDao;
import cn.com.bhh.erp.dao.FaultDao;
import cn.com.bhh.erp.dao.FaultHandleDao;
import cn.com.bhh.erp.dao.GetPK;
import cn.com.bhh.erp.dao.InstApplyDao;
import cn.com.bhh.erp.dao.InstallationDao;
import cn.com.bhh.erp.dao.InstallationHistoryDao;
import cn.com.bhh.erp.dao.MessageDao;
import cn.com.bhh.erp.dao.ProductCategoryDao;
import cn.com.bhh.erp.dao.ProductDao;
import cn.com.bhh.erp.dao.ProvinceDao;
import cn.com.bhh.erp.dao.UserDao;
import cn.com.bhh.erp.entity.AgentCustomer;
import cn.com.bhh.erp.entity.AgentProduct;
import cn.com.bhh.erp.entity.Bank;
import cn.com.bhh.erp.entity.City;
import cn.com.bhh.erp.entity.Company;
import cn.com.bhh.erp.entity.Province;
import cn.com.bhh.erp.entity.User;

/**
 * inlcude the base business of deal with the company.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class CompanyBusiness extends BaseBusiness {
    /**
     * create the company.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param com
     * @param companyCustomerAgentList
     * @param agentProductCateList
     */
    public Integer createCompany(Company com, List<Integer> companyCustomerAgentList, List<Integer> agentProductCateList) {
        Integer newCompanyId = null;
        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            com.setCode(com.getCode().trim());
            com.setShortName(com.getShortName().trim());            
            List<Integer> comTypeList = com.getComTypeList();
         
            // check whether the same code exists
            if (comDao.getCountByComCode(com) > 0) {
                errors.add("BSE00000,company.code");
                return null;
            }
            
            //check whether the company type selected is deleted
            CompanyTypeDao companyTypeDao = new CompanyTypeDao(conn);
            for(Integer companyTypeId:comTypeList){
                if (companyTypeDao.getCountByComTypeId(companyTypeId) == 0) {
                    errors.add("BSE01501");
                    return null;
                }
            }
            // check whether the short same code exists
            if (comDao.getCountByShortName(com) > 0) {
                errors.add("BSE00000,company.shortName");
                return null;
            }
    
            
            // check whether the selected province is deleted
            ProvinceDao provinceDao = new ProvinceDao(conn);
            provinceDao.setExclusiveCheck(false);
            Province argProvince = new Province();
            argProvince.setId(com.getProvinceId());
            
            Province province = provinceDao.searchProvinceById(argProvince);
            if (province == null) {
                errors.add("BSE01303");
                return null;
            }
            
            // check whether the selected city is deleted
            CityDao cityDao = new CityDao(conn);
            cityDao.setExclusiveCheck(false);
            City argCity = new City();
            argCity.setId(com.getCityId());
            City city = cityDao.searchCityById(argCity);
            if (city == null) {
                errors.add("BSE01502");
                return null;
            }
            
           //check whether the final user is selected
            if( comTypeList.size() == 1 && comTypeList.contains(new Integer(4))){
               // check whether the selected province is deleted
               BankDao bankDao = new BankDao(conn);
               Bank bank = bankDao.searchBankById(com.getBankId());
               if(bank == null){
                   errors.add("BSE01527");
                   return null;
               }
               
               String cityName = city.getName();
               String provinceName = province.getName();
               if(com.getMunicipality()!=null && com.getMunicipality().indexOf(provinceName)!=-1){
                   cityName ="";
               }
               
               String bankName = bank.getName();
               String comSubName = provinceName+cityName+"分行";
               String companyName = bankName+comSubName;
               com.setMainCompanyName(bankName);
               com.setSubCompanyName(comSubName);
               
                if (comDao.getCountByMainAndSubName(com) > 0) {
                    errors.add("BSE00000,"+companyName);
                    return null;
                }
            }else{
                com.setSubCompanyName("");
                com.setBankId(null);
                if (comDao.getCountByMainComName(com) > 0) {
                    errors.add("BSE00000,company.name");
                    return null;
                }
            }
 

            newCompanyId = new GetPK(conn).getPK("COMPANY_TBL_ID_SEQ");
            com.setId(newCompanyId);
            com.setAplyStartDate("00000000");
            com.setAplyEndDate("99999999");
            comDao.create(com);
            if(comTypeList.contains(new Integer(3))){
                AgentProductDao agentProductDao = new AgentProductDao(conn);
                ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
                for (Integer productCategoryId : agentProductCateList) {
                    if (productCategoryDao.getCountByProductCategoryId(productCategoryId) > 0) {
                        AgentProduct agentProduct = new AgentProduct();
                        agentProduct.setAgentId(newCompanyId);
                        agentProduct.setProductCategoryId(productCategoryId);
                        agentProductDao.createAgentProduct(agentProduct);
    
                    } else {
                        errors.add("BSE01503");
                        return null;
                    }
    
                }
    
                // add the customers to the agent_customer_table
                AgentCustomerDao agentCustomerDao = new AgentCustomerDao(conn);
                CompanyDao companyDao = new CompanyDao(conn);
                for (Integer customerId : companyCustomerAgentList) {
                    if (companyDao.getCountByCompanyId(customerId) > 0) {
                        AgentCustomer agentCustomer = new AgentCustomer();
                        agentCustomer.setAgentId(newCompanyId);
                        agentCustomer.setCustomerId(customerId);
                        agentCustomer.setCateFlag(1);
                        agentCustomerDao.create(agentCustomer);
                    } else {
                        errors.add("BSE01504");
                        return null;
                    }
                }
                //判断该公司是否为维修商，如果为维修商，并且又是销售商，则其销售商列表中应该加入该公司
                if(comTypeList.size()>0 && comTypeList.contains(new Integer(3)) && comTypeList.contains(new Integer(2))){
                    AgentCustomer agentCustomer = new AgentCustomer();
                    agentCustomer.setAgentId(newCompanyId);
                    agentCustomer.setCustomerId(newCompanyId);
                    agentCustomer.setCateFlag(1);
                    agentCustomerDao.create(agentCustomer);
                }
                //判断该公司是否为维修商，如果为维修商，并且又是最终用户，则其公司客户列表中应该加入该公司
                if(comTypeList.size()>0 && comTypeList.contains(new Integer(3)) && comTypeList.contains(new Integer(4))){
                    AgentCustomer agentCustomer = new AgentCustomer();
                    agentCustomer.setAgentId(newCompanyId);
                    agentCustomer.setCustomerId(newCompanyId);
                    agentCustomer.setCateFlag(0);
                    agentCustomerDao.create(agentCustomer);
                }
            }
     
            return newCompanyId;

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return null;
    }

    /**
     * delte the company,and check other table that has the relation with
     * company table.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param company
     */
    public void deleteCompany(Company com) {
        try {
            init();

            // Start UOC

            Integer companyId = com.getId();
            UserDao userDao = new UserDao(conn);
            //user_tbl
            if (userDao.getCountByCompanyId(companyId) > 0) {
                errors.add("BSE01505");
                return;
            }
            
            //agent_customer_tbl
            AgentCustomerDao agentCustomerDao = new AgentCustomerDao(conn);
            if (agentCustomerDao.getCountByCustomerId(companyId) > 0) {
                errors.add("BSE01521");
                return;
            }

            //product_tbl
            ProductDao productDao = new ProductDao(conn);
            if (productDao.getCountByProducerId(companyId) > 0) {
                errors.add("BSE01506");
                return;
            }

            // installation_tbl
            InstallationDao installationDao = new InstallationDao(conn);
            
            if (installationDao.getCountByCustomerId(companyId) > 0) {
                errors.add("BSE01507");
                return;
            }
            
            //gai
            if (installationDao.getCountBySalesContractCompId(companyId) > 0) {
                errors.add("BSE01508");
                return;
            }
            //gai
            if (installationDao.getCountByInsatllCompId(companyId) > 0) {
                errors.add("BSE01524");
                return;
            }
            
            if (installationDao.getCountByFirstRepairCompanyId(companyId) > 0) {
                errors.add("BSE01509");
                return;
            }
            if (installationDao.getCountByNowRepairCompanyId(companyId) > 0) {
                errors.add("BSE01510");
                return;
            }

            // installation_history_tbl
            
            InstallationHistoryDao installationHistoryDao = new InstallationHistoryDao(conn);
            
            if (installationHistoryDao.getCountByCustomerId(companyId) > 0) {
                errors.add("BSE01517");
                return;
            }
            //gai
            if (installationHistoryDao.getSaleContractComId(companyId) > 0) {
                errors.add("BSE01518");
                return;
            }
            //gai
            if (installationHistoryDao.getCountByInsatllCompId(companyId) > 0) {
                errors.add("BSE01525");
                return;
            }
        
            if (installationHistoryDao.getCountByFirstRepairCompanyId(companyId) > 0) {
                errors.add("BSE01519");
                return;
            }
            if (installationHistoryDao.getCountByNowRepairCompanyId(companyId) > 0) {
                errors.add("BSE01520");
                return;
            }

            //insatllation_apply_tbl
            InstApplyDao instApplyDao = new InstApplyDao(conn);
            
            if (instApplyDao.getCountByCustomerId(companyId) > 0) {
                errors.add("BSE01511");
                return;
            }
            
            //gai
            if (instApplyDao.getSaleContractComId(companyId) > 0) {
                errors.add("BSE01512");
                return;
            }
            
            //gai
            if (instApplyDao.getCountByInsatllCompId(companyId) > 0) {
                errors.add("BSE01526");
                return;
            }
            
            if (instApplyDao.getCountByFirstRepairCompanyId(companyId) > 0) {
                errors.add("BSE01513");
                return;
            }
            if (instApplyDao.getCountByNowRepairCompanyId(companyId) > 0) {
                errors.add("BSE01522");
                return;
            }

            //fault_tbl
            FaultDao faultDao = new FaultDao(conn);
            if (faultDao.getCountByRepairCompanyId(companyId) > 0) {
                errors.add("BSE01514");
                return;
            }

            //fault_handle_tbl
            FaultHandleDao faultHandleDao = new FaultHandleDao(conn);
            if (faultHandleDao.getCountByOperatorCompanyId(companyId) > 0) {
                errors.add("BSE01515");
                return;
            }

            //message_tbl
            MessageDao messageDao = new MessageDao(conn);
            if (messageDao.getCountByCompanyId(companyId) > 0) {
                errors.add("BSE01516");
                return;
            }

            // delete the company proxy customer company relation.
            agentCustomerDao.deleteAgentCustomerByAgentComId(companyId,null);

            // delete the company proxy product category relation.
            AgentProductDao agentProductDao = new AgentProductDao(conn);
            agentProductDao.deleteAgentProductByAgentComId(companyId);
            
            //delete the company document relation
            DocumentAgentDao documentAgentDao = new DocumentAgentDao(conn);
            documentAgentDao.deleteAgentByCompanytId(companyId);
            
            //delete the company
            CompanyDao companyDao = new CompanyDao(conn);
            companyDao.delete(com);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify company as history
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param com
     * @param loginUser
     * @param companyCustomerAgentList
     * @param agentProductCateList
     */
    public void modifyCompanyAsHistory(Company com, User loginUser, List<Integer> companyCustomerAgentList,
            List<Integer> agentProductCateList) {
        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            com.setCode(com.getCode().trim());
            com.setShortName(com.getShortName().trim());
            com.setModifierId(loginUser.getId());
            com.setModifyTime(TimeUtil.getNow());

            List<Integer> comTypeNowList = com.getComTypeList();
            
            // check whether the selected companyType is deleted
            CompanyTypeDao companyTypeDao = new CompanyTypeDao(conn);
            for(Integer companyTypeId:comTypeNowList){
                if (companyTypeDao.getCountByComTypeId(companyTypeId) == 0) {
                    errors.add("BSE01501");
                    return;
                }
            }
            
            if (comDao.getCountByShortNameModify(com) > 0) {
                errors.add("BSE00000,company.shortName");
                return;
            }
            
            // check whether the selected province is deleted
            ProvinceDao provinceDao = new ProvinceDao(conn);
            provinceDao.setExclusiveCheck(false);
            Province argProvince = new Province();
            argProvince.setId(com.getProvinceId());
            
            Province province = provinceDao.searchProvinceById(argProvince);
            if (province == null) {
                errors.add("BSE01303");
                return;
            }
            
            // check whether the selected city is deleted
            CityDao cityDao = new CityDao(conn);
            cityDao.setExclusiveCheck(false);
            City argCity = new City();
            argCity.setId(com.getCityId());
            City city = cityDao.searchCityById(argCity);
            if (city == null) {
                errors.add("BSE01502");
                return;
            }
            //check whether the final user is selected
            if( comTypeNowList.size() == 1 && comTypeNowList.contains(new Integer(4))){
               // check whether the selected province is deleted
               BankDao bankDao = new BankDao(conn);
               Bank bank = bankDao.searchBankById(com.getBankId());
               if(bank == null){
                   errors.add("BSE01527");
                   return;
               }
               
               String cityName = city.getName();
               String provinceName = province.getName();
               //判断选择的省份是否为直辖市
               if(com.getMunicipality()!=null && com.getMunicipality().indexOf(provinceName)!=-1){
                   cityName ="";
               }
               
               String bankName = bank.getName();
               String comSubName = provinceName+cityName+"分行";
               String companyName = bankName+comSubName;
               com.setMainCompanyName(bankName);
               com.setSubCompanyName(comSubName);
               
                if (comDao.getCountByMainAndSubNameModify(com) > 0) {
                    errors.add("BSE00000,"+companyName);
                    return;
                }
            }else{
                com.setSubCompanyName("");
                com.setBankId(null);
                if (comDao.getCountByMainComNameModify(com) > 0) {
                    errors.add("BSE00000,company.name");
                    return;
                }
            }     

            // modify the company record info
            String currentDate = TimeUtil.getNowDate();
            String nextDate = TimeUtil.getNowNextDay();
            Integer agentComId = com.getId();
            Company companyHistory = comDao.searchById(com);
            if (companyHistory == null) {
                errors.add("BSF00006");
                return;
            } else {
                String historyStartDate = companyHistory.getAplyStartDate();
                // check whether the record has been modified today
                if (!nextDate.equals(companyHistory.getAplyStartDate())) {
                    // create the company history record
                    companyHistory.setAplyStartDate(historyStartDate);
                    companyHistory.setAplyEndDate(currentDate);
                    comDao.createHistory(companyHistory);
                }           
            }
            
            // get the date of the next day
            com.setAplyStartDate(nextDate);
            comDao.modifyCompanyInfo(com, true);
       
            // modify the agent product catetory related to this record
            AgentProductDao agentProductDao = new AgentProductDao(conn);
            // delete from the agent_product_tbl by the agent company id
            agentProductDao.deleteAgentProductByAgentComId(com.getId());

            // modify the agent customer record related to this record
            AgentCustomerDao agentCustomerDao = new AgentCustomerDao(conn);
            // delete from the agent_customer_tbl by the agent company id
            agentCustomerDao.deleteAgentCustomerByAgentComId(agentComId,1);
            
            //判断修改时，该公司是否为维修商，如果原先为维修商，而现今没有选择维修商，则其公司客户映射表
            //中该公司cateFlag为0的数据删除
            if(!comTypeNowList.contains(new Integer(3))){
               agentCustomerDao.deleteAgentCustomerByAgentComId(agentComId,0);
            }else{
                //公司选择了维修商，则需更新产品公司映射表和公司客户映射表
                ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
                for (Integer productCategoryId : agentProductCateList) {
                    // check whether the selected product category exists
                    if (productCategoryDao.getCountByProductCategoryId(productCategoryId) > 0) {
                        AgentProduct agentProduct = new AgentProduct();
                        agentProduct.setAgentId(agentComId);
                        agentProduct.setProductCategoryId(productCategoryId);
                        agentProduct.setStartDate(TimeUtil.getNowDate());
                        agentProductDao.createAgentProduct(agentProduct);

                    } else {
                        errors.add("BSE01503");
                        return;
                    }
                }
                
                CompanyDao companyDao = new CompanyDao(conn);
                for (Integer customerId : companyCustomerAgentList) {
                    // check whether the selected company customer exists.
                    if (companyDao.getCountByCompanyId(customerId) > 0) {
                        AgentCustomer agentCustomer = new AgentCustomer();
                        agentCustomer.setAgentId(agentComId);
                        agentCustomer.setCustomerId(customerId);
                        agentCustomer.setCateFlag(1);
                        agentCustomerDao.create(agentCustomer);
                    } else {
                        errors.add("BSE01504");
                        return;
                    }
                }
                
                
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify company
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param com
     * @param loginUser
     * @param companyCustomerAgentList
     * @param agentProductCateList
     */
    public void modifyCompanyInfo(Company com, User loginUser, List<Integer> companyCustomerAgentList,
            List<Integer> agentProductCateList) {
        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            com.setCode(com.getCode().trim());
            com.setShortName(com.getShortName().trim());
            com.setModifierId(loginUser.getId());
            com.setModifyTime(TimeUtil.getNow());

            List<Integer> comTypeList = com.getComTypeList();
            
            // check whether the selected companyType is deleted
            CompanyTypeDao companyTypeDao = new CompanyTypeDao(conn);
            for(Integer companyTypeId:comTypeList){
                if (companyTypeDao.getCountByComTypeId(companyTypeId) == 0) {
                    errors.add("BSE01501");
                    return;
                }
            }
            
            if (comDao.getCountByShortNameModify(com) > 0) {
                errors.add("BSE00000,company.shortName");
                return;
            }
            
            // check whether the selected province is deleted
            ProvinceDao provinceDao = new ProvinceDao(conn);
            provinceDao.setExclusiveCheck(false);
            Province argProvince = new Province();
            argProvince.setId(com.getProvinceId());
            
            Province province = provinceDao.searchProvinceById(argProvince);
            if (province == null) {
                errors.add("BSE01303");
                return;
            }
            
            // check whether the selected city is deleted
            CityDao cityDao = new CityDao(conn);
            cityDao.setExclusiveCheck(false);
            City argCity = new City();
            argCity.setId(com.getCityId());
            City city = cityDao.searchCityById(argCity);
            if (city == null) {
                errors.add("BSE01502");
                return;
            }
            
            if( comTypeList.size() == 1 && comTypeList.contains(new Integer(4))){
                // check whether the selected province is deleted
                BankDao bankDao = new BankDao(conn);
                Bank bank = bankDao.searchBankById(com.getBankId());
                if(bank == null){
                    errors.add("BSE01527");
                    return;
                }
                
                String cityName = city.getName();
                String provinceName = province.getName();
                //判断所选的省份是否为直辖市
                if(com.getMunicipality()!=null && com.getMunicipality().indexOf(provinceName)!=-1){
                    cityName ="";
                }
                
                String bankName = bank.getName();
                String comSubName = provinceName+cityName+"分行";
                String companyName = bankName+comSubName;
                com.setMainCompanyName(bankName);
                com.setSubCompanyName(comSubName);
                
                 if (comDao.getCountByMainAndSubNameModify(com) > 0) {
                     errors.add("BSE00000,"+companyName);
                     return;
                 }
             }else{
                 com.setSubCompanyName("");
                 com.setBankId(null);
                 if (comDao.getCountByMainComNameModify(com) > 0) {
                     errors.add("BSE00000,company.name");
                     return;
                 }
             }
            
       
            // modify the company info
            comDao.modifyCompanyInfo(com, false);
            Integer agentComId = com.getId();
            // modify the agent product catetory related to this record
            AgentProductDao agentProductDao = new AgentProductDao(conn);
            // delete from the agent_product_tbl by the agent company id
            agentProductDao.deleteAgentProductByAgentComId(agentComId);


            // modify the agent customer record related to this record
            AgentCustomerDao agentCustomerDao = new AgentCustomerDao(conn);
            // delete from the agent_customer_tbl by the agent company id
            agentCustomerDao.deleteAgentCustomerByAgentComId(agentComId,1);
            
            //判断修改时，该公司是否为维修商，如果原先为维修商，而现今没有选择维修商，则其公司客户映射表
            //中该公司cateFlag为0的数据删除
            if(!comTypeList.contains(new Integer(3))){
               agentCustomerDao.deleteAgentCustomerByAgentComId(agentComId,0);
            }else{
                //公司选择了维修商，则需更新产品公司映射表和公司客户映射表
                ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
                for (Integer productCategoryId : agentProductCateList) {

                    // check whether the selected product category exists
                    if (productCategoryDao.getCountByProductCategoryId(productCategoryId) > 0) {
                        AgentProduct agentProduct = new AgentProduct();
                        agentProduct.setAgentId(agentComId);
                        agentProduct.setProductCategoryId(productCategoryId);
                        agentProduct.setStartDate(TimeUtil.getNowDate());
                        agentProductDao.createAgentProduct(agentProduct);

                    } else {
                        errors.add("BSE01503");
                        return;
                    }
                }
                
                CompanyDao companyDao = new CompanyDao(conn);
                for (Integer customerId : companyCustomerAgentList) {
                    // check whether the selected company customer exists.
                    if (companyDao.getCountByCompanyId(customerId) > 0) {
                        AgentCustomer agentCustomer = new AgentCustomer();
                        agentCustomer.setAgentId(agentComId);
                        agentCustomer.setCustomerId(customerId);
                        agentCustomer.setCateFlag(1);
                        agentCustomerDao.create(agentCustomer);
                    } else {
                        errors.add("BSE01504");
                        return;
                    }
                }
                
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    
    /**
     * modify company
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param com
     * @param loginUser
     * @param companyCustomerAgentList
     * @param agentProductCateList
     */
    public void editCompanyBankCustomer(
            Integer agentComId, 
            List<Integer> bankCustomerList
           ) {
        try {
            init();

            // Start UOC

            // modify the agent customer record related to this record
            AgentCustomerDao agentCustomerDao = new AgentCustomerDao(conn);
            // delete from the agent_customer_tbl by the agent company id
            agentCustomerDao.deleteAgentCustomerByAgentComId(agentComId,0);
            CompanyDao companyDao = new CompanyDao(conn);
            for (Integer bankCustomerId : bankCustomerList) {
           
                // check whether the selected company customer exists.
                if (companyDao.getCountByCompanyId(bankCustomerId) > 0) {
                    AgentCustomer agentCustomer = new AgentCustomer();
                    agentCustomer.setAgentId(agentComId);
                    agentCustomer.setCustomerId(bankCustomerId);
                    agentCustomer.setCateFlag(0);
                    agentCustomerDao.create(agentCustomer);
                } else {
                    errors.add("BSE01504");
                    return;
                }
     
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    
    /**
     * get compnay agent product category list.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param agentComId
     * @return List&ltInteger&gt
     */
    public List<Integer> getCompanyAgentProductCategory(Integer agentComId) {
        List<Integer> companyAgentProductCateList = new ArrayList<Integer>();

        try {
            init();

            // Start UOC

            AgentProductDao comDao = new AgentProductDao(conn);
            companyAgentProductCateList = comDao.getCompanyAgentProductCategory(agentComId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return companyAgentProductCateList;
    }


    /**
     * get all agent company
     * 
     * @author liugd
     * @return companyList
     */
    public List<Company> getAllAgent(User user) {
        List<Company> companyList = new ArrayList<Company>();

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            String subSql = " AND TYPE_ID LIKE '%,3,%' ";
            if (!user.filter("company_mng_all_data")) {
                subSql = subSql + " AND ID=" + user.getCompanyID();
            }
            companyList = comDao.getCompanyByCategory(subSql);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return companyList;
    }

    /**
     * get all the company for user select
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltCompany&gt
     */
    public List<Company> getCompanyForUser() {
        List<Company> comsOut = new ArrayList<Company>();

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            comsOut = comDao.getUserCompany();

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comsOut;
    }

    /**
     * get all the company for the message.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltCompany&gt
     */
    public List<Company> getCompanyFoMessage() {
        List<Company> comsOut = new ArrayList<Company>();

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            comsOut = comDao.getCompanyForMessage();

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comsOut;
    }

    /**
     * get all the company for delegate.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List<Company>
     */
    public List<Company> getCompanyFoDelegate() {
        List<Company> comsOut = new ArrayList<Company>();

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            comsOut = comDao.getCompanyForDelegate();

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comsOut;
    }
    
    /**
     * get all the company for delegate.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List<Company>
     */
    public List<Company> getAgentCustomerByAgentId(Integer agentId,Integer cateFlag) {
        List<Company> comsOut = new ArrayList<Company>();

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            comsOut = comDao.getCustomerByAgentId(agentId,cateFlag);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comsOut;
    }

    /**
     * get the bank company that the type
     * id including 4
     * @auther xiangzq
     * @version 2.0
     * @since 2.0
     * @return List<Company>
     */
    public List<Company> getCompanyCustomer(
            Integer bankId,
            Integer provinceId,
            Integer cityId,
            Integer saleComId) {
        List<Company> comsOut = new ArrayList<Company>();

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            comsOut = comDao.getCompanyCustomer(bankId, provinceId, cityId,saleComId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comsOut;
    }
    

    /**
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List<Company>
     */
    public List<Company> getDocumentAgentCompany(Integer productId) {
        List<Company> comsOut = new ArrayList<Company>();

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            comsOut = comDao.getDocumentAgentCompany(productId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comsOut;
    }
    
    /**
     * customer company
     * 
     * @auther liugd
     * @version 1.0
     * @since 1.0
     * @param user
     * @return List&ltCompany&gt
     */
    public List<Company> getCustomerCompanyByUser(
            User user,
            Integer productCategoryId,
            Integer agentComId,
            Integer bankId,
            Integer provinceId,
            Integer cityId) {
        List<Company> comsOut = new ArrayList<Company>();

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            String subSql = "";
            if (!user.filter("company_mng_all_data")) {
                //如果登陆用户没有看到所有数据的权限则只能看到其代理的银行客户和本公司
                subSql = " AND (ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE CATE_FLAG=0 AND AGENT_ID="
                        + user.getCompanyID() + " ) " +
                        " OR ID = " + user.getCompanyID() + " ) AND (TYPE_ID LIKE '%,4,%') ";
            } else {
              //否则
                if(productCategoryId != null){
                    //如果产品代理产品分类不为空，则能看到代理了代理了该产品的所有维修商的所代理的银行客户
                    subSql = 
                            " AND (TYPE_ID LIKE '%,4,%') "+ 
                            " AND ID IN  "+  
                            " ( SELECT "+ 
                            "   DISTINCT  "+ 
                            "   CUSTOMER_ID "+ 
                            "   FROM "+ 
                            "   AGENT_CUSTOMER_TBL "+ 
                            "   WHERE "+ 
                            "   AGENT_ID IN "+ 
                            "   ("+ 
                            "       SELECT "+ 
                            "       AGENT_ID "+ 
                            "       FROM "+
                            "       AGENT_PRODUCT_TBL "+ 
                            "       WHERE  "+ 
                            "       PRODUCT_CATEGORY_ID = "+productCategoryId + 
                            "   )" +
                            "  AND CATE_FLAG = 0 "+ 
                            " )";
                }else{
                    //否则能看到所有的公司分类中包含最终用户的所有公司。
                    subSql = " AND (TYPE_ID LIKE '%,4,%') ";
                }
            }
            if(bankId != null){
                subSql = subSql + " AND ( BANK_ID IS null or BANK_ID="+bankId+") ";
            }
            if(provinceId != null ){
                subSql = subSql + " AND PROVINCE_ID ="+provinceId;
            }
            if(cityId != null ){
                subSql = subSql + " AND CITY_ID ="+cityId;
            }
            if(agentComId!=null){
                subSql = subSql + 
                " AND ID IN " +
                " ( SELECT " +
                "   CUSTOMER_ID" +
                "   FROM AGENT_CUSTOMER_TBL " +
                "   WHERE CATE_FLAG = 0 AND " +
                "   AGENT_ID="+ agentComId +
                " ) " ;
            }
            
            comsOut = comDao.getCompanyByType(subSql);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comsOut;
    }

    /**
     * manufactury company,repair company
     * 
     * @auther liugd
     * @version 1.0
     * @since 1.0
     * @param subSql
     * @return List&ltCompany&gt
     */
    public List<Company> getCompanyByType(String subSql) {
        List<Company> comsOut = new ArrayList<Company>();

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            comsOut = comDao.getCompanyByType(subSql);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comsOut;
    }

    /**
     * get company by type.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltCompany&gt
     */
    public List<Company> getCompanyByTypeId(String comType) {
        List<Company> comsOut = new ArrayList<Company>();

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            comsOut = comDao.getCompanyByTypeId(comType);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comsOut;
    }

    /**
     * get one company by id
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param Company
     * @return Company
     */
    public Company searchCompanyById(Company com,boolean isExclusiveCheck) {
        Company companyOut = null;

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            comDao.setExclusiveCheck(isExclusiveCheck);
            companyOut = comDao.searchById(com);
            if (null == companyOut) {
                errors.add("BSF00006");
            }else{
                CompanyTypeDao companyTypeDao = new CompanyTypeDao(conn);
                //get the company type hash map
                Map<Integer, String> comTypeMap = companyTypeDao.selectComTypeMap();
                List<Integer> comTypeIdList = new ArrayList<Integer>();
                comTypeIdList = StringUtil.stringToArray(companyOut.getTypeId());
                int typeLength = comTypeIdList.size();
                String companyTypeName = "";
                for (int i = 0; i < typeLength; i++) {
                    Integer comTypeId = comTypeIdList.get(i);
                    if (i != typeLength - 1) {
                        companyTypeName = companyTypeName + comTypeMap.get(comTypeId) + ",";
                    } else {
                        companyTypeName = companyTypeName + comTypeMap.get(comTypeId);
                    }
                }
                String subCompanyName = companyOut.getSubCompanyName();
                if(subCompanyName == null){
                    subCompanyName = "";
                }
                companyOut.setCompanyNameShow(companyOut.getMainCompanyName()+subCompanyName);
                companyOut.setCompanyTypeName(companyTypeName);
                companyOut.setComTypeList(comTypeIdList);
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return companyOut;
    }

    /**
     * delete the company temporarily.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param Company
     * @return Company
     */
    public void deleteCompanyForTemp(Company com) {

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            com.setDeleted(1);
            comDao.deleteOrRecoverCompany(com);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

    }

    /**
     * recover the deleted company .
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param Company
     * @return Company
     */
    public void recoverCompany(Company com) {

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            com.setDeleted(0);
            comDao.deleteOrRecoverCompany(com);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

    }

    /**
     * get the company by page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param currPage
     * @param pageSize
     * @return List&ltCompany&gt
     */
    public List<Company> getComByPage(int currPage, int pageSize, Company company,User loginUser) {
        List<Company> companyOutList = new ArrayList<Company>();

        try {
            init();

            // Start UOC
            
            CompanyDao comDao = new CompanyDao(conn);
            int intBegin;
            int intEnd;

            if (currPage <= 1) {
                intBegin = 0;
                intEnd = pageSize;
            } else {
                intBegin = (currPage - 1) * pageSize;
                intEnd = intBegin + pageSize;
            }
            if (loginUser.filter("company_mng_all_data")) {
                //list all the company information
                companyOutList = comDao.getAllCompanyByPage(intBegin, intEnd, company, loginUser.getCompanyID());
            } else {
                //list the company of the login user and the proxy customer company information.
                companyOutList = comDao.getCompanyByPage(intBegin, intEnd, company, loginUser.getCompanyID());
            }
            CompanyTypeDao companyTypeDao = new CompanyTypeDao(conn);
            //get the company type hash map.
            Map<Integer, String> comTypeMap = companyTypeDao.selectComTypeMap();

            for (Company companyOut : companyOutList) {
                List<Integer> intList = new ArrayList<Integer>();
                intList = StringUtil.stringToArray(companyOut.getTypeId());
                int typeLength = intList.size();
                String companyTypeName = "";
                for (int i = 0; i < typeLength; i++) {
                    Integer comTypeId = intList.get(i);
                    if (i != typeLength - 1) {
                        companyTypeName = companyTypeName + comTypeMap.get(comTypeId) + ",";
                    } else {
                        companyTypeName = companyTypeName + comTypeMap.get(comTypeId);
                    }
                }
                companyOut.setCompanyTypeName(companyTypeName);
                companyOut.setComTypeList(intList);

            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return companyOutList;
    }

    /**
     * get company counts.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return int
     */
    public int getCompanyCounts(Company company,User loginUser) {
        int cityCounts = 0;

        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            if(loginUser.filter("company_mng_all_data")){
                //list all the company information
                cityCounts = comDao.getAllCompanyCountByRules(company,loginUser.getCompanyID());
            }else{
                //list the company of the login user and the proxy customer company information.
                cityCounts = comDao.getCompanyCountByRules(company,loginUser.getCompanyID());
            }
                
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return cityCounts;
    }
    
    /**
     * install company drop
     * @author sunyx
     * @return
     */
    public List<Company> getInstallCompany(String subSql){
        List<Company> list = new ArrayList<Company>();
        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            list = comDao.getInstallCompany(subSql);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return list;
    }

    /**
     * sales company drop
     * @author sunyx
     * @return
     */
    public List<Company> getSaleCompany(User user,Integer agentComId){
        List<Company> list = new ArrayList<Company>();
        try {
            init();

            // Start UOC

            CompanyDao comDao = new CompanyDao(conn);
            String subSql = "";
            if (!user.filter("company_mng_all_data")) {
                subSql = " AND (ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE AGENT_ID = "
                        + user.getCompanyID() + " AND CATE_FLAG=1 ) " + 
                        " OR ID = " + user.getCompanyID() + ") AND (TYPE_ID LIKE '%,2,%') ";
            } else {
                subSql = " AND (TYPE_ID LIKE '%,2,%') ";
            }
            if(agentComId!=null){
                subSql = subSql + " AND ID IN ( SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE CATE_FLAG=1 AND " +
                " AGENT_ID ="+agentComId +")";
            }
            list = comDao.getCompanyByType(subSql);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return list;
    }
}
