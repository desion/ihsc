package cn.com.bhh.erp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.com.bhh.erp.business.ProductBusiness;
import cn.com.bhh.erp.business.ProductCategoryBusiness;
import cn.com.bhh.erp.entity.Product;
import cn.com.bhh.erp.entity.ProductCategory;

@SuppressWarnings("serial")
public class ProductAction extends BaseAction {
    static final int three = 3;
    
    /**
     * 存放产品分类信息
     */
    private List<ProductCategory> productCateList = new ArrayList<ProductCategory>();
    
    /**
     * 存放大分类的产品分类信息
     */
    private List<ProductCategory> levelOneList = new ArrayList<ProductCategory>();
    
    /**
     * 存放产品分类数的HashMap
     */
    @SuppressWarnings("unchecked")
    private HashMap<ProductCategory, List> map = new HashMap<ProductCategory,List>();
    
    /** product category ID */
    private Integer productCategoryId = null;
    
    private Integer selectedProvinceId = null;

    /** product category name */
    private String productCategoryName = null;

    /** product category description */
    private String productCategoryDescription = null;

    /** product category deleted flag */
    private String productCategoryDeletedFlg = null;

    /** navigation list */
    private List<ProductCategory> navigationList = new ArrayList<ProductCategory>();

    /** navigation string */
    private String navigationString = null;

    /** product list */
    private List<Product> productList = new ArrayList<Product>();

    /** product information */
    private Product product = new Product();

    /** String of Producer ID */
    private String string_producer_id = null;

    /** String of Operation Type */
    private String strOperationType = null;

    /** String of Customer Loudspeaker Selected */
    private String customerLoudspeakerSel = null;

    /** String of Customer Voice Wizard Selected */
    private String customerVoiceWizardSel = null;

    /** business delete right */
    private String delRight = "0";

    /** delete right */
    private String delFRight = "0";

    /** action forward */
    private String actionForwardP = null;

    /** from self flag */
    private String fromSelfFlg = null;

    /** checked product ID */
    private Integer checkedProductId;
    

    public Integer getSelectedProvinceId() {
		return selectedProvinceId;
	}

	public void setSelectedProvinceId(Integer selectedProvinceId) {
		this.selectedProvinceId = selectedProvinceId;
	}

	/**
     * @return the productCategoryId
     */
    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * @param productCategoryId the productCategoryId to set
     */
    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    /**
     * @return the productCategoryName
     */
    public String getProductCategoryName() {
        return productCategoryName;
    }

    /**
     * @param productCategoryName the productCategoryName to set
     */
    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    /**
     * @return the productCategoryDescription
     */
    public String getProductCategoryDescription() {
        return productCategoryDescription;
    }

    /**
     * @param productCategoryDescription the productCategoryDescription to set
     */
    public void setProductCategoryDescription(String productCategoryDescription) {
        this.productCategoryDescription = productCategoryDescription;
    }

    /**
     * @return the productCategoryDeletedFlg
     */
    public String getProductCategoryDeletedFlg() {
        return productCategoryDeletedFlg;
    }

    /**
     * @param productCategoryDeletedFlg the productCategoryDeletedFlg to set
     */
    public void setProductCategoryDeletedFlg(String productCategoryDeletedFlg) {
        this.productCategoryDeletedFlg = productCategoryDeletedFlg;
    }

    /**
     * @return the navigationList
     */
    public List<ProductCategory> getNavigationList() {
        return navigationList;
    }

    /**
     * @param navigationList the navigationList to set
     */
    public void setNavigationList(List<ProductCategory> navigationList) {
        this.navigationList = navigationList;
    }

    /**
     * @return the navigationString
     */
    public String getNavigationString() {
        return navigationString;
    }

    /**
     * @param navigationString the navigationString to set
     */
    public void setNavigationString(String navigationString) {
        this.navigationString = navigationString;
    }

    /**
     * @return the productList
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * @param productList the productList to set
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the string_producer_id
     */
    public String getString_producer_id() {
        return string_producer_id;
    }

    /**
     * @param string_producer_id the string_producer_id to set
     */
    public void setString_producer_id(String string_producer_id) {
        this.string_producer_id = string_producer_id;
    }

    /**
     * @return the strOperationType
     */
    public String getStrOperationType() {
        return strOperationType;
    }

    /**
     * @param strOperationType the strOperationType to set
     */
    public void setStrOperationType(String strOperationType) {
        this.strOperationType = strOperationType;
    }

    /**
     * @return the customerLoudspeakerSel
     */
    public String getCustomerLoudspeakerSel() {
        return customerLoudspeakerSel;
    }

    /**
     * @param customerLoudspeakerSel the customerLoudspeakerSel to set
     */
    public void setCustomerLoudspeakerSel(String customerLoudspeakerSel) {
        this.customerLoudspeakerSel = customerLoudspeakerSel;
    }

