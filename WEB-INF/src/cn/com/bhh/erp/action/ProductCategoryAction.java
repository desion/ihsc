//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import cn.com.bhh.erp.business.ProductCategoryBusiness;
import cn.com.bhh.erp.entity.ProductCategory;

import java.util.ArrayList;
import java.util.List;


/**
 * Action Class of Product Category.
 *
 * @author luyan
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ProductCategoryAction extends BaseAction {
    /** product category information */
    private ProductCategory productCategory = new ProductCategory();

    /** parent product category information */
    private ProductCategory preProductCategory = new ProductCategory();

    /** product category list */
    private List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();

    /** navigation list */
    private List<ProductCategory> navigationList = new ArrayList<ProductCategory>();

    /** navigation string */
    private String navigationString = null;

    /** product category name */
    private String productCategoryName = null;

    /** product category description */
    private String productCategoryDescription = null;

    /** detail button flag */
    private String dtlBtnFlg = null;

    /** business delete right */
    private String delRight = "0";

    /** delete right */
    private String delFRight = "0";

    /** action forward */
    private String actionForward = null;

    /** checked product category ID */
    private Integer checkedPcId;

    /**
     * initialize product category list page action.
     *
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String initProductCategory() throws Exception {
        ProductCategoryBusiness pcb = new ProductCategoryBusiness();

        // get user action rights.
        delRight = "0";
        delFRight = "0";

        if (loginUser.hasPermission("PR001_32")) {
            delRight = "1";
        }

        if (loginUser.hasPermission("PR001_40")) {
            delFRight = "1";
        }

        // get product category List
        productCategory = new ProductCategory();
        productCategoryList = pcb.searchProductCategoryList(loginUser, productCategory, delRight);
        preProductCategory = new ProductCategory();
        productCategoryName = "";
        productCategoryDescription = "";
        dtlBtnFlg = "PR001_11";
        actionForward = "PR001_10";

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));

            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * search sub product category list page action.
     *
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String searchProductCategory() throws Exception {
        ProductCategoryBusiness pcb = new ProductCategoryBusiness();

        // ILLEGAL ERROR
        if (productCategory.getId() == null || productCategory.getId().compareTo(Integer.valueOf(0)) <= 0) {
            return ILLEGAL_ERR;
        }

        // get user action rights.
        delRight = "0";
        delFRight = "0";

        if (loginUser.hasPermission("PR001_32")) {
            delRight = "1";
        }

        if (loginUser.hasPermission("PR001_40")) {
            delFRight = "1";
        }

        // product category exist check
        productCategory = pcb.searchProductCategory(productCategory.getId());

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));
            return editListCommon();
        }

        preProductCategory = productCategory;

        if (productCategory.getCateLevel() == 2) {
            dtlBtnFlg = "PR002_10";
        } else {
            dtlBtnFlg = "PR001_11";
        }

        productCategoryName = productCategory.getName();
        productCategoryDescription = productCategory.getDescription();
        navigationList = pcb.searchProductCategoryNavi(productCategory);
        productCategoryList = pcb.searchProductCategoryList(loginUser, productCategory, delRight);

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));

            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * show product category create page action.
     *
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String showProductCategoryCreate() throws Exception {
        ProductCategoryBusiness pcb = new ProductCategoryBusiness();
        boolean errFlg = false;

        // ILLEGAL ERROR
        if (preProductCategory.getId() != null && preProductCategory.getId().compareTo(Integer.valueOf(0)) <= 0) {
            return ILLEGAL_ERR;
        }

        // get user action rights.
        delRight = "0";
        delFRight = "0";

        if (loginUser.hasPermission("PR001_32")) {
            delRight = "1";
        }

        if (loginUser.hasPermission("PR001_40")) {
            delFRight = "1";
        }

        preProductCategory = pcb.searchProductCategoryFr(preProductCategory);

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));
            return ERROR;
        }

        if (preProductCategory.getId() != null) {
            navigationList = pcb.searchProductCategoryNavi(preProductCategory);

            if (pcb.hasErrors()) {
                setActionMessages(getMessageText(pcb.getErrors()));
                errFlg = true;
            }

            if (!errFlg) {
                navigationString = "";

                for (int i = 0; i < navigationList.size(); i++) {
                    navigationString = navigationString + " &gt " + navigationList.get(i).getName();
                }
            }
        }

        productCategory = new ProductCategory();

        return SUCCESS;
    }

    /**
     * insert product category action.
     *
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String insertProductCategory() throws Exception {
        ProductCategoryBusiness pcb = new ProductCategoryBusiness();
        pcb.insertProductCategory(loginUser, preProductCategory, productCategory);

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));

            return INPUT;
        }

        if (!((preProductCategory.getId() != null) && (preProductCategory.getId() > 0))) {
            productCategory = new ProductCategory();

            return "success1";
        }

        productCategory.setId(preProductCategory.getId());

        return "success2";
    }

    /**
     * show product category modify page action.
     *
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String showProductCategoryModify() throws Exception {
        ProductCategoryBusiness pcb = new ProductCategoryBusiness();
        boolean errFlg = false;

        // ILLEGAL ERROR
        if (productCategory.getId() == null
                || productCategory.getId().compareTo(Integer.valueOf(0)) <= 0
                || productCategory.getExclusiveKey() == null
                || productCategory.getExclusiveKey().compareTo(Integer.valueOf(0)) < 0
                || (preProductCategory.getId() != null
                        && preProductCategory.getId().compareTo(Integer.valueOf(0)) <= 0)) {
            return ILLEGAL_ERR;
        }

        // get user action rights.
        delRight = "0";
        delFRight = "0";

        if (loginUser.hasPermission("PR001_32")) {
            delRight = "1";
        }

        if (loginUser.hasPermission("PR001_40")) {
            delFRight = "1";
        }

        // search product category for modify.
        productCategory = pcb.searchProductCategoryFm(productCategory);

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));

            return editListCommon();
        }

        if (preProductCategory.getId() != null) {
            navigationList = pcb.searchProductCategoryNavi(preProductCategory);

            if (pcb.hasErrors()) {
                setActionMessages(getMessageText(pcb.getErrors()));
                errFlg = true;
            }

            if (!errFlg) {
                navigationString = "";

                for (int i = 0; i < navigationList.size(); i++) {
                    navigationString = navigationString + " &gt " + navigationList.get(i).getName();
                }
            }
        } else {
            preProductCategory.setName("");
        }

        return SUCCESS;
    }

    /**
     * modify product category action.
     *
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String modifyProductCategory() throws Exception {
        ProductCategoryBusiness pcb = new ProductCategoryBusiness();

        // ILLEGAL ERROR
        if (productCategory.getId() == null
                || productCategory.getId().compareTo(Integer.valueOf(0)) <= 0
                || productCategory.getExclusiveKey() == null
                || productCategory.getExclusiveKey().compareTo(Integer.valueOf(0)) < 0
                || (preProductCategory.getId() != null
                        && preProductCategory.getId().compareTo(Integer.valueOf(0)) <= 0)) {
            return ILLEGAL_ERR;
        }

        Integer rtnVal = pcb.modifyProductCategory(loginUser, productCategory);

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));

            return INPUT;
        } else {
            if ((rtnVal != null) && (rtnVal >= 0)) {
                productCategory.setExclusiveKey(rtnVal);
            }
        }

        if (INPUT.equals(editListCommon())) {
            return SUCCESS;
        }
        return ERROR;
    }

    /**
     * business delete product category action.
     *
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String deleteProductCategory() throws Exception {
        ProductCategoryBusiness pcb = new ProductCategoryBusiness();

        // ILLEGAL ERROR
        if (productCategory.getId() == null
                || productCategory.getId().compareTo(Integer.valueOf(0)) <= 0
                || productCategory.getExclusiveKey() == null
                || productCategory.getExclusiveKey().compareTo(Integer.valueOf(0)) < 0
                || (preProductCategory.getId() != null
                        && preProductCategory.getId().compareTo(Integer.valueOf(0)) <= 0)) {
            return ILLEGAL_ERR;
        }

        pcb.modifyProductCategoryFd(loginUser, productCategory, 1);

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));
        }

        return editListCommon();
    }

    /**
     * recover product category action.
     *
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String recoverProductCategory() throws Exception {
        ProductCategoryBusiness pcb = new ProductCategoryBusiness();

        // ILLEGAL ERROR
        if (productCategory.getId() == null
                || productCategory.getId().compareTo(Integer.valueOf(0)) <= 0
                || productCategory.getExclusiveKey() == null
                || productCategory.getExclusiveKey().compareTo(Integer.valueOf(0)) < 0
                || (preProductCategory.getId() != null
                        && preProductCategory.getId().compareTo(Integer.valueOf(0)) <= 0)) {
            return ILLEGAL_ERR;
        }

        pcb.modifyProductCategoryFd(loginUser, productCategory, 0);

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));
        }

        return editListCommon();
    }

    /**
     * delete product category action.
     *
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String deleteProductCategoryF() throws Exception {
        ProductCategoryBusiness pcb = new ProductCategoryBusiness();

        // ILLEGAL ERROR
        if (productCategory.getId() == null
                || productCategory.getId().compareTo(Integer.valueOf(0)) <= 0
                || productCategory.getExclusiveKey() == null
                || productCategory.getExclusiveKey().compareTo(Integer.valueOf(0)) < 0
                || (preProductCategory.getId() != null
                        && preProductCategory.getId().compareTo(Integer.valueOf(0)) <= 0)) {
            return ILLEGAL_ERR;
        }

        pcb.deleteProductCategory(productCategory);

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));
        }

        return editListCommon();
    }

    /**
     * search product category list common method.
     *
     * @author luyan
     * @since 1.0
     * @param pcb product category Business
     * @throws Exception
     */
    private String editListCommon() throws Exception {

        ProductCategoryBusiness pcb = new ProductCategoryBusiness();
        // get user action rights.
        delRight = "0";
        delFRight = "0";

        if (loginUser.hasPermission("PR001_32")) {
            delRight = "1";
        }

        if (loginUser.hasPermission("PR001_40")) {
            delFRight = "1";
        }

        if (preProductCategory.getId() == null) {
            productCategory = new ProductCategory();
            productCategoryList = pcb.searchProductCategoryList(loginUser, productCategory, delRight);
            preProductCategory = new ProductCategory();
            productCategoryName = "";
            productCategoryDescription = "";
            dtlBtnFlg = "PR001_11";

            if (pcb.hasErrors()) {
                setActionMessages(getMessageText(pcb.getErrors()));
                return ERROR;
            }

            return INPUT;
        }

        productCategory.setId(preProductCategory.getId());
        productCategory = pcb.searchProductCategory(productCategory.getId());
        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));
            return ERROR;
        }

        preProductCategory = productCategory;
        
        if (productCategory.getCateLevel() == 2) {
            dtlBtnFlg = "PR002_10";
        } else {
            dtlBtnFlg = "PR001_11";
        }

        productCategoryName = productCategory.getName();
        productCategoryDescription = productCategory.getDescription();
        navigationList = pcb.searchProductCategoryNavi(productCategory);
        productCategoryList = pcb.searchProductCategoryList(loginUser, productCategory, delRight);

        if (pcb.hasErrors()) {
            setActionMessages(getMessageText(pcb.getErrors()));
            return ERROR;
        }

        return INPUT;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
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
     * @return the dtlBtnFlg
     */
    public String getDtlBtnFlg() {
        return dtlBtnFlg;
    }

    /**
     * @param dtlBtnFlg the dtlBtnFlg to set
     */
    public void setDtlBtnFlg(String dtlBtnFlg) {
        this.dtlBtnFlg = dtlBtnFlg;
    }

    /**
     * @return the preProductCategory
     */
    public ProductCategory getPreProductCategory() {
        return preProductCategory;
    }

    /**
     * @param preProductCategory the preProductCategory to set
     */
    public void setPreProductCategory(ProductCategory preProductCategory) {
        this.preProductCategory = preProductCategory;
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
    public String getActionForward() {
        return actionForward;
    }

    /**
     * @param actionForward the actionForward to set
     */
    public void setActionForward(String actionForward) {
        this.actionForward = actionForward;
    }

    /**
     * @return the checkedPcId
     */
    public Integer getCheckedPcId() {
        return checkedPcId;
    }

    /**
     * @param checkedPcId the checkedPcId to set
     */
    public void setCheckedPcId(Integer checkedPcId) {
        this.checkedPcId = checkedPcId;
    }

}
