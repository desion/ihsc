
package cn.com.bhh.erp.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.dao.ChannelDao;
import cn.com.bhh.erp.dao.CountryDao;
import cn.com.bhh.erp.dao.CustomerDao;
import cn.com.bhh.erp.dao.GetPK;
import cn.com.bhh.erp.dao.GroupDao;
import cn.com.bhh.erp.dao.ProvinceDao;
import cn.com.bhh.erp.dao.UserDao;
import cn.com.bhh.erp.entity.Channel;
import cn.com.bhh.erp.entity.ChannelAccumulate;
import cn.com.bhh.erp.entity.ChannelDetail;
import cn.com.bhh.erp.entity.Customer;
import cn.com.bhh.erp.entity.HhmtRequest;
import cn.com.bhh.erp.entity.Province;
import cn.com.bhh.erp.entity.User;

/**
 * inlcude the business of deal with the channel.
 * 
 * @author desionwang
 * @version 1.0
 * @since 1.0
 */
public class CustomerBusiness extends BaseBusiness {
	public static int[] TopupValues = {10,20,30,50,100};
    /**
     * create Customer
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @param province
     */
    public void createCustomer(Customer customer, User loginUser) throws Exception {
        try {
            init();

            // Start UOC
            CustomerDao customerDao = new CustomerDao(conn);
            

            String customerName = customer.getCustomerName().trim();
            customer.setCustomerName(customerName);
            String sname = customer.getSname().trim();
            customer.setSname(sname);
            // check whether the same name exists.
            if (customerDao.getCountByCustomerName(customer) > 0) {
                errors.add("BSE00000,channel.channelName");
                return;
            }
            
            // check whether the same name exists.
            if (customerDao.getCountBySName(customer) > 0) {
                errors.add("BSE00000,channel.same");
                return;
            }
            
            if(customer.getBalance() == null){
            	customer.setBalance(0.0f);
            }

            GetPK getPK = new GetPK(conn);
            Integer seqNumber = getPK.getPK("CUSTOMER_TBL_ID_SEQ");
            customer.setId(seqNumber);
            
            User user = new User();
            user.setName(customer.getSname());
            user.setMobilePhone(customer.getMobile());
            user.setEmail(customer.getEmail());
            user.setFullName(customer.getCustomerName());
            user.setPassword(customer.getSname());
            user.setCreatorID(loginUser.getId());
            user.setModifierID(loginUser.getId());
            user.setCreateTime(TimeUtil.getNow());
            user.setDepartment("订购用户");
            
            UserDao userDao = new UserDao(conn); 
            if (userDao.getCountByUserName(user) > 0) {
                errors.add("BSE00000,user.name");
                return;
            }

//            GroupDao groupDao = new GroupDao(conn);
//            if (groupDao.getCountById(user.getGroupId()) == 0) {
//                errors.add("BSE01013");
//                return;
//            }
            

            seqNumber = getPK.getPK("USER_TBL_ID_SEQ");
            user.setId(seqNumber);
            
            
            customerDao.createCustomer(customer, user);;
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    public void createCustomerDetail(ChannelDetail channelDetail) throws Exception {
        try {
            init();

            // Start UOC
            ChannelDao channelDao = new ChannelDao(conn);
            String valueStr = channelDetail.getValueStr();
            if(valueStr != null && valueStr.contains(",")){
            	String[] values = valueStr.split(",");
            	int topValue = 0;
            	for(String value : values){
            		topValue = Integer.valueOf(value);
            		if(topValue != 10 &&
            				topValue != 20 &&
            				topValue != 30 &&
            				topValue != 50 &&
            				topValue != 100){
            			errors.add("BSE01202");
            			return;
            		}
            	}
            	GetPK getPK = new GetPK(conn);
            	for(String value : values){
            		topValue = Integer.valueOf(value);
            		Integer seqNumber = getPK.getPK("CHANNEL_DETAIL_TBL_ID_SEQ");
            		channelDetail.setId(seqNumber);
            		channelDetail.setValue(topValue);
            		channelDao.createChannelDetail(channelDetail);
            	}
            }else{
            	errors.add("BSE01202");
    			return;
            }
            // End UOC

        } catch (Exception e) {
        	if (e instanceof SQLException) {
        		SQLException sqle = (SQLException)e;
        		if("23000".equals(sqle.getSQLState())){
        			errors.add("BSE02601");
        		}else{
        			handleException(e);
        		}
        	}else{
        		handleException(e);
        	}
        } finally {
            finish();
        }
    }

    /**
     * delete the province.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param province
     */
    public void deleteChannel(Province province) throws Exception {
        try {
            init();

            // Start UOC

            ProvinceDao provinceDao = new ProvinceDao(conn);
            // check whether the province has citys.
            int cityCounts = provinceDao.getCityCountOfProvince(province);

            if (cityCounts > 0) {
                errors.add("BSE01202");
                return;
            }
            provinceDao.deleteProvince(province);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * moidfy province information.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param province
     */
    public void modifyChannelInfo(Province province) throws Exception {
        try {
            init();

            // Start UOC

            ProvinceDao provinceDao = new ProvinceDao(conn);
            String provinceName = province.getName().trim();

            province.setName(provinceName);

            // check whether the same name exists beside self.
            int sameNameCount = provinceDao.getCountByProvinceNameModify(province);

            if (sameNameCount > 0) {
                errors.add("BSE00000,province.name");
                return;
            }

            // check whether the selected country exsits.
            CountryDao countryDao = new CountryDao(conn);
            if (countryDao.getCountByCountryId(province.getCountryId()) == 0) {
                errors.add("BSE01203");
                return;
            }

            String shortName = province.getShortName();
            shortName = shortName.trim().toUpperCase();
            province.setShortName(shortName);
            
            provinceDao.modifyProvinceInfo(province);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    public void Accumulate(ChannelAccumulate channelAccmum, User loginUser)throws Exception{
    	try {
            init();

            // Start UOC
            ChannelDao channelDao = new ChannelDao(conn);
            if(!channelDao.checkChannelBlance(channelAccmum)){
            	errors.add("BSF00012");
            	return;
            }
            channelAccmum.setId((System.currentTimeMillis() / 1000) + channelAccmum.getChannelId().toString());
            channelAccmum.setUserId(loginUser.getId());
            channelAccmum.setOpTime(TimeUtil.getNow());
            channelDao.Accumulate(channelAccmum);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * list all the channel information.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return List&ltProvince&gt
     */
    public List<Customer> searchCustomerList(Customer customer, User user, int currPage,int pageSize) {
    	List<Customer> customerOut = new ArrayList<Customer>();

        try {
            init();
            int intBegin;
            int intEnd;

            if (currPage <= 1) {
                intBegin = 0;
                intEnd = pageSize;
            } else {
                intBegin = (currPage - 1) * pageSize;
                intEnd = intBegin + pageSize;
            }
            // Start UOC
            CustomerDao customerDao = new CustomerDao(conn);
            customerOut = customerDao.searchAllCustomer(customer, user, intBegin, intEnd);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return customerOut;
    }
    
    /**
     * list all the channel information.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return List&ltProvince&gt
     */
    public List<ChannelDetail> searchChannelDetailList(Channel channel, User user, int currPage,int pageSize) {
    	List<ChannelDetail> channelOut = new ArrayList<ChannelDetail>();

        try {
            init();
            int intBegin;
            int intEnd;

            if (currPage <= 1) {
                intBegin = 0;
                intEnd = pageSize;
            } else {
                intBegin = (currPage - 1) * pageSize;
                intEnd = intBegin + pageSize;
            }
            // Start UOC
            ChannelDao channelDao = new ChannelDao(conn);
            channelOut = channelDao.searchAllChannelDetail(channel, user, intBegin, intEnd);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return channelOut;
    }
    
    /**
     * list all the channel information.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return List&ltProvince&gt
     */
    public List<ChannelAccumulate> searchChannelAccuList(ChannelAccumulate channelAccu, User user, int currPage,int pageSize) {
    	List<ChannelAccumulate> channelOut = new ArrayList<ChannelAccumulate>();

        try {
            init();
            int intBegin;
            int intEnd;

            if (currPage <= 1) {
                intBegin = 0;
                intEnd = pageSize;
            } else {
                intBegin = (currPage - 1) * pageSize;
                intEnd = intBegin + pageSize;
            }
            // Start UOC
            ChannelDao channelDao = new ChannelDao(conn);
            channelOut = channelDao.searchAllChannelAccu(channelAccu, user, intBegin, intEnd);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return channelOut;
    }
    /**
     * list all the channel information.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return List&ltProvince&gt
     */
    public int getCustomerCount(Customer customer, User user) {

    	int counts = 0;
        try {
            init();
            // Start UOC
            CustomerDao customerDao = new CustomerDao(conn);
            counts = customerDao.getCustomerCount(customer, user);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return counts;
    }
    
    /**
     * list all the channel information.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return List&ltProvince&gt
     */
    public int getChannelDetailCount(ChannelDetail channelDetail, User user) {

    	int counts = 0;
        try {
            init();
            // Start UOC
            ChannelDao channelDao = new ChannelDao(conn);
            counts = channelDao.getChannelDetailCount(channelDetail, user);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return counts;
    }
    
    /**
     * list all the channel information.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return List&ltProvince&gt
     */
    public int getRequestCount(HhmtRequest hhmtRequest, User user) {

    	int counts = 0;
        try {
            init();
            // Start UOC
            CustomerDao customerDao = new CustomerDao(conn);
            counts = customerDao.getChannelAccuCount(hhmtRequest, user);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return counts;
    }
    
    /**
     * list all the channel information.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return List&ltProvince&gt
     */
    public List<Channel> searchAllChannel() {
        List<Channel> channelOut = new ArrayList<Channel>();

        try {
            init();
            // Start UOC
            ChannelDao channelDao = new ChannelDao(conn);
            channelOut = channelDao.searchAllChannel();
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return channelOut;
    }
    
    /**
     * list all the province information of the country.
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   countryId
     * @return  List&ltProvince&gt
     */
    public List<Province> searchChannelById(Integer countryId) {
        List<Province> provinceOut = new ArrayList<Province>();

        try {
            init();

            // Start UOC

            ProvinceDao provinceDao = new ProvinceDao(conn);
            provinceOut = provinceDao.searchProvinceByCountryId(countryId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return provinceOut;
    }

    

    /**
     * get one channel by id.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @param channel
     * @return channel
     */
    public Channel getSingleChannel(Channel channel,boolean exclusive) throws Exception {
    	Channel channelOut = null;

        try {
            init();
            if(channel == null || channel.getId()== null){
            	errors.add("BSF00011");
            	return channelOut;
            }
            // Start UOC
            ChannelDao channelDao = new ChannelDao(conn);
            channelDao.setExclusiveCheck(exclusive);
            channelOut = channelDao.searchChannelById(channel);

            if (null == channelOut) {
                errors.add("BSF00011");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return channelOut;
    }

}