    /**
     * @return the customerVoiceWizardSel
     */
    public String getCustomerVoiceWizardSel() {
        return customerVoiceWizardSel;
    }

    /**
     * @param customerVoiceWizardSel the customerVoiceWizardSel to set
     */
    public void setCustomerVoiceWizardSel(String customerVoiceWizardSel) {
        this.customerVoiceWizardSel = customerVoiceWizardSel;
    }

    /**
     * @return the delRight
     */
    public String getDelRight() {
        return delRight;
    }

    /**
     * @param delRight the delRight to set
     */
    public void setDelRight(String delRight) {
        this.delRight = delRight;
    }

    /**
     * @return the delFRight
     */
    public String getDelFRight() {
        return delFRight;
    }

    /**
     * @param delFRight the delFRight to set
     */
    public void setDelFRight(String delFRight) {
        this.delFRight = delFRight;
    }

    /**
     * @return the actionForward
     */
    public String getActionForwardP() {
        return actionForwardP;
    }

    /**
     * @param actionForward the actionForward to set
     */
    public void setActionForwardP(String actionForwardP) {
        this.actionForwardP = actionForwardP;
    }

    /**
     * @return the fromSelfFlg
     */
    public String getFromSelfFlg() {
        return fromSelfFlg;
    }

    /**
     * @param fromSelfFlg the fromSelfFlg to set
     */
    public void setFromSelfFlg(String fromSelfFlg) {
        this.fromSelfFlg = fromSelfFlg;
    }

    /**
     * @return the checkedProductId
     */
    public Integer getCheckedProductId() {
        return checkedProductId;
    }

    /**
     * @param checkedProductId the checkedProductId to set
     */
    public void setCheckedProductId(Integer checkedProductId) {
        this.checkedProductId = checkedProductId;
    }

    public List<ProductCategory> getProductCateList() {
        return productCateList;
    }

    public void setProductCateList(List<ProductCategory> productCateList) {
        this.productCateList = productCateList;
    }

    public List<ProductCategory> getLevelOneList() {
        return levelOneList;
    }

    public void setLevelOneList(List<ProductCategory> levelOneList) {
        this.levelOneList = levelOneList;
    }

    @SuppressWarnings("unchecked")
    public HashMap<ProductCategory, List> getMap() {
        return map;
    }

    @SuppressWarnings("unchecked")
    public void setMap(HashMap<ProductCategory, List> map) {
        this.map = map;
    }

