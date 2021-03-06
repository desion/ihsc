//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;

import cn.com.bhh.erp.business.FaultBusiness;
import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.entity.Fault;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class WeeklyReportDownloadAction extends BaseAction {
    private String strBaseDate;
    private Integer faultType;
    
   
    public String weeklyReportDownload() throws Exception{
        String namePre = java.net.URLEncoder.encode(getText("fileName").trim(), "UTF-8"); 
        String fileName = namePre + "_" + TimeUtil.getNow();
        ActionContext.getContext().getActionInvocation().getProxy().setExecuteResult(false);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/msexcel");
        response.setHeader("Content-Disposition", "attachment; filename=" +fileName+ ".xls");
        OutputStream os = response.getOutputStream();
        List<String> excelErrors=export(os);
        if(excelErrors.size()>0){
            setActionMessages(getMessageText(excelErrors));
            return INPUT;
        }
        
        return SUCCESS;
    }
    
    public String forwardToDownloadPage() throws Exception{
       return SUCCESS;
    }
    
    public void  validateWeeklyReportDownload(){
        //日期时间检查
        if(strBaseDate!=null && !"".equals(strBaseDate.trim())){
            DateFormat dateFormat = new DateFormat();
            List<String> newErrors= new ArrayList<String>();
            strBaseDate = dateFormat.format(strBaseDate, "fault.baseDate");
            if(dateFormat.getErrors().size()>0){
                newErrors.addAll(dateFormat.getErrors());
            }else {
                //检查日期的合法性
                if(dateFormat.dateCheck(strBaseDate)){
                    newErrors.add("BSE00005," + "fault.baseDate");
                }
            }
            if(newErrors.size()>0){
                setActionErrors(getMessageText(newErrors));
            }
        } 
    }
    /**
     * create excel file to the outputStream
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   ouptStream
     * @return  List<String>
     */
    public List<String> export(OutputStream outputStream){
        List<String> errors=new ArrayList<String>();
        WritableWorkbook workBook = null;
        try {
            FaultBusiness faultBusiness = new FaultBusiness();
            List<String> modelList = new ArrayList<String>();
            List<Fault> weeklyFaultList = new ArrayList<Fault>();
            Date dtBaseDate = null;
            if(strBaseDate!=null && !"".equals(strBaseDate.trim())){
                DateFormat dateFormat = new DateFormat();
                dtBaseDate = dateFormat.stringToDate(strBaseDate, "fault.baseDate");
                if(dateFormat.getErrors().size()>0){
                    errors.addAll(dateFormat.getErrors());
                    return errors;
                }
            }else{
                dtBaseDate = new Date();
            }
            
            //上周星期天日期
            String beforeLastSunday=TimeUtil.getBeforeLastSunday(dtBaseDate);
            //本周星期一日期
            String lastMonday=TimeUtil.getLastMonday(dtBaseDate);
            //本周星期天日期
            String lastSunday=TimeUtil.getLastSunday(dtBaseDate);
            
            
            //获取上周一到本周日的代理公司名词列表
            List<String> agentCompanyNameList = faultBusiness.getRepairCompanyNameList(lastSunday,faultType);
            if(faultBusiness.hasErrors()){
                errors.addAll(faultBusiness.getErrors());
                return errors;
            }
            
            workBook = Workbook.createWorkbook(outputStream);
            if(agentCompanyNameList.size() > 0 ){
                for (String agentCompanyName : agentCompanyNameList) {
                    
                    WritableSheet sheet = workBook.createSheet(agentCompanyName, 0);
                    sheet.setPageSetup(PageOrientation.LANDSCAPE, PaperSize.A4, 1.0, 1.0);
                    SheetSettings sheetS = sheet.getSettings();
                    sheetS.setTopMargin(0.5);
                    sheetS.setLeftMargin(0.1);
                    sheetS.setRightMargin(0.1);
                    sheetS.setBottomMargin(0.5);
                    int rowIndex=0;
                    DateFormat dateFormat=new DateFormat();
                    String beforeLastWeekArg[] ={dateFormat.changeDate(beforeLastSunday)};
                    
                    //创建标题
                    sheet.mergeCells(0, rowIndex, 11,rowIndex);
                    String[] args= {"～",dateFormat.changeDate(lastSunday)};
                    String modelNameStr=getText("title",args);
                    Label titleLable = new Label(0, rowIndex, modelNameStr,getCellFormatTitle());
                    sheet.addCell(titleLable);
                    
                    //空两行
                    rowIndex=rowIndex+2;
                    
                    //创建上周未完成故障标题
                    String beforeLastWeekUnConfirmedTitle=getText("beforeLastWeekUnConfirmedTitle",beforeLastWeekArg);
                    sheet.mergeCells(0, rowIndex, 11,rowIndex);
                    Label modelLabel = new Label(0, rowIndex++, beforeLastWeekUnConfirmedTitle);
                    sheet.addCell(modelLabel);
                    //获取上周未完成的该代理公司下的机种名称
                    modelList=faultBusiness.getModelNameList(beforeLastSunday,agentCompanyName,0,faultType);
                    if(faultBusiness.hasErrors()){
                        errors.addAll(faultBusiness.getErrors());
                        return errors;
                    }
                    
                    for(String modelName:modelList){
                   //获取上周该代理公司名称下该机种名对应的未完成的故障列表
                        weeklyFaultList = faultBusiness.getWeeklyFaultList(beforeLastSunday,agentCompanyName,modelName,0,faultType);
                        if(faultBusiness.hasErrors()){
                            errors.addAll(faultBusiness.getErrors());
                            return errors;
                        }
                        rowIndex=generateRecord(sheet,modelName,rowIndex,weeklyFaultList);
                    }
                    
                    rowIndex++;
                    
                    //创建上周完成故障标题
                    
                    String beforeLastWeekConfirmedTitle=getText("beforeLastWeekConfirmedTitle",beforeLastWeekArg);
                    sheet.mergeCells(0, rowIndex, 11,rowIndex);
                    modelLabel = new Label(0, rowIndex++, beforeLastWeekConfirmedTitle);
                    sheet.addCell(modelLabel);
                    //获取上周已完成的该代理公司下的机种名称
                    modelList=faultBusiness.getModelNameList(beforeLastSunday,agentCompanyName,1,faultType);
                    if(faultBusiness.hasErrors()){
                        errors.addAll(faultBusiness.getErrors());
                        return errors;
                    }
                    
                    for(String modelName:modelList){
                    //获取上周该代理公司名称下该机种名对应的已完成的故障列表
                        weeklyFaultList = faultBusiness.getWeeklyFaultList(beforeLastSunday,agentCompanyName,modelName,1,faultType);
                        if(faultBusiness.hasErrors()){
                            errors.addAll(faultBusiness.getErrors());
                            return errors;
                        }
                        rowIndex=generateRecord(sheet,modelName,rowIndex,weeklyFaultList);
                    }
                    
                    rowIndex++;
                    
                  
                    //创建本周受理故障标题
                    String lastWeekFaultTitleArg[] ={dateFormat.changeDate(lastMonday),dateFormat.changeDate(lastSunday)};
                    String lastWeekFaultTitle=getText("lastWeekFaultTitle",lastWeekFaultTitleArg);
                    sheet.mergeCells(0, rowIndex, 11,rowIndex);
                    modelLabel = new Label(0, rowIndex++, lastWeekFaultTitle);
                    sheet.addCell(modelLabel);
                  //获取本周未该代理公司下的机种名称
                    modelList=faultBusiness.getModelNameList(lastMonday,lastSunday,agentCompanyName,faultType);
                    if(faultBusiness.hasErrors()){
                        errors.addAll(faultBusiness.getErrors());
                        return errors;
                    }
                    
                    for(String modelName:modelList){
                   //获取本周该代理公司名称下该机种名对应的故障列表
                        weeklyFaultList = faultBusiness.getWeeklyFaultList(lastMonday,lastSunday,agentCompanyName,modelName,faultType);
                        if(faultBusiness.hasErrors()){
                            errors.addAll(faultBusiness.getErrors());
                            return errors;
                        }
                        rowIndex=generateRecord(sheet,modelName,rowIndex,weeklyFaultList);
                    }
                   
                }
            }else{
                WritableSheet sheet = workBook.createSheet("sheet1", 0);
                sheet.addCell(new Label(0, 0, "No Data!"));
            }
            workBook.write();

        } catch (Exception e) {
             errors.add("BSF00009");
        } finally {
            try {
                if (null != workBook) {
                    workBook.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (Exception e) {
                errors.add("BSF00009");
            }
        }
        return errors;
        
    }
    
  
    /**
     * generate excel row according
     * to the product model name.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   
     * @return  int
     * @throws  Exception
     */
    public int generateRecord(WritableSheet sheet,String modelName,int rowIndex,List<Fault> faultList) throws WriteException {
        int curRowIndex=rowIndex;
        //合并单元格，并添加故障机型小标题
        sheet.mergeCells(0, curRowIndex, 11,curRowIndex);
        String[] args= {modelName};
        String modelNameStr=getText("fault",args);
        Label modelLabel = new Label(0, curRowIndex++, modelNameStr);
        sheet.addCell(modelLabel);
        DateFormat dateFormat=new DateFormat();
        //设置标题行
        int columnIndex = 0;
        sheet.setRowView(curRowIndex, 480, false);
        //项目
        sheet.setColumnView(columnIndex, 6);
        Label proNumberTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.proNumber"), getDetailTitleCellFormat());
        sheet.addCell(proNumberTtlLbl);
        //维修公司(原代理店)
        sheet.setColumnView(columnIndex, 12);
        Label agentCompanyTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.repairCompany"), getDetailTitleCellFormat());
        sheet.addCell(agentCompanyTtlLbl);
        //分类
        Label faultPartTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.faultPart"), getDetailTitleCellFormat());
        sheet.addCell(faultPartTtlLbl);
        //受理日
        sheet.setColumnView(columnIndex, 13);
        Label reportDateTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.reportDate"), getDetailTitleCellFormat());
        sheet.addCell(reportDateTtlLbl);
        //最终用户
        sheet.setColumnView(columnIndex, 20);
        Label customCompanyTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.customCompany"), getDetailTitleCellFormat());
        sheet.addCell(customCompanyTtlLbl);
        //制造号
        sheet.setColumnView(columnIndex, 10);
        Label manufatureNoTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.manufatureNo"), getDetailTitleCellFormat());
        sheet.addCell(manufatureNoTtlLbl);
        //管理No.
        sheet.setColumnView(columnIndex, 20);
        Label managementIdTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.managementId"), getDetailTitleCellFormat());
        sheet.addCell(managementIdTtlLbl);
        //受理内容
        sheet.setColumnView(columnIndex, 20);
        Label apperanceTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.apperance"), getDetailTitleCellFormat());
        sheet.addCell(apperanceTtlLbl);
        //支援内容
        sheet.setColumnView(columnIndex, 35);
        Label supportContentTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.supportContent"), getDetailTitleCellFormat());
        sheet.addCell(supportContentTtlLbl);
        //结果（故障）
        sheet.setColumnView(columnIndex, 20);
        Label resultTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.result"), getDetailTitleCellFormat());
        sheet.addCell(resultTtlLbl);
        //备件状况
        Label partStateTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.partState"), getDetailTitleCellFormat());
        sheet.addCell(partStateTtlLbl);
        //采集数据有无
        sheet.setColumnView(columnIndex, 20);
        Label dataGatherTtlLbl = new Label(columnIndex++, curRowIndex, getText("label.title.dataGather"), getDetailTitleCellFormat());
        sheet.addCell(dataGatherTtlLbl);

        curRowIndex++;

        //设置明细行
        int proNo=1;
        String applyDateUn = getText("fault.applyDate") ;
        String deliverDateUn = getText("fault.deliverDate") ;
        String receiveDateUn = getText("fault.receiveDate") ;
        String replaceDateUn = getText("fault.replaceDate") ;
        String traceLogUn = getText("fault.traceLog") ;
        String faultRecordUn = getText("fault.faultRecord") ;
        String statisticsUn = getText("fault.statistics") ;
        String applicationVersionUn = getText("fault.applicationVersion") ;
        String traceInfomationUn = getText("fault.traceInfomation") ;
        
        for (Fault fault:faultList) {
            columnIndex = 0;
            //设置行高
            sheet.setRowView(curRowIndex, 1200,false);
            
            //设置故障项目序号
            sheet.setColumnView(columnIndex, 6);
            Number proNumber = new Number(columnIndex++, curRowIndex,proNo,getCellFormat());
            sheet.addCell(proNumber);
            
            //维修公司(原代理店)
            sheet.setColumnView(columnIndex, 15);
            Label agentComNameLbl = new Label(columnIndex++, curRowIndex, fault.getRepairCompanyName(),getCellFormat());
            sheet.addCell(agentComNameLbl);
            
            //分类
            Label faultPartNameLbl = new Label(columnIndex++, curRowIndex, fault.getFaultPartName(),getCellFormat());
            sheet.addCell(faultPartNameLbl);
            
            //受理日
            sheet.setColumnView(columnIndex, 13);
            if(fault.getReportDate()!=null && !"".equals(fault.getReportDate())){
                fault.setReportDate(dateFormat.changeDate(fault.getReportDate()));
            }else{
                fault.setReportDate(" ");
            }
            Label reportDateLbl = new Label(columnIndex++, curRowIndex, fault.getReportDate(),getCellFormat());
            sheet.addCell(reportDateLbl);
            
            //最终用户  
            sheet.setColumnView(columnIndex, 20);
            Label customComNameLbl = new Label(columnIndex++, curRowIndex, fault.getCustomerCompanyName(),getCellFormat());
            sheet.addCell(customComNameLbl);
            
            //制造号
            sheet.setColumnView(columnIndex, 10);
            Label manufatureNoLbl = new Label(columnIndex++, curRowIndex, fault.getManufactureNo(),getCellFormat());
            sheet.addCell(manufatureNoLbl);
            
            //管理号
            sheet.setColumnView(columnIndex, 20);
            Label managementIdLbl = new Label(columnIndex++, curRowIndex, fault.getManagementId(),getCellFormat());
            sheet.addCell(managementIdLbl);
            
            //受理内容
            sheet.setColumnView(columnIndex, 20);
            Label apperanceLbl = new Label(columnIndex++, curRowIndex, fault.getAppearance(),getCellFormat());
            sheet.addCell(apperanceLbl);
            
            //支援内容
            sheet.setColumnView(columnIndex, 35);
            String supportContentStr="";
            if(fault.getStrategyDetail()!=null && !"".equals(fault.getStrategyDetail())){
                supportContentStr= fault.getStrategyDetail();
            }else{
                supportContentStr= fault.getStrategy();
            }
            Label supportContentLbl = new Label(columnIndex++, curRowIndex, supportContentStr,getCellFormat());
            sheet.addCell(supportContentLbl);
            
            //结果
            sheet.setColumnView(columnIndex, 20);
            Label resultLbl = new Label(columnIndex++, curRowIndex, fault.getResult(),getCellFormat());
            sheet.addCell(resultLbl);
            
            
            //备件状况
            if(fault.getApplyDate()!=null && !"".equals(fault.getApplyDate())){
                fault.setApplyDate(dateFormat.changeDate(fault.getApplyDate()));
            }else{
                fault.setApplyDate(" ");
            }
            
            if(fault.getDeliverDate()!=null && !"".equals(fault.getDeliverDate())){
                fault.setDeliverDate(dateFormat.changeDate(fault.getDeliverDate()));
            }else{
                fault.setDeliverDate(" ");
            }
                       
            if(fault.getReceiveDate()!=null && !"".equals(fault.getReceiveDate())){
                fault.setReceiveDate(dateFormat.changeDate(fault.getReceiveDate()));
            }else{
                fault.setReceiveDate(" ");
            }
            
            if(fault.getReplaceDate()!=null && !"".equals(fault.getReplaceDate())){
                fault.setReplaceDate(dateFormat.changeDate(fault.getReplaceDate()));
            }else{
                fault.setReplaceDate(" ");
            }
            
        
            
            sheet.setColumnView(columnIndex, 20);
            String partStateStr=
                                applyDateUn+fault.getApplyDate()+"\n" +
                                deliverDateUn+fault.getDeliverDate()+"\n" +
                                receiveDateUn+fault.getReceiveDate()+"\n"+
                                replaceDateUn+fault.getReplaceDate();
            Label partStateLbl = new Label(columnIndex++,curRowIndex, partStateStr,getCellFormat());
            sheet.addCell(partStateLbl);
            
            //采集数据有无
            sheet.setColumnView(columnIndex, 20);
            String dataGather=  
                                traceLogUn + getYesOrNo(fault.getTradeLog())+"\n" +
                                faultRecordUn + getYesOrNo(fault.getFaultRecord())+"\n" +
                                statisticsUn + getYesOrNo(fault.getStatistics())+"\n"+
                                applicationVersionUn + getYesOrNo(fault.getApplicationVersion())+"\n"+
                                traceInfomationUn + getYesOrNo(fault.getTraceInfomation());
            Label dataGatherLbl = new Label(columnIndex,curRowIndex, dataGather,getCellFormat());
            sheet.addCell(dataGatherLbl);
            
            curRowIndex++;
            proNo++;
        }
        
        return curRowIndex;
        
    }
    
   /**
    * 
    * @auther  xiangzq
    * @version 1.0
    * @since   1.0
    * @param   flag
    * @return  String
    */
   public String getYesOrNo(Integer flag) {
       String str="";
       if(flag!=null){
           if(flag.compareTo(1)==0){
               str=getText("yes");
           }else if(flag.compareTo(0)==0){
               str=getText("no");
           }else{
           }
       }else{
           
       }
       return str;
   }
    
   
   
    public WritableCellFormat getCellFormat() throws WriteException {
        WritableCellFormat cellFormat=getCellFormat(Alignment.LEFT);
        cellFormat.setBorder(Border.ALL,BorderLineStyle.THIN);
        return cellFormat;
    }
    
    public WritableCellFormat getCellFormatTitle() throws WriteException {
        return getCellFormat(Alignment.CENTRE);
    }

    /**
     * get detail title format.
     * @author luyan
     * @since 1.0
     * @return cell format
     * @throws WriteException
     */
    public WritableCellFormat getDetailTitleCellFormat() throws WriteException {
        WritableCellFormat cellFormat=getCellFormat(Alignment.CENTRE);
        cellFormat.setBorder(Border.ALL,BorderLineStyle.THIN);
        return cellFormat;
    }

    /**
     * set cell format
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   alignment
     * @return  WritableCellFormat
     * @throws  WriteException
     */
    public WritableCellFormat getCellFormat(Alignment alignment) throws WriteException {
        
        WritableFont fontStyle = new WritableFont(WritableFont.TAHOMA, 9, WritableFont.NO_BOLD, false,
                UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat cellFormat = new WritableCellFormat(fontStyle);
        cellFormat.setWrap(true);
        cellFormat.setAlignment(alignment);
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        return cellFormat;
     }


    /**
     * @return the strBaseDate
     */
    public String getStrBaseDate() {
        return strBaseDate;
    }

    /**
     * @param strBaseDate the strBaseDate to set
     */
    public void setStrBaseDate(String strBaseDate) {
        this.strBaseDate = strBaseDate;
    }

    /**
     * @return the faultType
     */
    public Integer getFaultType() {
        return faultType;
    }

    /**
     * @param faultType the faultType to set
     */
    public void setFaultType(Integer faultType) {
        this.faultType = faultType;
    }
    
    
    
}
