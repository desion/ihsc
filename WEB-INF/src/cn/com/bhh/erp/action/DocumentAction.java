package cn.com.bhh.erp.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

//import cn.com.bhh.erp.business.DocumentBusiness;
import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.entity.Document;

import com.opensymphony.xwork2.ActionContext;


@SuppressWarnings("serial")
public class DocumentAction extends BaseAction {
    
    private List<Document> documentList = new ArrayList<Document>();
    private List<Integer> companyIdList = new ArrayList<Integer>();
    private List<Integer> interfaceDocProCateList = new ArrayList<Integer>();
    private List<Integer> interfaceDocProductList = new ArrayList<Integer>();
    private Document document = new Document();
    private File docData;
    private String docDataFileName;
    private String docDataContentType;
    private Integer modifyFlag=0;
    
    /**
     * list the document.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String listDocument() throws Exception {
//        DocumentBusiness documentBusiness = new DocumentBusiness();
//        documentList = documentBusiness.getDocumentList(null,loginUser);
//        if (documentBusiness.hasErrors()) {
//            setActionMessages(getMessageText(documentBusiness.getErrors()));
//            return INPUT;
//        }
//        
//        DateFormat dateFormat = new DateFormat();
//        for(Document doc:documentList){
//            doc.setPublishDate(dateFormat.changeDate(doc.getPublishDate()));
//        }
        
        return SUCCESS;
    }
    
    
    /**
     * direct to the document addr page。 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String createProductDocumentPre() throws Exception {  
        document = new Document();
        return SUCCESS;
    }
    
    
    /**
     * upload document.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String createProductDocument() throws Exception {
        List<String> newErrors= new ArrayList<String>();   
        
        DateFormat dateFormat= new DateFormat();
        String publishDate = document.getPublishDate();
        if(publishDate == null || "".equals(publishDate.trim())){
            publishDate = TimeUtil.getNowDate();
        }else{
            publishDate = dateFormat.format(publishDate);
            if(publishDate == null){
                newErrors.add("BSE02306");
            }
        }         
        
        if(docDataFileName == null ||"".equals(docDataFileName.trim())){
            newErrors.add("BSE00019");
        }else {
            
            if(docData.length()<=0){
                newErrors.add("BSE02302");
            }else{
                //从配置文件中读取允许上传的文件最大字节数。
                int maxSize = 5242880;
                try{
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("system");
                    if(resourceBundle!=null){
                        maxSize = Integer.parseInt(resourceBundle.getString("uploadMaxSize"));
                    }else{
                        maxSize = 5242880;
                    }
                }catch(Exception e){
                    
                }
                int maxM = maxSize/(1024*1024);
                if(docData.length()>maxSize){
                  newErrors.add("BSE02303,"+maxM);
                }
                
            }
        }
        
        if(newErrors.size() > 0){
            setActionMessages((getMessageText(newErrors)));
            return INPUT;
        }

//        DocumentBusiness documentBusiness = new DocumentBusiness();
//        
//        document.setName(docDataFileName);
//        document.setCreatorID(loginUser.getId());
//        document.setModifierID(loginUser.getId());
//        document.setPublishDate(publishDate);
//        
//        documentBusiness.createProductDocument(document, docData,interfaceDocProCateList,interfaceDocProductList,companyIdList);
//        if (documentBusiness.hasErrors()) {
//            setActionMessages(getMessageText(documentBusiness.getErrors()));
//            modifyFlag = 1;
//            return INPUT;
//        }
        modifyFlag = 0 ;
      
        return SUCCESS;
    }
    
    
    /**
     * delete the document.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String deleteProductDocument() throws Exception {
   
        if (null == document.getId()) {
            return ILLEGAL_ERR;
        }
//        DocumentBusiness documentBusiness = new DocumentBusiness();
//        documentBusiness.deleteProductDocumentByDocId(document);
//        if (documentBusiness.hasErrors()) {
//            setActionMessages(getMessageText(documentBusiness.getErrors()));
//            return INPUT;
//        }
        
        return SUCCESS;
    }
    
    
    
    /**
     * direct to the document permission
     * edit page.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String directToDetail() throws Exception {
        if (null == document.getId()) {
            return ILLEGAL_ERR;
        }
//        DocumentBusiness documentBusiness = new DocumentBusiness();
//        Document dbDocument = documentBusiness.getDocumentById(document.getId());
//        if (documentBusiness.hasErrors()) {
//            setActionMessages(getMessageText(documentBusiness.getErrors()));
//            return INPUT;
//        }
        
//        DateFormat dateFormat = new DateFormat();
//        dbDocument.setPublishDate(dateFormat.changeDate(dbDocument.getPublishDate()));
//        document =dbDocument;
        
        return SUCCESS;
    }
    
    
    /**
     * direct to the search page.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String searchPre() throws Exception {
        document = new Document();
        return SUCCESS;
    }
    
    
    /**
     * do the search operation.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String documentSearch() throws Exception{
        
        String publishDate = document.getPublishDate();
        if(publishDate != null && !"".equals(document.getPublishDate())){
            DateFormat dateFormat = new DateFormat();
            publishDate = dateFormat.format(publishDate);
            if(publishDate == null){
                List<String> newErrors= new ArrayList<String>();   
                newErrors.add("BSE02306");
                setActionMessages((getMessageText(newErrors)));
                return INPUT;
            }else{
                document.setPublishDate(publishDate);
            }
        }
        
//        DocumentBusiness documentBusiness = new DocumentBusiness();
//        documentList = documentBusiness.getDocumentList(document,loginUser);
//        if (documentBusiness.hasErrors()) {
//            setActionMessages(getMessageText(documentBusiness.getErrors()));
//            return INPUT;
//        }
//        
//        DateFormat dateFormat = new DateFormat();
//        for(Document doc:documentList){
//            doc.setPublishDate(dateFormat.changeDate(doc.getPublishDate()));
//        }
        
        return SUCCESS;
        
    }
    
    
    
    
    /**
     * download the document.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String downloadProductDocument() throws Exception {
        
        if (null == document.getId()) {
            return ILLEGAL_ERR;
        }
        
//        DocumentBusiness documentBusiness = new DocumentBusiness();
//        Document proDataDocument = documentBusiness.searchProductDocumentById(document);
//        if (documentBusiness.hasErrors()) {
//            setActionMessages(getMessageText(documentBusiness.getErrors()));
//            return INPUT;
//        }
//        
//        ActionContext.getContext().getActionInvocation().getProxy().setExecuteResult(false);
//        HttpServletResponse response = ServletActionContext.getResponse();
////        response.setContentType("application/octet-stream");
//        String docName = document.getName();
//        BufferedOutputStream bufferedOutputStream = null;
//        BufferedInputStream bufferedInputStream  = null;
//        try{
//            String downloadName = URLEncoder.encode(docName, "UTF-8");
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("Content-Disposition", "attachment; filename="+downloadName);
//            bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
//            bufferedInputStream = new BufferedInputStream(proDataDocument.getDocFile().getBinaryStream());
//            
//            byte[] buf = new byte[1024] ;
//            int length = -1;
//            while((length = bufferedInputStream.read(buf)) != -1){
//                bufferedOutputStream.write(buf,0,length);
//            }
//        }catch(Exception ioe){
//            
//        }finally{
//            try{
//                if(bufferedInputStream != null){
//                    bufferedInputStream.close();
//                }
//                if(bufferedOutputStream != null){
//                    bufferedOutputStream.close();
//                }
//            }catch(IOException ioe2){
//                
//            }
//        }

        return SUCCESS;
    }
    

    /**
     * @return the docData
     */
    public File getDocData() {
        return docData;
    }


