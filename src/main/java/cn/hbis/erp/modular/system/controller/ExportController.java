/**
 * FileName: ExportController
 * Author:   zhangb
 * Date:     2019/5/8 8:42
 */
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.core.util.ExcelNewUtil;
import cn.hbis.erp.modular.system.service.ReportProductClassLevelService;
import cn.hbis.erp.modular.system.service.TargetQuantityManagementService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/export")
public class ExportController {
    private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";

    @Autowired
    private ReportProductClassLevelService reportProductClassLevelService;

    @Autowired
    private TargetQuantityManagementService targetQuantityManagementService;

    @ApiOperation(value="品种钢完成情况导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "dw", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String")
    })
    @GetMapping(value = "exportPzgCx")
    public void exportPzgCx(String dw, @RequestParam(required = false) List<String> cx , String startTime, String endTime, HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        startTime=(String) DateUtil.getFirstDayOfMonth(startTime);
        endTime=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String, Object>> list=reportProductClassLevelService.cxexcel(dw,cx,startTime +" 00:00:00",endTime+" 23:59:59");//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C13", "品种钢完成情况(明细)");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "公司名");
        map.put("column2", "开票日期");
        map.put("column3", "实际发货日期");
        map.put("column4", "定价日期");
        map.put("column5", "发票类型");
        map.put("column6", "订单类型");
        map.put("column7", "价格类型");
        map.put("column8", "销售组织");
        map.put("column9", "销售主体");
        map.put("column10", "例会主体");
        map.put("column11", "产品分类");
        map.put("column12", "产品等级");
        map.put("column13", "报表产线");
        map.put("column14", "原产线名");
        map.put("column15", "品种");
        map.put("column16", "材质");
        map.put("column17", "规格");
        map.put("column18", "镀层代码");
        map.put("column19", "物料描述");
        map.put("column20", "合同单位");
        map.put("column21", "送达方");
        map.put("column22", "订单量");
        map.put("column23", "订单价格");
        map.put("column24", "结算量");
        map.put("column25", "结算价格(含税)");
        map.put("column26", "结算金额1");
        map.put("column27", "连接状态");
        map.put("column28", "系统发票号");
        map.put("column29", "交货单");
        map.put("column30", "交货单行");
        map.put("column31", "订单号");
        map.put("column32", "订单行");
        map.put("column33", "采购订单编号");
        map.put("column34", "到站");
        map.put("column35", "产品组编码");
        map.put("column36", "合同备注");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"COMPANYNAME","COMPANY_ID","SALE_BODY",
                "PRODUCT_GRADE","FKIMG","FKDAT","ORDER_MOUNT","ORDER_TYPE_DESCRIBE",
                "SALER_NAME","SALE_GROUP","SS","NAME","PRODUCT_LINE"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "品种钢完成情况（明细）";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
        setResponse(response, list, listmap, colOrder, mergeCols, fileName);
    }

    private void setResponse(HttpServletResponse response, List<Map<String, Object>> list, List<Map<String, Object>> listmap, String[] colOrder, String[] mergeCols, String fileName) {
        OutputStream out;
        try {
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment;filename= "+new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            out = response.getOutputStream();
            exportXlsx(out,fileName,listmap,list,mergeCols,colOrder,response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value="协议户明细列表模板")
    @GetMapping(value = "exportAgreement")
    public void exportAgreement(HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        List<Map<String, Object>> list=new ArrayList<>();
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C10", "协议户明细列表");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "序号");
        map.put("column2", "上传时间");
        map.put("column3", "协议年份");
        map.put("column4", "用户名称(全称)");
        map.put("column5", "品种");
        map.put("column6", "主销售区域");
        map.put("column7", "辅助销售区域一");
        map.put("column8", "辅助销售区域二");
        map.put("column9", "钢厂");
        map.put("column10", "年协议量(吨)");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "协议户明细列表模板";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
        setResponse(response, list, listmap, colOrder, mergeCols, fileName);
    }

    @ApiOperation(value="产品等级价格分布导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "zl", value = "产品大类", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String")
    })
    @GetMapping(value = "exportReport")
    public void exportReport(@RequestParam(required = false) List<String> cx, String startTime, String endTime,String zl, HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        startTime=(String) DateUtil.getFirstDayOfMonth(startTime);
        endTime=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String, Object>> list=reportProductClassLevelService.getcxfb01(cx,startTime +" 00:00:00",endTime+" 23:59:59",zl);//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C20", "产品等级价格分布导出");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1_R2", "产品大类");
        map.put("column2_R2", "产线");
        map.put("column3_R2", "产品等级");
        map.put("column4_R2", "销售量(吨)");
        map.put("column5_R2", "税前售价(元/吨)");
        map.put("column6_C3", "专业公司");
        map.put("column7_C3", "分公司");
        map.put("column8_C3", "事业部");
        map.put("column9_C3", "现货");
        map.put("column10_C3", "自办公司");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column11_5", "销量");
        map.put("column12_6", "均价");
        map.put("column13_7", "销售占比");
        map.put("column14_8", "销量");
        map.put("column15_9", "均价");
        map.put("column16_10", "销售占比");
        map.put("column17_11", "销量");
        map.put("column18_12", "均价");
        map.put("column19_13", "销售占比");
        map.put("column20_14", "销量");
        map.put("column21_15", "均价");
        map.put("column22_16", "销售占比");
        map.put("column23_17", "销量");
        map.put("column24_18", "均价");
        map.put("column25_19", "销售占比");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"ZL","CXNAME","PRODUCT_GRADE","FKIMG",
                "ZSJ","ZYFKIMG","ZYSJ","ZYXSZB","FGSFKIMG","FGSSJ",
                "FGSXSZB","SYBFKIMG","SYBSJ","SYBXSZB","XHFKIMG","XHSJ",
                "XHXSZB","ZBGSFKIMG","ZBGSSJ","ZBGSXSZB"
        };
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "产品等级价格分布导出";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
        setResponse(response, list, listmap, colOrder, mergeCols, fileName);
    }

    @ApiOperation(value="销售结算情况（产线）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "jd", value = "借贷", dataType = "String")
    })
    @GetMapping(value = "exportXSJS")
    public void exportXSJS(String startTime, @RequestParam(required = false) List<String> cx, String endTime, String pz,String jd, HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        List<Map<String, Object>> list=targetQuantityManagementService.xsjswccxexport(startTime,endTime,pz,cx,jd);//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C22", "销售结算情况（产线）");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1_R2", "产品大类");
        map.put("column2_R2", "产线");
        map.put("column3_C3", "集团");
        map.put("column4_C2", "内贸");
        map.put("column5_C6", "销售总公司");
        map.put("column6_C6", "子公司");
        map.put("column7_C2", "自办公司");
        map.put("column8_C2", "出口");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column9_2", "销售量(吨)");
        map.put("column10_3", "平均售价(元/吨)");
        map.put("column11_4", "销售额(万元)");
        map.put("column12_5", "销售量(吨)");
        map.put("column13_6", "平均售价(元/吨)");
        map.put("column14_7", "销售量(吨)");
        map.put("column12_8", "平均售价(元/吨)");
        map.put("column13_9", "专业公司(吨)");
        map.put("column14_10", "平均售价(元/吨)");
        map.put("column15_11", "分公司(吨)");
        map.put("column16_12", "平均售价(元/吨)");
        map.put("column17_13", "销售量(吨)");
        map.put("column18_14", "平均售价(元/吨)");
        map.put("column19_15", "事业部(吨)");
        map.put("column20_16", "平均售价(元/吨)");
        map.put("column15_17", "现货(吨)");
        map.put("column16_18", "平均售价(元/吨)");
        map.put("column17_19", "销售量(吨)");
        map.put("column18_20", "平均售价(元/吨)");
        map.put("column19_21", "销售量(吨)");
        map.put("column20_22", "平均售价(元/吨)");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"ZL","CXNAME","FKIMG","ZSJ","KZWI6","NMFKIMG","NMSJ",
                "XSZGSFKIMG","XSZGSSJ","ZYFKIMG","ZYSJ","FGSFKIMG","FGSSJ","ZGSFKIMG",
                "ZGSSJ","SYBFKIMG","SYBSJ","XHFKIMG","XHSJ","ZBGSFKIMG","ZBGSSJ","CKFKIMG","CKSJ"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "销售结算情况（产线）";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
        setResponse(response, list, listmap, colOrder, mergeCols, fileName);
    }

    public void exportXlsx(OutputStream out, String fileName, List<Map<String, Object>> headListMap, List<Map<String, Object>> dataListMap, String[] mergeCols, String[] colOrder, HttpServletResponse response) {
        XSSFWorkbook wb = new XSSFWorkbook();
        export(out, fileName, headListMap, dataListMap, mergeCols, colOrder, wb);
    }

    static void export(OutputStream out, String fileName, List<Map<String, Object>> headListMap, List<Map<String, Object>> dataListMap, String[] mergeCols, String[] colOrder, XSSFWorkbook wb) {
        try {
            Map<String,Object> map=new HashMap<String,Object>();
            XSSFSheet sheet1 = wb.createSheet(fileName);

            //创建表头
            ExcelNewUtil.createExcelHeader(wb, sheet1, headListMap);
            //填入表内容
            if (dataListMap.size()>0){
                ExcelNewUtil.fillExcel(headListMap.size(),mergeCols,colOrder,wb,sheet1,dataListMap);
            }
            //导出
            wb.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(wb);
            IOUtils.closeQuietly(out);
        }
    }
}
