/**
 * FileName: ExportController
 * Author:   zhangb
 * Date:     2019/5/8 8:42
 */
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.core.util.ExcelNewUtil;
import cn.hbis.erp.modular.system.service.ReportProductClassLevelService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ApiOperation(value="品种钢完成情况导出",produces="application/octet-stream")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "dw", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "cx", value = "产线", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String")
    })
    @GetMapping("exportPzgCx")
    public void exportPzgCx(String dw, List cx ,String startTime,String endTime, HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        startTime=(String) DateUtil.getFirstDayOfMonth(startTime);
        endTime=(String)DateUtil.getLastDayOfMonth(endTime);
        System.out.println("进查"+format1.format(new Date()));
        List<Map<String, Object>> list=reportProductClassLevelService.cxexcel(dw,cx,startTime +" 00:00:00",endTime+" 23:59:59");//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        System.out.println("出查"+format1.format(new Date()));
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C13", "品种钢完成情况(产线)");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "公司");
        map.put("column2", "公司ID");
        map.put("column3", "销售主体2");
        map.put("column4", "产品等级");
        map.put("column5", "开票数量");
        map.put("column6", "开票日期");
        map.put("column7", "订单量");
        map.put("column8", "订单类型描述");
        map.put("column9", "合同单位");
        map.put("column10", "销售组织");
        map.put("column11", "销售主体");
        map.put("column12", "品种");
        map.put("column13", "产线");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"COMPANYNAME","COMPANY_ID","SALE_BODY",
                "PRODUCT_GRADE","FKIMG","FKDAT","ORDER_MOUNT","ORDER_TYPE_DESCRIBE",
                "SALER_NAME","SALE_GROUP","SS","NAME","PRODUCT_LINE"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={};

        String fileName = "品种钢（产线）";
        fileName = fileName.concat(format1.format(new Date()).substring(0,8));
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        OutputStream out = null;
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

    public void exportXlsx(OutputStream out, String fileName, List<Map<String, Object>> headListMap, List<Map<String, Object>> dataListMap, String[] mergeCols, String[] colOrder, HttpServletResponse response) {
        XSSFWorkbook wb = new XSSFWorkbook();
        export(out, fileName, headListMap, dataListMap, mergeCols, colOrder, wb);
    }

    static void export(OutputStream out, String fileName, List<Map<String, Object>> headListMap, List<Map<String, Object>> dataListMap, String[] mergeCols, String[] colOrder, XSSFWorkbook wb) {
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println("进导"+format1.format(new Date()));
        try {
            Map<String,Object> map=new HashMap<String,Object>();
            XSSFSheet sheet1 = wb.createSheet(fileName);

            //创建表头
            ExcelNewUtil.createExcelHeader(wb, sheet1, headListMap);
            //填入表内容
            ExcelNewUtil.fillExcel(headListMap.size(),mergeCols,colOrder,wb,sheet1,dataListMap);
            //导出
            wb.write(out);
            System.out.println("出导"+format1.format(new Date()));
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
