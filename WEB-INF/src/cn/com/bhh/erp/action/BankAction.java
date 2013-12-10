//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   10/02/09
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.business.BankBusiness;
import cn.com.bhh.erp.entity.Bank;



/**
 * this class include the functions: create,delete,modify and list bank.
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class BankAction extends BaseAction {
    
    private Bank bank = new Bank();
    private List<Bank> bankList = new ArrayList<Bank>();
    
    /**
     * list the bank
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String listBank() throws Exception {
        
        BankBusiness bankBusiness = new BankBusiness();
        bankList = bankBusiness.searchAllBank();
        if (bankBusiness.hasErrors()) {
            setActionMessages(getMessageText(bankBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to the bank create page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createBankPre() throws Exception {
        bank = new Bank();
        return SUCCESS;
    }

    /**
     * the operation of deleting bank.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String deleteBank() throws Exception {

        if (null == bank.getId() || null == bank.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        BankBusiness bankBusiness = new BankBusiness();
        bankBusiness.deleteBank(bank);

        if (bankBusiness.hasErrors()) {
            setActionMessages(getMessageText(bankBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * direct to the modify bank page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyBankPre() throws Exception {
        
        if (null == bank.getId() || null == bank.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        BankBusiness bankBusiness = new BankBusiness();
        bank = bankBusiness.searchBankById(bank.getId());
        if (bankBusiness.hasErrors()) {
            setActionMessages(getMessageText(bankBusiness.getErrors()));
            return INPUT;
        }
       
        return SUCCESS;
    }

    /**
     * modify the bank.
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param
     * @return String
     * @throws Exception
     */
    public String modifyBank() throws Exception {

        if (null == bank.getId() || null == bank.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        BankBusiness bankBusiness = new BankBusiness();
        bank.setModifierId(loginUser.getId());
        bankBusiness.modifyBank(bank);

        if (bankBusiness.hasErrors()) {
            setActionMessages(getMessageText(bankBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * the operation of creating bank.
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createBank() throws Exception {
        
        BankBusiness bankBusiness = new BankBusiness();
        bank.setModifierId(loginUser.getId());
        bank.setCreatorId(loginUser.getId());
        bankBusiness.createBank(bank);

        if (bankBusiness.hasErrors()) {
            setActionMessages(getMessageText(bankBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * @param bank the bank to set
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * @return the bankList
     */
    public List<Bank> getBankList() {
        return bankList;
    }

    /**
     * @param bankList the bankList to set
     */
    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    
    


   
}
