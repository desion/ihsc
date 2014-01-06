package cn.com.bhh.erp.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import cn.com.bhh.erp.business.BaseBusiness;
import cn.com.bhh.erp.entity.DownloadData;


/**
 * excel handle
 * @author liugd
 * @version 1.0
 * @since 1.0
 * @return
 */
public class ExcelHandle extends BaseBusiness{
    /**
     * excel export
     * @author liugd
     * @param os
     * @param dataList
     */
    @SuppressWarnings("unchecked")
    public void export(OutputStream os,DownloadData dataList) {
        jxl.write.WritableWorkbook wbook = null;
        try {
            wbook = Workbook.createWorkbook(os);
            
            WritableSheet wsheet = wbook.createSheet("sheet1",0);
            String[] head = dataList.getHead();
            for (int i = 0; i < head.length; i++) {
                wsheet.addCell(new Label(i, 0, head[i]));    
            }
            
            for (int j = 1; j <= dataList.getDataList().size(); j++) {
                ArrayList row = (ArrayList)dataList.getDataList().get(j-1);
                for (int i = 0; i < row.size(); i++) {
                    if (row.get(i) == null) {
                        wsheet.addCell(new Label(i, j, ""));
                    } else {
                        wsheet.addCell(new Label(i, j, String.valueOf (row.get(i))));
                    }
                }
            }
            wbook.write(); // file out put
        } catch (Exception e) {
            errors.add("BSF00009");
        } finally {
            try {
                if (null != wbook) {
                    wbook.close();
                }
                if (null != os) {
                    os.close();
                }
            } catch (Exception e) {
                errors.add("BSF00009");
            }
        }
    }
    
   
    
    /**
     * excel import
     * @author liugd
     * @param fis:input file stream
     * @param colSum:excel's column summary
     * @return
     */
    @SuppressWarnings("unchecked")
    public ArrayList getExcelList(String uploadFileName, File upload, int colSum) {
        ArrayList excelList = new ArrayList();
        boolean errFlg = false;
        if (uploadFileName == null || "".equals(uploadFileName)) {
            errors.add("BSE00019");
            errFlg = true;
        }
        if (!errFlg) {
            int index = uploadFileName.lastIndexOf('.');
            if (index != -1) {
                if (!".XLS".equals(uploadFileName.substring(index).toUpperCase())) {
                    errors.add("BSE00020");
                    errFlg = true;
                }
            } else {
                errors.add("BSE00020");
                errFlg = true;
            }
        }
        if (!errFlg) {
            Workbook book = null;
            try {
                FileInputStream fis = new FileInputStream(upload);
                book = Workbook.getWorkbook(fis);
                Sheet sheet = book.getSheet(0);
    
                int rsColumns = sheet.getColumns();
                if (rsColumns < colSum) {
                    errors.add("BSE00010," + colSum);
                }
                int rsRows = sheet.getRows();
                if (rsRows < 2) {
                    errors.add("BSE00011");
                }
                if (errors.size() == 0) {
                    ArrayList<String> rowList;
                    String result;
                    for (int i = 1; i < rsRows; i++) {
                        rowList = new ArrayList<String>();
                        for (int j = 0; j < rsColumns; j++) {
                            result = "";
                            Cell cell = sheet.getCell(j, i);
                            result = cell.getContents();
                            result = result.replace('\n', ' ');
                            result = result.trim();
                            rowList.add(result);
                        }
                        excelList.add(rowList);
                    }
                }
            } catch (Exception e) {
                errors.add("BSF00010");
            } finally {
                try {
                    if (book != null) {
                        book.close();
                    }
                } catch (Exception e) {
                    errors.add("BSF00010");
                }
            }
        }
        try {
            if (upload != null && upload.exists()) {
                upload.delete();
            }
        } catch (Exception e) {
            errors.add("BSF00010");
        }
        return excelList;
    }
}
