package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.AgentProductDao;
import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.dao.InstApplyDao;
import cn.com.bhh.erp.dao.InstallationDao;
import cn.com.bhh.erp.dao.OperationTypeDao;
import cn.com.bhh.erp.dao.ProductCategoryDao;
import cn.com.bhh.erp.dao.ProductDao;
import cn.com.bhh.erp.dao.UserDao;
import cn.com.bhh.erp.entity.Product;
import cn.com.bhh.erp.entity.ProductCategory;
import cn.com.bhh.erp.entity.User;

/**
 * Class of Product Business
 * @author desion
 * @version 1.0
 * @since 1.0
 */
public class ProductBusiness extends BaseBusiness{

    /**
     * get list.
     * @auther  desion
     * @version 1.0
     * @since   1.0
     * @return  list&ltProduct&gt
     */
    public List<Product> getProductList(Product product, User loginUser,int currPage, int pageSize) throws Exception{
        List<Product> productList = new ArrayList<Product>();
        
        try{
            init();
            
            // Start UOC
            ProductDao productDao = new ProductDao(conn);
            int intBegin;
            int intEnd;

            if (currPage <= 1) {
                intBegin = 0;
                intEnd = pageSize;
            } else {
                intBegin = (currPage - 1) * pageSize;
                intEnd = intBegin + pageSize;
            }

            productList = productDao.searchAll(product,loginUser,intBegin,intEnd);
            
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return productList;
    }

    /**
     * get product list
     * @author liugd
     * @since 1.0
     * @return List<Product>
     */
    public List<Product> getProductList(User user) {
        List<Product> productList = new ArrayList<Product>();
        
        try{
            init();
            
            // Start UOC
            if (!user.filter("install_mng_all_data")) {
                AgentProductDao agentProductDao = new AgentProductDao(conn);
                productList = agentProductDao.serchProductByAgentId(user.getCompanyID());
            } else {
                ProductDao productDao = new ProductDao(conn);
//                productList = productDao.searchAll(product , user);
            }
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return productList;
    }
    
    public int getProductCounts(Product product,User loginUser) {
        int counts = 0;

        try {
            init();

            // Start UOC

            ProductDao productDao = new ProductDao(conn);
            counts = productDao.getProducerCount(product,loginUser);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return counts;
    }
    
    
//    /**
//     * 
//     * @auther  xiangzq
//     * @version 1.0
//     * @since   1.0
//     * @param   user
//     * @return  List<Product>
//     * @throws  Exception
//     */
//    public List<Product> getProductModelListByAgent(User user) {
//        List<Product> productList = new ArrayList<Product>();
//        
//        try{
//            init();
//            
//            // Start UOC
//            if (!user.filter("prod_cate_mng_all_data")) {
//                AgentProductDao agentProductDao = new AgentProductDao(conn);
//                productList = agentProductDao.serchProductModelByAgentId(user.getCompanyID());
//            } else {
//                ProductDao productDao = new ProductDao(conn);
//                productList = productDao.searchAllProductModel();
//            }
//            // End UOC
//        } catch (Exception e) {
//            handleException(e);
//        } finally {
//            finish();
//        }
//        
//        return productList;
//    }

//    /**
//     * search product list by product category ID.
//     * @author luyan
//     * @since 1.0
//     * @param productCategoryId product category ID
//     * @param delRight delete right
//     * @return product list
//     */
//    public List<Product> searchProductList(Integer productCategoryId, String delRight) {
//        List<Product> productList = new ArrayList<Product>();
//
//        try{
//            init();
//            
//            // Start UOC
//            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
//            ProductDao productDao = new ProductDao(conn);
//            ProductCategory pcInfo = productCategoryDao.searchById(productCategoryId);
//            boolean errFlg = false;
//            if (pcInfo.getId() == null) {
//                // 数据已被其他用户删除、E
//                errors.add("BSF00006");
//                errFlg = true;
//            }
//            if (!errFlg) {
//                productList = productDao.searchByPcId(productCategoryId, delRight);
//            }
//            // End UOC
//
//        } catch (Exception e) {
//            handleException(e);
//        } finally {
//            finish();
//        }
//        
//        return productList;
//    }
    
    
    
//    public List<Product> searchProductList(Integer productCategoryId,User loginUser) {
//        List<Product> productList = new ArrayList<Product>();
//
//        try{
//            init();
//            
//            // Start UOC
//     
//            ProductDao productDao = new ProductDao(conn);
//            productList = productDao.searchProductByProductCategory(productCategoryId,loginUser);
// 
//            // End UOC
//
//        } catch (Exception e) {
//            handleException(e);
//        } finally {
//            finish();
//        }
//        
//        return productList;
//    }

//    /**
//     * search product information by product ID.
//     * @author luyan
//     * @since 1.0
//     * @param productId product ID
//     * @return product information
//     */
//    public Product searchProduct(Product product) {
//        Product productOut = new Product();
//
//        try{
//            init();
//            
//            // Start UOC
//            ProductDao productDao = new ProductDao(conn);
//            productOut = productDao.searchByIdFm(product);
//            if (productOut.getId() == null) {
//                // 数据已被其他用户删除、E
//                errors.add("BSF00006");
//            }
//            // End UOC
//
//        } catch (Exception e) {
//            handleException(e);
//        } finally {
//            finish();
//        }
//        
//        return productOut;
//    }

    /**
     * 产品分类信息取得。
     * 
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
            if (productCategoryOut.getId() == null || Integer.valueOf(1).equals(productCategoryOut.getDeleted())) {
                // 产品分类已被删除，无法增加产品信息。
                errors.add("BSE01901");
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
     * insert product information.
     * @author luyan
     * @since 1.0
     * @param loginUser login user information
     * @param product product information
     */
    public void insertProduct(User loginUser, Product product) {

        try{
            init();
            
            // Start UOC
            ProductDao productDao = new ProductDao(conn);

            // product model exist check.
            Product tmpProduct = productDao.searchByID(product.getId());
            if (tmpProduct.getId() != null) {

                // 数据已存在。
                errors.add("BSE00000,message.product.model");
                return;
            }

            //insert
            product.setOperateUser(loginUser.getId());
            productDao.insert(product);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return;
    }

//    /**
//     * search product information for modify.
//     * @author luyan
//     * @since 1.0
//     * @param product product information
//     * @return product information
//     */
//    public Product searchProductFm(Product product) {
//        Product productOut = new Product();
//
//        try{
//            init();
//            
//            // Start UOC
//            ProductDao productDao = new ProductDao(conn);
//            productOut = productDao.searchByIdFm(product);
//            if (productOut.getId() == null) {
//                // 数据已被其他用户删除、E
//                errors.add("BSF00006");
//            }
//            // End UOC
//
//        } catch (Exception e) {
//            handleException(e);
//        } finally {
//            finish();
//        }
//        
//        return productOut;
//    }

    /**
     * modify product information.
     * @author luyan
     * @since 1.0
     * @param loginUser login user information
     * @param product product information
     */
    public void modifyProduct(User loginUser, Product product) {

        try{
            init();
            
            // Start UOC
            ProductDao productDao = new ProductDao(conn);

            Product tmpProduct = productDao.searchByID(product.getId());
            if (tmpProduct.getId() == null) {
                errors.add("BSE00006,message.product.model");
                return;
            }

            //update
            product.setOperateUser(loginUser.getId());
            productDao.modifyById(product);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return;
    }

//    /**
//     * modify product information for business delete/recover.
//     * @author luyan
//     * @since 1.0
//     * @param loginUser login user information
//     * @param product product information
//     * @param delFlg delete flag
//     */
//    public void modifyProductFd(User loginUser, Product product, Integer delFlg) {
//
//        try{
//            init();
//
//            // Start UOC
//            ProductDao productDao = new ProductDao(conn);
//
//            //update
//            product.setModifierId(loginUser.getId());
//            productDao.modifyByIdFd(product, delFlg);
//
//            // End UOC
//
//        } catch (Exception e) {
//            handleException(e);
//        } finally {
//            finish();
//        }
//
//        return;
//    }

    /**
     * delete product information.
     * @author luyan
     * @since 1.0
     * @param product product information
     */
    public void deleteProduct(Product product) {

        try{
            init();
            
            // Start UOC
            ProductDao productDao = new ProductDao(conn);

            // delete
            productDao.deleteById(product);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return;
    }
}
