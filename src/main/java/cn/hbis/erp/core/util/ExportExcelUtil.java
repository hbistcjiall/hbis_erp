package cn.hbis.erp.core.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ExportExcelUtil
 * @Description  excel导出工具
 * @author zhangry12988
 * @date 2017-10-16 13:43
 * @Copyright 2017 hundsun Inc. All rights reserved.
 * 注意：本内容仅限于限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class ExportExcelUtil {
    //private static Log log = LogFactory.getLog(ExportExcelUtil.class);
    // 2007 版本以上 最大支持1048576行
    public final static String EXCEl_FILE_2007 = ".xlsx";
    // 2003 版本 最大支持65536 行007
    public final static String EXCEL_FILE_2003 = ".xls";

    public static HSSFCellStyle getBoldCellStyle(HSSFWorkbook workbook2003) {
        HSSFCellStyle styleBold = getNomalCellStyle(workbook2003);
        HSSFFont font = workbook2003.createFont();
        font.setFontHeightInPoints(HSSFFont.COLOR_RED);
        font.setFontName("宋体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setAlignment(HSSFFont.SS_SUB);
        styleBold.setFont(font);
        return styleBold;
    }

    public static HSSFCellStyle getBoldCellStyle(HSSFWorkbook workbook2003, short color) {
        HSSFCellStyle styleBold = getNomalCellStyle(workbook2003);
        HSSFFont font = workbook2003.createFont();
        font.setFontHeightInPoints(HSSFFont.COLOR_RED);
        font.setFontName("宋体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(color);
        styleBold.setFont(font);
        return styleBold;
    }

    public static HSSFCellStyle getTitleCellStyle(HSSFWorkbook workbook2003) {
        HSSFCellStyle styleTitle = getNomalCellStyle(workbook2003);
        HSSFFont font = workbook2003.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("宋体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleTitle.setFont(font);
        styleTitle.setAlignment(HSSFFont.SS_SUB);
        return styleTitle;
    }

    public static HSSFCellStyle getNomalCellStyle(HSSFWorkbook workbook2003) {
        HSSFCellStyle style = workbook2003.createCellStyle();
        style.setBorderBottom(HSSFFont.SS_SUPER);
        style.setBorderLeft(HSSFFont.SS_SUPER);
        style.setBorderRight(HSSFFont.SS_SUPER);
        style.setBorderTop(HSSFFont.SS_SUPER);
        style.setVerticalAlignment(HSSFFont.SS_SUPER);
        style.setAlignment(HSSFFont.SS_SUB);
        HSSFFont font = workbook2003.createFont();
        font.setFontHeightInPoints(HSSFFont.COLOR_RED);
        font.setFontName("宋体");
        style.setFont(font);
        return style;
    }

    /**
     * @Title ExportExcelUtil.setFileName
     * @Description 设置导出文件名
     * @author zhangry12988
     * @time 2017-10-16 15:05
     * @param fileName
     * @param request
     * @param response
     * @return void
     * @throws
     */
    public static void setFileName(String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            String finalFileName = "";
            String agent = request.getHeader("User-Agent");
            boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
            if (isMSIE) {
                finalFileName = java.net.URLEncoder.encode(fileName + ".xls", "UTF8");
            } else {
                String fileTempName = fileName + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".xls";
                finalFileName = new String(fileTempName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + finalFileName);
        } catch (Exception e) {
            //log.error(e.getMessage());
        }
    }

    public static void setFileName(String fileName, HttpServletRequest request, HttpServletResponse response,
                                   String type) {
        try {
            String finalFileName = "";
            String agent = request.getHeader("User-Agent");
            boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
            if (isMSIE) {
                finalFileName = java.net.URLEncoder.encode(fileName +type, "UTF8");
            } else {
                String fileTempName = fileName + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + type;
                finalFileName = new String(fileTempName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + finalFileName);
        } catch (Exception e) {
            //log.error(e.getMessage());
        }

    }

    /**
     * @Title ExportExcelUtil.setFileHeader
     * @Description 设置表头项
     * @author zhangry12988
     * @time 2017-10-16 15:07
     * @param header
     * @param sheet
     * @param row
     * @param cell
     * @param headStyle
     * @return void
     * @throws
     */
    public static void setFileHeader(List<String> header, HSSFSheet sheet, HSSFRow row, HSSFCell cell,
                                     HSSFCellStyle headStyle) {
        int length = header.size();
        for (int i = 0; i < length; i++) {
            sheet.setColumnWidth(i, 4000);
            cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(header.get(i));
        }
    }

    public static void setFileHeader(String[] header, HSSFSheet sheet, HSSFRow row, HSSFCell cell,
                                     HSSFCellStyle headStyle) {
        int length = header.length;
        for (int i = 0; i < length; i++) {
            sheet.setColumnWidth(i, 4000);
            cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(header[i]);
        }
    }

    public static void setBorder(int style, CellRangeAddress cra, HSSFSheet sheet, HSSFWorkbook workbook) {
        RegionUtil.setBorderTop(style, cra, sheet, workbook);
        RegionUtil.setBorderBottom(style, cra, sheet, workbook);
        RegionUtil.setBorderLeft(style, cra, sheet, workbook);
        RegionUtil.setBorderRight(style, cra, sheet, workbook);
    }

    public static void fillCell(HSSFRow row, int cellnum, HSSFCellStyle style, String value) {
        HSSFCell cell = row.createCell(cellnum);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    public static void fillCell(HSSFRow row, int cellnum, HSSFCellStyle style, Long value) {
        HSSFCell cell = row.createCell(cellnum);
        cell.setCellStyle(style);
        cell.setCellValue((double) value.longValue());
    }

    public static void fillCell(HSSFRow row, int cellnum, HSSFCellStyle style, Double value) {
        HSSFCell cell = row.createCell(cellnum);
        cell.setCellStyle(style);
        cell.setCellValue(value.doubleValue());
    }

    public static void fillCell(HSSFRow row, int cellnum, HSSFCellStyle style, int hSSFCellType, Double value) {
        HSSFCell cell = row.createCell(cellnum);
        cell.setCellStyle(style);
        cell.setCellType(hSSFCellType);
        cell.setCellValue(value.doubleValue());
    }

    public static void fillCell(HSSFRow row, int cellnum, HSSFCellStyle style, int hSSFCellType, String value) {
        HSSFCell cell = row.createCell(cellnum);
        cell.setCellStyle(style);
        cell.setCellType(hSSFCellType);
        cell.setCellValue(value);
    }

    public static void fillCell(HSSFRow row, int cellnum, HSSFCellStyle style, int hSSFCellType, Long value) {
        HSSFCell cell = row.createCell(cellnum);
        cell.setCellStyle(style);
        cell.setCellType(hSSFCellType);
        cell.setCellValue((double) value.longValue());
    }

    public static void mergedRegionCell(HSSFSheet sheet, int startRow, int[] cells) {
        int rows = sheet.getLastRowNum();//获取总行数
        for (int cell : cells) {
            int firstRow = startRow;//合并开始行
            int lastRow = startRow;//合并结束行
            //遍历每一行（从开始行的下一行开始）
            for (int i = startRow + 1; i <= rows; i++) {
                String content = sheet.getRow(i - 1).getCell(cell).getStringCellValue();//上一行的值
                String oldContent = sheet.getRow(i).getCell(cell).getStringCellValue();//当前行的值
                if (content.equals(oldContent)) {//如果内容相等
                    lastRow++;//合并结束行+1
                } else {
                    sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, cell, cell));// 合并单元格
                    sheet.getRow(firstRow).getCell(cell).setCellValue(content);//设置内容
                    firstRow = lastRow + 1;//设置合并开始行：  原结束行+1
                    lastRow = firstRow;//设置合并结束行：原结束行+1
                }
                //如果当前行为最后一行数据并且合并开始行不等于合并结束行
                if (i == rows && firstRow != lastRow) {
                    sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, cell, cell));// 合并单元格
                    sheet.getRow(firstRow).getCell(cell).setCellValue(content);
                }
            }
        }
    }

    /********************************2007及以上版本***************************************/
    /**
     * @Title getTitleCellStyle
     * @Description 2007
     * @param workbook
     * @return CellStyle
     * @throws
     */
    public static CellStyle getTitleCellStyle(SXSSFWorkbook workbook) {
        CellStyle styleTitle = getNomalCellStyle(workbook);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("宋体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleTitle.setFont(font);
        styleTitle.setAlignment(HSSFFont.SS_SUB);
        return styleTitle;
    }

    public static CellStyle getNomalCellStyle(SXSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFFont.SS_SUPER);
        style.setBorderLeft(HSSFFont.SS_SUPER);
        style.setBorderRight(HSSFFont.SS_SUPER);
        style.setBorderTop(HSSFFont.SS_SUPER);
        style.setVerticalAlignment(HSSFFont.SS_SUPER);
        style.setAlignment(HSSFFont.SS_SUB);
        Font font = workbook.createFont();
        font.setFontHeightInPoints(HSSFFont.COLOR_RED);
        font.setFontName("宋体");
        style.setFont(font);
        return style;
    }

    public static CellStyle getBoldCellStyle(SXSSFWorkbook workbook) {
        CellStyle styleBold = getNomalCellStyle(workbook);
        Font font = workbook.createFont();
        font.setFontHeightInPoints(HSSFFont.COLOR_RED);
        font.setFontName("宋体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setAlignment(HSSFFont.SS_SUB);
        styleBold.setFont(font);
        return styleBold;
    }

    public static void fillCell(Row row, int cellnum, CellStyle style, String value) {
        Cell cell = row.createCell(cellnum);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    public static void setFileHeader(String[] header, SXSSFSheet sheet, Row row, Cell cell, CellStyle headStyle) {
        int length = header.length;
        for (int i = 0; i < length; i++) {
            sheet.setColumnWidth(i, 4000);
            cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(header[i]);
        }
    }

    public static void fillCell(Row row, int cellnum, CellStyle style, Double value) {
        Cell cell = row.createCell(cellnum);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }
}
