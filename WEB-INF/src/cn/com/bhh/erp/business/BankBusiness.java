package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.dao.BankDao;
import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.entity.Bank;
import cn.com.bhh.erp.entity.Company;



public class BankBusiness extends BaseBusiness{

    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   isInCompany
     * @param   cateFlag 0:列出银行 1：列出银行和销售商
     * @return  List<Bank>
     * @throws  Exception
     */
    public List<Bank> getBankListForDrop(boolean isInCompany,Integer cateFlag){
        List<Bank> list = new ArrayList<Bank>();
        try{
            init();
            
            // Start UOC
            BankDao bankDao = new BankDao(conn);
            list = bankDao.getBankListForDrop(isInCompany);
            CompanyDao companyDao = new CompanyDao(conn);
            if(cateFlag == 1){
                List<Company> saleCompanyList = companyDao.getSaleCustomerCompany();
                for(Company saleCompany:saleCompanyList){
                    Bank bank = new Bank();
                    bank.setId(saleCompany.getId());
                    bank.setName(saleCompany.getShortName());
                    bank.setCateFlag(1);
                    list.add(bank);
                }
            }
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return list;
    }
    /**
     * create a bank
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param bank
     */
    public void createBank(Bank bank){
        try {
            init();

            // Start UOC

            BankDao bankDao = new BankDao(conn);
            if (bankDao.getCountByName(bank) > 0) {
                // decide whether db has the same name with the input name
                errors.add("BSE00000,bank.name");
                return;
            }
            
            bank.setCateFlag(0);
            bank.setShortName(bank.getShortName().toUpperCase());
            bank.setCreateTime(TimeUtil.getNow());
            bank.setModifyTime(TimeUtil.getNow());
            // create the bank
            bankDao.createBank(bank);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * delete the message.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param message
     */
    public void deleteBank(Bank bank) {
        try {
            init();

            // Start UOC

            
            CompanyDao companyDao = new CompanyDao(conn);
            if(companyDao.getCountByBankId(bank.getId())>0){
                errors.add("BSE02501");
                return;
            }
            
            BankDao bankDao = new BankDao(conn);
            bankDao.deleteBank(bank);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   bank
     * @return  void
     * @throws  Exception
     */
    public void modifyBank(Bank bank) {
        try {
            init();

            // Start UOC

            BankDao bankDao = new BankDao(conn);
            // get the same name count of bank
            int sameNameCount = bankDao.getCountByNameModify(bank);
            if (sameNameCount > 0) {
                errors.add("BSE00000,bank.name");
                return;
            }

            bank.setModifyTime(TimeUtil.getNow());
            bankDao.modifyBank(bank);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * get all the Bank list.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List<Bank>
     */
    public List<Bank> searchAllBank() {
        List<Bank> bankListOut = new ArrayList<Bank>();

        try {
            init();

            // Start UOC

            BankDao bankDao = new BankDao(conn);
            bankListOut = bankDao.getBankListForDrop(false);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return bankListOut;
    }

    /**
     * get all the Bank by id.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List<Bank>
     */
    public  Bank  searchBankById(Integer id) {
        Bank bankOut = null;

        try {
            init();

            // Start UOC

            BankDao bankDao = new BankDao(conn);
            bankOut = bankDao.searchBankById(id);
            if(bankOut == null){
                errors.add("BSF00006");
                
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return bankOut;
    }
    
}
