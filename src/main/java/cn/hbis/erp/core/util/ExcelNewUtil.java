/**
 * FileName: ExcelNewUtil
 * Author:   zhangb
 * Date:     2019/3/24 17:07
 */
package cn.hbis.erp.core.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.util.List;
import java.util.Map;

public class ExcelNewUtil {
    /**
     * 设置表头
     * @param wb
     * @param sheet
     * @param headListMap （_*表示列头从第几列开始，默认0，_C*表示合并*列，_R*表示合并*行）
     * CellRangeAddress(起始行号，结束行号，起始列号，结束列号)
     * 合并行或列
     */
    public static void createExcelHeader(XSSFWorkbook wb, XSSFSheet sheet, List<Map<String, Object>> headListMap){
        sheet.setDefaultColumnWidth(7);
        XSSFRow sr = null;
        XSSFCell sc = null;

        int j=0;//行
        //遍历表头集合
        for (int i = 0; i < headListMap.size(); i++) {
            Map<String, Object> map=headListMap.get(i);
            //创建行
            sr = sheet.createRow(i);
            sc = null;

            int k=0;//列
            int front=0;//上一次的单元格位置
            //表头
            for (String key : map.keySet()) {
                //如果是标题
                if(key.startsWith("head")){
                    int r=0,c=0;
                    //判断是否跨行或者跨列
                    if(key.indexOf("_R")>-1||key.indexOf("_C")>-1){
                        String[] keys=key.split("_");//分组
                        for (int l = 0; l < keys.length; l++) {
                            //跨行
                            if(keys[l].startsWith("R")){
                                //实际值从0开始，因此减一
                                r=Integer.parseInt(keys[l].replaceAll("R", ""))-1;
                            }
                            //跨列
                            else if(keys[l].startsWith("C")){
                                c=Integer.parseInt(keys[l].replaceAll("C", ""))-1;
                                //跨列会让跨过的单元格没有边框线，设置边框线
                                setCellBorder(k+1,k+c,sr,createBorderStyle(wb));
                            }
                        }
                    }
                    //创建单元格
                    sc = sr.createCell(k);
                    sc.setCellValue(map.get(key).toString());
                    //合并列、合并行
                    sheet.addMergedRegion(new CellRangeAddress(0, r, 0, c));
                    //设置标题样式
                    sc.setCellStyle(createHeaderStyle(wb));
                    //设置高度
                    sr.setHeight((short) 800);
                }
                //列头
                else{
                    int r=0,c=0;
                    //判断是否跨行或者跨列
                    if(key.indexOf("_R")>-1||key.indexOf("_C")>-1){
                        String[] keys=key.split("_");//分组
                        for (int l = 0; l < keys.length; l++) {
                            //跨行
                            if(keys[l].startsWith("R")){
                                r=Integer.parseInt(keys[l].replaceAll("R", ""))-1;
                            }
                            //跨列
                            else if(keys[l].startsWith("C")){
                                c=Integer.parseInt(keys[l].replaceAll("C", ""))-1;
                                //跨列会让跨过的单元格没有边框线，设置边框线
                                setCellBorder(k+1,k+c,sr,createBorderStyle(wb));
                                //设置单元格宽度
                                sheet.setColumnWidth(k+c, 10 * 256);
                            }
                        }

                    }
                    //如果前面有跨行的列，第二行需要单元格位置，
                    //如上一行第一列为column_R2，垮了两行，下面那行如果有列，则需要设置列起始位置，如column_1（单元格位置从0开始），从第二列开始
                    else{
                        if(key.indexOf("_")>-1){
                            k=Integer.parseInt(key.split("_")[1]);
                            //判断当前位置与前一次位置是否是连续的，非连续则证明中间有跨行的列，需要设置被跨行的单元格边框线
                            if(k-front>1){
                                setCellBorder(front,k-1,sr,createBorderStyle(wb));
                            }
                            //赋值本次坐标
                            front=k;
                        }
                    }
                    //创建单元格
                    sc = sr.createCell(k);
                    sc.setCellValue(map.get(key).toString());
                    //设置跨行或跨列
                    if (!(j == j+r && k == k+c)) {
                        sheet.addMergedRegion(new CellRangeAddress(j, j+r, k, k+c));
                    }
                    //设置单元格宽度
                    sheet.setColumnWidth(k, 10 * 256);
                    //设置单元格高度
                    sr.setHeight((short) 600);
                    //设置单元格样式
                    sc.setCellStyle(createBorderStyle(wb));
                    k=k+c;
                    k++;
                }
            }
            j++;
        }

    }
    private static String[] setRowStyle={"总计","小计"};//如果包含这些的行，字体样式设为粗体
    /**
     * 表内容
     * @param startRow 开始行
     * @param mergeCols 需要做跨行合并的列（数据库查询出的列名）
     * @param colOrder 列顺序（数据库查询出的列名）Map无序，需要设定排列顺序
     * @param wb
     * @param sheet
     * @param list 表内容集合
     */
    public static void fillExcel(int startRow,String[] mergeCols,String[] colOrder,XSSFWorkbook wb,XSSFSheet sheet,List<Map<String,Object>> list) {
        CellStyle style = wb.createCellStyle();
        style = createBorderStyle(wb);
        @SuppressWarnings("deprecation")
        XSSFRow sr = null;
        XSSFCell sc = null;
        Map<String, Object> frontMap=null;//上一行的数据
        JSONObject merge=new JSONObject();//需要合并的位置
        String mer="";//记录首次出现的行
        for (int i = 0; i < list.size(); i++,startRow++) {
            Map<String, Object> map=list.get(i);
            //创建行
            sr = sheet.createRow(startRow);
            Boolean isBold=false;
            for (int k = 0; k < colOrder.length; k++) {
                //判断是否有需要合并的行
                if(mergeCols.length>0){
                    for (int j = 0, l=0; j < mergeCols.length; j++,l++) {
                        //相等
                        if(colOrder[k].equals(mergeCols[j])){
                            //判断之前是否已经出现过
                            if(mer.indexOf(map.get(colOrder[k]).toString())==-1){
                                //创建单元格
                                sc = sr.createCell(k);
                                sc.setCellValue(map.get(colOrder[k]).toString());
                                //设置单元格样式
                                sc.setCellStyle(createBorderStyle(wb));
                                if(!isBold){
                                    if(ArrayUtils.contains(setRowStyle,map.get(colOrder[k]).toString())){
                                        isBold=true;
                                        sc.setCellStyle(style);
                                    }
                                }else{
                                    sc.setCellStyle(style);
                                }
                                mer+=map.get(colOrder[k]).toString()+",";//记录
                            }else{
                                mer+=map.get(colOrder[k]).toString()+",";//记录
                            }
                            //如果为空，则代表这是第一行，否则需要判断需要合并列值是否与上一次的值相同，相同则合并
                            if(frontMap!=null){
                                //判断值相同
                                if(map.get(colOrder[k]).equals(frontMap.get(colOrder[k]))){
                                    //如果merge对象中存在当前值，则修改结束行，其他不变。
                                    if(merge.containsKey(map.get(colOrder[k]).toString())){
                                        //取出之前值
                                        String merges=merge.get(map.get(colOrder[k])).toString();
                                        String[] arr=merges.split(",");
                                        //修改结束行
                                        merge.put(map.get(colOrder[k]).toString(), arr[0]+","+(startRow)+","+arr[2]+","+arr[2]);
                                    }else{
                                        merge.put(map.get(colOrder[k]).toString(), (startRow-1)+","+(startRow)+","+k+","+k);
                                    }

                                }
                            }
                            break;
                        }else{
                            if(l==mergeCols.length-1){
                                if(map.get(colOrder[k])==null){
                                    if(k>0){
                                        if (sr.getCell(k-1) != null){
                                            sc = sr.getCell(k-1);
                                            if(ArrayUtils.contains(setRowStyle,sc.getStringCellValue())){
                                                sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, k-1, k));
                                            }
                                        }
                                    }
                                }else{
                                    //创建单元格
                                    sc = sr.createCell(k);
                                    sc.setCellValue(map.get(colOrder[k])+"");
                                    //设置单元格样式
                                    sc.setCellStyle(style);
                                    if(!isBold){
                                        if(ArrayUtils.contains(setRowStyle,map.get(colOrder[k]).toString())){
                                            isBold=true;
                                            sc.setCellStyle(style);
                                        }
                                    }else{
                                        sc.setCellStyle(style);
                                    }
                                }
                            }
                        }
                    }
                }else{
                    //创建单元格
                    sc = sr.createCell(k);
                    sc.setCellValue(map.get(colOrder[k]).toString());
                    //设置单元格样式
                    sc.setCellStyle(style);
                    if(!isBold){
                        if(ArrayUtils.contains(setRowStyle,map.get(colOrder[k]).toString())){
                            isBold=true;
                            sc.setCellStyle(style);
                        }
                    }else{
                        sc.setCellStyle(style);
                    }
                }
            }
            frontMap=list.get(i);
        }
        //遍历merge对象，该合并的合并
        for (String key : merge.keySet()) {
            String merges=merge.get(key).toString();
            String[] arr=merges.split(",");
            //设置跨行或跨列
            sheet.addMergedRegion(new CellRangeAddress(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3])));
            for (int j = Integer.parseInt(arr[0])+1; j <= Integer.parseInt(arr[1]); j++) {
                setCellBorder(Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),sheet.getRow(j),style);
            }
        }
    }
    /**
     * 设置单元格
     * @param wb
     * @return
     */
    public static CellStyle createAlignStyle(XSSFWorkbook wb){
        CellStyle style = wb.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }
    /**
     * 设置边框样式
     * @param wb
     * @return
     */
    public static CellStyle createBorderStyle(XSSFWorkbook wb){
        CellStyle style = wb.createCellStyle();
        style.setWrapText(true);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);//设置字体大小
        style.setFont(font);
        return style;
    }
    /**
     * 设置边框样式 粗体
     * @param wb
     * @return
     */
    public static CellStyle createBorderBoldStyle(XSSFWorkbook wb){
        CellStyle style = wb.createCellStyle();
        style.setWrapText(true);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);//设置字体大小
        font.setBold(true);//粗体显示
        style.setFont(font);
        return style;
    }
    /**
     * 设置边框
     * @param start 开始列
     * @param end 结束列
     * @param row 行
     * @param style
     */
    public static void setCellBorder(int start, int end, XSSFRow row,  CellStyle style) {
        for(int i=start;i<=end;i++){
            XSSFCell cell = row.createCell(i);
            cell.setCellValue("");
            cell.setCellStyle(style);
        }
    }
    /**
     * 设置边框
     * @param start 开始列
     * @param end 结束列
     * @param row 行
     * @param style
     */
    public static void setCellBorder1(int start, int end, XSSFRow row,  CellStyle style) {
        for(int i=end+1;i>=start;i--){
            XSSFCell cell = row.createCell(i);
            cell.setCellValue("");
            cell.setCellStyle(style);
        }
    }
    /**
     * 设置边框
     * @param row 行
     * @param val 值
     * @param style
     */
    public static void setCellBorder(int i, XSSFRow row,  CellStyle style, String val) {
        XSSFCell cell = row.createCell(i);
        cell.setCellValue(val);
        cell.setCellStyle(style);
    }
    /**
     * 创建头部样式
     * @param wb
     * @return
     */
    public static CellStyle createHeaderStyle(XSSFWorkbook wb){
        CellStyle style = wb.createCellStyle();
		/*style.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
*/		style.setAlignment(HorizontalAlignment.CENTER); //字体居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
        XSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setBold(true);//粗体显示
        font.setFontHeightInPoints((short) 16);//设置字体大小
        style.setFont(font);
        return style;
    }
    /**
     * 创建头部样式
     * @param wb
     * @param size 字体大小
     * @return
     */
    public static CellStyle createHeaderStyle(XSSFWorkbook wb,int size){
        CellStyle style = wb.createCellStyle();
		/*style.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
*/		style.setAlignment(HorizontalAlignment.CENTER); //字体居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
        XSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setBold(true);//粗体显示
        font.setFontHeightInPoints((short) size);//设置字体大小
        style.setFont(font);
        return style;
    }
}
