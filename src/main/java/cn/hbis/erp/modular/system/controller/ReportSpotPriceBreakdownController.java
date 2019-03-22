package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.ReportSpotPriceBreakdown;
import cn.hbis.erp.modular.system.service.ReportSpotPriceBreakdownService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 建材北京市场现货价格
 *
 *
 *
 */
@RestController
@RequestMapping("/reportSpotPriceBreakdown")
public class ReportSpotPriceBreakdownController {

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
        /*if (null == query.getEndMonth()|| "".equals(query.getEndMonth())) {
            Calendar calendar = Calendar.getInstance();
            query.setEndMonth(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        }*/
        //默认所有公司
        /*if (null == query.getCompany()) {
            query.setCompany("0");
        }*/
        //query.setStartMonth(DateUtil.getFirstDayOfMonth(query.getEndMonth()));
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
    @ApiOperation(value = "导出建材北京市场现货价格列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "endMonth" ,value = "记录日期",dataType ="String" ),
            @ApiImplicitParam(name = "company" ,value = "钢厂",dataType ="String" )
    })
    @PostMapping(value = "exportExcel")
    public void exportExcel(String endMonth, String company, HttpServletRequest request, HttpServletResponse response) {
        // 默认查询当日
        if (null == endMonth|| "".equals(endMonth)) {
            Calendar calendar = Calendar.getInstance();
            endMonth = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        }
        //默认所有公司
        if (null == company || "".equals(company)) {
            company = "0";
        }
        String startMonth = DateUtil.getFirstDayOfMonth(endMonth);
        //开始导出
        reportSpotPriceBreakdownService.exportExcel(startMonth, endMonth, company, request, response);
    }
}