    /**
     * @param docData the docData to set
     */
    public void setDocData(File docData) {
        this.docData = docData;
    }


    /**
     * @return the docDataFileName
     */
    public String getDocDataFileName() {
        return docDataFileName;
    }


    /**
     * @param docDataFileName the docDataFileName to set
     */
    public void setDocDataFileName(String docDataFileName) {
        this.docDataFileName = docDataFileName;
    }


    /**
     * @return the docDataContentType
     */
    public String getDocDataContentType() {
        return docDataContentType;
    }


    /**
     * @param docDataContentType the docDataContentType to set
     */
    public void setDocDataContentType(String docDataContentType) {
        this.docDataContentType = docDataContentType;
    }


    /**
     * @return the documentList
     */
    public List<Document> getDocumentList() {
        return documentList;
    }


    /**
     * @param documentList the documentList to set
     */
    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }


    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }


    /**
     * @param document the document to set
     */
    public void setDocument(Document document) {
        this.document = document;
    }


    /**
     * @return the companyIdList
     */
    public List<Integer> getCompanyIdList() {
        return companyIdList;
    }


    /**
     * @param companyIdList the companyIdList to set
     */
    public void setCompanyIdList(List<Integer> companyIdList) {
        this.companyIdList = companyIdList;
    }


    /**
     * @return the modifyFlag
     */
    public Integer getModifyFlag() {
        return modifyFlag;
    }


    /**
     * @param modifyFlag the modifyFlag to set
     */
    public void setModifyFlag(Integer modifyFlag) {
        this.modifyFlag = modifyFlag;
    }



    /**
     * @return the interfaceDocProCateList
     */
    public List<Integer> getInterfaceDocProCateList() {
        return interfaceDocProCateList;
    }


    /**
     * @param interfaceDocProCateList the interfaceDocProCateList to set
     */
    public void setInterfaceDocProCateList(List<Integer> interfaceDocProCateList) {
        this.interfaceDocProCateList = interfaceDocProCateList;
    }


    /**
     * @return the interfaceDocProductList
     */
    public List<Integer> getInterfaceDocProductList() {
        return interfaceDocProductList;
    }


    /**
     * @param interfaceDocProductList the interfaceDocProductList to set
     */
    public void setInterfaceDocProductList(List<Integer> interfaceDocProductList) {
        this.interfaceDocProductList = interfaceDocProductList;
    }

    
}
