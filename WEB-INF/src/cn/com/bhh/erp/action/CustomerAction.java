
package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.business.ChannelBusiness;
import cn.com.bhh.erp.business.CustomerBusiness;
import cn.com.bhh.erp.entity.Customer;

/**
 * prepare the data for the channel.
 * 
 */
@SuppressWarnings("serial")
public class CustomerAction extends BaseAction {
	private Customer customer = new Customer();
    private List<Customer> customerList = new ArrayList<Customer>();
    private Integer selectedCustomerId = null;
    private List<String> affirmChkList = new ArrayList<String>();
   
    
    public String SearchCustomerList()throws Exception {
        //unconfirmed installation list
        CustomerBusiness customerBussiness = new CustomerBusiness();
        int totalNum = customerBussiness.getCustomerCount(customer, loginUser);
        setTotalCount(totalNum);
        customerList = customerBussiness.searchCustomerList(customer, loginUser,currPage, pageSize);
        if (customerBussiness.hasErrors()) {
            setActionMessages(getMessageText(customerBussiness.getErrors()));
            return INPUT;
        }      

        return SUCCESS;
    } 
    
    
    
    /**
     * direct to create channel page.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createCustomerPre() throws Exception {
    	customer = new Customer();
        return SUCCESS;
    }
    
    /**
     * create the channel operation.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createCustomer() throws Exception {

    	CustomerBusiness customerBussiness = new CustomerBusiness();
    	customerBussiness.createCustomer(customer,loginUser);
        if (customerBussiness.hasErrors()) {
            setActionMessages(getMessageText(customerBussiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }
    




	public List<String> getAffirmChkList() {
		return affirmChkList;
	}


	public void setAffirmChkList(List<String> affirmChkList) {
		this.affirmChkList = affirmChkList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public Integer getSelectedCustomerId() {
		return selectedCustomerId;
	}

	public void setSelectedCustomerId(Integer selectedCustomerId) {
		this.selectedCustomerId = selectedCustomerId;
	}
    
}
