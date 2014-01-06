package cn.com.bhh.erp.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
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
            wsheet.getSettings().setShowGridLines(false);
            WritableCellFormat formatTitle = new WritableCellFormat();  
            formatTitle.setAlignment(Alignment.CENTRE);
            WritableFont font = new WritableFont(WritableFont.createFont("宋体"));
            formatTitle.setFont(font);
            formatTitle.setWrap(false); 
            String[] head = dataList.getHead();
            int[] width = dataList.getWidth();
            for (int i = 0; i < head.length; i++) {
                wsheet.addCell(new Label(i, 0, head[i],formatTitle));
                wsheet.setColumnView(i, width[i]);
            }
            for (int j = 1; j <= dataList.getDataList().size(); j++) {
                ArrayList row = (ArrayList)dataList.getDataList().get(j-1);
                for (int i = 0; i < row.size(); i++) {
                    if (row.get(i) == null) {
                        wsheet.addCell(new Label(i, j, "",formatTitle));
                    } else {
                        wsheet.addCell(new Label(i, j, String.valueOf (row.get(i)),formatTitle));
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