    /**
     * search product list by product category ID action.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String searchProductList() throws Exception {

        ProductBusiness pb = new ProductBusiness();
        setTotalCount(pb.getProductCounts(product, loginUser));
        productList = pb.getProductList(product, loginUser,currPage, pageSize);
        if (pb.hasErrors()) {
          setActionMessages(getMessageText(pb.getErrors()));
          if (!"PR001_10".equals(actionForwardP) || "true".equals(fromSelfFlg)) {
              return ERROR;
          }
          return INPUT;
      }

//        // ILLEGAL ERROR
//        if (productCategoryId == null || productCategoryId.compareTo(Integer.valueOf(0)) <= 0) {
//            return ILLEGAL_ERR;
//        }
//
//        // get user action rights.
//        delRight = "0";
//        delFRight = "0";
//        if (loginUser.hasPermission("PR002_32")) {
//            delRight = "1";
//        }
//        if (loginUser.hasPermission("PR002_40")) {
//            delFRight = "1";
//        }
//
//        ProductCategory pcInfo = pcb.searchProductCategory(productCategoryId);
//        if (pcb.hasErrors()) {
//            setActionMessages(getMessageText(pcb.getErrors()));
//            if (!"PR001_10".equals(actionForwardP) || "true".equals(fromSelfFlg)) {
//                return ERROR;
//            }
//            return INPUT;
//        }
//        ProductCategory productCategory = new ProductCategory();
//        productCategory.setId(productCategoryId);
//        navigationList = pcb.searchProductCategoryNavi(productCategory);
//        if (pcb.hasErrors()) {
//            setActionMessages(getMessageText(pcb.getErrors()));
//            if (!"PR001_10".equals(actionForwardP) || "true".equals(fromSelfFlg)) {
//                return ERROR;
//            }
//            return INPUT;
//        }
//        productCategoryName = pcInfo.getName();
//        productCategoryDescription = pcInfo.getDescription();
//        productCategoryDeletedFlg = pcInfo.getDeleted().toString();
//        productList = pb.searchProductList(productCategoryId, delRight);
//        if (pb.hasErrors()) {
//            setActionMessages(getMessageText(pb.getErrors()));
//            if (!"PR001_10".equals(actionForwardP) || "true".equals(fromSelfFlg)) {
//                return ERROR;
//            }
//            return INPUT;
//        }

        return SUCCESS;
    }

    /**
     * PR003:product category tree
     * @author sunyx
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String getCategory() throws Exception{
        ProductCategoryBusiness productCategoryBusiness = new ProductCategoryBusiness();
        productCateList = productCategoryBusiness.getCategory(loginUser);
        if (productCategoryBusiness.hasErrors()) {
            setActionMessages(getMessageText(productCategoryBusiness.getErrors()));
            return ERROR;
        }
        
        ProductBusiness productBusiness = new ProductBusiness();
//        productList = productBusiness.getModelList();
        if (productBusiness.hasErrors()) {
            setActionMessages(getMessageText(productBusiness.getErrors()));
            return ERROR;
        }
        
        Iterator<ProductCategory> iteratorCate = productCateList.iterator();
        while(iteratorCate.hasNext()){
            ProductCategory productCategory = iteratorCate.next();
            List list = null;
            if(productCategory.getParentId() != null){
                Iterator<ProductCategory> iteratorParent = productCateList.iterator();
                while(iteratorParent.hasNext()){
                    ProductCategory productParent = iteratorParent.next();
                    if(productParent.getId().compareTo(productCategory.getParentId().intValue()) == 0){
                        if(map.get(productParent) != null){
                            list = map.get(productParent);
                        } else {
                            list = new ArrayList();
                        }
                        list.add(productCategory);
                        map.put(productParent, list);
                        break;
                    }
                }
            } else {
                levelOneList.add(productCategory);
            }
            
            if(productCategory.getCateLevel().equals(three)){
                Iterator<Product> iterator = productList.iterator();
                while(iterator.hasNext()){
                    Product product = iterator.next();
//                    if(product.getProductCategoryId().equals(productCategory.getId())){
//                        if(map.get(productCategory) != null){
//                            list = map.get(productCategory);
//                        } else {
//                            list = new ArrayList();
//                        }
//                        list.add(product);
//                        map.put(productCategory, list);
//                    }
                }
            }
        }
        
        return SUCCESS;
    }

    /**
     * show product detail action.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String showProductDetail() throws Exception {

        ProductBusiness pb = new ProductBusiness();

//        setActionMessages(getMessageText(pcb.getErrors()));

        return SUCCESS;
    }

    /**
     * show product create action.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String showProductCreate() throws Exception {

        ProductCategoryBusiness pcb = new ProductCategoryBusiness();
        ProductBusiness pb = new ProductBusiness();

        // ILLEGAL ERROR
        if (productCategoryId == null || productCategoryId.compareTo(Integer.valueOf(0)) <= 0) {
            return ILLEGAL_ERR;
        }

        //product category exist check.
        ProductCategory productCategory = pb.searchProductCategory(productCategoryId);
        if (pb.hasErrors()) {
            setActionMessages(getMessageText(pb.getErrors()));
            return ERROR;
        }

        //get navigation.
        navigationList = pcb.searchProductCategoryNavi(productCategory);
        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));
            return ERROR;
        }

        navigationString = "";

        for (int i = 0; i < navigationList.size(); i++) {
            navigationString = navigationString + "&nbsp;&gt;&nbsp;" + navigationList.get(i).getName();
        }

        product = new Product();
//        product.setProductCategoryId(productCategory.getId());
//        product.setProductCategoryName(productCategory.getName());
        productCategoryId = productCategory.getId();
        strOperationType = "";
        customerLoudspeakerSel = "";
        customerVoiceWizardSel = "";

        return SUCCESS;
    }

    /**
     * insert product information.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String insertProduct() throws Exception {

        ProductBusiness pb = new ProductBusiness();

//        if (string_producer_id != null && !"".equals(string_producer_id.trim())) {
//            product.setProducerId(Integer.valueOf(string_producer_id));
//        }
//        if (strOperationType != null && !"".equals(strOperationType.trim())) {
//            product.setOperationType(Integer.valueOf(strOperationType));
//        } else {
//            product.setOperationType(Integer.valueOf(-1));
//        }
//        if (customerLoudspeakerSel != null && !"".equals(customerLoudspeakerSel.trim())) {
//            product.setCustomerLoudspeaker(Integer.valueOf(customerLoudspeakerSel));
//        } else {
//            product.setCustomerLoudspeaker(Integer.valueOf(-1));
//        }
//        if (customerVoiceWizardSel != null && !"".equals(customerVoiceWizardSel.trim())) {
//            product.setCustomerVoiceWizard(Integer.valueOf(customerVoiceWizardSel));
//        } else {
//            product.setCustomerVoiceWizard(Integer.valueOf(-1));
//        }
        pb.insertProduct(loginUser, product);
        if (pb.hasErrors()) {
            setActionMessages(getMessageText(pb.getErrors()));
            return ERROR;
        }

        fromSelfFlg = "true";
        return SUCCESS;
    }

    /**
     * show product modify action.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String showProductModify() throws Exception {

        ProductCategoryBusiness pcb = new ProductCategoryBusiness();
        ProductBusiness pb = new ProductBusiness();

        // ILLEGAL ERROR
        if (product.getId() == null
                || product.getId().compareTo(Integer.valueOf(0)) <= 0
                || product.getExclusiveKey() == null
                || product.getExclusiveKey().compareTo(Integer.valueOf(0)) < 0) {
            return ILLEGAL_ERR;
        }

        //product category exist check.
//        Product tmpProduct = pb.searchProductFm(product);
//        if (pb.hasErrors()) {
//            setActionMessages(getMessageText(pb.getErrors()));
//            fromSelfFlg = "true";
//            return INPUT;
//        }
//        product = tmpProduct;
//
//        //get navigation.
//        ProductCategory productCategory = new ProductCategory();
//        productCategory.setId(product.getProductCategoryId());
//        navigationList = pcb.searchProductCategoryNavi(productCategory);
        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));
            fromSelfFlg = "true";
            return ERROR;
        }

        navigationString = "";

        for (int i = 0; i < navigationList.size(); i++) {
            navigationString = navigationString + "&nbsp;&gt;&nbsp;" + navigationList.get(i).getName();
        }

//        productCategoryId = product.getProductCategoryId();
//        string_producer_id = product.getProducerId().toString();
//        if (product.getOperationType() != null) {
//            strOperationType = product.getOperationType().toString();
//        } else {
//            strOperationType = "";
//        }
//        customerLoudspeakerSel = product.getCustomerLoudspeaker().toString();
//        customerVoiceWizardSel = product.getCustomerVoiceWizard().toString();

        return SUCCESS;
    }
    
    public void outXml(Product product, String code) throws Exception{
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");  
        sb.append("<product>"); 
        sb.append("<index>");
        sb.append(product.getIndex());
        sb.append("</index>");
        sb.append("<id>");
        sb.append(product.getId());
        sb.append("</id>");
        sb.append("<price>");
        sb.append(product.getPrice());
        sb.append("</price>");
        sb.append("<status>");
        sb.append(product.getStatusName());
        sb.append("</status>");
        sb.append("<code>");
        sb.append(code);
        sb.append("</code>");
        sb.append("</product>");  
        HttpServletResponse response = ServletActionContext.getResponse();  
        //设置编码  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out = response.getWriter();  
        out.write(sb.toString());  
        out.flush();  
        out.close(); 
     }

    /**
     * modify product information.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String modifyProduct() throws Exception {

        ProductBusiness pb = new ProductBusiness();
        if(product.getIndex() == null){
        	return ERROR;
        }
        
        pb.modifyProduct(loginUser, product);
        if (pb.hasErrors()) {
            setActionMessages(getMessageText(pb.getErrors()));
            fromSelfFlg = "true";
            return ERROR;
        }
        outXml(product,SUCCESS);
        return SUCCESS;
    }

    /**
     * business delete product information action.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String deleteProduct() throws Exception {

        ProductBusiness pb = new ProductBusiness();

        // ILLEGAL ERROR
        if (product.getId() == null
                || product.getId().compareTo(Integer.valueOf(0)) <= 0
                || product.getExclusiveKey() == null
                || product.getExclusiveKey().compareTo(Integer.valueOf(0)) < 0) {
            return ILLEGAL_ERR;
        }

        // business delete
        fromSelfFlg = "true";
//        pb.modifyProductFd(loginUser, product, 1);
        if (pb.hasErrors()) {
            setActionMessages(getMessageText(pb.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * recover product information action.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String recoverProduct() throws Exception {

        ProductBusiness pb = new ProductBusiness();

        // ILLEGAL ERROR
        if (product.getId() == null
                || product.getId().compareTo(Integer.valueOf(0)) <= 0
                || product.getExclusiveKey() == null
                || product.getExclusiveKey().compareTo(Integer.valueOf(0)) < 0) {
            return ILLEGAL_ERR;
        }

        // recover
        fromSelfFlg = "true";
//        pb.modifyProductFd(loginUser, product, 0);
        if (pb.hasErrors()) {
            setActionMessages(getMessageText(pb.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * delete product information forever action.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String deleteFProduct() throws Exception {

        ProductBusiness pb = new ProductBusiness();

        // ILLEGAL ERROR
        if (product.getId() == null
                || product.getId().compareTo(Integer.valueOf(0)) <= 0
                || product.getExclusiveKey() == null
                || product.getExclusiveKey().compareTo(Integer.valueOf(0)) < 0) {
            return ILLEGAL_ERR;
        }

        // delete
        fromSelfFlg = "true";
        pb.deleteProduct(product);
        if (pb.hasErrors()) {
            setActionMessages(getMessageText(pb.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }

}