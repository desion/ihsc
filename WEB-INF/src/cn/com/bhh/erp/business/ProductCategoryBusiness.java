//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.AgentProductDao;
import cn.com.bhh.erp.dao.DocumentProductCategoryDao;
import cn.com.bhh.erp.dao.ProductCategoryDao;
import cn.com.bhh.erp.dao.ProductDao;
import cn.com.bhh.erp.entity.AgentProduct;
import cn.com.bhh.erp.entity.Product;
import cn.com.bhh.erp.entity.ProductCategory;
import cn.com.bhh.erp.entity.User;

/**
 * Class of Product Category Business
 * 
 * @author luyan
 * @version 1.0
 * @since 1.0
 */
public class ProductCategoryBusiness extends BaseBusiness {

    /**
     * 产品分类信息取得
     * @author sunyx
     * @version 1.0
     * @since 1.0
     * @return List&ltProductCategory&gt
     */
    public List<ProductCategory> getCategory(User user)  {
        List<ProductCategory> productList = new ArrayList<ProductCategory>();

        try {
            init();

            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);

            if(user.filter("prod_cate_mng_all_data")){
                productList = productCategoryDao.getCategory();
            } else {
                AgentProductDao APDao = new AgentProductDao(conn);
                List<AgentProduct> APDaoOut = new ArrayList<AgentProduct>();
                APDaoOut = APDao.serchByAgentId(user.getCompanyID());
                ProductCategory pCategory = null;
                for(int i=0; i < APDaoOut.size(); i++){
                    pCategory = new ProductCategory();
                    
                    pCategory.setId(APDaoOut.get(i).getPCId());
                    pCategory.setName(APDaoOut.get(i).getPCName());
                    pCategory.setCateLevel(APDaoOut.get(i).getPCCateLevel());
                    pCategory.setDescription(APDaoOut.get(i).getPCDescription());
                    pCategory.setParentId(APDaoOut.get(i).getPCParentId());
                    
                    productList.add(pCategory);
                }
            }
            
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return productList;
    }
   
