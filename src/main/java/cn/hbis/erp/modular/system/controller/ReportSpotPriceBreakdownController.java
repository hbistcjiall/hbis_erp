package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.ReportSpotPriceBreakdown;
import cn.hbis.erp.modular.system.service.ReportSpotPriceBreakdownService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import cn.hbis.erp.core.util.ExportExcel;

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
    @Async
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
        //map.put("query", query);
        return map;
    }
    /*@ApiOperation(value = "导出建材北京市场现货价格列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "endMonth" ,value = "记录日期",dataType ="String" ),
            @ApiImplicitParam(name = "company" ,value = "钢厂",dataType ="String" )
    })
    @PostMapping(value = "exportExcel")
    public void exportExcel(Map<String, Object> map,String endMonth, String company, HttpServletResponse response) {
        String startMonth = DateUtil.getFirstDayOfMonth(endMonth);
        List<ReportSpotPriceBreakdown> dayList = reportSpotPriceBreakdownService.queryDayList(startMonth, endMonth, company);
        List<ReportSpotPriceBreakdown> xunList = reportSpotPriceBreakdownService.queryXunList(startMonth, endMonth, company);
        List<ReportSpotPriceBreakdown> monthList = reportSpotPriceBreakdownService.queryMonthList(startMonth, endMonth, company);
        startMonth = DateUtil.getFirstDayDateOfYear(endMonth);
        List<ReportSpotPriceBreakdown> yearList = reportSpotPriceBreakdownService.queryYearList(startMonth, endMonth, company);

        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 0; i < dayList.size(); i++) {
            Map<String, Object> temp_ = new HashMap<>();
            temp_.put("time", dayList.get(i).getOrderDay());
            if ("CHENGGANG".equals(dayList.get(i).getCompany())){
                temp_.put("company", "承钢");
            } else if ("XUANGANG".equals(dayList.get(i).getCompany())){
                temp_.put("company", "宣钢");
            } else if (dayList.get(i).getCompany() == null){
                temp_.put("company", "小计");
            }
            if ("1".equals(dayList.get(i).getType())){
                temp_.put("type", "结算数量");
            } else if ("2".equals(dayList.get(i).getType())){
                temp_.put("type", "结算价格");
            }
            temp_.put("gxData1", dayList.get(i).getGxData1());
            temp_.put("gxData2", dayList.get(i).getGxData2());
            temp_.put("gxData3", dayList.get(i).getGxData3());
            temp_.put("gxData4", dayList.get(i).getGxData4());
            temp_.put("plData1", dayList.get(i).getPlData1());
            temp_.put("plData2", dayList.get(i).getPlData2());
            temp_.put("plData3", dayList.get(i).getPlData3());
            temp_.put("plData4", dayList.get(i).getPlData4());
            temp_.put("plData5", dayList.get(i).getPlData5());
            temp_.put("plData6", dayList.get(i).getPlData6());
            temp_.put("plData7", dayList.get(i).getPlData7());
            temp_.put("plData8", dayList.get(i).getPlData8());
            temp_.put("lwData1", dayList.get(i).getLwData1());
            temp_.put("lwData2", dayList.get(i).getLwData2());
            temp_.put("lwData3", dayList.get(i).getLwData3());
            temp_.put("lwData4", dayList.get(i).getLwData4());
            temp_.put("lwData5", dayList.get(i).getLwData5());
            temp_.put("lwData6", dayList.get(i).getLwData6());
            temp_.put("lwData7", dayList.get(i).getLwData7());
            temp_.put("lwData8", dayList.get(i).getLwData8());
            temp_.put("lwData9", dayList.get(i).getLwData9());
            temp_.put("lwData10", dayList.get(i).getLwData10());
            temp_.put("lwData11", dayList.get(i).getLwData11());
            temp_.put("lwData12", dayList.get(i).getLwData12());
            temp_.put("lwData13", dayList.get(i).getLwData13());
            temp_.put("lwData14", dayList.get(i).getLwData14());
            temp_.put("lwData15", dayList.get(i).getLwData15());
            temp_.put("lwData16", dayList.get(i).getLwData16());
            temp_.put("lwData17", dayList.get(i).getLwData17());
            temp_.put("lwData18", dayList.get(i).getLwData18());
            temp_.put("lwData19", dayList.get(i).getLwData19());
            temp_.put("lwData20", dayList.get(i).getLwData20());
            temp_.put("lwData21", dayList.get(i).getLwData21());
            temp_.put("lwData22", dayList.get(i).getLwData22());
            temp_.put("lwData23", dayList.get(i).getLwData23());
            temp_.put("lwData24", dayList.get(i).getLwData24());
            temp_.put("lwData25", dayList.get(i).getLwData25());
            temp_.put("lwData26", dayList.get(i).getLwData26());
            list.add(temp_);
        }
        for(int i = 0; i < xunList.size(); i++) {
            Map<String, Object> temp_ = new HashMap<>();
            temp_.put("time", xunList.get(i).getOrderXun());
            if ("CHENGGANG".equals(dayList.get(i).getCompany())){
                temp_.put("company", "承钢");
            } else if ("XUANGANG".equals(dayList.get(i).getCompany())){
                temp_.put("company", "宣钢");
            } else if (dayList.get(i).getCompany() == null){
                temp_.put("company", "小计");
            }
            if ("1".equals(dayList.get(i).getType())){
                temp_.put("type", "结算数量");
            } else if ("2".equals(dayList.get(i).getType())){
                temp_.put("type", "结算价格");
            }
            temp_.put("gxData1", xunList.get(i).getGxData1());
            temp_.put("gxData2", xunList.get(i).getGxData2());
            temp_.put("gxData3", xunList.get(i).getGxData3());
            temp_.put("gxData4", xunList.get(i).getGxData4());
            temp_.put("plData1", xunList.get(i).getPlData1());
            temp_.put("plData2", xunList.get(i).getPlData2());
            temp_.put("plData3", xunList.get(i).getPlData3());
            temp_.put("plData4", xunList.get(i).getPlData4());
            temp_.put("plData5", xunList.get(i).getPlData5());
            temp_.put("plData6", xunList.get(i).getPlData6());
            temp_.put("plData7", xunList.get(i).getPlData7());
            temp_.put("plData8", xunList.get(i).getPlData8());
            temp_.put("lwData1", xunList.get(i).getLwData1());
            temp_.put("lwData2", xunList.get(i).getLwData2());
            temp_.put("lwData3", xunList.get(i).getLwData3());
            temp_.put("lwData4", xunList.get(i).getLwData4());
            temp_.put("lwData5", xunList.get(i).getLwData5());
            temp_.put("lwData6", xunList.get(i).getLwData6());
            temp_.put("lwData7", xunList.get(i).getLwData7());
            temp_.put("lwData8", xunList.get(i).getLwData8());
            temp_.put("lwData9", xunList.get(i).getLwData9());
            temp_.put("lwData10", xunList.get(i).getLwData10());
            temp_.put("lwData11", xunList.get(i).getLwData11());
            temp_.put("lwData12", xunList.get(i).getLwData12());
            temp_.put("lwData13", xunList.get(i).getLwData13());
            temp_.put("lwData14", xunList.get(i).getLwData14());
            temp_.put("lwData15", xunList.get(i).getLwData15());
            temp_.put("lwData16", xunList.get(i).getLwData16());
            temp_.put("lwData17", xunList.get(i).getLwData17());
            temp_.put("lwData18", xunList.get(i).getLwData18());
            temp_.put("lwData19", xunList.get(i).getLwData19());
            temp_.put("lwData20", xunList.get(i).getLwData20());
            temp_.put("lwData21", xunList.get(i).getLwData21());
            temp_.put("lwData22", xunList.get(i).getLwData22());
            temp_.put("lwData23", xunList.get(i).getLwData23());
            temp_.put("lwData24", xunList.get(i).getLwData24());
            temp_.put("lwData25", xunList.get(i).getLwData25());
            temp_.put("lwData26", xunList.get(i).getLwData26());
            list.add(temp_);
        }
        for(int i = 0; i < monthList.size(); i++) {
            Map<String, Object> temp_ = new HashMap<>();
            temp_.put("time", monthList.get(i).getOrderMonth() + "月");
            if ("CHENGGANG".equals(dayList.get(i).getCompany())){
                temp_.put("company", "承钢");
            } else if ("XUANGANG".equals(dayList.get(i).getCompany())){
                temp_.put("company", "宣钢");
            } else if (dayList.get(i).getCompany() == null){
                temp_.put("company", "小计");
            }
            if ("1".equals(dayList.get(i).getType())){
                temp_.put("type", "结算数量");
            } else if ("2".equals(dayList.get(i).getType())){
                temp_.put("type", "结算价格");
            }
            temp_.put("gxData1", monthList.get(i).getGxData1());
            temp_.put("gxData2", monthList.get(i).getGxData2());
            temp_.put("gxData3", monthList.get(i).getGxData3());
            temp_.put("gxData4", monthList.get(i).getGxData4());
            temp_.put("plData1", monthList.get(i).getPlData1());
            temp_.put("plData2", monthList.get(i).getPlData2());
            temp_.put("plData3", monthList.get(i).getPlData3());
            temp_.put("plData4", monthList.get(i).getPlData4());
            temp_.put("plData5", monthList.get(i).getPlData5());
            temp_.put("plData6", monthList.get(i).getPlData6());
            temp_.put("plData7", monthList.get(i).getPlData7());
            temp_.put("plData8", monthList.get(i).getPlData8());
            temp_.put("lwData1", monthList.get(i).getLwData1());
            temp_.put("lwData2", monthList.get(i).getLwData2());
            temp_.put("lwData3", monthList.get(i).getLwData3());
            temp_.put("lwData4", monthList.get(i).getLwData4());
            temp_.put("lwData5", monthList.get(i).getLwData5());
            temp_.put("lwData6", monthList.get(i).getLwData6());
            temp_.put("lwData7", monthList.get(i).getLwData7());
            temp_.put("lwData8", monthList.get(i).getLwData8());
            temp_.put("lwData9", monthList.get(i).getLwData9());
            temp_.put("lwData10", monthList.get(i).getLwData10());
            temp_.put("lwData11", monthList.get(i).getLwData11());
            temp_.put("lwData12", monthList.get(i).getLwData12());
            temp_.put("lwData13", monthList.get(i).getLwData13());
            temp_.put("lwData14", monthList.get(i).getLwData14());
            temp_.put("lwData15", monthList.get(i).getLwData15());
            temp_.put("lwData16", monthList.get(i).getLwData16());
            temp_.put("lwData17", monthList.get(i).getLwData17());
            temp_.put("lwData18", monthList.get(i).getLwData18());
            temp_.put("lwData19", monthList.get(i).getLwData19());
            temp_.put("lwData20", monthList.get(i).getLwData20());
            temp_.put("lwData21", monthList.get(i).getLwData21());
            temp_.put("lwData22", monthList.get(i).getLwData22());
            temp_.put("lwData23", monthList.get(i).getLwData23());
            temp_.put("lwData24", monthList.get(i).getLwData24());
            temp_.put("lwData25", monthList.get(i).getLwData25());
            temp_.put("lwData26", monthList.get(i).getLwData26());
            list.add(temp_);
        }
        for(int i = 0; i < yearList.size(); i++) {
            Map<String, Object> temp_ = new HashMap<>();
            temp_.put("time", yearList.get(i).getOrderYear() + "年");
            if ("CHENGGANG".equals(dayList.get(i).getCompany())){
                temp_.put("company", "承钢");
            } else if ("XUANGANG".equals(dayList.get(i).getCompany())){
                temp_.put("company", "宣钢");
            } else if (dayList.get(i).getCompany() == null){
                temp_.put("company", "小计");
            }
            if ("1".equals(dayList.get(i).getType())){
                temp_.put("type", "结算数量");
            } else if ("2".equals(dayList.get(i).getType())){
                temp_.put("type", "结算价格");
            }
            temp_.put("gxData1", yearList.get(i).getGxData1());
            temp_.put("gxData2", yearList.get(i).getGxData2());
            temp_.put("gxData3", yearList.get(i).getGxData3());
            temp_.put("gxData4", yearList.get(i).getGxData4());
            temp_.put("plData1", yearList.get(i).getPlData1());
            temp_.put("plData2", yearList.get(i).getPlData2());
            temp_.put("plData3", yearList.get(i).getPlData3());
            temp_.put("plData4", yearList.get(i).getPlData4());
            temp_.put("plData5", yearList.get(i).getPlData5());
            temp_.put("plData6", yearList.get(i).getPlData6());
            temp_.put("plData7", yearList.get(i).getPlData7());
            temp_.put("plData8", yearList.get(i).getPlData8());
            temp_.put("lwData1", yearList.get(i).getLwData1());
            temp_.put("lwData2", yearList.get(i).getLwData2());
            temp_.put("lwData3", yearList.get(i).getLwData3());
            temp_.put("lwData4", yearList.get(i).getLwData4());
            temp_.put("lwData5", yearList.get(i).getLwData5());
            temp_.put("lwData6", yearList.get(i).getLwData6());
            temp_.put("lwData7", yearList.get(i).getLwData7());
            temp_.put("lwData8", yearList.get(i).getLwData8());
            temp_.put("lwData9", yearList.get(i).getLwData9());
            temp_.put("lwData10", yearList.get(i).getLwData10());
            temp_.put("lwData11", yearList.get(i).getLwData11());
            temp_.put("lwData12", yearList.get(i).getLwData12());
            temp_.put("lwData13", yearList.get(i).getLwData13());
            temp_.put("lwData14", yearList.get(i).getLwData14());
            temp_.put("lwData15", yearList.get(i).getLwData15());
            temp_.put("lwData16", yearList.get(i).getLwData16());
            temp_.put("lwData17", yearList.get(i).getLwData17());
            temp_.put("lwData18", yearList.get(i).getLwData18());
            temp_.put("lwData19", yearList.get(i).getLwData19());
            temp_.put("lwData20", yearList.get(i).getLwData20());
            temp_.put("lwData21", yearList.get(i).getLwData21());
            temp_.put("lwData22", yearList.get(i).getLwData22());
            temp_.put("lwData23", yearList.get(i).getLwData23());
            temp_.put("lwData24", yearList.get(i).getLwData24());
            temp_.put("lwData25", yearList.get(i).getLwData25());
            temp_.put("lwData26", yearList.get(i).getLwData26());
            list.add(temp_);
        }

        ExportExcel<List<Map<String, Object>>> exportExcel = new ExportExcel<>();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer filename = null;
        if(ToolUtil.isEmpty(map.get("fileName"))) {
            filename = new StringBuffer();
            filename.append("建材北京市场现货价格分类汇总");
            filename.append(format.format(new Date()));
        } else {
            filename = ((StringBuffer)map.get("fileName")).insert(0,format.format(new Date()).substring(0,7));
        }
        if(ToolUtil.isEmpty(map.get("excel_type"))) {
            filename.append(EXPORT_XLSX_FILE_SUFFIX);
        } else {
            filename.append(map.get("excel_type"));
        }

        try {
            FileOutputStream out = new FileOutputStream("D:/"+filename.toString());
            exportExcel.exportXSExcelByColumn(filename.toString(), new String[] {"时间","单位","", "φ6.5mm", "φ8mm","φ10mm","φ12mm","φ6.5mm", "φ8mm","φ10mm","φ12mm",
                            "φ6.5mm", "φ8mm","φ10mm","φ12mm","φ10mm", "φ12mm","φ14mm","φ16mm","φ18mm", "φ20mm","φ22mm","φ25mm","φ28mm", "φ32mm","φ36mm","φ40mm","18-25",
                            "φ10mm", "φ12mm","φ14mm","φ16mm","φ18mm", "φ20mm","φ22mm","φ25mm","φ28mm", "φ32mm","φ36mm","φ40mm","18-25"},
                    new String[] {"time", "company", "type","gxData1","gxData2","gxData3","gxData4","plData1","plData2","plData3","plData4","plData5","plData6","plData7","plData8",
                            "lwData1","lwData2","lwData3","lwData4","lwData5","lwData6","lwData7","lwData8","lwData9","lwData10","lwData11","lwData12","lwData13","lwData14","lwData15",
                            "lwData16","lwData17","lwData18","lwData19","lwData20","lwData21","lwData22","lwData23","lwData24","lwData25","lwData26"},
                    list, out ,null);
        } catch (IOException e) {
        }
    }*/
}
