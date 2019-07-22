/**
 * FileName: ExportController
 * Author:   zhangb
 * Date:     2019/5/8 8:42
 */
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.core.util.ExcelNewUtil;
import cn.hbis.erp.modular.system.service.AccountabilityUnitManageService;
import cn.hbis.erp.modular.system.service.BasicDataExportService;
import cn.hbis.erp.modular.system.service.ReportProductClassLevelService;
import cn.hbis.erp.modular.system.service.TargetQuantityManagementService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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

    @Autowired
    private AccountabilityUnitManageService accountabilityUnitManageService;

    @Autowired
    private BasicDataExportService  basicDataExportService;


    @ApiOperation(value="品种钢完成情况导出(产线)")
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
        map.put("head_C34", "品种钢完成情况(产线)明细");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "公司名称");
        map.put("column2", "报表月");
        map.put("column3", "开票日期");
        map.put("column4", "结算量");
        map.put("column5", "结算价格");
        map.put("column6", "结算金额");
        map.put("column7", "发票类型");
        map.put("column8", "订单类型描述");
        map.put("column9", "产品等级");
        map.put("column10", "销售组织");
        map.put("column11", "销售主体");
        map.put("column12", "订单数量");
        map.put("column13", "合同单位");
        map.put("column14", "合同号");
        map.put("column15", "定价日期");
        map.put("column16", "产品类别");
        map.put("column17", "产线");
        map.put("column18", "品种");
        map.put("column19", "实际发货日期");
        map.put("column20", "原产线");
        map.put("column21", "材质");
        map.put("column22", "规格");
        map.put("column23", "镀层代码");
        map.put("column24", "物料描述");
        map.put("column25", "付款方名称");
        map.put("column26", "订单金额");
        map.put("column27", "系统发票号");
        map.put("column28", "交货单号");
        map.put("column29", "交货单行");
        map.put("column30", "订单号");
        map.put("column31", "订单行");
        map.put("column32", "到站名称");
        map.put("column33", "产品组编码");
        map.put("column34", "合同备注");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"COMPANYNAME","MONTH","FKDAT","FKIMG",
                "KZWI6","MONEY","FKART","ORDER_TYPE_DESCRIBE","PRODUCT_GRADE",
                "SALE_GROUP","SALE_BODY","ORDER_MOUNT","DESTINATION_NAME","CONTRACT_NO",
                "DJ_DATE","PRODUCT_TYPE","NAME","VARIETY","ACTUAL_DATE",
                "PRODUCT_LINE","ATTRIBUTE1","ATTRIBUTE2","ATTRIBUTE9","MATERIAL_INFO",
                "PAYER_NAME","SALE_PRICE","VBELN","VGBEL","VGPOS",
                "ORDER_NO","ORDER_ROW","DZ_NAME","PRODUCT_CODE","ORDER_NOTE"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "品种钢完成情况（产线）明细";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
        setResponse(response, list, listmap, colOrder, mergeCols, fileName);
    }

    @ApiOperation(value="品种钢完成情况导出(品种)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String")
    })
    @GetMapping(value = "exportPzgPz")
    public void exportPzgPz(String pz, String startTime, String endTime, HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        startTime=(String) DateUtil.getFirstDayOfMonth(startTime);
        endTime=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String, Object>> list=reportProductClassLevelService.pzexcel(pz,startTime,endTime);//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C34", "品种钢完成情况(品种)明细");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "公司名称");
        map.put("column2", "报表月");
        map.put("column3", "开票日期");
        map.put("column4", "结算量");
        map.put("column5", "结算价格");
        map.put("column6", "结算金额");
        map.put("column7", "发票类型");
        map.put("column8", "订单类型描述");
        map.put("column9", "产品等级");
        map.put("column10", "销售组织");
        map.put("column11", "销售主体");
        map.put("column12", "订单数量");
        map.put("column13", "合同单位");
        map.put("column14", "合同号");
        map.put("column15", "定价日期");
        map.put("column16", "产品类别");
        map.put("column17", "产线");
        map.put("column18", "品种");
        map.put("column19", "实际发货日期");
        map.put("column20", "原产线");
        map.put("column21", "材质");
        map.put("column22", "规格");
        map.put("column23", "镀层代码");
        map.put("column24", "物料描述");
        map.put("column25", "付款方名称");
        map.put("column26", "订单金额");
        map.put("column27", "系统发票号");
        map.put("column28", "交货单号");
        map.put("column29", "交货单行");
        map.put("column30", "订单号");
        map.put("column31", "订单行");
        map.put("column32", "到站名称");
        map.put("column33", "产品组编码");
        map.put("column34", "合同备注");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"COMPANYNAME","MONTH","FKDAT","FKIMG",
                "KZWI6","MONEY","FKART","ORDER_TYPE_DESCRIBE","PRODUCT_GRADE",
                "SALE_GROUP","SALE_BODY","ORDER_MOUNT","DESTINATION_NAME","CONTRACT_NO",
                "DJ_DATE","PRODUCT_TYPE","CXNAME","VARIETY","ACTUAL_DATE",
                "PRODUCT_LINE","ATTRIBUTE1","ATTRIBUTE2","ATTRIBUTE9","MATERIAL_INFO",
                "PAYER_NAME","SALE_PRICE","VBELN","VGBEL","VGPOS",
                "ORDER_NO","ORDER_ROW","DZ_NAME","PRODUCT_CODE","ORDER_NOTE"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "品种钢完成情况（品种）明细";
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


    @ApiOperation(value="销售结算情况（产线）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "jd", value = "借贷", dataType = "String"),
            @ApiImplicitParam(name = "pzg", value = "品种钢", dataType = "String")
    })
    @GetMapping(value = "exportJTXSJS")
    public void exportJTXSJS(String startTime, @RequestParam(required = false) List<String> cx, String endTime, String pz,String jd, String pzg,HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        List<Map<String, Object>> list=targetQuantityManagementService.xsjsjtwccxexport(startTime,endTime,pz,cx,jd,pzg);//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C13", "销售结算情况（产线）");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1_R2", "产品大类");
        map.put("column2_R2", "产线");
        map.put("column3_C3", "集团");
        map.put("column4_C2", "内贸");
        map.put("column5_C2", "销售总公司");
        map.put("column6_C2", "子公司");
        map.put("column7_C2", "出口");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column9_2", "销售量(吨)");
        map.put("column10_3", "平均售价(元/吨)");
        map.put("column11_4", "销售额(万元)");
        map.put("column12_5", "销售量(吨)");
        map.put("column13_6", "平均售价(元/吨)");
        map.put("column14_7", "销售量(吨)");
        map.put("column12_8", "平均售价(元/吨)");
        map.put("column13_9", "销售量(吨)");
        map.put("column14_10", "平均售价(元/吨)");
        map.put("column15_11", "销售量(吨)");
        map.put("column16_12", "平均售价(元/吨)");

        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"ZL","CXNAME","FKIMG","ZSJ","KZWI6","NMFKIMG","NMSJ",
                "XSZGSFKIMG","XSZGSSJ","ZGSFKIMG",
                "ZGSSJ","CKFKIMG","CKSJ"};
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
        SXSSFWorkbook wb = new SXSSFWorkbook(500);
        export(out, fileName, headListMap, dataListMap, mergeCols, colOrder, wb);
    }

    static void export(OutputStream out, String fileName, List<Map<String, Object>> headListMap, List<Map<String, Object>> dataListMap, String[] mergeCols, String[] colOrder, SXSSFWorkbook wb) {
        try {
            Map<String,Object> map=new HashMap<String,Object>();
            SXSSFSheet sheet1 = wb.createSheet(fileName);

            //创建表头
            ExcelNewUtil.createExcelHeader(wb, sheet1, headListMap);
            //填入表内容
            if (dataListMap.size()>0){
                ExcelNewUtil.fillExcel(headListMap.size(),mergeCols,colOrder,wb,sheet1,dataListMap);
            }
            //导出
            wb.write(out);
            System.out.println("导出完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(wb);
            IOUtils.closeQuietly(out);
        }
    }

    @ApiOperation(value="销售结算情况（钢厂）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "companyName", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "jd", value = "借贷", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String")
    })
    @GetMapping(value = "exportXSJSGc")
    public void exportXSJSGc(String companyName, String startTime, String endTime,String jd, HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        startTime=(String) DateUtil.getFirstDayOfMonth(startTime);
        endTime=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String, Object>> list=accountabilityUnitManageService.xsegc(companyName,jd,startTime,endTime);//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C34", "销售结算情况（钢厂）明细");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "公司名称");
        map.put("column2", "报表月");
        map.put("column3", "开票日期");
        map.put("column4", "结算量");
        map.put("column5", "结算价格");
        map.put("column6", "结算金额");
        map.put("column7", "发票类型");
        map.put("column8", "订单类型描述");
        map.put("column9", "产品等级");
        map.put("column10", "销售组织");
        map.put("column11", "销售主体");
        map.put("column12", "订单数量");
        map.put("column13", "合同单位");
        map.put("column14", "合同号");
        map.put("column15", "定价日期");
        map.put("column16", "产品类别");
        map.put("column17", "产线");
        map.put("column18", "品种");
        map.put("column19", "实际发货日期");
        map.put("column20", "原产线");
        map.put("column21", "材质");
        map.put("column22", "规格");
        map.put("column23", "镀层代码");
        map.put("column24", "物料描述");
        map.put("column25", "付款方名称");
        map.put("column26", "订单金额");
        map.put("column27", "系统发票号");
        map.put("column28", "交货单号");
        map.put("column29", "交货单行");
        map.put("column30", "订单号");
        map.put("column31", "订单行");
        map.put("column32", "到站名称");
        map.put("column33", "产品组编码");
        map.put("column34", "合同备注");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"COMPANYNAME","MONTH","FKDAT","FKIMG",
                "KZWI6","MONEY","FKART","ORDER_TYPE_DESCRIBE","PRODUCT_GRADE",
                "SALE_GROUP","SALE_BODY","ORDER_MOUNT","DESTINATION_NAME","CONTRACT_NO",
                "DJ_DATE","PRODUCT_TYPE","NAME","VARIETY","ACTUAL_DATE",
                "PRODUCT_LINE","ATTRIBUTE1","ATTRIBUTE2","ATTRIBUTE9","MATERIAL_INFO",
                "PAYER_NAME","SALE_PRICE","VBELN","VGBEL","VGPOS",
                "ORDER_NO","ORDER_ROW","DZ_NAME","PRODUCT_CODE","ORDER_NOTE"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "销售结算情况（钢厂）明细";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
        setResponse(response, list, listmap, colOrder, mergeCols, fileName);
    }

    @ApiOperation(value="销售结算情况(品种)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),
            @ApiImplicitParam(name = "jd", value = "借贷", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String")
    })
    @GetMapping(value = "exportXSJSPz")
    public void exportXSJSPz(String pz, String jd,String startTime, String endTime, HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        startTime=(String) DateUtil.getFirstDayOfMonth(startTime);
        endTime=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String, Object>> list=accountabilityUnitManageService.xsepz(jd,pz,startTime,endTime);//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C34", "销售结算情况(品种)明细");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "公司名称");
        map.put("column2", "报表月");
        map.put("column3", "开票日期");
        map.put("column4", "结算量");
        map.put("column5", "结算价格");
        map.put("column6", "结算金额");
        map.put("column7", "发票类型");
        map.put("column8", "订单类型描述");
        map.put("column9", "产品等级");
        map.put("column10", "销售组织");
        map.put("column11", "销售主体");
        map.put("column12", "订单数量");
        map.put("column13", "合同单位");
        map.put("column14", "合同号");
        map.put("column15", "定价日期");
        map.put("column16", "产品类别");
        map.put("column17", "产线");
        map.put("column18", "品种");
        map.put("column19", "实际发货日期");
        map.put("column20", "原产线");
        map.put("column21", "材质");
        map.put("column22", "规格");
        map.put("column23", "镀层代码");
        map.put("column24", "物料描述");
        map.put("column25", "付款方名称");
        map.put("column26", "订单金额");
        map.put("column27", "系统发票号");
        map.put("column28", "交货单号");
        map.put("column29", "交货单行");
        map.put("column30", "订单号");
        map.put("column31", "订单行");
        map.put("column32", "到站名称");
        map.put("column33", "产品组编码");
        map.put("column34", "合同备注");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"COMPANYNAME","MONTH","FKDAT","FKIMG",
                "KZWI6","MONEY","FKART","ORDER_TYPE_DESCRIBE","PRODUCT_GRADE",
                "SALE_GROUP","SALE_BODY","ORDER_MOUNT","DESTINATION_NAME","CONTRACT_NO",
                "DJ_DATE","PRODUCT_TYPE","CXNAME","VARIETY","ACTUAL_DATE",
                "PRODUCT_LINE","ATTRIBUTE1","ATTRIBUTE2","ATTRIBUTE9","MATERIAL_INFO",
                "PAYER_NAME","SALE_PRICE","VBELN","VGBEL","VGPOS",
                "ORDER_NO","ORDER_ROW","DZ_NAME","PRODUCT_CODE","ORDER_NOTE"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "销售结算情况（品种）明细";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
        setResponse(response, list, listmap, colOrder, mergeCols, fileName);
    }

    @ApiOperation(value="销售结算情况(产线)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),
            @ApiImplicitParam(name = "pzg", value = "品种钢", dataType = "String"),
            @ApiImplicitParam(name = "jd", value = "借贷", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String")
    })
    @GetMapping(value = "exportXSJSCx")
    public void exportXSJSCx(String pzg,String pz,@RequestParam(required = false) List<String> cx,String jd,String startTime, String endTime, HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        startTime=(String) DateUtil.getFirstDayOfMonth(startTime);
        endTime=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String, Object>> list=accountabilityUnitManageService.jtxsecx(pzg,pz,cx,jd,startTime,endTime);//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C34", "销售结算情况(产线)明细");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "公司名称");
        map.put("column2", "报表月");
        map.put("column3", "开票日期");
        map.put("column4", "结算量");
        map.put("column5", "结算价格");
        map.put("column6", "结算金额");
        map.put("column7", "发票类型");
        map.put("column8", "订单类型描述");
        map.put("column9", "产品等级");
        map.put("column10", "销售组织");
        map.put("column11", "销售主体");
        map.put("column12", "订单数量");
        map.put("column13", "合同单位");
        map.put("column14", "合同号");
        map.put("column15", "定价日期");
        map.put("column16", "产品类别");
        map.put("column17", "产线");
        map.put("column18", "品种");
        map.put("column19", "实际发货日期");
        map.put("column20", "原产线");
        map.put("column21", "材质");
        map.put("column22", "规格");
        map.put("column23", "镀层代码");
        map.put("column24", "物料描述");
        map.put("column25", "付款方名称");
        map.put("column26", "订单金额");
        map.put("column27", "系统发票号");
        map.put("column28", "交货单号");
        map.put("column29", "交货单行");
        map.put("column30", "订单号");
        map.put("column31", "订单行");
        map.put("column32", "到站名称");
        map.put("column33", "产品组编码");
        map.put("column34", "合同备注");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"COMPANYNAME","MONTH","FKDAT","FKIMG",
                "KZWI6","MONEY","FKART","ORDER_TYPE_DESCRIBE","PRODUCT_GRADE",
                "SALE_GROUP","SALE_BODY","ORDER_MOUNT","DESTINATION_NAME","CONTRACT_NO",
                "DJ_DATE","PRODUCT_TYPE","NAME","VARIETY","ACTUAL_DATE",
                "PRODUCT_LINE","ATTRIBUTE1","ATTRIBUTE2","ATTRIBUTE9","MATERIAL_INFO",
                "PAYER_NAME","SALE_PRICE","VBELN","VGBEL","VGPOS",
                "ORDER_NO","ORDER_ROW","DZ_NAME","PRODUCT_CODE","ORDER_NOTE"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "销售结算情况（产线）明细";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
        setResponse(response, list, listmap, colOrder, mergeCols, fileName);
    }

    @ApiOperation(value="产品等级价格分布(明细)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "zl", value = "种类", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String")
    })
    @GetMapping(value = "exportJGFB")
    public void exportJGFB(@RequestParam(required = false) List<String> cx,String zl,String startTime, String endTime, HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        startTime=(String) DateUtil.getFirstDayOfMonth(startTime);
        endTime=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String, Object>> list=accountabilityUnitManageService.xsecxfb(cx,zl,startTime,endTime);//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C34", "产品等级价格分布(明细)");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "公司名称");
        map.put("column2", "报表月");
        map.put("column3", "开票日期");
        map.put("column4", "结算量");
        map.put("column5", "结算价格");
        map.put("column6", "结算金额");
        map.put("column7", "发票类型");
        map.put("column8", "订单类型描述");
        map.put("column9", "产品等级");
        map.put("column10", "销售组织");
        map.put("column11", "销售主体");
        map.put("column12", "订单数量");
        map.put("column13", "合同单位");
        map.put("column14", "合同号");
        map.put("column15", "定价日期");
        map.put("column16", "产品类别");
        map.put("column17", "产线");
        map.put("column18", "品种");
        map.put("column19", "实际发货日期");
        map.put("column20", "原产线");
        map.put("column21", "材质");
        map.put("column22", "规格");
        map.put("column23", "镀层代码");
        map.put("column24", "物料描述");
        map.put("column25", "付款方名称");
        map.put("column26", "订单金额");
        map.put("column27", "系统发票号");
        map.put("column28", "交货单号");
        map.put("column29", "交货单行");
        map.put("column30", "订单号");
        map.put("column31", "订单行");
        map.put("column32", "到站名称");
        map.put("column33", "产品组编码");
        map.put("column34", "合同备注");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"COMPANYNAME","MONTH","FKDAT","FKIMG",
                "KZWI6","MONEY","FKART","ORDER_TYPE_DESCRIBE","PRODUCT_GRADE",
                "SALE_GROUP","SALE_BODY","ORDER_MOUNT","DESTINATION_NAME","CONTRACT_NO",
                "DJ_DATE","PRODUCT_TYPE","CXNAME","VARIETY","ACTUAL_DATE",
                "PRODUCT_LINE","ATTRIBUTE1","ATTRIBUTE2","ATTRIBUTE9","MATERIAL_INFO",
                "PAYER_NAME","SALE_PRICE","VBELN","VGBEL","VGPOS",
                "ORDER_NO","ORDER_ROW","DZ_NAME","PRODUCT_CODE","ORDER_NOTE"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "产品等级价格分布(明细)";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
        setResponse(response, list, listmap, colOrder, mergeCols, fileName);
    }



    @ApiOperation(value="发货数据")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "companyName", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "variety", value = "品种", dataType = "String"),
            @ApiImplicitParam(name = "actualStartTime", value = "实际发货开始日期", dataType = "String"),
            @ApiImplicitParam(name = "actualEndTime", value = "实际发货结束日期", dataType = "String"),
            @ApiImplicitParam(name = "delivNumb", value = "交货单号", dataType = "String"),
            @ApiImplicitParam(name = "delivItem", value = "交货单行号", dataType = "String"),
            @ApiImplicitParam(name = "ModifyStartTime", value = "插入开始日期", dataType = "String"),
            @ApiImplicitParam(name = "ModifyEndTime", value = "插入结束日期", dataType = "String")
    })
    @GetMapping(value = "exportFHSJ")
    public String exportFHSJ(String companyName, String variety, String actualStartTime,String actualEndTime,String delivNumb,String delivItem,String ModifyStartTime,String ModifyEndTime, HttpServletResponse response)
    {
        DateFormat format = new SimpleDateFormat("yyyy");
       DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        actualStartTime=(String) DateUtil.getFirstDayOfMonth(actualStartTime);
//        actualEndTime=(String)DateUtil.getLastDayOfMonth(actualEndTime);
        List<Map<String, Object>> list= basicDataExportService.fhsj(companyName,variety,actualStartTime,actualEndTime,delivNumb,delivItem,ModifyStartTime,ModifyEndTime);//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C41", "发货数据明细");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "实际发货日期");
        map.put("column2", "产品等级");
        map.put("column3", "合同单位");
        map.put("column4", "订单类型编码");
        map.put("column5", "订单类型描述");
        map.put("column6", "价格类型");
        map.put("column7", "公司id");
        map.put("column8", "销售组织");
        map.put("column9", "送达方");
        map.put("column10", "终端用户");
        map.put("column11", "物料描述");
        map.put("column12", "交货单");
        map.put("column13", "交货单行");
        map.put("column14", "订单号");
        map.put("column15", "订单行");
        map.put("column16", "订单连接状态");
        map.put("column17", "客户组4");
        map.put("column18", "销售主体");
        map.put("column19", "例会主体");
        map.put("column20", "采购订单编号");
        map.put("column21", "产品分类");
        map.put("column22", "到站");
        map.put("column23", "运输方式");
        map.put("column24", "定价日期");
        map.put("column25", "产品组编码");
        map.put("column26", "产线");
        map.put("column27", "原产线名");
        map.put("column28", "公司名");
        map.put("column29", "品种");
        map.put("column30", "材质");
        map.put("column31", "规格");
        map.put("column32", "镀层代码");
        map.put("column33", "表面质量等级");
        map.put("column34", "加工费");
        map.put("column35", "价政编号");
        map.put("column36", "订单原因描述");
        map.put("column37", "合同备注");
        map.put("column38", "订单量");
        map.put("column39", "订单金额");
        map.put("column40", "发货量");
        map.put("column41", "插入数据库时间");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"ACTUALDATE","PRODUCTGRADE","SALERNAME","ORDERTYPENUM",
                "ORDERTYPEDESCRIBE","PRICETYPE","COMPANYID","SALEGROUP","DESTINATIONNAME",
                "ENDUSER","MATERIALINFO","DELIVNUMB","DELIVITEM","ORDERNO","ORDERROW",
                "STATUS","CUSTOMERSALEBODY","SALEBODY","SESSIONBODY","CONTRACTNO",
                "PRODUCTTYPE","DZNAME","TRANSPORTNAME","DJDATE","PRODUCTCODE",
                "PRODUCTLINE","EXT","COMPANYNAME","VARIETY","ATTRIBUTE1",
                "ATTRIBUTE2","ATTRIBUTE9","ATTRIBUTE11","FARE","JZBH",
                "ORDERREASONNAME","ORDERNOTE","ORDERMOUNT","SALEPRICE",
                "TOTALWEIGHT","GMTMODIFY"};

        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "发货数据明细";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
        setResponse(response, list, listmap, colOrder, mergeCols, fileName);
        return "success";
    }

}