    /**
     * 获取顶层产品分类
     * @author xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltProductCategory&gt
     */
    public List<ProductCategory> getTopCategory()  {
        List<ProductCategory> productList = new ArrayList<ProductCategory>();

        try {
            init();

            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            productList = productCategoryDao.searchTopLevel();
      
            
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return productList;
    }
    
    /**
     * get the product category list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   loginUser
     * @return  List<ProductCategory>
     */
    public List<ProductCategory> getProductCategoryList(User loginUser)  {
        List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();

        try {
            init();

            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);

            if(loginUser.filter("prod_cate_mng_all_data")){
                productCategoryList = productCategoryDao.getProductCategoryList();
            } else {
                productCategoryList = productCategoryDao.getAgentProductCategoryList(loginUser.getCompanyID());
            }
            
            
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return productCategoryList;
    }
    
    /**
     * get the product category
     * which the cate_level is 3.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   loginUser
     * @return  List<ProductCategory>
     * @throws  Exception
     */
    public List<ProductCategory> getProductCategoryThreeList(User loginUser)  {
        List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();

        try {
            init();

            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);

            if(loginUser.filter("prod_cate_mng_all_data")){
                productCategoryList = productCategoryDao.getProductCategoryThreeList();
            } else {
                productCategoryList = productCategoryDao.getAgentProductCategoryThreeList(loginUser.getCompanyID());
            }
            
            
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return productCategoryList;
    }

    /**
     * 产品分类信息取得。     * 
     * @author luyan
     * @since 1.0
     * @param productCategoryId product category ID
     * @return product category information
     */
    public ProductCategory searchProductCategory(Integer productCategoryId) {
        ProductCategory productCategoryOut = new ProductCategory();

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            productCategoryOut = productCategoryDao.searchById(productCategoryId);
            if (productCategoryOut.getId() == null) {
                // 数据已被其他用户删除。
                errors.add("BSF00006");
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return productCategoryOut;
    }
    
    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   
     * @return  ProductCategory
     * @throws  Exception
     */
    public String searchTopProCateName(String model) {
        String topProCateName = null;

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            topProCateName = productCategoryDao.getTopProCateNameByModel(model);
            if (topProCateName == null) {
                // 最上层产品分类不存在或被删除。
                errors.add("BSE01806");
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return topProCateName;
    }

    /**
     * 产品分类列表取得。     * 
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @param delRight business delete right
     * @return product category list
     */
    public List<ProductCategory> searchProductCategoryList(
            User loginUser, ProductCategory productCategory, String delRight) {
        List<ProductCategory> productCategoryOut = new ArrayList<ProductCategory>();

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            if (loginUser.filter("prod_cate_mng_all_data")) {
                if (productCategory.getId() == null) {
                    productCategoryOut = productCategoryDao.searchLevel1(delRight);
                } else {
                    productCategoryOut = productCategoryDao.searchByParentId(productCategory, delRight);
                }
            } else {
                if (productCategory.getId() == null) {
                    productCategoryOut = productCategoryDao.searchLevel1WA(loginUser.getCompanyID(), delRight);
                } else {
                    productCategoryOut = productCategoryDao
                            .searchByParentIdWA(productCategory, loginUser.getCompanyID(), delRight);
                }
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return productCategoryOut;
    }

    /**
     * get the category list by the parent id.
     * @author xiangzq
     * @since 1.0
     * @param  parentId
     * @return product category list
     */
    public List<ProductCategory> searchProductCategoryListByParentId(Integer parentId) {
        List<ProductCategory> productCategoryOut = new ArrayList<ProductCategory>();

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            productCategoryOut = productCategoryDao.searchByParentId(parentId);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return productCategoryOut;
    }
    
    /**
     * get the category list by the parent id.
     * @author xiangzq
     * @since 1.0
     * @param  parentId
     * @return product category list
     */
    public List<ProductCategory> searchProductCategoryListByParentId(Integer parentId,User loginUser) {
        List<ProductCategory> productCategoryOut = new ArrayList<ProductCategory>();

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            productCategoryOut = productCategoryDao.searchByParentId(parentId,loginUser);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return productCategoryOut;
    }
    
    /**
     * get the level one product category list
     * @author xiangzq
     * @since 1.0
     * @param  parentId
     * @return product category list
     */
    public List<ProductCategory> searchLevelOneProductCategoryList() {
        List<ProductCategory> productCategoryOut = new ArrayList<ProductCategory>();

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            productCategoryOut = productCategoryDao.searchLevel1("0");
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return productCategoryOut;
    }
    /**
     * get the level one product category list
     * @author xiangzq
     * @since 1.0
     * @param  parentId
     * @return product category list
     */
    public List<ProductCategory> searchLevelOneProductCategoryList(User loginUser) {
        List<ProductCategory> productCategoryOut = new ArrayList<ProductCategory>();

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            productCategoryOut = productCategoryDao.searchLevel1("0",loginUser);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return productCategoryOut;
    }

    /**
     * 导航条取得。     * 
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @return navigation list
     */
    public List<ProductCategory> searchProductCategoryNavi(
            ProductCategory productCategory) {
        List<ProductCategory> productCategoryOut = new ArrayList<ProductCategory>();

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            if (productCategory.getId() != null) {
                productCategory = productCategoryDao.searchById(productCategory.getId());
                if (productCategory.getId() == null) {
                    // 父产品分类已被删除，产品分类导航条取得失败。
                    errors.add("BSE01803");
                }
                productCategoryOut.add(0, productCategory);
                while (productCategory.getParentId() != null) {
                    productCategory = productCategoryDao
                            .searchById(productCategory.getParentId().intValue());
                    if (productCategory.getId() == null) {
                        // 父产品分类已被删除，产品分类导航条取得失败。
                        errors.add("BSE01803");
                    }
                    ProductCategory tmpProductCategory = new ProductCategory();
                    tmpProductCategory.setId(productCategory.getId());
                    tmpProductCategory.setName(productCategory.getName());
                    tmpProductCategory.setExclusiveKey(productCategory.getExclusiveKey());
                    productCategoryOut.add(0, tmpProductCategory);
                }
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return productCategoryOut;
    }

    /**
     * 父分类存在check。     * 
     * @author luyan
     * @since 1.0
     * @param productCategory parent product category information
     * @return parent product category information
     */
    public ProductCategory searchProductCategoryFr(
            ProductCategory productCategory) {
        ProductCategory productCategoryOut = new ProductCategory();

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            if (productCategory.getId() != null) {
                productCategoryOut = productCategoryDao.searchById(productCategory.getId());
                if (productCategoryOut.getId() == null || Integer.valueOf(1).equals(productCategoryOut.getDeleted())) {
                    // 父产品分类已被删除，无法增加子产品分类。
                    errors.add("BSE01804");
                }
            } else {
                productCategoryOut.setName("");
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return productCategoryOut;
    }

    /**
     * 分类存在check。     * 
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @return product category information
     */
    public ProductCategory searchProductCategoryFm(
            ProductCategory productCategory) {
        ProductCategory productCategoryOut = new ProductCategory();

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            if (productCategory.getId() != null) {
                productCategoryOut = productCategoryDao.searchByIdFm(productCategory);
                if (productCategoryOut.getId() == null) {
                    // 数据已被其他用户删除。
                    errors.add("BSF00006");
                }
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return productCategoryOut;
    }

    /**
     * 产品分类插入实行。     * 
     * @author luyan
     * @since 1.0
     * @param loginUser user information
     * @param preProductCategory parent product category information
     * @param productCategory product category information
     * @throws Exception
     */
    public void insertProductCategory(User loginUser,
            ProductCategory preProductCategory, ProductCategory productCategory) {

        try {
            init();
            // Start UOC
            Integer tmpParentLevel = 0;
            BigDecimal tmpParentId = null;
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            // parent category exist check.
            if (preProductCategory.getId() != null) {
                ProductCategory parentPC = new ProductCategory();
                parentPC = productCategoryDao.searchById(preProductCategory.getId());
                if (parentPC.getId() == null || Integer.valueOf(1).equals(parentPC.getDeleted())) {
                    // 父产品分类已被删除，无法增加子产品分类。
                    errors.add("BSE01804");
                    return;
                }
                tmpParentLevel = parentPC.getCateLevel();
                tmpParentId = new BigDecimal(parentPC.getId());
            }
            // name exist check.
            List<ProductCategory> pcList = new ArrayList<ProductCategory>();
            pcList = productCategoryDao.searchByName(productCategory.getName().trim());
            if (pcList.size() != 0) {
                if (tmpParentId == null
                        || !pcList.get(pcList.size() - 1).getId().equals(tmpParentId.intValue())) {
                    errors.add("BSE00000,message.productCategory");
                    return;
                }
            }
            // insert
            ProductCategory currPC = new ProductCategory();
            currPC.setName(productCategory.getName().trim());
            currPC.setDescription(productCategory.getDescription().trim());
            currPC.setCateLevel(tmpParentLevel + 1);
            currPC.setParentId(tmpParentId);
            currPC.setCreatorId(loginUser.getId());
            productCategoryDao.insert(currPC);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return;
    }

    /**
     * 产品分类更新实行。     * 
     * @author luyan
     * @since 1.0
     * @param loginUser user information
     * @param productCategory product category information
     * @return new exclusive key
     */
    public Integer modifyProductCategory(User loginUser,
            ProductCategory productCategory) {
        Integer newExKey = null;

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);

            // name exist check.
            List<ProductCategory> pcList = new ArrayList<ProductCategory>();
            pcList = productCategoryDao.searchByName(productCategory.getName());
            if (pcList.size() != 0) {
                boolean errFlg = true;
                for (ProductCategory tmpPC:pcList) {
                    if (tmpPC.getId().equals(productCategory.getId())) {
                        errFlg = false;
                        break;
                    } else if (productCategory.getParentId() != null && tmpPC.getId().equals(productCategory.getParentId().intValue())) {
                        errFlg = false;
                        break;
                    } else if (tmpPC.getParentId() != null && productCategory.getId().equals(tmpPC.getParentId().intValue())) {
                        errFlg = false;
                        break;
                    }
                }
                if (errFlg) {
                    errors.add("BSE00000,message.productCategory");
                    return -1;
                }
            }

            // modify
            ProductCategory currPC = new ProductCategory();
            currPC.setId(productCategory.getId());
            currPC.setName(productCategory.getName().trim());
            currPC.setDescription(productCategory.getDescription().trim());
            currPC.setModifierId(loginUser.getId());
            currPC.setExclusiveKey(productCategory.getExclusiveKey());
            newExKey = productCategoryDao.modifyById(currPC);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return newExKey;
    }

    /**
     * 修改产品分类删除标记。     * 
     * @author luyan
     * @since 1.0
     * @param loginUser user information
     * @param productCategory product category information
     * @param deleteFlg delete flag
     */
    public void modifyProductCategoryFd(User loginUser,
            ProductCategory productCategory, Integer deleteFlg) {

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            ProductDao productDao = new ProductDao(conn);

            // modify
            ProductCategory currPC = new ProductCategory();
            currPC.setId(productCategory.getId());
            currPC.setDeleted(deleteFlg);
            currPC.setModifierId(loginUser.getId());
            currPC.setExclusiveKey(productCategory.getExclusiveKey());
            productCategoryDao.modifyForDelete(currPC);
            productDao.modifyByPcIdFd(currPC);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return;
    }

    /**
     * 删除产品分类信息。     * 
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     */
    public void deleteProductCategory(ProductCategory productCategory) {

        try {
            init();
            // Start UOC
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            ProductDao productDao = new ProductDao(conn);
            AgentProductDao agentProductDao = new AgentProductDao(conn);
            // name exist check.
            ProductCategory currPC = new ProductCategory();
            currPC = productCategoryDao.searchByIdFm(productCategory);
            if (currPC.getId() == null) {
                // 数据已被其他用户删除。
                errors.add("BSF00006");
                return;
            } else if (Integer.valueOf(0).equals(currPC.getDeleted())) {
                // 该数据未被删除，无法永久删除。

                errors.add("BSE00008");
                return;
            }
            // sub category exist check
            if (currPC.getCateLevel() == 3) {
                List<Product> productList = productDao.searchByPcId(productCategory.getId(), "1");
                if (productList.size() != 0) {
                    // 该产品分类下已经存在产品了,请先将该分类下的产品删除再删除该分类。
                    errors.add("BSE01801");
                    return;
                }
            } else {
                List<ProductCategory> subCateList = productCategoryDao.searchByParentId(productCategory, "1");
                if (subCateList.size() != 0) {
                    // 该产品分类下已经存在子分类了,请先将该分类下的子分类删除再删除该分类。
                    errors.add("BSE01802");
                    return;
                }
            }

            // agent exist check
            int count = agentProductDao.searchCountByPcId(productCategory.getId());
            if (count > 0) {
                errors.add("BSE01805");
                return;
            }
            
            
            //删除产品分类对应的文档产品分类关系。modify by xzq
            DocumentProductCategoryDao documentProductCategoryDao = new DocumentProductCategoryDao(conn);
            documentProductCategoryDao.deleteDocumentProductCategoryByProCateId(productCategory.getId());

            // delete
            currPC.setId(productCategory.getId());
            currPC.setExclusiveKey(productCategory.getExclusiveKey());
            productCategoryDao.deleteById(currPC);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return;
    }

}
