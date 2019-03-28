package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
//import cn.hbis.erp.core.util.ExportExcel;
import cn.hbis.erp.core.util.ExcelNewUtil;
import cn.hbis.erp.modular.system.entity.ReportSpotPriceBreakdown;
import cn.hbis.erp.modular.system.service.ReportSpotPriceBreakdownService;
import cn.stylefeng.roses.core.util.ToolUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 建材北京市场现货价格
 *
 *
 *
 */
@RestController
@RequestMapping("/reportSpotPriceBreakdown")
public class ReportSpotPriceBreakdownController {
    private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";
    @Autowired
    private ReportSpotPriceBreakdownService reportSpotPriceBreakdownService;

    /**
     * 获取建材北京市场现货价格列表
     *
     *
     */
    @ApiOperation(value = "查询建材北京市场现货价格列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "endMonth" ,value = "记录日期",dataType ="String" ),
            @ApiImplicitParam(name = "company" ,value = "钢厂",dataType ="String" )
    })
    @PostMapping(value = "list")
    public Map list(String endMonth, String company) {
        Map map = new HashMap();
        // 默认查询当日
        if (null == endMonth || "".equals(endMonth)) {
            Calendar calendar = Calendar.getInstance();
            endMonth = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        }
        //默认所有公司
        if (null == company) {
            company = "0";
        }
        String startMonth = DateUtil.getFirstDayOfMonth(endMonth);
        //1-按日查询
        List<ReportSpotPriceBreakdown> dayList = reportSpotPriceBreakdownService.queryDayList(startMonth, endMonth, company);
        List<ReportSpotPriceBreakdown> xunList = reportSpotPriceBreakdownService.queryXunList(startMonth, endMonth, company);
        List<ReportSpotPriceBreakdown> monthList = reportSpotPriceBreakdownService.queryMonthList(startMonth, endMonth, company);
        startMonth = DateUtil.getFirstDayDateOfYear(endMonth);
        List<ReportSpotPriceBreakdown> yearList = reportSpotPriceBreakdownService.queryYearList(startMonth, endMonth, company);
        map.put("dayList", dayList);
        map.put("xunList", xunList);
        map.put("monthList", monthList);
        map.put("yearList", yearList);
        return map;
    }
    @ApiOperation(value = "导出建材北京市场现货价格列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "endMonth" ,value = "记录日期",dataType ="String" ),
            @ApiImplicitParam(name = "company" ,value = "钢厂",dataType ="String" )
    })
    @PostMapping(value = "exportExcel")
    public void exportExcel(Map<String, Object> map1,String endMonth, String company, HttpServletResponse response) {
        DateFormat format = new SimpleDateFormat("yyyy");
        String startMonth = DateUtil.getFirstDayOfMonth(endMonth);
        List<ReportSpotPriceBreakdown> dayList = reportSpotPriceBreakdownService.queryDayList(startMonth, endMonth, company);
        List<ReportSpotPriceBreakdown> xunList = reportSpotPriceBreakdownService.queryXunList(startMonth, endMonth, company);
        List<ReportSpotPriceBreakdown> monthList = reportSpotPriceBreakdownService.queryMonthList(startMonth, endMonth, company);
        startMonth = DateUtil.getFirstDayDateOfYear(endMonth);
        List<ReportSpotPriceBreakdown> yearList = reportSpotPriceBreakdownService.queryYearList(startMonth, endMonth, company);
        List<Map<String, Object>> list = new ArrayList<>();
        String da = "河钢";
        int j =1;
        for(int i = 0; i < 100; i++) { //数据库为空,遍历了100个
            Map<String, Object> temp = new HashMap<>();
            if (i>=0&&i<10){
                temp.put("nature", i+da);
                temp.put("remark", i+1);
                temp.put("mileage", (i + 1) + "描述");
            }else
            if (i>=10&&i<20){
                temp.put("nature", i+da+"ad");
                temp.put("remark", i+1);
                temp.put("mileage", (i + 2) + "描述");
            }else
            if (i>=20){
                temp.put("nature", i+da+"add");
                temp.put("remark", i+1);
                temp.put("mileage", (i + 3) + "描述");
            }

            list.add(temp);
        }
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        //第一行列头（_*表示表头从第几列开始，默认0，_C*表示合并*列，_R*表示合并*行）
        map=new LinkedHashMap<String,Object>();
        map.put("column1_R2", "管养单位");
        map.put("column2_R2", "通车里程（km）");
        map.put("column3_R2"," ");
        listmap.add(map);
        //第二行列头
        map=new LinkedHashMap<String,Object>();
        map.put("column4_4_C4", "高线（HPB300）");
        map.put("column5_8_C4", "盘螺（HRB400/400E）");
        map.put("column6_12_C4", "盘螺（HRB500/500E）");
        map.put("column7_16_C13", "螺纹钢（HRB400/400E）");
        map.put("column8_29_C13", "螺纹钢（HRB500/500E）");
        listmap.add(map);
        //第三行列头（_*表示表头从第几列开始，默认0，_C*表示合并*列，_R*表示合并*行）
        map=new LinkedHashMap<String,Object>();
        map.put("column4_4", "φ6.5mm");
        map.put("column5_5", "φ8mm");
        map.put("column6_6", "φ10mm");
        map.put("column7_7", "φ12mm");
        map.put("column8_8", "φ6mm");
        map.put("column9_9", "φ8mm");
        map.put("column10_10", "φ10mm");
        map.put("column11_11", "φ12mm");
        map.put("column12_12", "φ6mm");
        map.put("column13_13", "φ8mm");
        map.put("column14_14", "φ10mm");
        map.put("column15_15", "φ12mm");

        map.put("column16_16", "φ10mm");
        map.put("column17_17", "φ12mm");
        map.put("column18_18", "φ14mm");
        map.put("column19_19", "φ16mm");
        map.put("column20_20", "φ18mm");
        map.put("column21_21", "φ20mm");
        map.put("column22_22", "φ22mm");
        map.put("column23_23", "φ25mm");
        map.put("column24_24", "φ28mm");
        map.put("column25_25", "φ32mm");
        map.put("column26_26", "φ36mm");
        map.put("column27_27", "φ40mm");
        map.put("column28_28", "φ18-25mm");

        map.put("column29_29", "φ10mm");
        map.put("column30_30", "φ12mm");
        map.put("column31_31", "φ14mm");
        map.put("column32_32", "φ16mm");
        map.put("column33_33", "φ18mm");
        map.put("column34_34", "φ20mm");
        map.put("column35_35", "φ22mm");
        map.put("column36_36", "φ25mm");
        map.put("column37_37", "φ28mm");
        map.put("column38_38", "φ32mm");
        map.put("column39_39", "φ36mm");
        map.put("column40_40", "φ40mm");
        map.put("column41_41", "φ18-25mm");

        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"nature","remark","mileage","tenMileage","tenZb","fiveToTenMileage","fiveToTenZb","threeToFiveMileage","threeToFiveZb","threeLessThanMileage","threeLessThanZb"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={"nature","remark"};
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer filename = null;
        if(ToolUtil.isEmpty(map1.get("fileName"))) {
            filename = new StringBuffer();
            filename.append(format1.format(new Date()));
        } else {
            filename = ((StringBuffer)map1.get("fileName")).insert(0,format1.format(new Date()).substring(0,7));
        }
        if(ToolUtil.isEmpty(map1.get("excel_type"))) {
            filename.append(EXPORT_XLSX_FILE_SUFFIX);
        } else {
            filename.append(map1.get("excel_type"));
        }
        try {
            FileOutputStream out = new FileOutputStream("D:/"+filename.toString());
            exportXlsx(out,filename.toString(),listmap,list,mergeCols,colOrder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void exportXlsx(FileOutputStream out,String fileName,List<Map<String, Object>> headListMap,List<Map<String, Object>> dataListMap,String[] mergeCols,String[] colOrder) {
        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            Map<String,Object> map=new HashMap<String,Object>();
            XSSFSheet sheet1 = wb.createSheet(fileName);
            //创建表头
            ExcelNewUtil.createExcelHeader(wb, sheet1, headListMap);
            //填入表内容
            ExcelNewUtil.fillExcel(headListMap.size(),mergeCols,colOrder,wb,sheet1,dataListMap);
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
