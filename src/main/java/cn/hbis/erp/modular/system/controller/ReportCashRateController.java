package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.constant.state.EnumSummaryType;
import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.ReportCashRateSummary;
import cn.hbis.erp.modular.system.service.ReportCashRateService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import cn.hbis.erp.core.util.ExportExcel;

/**
 * 客户兑现率
 *
 *
 *
 */
@RestController
@RequestMapping("/reportCashRate")
public class ReportCashRateController {
    private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";

    @Autowired
    public ReportCashRateService reportCashRateService;

    /**
     * @Title ReportCashRateAction.cashRateSummary
     * @Description 客户兑现率汇总
     * @author zhangry12988
     * @time 2017-09-19 16:45
     * @param
     * @param
     * @return String
     * @throws
     */
    @ApiOperation(value = "查询客户兑现率汇总列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId" ,value = "子公司",dataType ="String" ),
            @ApiImplicitParam(name = "orderStopDateS" ,value = "交货截至日期开始",dataType ="String" ),
            @ApiImplicitParam(name = "orderStopDateE" ,value = "交货截至日期结束",dataType ="String" ),
            @ApiImplicitParam(name = "recordDate" ,value = "记录日期",dataType ="String" ),
            @ApiImplicitParam(name = "summaryType" ,value = "发货量汇总方式",dataType ="String" )
    })
    @PostMapping(value = "cashRateSummary")
    @Async
    public Map cashRateSummary(String companyId, String orderStopDateS, String orderStopDateE, String recordDate, String summaryType) {
        Map map = new HashMap();
        if (null == orderStopDateS || "".equals(orderStopDateS)) {
            orderStopDateS = DateUtil.getLastMonth();
            orderStopDateE = DateUtil.getLastMonth();
        }
        if(null==recordDate || "".equals(recordDate)) {
            recordDate = DateUtil.getTenthDayOfMonth(10);
        }
        if(null==summaryType || "".equals(summaryType)) {
            summaryType = EnumSummaryType.WITHOUT_ZERO.getCode();
        }
        List<ReportCashRateSummary> list = reportCashRateService.getCashRateSummary(companyId, orderStopDateS, orderStopDateE, recordDate, summaryType);
        map.put("list", list);
//        model.addAttribute("query", query);
//        model.addAttribute("company", EnumCompany.toMap());
//        model.addAttribute("summaryType", EnumSummaryType.toMap());
        return map;
    }

    /**
     * @Title ReportCashRateAction.cashRateCurve
     * @Description 兑现率曲线
     * @author zhangry12988
     * @time 2017-09-28 14:17
     * @param
     * @param
     * @return String
     * @throws
     */
    @ApiOperation(value = "兑现率曲线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId" ,value = "公司",dataType ="String" ),
            @ApiImplicitParam(name = "recordDate" ,value = "记录日期",dataType ="String" ),
            @ApiImplicitParam(name = "summaryType" ,value = "发货量汇总方式",dataType ="String" )
    })
    @PostMapping(value = "cashRateCurve")
    @Async
    public Map cashRateCurve(String companyId, String recordDate, String summaryType) {
        Map map = new HashMap();
        if (null == companyId) {
            companyId = "-1";
        }
        if(null==recordDate || "".equals(recordDate)) {
            recordDate = DateUtil.getTenthDayOfMonth(10);
        }
        List<ReportCashRateSummary> list = reportCashRateService.getCashRateCurve(companyId, recordDate, summaryType);
        //model.addAttribute("query", query);
        map.put("list",list);
//        model.addAttribute("summaryType", EnumSummaryType.toMap());
//        model.addAttribute("company", EnumCompany.toMap());
        return map;
    }
    /**
     * @Title ReportCashRateAction.cashRateSummaryGrade
     * @Description 客户兑现率汇总-产品等级
     * @author wangsf
     * @time 2019-01-23 09:00
     * @param
     * @param
     * @return String
     * @throws
     */
    @ApiOperation(value = "查询客户兑现率汇总-产品等级列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId" ,value = "子公司",dataType ="String" ),
            @ApiImplicitParam(name = "orderStopDateS" ,value = "交货截至日期开始",dataType ="String" ),
            @ApiImplicitParam(name = "orderStopDateE" ,value = "交货截至日期结束",dataType ="String" ),
            @ApiImplicitParam(name = "recordDate" ,value = "记录日期",dataType ="String" ),
            @ApiImplicitParam(name = "summaryType" ,value = "发货量汇总方式",dataType ="String" )
    })
    @PostMapping(value = "cashRateSummaryGrade")
    @Async
    public Map cashRateSummaryGrade(String companyId, String orderStopDateS, String orderStopDateE, String recordDate, String summaryType) {
        Map map = new HashMap();
        if (null == orderStopDateS || "".equals(orderStopDateS)) {
            orderStopDateS = DateUtil.getLastMonth();
            orderStopDateE = DateUtil.getLastMonth();
        }
        if(null==recordDate || "".equals(recordDate)) {
            recordDate = DateUtil.getTenthDayOfMonth(10);
        }
        if(null==summaryType || "".equals(summaryType)) {
            summaryType = EnumSummaryType.WITHOUT_ZERO.getCode();
        }
        List<ReportCashRateSummary> list = reportCashRateService.getCashRateSummaryGrade(companyId, orderStopDateS, orderStopDateE, recordDate, summaryType);
        map.put("list", list);
//        model.addAttribute("query", query);
//        model.addAttribute("company", EnumCompany.toMap());
//        model.addAttribute("summaryType", EnumSummaryType.toMap());
        return map;
    }
    /**
     * @Title ReportCashRateAction.cashRateDetai
     * @Description 客户兑现率明细
     * @author zhangry12988
     * @time 2017-09-19 14:30
     * @param
     * @param
     * @return String
     * @throws
     */
    @ApiOperation(value = "查询客户兑现率明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId" ,value = "子公司",dataType ="String" ),
            @ApiImplicitParam(name = "orderStopDateS" ,value = "交货截至日期开始",dataType ="String" ),
            @ApiImplicitParam(name = "orderStopDateE" ,value = "交货截至日期结束",dataType ="String" ),
            @ApiImplicitParam(name = "recordDate" ,value = "记录日期",dataType ="String" ),
            @ApiImplicitParam(name = "summaryType" ,value = "发货量汇总方式",dataType ="String" )
    })
    @PostMapping(value = "cashRateDetail")
    @Async
    public Map cashRateDetail(String companyId, String orderStopDateS, String orderStopDateE, String recordDate, String summaryType) {
        Map map = new HashMap();
        if (null == orderStopDateS || "".equals(orderStopDateS)) {
            orderStopDateS = DateUtil.getLastMonth();
            orderStopDateE = DateUtil.getLastMonth();
        }
        if(null==recordDate || "".equals(recordDate)) {
            recordDate = DateUtil.getTenthDayOfMonth(10);
        }
        if(null==summaryType || "".equals(summaryType)) {
            summaryType = EnumSummaryType.WITHOUT_ZERO.getCode();
        }
        List<Map> list = reportCashRateService.getCashRateDetail(companyId, orderStopDateS, orderStopDateE, recordDate, summaryType);
        map.put("list", list);
//        model.addAttribute("query", query);
//        model.addAttribute("company", EnumCompany.toMap());
//        model.addAttribute("summaryType", EnumSummaryType.toMap());
        return map;
    }
    /**
     * @Title ReportCashRateAction.cashRateDistribution
     * @Description 客户兑现率分布
     * @author zhangry12988
     * @time 2017-09-25 09:57
     * @param query
     * @param model
     * @return String
     * @throws
     */
    /*@RequestMapping(value ="/cashRateDistribution")
    public String cashRateDistribution(ReportCashRateQuery query, Model model) {
        if(null==query.getRecordDate() || "".equals(query.getRecordDate())) {
            query.setRecordDate(DateUtil.getTenthDayOfMonth(10));
        }
        List<ReportCashRate> list = reportCashRateManager.getCashRateDistribution(query);
        model.addAttribute("list", list);
        model.addAttribute("company", EnumCompany.toMap());
        model.addAttribute("query",query);
        model.addAttribute("summaryType", EnumSummaryType.toMap());
        return BASE_VIEW_PATH + "/cash_rate_distribution";
    }*/




    //=====================================================================================================================

    //=============================================================================================================================

    /**
     * @Title ReportCashRateAction.cashRateSummaryExportGrade
     * @Description 导出客户兑现率汇总-产品等级
     * @author wangsf
     * @time 2019-01-23 08:30
     * @param query
     * @param request
     * @param response
     * @return void
     * @throws
     */
    /*@RequestMapping(value="/cashRateSummaryExportGrade")
    public void cashRateSummaryExportGrade(ReportCashRateQuery query,HttpServletRequest request,HttpServletResponse response) {
        if (null == query.getOrderStopDateS() || "".equals(query.getOrderStopDateS())) {
            query.setOrderStopDateS(DateUtil.getLastMonth());
            query.setOrderStopDateE(DateUtil.getLastMonth());
        }
        if(null==query.getRecordDate() || "".equals(query.getRecordDate())) {
            query.setRecordDate(DateUtil.getTenthDayOfMonth(10));
        }
        //开始导出
        reportCashRateManager.cashRateSummaryExportGrade(query, request, response);
    }*/
    //=============================================================================================================================

    /**
     * @Title ReportCashRateAction.cashRateSummaryExport
     * @Description 导出客户兑现率汇总
     * @author zhangry12988
     * @time 2017-10-16 15:09
     * @param query
     * @param request
     * @param response
     * @return void
     * @throws
     */
    /*@RequestMapping(value="/cashRateSummaryExport")
    public void cashRateSummaryExport(ReportCashRateQuery query,HttpServletRequest request,HttpServletResponse response) {
        if (null == query.getOrderStopDateS() || "".equals(query.getOrderStopDateS())) {
            query.setOrderStopDateS(DateUtil.getLastMonth());
            query.setOrderStopDateE(DateUtil.getLastMonth());
        }
        if(null==query.getRecordDate() || "".equals(query.getRecordDate())) {
            query.setRecordDate(DateUtil.getTenthDayOfMonth(10));
        }
        //开始导出
        reportCashRateManager.cashRateSummaryExport(query, request, response);
    }*/


}
