package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.modular.system.entity.ReportSpotPriceBreakdown;
import cn.hbis.erp.modular.system.mapper.ReportSpotPriceBreakdownMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 建材北京市场现货价格 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class ReportSpotPriceBreakdownService extends ServiceImpl<ReportSpotPriceBreakdownMapper, ReportSpotPriceBreakdown> {

    //private final Logger log = Logger.getLogger(ReportSpotPriceBreakdownManager.class);

    @Resource
    private ReportSpotPriceBreakdownMapper reportSpotPriceBreakdownMapper;

    public List<ReportSpotPriceBreakdown> queryDayList(String startMonth, String endMonth, String company) {
        //log.info("== query days list ==");
        System.out.println(reportSpotPriceBreakdownMapper.queryDayList(startMonth, endMonth, company));
        return reportSpotPriceBreakdownMapper.queryDayList(startMonth, endMonth, company);
    }
    public List<ReportSpotPriceBreakdown> queryXunList(String startMonth, String endMonth,String company) {
        //log.info("== query xun list ==");
        System.out.println(reportSpotPriceBreakdownMapper.queryXunList(startMonth, endMonth, company));
        return reportSpotPriceBreakdownMapper.queryXunList(startMonth, endMonth, company);
    }
    public List<ReportSpotPriceBreakdown> queryMonthList(String startMonth, String endMonth,String company) {
        //log.info("== query month list ==");
        System.out.println(reportSpotPriceBreakdownMapper.queryMonthList(startMonth, endMonth, company));
        return reportSpotPriceBreakdownMapper.queryMonthList(startMonth, endMonth, company);
    }
    public List<ReportSpotPriceBreakdown> queryYearList(String startMonth, String endMonth,String company) {
        //log.info("== query year list ==");
        System.out.println(reportSpotPriceBreakdownMapper.queryYearList(startMonth, endMonth, company));
        return reportSpotPriceBreakdownMapper.queryYearList(startMonth, endMonth, company);
    }

    /*public void exportExcel(String startMonth, String endMonth, String company, HttpServletRequest request,
                            HttpServletResponse response) {
        try {
            String fileName = "建材北京市场现货价格分类汇总";
            ExportExcelUtil.setFileName(fileName,request,response);
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            //1.年月日
            List<ReportSpotPriceBreakdown> days = this.queryDayList(startMonth, endMonth, company);
            //2.旬
            List<ReportSpotPriceBreakdown> xuns = this.queryXunList(startMonth, endMonth, company);
            //3.月
            List<ReportSpotPriceBreakdown> months = this.queryMonthList(startMonth, endMonth, company);
            //4.年 度汇总为查询日期所在的年
            startMonth = DateUtil.getFirstDayDateOfYear(endMonth);
            List<ReportSpotPriceBreakdown> years = this.queryYearList(startMonth, endMonth, company);
            //开始导出
            this.createExcel(fileName,response,out,startMonth, endMonth, company,days,xuns,months,years);
            out.flush();
            out.close();
        } catch (Exception e) {
            //log.error(e.getMessage(),e);
        }
    }*/
    /*private void createExcel(String fileName, HttpServletResponse response, OutputStream out,
                             String startMonth, String endMonth, String company, List<ReportSpotPriceBreakdown> days,
                             List<ReportSpotPriceBreakdown> xuns,
                             List<ReportSpotPriceBreakdown> months,
                             List<ReportSpotPriceBreakdown> years) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(fileName);
        for(int i=0;i<41;i++) {
            sheet.setColumnWidth(i, 20*200);
        }
        // 标题
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(25);
        //表头格式
        HSSFCellStyle headStyle = workbook.createCellStyle();
        HSSFFont headFont = workbook.createFont();
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headStyle.setFont(headFont);
        //边框
        headStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
        headStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
        headStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
        headStyle.setBorderRight(CellStyle.BORDER_MEDIUM);

        //内容格式
        HSSFCellStyle styleBold = workbook.createCellStyle();
        styleBold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleBold.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        // 自定义数值格式
        HSSFCellStyle numberStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        numberStyle.setDataFormat(format.getFormat("0.00"));
        numberStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        numberStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中


        fileName = endMonth.substring(0, 4)+"年以来"+fileName;
        //设置表头
        this.fillTitle(workbook,sheet,row,headStyle,fileName);
        //填充数据
        int row_num = 3;
        if(days.isEmpty() && xuns.isEmpty() && months.isEmpty() && years.isEmpty()) {
            row = sheet.createRow(3);
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 40));// 合并单元格
            styleBold.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            ExportExcelUtil.fillCell(row,0, styleBold,"无数据！");
        }
        row_num = this.fillData(row_num,sheet,row,styleBold,numberStyle,days);
        row_num = this.fillData(row_num,sheet,row,styleBold,numberStyle,xuns);
        row_num = this.fillData(row_num,sheet,row,styleBold,numberStyle,months);
        row_num = this.fillData(row_num,sheet,row,styleBold,numberStyle,years);

        //设置需要合并的列
        int [] cells={0,1};
        // sheet startRow  cells
        ExportExcelUtil.mergedRegionCell(sheet,3,cells);

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    /*private int fillData(int row_num, HSSFSheet sheet, HSSFRow row, HSSFCellStyle styleBold, HSSFCellStyle numberStyle, List<ReportSpotPriceBreakdown> datas) {
        if(!datas.isEmpty()) {
            for(ReportSpotPriceBreakdown d : datas) {
                row = sheet.createRow(row_num);
                *//**
                 * 填充时间
                 *//*
                String cell_one ="";
                //填充xx月xx日
                if(null!=d.getOrderDay()) {
                    String month = d.getOrderDay().substring(5,7);
                    String day = d.getOrderDay().substring(8,10);
                    cell_one = Integer.valueOf(month)+"月"+Integer.valueOf(day)+"日";
                }
                //xx月xx旬
                if(null!=d.getOrderMonth() && null!=d.getOrderXun()) {
                    String data =  Integer.valueOf(d.getOrderMonth()) + "月";
                    if("0".equals(d.getOrderXun())) {
                        cell_one = data+"上旬";
                    }
                    if("1".equals(d.getOrderXun())) {
                        cell_one = data+"中旬";
                    }
                    if("2".equals(d.getOrderXun())) {
                        cell_one = data+"下旬";
                    }
                }else if(null!=d.getOrderMonth()){
                    //填充xx月
                    cell_one =  Integer.valueOf(d.getOrderMonth()) + "月";
                }
                //填充xx年
                if(null!=d.getOrderYear()) {
                    cell_one = d.getOrderYear()+"年";
                }
                ExportExcelUtil.fillCell(row,0, styleBold,cell_one);
                *//**
                 * 填充单位
                 *//*
                if(null!=d.getCompany()) {
                    if("XUANGANG".equals(d.getCompany())) {
                        ExportExcelUtil.fillCell(row,1, styleBold,"宣钢");
                    }
                    if("CHENGGANG".equals(d.getCompany())) {
                        ExportExcelUtil.fillCell(row,1, styleBold,"承钢");
                    }
                }else {
                    ExportExcelUtil.fillCell(row,1, styleBold,"小计");
                }
                *//**
                 * 填充类型
                 *//*
                if("1".equals(d.getType())) {
                    ExportExcelUtil.fillCell(row,2, styleBold,"结算数量");
                }else {
                    ExportExcelUtil.fillCell(row,2, styleBold,"结算价格");
                }
                *//**
                 * 填充其他数据
                 *//*
                this.setNumberData(row,numberStyle,d);
                row_num++;
            }
        }
        return row_num;
    }*/
    /**
     * @title ReportSpotPriceBreakdownManager.fillTitle
     * @description 设置表头
     * @author zhangry12988
     * @time 2018-05-30 17:54
     * @param workbook
     * @param sheet
     * @param row
     * @param
     * @param
     * @param fileName
     * @return void
     * @throws
     */
    /*private void fillTitle(HSSFWorkbook workbook, HSSFSheet sheet, HSSFRow row,
                           HSSFCellStyle headStyle,String fileName) {
        //标题填充数据
        sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 0));// 合并单元格
        ExportExcelUtil.fillCell(row, 0, headStyle,"时间");

        sheet.addMergedRegion(new CellRangeAddress(0, 2, 1, 1));// 合并单元格
        ExportExcelUtil.fillCell(row, 1, headStyle,"单位");

        sheet.addMergedRegion(new CellRangeAddress(0, 2, 2, 2));// 合并单元格
        ExportExcelUtil.fillCell(row, 2, headStyle,"");

        CellRangeAddress cell_3 = new CellRangeAddress(0, 0, 3, 40);
        sheet.addMergedRegion(cell_3);// 合并单元格
        ExportExcelUtil.fillCell(row, 3, headStyle,fileName);
        ExportExcelUtil.setBorder(2,cell_3,sheet,workbook);

        row = sheet.createRow(1);
        row.setHeightInPoints(25);
        CellRangeAddress cell_6 = new CellRangeAddress(1, 1, 3, 6);
        sheet.addMergedRegion(cell_6);// 合并单元格
        ExportExcelUtil.fillCell(row, 0, headStyle,"");
        ExportExcelUtil.fillCell(row, 1, headStyle,"");
        ExportExcelUtil.fillCell(row, 2, headStyle,"");
        ExportExcelUtil.fillCell(row, 3, headStyle,"高线（HPB300）");
        ExportExcelUtil.setBorder(2,cell_6,sheet,workbook);

        CellRangeAddress cell_10 =  new CellRangeAddress(1, 1, 7, 10);
        sheet.addMergedRegion(cell_10);// 合并单元格
        ExportExcelUtil.fillCell(row, 7, headStyle,"盘螺（HRB400/400E）");
        ExportExcelUtil.setBorder(2,cell_10,sheet,workbook);

        CellRangeAddress cell_14 =  new CellRangeAddress(1, 1, 11, 14);
        sheet.addMergedRegion(cell_14);// 合并单元格
        ExportExcelUtil.fillCell(row, 11, headStyle,"盘螺（HRB500/500E）");
        ExportExcelUtil.setBorder(2,cell_14,sheet,workbook);

        CellRangeAddress cell_27 = new CellRangeAddress(1, 1, 15, 27);
        sheet.addMergedRegion(cell_27);// 合并单元格
        ExportExcelUtil.fillCell(row, 15, headStyle,"螺纹钢（HRB400/400E）");
        ExportExcelUtil.setBorder(2,cell_27,sheet,workbook);

        CellRangeAddress cell_40 = new CellRangeAddress(1, 1, 28, 40);
        sheet.addMergedRegion(cell_40);// 合并单元格
        ExportExcelUtil.fillCell(row, 28, headStyle,"螺纹钢（HRB500/500E）");
        ExportExcelUtil.setBorder(2,cell_40,sheet,workbook);

        row = sheet.createRow(2);
        row.setHeightInPoints(25);
        ExportExcelUtil.fillCell(row, 0, headStyle,"");
        ExportExcelUtil.fillCell(row, 1, headStyle,"");
        ExportExcelUtil.fillCell(row, 2, headStyle,"");
        ExportExcelUtil.fillCell(row, 3, headStyle,"φ6.5mm");
        ExportExcelUtil.fillCell(row, 4, headStyle,"φ8mm");
        ExportExcelUtil.fillCell(row, 5, headStyle,"φ10mm");
        ExportExcelUtil.fillCell(row, 6, headStyle,"φ12mm");
        ExportExcelUtil.fillCell(row, 7, headStyle,"φ6mm");
        ExportExcelUtil.fillCell(row, 8, headStyle,"φ8mm");
        ExportExcelUtil.fillCell(row, 9, headStyle,"φ10mm");
        ExportExcelUtil.fillCell(row, 10, headStyle,"φ12mm");
        ExportExcelUtil.fillCell(row, 11, headStyle,"φ6mm");
        ExportExcelUtil.fillCell(row, 12, headStyle,"φ8mm");
        ExportExcelUtil.fillCell(row, 13, headStyle,"φ10mm");
        ExportExcelUtil.fillCell(row, 14, headStyle,"φ12mm");
        ExportExcelUtil.fillCell(row, 15, headStyle,"φ10mm");
        ExportExcelUtil.fillCell(row, 16, headStyle,"φ12mm");
        ExportExcelUtil.fillCell(row, 17, headStyle,"φ14mm");
        ExportExcelUtil.fillCell(row, 18, headStyle,"φ16mm");
        ExportExcelUtil.fillCell(row, 19, headStyle,"φ18mm");
        ExportExcelUtil.fillCell(row, 20, headStyle,"φ20mm");
        ExportExcelUtil.fillCell(row, 21, headStyle,"φ22mm");
        ExportExcelUtil.fillCell(row, 22, headStyle,"φ25mm");
        ExportExcelUtil.fillCell(row, 23, headStyle,"φ28mm");
        ExportExcelUtil.fillCell(row, 24, headStyle,"φ32mm");
        ExportExcelUtil.fillCell(row, 25, headStyle,"φ36mm");
        ExportExcelUtil.fillCell(row, 26, headStyle,"φ40mm");
        ExportExcelUtil.fillCell(row, 27, headStyle,"φ18—25mm");

        ExportExcelUtil.fillCell(row, 28, headStyle,"φ10mm");
        ExportExcelUtil.fillCell(row, 29, headStyle,"φ12mm");
        ExportExcelUtil.fillCell(row, 30, headStyle,"φ14mm");
        ExportExcelUtil.fillCell(row, 31, headStyle,"φ16mm");
        ExportExcelUtil.fillCell(row, 32, headStyle,"φ18mm");
        ExportExcelUtil.fillCell(row, 33, headStyle,"φ20mm");
        ExportExcelUtil.fillCell(row, 34, headStyle,"φ22mm");
        ExportExcelUtil.fillCell(row, 35, headStyle,"φ25mm");
        ExportExcelUtil.fillCell(row, 36, headStyle,"φ28mm");
        ExportExcelUtil.fillCell(row, 37, headStyle,"φ32mm");
        ExportExcelUtil.fillCell(row, 38, headStyle,"φ36mm");
        ExportExcelUtil.fillCell(row, 39, headStyle,"φ40mm");
        ExportExcelUtil.fillCell(row, 40, headStyle,"φ18—25mm");
    }*/
    /*private void setNumberData(HSSFRow row, HSSFCellStyle numberStyle, ReportSpotPriceBreakdown d) {
        ExportExcelUtil.fillCell(row,3, numberStyle,d.getGxData1());
        ExportExcelUtil.fillCell(row,4, numberStyle,d.getGxData2());
        ExportExcelUtil.fillCell(row,5, numberStyle,d.getGxData3());
        ExportExcelUtil.fillCell(row,6, numberStyle,d.getGxData4());
        ExportExcelUtil.fillCell(row,7, numberStyle,d.getPlData1());
        ExportExcelUtil.fillCell(row,8, numberStyle,d.getPlData2());
        ExportExcelUtil.fillCell(row,9, numberStyle,d.getPlData3());
        ExportExcelUtil.fillCell(row,10, numberStyle,d.getPlData4());
        ExportExcelUtil.fillCell(row,11, numberStyle,d.getPlData5());
        ExportExcelUtil.fillCell(row,12, numberStyle,d.getPlData6());
        ExportExcelUtil.fillCell(row,13, numberStyle,d.getPlData7());
        ExportExcelUtil.fillCell(row,14, numberStyle,d.getPlData8());
        ExportExcelUtil.fillCell(row,15, numberStyle,d.getLwData1());
        ExportExcelUtil.fillCell(row,16, numberStyle,d.getLwData2());
        ExportExcelUtil.fillCell(row,17, numberStyle,d.getLwData3());
        ExportExcelUtil.fillCell(row,18, numberStyle,d.getLwData4());
        ExportExcelUtil.fillCell(row,19, numberStyle,d.getLwData5());
        ExportExcelUtil.fillCell(row,20, numberStyle,d.getLwData6());
        ExportExcelUtil.fillCell(row,21, numberStyle,d.getLwData7());
        ExportExcelUtil.fillCell(row,22, numberStyle,d.getLwData8());
        ExportExcelUtil.fillCell(row,23, numberStyle,d.getLwData9());
        ExportExcelUtil.fillCell(row,24, numberStyle,d.getLwData10());
        ExportExcelUtil.fillCell(row,25, numberStyle,d.getLwData11());
        ExportExcelUtil.fillCell(row,26, numberStyle,d.getLwData12());
        ExportExcelUtil.fillCell(row,27, numberStyle,d.getLwData13());
        ExportExcelUtil.fillCell(row,28, numberStyle,d.getLwData14());
        ExportExcelUtil.fillCell(row,29, numberStyle,d.getLwData15());
        ExportExcelUtil.fillCell(row,30, numberStyle,d.getLwData16());
        ExportExcelUtil.fillCell(row,31, numberStyle,d.getLwData17());
        ExportExcelUtil.fillCell(row,32, numberStyle,d.getLwData18());
        ExportExcelUtil.fillCell(row,33, numberStyle,d.getLwData19());
        ExportExcelUtil.fillCell(row,34, numberStyle,d.getLwData20());
        ExportExcelUtil.fillCell(row,35, numberStyle,d.getLwData21());
        ExportExcelUtil.fillCell(row,36, numberStyle,d.getLwData22());
        ExportExcelUtil.fillCell(row,37, numberStyle,d.getLwData23());
        ExportExcelUtil.fillCell(row,38, numberStyle,d.getLwData24());
        ExportExcelUtil.fillCell(row,39, numberStyle,d.getLwData25());
        ExportExcelUtil.fillCell(row,40, numberStyle,d.getLwData26());
    }*/

}
