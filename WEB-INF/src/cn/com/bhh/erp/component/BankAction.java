package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.BankBusiness;
import cn.com.bhh.erp.entity.Bank;



@SuppressWarnings("serial")
public class BankAction extends BaseAction{
    private List<Bank> bankList = new ArrayList<Bank>();
    private Integer selectedBankId;
    private Integer useFlag;//0:列出所有的银行种类，1：仅列出公司表中具有bank_id的银行种类。
    private Integer cateFlag;//0:仅列出银行 1：列出银行和销售商 (默认列出银行)
    public BankAction(){
 
    }
    
    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }
    public void initData(){
        BankBusiness bankBusiness = new BankBusiness();
        if(cateFlag ==null){
            cateFlag = 0;
        }
        if(useFlag!=null){
            if(useFlag == 1){
                bankList = bankBusiness.getBankListForDrop(true,cateFlag);
            }else{
                bankList = bankBusiness.getBankListForDrop(false,cateFlag);
            }
        }else{
            bankList = bankBusiness.getBankListForDrop(false,cateFlag);
        }
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

    /**
     * @return the selectedBankId
     */
    public Integer getSelectedBankId() {
        return selectedBankId;
    }

    /**
     * @param selectedBankId the selectedBankId to set
     */
    public void setSelectedBankId(Integer selectedBankId) {
        this.selectedBankId = selectedBankId;
    }

    /**
     * @return the useFlag
     */
    public Integer getUseFlag() {
        return useFlag;
    }

    /**
     * @param useFlag the useFlag to set
     */
    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
    }

    /**
     * @return the cateFlag
     */
    public Integer getCateFlag() {
        return cateFlag;
    }

    /**
     * @param cateFlag the cateFlag to set
     */
    public void setCateFlag(Integer cateFlag) {
        this.cateFlag = cateFlag;
    }



   
    
}
